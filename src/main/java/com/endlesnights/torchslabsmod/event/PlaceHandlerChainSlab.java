package com.endlesnights.torchslabsmod.event;

import java.util.Collection;
import java.util.HashMap;

import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.blocks.vanilla.BlockChainSlab;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EndRodBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid=TorchSlabsMod.MODID)
public class PlaceHandlerChainSlab 
{
private static final HashMap<ResourceLocation,Block> PLACE_ENTRIES = new HashMap<>();
	
	@SubscribeEvent
	public static void onBlockEntityPlace(RightClickBlock event)
	{	
		ItemStack held = event.getItemStack();
		ResourceLocation rl = ForgeRegistries.ITEMS.getKey(held.getItem());

		if(PLACE_ENTRIES.containsKey(rl))
			placeChain(event, held, PLACE_ENTRIES.get(rl));
	}

	@SuppressWarnings("deprecation")
	private static void placeChain(RightClickBlock event, ItemStack held, Block block)
	{		
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		BlockPos placeAt = pos.relative(face);
		Level world = event.getLevel();
		SoundType soundType;
		
		if(
				((world.getBlockState(pos).getBlock() instanceof SlabBlock
				&& ((world.getBlockState(pos).getValue(SlabBlock.TYPE) == SlabType.BOTTOM && face == Direction.UP)
				|| (world.getBlockState(pos).getValue(SlabBlock.TYPE) == SlabType.TOP) && face == Direction.DOWN))
						
				|| (world.getBlockState(pos).getBlock() instanceof BlockChainSlab && (face == Direction.UP ||  face == Direction.DOWN))
				//|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(pos).getBlock() instanceof FenceSlabBlock)
				//|| (world.getBlockState(pos).getBlock() instanceof BlockChainSlab && face == Direction.DOWN)
						)
				&& (world.isEmptyBlock(placeAt) || world.getFluidState(placeAt).getType() == Fluids.WATER || world.getFluidState(placeAt).getType() == Fluids.FLOWING_WATER) )
		{			

			if (block instanceof SimpleWaterloggedBlock)
				world.setBlockAndUpdate(placeAt, block.defaultBlockState()
						.setValue(BlockChainSlab.HANGING, world.getBlockState(placeAt.above()).getBlock() instanceof SlabBlock && world.getBlockState(placeAt.above()).getValue(SlabBlock.TYPE) == SlabType.TOP)
						.setValue(BlockChainSlab.HANGING_UP, Block.canSupportCenter(world, placeAt.above(), Direction.DOWN))
						.setValue(BlockChainSlab.HANGING_DOWN, Block.canSupportCenter(world, placeAt.below(), Direction.DOWN))
						.setValue(BlockStateProperties.WATERLOGGED, (world.getFluidState(placeAt).getType() == Fluids.WATER) )
						);
			
			else
				world.setBlockAndUpdate(placeAt, block.defaultBlockState()
						.setValue(BlockChainSlab.HANGING, world.getBlockState(placeAt.above()).getBlock() instanceof SlabBlock && world.getBlockState(placeAt.above()).getValue(SlabBlock.TYPE) == SlabType.TOP)
						.setValue(BlockChainSlab.HANGING_UP, Block.canSupportCenter(world, placeAt.above(), Direction.DOWN))
						.setValue(BlockChainSlab.HANGING_DOWN, Block.canSupportCenter(world, placeAt.below(), Direction.DOWN))
						);

			
			soundType = block.getSoundType(block.defaultBlockState(), world, pos, event.getEntity());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundSource.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getEntity().swing(event.getHand());

			if(!event.getEntity().isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}

	}
	
	public static void registerPlaceEntry(ResourceLocation itemName, Block chainSlab)
	{
		if(!PLACE_ENTRIES.containsKey(itemName) && chainSlab != null)
			PLACE_ENTRIES.put(itemName, chainSlab);
	}
}
