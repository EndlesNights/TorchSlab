package com.endlesnights.torchslabsmod.blocks.vanilla;

import java.util.Random;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndRodBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
public class BlockEndRodSlab extends EndRodBlock
{
	public static final BooleanProperty CONDITIONAL = BlockStateProperties.CONDITIONAL;
	
	protected static final VoxelShape SLAB_SHAPE_UP = Block.box(6.0D, -8.0D, 6.0D, 10.0D, 8.0D, 10.0D);
	protected static final VoxelShape SLAB_SHAPE_DOWN = Block.box(6.0D, 8.0D, 6.0D, 10.0D, 24.0D, 10.0D);
	
	protected static final VoxelShape SLAB_SHAPE_DOUBLE = Block.box(6.0D, -8.0D, 6.0D, 10.0D, 24.0D, 10.0D);


	protected BlockEndRodSlab(Properties builder)
	{
		super(builder);
//		this.setDefaultState(this.stateContainer.getBaseState()
//				.setValue(FACING, Direction.UP)
//				.setValue(CONDITIONAL, false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(CONDITIONAL, false));

	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		if(state.getValue(CONDITIONAL))
			return SLAB_SHAPE_DOUBLE;
		
		if(state.getValue(FACING) == Direction.UP)
			return SLAB_SHAPE_UP;

		return SLAB_SHAPE_DOWN;
	}

	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand)
	{
		Direction direction = stateIn.getValue(FACING);
		double offset = 0.5D;
		
		if(stateIn.getValue(FACING) == Direction.UP || stateIn.getValue(CONDITIONAL))
			offset *= -1;
		
		double d0 = (double)pos.getX() + 0.55D - (double)(rand.nextFloat() * 0.1F);
		double d1 = (double)pos.getY() + 0.55D - (double)(rand.nextFloat() * 0.1F) + offset;
		double d2 = (double)pos.getZ() + 0.55D - (double)(rand.nextFloat() * 0.1F);
		double d3 = (double)(0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);
		
		if (rand.nextInt(5) == 0)
		{
			worldIn.addParticle(ParticleTypes.END_ROD, d0 + (double)direction.getStepX() * d3, d1 + (double)direction.getStepY() * d3, d2 + (double)direction.getStepZ() * d3, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D);
		}
		
		if(stateIn.getValue(CONDITIONAL))
		{
			d1 ++;
			worldIn.addParticle(ParticleTypes.END_ROD, d0 + (double)direction.getStepX() * d3, d1 + (double)direction.getStepY() * d3, d2 + (double)direction.getStepZ() * d3, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D);
		}
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos)
	{
		
		return !canSurvive(state, world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
	{

		
		if(state.getValue(FACING) == Direction.UP)
		{
			if( !(worldIn.isEmptyBlock(pos.below())
					|| ((worldIn.getBlockState(pos.below()).getBlock() instanceof SlabBlock)
					&& worldIn.getBlockState(pos.below()).getValue(SlabBlock.TYPE) == SlabType.BOTTOM)))
			{
				return false;
			}
			if(state.getValue(CONDITIONAL) 
					&& !(worldIn.isEmptyBlock(pos.above())
					|| ((worldIn.getBlockState(pos.above()).getBlock() instanceof SlabBlock)
					&& worldIn.getBlockState(pos.above()).getValue(SlabBlock.TYPE) == SlabType.TOP)))
			{
				return false;
			}
		}
		
		
		if(state.getValue(FACING) == Direction.DOWN)
		{
			if( !(worldIn.isEmptyBlock(pos.above())
					|| ((worldIn.getBlockState(pos.above()).getBlock() instanceof SlabBlock)
					&& worldIn.getBlockState(pos.above()).getValue(SlabBlock.TYPE) == SlabType.TOP)))
			{
				return false;
			}
			if(state.getValue(CONDITIONAL) 
					&& !(worldIn.isEmptyBlock(pos.below())
					|| ((worldIn.getBlockState(pos.below()).getBlock() instanceof SlabBlock)
					&& worldIn.getBlockState(pos.below()).getValue(SlabBlock.TYPE) == SlabType.BOTTOM)))
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, CONDITIONAL);
	}
	
	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player)
	{
		return new ItemStack( Items.END_ROD);
	}
	
}
