package com.endlesnights.torchslabsmod.blocks.vanilla;

//import com.endlesnights.naturalslabsmod.blocks.FenceSlabBlock;

import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndRodBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.RedstoneWallTorchBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StoneButtonBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.WoodButtonBlock;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;

public class BlockWallLanternSlab extends LanternBlock
{
	protected static final VoxelShape UPPER_SHAPE_EAST = Shapes.join(
			Block.box(2.0D, 3.0D, 5.0D, 8.0D, 10.0D, 11.0D),
			Block.box(3.0D, 10.0D, 6.0D, 7.0D, 12.0D, 10.0D),
			BooleanOp.OR);
	protected static final VoxelShape UPPER_SHAPE_SOUTH = Shapes.join(
			Block.box(5.0D, 3.0D, 2.0D, 11.0D, 10.0D, 8.0D),
			Block.box(6.0D, 10.0D, 3.0D, 10.0D, 12.0D, 7.0D),
			BooleanOp.OR);
	protected static final VoxelShape UPPER_SHAPE_WEST = Shapes.join(
			Block.box(8.0D, 3.0D, 5.0D, 14.0D, 10.0D, 11.0D),
			Block.box(9.0D, 10.0D, 6.0D, 13.0D, 12.0D, 10.0D),
			BooleanOp.OR);
	protected static final VoxelShape UPPER_SHAPE_NORTH = Shapes.join(
			Block.box(5.0D, 3.0D, 8.0D, 11.0D, 10.0D, 14.0D),
			Block.box(6.0D, 10.0D, 9.0D, 10.0D, 12.0D, 13.0D),
			BooleanOp.OR);	
	
	protected static final VoxelShape LOWER_SHAPE_EAST = Shapes.or(
			   Block.box(2.0D, -5.0D, 5.0D, 8.0D, 2.0D, 11.0D),
			   Block.box(3.0D, 2.0D, 6.0D, 7.0D, 4.0D, 10.0D));
	protected static final VoxelShape LOWER_SHAPE_SOUTH = Shapes.or(
			   Block.box(5.0D, -5.0D, 2.0D, 11.0D, 2.0D, 8.0D),
			   Block.box(6.0D, 2.0D, 3.0D, 10.0D, 4.0D, 7.0D));
	protected static final VoxelShape LOWER_SHAPE_WEST = Shapes.or(
			   Block.box(8.0D, -5.0D, 5.0D, 14.0D, 2.0D, 11.0D),
			   Block.box(9.0D, 2.0D, 6.0D, 13.0D, 4.0D, 10.0D));
	protected static final VoxelShape LOWER_SHAPE_NORTH = Shapes.or(
			   Block.box(5.0D, -5.0D, 8.0D, 11.0D, 2.0D, 14.0D),
			   Block.box(6.0D, 2.0D, 9.0D, 10.0D, 4.0D, 13.0D));

	public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	final Character TYPE;
	
	public BlockWallLanternSlab(Block.Properties properties, Character type)
	{
		super(properties);
//		this.setDefaultState(this.stateContainer.getBaseState().setValue(FACING, Direction.NORTH).setValue(HALF, Half.BOTTOM).setValue(WATERLOGGED, false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HALF, Half.BOTTOM).setValue(WATERLOGGED, Boolean.valueOf(false)));
		this.TYPE = type;
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, HALF, HANGING, WATERLOGGED);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		if(state.getValue(HALF) == Half.BOTTOM)
		{
			if(state.getValue(FACING) == Direction.EAST)
				return LOWER_SHAPE_EAST;
			else if(state.getValue(FACING) == Direction.SOUTH)
				return LOWER_SHAPE_SOUTH;
			else if(state.getValue(FACING) == Direction.WEST)
				return LOWER_SHAPE_WEST;
			else
				return LOWER_SHAPE_NORTH;
		}
		else
		{
			if(state.getValue(FACING) == Direction.EAST)
				return UPPER_SHAPE_EAST;
			else if(state.getValue(FACING) == Direction.SOUTH)
				return UPPER_SHAPE_SOUTH;
			else if(state.getValue(FACING) == Direction.WEST)
				return UPPER_SHAPE_WEST;
			else
				return UPPER_SHAPE_NORTH;
		}
	}
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
	{
		Direction direction = state.getValue(FACING).getOpposite();		
		
		if(state.getValue(HALF) == Half.BOTTOM
			&& ((worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof SlabBlock && worldIn.getBlockState(pos.relative(direction)).getValue(SlabBlock.TYPE) == SlabType.BOTTOM)
			|| (worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof StairBlock && worldIn.getBlockState(pos.relative(direction)).getValue(StairBlock.HALF) == Half.BOTTOM )
			|| (worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof FenceBlock)
			//|| (ModList.get().isLoaded("naturalslabsmod") && worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof FenceSlabBlock)
			|| (Block.canSupportCenter(worldIn, pos.relative(direction), direction) )))
		{
			return validBot(worldIn.getBlockState(pos.below()), state);
		}
		else if(state.getValue(HALF) == Half.TOP
			&& ((worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof SlabBlock && worldIn.getBlockState(pos.relative(direction)).getValue(SlabBlock.TYPE) == SlabType.TOP)
			|| (worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof StairBlock && worldIn.getBlockState(pos.relative(direction)).getValue(StairBlock.HALF) == Half.TOP )
			|| (worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof FenceBlock)
			//|| (ModList.get().isLoaded("naturalslabsmod") && worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof FenceSlabBlock)
			|| (Block.canSupportCenter( worldIn, pos.relative(direction), direction) )))
				return true;
		
		return false;
	}
	
	public static boolean validBot(BlockState state, BlockState lanternState)
	{
		if(
				state.getBlock() instanceof AirBlock
				|| state.getBlock() instanceof LiquidBlock
				||(state.getBlock() instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM)
				||(state.getBlock() instanceof StairBlock && state.getValue(HALF) == Half.BOTTOM && state.getValue(FACING) == lanternState.getValue(FACING))
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
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		return !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn;
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player)
	{
		return new ItemStack( TYPE == 'l' ? Items.LANTERN : Items.AIR);
	}
	
}
