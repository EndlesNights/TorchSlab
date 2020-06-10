package com.endlesnights.torchslabsmod.blocks.druidcraft;

import java.util.Random;

import com.endlesnights.torchslabsmod.blocks.vanilla.BlockWallTorchSlab;
import com.vulp.druidcraft.registry.ParticleRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockFieryTorchWallSlab extends BlockWallTorchSlab implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public BlockFieryTorchWallSlab(Properties properties)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(HORIZONTAL_FACING, Direction.NORTH)
				.with(HALF, Half.BOTTOM)
				.with(WATERLOGGED, false));	
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(HORIZONTAL_FACING, HALF, WATERLOGGED);
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
	    return super.updatePostPlacement(stateIn, facing, stateIn, worldIn, currentPos, facingPos);
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
	
	@Override
	@OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        Direction direction = stateIn.get(HORIZONTAL_FACING);
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.65D;
        double d2 = (double) pos.getZ() + 0.5D;
        double d3 = 0.22D;
        double d4 = 0.27D;
        Direction direction1 = direction.getOpposite();
        float limit = 0.05f;
        
		if(stateIn.get(HALF) == Half.TOP)
			d1 += 0.5D;
		
        float offset0 = Math.min(limit, Math.max(-limit, rand.nextFloat() - 0.5f));
        float offset1 = Math.min(limit, Math.max(-limit, rand.nextFloat() - 0.5f));
        float offset2 = Math.min(limit, Math.max(-limit, rand.nextFloat() - 0.5f));
        worldIn.addParticle(ParticleRegistry.fiery_spark, false, d0 + offset0 + d4 * (double) direction1.getXOffset(), d1 + offset1 + d3, d2 + offset2 + d4 * (double) direction1.getZOffset(), 0F, 0F, 0F);
        worldIn.addParticle(ParticleRegistry.fiery_glow, false, d0 + d4 * (double) direction1.getXOffset(), d1 + d3 - 0.1D, d2 + d4 * (double) direction1.getZOffset(), 0F, 0F, 0F);
    }
	
	@Override
	public ResourceLocation getLootTable()
	{
		return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("druidcraft:fiery_torch")).getLootTable();
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("druidcraft:fiery_torch")));
	}
	

}
