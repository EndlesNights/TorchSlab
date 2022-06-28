package com.endlesnights.torchslabsmod.event;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Supplier;

import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.blocks.vanilla.BlockEndRodSlab;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EndRodBlock;
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

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid=TorchSlabsMod.MODID)
public class PlaceHandlerEndRod
{
	private static final HashMap<ResourceLocation, Supplier<Block>> PLACE_ENTRIES = new HashMap<>();
	
	@SubscribeEvent
	public static void onBlockEntityPlace(RightClickBlock event)
	{	
		ItemStack held = event.getItemStack();
		ResourceLocation rl = ForgeRegistries.ITEMS.getKey(held.getItem());

		if(PLACE_ENTRIES.containsKey(rl))
			placeRod(event, held, PLACE_ENTRIES.get(rl).get());
	}

	private static void placeRod(RightClickBlock event, ItemStack held, Block block)
	{		
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		BlockPos placeAt = pos.relative(face);
		Level world = event.getWorld();		
		SoundType soundType;
		
		if(world.getBlockState(pos).getBlock() instanceof SlabBlock && face == Direction.UP 
				&& world.getBlockState(pos).getValue(SlabBlock.TYPE) == SlabType.BOTTOM 
				&& (world.isEmptyBlock(placeAt) || world.getFluidState(placeAt).getType() == Fluids.WATER || world.getFluidState(placeAt).getType() == Fluids.FLOWING_WATER) )
		{	

			world.setBlockAndUpdate(placeAt, block.defaultBlockState());
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(BlockStateProperties.WATERLOGGED, false));
			
			rodPlaced(event, held, block, pos);
		}
		else if(world.getBlockState(pos).getBlock() instanceof SlabBlock && face == Direction.DOWN 
				&& world.getBlockState(pos).getValue(SlabBlock.TYPE) == SlabType.TOP 
				&& (world.isEmptyBlock(placeAt) || world.getFluidState(placeAt).getType() == Fluids.WATER || world.getFluidState(placeAt).getType() == Fluids.FLOWING_WATER) )
		{	

			world.setBlockAndUpdate(placeAt, block.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.DOWN));
			world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(BlockStateProperties.WATERLOGGED, false));
			
			rodPlaced(event, held, block, pos);
		}
		else if( (face == Direction.UP || face == Direction.DOWN)
				&& world.getBlockState(pos).getBlock() instanceof BlockEndRodSlab)
		{
			if(world.getBlockState(pos).getValue(DirectionalBlock.FACING) == Direction.DOWN && face == Direction.DOWN )
			{
				if(!world.getBlockState(pos).getValue(BlockEndRodSlab.CONDITIONAL)
						&& ((world.getBlockState(pos.below()).getBlock() instanceof SlabBlock && world.getBlockState(pos.below()).getValue(SlabBlock.TYPE) == SlabType.BOTTOM)
						|| (world.isEmptyBlock(pos.below()) || world.getFluidState(pos.below()).getType() == Fluids.WATER || world.getFluidState(pos.below()).getType() == Fluids.FLOWING_WATER)))
					{
						world.setBlockAndUpdate(pos, block.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.DOWN).setValue(BlockEndRodSlab.CONDITIONAL, true));
						
						if(world.getBlockState(pos.below()).getBlock() instanceof SlabBlock)
							world.setBlockAndUpdate(pos.below(), world.getBlockState(pos.below()).setValue(BlockStateProperties.WATERLOGGED, false));
						else
							world.setBlockAndUpdate(pos.below(), Blocks.AIR.defaultBlockState());
						
						rodPlaced(event, held, block, pos);
						return;
					}
				else if((world.isEmptyBlock(placeAt.below()) || world.getFluidState(placeAt.below()).getType() == Fluids.WATER || world.getFluidState(placeAt.below()).getType() == Fluids.FLOWING_WATER)
						&&(world.isEmptyBlock(placeAt)))
				{
					world.setBlockAndUpdate(placeAt.below(), block.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.DOWN));
					rodPlaced(event, held, block, placeAt.below());
					return;
				}
			}
			else if(world.getBlockState(pos).getValue(DirectionalBlock.FACING) == Direction.UP && face == Direction.UP)
			{
				if(!world.getBlockState(pos).getValue(BlockEndRodSlab.CONDITIONAL) 
						&& ((world.getBlockState(pos.above()).getBlock() instanceof SlabBlock && world.getBlockState(pos.above()).getValue(SlabBlock.TYPE) == SlabType.TOP)
						|| (world.isEmptyBlock(pos.above()) || world.getFluidState(pos.above()).getType() == Fluids.WATER || world.getFluidState(pos.above()).getType() == Fluids.FLOWING_WATER)))
					{
						world.setBlockAndUpdate(pos, block.defaultBlockState().setValue(BlockEndRodSlab.CONDITIONAL, true));
						
						if(world.getBlockState(pos.above()).getBlock() instanceof SlabBlock)
							world.setBlockAndUpdate(pos.above(), world.getBlockState(pos.above()).setValue(BlockStateProperties.WATERLOGGED, false));
						else
							world.setBlockAndUpdate(pos.above(), Blocks.AIR.defaultBlockState());
						rodPlaced(event, held, block, pos);
						return;
					}
				else if((world.isEmptyBlock(placeAt.above()) || world.getFluidState(placeAt.above()).getType() == Fluids.WATER || world.getFluidState(placeAt.above()).getType() == Fluids.FLOWING_WATER)
						&&(world.isEmptyBlock(placeAt) ))
					{
						world.setBlockAndUpdate(placeAt.above(), block.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.UP));
						rodPlaced(event, held, block, placeAt.above());
						return;
					}
			}
			else if(world.getBlockState(pos).getValue(DirectionalBlock.FACING) == Direction.DOWN && face == Direction.UP
					&& (world.isEmptyBlock(placeAt.above()) || world.getFluidState(placeAt.above()).getType() == Fluids.WATER || world.getFluidState(placeAt.above()).getType() == Fluids.FLOWING_WATER))
			{
				{	
					world.setBlockAndUpdate(placeAt.above(), block.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.UP));
					rodPlaced(event, held, block, placeAt.above());
					return;
				}
			}
			else if(world.getBlockState(pos).getValue(DirectionalBlock.FACING) == Direction.UP && face == Direction.DOWN 
					&& (world.isEmptyBlock(placeAt.below()) || world.getFluidState(placeAt.below()).getType() == Fluids.WATER || world.getFluidState(placeAt.below()).getType() == Fluids.FLOWING_WATER))
			{
				{	
					world.setBlockAndUpdate(placeAt.below(), block.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.DOWN));
					rodPlaced(event, held, block, placeAt.below());
					return;
				}
			}
			
			if(face == Direction.DOWN && world.getBlockState(pos).getValue(BlockEndRodSlab.CONDITIONAL) == true
					&& world.getBlockState(placeAt.below()).getBlock() instanceof BlockEndRodSlab
					&& world.getBlockState(placeAt.below()).getValue(BlockEndRodSlab.CONDITIONAL) == false)
			{
				world.setBlockAndUpdate(placeAt.below(), world.getBlockState(placeAt.below()).setValue(BlockEndRodSlab.CONDITIONAL, true));
				rodPlaced(event, held, block, placeAt.below());
				return;
			}
			else if (face == Direction.UP && world.getBlockState(pos).getValue(BlockEndRodSlab.CONDITIONAL) == true
					&& world.getBlockState(placeAt.above()).getBlock() instanceof BlockEndRodSlab
					&& world.getBlockState(placeAt.above()).getValue(BlockEndRodSlab.CONDITIONAL) == false)
			{
				world.setBlockAndUpdate(placeAt.above(), world.getBlockState(placeAt.above()).setValue(BlockEndRodSlab.CONDITIONAL, true));
				rodPlaced(event, held, block, placeAt.above());
				return;
			}
			
			
			event.setCanceled(true);
		}
		else if(face == Direction.UP &&
				world.getBlockState(pos).getBlock() instanceof SlabBlock && world.getBlockState(pos).getValue(SlabBlock.TYPE) == SlabType.BOTTOM 
				&& (world.getBlockState(placeAt).getBlock() instanceof BlockEndRodSlab
				&& !world.getBlockState(placeAt).getValue(BlockEndRodSlab.CONDITIONAL)
				&& world.getBlockState(placeAt).getValue(EndRodBlock.FACING) == Direction.DOWN))
		{
			world.setBlockAndUpdate(placeAt, block.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.DOWN).setValue(BlockEndRodSlab.CONDITIONAL, true));
			rodPlaced(event, held, block, placeAt);
		}
		else if(face == Direction.DOWN &&
				world.getBlockState(pos).getBlock() instanceof SlabBlock && world.getBlockState(pos).getValue(SlabBlock.TYPE) == SlabType.TOP 
				&& (world.getBlockState(placeAt).getBlock() instanceof BlockEndRodSlab
				&& !world.getBlockState(placeAt).getValue(BlockEndRodSlab.CONDITIONAL)
				&& world.getBlockState(placeAt).getValue(EndRodBlock.FACING) == Direction.UP))
		{
			world.setBlockAndUpdate(placeAt, block.defaultBlockState().setValue(DirectionalBlock.FACING, Direction.UP).setValue(BlockEndRodSlab.CONDITIONAL, true));
			rodPlaced(event, held, block, placeAt);
		}
	}
	public static void rodPlaced(RightClickBlock event, ItemStack held, Block block, BlockPos pos)
	{
		Level world = event.getWorld();
		SoundType soundType;
		
		soundType = block.getSoundType(block.defaultBlockState(), world, pos, event.getPlayer());
		world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundSource.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
		event.getPlayer().swing(event.getHand());
		
		if(!event.getPlayer().isCreative())
			held.shrink(1);
		event.setCanceled(true);
	}
	
	
	public static void registerPlaceEntry(ResourceLocation itemName, Supplier<Block> endRodSupplier)
	{
		if(!PLACE_ENTRIES.containsKey(itemName) && endRodSupplier != null)
			PLACE_ENTRIES.put(itemName, endRodSupplier);
	}
}
