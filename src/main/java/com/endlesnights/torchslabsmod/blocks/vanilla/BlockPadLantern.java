package com.endlesnights.torchslabsmod.blocks.vanilla;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
public class BlockPadLantern extends Block
{
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;;

	public static final VoxelShape PAD_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 1.5D, 15.0D);
	public static final VoxelShape SHAPE =  Shapes.or(
			Block.box(5.0D, 0.0D, 5.0D, 11.0D, 7.25D, 11.0D), //Lantern base
			Block.box(6.0D, 7.0D, 6.0D, 10.0D, 9.25D, 10.0D), //Lantern top
			PAD_SHAPE 
			);
	final Character TYPE;
	public BlockPadLantern(Block.Properties properties, Character type)
	{
		super(properties);
//		this.setDefaultState(this.stateContainer.getBaseState().setValue(FACING, Direction.NORTH));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
		this.TYPE = type;
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);	
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		return SHAPE;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		return SHAPE;
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
		super.entityInside(state, worldIn, pos, entityIn);
		if (worldIn instanceof ServerLevel && entityIn instanceof Boat) {
			worldIn.destroyBlock(new BlockPos(pos), true, entityIn);
		}

	}
	
	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player)
	{
		return new ItemStack( TYPE == 'l' ? Items.LANTERN : Items.AIR);
	}
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos blockpos)
	{
		 return this.mayPlaceOn(worldIn.getBlockState(blockpos.below()), worldIn, blockpos.below());
	}
	
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos)
	{
//		FluidState ifluidstate = worldIn.getFluidState(pos);
//		return ifluidstate.getType() == Fluids.WATER || state.getMaterial() == Material.ICE;
	  FluidState fluidstate = worldIn.getFluidState(pos);
	  FluidState fluidstate1 = worldIn.getFluidState(pos.above());
	  return (fluidstate.getType() == Fluids.WATER || state.getMaterial() == Material.ICE) && fluidstate1.getType() == Fluids.EMPTY;		
	}
	
	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		return !canSurvive(stateIn, worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn;
	}
	
	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
	      return type == PathComputationType.AIR && !this.hasCollision ? true : super.isPathfindable(state, worldIn, pos, type);
	}
	
	public static class ColorHandler implements BlockColor 
	{
		@Override
		public int getColor(BlockState state, @Nullable BlockAndTintGetter  reader , @Nullable BlockPos blockPos, int tintIndex)
		{
			if(reader != null && blockPos != null)
			{
				return BiomeColors.getAverageFoliageColor(reader, blockPos);
			}
			return GrassColor.get(0.5D, 1.0D);
		}
	}
	

}
