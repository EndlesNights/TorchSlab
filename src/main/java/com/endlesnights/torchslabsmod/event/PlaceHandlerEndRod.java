package com.endlesnights.torchslabsmod.event;

import java.util.Collection;
import java.util.HashMap;

import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.blocks.vanilla.BlockEndRodSlab;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.EndRodBlock;
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
public class PlaceHandlerEndRod
{
	private static final HashMap<ResourceLocation,Block> PLACE_ENTRIES = new HashMap<>();
	
	@SubscribeEvent
	public static void onBlockEntityPlace(RightClickBlock event)
	{	
		ItemStack held = event.getItemStack();
		ResourceLocation rl = held.getItem().getRegistryName();

		if(PLACE_ENTRIES.containsKey(rl))
			placeRod(event, held, PLACE_ENTRIES.get(rl));
	}

	private static void placeRod(RightClickBlock event, ItemStack held, Block block)
	{		
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		BlockPos placeAt = pos.offset(face);
		World world = event.getWorld();		

		if(world.getBlockState(pos).getBlock() instanceof SlabBlock && face == Direction.UP 
				&& world.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.BOTTOM 
				&& (world.isAirBlock(placeAt) || world.getFluidState(placeAt).getFluid() == Fluids.WATER || world.getFluidState(placeAt).getFluid() == Fluids.FLOWING_WATER) )
		{	

			world.setBlockState(placeAt, block.getDefaultState());
			world.setBlockState(pos, world.getBlockState(pos).with(BlockStateProperties.WATERLOGGED, false));
			
			rodPlaced(event, held, block, pos);
		}
		else if(world.getBlockState(pos).getBlock() instanceof SlabBlock && face == Direction.DOWN 
				&& world.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.TOP 
				&& (world.isAirBlock(placeAt) || world.getFluidState(placeAt).getFluid() == Fluids.WATER || world.getFluidState(placeAt).getFluid() == Fluids.FLOWING_WATER) )
		{	

			world.setBlockState(placeAt, block.getDefaultState().with(DirectionalBlock.FACING, Direction.DOWN));
			world.setBlockState(pos, world.getBlockState(pos).with(BlockStateProperties.WATERLOGGED, false));
			
			rodPlaced(event, held, block, pos);
		}
		else if( (face == Direction.UP || face == Direction.DOWN)
				&& world.getBlockState(pos).getBlock() instanceof BlockEndRodSlab)
		{
			if(world.getBlockState(pos).get(DirectionalBlock.FACING) == Direction.DOWN && face == Direction.DOWN )
			{
				if(!world.getBlockState(pos).get(BlockEndRodSlab.CONDITIONAL)
						&& ((world.getBlockState(pos.down()).getBlock() instanceof SlabBlock && world.getBlockState(pos.down()).get(SlabBlock.TYPE) == SlabType.BOTTOM)
						|| (world.isAirBlock(pos.down()) || world.getFluidState(pos.down()).getFluid() == Fluids.WATER || world.getFluidState(pos.down()).getFluid() == Fluids.FLOWING_WATER)))
					{
						world.setBlockState(pos, block.getDefaultState().with(DirectionalBlock.FACING, Direction.DOWN).with(BlockEndRodSlab.CONDITIONAL, true));
						
						if(world.getBlockState(pos.down()).getBlock() instanceof SlabBlock)
							world.setBlockState(pos.down(), world.getBlockState(pos.down()).with(BlockStateProperties.WATERLOGGED, false));
						else
							world.setBlockState(pos.down(), Blocks.AIR.getDefaultState());
						
						rodPlaced(event, held, block, pos);
						return;
					}
				else if((world.isAirBlock(placeAt.down()) || world.getFluidState(placeAt.down()).getFluid() == Fluids.WATER || world.getFluidState(placeAt.down()).getFluid() == Fluids.FLOWING_WATER)
						&&(world.isAirBlock(placeAt)))
				{
					world.setBlockState(placeAt.down(), block.getDefaultState().with(DirectionalBlock.FACING, Direction.DOWN));
					rodPlaced(event, held, block, placeAt.down());
					return;
				}
			}
			else if(world.getBlockState(pos).get(DirectionalBlock.FACING) == Direction.UP && face == Direction.UP)
			{
				if(!world.getBlockState(pos).get(BlockEndRodSlab.CONDITIONAL) 
						&& ((world.getBlockState(pos.up()).getBlock() instanceof SlabBlock && world.getBlockState(pos.up()).get(SlabBlock.TYPE) == SlabType.TOP)
						|| (world.isAirBlock(pos.up()) || world.getFluidState(pos.up()).getFluid() == Fluids.WATER || world.getFluidState(pos.up()).getFluid() == Fluids.FLOWING_WATER)))
					{
						world.setBlockState(pos, block.getDefaultState().with(BlockEndRodSlab.CONDITIONAL, true));
						
						if(world.getBlockState(pos.up()).getBlock() instanceof SlabBlock)
							world.setBlockState(pos.up(), world.getBlockState(pos.up()).with(BlockStateProperties.WATERLOGGED, false));
						else
							world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
						rodPlaced(event, held, block, pos);
						return;
					}
				else if((world.isAirBlock(placeAt.up()) || world.getFluidState(placeAt.up()).getFluid() == Fluids.WATER || world.getFluidState(placeAt.up()).getFluid() == Fluids.FLOWING_WATER)
						&&(world.isAirBlock(placeAt) ))
					{
						world.setBlockState(placeAt.up(), block.getDefaultState().with(DirectionalBlock.FACING, Direction.UP));
						rodPlaced(event, held, block, placeAt.up());
						return;
					}
			}
			else if(world.getBlockState(pos).get(DirectionalBlock.FACING) == Direction.DOWN && face == Direction.UP
					&& (world.isAirBlock(placeAt.up()) || world.getFluidState(placeAt.up()).getFluid() == Fluids.WATER || world.getFluidState(placeAt.up()).getFluid() == Fluids.FLOWING_WATER))
			{
				{	
					world.setBlockState(placeAt.up(), block.getDefaultState().with(DirectionalBlock.FACING, Direction.UP));
					rodPlaced(event, held, block, placeAt.up());
					return;
				}
			}
			else if(world.getBlockState(pos).get(DirectionalBlock.FACING) == Direction.UP && face == Direction.DOWN 
					&& (world.isAirBlock(placeAt.down()) || world.getFluidState(placeAt.down()).getFluid() == Fluids.WATER || world.getFluidState(placeAt.down()).getFluid() == Fluids.FLOWING_WATER))
			{
				{	
					world.setBlockState(placeAt.down(), block.getDefaultState().with(DirectionalBlock.FACING, Direction.DOWN));
					rodPlaced(event, held, block, placeAt.down());
					return;
				}
			}
			
			if(face == Direction.DOWN && world.getBlockState(pos).get(BlockEndRodSlab.CONDITIONAL) == true
					&& world.getBlockState(placeAt.down()).getBlock() instanceof BlockEndRodSlab
					&& world.getBlockState(placeAt.down()).get(BlockEndRodSlab.CONDITIONAL) == false)
			{
				world.setBlockState(placeAt.down(), world.getBlockState(placeAt.down()).with(BlockEndRodSlab.CONDITIONAL, true));
				rodPlaced(event, held, block, placeAt.down());
				return;
			}
			else if (face == Direction.UP && world.getBlockState(pos).get(BlockEndRodSlab.CONDITIONAL) == true
					&& world.getBlockState(placeAt.up()).getBlock() instanceof BlockEndRodSlab
					&& world.getBlockState(placeAt.up()).get(BlockEndRodSlab.CONDITIONAL) == false)
			{
				world.setBlockState(placeAt.up(), world.getBlockState(placeAt.up()).with(BlockEndRodSlab.CONDITIONAL, true));
				rodPlaced(event, held, block, placeAt.up());
				return;
			}
			
			
			event.setCanceled(true);
		}
		else if(face == Direction.UP &&
				world.getBlockState(pos).getBlock() instanceof SlabBlock && world.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.BOTTOM 
				&& (world.getBlockState(placeAt).getBlock() instanceof BlockEndRodSlab
				&& !world.getBlockState(placeAt).get(BlockEndRodSlab.CONDITIONAL)
				&& world.getBlockState(placeAt).get(EndRodBlock.FACING) == Direction.DOWN))
		{
			world.setBlockState(placeAt, block.getDefaultState().with(DirectionalBlock.FACING, Direction.DOWN).with(BlockEndRodSlab.CONDITIONAL, true));
			rodPlaced(event, held, block, placeAt);
		}
		else if(face == Direction.DOWN &&
				world.getBlockState(pos).getBlock() instanceof SlabBlock && world.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.TOP 
				&& (world.getBlockState(placeAt).getBlock() instanceof BlockEndRodSlab
				&& !world.getBlockState(placeAt).get(BlockEndRodSlab.CONDITIONAL)
				&& world.getBlockState(placeAt).get(EndRodBlock.FACING) == Direction.UP))
		{
			world.setBlockState(placeAt, block.getDefaultState().with(DirectionalBlock.FACING, Direction.UP).with(BlockEndRodSlab.CONDITIONAL, true));
			rodPlaced(event, held, block, placeAt);
		}
	}
	public static void rodPlaced(RightClickBlock event, ItemStack held, Block block, BlockPos pos)
	{
		World world = event.getWorld();
		world.playSound(null, pos, block.getSoundType(world.getBlockState(pos)).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
		event.getPlayer().swingArm(event.getHand());
		
		if(!event.getPlayer().isCreative())
			held.shrink(1);
		event.setCanceled(true);
	}
	
	
	public static void registerPlaceEntry(ResourceLocation itemName, Block endRod)
	{
		if(!PLACE_ENTRIES.containsKey(itemName))
			PLACE_ENTRIES.put(itemName, endRod);
	}

	public static Collection<Block> getPlaceEntryBlocks()
	{
		return PLACE_ENTRIES.values();
	}
}
