package com.endlesnights.torchslabsmod.blocks.vanilla;

import java.util.Random;

//import com.endlesnights.naturalslabsmod.blocks.FenceSlabBlock;

//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.SlabBlock;
//import net.minecraft.block.TorchBlock;
//import net.minecraft.entity.player.Player;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.Items;
//import net.minecraft.particles.ParticleOptions;
//import net.minecraft.particles.ParticleTypes;
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
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.fml.ModList;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.TorchBlock;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class BlockTorchSlab extends TorchBlock
{
	protected static final VoxelShape SLAB_SHAPE = Block.box(6.0D, -8.0D, 6.0D, 10.0D, 8.0D, 10.0D);
	
	final ParticleOptions FLAMEPART;
	final Character TYPE;

	public BlockTorchSlab(Block.Properties properties, ParticleOptions particleType, Character type)
	{
		super(properties, particleType);
		this.TYPE = type;
		this.FLAMEPART = particleType;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		return SLAB_SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos)
	{
		return facing == Direction.DOWN && !canSurvive(state, world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
	{
		
		return ((world.getBlockState(pos.below()).getBlock() instanceof SlabBlock 
//				&& world.getBlockState(pos.below()).get(SlabBlock.TYPE) == SlabType.BOTTOM)
				&& world.getBlockState(pos.below()).getValue(SlabBlock.TYPE) == SlabType.BOTTOM)
				//|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(pos.relative(Direction.DOWN)).getBlock() instanceof FenceSlabBlock)
				);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level world, BlockPos pos, RandomSource rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.7D;
		double z = pos.getZ() + 0.5D;
		double offset = -0.50D;

		world.addParticle(ParticleTypes.SMOKE, x, y + offset, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(FLAMEPART, x, y + offset, z, 0.0D, 0.0D, 0.0D);
	}
	
	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter  world, BlockPos pos, Player player)
	{
		return new ItemStack(TYPE == 't' ? Items.TORCH : Items.AIR);
	}
}
