package com.endlesnights.torchslabsmod.blocks.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndRodBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
public class BlockEndRodSlab extends EndRodBlock
{
	public static final BooleanProperty CONDITIONAL = BlockStateProperties.CONDITIONAL;
	
	protected static final VoxelShape SLAB_SHAPE_UP = Block.makeCuboidShape(6.0D, -8.0D, 6.0D, 10.0D, 8.0D, 10.0D);
	protected static final VoxelShape SLAB_SHAPE_DOWN = Block.makeCuboidShape(6.0D, 8.0D, 6.0D, 10.0D, 24.0D, 10.0D);
	
	protected static final VoxelShape SLAB_SHAPE_DOUBLE = Block.makeCuboidShape(6.0D, -8.0D, 6.0D, 10.0D, 24.0D, 10.0D);


	protected BlockEndRodSlab(Properties builder)
	{
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(FACING, Direction.UP)
				.with(CONDITIONAL, false));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		if(state.get(CONDITIONAL))
			return SLAB_SHAPE_DOUBLE;
		
		if(state.get(FACING) == Direction.UP)
			return SLAB_SHAPE_UP;

		return SLAB_SHAPE_DOWN;
	}

	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		Direction direction = stateIn.get(FACING);
		double offset = 0.5D;
		
		if(stateIn.get(FACING) == Direction.UP || stateIn.get(CONDITIONAL))
			offset *= -1;
		
		double d0 = (double)pos.getX() + 0.55D - (double)(rand.nextFloat() * 0.1F);
		double d1 = (double)pos.getY() + 0.55D - (double)(rand.nextFloat() * 0.1F) + offset;
		double d2 = (double)pos.getZ() + 0.55D - (double)(rand.nextFloat() * 0.1F);
		double d3 = (double)(0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);
		
		if (rand.nextInt(5) == 0)
		{
			worldIn.addParticle(ParticleTypes.END_ROD, d0 + (double)direction.getXOffset() * d3, d1 + (double)direction.getYOffset() * d3, d2 + (double)direction.getZOffset() * d3, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D);
		}
		
		if(stateIn.get(CONDITIONAL))
		{
			d1 ++;
			worldIn.addParticle(ParticleTypes.END_ROD, d0 + (double)direction.getXOffset() * d3, d1 + (double)direction.getYOffset() * d3, d2 + (double)direction.getZOffset() * d3, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D);
		}
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		
		return !isValidPosition(state, world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
	{

		
		if(state.get(FACING) == Direction.UP)
		{
			if( !(worldIn.isAirBlock(pos.down())
					|| ((worldIn.getBlockState(pos.down()).getBlock() instanceof SlabBlock)
					&& worldIn.getBlockState(pos.down()).get(SlabBlock.TYPE) == SlabType.BOTTOM)))
			{
				return false;
			}
			if(state.get(CONDITIONAL) 
					&& !(worldIn.isAirBlock(pos.up())
					|| ((worldIn.getBlockState(pos.up()).getBlock() instanceof SlabBlock)
					&& worldIn.getBlockState(pos.up()).get(SlabBlock.TYPE) == SlabType.TOP)))
			{
				return false;
			}
		}
		
		
		if(state.get(FACING) == Direction.DOWN)
		{
			if( !(worldIn.isAirBlock(pos.up())
					|| ((worldIn.getBlockState(pos.up()).getBlock() instanceof SlabBlock)
					&& worldIn.getBlockState(pos.up()).get(SlabBlock.TYPE) == SlabType.TOP)))
			{
				return false;
			}
			if(state.get(CONDITIONAL) 
					&& !(worldIn.isAirBlock(pos.down())
					|| ((worldIn.getBlockState(pos.down()).getBlock() instanceof SlabBlock)
					&& worldIn.getBlockState(pos.down()).get(SlabBlock.TYPE) == SlabType.BOTTOM)))
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, CONDITIONAL);
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack( Items.END_ROD);
	}
	
}
