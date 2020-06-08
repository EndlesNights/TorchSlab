package com.endlesnights.torchslabsmod.blocks.upgradeaquatic;

import java.util.Random;

import com.endlesnights.torchslabsmod.blocks.vanilla.BlockWallTorchSlab;
import com.teamabnormals.upgrade_aquatic.client.particle.UAParticles;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.IWaterLoggable;
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
import net.minecraft.state.properties.Half;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockWallJellyTorchSlab extends BlockWallTorchSlab implements IBucketPickupHandler, ILiquidContainer, IWaterLoggable
{
	public enum JellyTorchType {
		PINK(TextFormatting.LIGHT_PURPLE), 
		PURPLE(TextFormatting.DARK_PURPLE), 
		BLUE(TextFormatting.BLUE), 
		GREEN(TextFormatting.GREEN), 
		YELLOW(TextFormatting.YELLOW), 
		ORANGE(TextFormatting.GOLD),
		RED(TextFormatting.RED),
		WHITE(TextFormatting.WHITE);
		
		public final TextFormatting color;
		
		private JellyTorchType(TextFormatting color) {
			this.color = color;
		}
	}
	
	private final JellyTorchType torchType;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public BlockWallJellyTorchSlab(Properties props, JellyTorchType torchType) {
		super(props);
		this.torchType = torchType;
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(WATERLOGGED, false)
				.with(HORIZONTAL_FACING, Direction.NORTH)
				.with(HALF, Half.BOTTOM));
	}
	
	@Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random random)
	{
        Direction direction = state.get(HORIZONTAL_FACING);
        double xOffset = random.nextBoolean() ? -(Math.random() * 0.075) : (Math.random() * 0.075);
		double yOffset = random.nextBoolean() ? -(Math.random() * 0.075) : (Math.random() * 0.075);
		double zOffset = random.nextBoolean() ? -(Math.random() * 0.075) : (Math.random() * 0.075);
		
        double d0 = (double) pos.getX() + 0.5d + xOffset;
        double d1 = (double) pos.getY() + 0.45d + yOffset;
        double d2 = (double) pos.getZ() + 0.5d + zOffset;
        double d3 = 0.18d;
        double d4 = 0.3d;
        
		if(state.get(HALF) == Half.TOP)
			d1 += 0.5D;
        
        Direction facing = direction.getOpposite();
        world.addParticle(getTorchParticleType(this.torchType), d0 + d4 * (double) facing.getXOffset(), d1 + d3, d2 + d4 * (double) facing.getZOffset(), 0d, 0.004d, 0d);
    }

	@Override
	public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED, HORIZONTAL_FACING, HALF);
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
		try
		{
			switch(torchType)
			{
				default:
				case PINK:
					return UABlocks.PINK_JELLY_TORCH.get().getLootTable();
				case PURPLE:
					return UABlocks.PURPLE_JELLY_TORCH.get().getLootTable();
				case BLUE:
					return UABlocks.BLUE_JELLY_TORCH.get().getLootTable();
				case GREEN:
					return UABlocks.GREEN_JELLY_TORCH.get().getLootTable();
				case YELLOW:
					return UABlocks.YELLOW_JELLY_TORCH.get().getLootTable();
				case ORANGE:
					return UABlocks.ORANGE_JELLY_TORCH.get().getLootTable();
				case RED:
					return UABlocks.RED_JELLY_TORCH.get().getLootTable();
				case WHITE:
					return UABlocks.WHITE_JELLY_TORCH.get().getLootTable();
			}
		}
		catch(Error e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & Upgrade Aquatic. Skipping Block:" + e.getMessage());
			return null;
		}

	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		try
		{
			switch(torchType)
			{
				default:
				case PINK:
					return new ItemStack( UABlocks.PINK_JELLY_TORCH.get());
				case PURPLE:
					return new ItemStack( UABlocks.PURPLE_JELLY_TORCH.get());
				case BLUE:
					return new ItemStack( UABlocks.BLUE_JELLY_TORCH.get());
				case GREEN:
					return new ItemStack( UABlocks.GREEN_JELLY_TORCH.get());
				case YELLOW:
					return new ItemStack( UABlocks.YELLOW_JELLY_TORCH.get());
				case ORANGE:
					return new ItemStack( UABlocks.ORANGE_JELLY_TORCH.get());
				case RED:
					return new ItemStack( UABlocks.RED_JELLY_TORCH.get());
				case WHITE:
					return new ItemStack( UABlocks.WHITE_JELLY_TORCH.get());
			}
		}
		catch(Error e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & Upgrade Aquatic. Skipping Block:" + e.getMessage());
			return null;
		}
		

	}
}