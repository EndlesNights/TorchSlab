package com.endlesnights.torchslabsmod.blocks.vanilla;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;

public class BlockChainSlab extends ChainBlock implements SimpleWaterloggedBlock
{
	public static final BooleanProperty HANGING = BooleanProperty.create("hanging");
	public static final BooleanProperty HANGING_UP = BooleanProperty.create("hanging_up");
	public static final BooleanProperty HANGING_DOWN = BooleanProperty.create("hanging_down");

	   protected static final VoxelShape axisY = Block.box(6.5D, -8.0D, 6.5D, 9.5D, 8.0D, 9.5D);
	   protected static final VoxelShape axisZ = Block.box(6.5D, -2.5D, 0.0D, 9.5D, 1.5D, 16.0D);
	   protected static final VoxelShape axisX = Block.box(0.0D, -2.5D, 6.5D, 16.0D, 1.5D, 9.5D);
	   
	   protected static final VoxelShape axisYHanging = Block.box(6.5D, 8.0D, 6.5D, 9.5D, 24.0D, 9.5D);
	   protected static final VoxelShape axisZHanging = Block.box(6.5D, 14.5D, 0.0D, 9.5D, 17.5D, 16.0D);
	   protected static final VoxelShape axisXHanging = Block.box(0.0D, 14.5D, 6.5D, 16.0D, 17.5D, 9.5D);
	   
	public BlockChainSlab(Properties properties)
	{
		super(properties);
//		this.setDefaultState(this.stateContainer.getBaseState()
//				.setValue(HANGING, false)
//				.setValue(HANGING_UP, false)
//				.setValue(HANGING_DOWN, false)
//				);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(AXIS, Direction.Axis.Y));
	}
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		
		super.createBlockStateDefinition(builder);
		builder.add(HANGING, HANGING_UP, HANGING_DOWN);
	}
	public FluidState getFluidState(BlockState state) {
		//WATERLOGGABLE
//		return state.getValue(field_235484_b_) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
		return super.getFluidState(state);
	}
	
	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
//		return super.updateShape(stateIn
//				.setValue(HANGING, worldIn.getBlockState(currentPos.above()).getBlock() instanceof SlabBlock && worldIn.getBlockState(currentPos.above()).getValue(SlabBlock.TYPE) == SlabType.TOP)
//				.setValue(HANGING_UP, Block.canSupportCenter(worldIn, currentPos.above(), Direction.DOWN))
//				.setValue(HANGING_DOWN, Block.canSupportCenter(worldIn, currentPos.below(), Direction.UP)),
//				facing, facingState, worldIn, currentPos, facingPos);
	}
	
	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player)
	{
		return new ItemStack(Items.CHAIN); //CHAIN
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		if(state.getValue(HANGING))
		{
		      switch((Direction.Axis)state.getValue(AXIS)) {
		      case X:
		      default:
		         return axisXHanging;
		      case Z:
		         return axisZHanging;
		      case Y:
		         return axisYHanging;
		      }				
		}
		else
		{
		      switch((Direction.Axis)state.getValue(AXIS)) {
		      case X:
		      default:
		         return axisX;
		      case Z:
		         return axisZ;
		      case Y:
		         return axisY;
		      }				
		}
	}
}
