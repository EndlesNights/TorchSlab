package com.endlesnights.torchslabsmod.blocks.buzzierbees;

import java.util.Random;

import com.bagel.buzzierbees.common.blocks.CandleBlock;
import com.endlesnights.torchslabsmod.blocks.buzzierbees.entities.FallingCandleSlabEntity;
import com.endlesnights.torchslabsmod.blocks.buzzierbees.util.GetCandle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
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
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockCandleSlab extends Block implements IWaterLoggable
{
	public static final IntegerProperty CANDLES 	= CandleBlock.CANDLES;
	public static final BooleanProperty WATERLOGGED = CandleBlock.WATERLOGGED;
	public static final DirectionProperty FACING 	= CandleBlock.FACING;
	
	protected static final VoxelShape ONE_SHAPE 	= Block.makeCuboidShape(6.0D, -8.0D, 6.0D, 10.0D, 1.0D, 10.0D);
	protected static final VoxelShape TWO_SHAPE 	= Block.makeCuboidShape(3.0D, -8.0D, 3.0D, 13.0D, 1.0D, 13.0D);
	protected static final VoxelShape THREE_SHAPE 	= Block.makeCuboidShape(3.0D, -8.0D, 3.0D, 13.0D, 1.0D, 13.0D);
	protected static final VoxelShape FOUR_SHAPE 	= Block.makeCuboidShape(3.0D, -8.0D, 3.0D, 13.0D, 1.0D, 13.0D);
	
	public BlockCandleSlab(Block.Properties properties)
	{
	      super(properties.hardnessAndResistance(0.1F));
	      this.setDefaultState(this.getDefaultState().with(CANDLES, 1).with(WATERLOGGED, false));
	}
	
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
	}
	
	public int getLightValue(BlockState state) {
		return this.isInBadEnvironment(state) ? 0 : super.getLightValue(state) + (11 + (1 * state.get(CANDLES)));	
	}
	
	
	private boolean isInBadEnvironment(BlockState state) {
		return state.get(WATERLOGGED);	
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos blockpos = pos.down();
	    return this.isValidGround(worldIn.getBlockState(blockpos), worldIn, blockpos);
	 }
	
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() instanceof SlabBlock && state.get(SlabBlock.TYPE) == SlabType.BOTTOM ;
	}
	
	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
		return (0.1F * state.get(CANDLES));	
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{		
		if (!this.isValidPosition(state, worldIn, currentPos))
		{
			if(worldIn.getBlockState(currentPos.down()).getBlock() instanceof SlabBlock && worldIn.getBlockState(currentPos.down()).get(SlabBlock.TYPE) != SlabType.BOTTOM)
				return Blocks.AIR.getDefaultState();
			
			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, this.tickRate(worldIn));
			return super.updatePostPlacement(state, facing, facingState, worldIn, currentPos, facingPos);
		}

		
		return state;
	}
	
	public void func_225534_a_(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_)
	{
		if (p_225534_2_.isAirBlock(p_225534_3_.down()) || canFallThrough(p_225534_2_.getBlockState(p_225534_3_.down())) && p_225534_3_.getY() >= 0)
		{
			FallingCandleSlabEntity fallingCandleEntity = new FallingCandleSlabEntity(p_225534_2_, (double)p_225534_3_.getX() + 0.5D, (double)p_225534_3_.getY(), (double)p_225534_3_.getZ() + 0.5D, p_225534_2_.getBlockState(p_225534_3_));
			this.onStartFalling(fallingCandleEntity);
			p_225534_2_.addEntity(fallingCandleEntity);
		}
	}
	
	protected void onStartFalling(FallingCandleSlabEntity fallingEntity) {
	}

	public int tickRate(IWorldReader worldIn) {
		return 2;
	}
	
	public static boolean canFallThrough(BlockState state) {
		Block block = state.getBlock();
		Material material = state.getMaterial();
		return state.isAir() || block == Blocks.FIRE || material.isLiquid();
	}

	public void onEndFalling(World worldIn, BlockPos pos, BlockState fallingState, BlockState hitState)
	{

	}

	public void breakFall(World worldIn, BlockPos pos, BlockState fallingState, BlockState hitState, FallingCandleSlabEntity entity)
	{
		
		if(worldIn.getBlockState(pos).getBlock() instanceof SlabBlock && worldIn.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.BOTTOM
				&& (worldIn.isAirBlock(pos.up()) || worldIn.getFluidState(pos.up()).getFluid() == Fluids.WATER || worldIn.getFluidState(pos.up()).getFluid() == Fluids.FLOWING_WATER))
			{
				worldIn.setBlockState(pos.up(), fallingState);
			}
		else if(worldIn.isAirBlock(pos) || worldIn.getFluidState(pos).getFluid() == Fluids.WATER || worldIn.getFluidState(pos).getFluid() == Fluids.FLOWING_WATER
			|| worldIn.getBlockState(pos).getBlock() instanceof TallGrassBlock
			|| worldIn.getBlockState(pos).getBlock() instanceof SnowBlock && worldIn.getBlockState(pos).get(SnowBlock.LAYERS) == 1)
		{
			worldIn.setBlockState(pos,GetCandle.getParentSlab(fallingState).getDefaultState()
					.with(CANDLES, fallingState.get(CANDLES))
					.with(WATERLOGGED, fallingState.get(WATERLOGGED))
					.with(FACING, fallingState.get(FACING))
					,3);
		}
		else if(worldIn.getBlockState(pos).getBlock() instanceof LilyPadBlock)
			worldIn.setBlockState(pos,GetCandle.getPadBlock(fallingState).getDefaultState()
					.with(CANDLES, fallingState.get(CANDLES))
					.with(FACING, fallingState.get(FACING))
					,3);
		else
			entity.entityDropItem(new ItemStack(GetCandle.getParentSlab(fallingState).asItem(),fallingState.get(CANDLES)));	
	}
	
	public void onBroken(World worldIn, BlockPos pos){
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.get(CANDLES)) {
		case 1:
			default:
				return ONE_SHAPE;	
		case 2:
			return TWO_SHAPE;	
		case 3:
			return THREE_SHAPE;	
		case 4:
			return FOUR_SHAPE;	
		}	
	}
	
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);	
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(CANDLES, WATERLOGGED, FACING);	
	}
	
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}
	
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return type == PathType.AIR && !this.blocksMovement ? true : super.allowsMovement(state, worldIn, pos, type);	
	}	
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack( GetCandle.getParentSlab(state).asItem());
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		if(state.get(WATERLOGGED) == false) {
			double x = pos.getX();
			double y = pos.getY() -0.5D;
			double z = pos.getZ();
			
			if(state.get(CANDLES) == 1) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.5D, y+0.75D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.5D, y+0.75D, z+0.5D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 2 && state.get(FACING) == Direction.NORTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.5625D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.5625D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 2 && state.get(FACING) == Direction.EAST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.75D, z+0.5625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.75D, z+0.5625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 2 && state.get(FACING) == Direction.SOUTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.4375D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.4375D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 2 && state.get(FACING) == Direction.WEST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.75D, z+0.4375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.75D, z+0.4375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 3 && state.get(FACING) == Direction.NORTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.75D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.75D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.5D, y+0.6875D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.5D, y+0.6875D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 3 && state.get(FACING) == Direction.EAST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.6875D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.6875D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 3 && state.get(FACING) == Direction.SOUTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.75D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.75D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.5D, y+0.6875D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.5D, y+0.6875D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 3 && state.get(FACING) == Direction.WEST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.6875D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.6875D, z+0.5D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 4 && state.get(FACING) == Direction.NORTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.6875D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.6875D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.625D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.4375D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.4375D, z+0.6875D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 4 && state.get(FACING) == Direction.EAST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.6875D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.6875D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.625D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.4375D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.4375D, z+0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 4 && state.get(FACING) == Direction.SOUTH) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.75D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.6875D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.6875D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.625D, z+0.625D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.4375D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.4375D, z+0.3125D, 0.002D, 0.01D, 0.002D);
			}
			if(state.get(CANDLES) == 4 && state.get(FACING) == Direction.WEST) {
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.3125D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.3125D, y+0.75D, z+0.3125D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.625D, y+0.6875D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.625D, y+0.6875D, z+0.375D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.375D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.375D, y+0.625D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.SMOKE, x+0.6875D, y+0.4375D, z+0.6875D, 0.002D, 0.01D, 0.002D);
				worldIn.addParticle(ParticleTypes.FLAME, x+0.6875D, y+0.4375D, z+0.6875D, 0.002D, 0.01D, 0.002D);
			}
		}
	}
}
