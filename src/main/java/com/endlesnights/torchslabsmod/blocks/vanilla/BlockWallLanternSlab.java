package com.endlesnights.torchslabsmod.blocks.vanilla;

//import com.endlesnights.naturalslabsmod.blocks.FenceSlabBlock;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndRodBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.RedstoneWallTorchBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.StoneButtonBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WebBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fml.ModList;

public class BlockWallLanternSlab extends LanternBlock
{
	protected static final VoxelShape UPPER_SHAPE_EAST = VoxelShapes.combine(
			Block.makeCuboidShape(2.0D, 3.0D, 5.0D, 8.0D, 10.0D, 11.0D),
			Block.makeCuboidShape(3.0D, 10.0D, 6.0D, 7.0D, 12.0D, 10.0D),
			IBooleanFunction.OR);
	protected static final VoxelShape UPPER_SHAPE_SOUTH = VoxelShapes.combine(
			Block.makeCuboidShape(5.0D, 3.0D, 2.0D, 11.0D, 10.0D, 8.0D),
			Block.makeCuboidShape(6.0D, 10.0D, 3.0D, 10.0D, 12.0D, 7.0D),
			IBooleanFunction.OR);
	protected static final VoxelShape UPPER_SHAPE_WEST = VoxelShapes.combine(
			Block.makeCuboidShape(8.0D, 3.0D, 5.0D, 14.0D, 10.0D, 11.0D),
			Block.makeCuboidShape(9.0D, 10.0D, 6.0D, 13.0D, 12.0D, 10.0D),
			IBooleanFunction.OR);
	protected static final VoxelShape UPPER_SHAPE_NORTH = VoxelShapes.combine(
			Block.makeCuboidShape(5.0D, 3.0D, 8.0D, 11.0D, 10.0D, 14.0D),
			Block.makeCuboidShape(6.0D, 10.0D, 9.0D, 10.0D, 12.0D, 13.0D),
			IBooleanFunction.OR);	
	
	protected static final VoxelShape LOWER_SHAPE_EAST = VoxelShapes.or(
			   Block.makeCuboidShape(2.0D, -5.0D, 5.0D, 8.0D, 2.0D, 11.0D),
			   Block.makeCuboidShape(3.0D, 2.0D, 6.0D, 7.0D, 4.0D, 10.0D));
	protected static final VoxelShape LOWER_SHAPE_SOUTH = VoxelShapes.or(
			   Block.makeCuboidShape(5.0D, -5.0D, 2.0D, 11.0D, 2.0D, 8.0D),
			   Block.makeCuboidShape(6.0D, 2.0D, 3.0D, 10.0D, 4.0D, 7.0D));
	protected static final VoxelShape LOWER_SHAPE_WEST = VoxelShapes.or(
			   Block.makeCuboidShape(8.0D, -5.0D, 5.0D, 14.0D, 2.0D, 11.0D),
			   Block.makeCuboidShape(9.0D, 2.0D, 6.0D, 13.0D, 4.0D, 10.0D));
	protected static final VoxelShape LOWER_SHAPE_NORTH = VoxelShapes.or(
			   Block.makeCuboidShape(5.0D, -5.0D, 8.0D, 11.0D, 2.0D, 14.0D),
			   Block.makeCuboidShape(6.0D, 2.0D, 9.0D, 10.0D, 4.0D, 13.0D));

	public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
	public static final DirectionProperty HORIZONTAL_FACING = HorizontalBlock.HORIZONTAL_FACING;
	final Character TYPE;
	
	public BlockWallLanternSlab(Block.Properties properties, Character type)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(HALF, Half.BOTTOM).with(field_242675_b, false));
		this.TYPE = type;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(HORIZONTAL_FACING, HALF, HANGING, field_242675_b);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		if(state.get(HALF) == Half.BOTTOM)
		{
			if(state.get(HORIZONTAL_FACING) == Direction.EAST)
				return LOWER_SHAPE_EAST;
			else if(state.get(HORIZONTAL_FACING) == Direction.SOUTH)
				return LOWER_SHAPE_SOUTH;
			else if(state.get(HORIZONTAL_FACING) == Direction.WEST)
				return LOWER_SHAPE_WEST;
			else
				return LOWER_SHAPE_NORTH;
		}
		else
		{
			if(state.get(HORIZONTAL_FACING) == Direction.EAST)
				return UPPER_SHAPE_EAST;
			else if(state.get(HORIZONTAL_FACING) == Direction.SOUTH)
				return UPPER_SHAPE_SOUTH;
			else if(state.get(HORIZONTAL_FACING) == Direction.WEST)
				return UPPER_SHAPE_WEST;
			else
				return UPPER_SHAPE_NORTH;
		}
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
	{
		Direction direction = state.get(HORIZONTAL_FACING).getOpposite();		
		
		if(state.get(HALF) == Half.BOTTOM
			&& ((worldIn.getBlockState(pos.offset(direction)).getBlock() instanceof SlabBlock && worldIn.getBlockState(pos.offset(direction)).get(SlabBlock.TYPE) == SlabType.BOTTOM)
			|| (worldIn.getBlockState(pos.offset(direction)).getBlock() instanceof StairsBlock && worldIn.getBlockState(pos.offset(direction)).get(StairsBlock.HALF) == Half.BOTTOM )
			|| (worldIn.getBlockState(pos.offset(direction)).getBlock() instanceof FenceBlock)
			//|| (ModList.get().isLoaded("naturalslabsmod") && worldIn.getBlockState(pos.offset(direction)).getBlock() instanceof FenceSlabBlock)
			|| (Block.hasEnoughSolidSide(worldIn, pos.offset(direction), direction) )))
		{
			return validBot(worldIn.getBlockState(pos.down()), state);
		}
		else if(state.get(HALF) == Half.TOP
			&& ((worldIn.getBlockState(pos.offset(direction)).getBlock() instanceof SlabBlock && worldIn.getBlockState(pos.offset(direction)).get(SlabBlock.TYPE) == SlabType.TOP)
			|| (worldIn.getBlockState(pos.offset(direction)).getBlock() instanceof StairsBlock && worldIn.getBlockState(pos.offset(direction)).get(StairsBlock.HALF) == Half.TOP )
			|| (worldIn.getBlockState(pos.offset(direction)).getBlock() instanceof FenceBlock)
			//|| (ModList.get().isLoaded("naturalslabsmod") && worldIn.getBlockState(pos.offset(direction)).getBlock() instanceof FenceSlabBlock)
			|| (Block.hasEnoughSolidSide( worldIn, pos.offset(direction), direction) )))
				return true;
		
		return false;
	}
	
	public static boolean validBot(BlockState state, BlockState lanternState)
	{
		if(
				state.getBlock() instanceof AirBlock
				|| state.getBlock() instanceof FlowingFluidBlock
				||(state.getBlock() instanceof SlabBlock && state.get(SlabBlock.TYPE) == SlabType.BOTTOM)
				||(state.getBlock() instanceof StairsBlock && state.get(HALF) == Half.BOTTOM && state.get(HORIZONTAL_FACING) == lanternState.get(HORIZONTAL_FACING))
				|| state.getBlock() instanceof EndRodBlock
				|| state.getBlock() instanceof TorchBlock
				|| state.getBlock() instanceof WallSignBlock
				|| state.getBlock() instanceof LadderBlock
				|| state.getBlock() instanceof WebBlock
				|| state.getBlock() instanceof VineBlock
				|| state.getBlock() instanceof RedstoneWallTorchBlock
				|| state.getBlock() instanceof StoneButtonBlock
				|| state.getBlock() instanceof WoodButtonBlock
				)
			return true;
		
		return false;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : stateIn;
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack( TYPE == 'l' ? Items.LANTERN : Items.field_234790_rk_);
	}
	
}
