package com.endlesnights.torchslabsmod.blocks.bambooblock;

import java.util.Random;

import com.endlesnights.naturalslabsmod.blocks.FenceSlabBlock;
import com.pugz.bambooblocks.common.block.BambooTorchBlock;
import com.pugz.bambooblocks.core.registry.BambooBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;

public class BlockBambooTorchSlab extends TorchBlock
{
	protected static final IntegerProperty SIZE = IntegerProperty.create("size", 0, 2);
	protected static final VoxelShape SLAB_SHAPE = Block.makeCuboidShape(5.5D, -8.0D, 5.5D, 10.5D, 10.0D, 10.5D);
	protected static final VoxelShape SLAB_SHAPE_LARGE = Block.makeCuboidShape(4.0D, -8.0D, 4.0D, 12.0D, 10.0D, 12.0D);
	
	public BlockBambooTorchSlab()
	{
		super(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(14).sound(SoundType.BAMBOO));
		setDefaultState(stateContainer.getBaseState().with(SIZE, 0));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(SIZE);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return state.get(SIZE) < 2 ? SLAB_SHAPE : SLAB_SHAPE_LARGE;
	}
	
	@Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return VoxelShapes.empty();
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		return facing == Direction.DOWN && !isValidPosition(state, world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return ((world.getBlockState(pos.offset(Direction.DOWN)).getProperties().contains(SlabBlock.TYPE) 
				&& world.getBlockState(pos.offset(Direction.DOWN)).get(SlabBlock.TYPE) == SlabType.BOTTOM)
				|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(pos.offset(Direction.DOWN)).getBlock() instanceof FenceSlabBlock));
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.9D;
		double z = pos.getZ() + 0.5D;
		double offset = -0.50D;

		world.addParticle(ParticleTypes.SMOKE, x, y + offset, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(ParticleTypes.FLAME, x, y + offset, z, 0.0D, 0.0D, 0.0D);
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(BambooBlocks.BAMBOO_TORCH.get().asItem());
	}	
	
}
