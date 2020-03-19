package com.endlesnights.torchslabsmod.event.buzziebees;

import java.util.Collection;
import java.util.HashMap;

import com.bagel.buzzierbees.common.blocks.CandleBlock;
import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.blocks.buzzierbees.BlockCandleSlab;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SlabBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=TorchSlabsMod.MODID)
public class PlaceHandlerCandleSlab
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
		BlockPos placeAt = pos.offset(face);
		World world = event.getWorld();		

		if(world.getBlockState(pos).getBlock() instanceof SlabBlock && face == Direction.UP 
				&& world.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.BOTTOM 
				&& (world.isAirBlock(placeAt) || world.getFluidState(placeAt).getFluid() == Fluids.WATER || world.getFluidState(placeAt).getFluid() == Fluids.FLOWING_WATER) )
		{	
			if (block instanceof IWaterLoggable)
				world.setBlockState(placeAt, block.getDefaultState()
						.with(BlockStateProperties.WATERLOGGED, (world.getFluidState(placeAt).getFluid() == Fluids.WATER) )
						.with(HorizontalBlock.HORIZONTAL_FACING, event.getPlayer().getHorizontalFacing())
						);
			else
				world.setBlockState(placeAt, block.getDefaultState()
						.with(HorizontalBlock.HORIZONTAL_FACING, event.getPlayer().getHorizontalFacing())
						);

			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), block.getSoundType(world.getBlockState(pos)).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			event.getPlayer().swingArm(event.getHand());
			
			if(!event.getPlayer().isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}
		else if((world.getBlockState(pos).getBlock() instanceof SlabBlock && face == Direction.UP 
				&& world.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.BOTTOM 
				&& (world.getBlockState(placeAt).getBlock() == block && world.getBlockState(placeAt).get(BlockCandleSlab.CANDLES) < 4 ))
				|| (world.getBlockState(pos).getBlock() == block && world.getBlockState(pos).get(BlockCandleSlab.CANDLES) < 4 ))
		{
			if(world.getBlockState(pos).getBlock() == block)
				placeAt = pos;
				
			BlockState placeState = world.getBlockState(placeAt);
			
			world.setBlockState(placeAt,placeState
					.with(CandleBlock.CANDLES, placeState.get(CandleBlock.CANDLES) + 1)
					.with(CandleBlock.WATERLOGGED, placeState.get(CandleBlock.WATERLOGGED))
					.with(CandleBlock.FACING, placeState.get(CandleBlock.FACING))
					);

			world.playSound(null, placeAt.getX(), placeAt.getY(), placeAt.getZ(), block.getSoundType(world.getBlockState(placeAt)).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
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