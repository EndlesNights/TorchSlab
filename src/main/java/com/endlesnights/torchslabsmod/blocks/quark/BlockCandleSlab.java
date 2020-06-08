package com.endlesnights.torchslabsmod.blocks.quark;

import java.util.Random;

import com.endlesnights.naturalslabsmod.blocks.FenceSlabBlock;
import com.endlesnights.torchslabsmod.blocks.quark.entities.FallingCandleSlabEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
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
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;
import vazkii.quark.building.module.TallowAndCandlesModule;

@SuppressWarnings("deprecation")
public class BlockCandleSlab extends Block
{

	private static final VoxelShape SHAPE = Block.makeCuboidShape(6F, -8F, 6F, 10F, 0F, 10F);
	
	protected final DyeColor color;
	
	public BlockCandleSlab(Properties properties, DyeColor color)
	{
		super(Block.Properties.create(Material.MISCELLANEOUS, color.getMapColor())
				.hardnessAndResistance(0.2F)
				.lightValue(14)
				.sound(SoundType.CLOTH));
		this.color = color;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
	      worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {		
	      worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if(!worldIn.isRemote && TallowAndCandlesModule.candlesFall)
			checkFallable(worldIn, pos);
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{		
		if (!this.isValidPosition(state, worldIn, currentPos))
		{
				return Blocks.AIR.getDefaultState();
		}

		return super.updatePostPlacement(state, facing, facingState, worldIn, currentPos, facingPos);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return ((worldIn.getBlockState(pos.offset(Direction.DOWN)).getProperties().contains(SlabBlock.TYPE) 
				&& worldIn.getBlockState(pos.offset(Direction.DOWN)).get(SlabBlock.TYPE) == SlabType.BOTTOM)
				|| (ModList.get().isLoaded("naturalslabsmod") && worldIn.getBlockState(pos.offset(Direction.DOWN)).getBlock() instanceof FenceSlabBlock) );
	 }
	
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() instanceof SlabBlock && state.get(SlabBlock.TYPE) == SlabType.BOTTOM ;
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
		return (float) TallowAndCandlesModule.enchantPower;
	}

	// Copypasta from FallingBlock
	private void checkFallable(World worldIn, BlockPos pos) {
		if (worldIn.isAirBlock(pos.down()) || FallingBlock.canFallThrough(worldIn.getBlockState(pos.down())) && pos.getY() >= 0) {
			if (!worldIn.isRemote) {
				FallingCandleSlabEntity fallingblockentity = new FallingCandleSlabEntity(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
				worldIn.addEntity(fallingblockentity);
			}
		}
	}

	@Override
	public int tickRate(IWorldReader worldIn) {
		return 2;
	}
	
	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(GetCandle.getParent(color));

	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double d0 = pos.getX() + 0.5D;
		double d1 = pos.getY() + 0.7D - 0.5D;
		double d2 = pos.getZ() + 0.5D;

		worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
	
	protected void onStartFalling(FallingCandleSlabEntity fallingEntity) {
	}

	
	public static boolean canFallThrough(BlockState state) {
		Block block = state.getBlock();
		Material material = state.getMaterial();
		return state.isAir() || block == Blocks.FIRE || material.isLiquid();
	}

	public void onEndFalling(World worldIn, BlockPos pos, BlockState fallingState, BlockState hitState)
	{

	}
	
	public void onBroken(World worldIn, BlockPos pos){
	}

	public void breakFall(World worldIn, BlockPos pos, BlockState fallingState, BlockState hitState, FallingCandleSlabEntity entity)
	{
		if(worldIn.getBlockState(pos).getBlock() instanceof SlabBlock && worldIn.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.BOTTOM
				&& (worldIn.isAirBlock(pos.up()) || worldIn.getFluidState(pos.up()).getFluid() == Fluids.WATER || worldIn.getFluidState(pos.up()).getFluid() == Fluids.FLOWING_WATER))
			{
				worldIn.setBlockState(pos.up(), fallingState);
			}
		else if(worldIn.isAirBlock(pos) || worldIn.getFluidState(pos).getFluid() == Fluids.WATER || worldIn.getFluidState(pos).getFluid() == Fluids.FLOWING_WATER
			|| worldIn.getBlockState(pos).getBlock() instanceof TallGrassBlock
			|| worldIn.getBlockState(pos).getBlock() instanceof SnowBlock && worldIn.getBlockState(pos).get(SnowBlock.LAYERS) == 1)
		{
			worldIn.setBlockState(pos,GetCandle.getParent(color).getDefaultState());
		}
		else if(worldIn.getBlockState(pos).getBlock() instanceof LilyPadBlock)
			worldIn.setBlockState(pos,GetCandle.getPad(color).getDefaultState());
		else
			entity.entityDropItem(new ItemStack(GetCandle.getParent(color).asItem(),1));	
	}
}
