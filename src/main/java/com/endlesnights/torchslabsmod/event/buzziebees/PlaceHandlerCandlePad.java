package com.endlesnights.torchslabsmod.event.buzziebees;

import java.util.Collection;
import java.util.HashMap;

import com.bagel.buzzierbees.common.blocks.CandleBlock;
import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.blocks.buzzierbees.BlockCandleSlab;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=TorchSlabsMod.MODID)
public class PlaceHandlerCandlePad 
{
	private static final HashMap<ResourceLocation,Block> PLACE_ENTRIES = new HashMap<>();
	
	@SubscribeEvent
	public static void onBlockEntityPlace(RightClickBlock event)
	{	
		ItemStack held = event.getItemStack();
		ResourceLocation rl = held.getItem().getRegistryName();

		if(PLACE_ENTRIES.containsKey(rl))
			placeCandle(event, held, PLACE_ENTRIES.get(rl));
	}

	private static void placeCandle(RightClickBlock event, ItemStack held, Block block)
	{		
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		World world = event.getWorld();		

		
		if(world.getBlockState(pos).getBlock() instanceof LilyPadBlock && face == Direction.UP)
		{	
			
			world.setBlockState(pos, block.getDefaultState()
					.with(HorizontalBlock.HORIZONTAL_FACING, event.getPlayer().getHorizontalFacing())
					);
			
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), block.getSoundType(world.getBlockState(pos)).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			event.getPlayer().swingArm(event.getHand());
			
			if(!event.getPlayer().isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}
		else if(world.getBlockState(pos).getBlock() == block && world.getBlockState(pos).get(BlockCandleSlab.CANDLES) < 4 )
		{

				
			BlockState placeState = world.getBlockState(pos);
			
			world.setBlockState(pos,placeState
					.with(CandleBlock.CANDLES, placeState.get(CandleBlock.CANDLES) + 1)
					.with(CandleBlock.FACING, placeState.get(CandleBlock.FACING))
					);

			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), block.getSoundType(world.getBlockState(pos)).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			event.getPlayer().swingArm(event.getHand());
			
			if(!event.getPlayer().isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}
	}
	
	public static void registerPlaceEntry(ResourceLocation itemName, Block candleSlab)
	{
		if(!PLACE_ENTRIES.containsKey(itemName))
			PLACE_ENTRIES.put(itemName, candleSlab);
	}

	public static Collection<Block> getPlaceEntryBlocks()
	{
		return PLACE_ENTRIES.values();
	}
}
