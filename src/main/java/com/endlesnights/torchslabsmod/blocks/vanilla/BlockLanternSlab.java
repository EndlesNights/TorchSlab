package com.endlesnights.torchslabsmod.blocks.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.world.IBlockReader;

import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class BlockLanternSlab extends LanternBlock
{
	protected static final VoxelShape SLAB_BOTTOM_SHAPE = VoxelShapes.combine(
			Block.makeCuboidShape(5.0D, -8.0D, 5.0D, 11.0D, -1.0D, 11.0D), 
			Block.makeCuboidShape(6.0D, -1.0D, 6.0D, 10.0D, 1.0D, 10.0D), 
			IBooleanFunction.OR);
	
	protected static final VoxelShape HANGING = VoxelShapes.combine(
			Block.makeCuboidShape(5.0D, 9.0D, 5.0D, 11.0D, 16.0D, 11.0D),
			Block.makeCuboidShape(6.0D, 16.0D, 6.0D, 10.0D, 18.0D, 10.0D),
			IBooleanFunction.OR);
	
	public BlockLanternSlab (Block.Properties properties)
	{
		super(properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		if(state == this.getDefaultState())
			return SLAB_BOTTOM_SHAPE;
		else
			return HANGING;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		if(state == this.getDefaultState())
			return facing == Direction.DOWN && !isValidPosition(state, world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
		else
			return facing == Direction.UP && !isValidPosition(state, world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
				
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		if(state == this.getDefaultState())
			return (world.getBlockState(pos.offset(Direction.DOWN)).getProperties().contains(SlabBlock.TYPE) 
				&& world.getBlockState(pos.offset(Direction.DOWN)).get(SlabBlock.TYPE) == SlabType.BOTTOM);		
		else
			return  (world.getBlockState(pos.offset(Direction.UP)).getProperties().contains(SlabBlock.TYPE) 
					&& world.getBlockState(pos.offset(Direction.UP)).get(SlabBlock.TYPE) == SlabType.TOP);
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack( Items.LANTERN);
	}
}
