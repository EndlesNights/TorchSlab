package com.endlesnights.torchslabsmod.blocks.buzzierbees;

import java.util.Random;

import javax.annotation.Nullable;

import com.bagel.buzzierbees.common.blocks.CandleBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.GrassColors;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ILightReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockPadCandle extends Block
{
	public final Block PARENT_BLOCK;
	public static final IntegerProperty CANDLES 	= CandleBlock.CANDLES;
	public static final DirectionProperty FACING 	= CandleBlock.FACING;
	
	public static final VoxelShape PAD_SHAPE 		= Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 1.5D, 15.0D);
	protected static final VoxelShape ONE_SHAPE 	= VoxelShapes.or( Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 9.25D, 10.0D), PAD_SHAPE);
	protected static final VoxelShape TWO_SHAPE 	= VoxelShapes.or( Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 9.25D, 13.0D), PAD_SHAPE);
	protected static final VoxelShape THREE_SHAPE 	= VoxelShapes.or( Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 9.25D, 13.0D), PAD_SHAPE);
	protected static final VoxelShape FOUR_SHAPE 	= VoxelShapes.or(Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 9.25D, 13.0D), PAD_SHAPE);
	
	
	public BlockPadCandle(Block.Properties properties, Block parentBlock)
	{
		super(properties);
		this.PARENT_BLOCK = parentBlock;
		this.setDefaultState(this.getDefaultState().with(CANDLES, 1));
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(CANDLES, FACING);	
	}
	
	public int getLightValue(BlockState state) {
		return super.getLightValue(state) + (11 + (1 * state.get(CANDLES)));	
	}
	
	@Override
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
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityCollision(state, worldIn, pos, entityIn);
		if (worldIn instanceof ServerWorld && entityIn instanceof BoatEntity) {
			worldIn.func_225521_a_(new BlockPos(pos), true, entityIn);
		}
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos blockpos)
	{
		return this.isValidGround(worldIn.getBlockState(blockpos.down()), worldIn, blockpos.down());
	}
	
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		IFluidState ifluidstate = worldIn.getFluidState(pos);
		return ifluidstate.getFluid() == Fluids.WATER || state.getMaterial() == Material.ICE;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return !isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
	}
	
	public static class ColorHandler implements IBlockColor 
	{
		@Override
		public int getColor(BlockState state, @Nullable ILightReader reader , @Nullable BlockPos blockPos, int tintIndex)
		{
			if(reader != null && blockPos != null)
			{
				return BiomeColors.func_228358_a_(reader, blockPos);
			}
			return GrassColors.get(0.5D, 1.0D);
		}
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack( PARENT_BLOCK.asItem());
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand)
	{
		double x = pos.getX();
		double y = pos.getY() -0.1D;
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
