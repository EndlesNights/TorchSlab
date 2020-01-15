package com.endlesnights.torchslabsmod.upgradeaquatic;

import com.endlesnights.torchslabsmod.vanilla.BlockTorchSlab;
import java.util.Random;

import com.teamabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.TorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockJellyTorchSlab extends BlockTorchSlab implements IBucketPickupHandler, ILiquidContainer, IWaterLoggable
{
	public enum JellyTorchType {
		PINK, 
		PURPLE, 
		BLUE, 
		GREEN, 
		YELLOW, 
		ORANGE,
		RED,
		WHITE;
	}
	private final JellyTorchType torchType;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public BlockJellyTorchSlab(Properties props, JellyTorchType torchType) {
		super(props);
		this.torchType = torchType;
		this.setDefaultState(this.stateContainer.getBaseState().with(WATERLOGGED, false));
	}
	
	@Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		double xOffset = rand.nextBoolean() ? -(Math.random() * 0.1) : (Math.random() * 0.1);
		double yOffset = rand.nextBoolean() ? -(Math.random() * 0.1) : (Math.random() * 0.1);
		double zOffset = rand.nextBoolean() ? -(Math.random() * 0.1) : (Math.random() * 0.1);
        double d0 = (double) pos.getX() + 0.5d + xOffset;
        double d1 = (double) pos.getY() + yOffset;
        double d2 = (double) pos.getZ() + 0.5d + zOffset;
        world.addParticle(getTorchParticleType(this.torchType), d0, d1, d2, 0d, 0.0d, 0d);
    }

	@Override
	public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
	    return super.updatePostPlacement(stateIn, facing, stateIn, worldIn, currentPos, facingPos);
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return super.getStateForPlacement(context).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
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
	
	public static BasicParticleType getTorchParticleType(JellyTorchType type) {
		switch(type) {
			default:
         	case PINK:
         		return UAParticles.PINK_JELLY_FLAME;
         	case PURPLE:
         		return UAParticles.PURPLE_JELLY_FLAME;
         	case BLUE:
                return UAParticles.BLUE_JELLY_FLAME;
         	case GREEN:
                return UAParticles.GREEN_JELLY_FLAME;
         	case YELLOW:
                return UAParticles.YELLOW_JELLY_FLAME;
         	case ORANGE:
                return UAParticles.ORANGE_JELLY_FLAME;
         	case RED:
                return UAParticles.RED_JELLY_FLAME;
         	case WHITE:
                return UAParticles.WHITE_JELLY_FLAME;
        }
    }
	
	@Override
	public ResourceLocation getLootTable()
	{
		switch(torchType)
		{
			default:
			case PINK:
				return UABlocks.JELLY_TORCH_PINK.getLootTable();
			case PURPLE:
				return UABlocks.JELLY_TORCH_PURPLE.getLootTable();
			case BLUE:
				return UABlocks.JELLY_TORCH_BLUE.getLootTable();
			case GREEN:
				return UABlocks.JELLY_TORCH_GREEN.getLootTable();
			case YELLOW:
				return UABlocks.JELLY_TORCH_YELLOW.getLootTable();
			case ORANGE:
				return UABlocks.JELLY_TORCH_ORANGE.getLootTable();
			case RED:
				return UABlocks.JELLY_TORCH_RED.getLootTable();
			case WHITE:
				return UABlocks.JELLY_TORCH_WHITE.getLootTable();
		}
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		switch(torchType)
		{
			default:
			case PINK:
				return new ItemStack( UABlocks.JELLY_TORCH_PINK);
			case PURPLE:
				return new ItemStack( UABlocks.JELLY_TORCH_PURPLE);
			case BLUE:
				return new ItemStack( UABlocks.JELLY_TORCH_BLUE);
			case GREEN:
				return new ItemStack( UABlocks.JELLY_TORCH_GREEN);
			case YELLOW:
				return new ItemStack( UABlocks.JELLY_TORCH_YELLOW);
			case ORANGE:
				return new ItemStack( UABlocks.JELLY_TORCH_ORANGE);
			case RED:
				return new ItemStack( UABlocks.JELLY_TORCH_RED);
			case WHITE:
				return new ItemStack( UABlocks.JELLY_TORCH_WHITE);
		}
	}
}
