package com.endlesnights.torchslabsmod.blocks.vanilla;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockPadCandle extends CandleBlock{

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;;
	public static final VoxelShape PAD_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 1.5D, 15.0D);
	
	private static final VoxelShape ONE_AABB = Shapes.join( Block.box(7.0D, 0.0D, 7.0D, 9.0D, 6.0D, 9.0D), PAD_SHAPE, BooleanOp.OR);
	private static final VoxelShape TWO_AABB = Shapes.join( Block.box(5.0D, 0.0D, 6.0D, 11.0D, 6.0D, 9.0D),PAD_SHAPE, BooleanOp.OR);
	private static final VoxelShape THREE_AABB = Shapes.join( Block.box(5.0D, 0.0D, 6.0D, 10.0D, 6.0D, 11.0D),PAD_SHAPE, BooleanOp.OR);
	private static final VoxelShape FOUR_AABB = Shapes.join( Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 10.0D),PAD_SHAPE, BooleanOp.OR);

	   private static final Int2ObjectMap<List<Vec3>> PARTICLE_OFFSETS = Util.make(() -> {
		      Int2ObjectMap<List<Vec3>> int2objectmap = new Int2ObjectOpenHashMap<>();
		      int2objectmap.defaultReturnValue(ImmutableList.of());
		      int2objectmap.put(1, List.of(new Vec3(0.5D, 0.5D, 0.5D)));
		      int2objectmap.put(2, List.of(new Vec3(0.375D, 0.44D, 0.5D), new Vec3(0.625D, 0.5D, 0.44D)));
		      int2objectmap.put(3, List.of(new Vec3(0.5D, 0.313D, 0.625D), new Vec3(0.375D, 0.44D, 0.5D), new Vec3(0.56D, 0.5D, 0.44D)));
		      int2objectmap.put(4, List.of(new Vec3(0.44D, 0.313D, 0.56D), new Vec3(0.625D, 0.44D, 0.56D), new Vec3(0.375D, 0.44D, 0.375D), new Vec3(0.56D, 0.5D, 0.375D)));
		      return Int2ObjectMaps.unmodifiable(int2objectmap);
		   });
	   
	private Item pickerItem;
	   
	public BlockPadCandle(Properties properties, Item item) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(CANDLES, Integer.valueOf(1))
				.setValue(LIT, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false))
		);
		this.pickerItem = item;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, CANDLES, LIT, WATERLOGGED);	
	}
	
	public VoxelShape getShape(BlockState p_152817_, BlockGetter p_152818_, BlockPos p_152819_, CollisionContext p_152820_) {
		switch(p_152817_.getValue(CANDLES)) {
			case 1:
			default:
				return rotateShape(this.defaultBlockState().getValue(FACING),p_152817_.getValue(FACING) ,ONE_AABB);
			case 2:
				return rotateShape(this.defaultBlockState().getValue(FACING),p_152817_.getValue(FACING) ,TWO_AABB);
			case 3:
				return rotateShape(this.defaultBlockState().getValue(FACING),p_152817_.getValue(FACING) ,THREE_AABB);
			case 4:
				return rotateShape(this.defaultBlockState().getValue(FACING),p_152817_.getValue(FACING) ,FOUR_AABB);
		}
	}
	
	public static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
		VoxelShape[] buffer = new VoxelShape[]{ shape, Shapes.empty() };

		int times = (to.get2DDataValue() - from.get2DDataValue() + 4) % 4;
		for (int i = 0; i < times; i++) {
			buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1], Shapes.create(1-maxZ, minY, minX, 1-minZ, maxY, maxX)));
			buffer[0] = buffer[1];
			buffer[1] = Shapes.empty();
		}

		return buffer[0];		
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		switch(state.getValue(CANDLES)) {
		case 1:
		default:
			return rotateShape(this.defaultBlockState().getValue(FACING),state.getValue(FACING) ,ONE_AABB);
		case 2:
			return rotateShape(this.defaultBlockState().getValue(FACING),state.getValue(FACING) ,TWO_AABB);
		case 3:
			return rotateShape(this.defaultBlockState().getValue(FACING),state.getValue(FACING) ,THREE_AABB);
		case 4:
			return rotateShape(this.defaultBlockState().getValue(FACING),state.getValue(FACING) ,FOUR_AABB);
	}
	}
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos blockpos)
	{
		return this.mayPlaceOn(worldIn.getBlockState(blockpos.below()), worldIn, blockpos.below());
	}
	
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
//		FluidState ifluidstate = worldIn.getFluidState(pos);
//		return ifluidstate.getType() == Fluids.WATER || state.getMaterial() == Material.ICE;
	      FluidState fluidstate = worldIn.getFluidState(pos);
	      FluidState fluidstate1 = worldIn.getFluidState(pos.above());
	      return (fluidstate.getType() == Fluids.WATER || state.getMaterial() == Material.ICE) && fluidstate1.getType() == Fluids.EMPTY;		
	}
	
	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		return !canSurvive(stateIn, worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn;
	}
	
	@Override
	protected Iterable<Vec3> getParticleOffsets(BlockState p_152812_) {
		
		if(p_152812_.getValue(FACING) == Direction.NORTH) {
			return PARTICLE_OFFSETS.get(p_152812_.getValue(CANDLES).intValue());
		}
		
		List<Vec3> vecList = new ArrayList<>();
		for(Vec3 v : PARTICLE_OFFSETS.get(p_152812_.getValue(CANDLES).intValue())) {
			
				float angle = 1.5708f; //west
				if(p_152812_.getValue(FACING) == Direction.EAST) {
					angle = -1.5708f;
				}
				else if(p_152812_.getValue(FACING) == Direction.SOUTH) {
					angle = 3.1416f;
				}
				Vec3 newV = new Vec3(
						Math.cos(angle) * ( v.x - 0.5D) + Math.sin(angle) * ( v.z - 0.5D ) + 0.5D,
						v.y,
						Math.cos(angle) * ( v.z - 0.5D) - Math.sin(angle) * ( v.x - 0.5D ) + 0.5D
				);
				vecList.add(newV);
			}
			
		return vecList;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState p_151929_, Level p_151930_, BlockPos p_151931_, Random p_151932_) {
	      if (p_151929_.getValue(LIT)) {
	         this.getParticleOffsets(p_151929_).forEach((p_151917_) -> {
	            addParticlesAndSound(p_151930_, p_151917_.add((double)p_151931_.getX(), (double)p_151931_.getY(), (double)p_151931_.getZ()), p_151932_);
	         });
	      }
	   }
   private static void addParticlesAndSound(Level p_151910_, Vec3 p_151911_, Random p_151912_) {
	      float f = p_151912_.nextFloat();
	      if (f < 0.3F) {
	         p_151910_.addParticle(ParticleTypes.SMOKE, p_151911_.x, p_151911_.y, p_151911_.z, 0.0D, 0.0D, 0.0D);
	         if (f < 0.17F) {
	            p_151910_.playLocalSound(p_151911_.x + 0.5D, p_151911_.y + 0.5D, p_151911_.z + 0.5D, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1.0F + p_151912_.nextFloat(), p_151912_.nextFloat() * 0.7F + 0.3F, false);
	         }
	      }

	      p_151910_.addParticle(ParticleTypes.SMALL_FLAME, p_151911_.x, p_151911_.y, p_151911_.z, 0.0D, 0.0D, 0.0D);
	   }
   
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult p_225533_6_) {
		ItemStack itemStack = playerIn.getItemInHand(hand);
		if(itemStack.getItem() instanceof FlintAndSteelItem) {
			return onItemUseFlint(state, worldIn, pos, playerIn, hand, p_225533_6_);
		}
		return super.use(state, worldIn, pos, playerIn, hand, p_225533_6_);
	}
	   
	public static InteractionResult onItemUseFlint(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult p_225533_6_) {
		   
		UseOnContext context = new UseOnContext(playerIn, hand, p_225533_6_);
		Level world = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
			
		BlockState blockstate = state
			.setValue(CandleBlock.LIT, true)
			.setValue(CandleBlock.CANDLES, state.getValue(CandleBlock.CANDLES))
			.setValue(CandleBlock.WATERLOGGED, state.getValue(CandleBlock.WATERLOGGED)
		);
		    
		if (blockstate != null) {
			Player playerentity = context.getPlayer();
			world.playSound(playerentity, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);			    
			world.setBlockAndUpdate(blockpos, blockstate);
			if (playerentity != null) {
				context.getItemInHand().hurtAndBreak(1, playerentity, (p_220043_1_) -> {
					p_220043_1_.broadcastBreakEvent(context.getHand());
				});
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
		return type == PathComputationType.AIR && !this.hasCollision ? true : super.isPathfindable(state, worldIn, pos, type);
	}	
			
	public static class ColorHandler implements BlockColor {
		@Override
		public int getColor(BlockState state, @Nullable BlockAndTintGetter  reader , @Nullable BlockPos blockPos, int tintIndex) {
			if(reader != null && blockPos != null) {
				return BiomeColors.getAverageFoliageColor(reader, blockPos);
			}
			return GrassColor.get(0.5D, 1.0D);
		}
	}
			   
	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter  world, BlockPos pos, Player player) {
		return new ItemStack(pickerItem);
	}	
}
