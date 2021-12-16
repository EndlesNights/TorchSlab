package com.endlesnights.torchslabsmod.blocks.vanilla;

//import com.endlesnights.naturalslabsmod.blocks.FenceSlabBlock;
//import com.endlesnights.torchslabsmod.blocks.quark.BlockChainSlab;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SlabBlock;
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

//import net.minecraftforge.fml.ModList;

public class BlockLanternSlab extends LanternBlock
{
	protected static final VoxelShape SLAB_BOTTOM_SHAPE = Shapes.join(
			Block.box(5.0D, -8.0D, 5.0D, 11.0D, -1.0D, 11.0D), 
			Block.box(6.0D, -1.0D, 6.0D, 10.0D, 1.0D, 10.0D), 
			BooleanOp.OR);
	
	protected static final VoxelShape HANGING = Shapes.join(
			Block.box(5.0D, 9.0D, 5.0D, 11.0D, 16.0D, 11.0D),
			Block.box(6.0D, 16.0D, 6.0D, 10.0D, 18.0D, 10.0D),
			BooleanOp.OR);
	
	final Character TYPE;
	
	public BlockLanternSlab (Block.Properties properties, Character type)
	{
		super(properties);
		this.TYPE = type;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		if(state.getValue(LanternBlock.HANGING))
			return HANGING;
		else
			return SLAB_BOTTOM_SHAPE;
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos)
	{
		if(state == this.defaultBlockState())
			return facing == Direction.DOWN && !canSurvive(state, world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
		else
			return facing == Direction.UP && !canSurvive(state, world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
				
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
	{
		if(state == this.defaultBlockState())
			return ((world.getBlockState(pos.relative(Direction.DOWN)).getBlock() instanceof SlabBlock 
				&& world.getBlockState(pos.relative(Direction.DOWN)).getValue(SlabBlock.TYPE) == SlabType.BOTTOM)
					//|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(pos.relative(Direction.DOWN)).getBlock() instanceof FenceSlabBlock)
					);		
		else
			return  ((world.getBlockState(pos.relative(Direction.UP)).getBlock() instanceof SlabBlock
					&& world.getBlockState(pos.relative(Direction.UP)).getValue(SlabBlock.TYPE) == SlabType.TOP)
					//|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(pos.relative(Direction.UP)).getBlock() instanceof FenceSlabBlock)
					//|| world.getBlockState(pos.relative(Direction.UP)).getBlock() instanceof BlockChainSlab
					);
	}
	
	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player)
	{
		return new ItemStack( TYPE == 'l' ? Items.LANTERN : Items.AIR);
	}
}
