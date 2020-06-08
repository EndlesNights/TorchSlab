package com.endlesnights.torchslabsmod.blocks.quark;

import java.util.Random;

import javax.annotation.Nullable;

import com.bagel.buzzierbees.common.blocks.CandleBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
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

@SuppressWarnings("deprecation")
public class BlockCandlePad extends Block
{
	public static final DirectionProperty FACING = CandleBlock.FACING;
	
	protected static final VoxelShape SHAPE = VoxelShapes.or(
			Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 8.25D, 10.0D),
			Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 1.5D, 15.0D));
	
	protected final DyeColor color;
	
	public BlockCandlePad(Properties properties, DyeColor color)
	{
		super(Block.Properties.create(Material.MISCELLANEOUS, color.getMapColor())
				.hardnessAndResistance(0.2F)
				.lightValue(14)
				.sound(SoundType.CLOTH));
		
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
				
		this.color = color;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);	
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityCollision(state, worldIn, pos, entityIn);
		if (worldIn instanceof ServerWorld && entityIn instanceof BoatEntity) {
			worldIn.destroyBlock(new BlockPos(pos), true, entityIn);
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
				return BiomeColors.getFoliageColor(reader, blockPos);
			}
			return GrassColors.get(0.5D, 1.0D);
		}
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(GetCandle.getParent(color));

	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double d0 = pos.getX() + 0.5D;
		double d1 = pos.getY() + 0.7D;
		double d2 = pos.getZ() + 0.5D;

		worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
	
}
