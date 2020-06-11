package com.endlesnights.torchslabsmod.blocks.druidcraft;

import com.endlesnights.naturalslabsmod.blocks.FenceSlabBlock;
import com.endlesnights.torchslabsmod.blocks.quark.BlockChainSlab;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockCeramicLanternSlab extends Block implements IWaterLoggable
{

    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    //public static final BooleanProperty ROPED = BooleanProperty.create("roped");
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    
	public BlockCeramicLanternSlab(Properties properties)
	{
		super(properties);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HANGING, WATERLOGGED);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		VoxelShape lantern_grounded = VoxelShapes.or(VoxelShapes.or(Block.makeCuboidShape(4.0f, 0.0f, 4.0f, 12.0f, 2.0f, 12.0f), Block.makeCuboidShape(3.0f, 2.0f, 3.0f, 13.0f, 8.0f, 13.0f)), Block.makeCuboidShape(5.0f, 8.0f, 5.0f, 11.0f, 10.0f, 11.0f));
		VoxelShape lantern_hanging = VoxelShapes.or(VoxelShapes.or(Block.makeCuboidShape(4.0f, 1.0f, 4.0f, 12.0f, 3.0f, 12.0f), Block.makeCuboidShape(3.0f, 3.0f, 3.0f, 13.0f, 9.0f, 13.0f)), Block.makeCuboidShape(5.0f, 9.0f, 5.0f, 11.0f, 11.0f, 11.0f));

		if (state.get(HANGING)) {
//			if (state.get(ROPED)) {
//				return VoxelShapes.or(lantern_hanging, VoxelShapes.or(Block.makeCuboidShape(5.0f, 11.0f, 5.0f, 11.0f, 14.0f, 11.0f), Block.makeCuboidShape(6.0f, 14.0f, 6.0f, 10.0f, 16.0f, 10.0f)));
//			}
			return VoxelShapes.or(lantern_hanging, Block.makeCuboidShape(5.0f, 11.0f, 5.0f, 11.0f, 16.0f, 11.0f));
		}
		return VoxelShapes.or(lantern_grounded, Block.makeCuboidShape(5.0f, 10.0f, 5.0f, 11.0f, 13.0f, 11.0f));
    }
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		if(state == this.getDefaultState())
			return facing == Direction.DOWN && !isValidPosition(state, world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
		else
			return facing == Direction.UP && !isValidPosition(state, world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
				
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		if(state.get(HANGING) == false)
			return ((world.getBlockState(pos.offset(Direction.DOWN)).getProperties().contains(SlabBlock.TYPE) 
				&& world.getBlockState(pos.offset(Direction.DOWN)).get(SlabBlock.TYPE) == SlabType.BOTTOM)
					|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(pos.offset(Direction.DOWN)).getBlock() instanceof FenceSlabBlock));		
		else
			return  ((world.getBlockState(pos.offset(Direction.UP)).getProperties().contains(SlabBlock.TYPE) 
					&& world.getBlockState(pos.offset(Direction.UP)).get(SlabBlock.TYPE) == SlabType.TOP)
					|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(pos.offset(Direction.UP)).getBlock() instanceof FenceSlabBlock)
					|| world.getBlockState(pos.offset(Direction.UP)).getBlock() instanceof BlockChainSlab);
	}
	
    @Override
    public PushReaction getPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    @SuppressWarnings("deprecation")
    public IFluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidState) {
        if (!state.get(WATERLOGGED) && fluidState.getFluid() == Fluids.WATER) {
            if (!worldIn.isRemote()) {
                worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
                worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }
    
	@Override
	public ResourceLocation getLootTable()
	{
		return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("druidcraft:ceramic_lantern")).getLootTable();
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("druidcraft:fiery_torch")));
	}
}
