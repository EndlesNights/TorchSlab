package com.endlesnights.torchslabsmod.blocks.bambooblock;

import java.util.Random;

import com.endlesnights.torchslabsmod.blocks.vanilla.BlockPadTorch;
import com.pugz.bambooblocks.core.registry.BambooBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockBambooTorchPad extends BlockPadTorch
{
	public static final VoxelShape SHAPE = VoxelShapes.combine(
			Block.makeCuboidShape(5.5D, 0.0D, 5.5D, 10.5D, 11.5D, 10.5D), //Torch
			PAD_SHAPE,
			IBooleanFunction.OR);
	
	public BlockBambooTorchPad()
	{
		super(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(14).sound(SoundType.BAMBOO));
		this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(BambooBlocks.BAMBOO_TORCH.get().asItem());
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.9D;
		double z = pos.getZ() + 0.5D;
		double offset = +0.025D;

		world.addParticle(ParticleTypes.SMOKE, x, y + offset, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(ParticleTypes.FLAME, x, y + offset, z, 0.0D, 0.0D, 0.0D);
	}
}
