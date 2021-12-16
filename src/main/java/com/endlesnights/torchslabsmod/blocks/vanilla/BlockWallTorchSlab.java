package com.endlesnights.torchslabsmod.blocks.vanilla;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;


import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndRodBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.RedstoneWallTorchBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StoneButtonBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.WoodButtonBlock;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
//import net.minecraft.block.AirBlock;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.EndRodBlock;
//import net.minecraft.block.FlowingFluidBlock;
//import net.minecraft.block.LadderBlock;
//import net.minecraft.block.RedstoneWallTorchBlock;
//import net.minecraft.block.SlabBlock;
//import net.minecraft.block.StairBlock;
//import net.minecraft.block.StoneButtonBlock;
//import net.minecraft.block.TorchBlock;
//import net.minecraft.block.VineBlock;
//import net.minecraft.block.WallSignBlock;
//import net.minecraft.block.WallTorchBlock;
//import net.minecraft.block.WebBlock;
//import net.minecraft.block.WoodButtonBlock;
//import net.minecraft.entity.player.Player;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.Items;
//import net.minecraft.particles.ParticleOptions;
//import net.minecraft.particles.ParticleTypes;
//import net.minecraft.state.EnumProperty;
//import net.minecraft.state.StateContainer;
//import net.minecraft.state.properties.BlockStateProperties;
//import net.minecraft.state.properties.Half;
//import net.minecraft.state.properties.SlabType;
//import net.minecraft.util.Direction;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.HitResult;
//import net.minecraft.util.math.shapes.CollisionContext;
//import net.minecraft.util.math.shapes.VoxelShape;
//import net.minecraft.world.BlockGetter;
//import net.minecraft.world.LevelAccessor;
//import net.minecraft.world.LevelReader;
//import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockWallTorchSlab extends WallTorchBlock
{

	private static final Map<Direction, VoxelShape> SHAPES_BOTTOM = Maps.newEnumMap(ImmutableMap.of(
			   Direction.NORTH, Block.box(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), 
			   Direction.SOUTH, Block.box(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), 
			   Direction.WEST, Block.box(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), 
			   Direction.EAST, Block.box(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

	private static final Map<Direction, VoxelShape> SHAPES_TOP = Maps.newEnumMap(ImmutableMap.of(
			   Direction.NORTH, Block.box(5.5D, 11.0D, 11.0D, 10.5D, 21.0D, 16.0D), 
			   Direction.SOUTH, Block.box(5.5D, 11.0D, 0.0D, 10.5D, 21.0D, 5.0D), 
			   Direction.WEST, Block.box(11.0D, 11.0D, 5.5D, 16.0D, 21.0D, 10.5D), 
			   Direction.EAST, Block.box(0.0D, 11.0D, 5.5D, 5.0D, 21.0D, 10.5D)));
	
	public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
	
	final ParticleOptions FLAMEPART;
	final Character TYPE;
	
	public BlockWallTorchSlab(Block.Properties properties, ParticleOptions particleType, Character type)
	{
		super(properties, particleType);
//		this.setDefaultState(this.stateContainer.getBaseState().setValue(FACING, Direction.NORTH).setValue(HALF, Half.BOTTOM));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HALF, Half.BOTTOM));
		this.TYPE = type;
		this.FLAMEPART = particleType;
	}
	
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, HALF);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		if(state.getValue(HALF) == Half.TOP)
			return SHAPES_TOP.get(state.getValue(FACING));
		else
			return SHAPES_BOTTOM.get(state.getValue(FACING));
	}
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
	{
		Direction direction = state.getValue(FACING).getOpposite();
		

		
		if(state.getValue(HALF) == Half.BOTTOM
			&& ((worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof SlabBlock && worldIn.getBlockState(pos.relative(direction)).getValue(SlabBlock.TYPE) == SlabType.BOTTOM)
			|| (worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof StairBlock && worldIn.getBlockState(pos.relative(direction)).getValue(StairBlock.HALF) == Half.BOTTOM )
			|| super.canSurvive(state, worldIn, pos)))
				return true;
		else if(state.getValue(HALF) == Half.TOP
			&& ((worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof SlabBlock && worldIn.getBlockState(pos.relative(direction)).getValue(SlabBlock.TYPE) == SlabType.TOP)
			|| (worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof StairBlock && worldIn.getBlockState(pos.relative(direction)).getValue(StairBlock.HALF) == Half.TOP )
			|| super.canSurvive(state, worldIn, pos)))
		{
			return validTop(worldIn.getBlockState(pos.above()), state);
		}	
		
		return false;
	}
	
	public static boolean validTop(BlockState state, BlockState torchState)
	{
		if(
				state.getBlock() instanceof AirBlock
				|| state.getBlock() instanceof LiquidBlock
				||(state.getBlock() instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.TOP)
				|| (state.getBlock() instanceof StairBlock && state.getValue(HALF) == Half.TOP && state.getValue(FACING) == torchState.getValue(FACING))
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
	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand)
	{
		Direction direction = stateIn.getValue(FACING);
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;
	      
		if(stateIn.getValue(HALF) == Half.TOP)
			d1 += 0.5D;
		
		Direction direction1 = direction.getOpposite();
		worldIn.addParticle(ParticleTypes.SMOKE, d0 + 0.27D * (double)direction1.getStepX(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(FLAMEPART, d0 + 0.27D * (double)direction1.getStepX(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
	   }
	
	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player)
	{
		return new ItemStack(TYPE == 't' ? Items.TORCH : Items.AIR);
	}
}
