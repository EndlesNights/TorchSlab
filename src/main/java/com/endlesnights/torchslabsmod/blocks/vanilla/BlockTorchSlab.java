package com.endlesnights.torchslabsmod.blocks.vanilla;

import java.util.Random;

//import com.endlesnights.naturalslabsmod.blocks.FenceSlabBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;

public class BlockTorchSlab extends TorchBlock
{
	protected static final VoxelShape SLAB_SHAPE = Block.makeCuboidShape(6.0D, -8.0D, 6.0D, 10.0D, 8.0D, 10.0D);
	
	final IParticleData FLAMEPART;
	final Character TYPE;

	public BlockTorchSlab(Block.Properties properties, IParticleData particleType, Character type)
	{
		super(properties, particleType);
		this.TYPE = type;
		this.FLAMEPART = particleType;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return SLAB_SHAPE;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		return facing == Direction.DOWN && !isValidPosition(state, world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return ((world.getBlockState(pos.offset(Direction.DOWN)).getBlock() instanceof SlabBlock 
				&& world.getBlockState(pos.offset(Direction.DOWN)).get(SlabBlock.TYPE) == SlabType.BOTTOM)
				//|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(pos.offset(Direction.DOWN)).getBlock() instanceof FenceSlabBlock)
				);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.7D;
		double z = pos.getZ() + 0.5D;
		double offset = -0.50D;

		world.addParticle(ParticleTypes.SMOKE, x, y + offset, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(FLAMEPART, x, y + offset, z, 0.0D, 0.0D, 0.0D);
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(TYPE == 't' ? Items.TORCH : Items.SOUL_TORCH);
	}
}