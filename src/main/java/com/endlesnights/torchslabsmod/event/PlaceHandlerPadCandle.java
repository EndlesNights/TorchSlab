package com.endlesnights.torchslabsmod.event;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Supplier;

import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.blocks.vanilla.BlockPadCandle;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid=TorchSlabsMod.MODID)
public class PlaceHandlerPadCandle {
	private static final HashMap<ResourceLocation,Supplier<Block>> PLACE_ENTRIES = new HashMap<>();
	
	@SubscribeEvent
	public static void onBlockEntityPlace(RightClickBlock event)
	{	
		ItemStack held = event.getItemStack();
		ResourceLocation rl = ForgeRegistries.ITEMS.getKey(held.getItem());

		if(PLACE_ENTRIES.containsKey(rl))
			placeCandle(event, held, PLACE_ENTRIES.get(rl).get());
	}

	private static void placeCandle(RightClickBlock event, ItemStack held, Block block)
	{		
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		Level world = event.getLevel();
		SoundType soundType;

		if(world.getBlockState(pos).getBlock() instanceof WaterlilyBlock && face == Direction.UP)
		{	
			
			world.setBlockAndUpdate(pos, block.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, event.getEntity().getDirection()));
			soundType = block.getSoundType(block.defaultBlockState(), world, pos, event.getEntity());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundSource.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getEntity().swing(event.getHand());
			
			if(!event.getEntity().isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}
		else if(world.getBlockState(pos).getBlock() == block && world.getBlockState(pos).getValue(CandleBlock.CANDLES) < 4  ) {
			
			
			BlockState placeState = world.getBlockState(pos);
			world.setBlockAndUpdate(pos,placeState
					.setValue(HorizontalDirectionalBlock.FACING, placeState.getValue(BlockPadCandle.FACING))
					.setValue(CandleBlock.LIT, placeState.getValue(BlockPadCandle.LIT))
					.setValue(CandleBlock.CANDLES, placeState.getValue(BlockPadCandle.CANDLES) + 1)
					.setValue(CandleBlock.WATERLOGGED, placeState.getValue(BlockPadCandle.WATERLOGGED))
					);
			
			soundType = block.getSoundType(block.defaultBlockState(), world, pos, event.getEntity());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundSource.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getEntity().swing(event.getHand());
			
			if(!event.getEntity().isCreative())
				held.shrink(1);
			event.setCanceled(true);	
		}
	}
	
	public static void registerPlaceEntry(ResourceLocation itemName, Supplier<Block> padLightSupplier)
	{
		if(!PLACE_ENTRIES.containsKey(itemName)  && padLightSupplier != null)
			PLACE_ENTRIES.put(itemName, padLightSupplier);
	}
}
