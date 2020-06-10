package com.endlesnights.torchslabsmod.blocks.quark;

import java.util.Locale;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockChainSlab extends Block implements IWaterLoggable
{
	
	private static final VoxelShape SHAPE = makeCuboidShape(6, 8, 6, 10, 24, 10);
	public static final EnumProperty<ChainType> TYPE = EnumProperty.create("type", ChainType.class);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public BlockChainSlab()
	{
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(0.5F).sound(SoundType.LANTERN));
		setDefaultState(getDefaultState().with(TYPE, ChainType.MIDDLE).with(WATERLOGGED, false));
	}
	
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(TYPE, WATERLOGGED);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos upPos = pos.up();
		BlockState upState = worldIn.getBlockState(upPos);
		return upState.getBlock() == this || getChainType(worldIn, pos) == ChainType.TOP;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context)
				.with(TYPE, getChainType(context.getWorld(), context.getPos()))
				.with(WATERLOGGED, context.getWorld().getFluidState(context.getPos()).getFluid() == Fluids.WATER);
	}
	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if(!state.isValidPosition(worldIn, pos)) {
			worldIn.playEvent(2001, pos, Block.getStateId(worldIn.getBlockState(pos)));
			spawnDrops(state, worldIn, pos);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
		} else {
			ChainType curr = state.get(TYPE);
			ChainType target = getChainType(worldIn, pos);
			if(target != curr)
				worldIn.setBlockState(pos, state.with(TYPE, target));
		}
	}

	@Nonnull
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	
	@Nonnull
	@Override
	@SuppressWarnings("deprecation")
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Nonnull
	@Override
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(@Nonnull BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if(stateIn.get(WATERLOGGED))
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));

		return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean allowsMovement(@Nonnull BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, PathType type) {
		return type == PathType.WATER && worldIn.getFluidState(pos).isTagged(FluidTags.WATER); 
	}
	
	public ChainType getChainType(IWorldReader world, BlockPos pos) {
		BlockPos up = pos.up();
		BlockState state = world.getBlockState(up);
		//if(hasSolidSide(state, world, up, Direction.DOWN) || state.getBlock().isIn(BlockTags.FENCES) || state.getBlock().isIn(BlockTags.WALLS))
		if((state.getBlock() instanceof SlabBlock && state.get(SlabBlock.TYPE) == SlabType.TOP))
			return ChainType.TOP;
		
		BlockPos down = pos.down();
		if(world.isAirBlock(down) || world.getBlockState(down).getBlock() == Blocks.WATER)
			return ChainType.BOTTOM;
		
		return ChainType.MIDDLE;
	}
	
	public enum ChainType implements IStringSerializable {
		TOP, MIDDLE, BOTTOM;

		@Override
		public String getName() {
			return name().toLowerCase(Locale.ROOT);
		}
		
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:iron_chain")));
	}

}