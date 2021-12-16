package com.endlesnights.torchslabsmod.blocks.vanilla;

import java.util.List;

import com.google.common.collect.ImmutableList;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockCandleSlab extends CandleBlock {

	   private static final VoxelShape ONE_AABB = Block.box(7.0D, -8.0D, 7.0D, 9.0D, 2.0D, 9.0D);
	   private static final VoxelShape TWO_AABB = Block.box(5.0D, -8.0D, 6.0D, 11.0D, 2.0D, 9.0D);
	   private static final VoxelShape THREE_AABB = Block.box(5.0D, -8.0D, 6.0D, 10.0D, 2.0D, 11.0D);
	   private static final VoxelShape FOUR_AABB = Block.box(5.0D, -8.0D, 5.0D, 11.0D, 2.0D, 10.0D);

	   
	   private static final VoxelShape ONE_AABB2 = Block.box(7.0D, -8.0D, 7.0D, 9.0D, 0.0D, 9.0D);
	   private static final VoxelShape TWO_AABB2 = Block.box(5.0D, -8.0D, 6.0D, 11.0D, 0.0D, 9.0D);
	   private static final VoxelShape THREE_AABB2 = Block.box(5.0D, -8.0D, 6.0D, 10.0D, 0.0D, 11.0D);
	   private static final VoxelShape FOUR_AABB2 = Block.box(5.0D, -8.0D, 5.0D, 11.0D, 0.0D, 10.0D);
	   
	   private static final Int2ObjectMap<List<Vec3>> PARTICLE_OFFSETS = Util.make(() -> {
		      Int2ObjectMap<List<Vec3>> int2objectmap = new Int2ObjectOpenHashMap<>();
		      int2objectmap.defaultReturnValue(ImmutableList.of());
		      int2objectmap.put(1, ImmutableList.of(new Vec3(0.5D, 0.0D, 0.5D)));
		      int2objectmap.put(2, ImmutableList.of(new Vec3(0.375D, -0.06, 0.5D), new Vec3(0.625D, 0.0D, 0.44D)));
		      int2objectmap.put(3, ImmutableList.of(new Vec3(0.5D, -0.187D, 0.625D), new Vec3(0.375D, -0.06D, 0.5D), new Vec3(0.56D, 0.0D, 0.44D)));
		      int2objectmap.put(4, ImmutableList.of(new Vec3(0.44D, -0.187D, 0.56D), new Vec3(0.625D, -0.06D, 0.56D), new Vec3(0.375D, -0.06D, 0.375D), new Vec3(0.56D, 0.0D, 0.375D)));
		      return Int2ObjectMaps.unmodifiable(int2objectmap);
		   });
	   
	private Item pickerItem;
	   
	public BlockCandleSlab(Properties properties, Item item) {
		super(properties);
		this.pickerItem = item;
	}

	public VoxelShape getShape(BlockState p_152817_, BlockGetter p_152818_, BlockPos p_152819_, CollisionContext p_152820_) {
		switch(p_152817_.getValue(CANDLES)) {
			case 1:
			default:
				return ONE_AABB;
			case 2:
				return TWO_AABB;
			case 3:
				return THREE_AABB;
			case 4:
				return FOUR_AABB;
		}
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		switch(state.getValue(CANDLES)) {
		case 1:
		default:
			return ONE_AABB2;
		case 2:
			return TWO_AABB2;
		case 3:
			return THREE_AABB2;
		case 4:
			return FOUR_AABB2;
		}
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
				&& world.getBlockState(pos.below()).getValue(SlabBlock.TYPE) == SlabType.BOTTOM)
				);
	}
	
   protected Iterable<Vec3> getParticleOffsets(BlockState p_152812_) {
	      return PARTICLE_OFFSETS.get(p_152812_.getValue(CANDLES).intValue());
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
	    
	    if (blockstate != null)
	    {
	    	Player playerentity = context.getPlayer();
			world.playSound(playerentity, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);			    
			world.setBlockAndUpdate(blockpos, blockstate);
		    if (playerentity != null)
		    {
			context.getItemInHand().hurtAndBreak(1, playerentity, (p_220043_1_) -> {
				    p_220043_1_.broadcastBreakEvent(context.getHand());
				});
		    }
		    return InteractionResult.SUCCESS;
	    }
		
	   return InteractionResult.FAIL;
   }
   
   
	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter  world, BlockPos pos, Player player)
	{
		return new ItemStack(pickerItem);
	}
}
