package com.endlesnights.torchslabsmod.blocks.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChainBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class BlockChainSlab extends ChainBlock implements IWaterLoggable
{
	public static final BooleanProperty HANGING = BooleanProperty.create("hanging");
	public static final BooleanProperty HANGING_UP = BooleanProperty.create("hanging_up");
	public static final BooleanProperty HANGING_DOWN = BooleanProperty.create("hanging_down");
	
	public BlockChainSlab(Properties properties)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(HANGING, false)
				.with(HANGING_UP, false)
				.with(HANGING_DOWN, false)
				);
	}
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		
		builder.add(HANGING, HANGING_UP, HANGING_DOWN, WATERLOGGED);
	}
	public FluidState getFluidState(BlockState state) {
		//WATERLOGGABLE
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		return super.updatePostPlacement(stateIn
				.with(HANGING, worldIn.getBlockState(currentPos.up()).getBlock() instanceof SlabBlock && worldIn.getBlockState(currentPos.up()).get(SlabBlock.TYPE) == SlabType.TOP)
				.with(HANGING_UP, Block.hasEnoughSolidSide(worldIn, currentPos.up(), Direction.DOWN))
				.with(HANGING_DOWN, Block.hasEnoughSolidSide(worldIn, currentPos.down(), Direction.UP)),
				facing, facingState, worldIn, currentPos, facingPos);
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(Items.CHAIN);
	}
}
