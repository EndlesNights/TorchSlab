package com.endlesnights.torchslabsmod.blocks.upgradeaquatic;

import com.endlesnights.naturalslabsmod.blocks.FenceSlabBlock;
import com.endlesnights.torchslabsmod.blocks.quark.BlockChainSlab;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fml.ModList;

public class BlockToothLanternSlab extends Block implements IBucketPickupHandler, ILiquidContainer, IWaterLoggable
{
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public static final VoxelShape[] SHAPES = new VoxelShape[]
			{
					//LOWER HALF
			VoxelShapes.or( // UP
					makeCuboidShape(7.0F, -8.0F, 7.0D, 9.0D, -4.0D, 9.0D), 
					makeCuboidShape(4.0F, -4.0F, 4.0F, 12.0F, -3.0F, 12.0F), 
					makeCuboidShape(5.0F, -3.0F, 5.0F, 11.0F, 5.0F, 11.0F), 
					makeCuboidShape(4.0F, 5.0F, 4.0F, 12.0F, 6.0F, 12.0F)), 
			VoxelShapes.or( // DOWN
					makeCuboidShape(7.0F, 4.0F, 7.0D, 9.0D, 8.0D, 9.0D), 
					makeCuboidShape(4.0F, -6.0F, 4.0F, 12.0F, -5.0F, 12.0F), 
					makeCuboidShape(5.0F, -5.0F, 5.0F, 11.0F, 3.0F, 11.0F), 
					makeCuboidShape(4.0F, -3.0F, 4.0F, 12.0F, 4.0F, 12.0F)), 
			VoxelShapes.or( // NORTH
					makeCuboidShape(7.0F, 4.0F, 10.0D, 9.0D, 8.0D, 12.0D),
					makeCuboidShape(7.0F, 6.0F, 10.0D, 9.0D, 8.0D, 16.0D), 
					makeCuboidShape(4.0F, -6.0F, 7.0F, 12.0F, -5.0F, 15.0F), 
					makeCuboidShape(5.0F, -5.0F, 8.0F, 11.0F, 3.0F, 14.0F), 
					makeCuboidShape(4.0F, 3.0F, 7.0F, 12.0F, 4.0F, 15.0F)), 
			VoxelShapes.or( // EAST
					makeCuboidShape(4.0F, 4.0F, 7.0D, 6.0D, 8.0D, 9.0D),
					makeCuboidShape(0.0F, 6.0F, 7.0D, 6.0D, 8.0D, 9.0D), 
					makeCuboidShape(1.0F, -6.0F, 4.0F, 9.0F, -5.0F, 12.0F), 
					makeCuboidShape(2.0F, -5.0F, 5.0F, 8.0F, 3.0F, 11.0F), 
					makeCuboidShape(1.0F, 3.0F, 4.0F, 9.0F, 4.0F, 12.0F)), 
			VoxelShapes.or( // SOUTH
					makeCuboidShape(7.0F, 4.0F, 4.0D, 9.0D, 8.0D, 6.0D),
					makeCuboidShape(7.0F, 6.0F, 0.0D, 9.0D, 8.0D, 6.0D), 
					makeCuboidShape(4.0F, -6.0F, 1.0F, 12.0F, -5.0F, 9.0F), 
					makeCuboidShape(5.0F, -5.0F, 2.0F, 11.0F, 3.0F, 8.0F), 
					makeCuboidShape(4.0F, 3.0F, 1.0F, 12.0F, 4.0F, 9.0F)), 
			VoxelShapes.or( // WEST
					makeCuboidShape(10.0F, 4.0F, 7.0D, 12.0D, 8.0D, 9.0D),
					makeCuboidShape(10.0F, 6.0F, 7.0D, 16.0D, 8.0D, 9.0D),
					makeCuboidShape(7.0F, -6.0F, 4.0F, 15.0F, -5.0F, 12.0F),
					makeCuboidShape(8.0F, -5.0F, 5.0F, 14.0F, 3.0F, 11.0F),
					makeCuboidShape(7.0F, 3.0F, 4.0F, 15.0F, 4.0F, 12.0F)),
			
					//UPPER HALF
			VoxelShapes.or( // UP
					makeCuboidShape(7.0F, 8.0F, 7.0D, 9.0D, 12.0D, 9.0D), 
					makeCuboidShape(4.0F, 12.0F, 4.0F, 12.0F, 13.0F, 12.0F),
					makeCuboidShape(5.0F, 13.0F, 5.0F, 11.0F, 21.0F, 11.0F),
					makeCuboidShape(4.0F, 21.0F, 4.0F, 12.0F, 22.0F, 12.0F)),
			VoxelShapes.or( // DOWN
					makeCuboidShape(7.0F, 20.0F, 7.0D, 9.0D, 24.0D, 9.0D), 
					makeCuboidShape(4.0F, 10.0F, 4.0F, 12.0F, 11.0F, 12.0F),
					makeCuboidShape(5.0F, 11.0F, 5.0F, 11.0F, 19.0F, 11.0F),
					makeCuboidShape(4.0F, 19.0F, 4.0F, 12.0F, 20.0F, 12.0F)),
			VoxelShapes.or( // NORTH
					makeCuboidShape(7.0F, 12.0F, 10.0D, 9.0D, 16.0D, 12.0D),
					makeCuboidShape(7.0F, 14.0F, 10.0D, 9.0D, 16.0D, 16.0D),
					makeCuboidShape(4.0F, 2.0F, 7.0F, 12.0F, 3.0F, 15.0F),
					makeCuboidShape(5.0F, 3.0F, 8.0F, 11.0F, 11.0F, 14.0F),
					makeCuboidShape(4.0F, 11.0F, 7.0F, 12.0F, 12.0F, 15.0F)),
			VoxelShapes.or( // EAST
					makeCuboidShape(4.0F, 12.0F, 7.0D, 6.0D, 16.0D, 9.0D),
					makeCuboidShape(0.0F, 14.0F, 7.0D, 6.0D, 16.0D, 9.0D),
					makeCuboidShape(1.0F, 2.0F, 4.0F, 9.0F, 3.0F, 12.0F),
					makeCuboidShape(2.0F, 3.0F, 5.0F, 8.0F, 11.0F, 11.0F),
					makeCuboidShape(1.0F, 11.0F, 4.0F, 9.0F, 12.0F, 12.0F)),
			VoxelShapes.or( // SOUTH
					makeCuboidShape(7.0F, 12.0F, 4.0D, 9.0D, 16.0D, 6.0D),
					makeCuboidShape(7.0F, 14.0F, 0.0D, 9.0D, 16.0D, 6.0D),
					makeCuboidShape(4.0F, 2.0F, 1.0F, 12.0F, 3.0F, 9.0F),
					makeCuboidShape(5.0F, 3.0F, 2.0F, 11.0F, 11.0F, 8.0F),
					makeCuboidShape(4.0F, 11.0F, 1.0F, 12.0F, 12.0F, 9.0F)),
			VoxelShapes.or( // WEST
					makeCuboidShape(10.0F, 12.0F, 7.0D, 12.0D, 16.0D, 9.0D),
					makeCuboidShape(10.0F, 14.0F, 7.0D, 16.0D, 16.0D, 9.0D),
					makeCuboidShape(7.0F, 2.0F, 4.0F, 15.0F, 3.0F, 12.0F),
					makeCuboidShape(8.0F, 3.0F, 5.0F, 14.0F, 11.0F, 11.0F),
					makeCuboidShape(7.0F, 11.0F, 4.0F, 15.0F, 12.0F, 12.0F)),			
		};
	
	public BlockToothLanternSlab(Properties properties)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(FACING, Direction.NORTH)
				.with(WATERLOGGED, false)
				.with(HALF, Half.TOP));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, HALF, WATERLOGGED);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		if(state.get(HALF) == Half.BOTTOM)
		{
			switch(state.get(FACING))
			{
				case UP:
					return SHAPES[0];
				case DOWN:
					default:
					return SHAPES[1];
				case NORTH:
					return SHAPES[2];	
				case EAST:
				    return SHAPES[3]; 
				case SOUTH:
			        return SHAPES[4];
			    case WEST:
			        return SHAPES[5];
			}
		}
		else
		{
			switch(state.get(FACING))
			{
				case UP:
					return SHAPES[6];
				case DOWN:
					default:
					return SHAPES[7];
				case NORTH:
					return SHAPES[8];	
				case EAST:
				    return SHAPES[9]; 
				case SOUTH:
			        return SHAPES[10];
			    case WEST:
			        return SHAPES[11];
			}
		}
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos) {
		Direction direction = state.get(FACING);
		BlockPos blockpos = pos.offset(direction.getOpposite());
		
		if(direction == Direction.UP && state.get(HALF) == Half.BOTTOM)
		{
			return ((world.getBlockState(blockpos).getProperties().contains(SlabBlock.TYPE) 
					&& world.getBlockState(blockpos).get(SlabBlock.TYPE) == SlabType.BOTTOM)
					|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(blockpos).getBlock() instanceof FenceSlabBlock) ); 
		}
		if(direction == Direction.DOWN && state.get(HALF) == Half.TOP)
		{
			return ((world.getBlockState(blockpos).getProperties().contains(SlabBlock.TYPE) 
					&& world.getBlockState(blockpos).get(SlabBlock.TYPE) == SlabType.TOP)
					|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(blockpos).getBlock() instanceof FenceSlabBlock) ); 
		}
		
		if(state.get(HALF) == Half.BOTTOM
				&& ((world.getBlockState(blockpos).getBlock() instanceof SlabBlock && world.getBlockState(blockpos).get(SlabBlock.TYPE) == SlabType.BOTTOM)
				|| (world.getBlockState(blockpos).getBlock() instanceof StairsBlock && world.getBlockState(blockpos).get(StairsBlock.HALF) == Half.BOTTOM )
				|| (world.getBlockState(blockpos).getBlock() instanceof FenceBlock)
				|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(blockpos).getBlock() instanceof FenceSlabBlock)
				|| (Block.hasSolidSide(world.getBlockState(blockpos), world, blockpos, direction) )))
			{
				if(Block.hasSolidSide(world.getBlockState(pos.down()), world, pos.down(), Direction.UP))
					return false;
				else
					return true;
			}
			else if(state.get(HALF) == Half.TOP
				&& ((world.getBlockState(blockpos).getBlock() instanceof SlabBlock && world.getBlockState(blockpos).get(SlabBlock.TYPE) == SlabType.TOP)
				|| (world.getBlockState(blockpos).getBlock() instanceof StairsBlock && world.getBlockState(blockpos).get(StairsBlock.HALF) == Half.TOP )
				|| (world.getBlockState(blockpos).getBlock() instanceof FenceBlock)
				|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(blockpos).getBlock() instanceof FenceSlabBlock)
				|| (world.getBlockState(pos.offset(Direction.UP)).getBlock() instanceof BlockChainSlab)
				|| (Block.hasSolidSide(world.getBlockState(blockpos), world, blockpos, direction) )))
					return true;
		
		return false;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos) {
		return this.isValidPosition(state, world, currentPos) ? state : Blocks.AIR.getDefaultState();
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(UABlocks.TOOTH_LANTERN.get());
	}
	
	@Override
	public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.get(WATERLOGGED)) {
			worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER;
		} else {
	        return Fluids.EMPTY;
		}
	}
	
	@SuppressWarnings("deprecation")
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	@Override
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
	}
	
	@Override
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
		if (!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
			if (!worldIn.isRemote()) {
				worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
	            worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
	         }
	         return true;
		} else {
			return false;
	    }
	}
	
}
