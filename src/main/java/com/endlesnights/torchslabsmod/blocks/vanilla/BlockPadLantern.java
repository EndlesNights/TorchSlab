package com.endlesnights.torchslabsmod.blocks.vanilla;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.PathType;
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
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.server.ServerWorld;

@SuppressWarnings("deprecation")
public class BlockPadLantern extends Block
{
	public static final DirectionProperty HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;;

	public static final VoxelShape PAD_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 1.5D, 15.0D);
	public static final VoxelShape SHAPE =  VoxelShapes.or(
			Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 7.25D, 11.0D), //Lantern base
			Block.makeCuboidShape(6.0D, 7.0D, 6.0D, 10.0D, 9.25D, 10.0D), //Lantern top
			PAD_SHAPE 
			);
	final Character TYPE;
	public BlockPadLantern(Block.Properties properties, Character type)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
		this.TYPE = type;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING);	
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
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
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack( TYPE == 'l' ? Items.LANTERN : Items.SOUL_LANTERN);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos blockpos)
	{
		 return this.isValidGround(worldIn.getBlockState(blockpos.down()), worldIn, blockpos.down());
	}
	
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		FluidState ifluidstate = worldIn.getFluidState(pos);
		return ifluidstate.getFluid() == Fluids.WATER || state.getMaterial() == Material.ICE;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return !isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
	}
	
	public static class ColorHandler implements IBlockColor 
	{
		@Override
		public int getColor(BlockState state, @Nullable IBlockDisplayReader reader , @Nullable BlockPos blockPos, int tintIndex)
		{
			if(reader != null && blockPos != null)
			{
				return BiomeColors.getFoliageColor(reader, blockPos);
			}
			return GrassColors.get(0.5D, 1.0D);
		}
	}
	
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}
}
