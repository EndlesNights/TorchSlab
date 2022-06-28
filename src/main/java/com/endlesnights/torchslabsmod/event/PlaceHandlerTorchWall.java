package com.endlesnights.torchslabsmod.event;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Supplier;

import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.blocks.vanilla.BlockWallTorchSlab;
import com.endlesnights.torchslabsmod.config.Config;
import com.endlesnights.torchslabsmod.config.TorchSlabConfig;
import com.mojang.math.Vector3d;

import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndRodBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.RedstoneWallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StoneButtonBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid=TorchSlabsMod.MODID)
public class PlaceHandlerTorchWall
{
	private static final HashMap<ResourceLocation, Supplier<Block>> PLACE_ENTRIES = new HashMap<>();
	
	@SubscribeEvent
	public static void onBlockEntityPlace(RightClickBlock event)
	{	
		ItemStack held = event.getItemStack();
		ResourceLocation rl = ForgeRegistries.ITEMS.getKey(held.getItem());

		if(PLACE_ENTRIES.containsKey(rl))
			placeTorch(event, held, PLACE_ENTRIES.get(rl).get());
	}

	private static void placeTorch(RightClickBlock event, ItemStack held, Block block)
	{		
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		BlockPos placeAt = pos.relative(face);
		Level world = event.getWorld();		
		Player playerIn = event.getPlayer();
		SoundType soundType;

		Config.loadConfig(Config.SERVER, FMLPaths.CONFIGDIR.get().resolve("torchslabmod-server.toml").toString());
		if(!playerIn.isSteppingCarefully() && TorchSlabConfig.interactiveCheckList.get().contains(ForgeRegistries.BLOCKS.getKey(world.getBlockState(pos).getBlock()).toString()) )
			return;
		
		if((face != Direction.UP && face != Direction.DOWN)
				&& Block.canSupportCenter(world, pos, face)
				&& (world.isEmptyBlock(placeAt) || world.getBlockState(placeAt).getBlock() == Blocks.WATER || world.getFluidState(placeAt).getType() == Fluids.FLOWING_WATER) )
		{	


			if(blockHalf(playerIn, pos, face) >= 0 && validTop(world.getBlockState(placeAt.above()), block.defaultBlockState().setValue(WallTorchBlock.FACING, face).setValue(BlockWallTorchSlab.HALF, Half.TOP) ))
			{ 
				if(!TorchSlabConfig.upperBlockCheck.get())
					return;
				
				
				if (block instanceof SimpleWaterloggedBlock)
				{
					world.setBlockAndUpdate(placeAt, block.defaultBlockState()
							.setValue(WallTorchBlock.FACING, face)
							.setValue(BlockWallTorchSlab.HALF, Half.TOP)
							.setValue(BlockStateProperties.WATERLOGGED, (world.getFluidState(placeAt).getType() == Fluids.WATER) ));
				}
				else
					world.setBlockAndUpdate(placeAt, block.defaultBlockState().setValue(WallTorchBlock.FACING, face).setValue(BlockWallTorchSlab.HALF, Half.TOP));

			}
			else
			{	
				return;
				//world.setBlockAndUpdate(placeAt, Blocks.WALL_TORCH.defaultBlockState().setValue(WallTorchBlock.FACING, face));
				//world.setBlockAndUpdate(placeAt, block.defaultBlockState().setValue(WallTorchBlock.FACING, face).setValue(BlockWallTorchSlab.HALF, Half.BOTTOM));
			}
			
			soundType = block.getSoundType(block.defaultBlockState(), world, pos, event.getPlayer());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundSource.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getPlayer().swing(event.getHand());
			
			if(!event.getPlayer().isCreative())
				held.shrink(1);
			event.setCanceled(true);

		}
		else if(((world.getBlockState(pos).getBlock() instanceof SlabBlock  && world.getBlockState(pos).getValue(SlabBlock.TYPE) == SlabType.BOTTOM)
				|| (world.getBlockState(pos).getBlock() instanceof StairBlock && world.getBlockState(pos).getValue(StairBlock.HALF) == Half.BOTTOM ))
				&& (face != Direction.UP && face != Direction.DOWN)
				&& (world.isEmptyBlock(placeAt) || world.getBlockState(placeAt).getBlock() == Blocks.WATER || world.getFluidState(placeAt).getType() == Fluids.FLOWING_WATER) )
		{
			soundType = block.getSoundType(block.defaultBlockState(), world, pos, event.getPlayer());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundSource.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getPlayer().swing(event.getHand());
			
			if (block instanceof SimpleWaterloggedBlock)
			{
				world.setBlockAndUpdate(placeAt, block.defaultBlockState()
						.setValue(WallTorchBlock.FACING, face)
						.setValue(BlockWallTorchSlab.HALF, Half.BOTTOM)
						.setValue(BlockStateProperties.WATERLOGGED, (world.getFluidState(placeAt).getType() == Fluids.WATER) ));
			}
			else
				world.setBlockAndUpdate(placeAt, block.defaultBlockState().setValue(WallTorchBlock.FACING, face).setValue(BlockWallTorchSlab.HALF, Half.BOTTOM));		
			
			if(!event.getPlayer().isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}
		else if(((world.getBlockState(pos).getBlock() instanceof SlabBlock && world.getBlockState(pos).getValue(SlabBlock.TYPE) == SlabType.TOP)
				|| (world.getBlockState(pos).getBlock() instanceof StairBlock && world.getBlockState(pos).getValue(StairBlock.HALF) == Half.TOP))
				&& (face != Direction.UP && face != Direction.DOWN)
				&& (world.isEmptyBlock(placeAt) || world.getBlockState(placeAt).getBlock() == Blocks.WATER || world.getFluidState(placeAt).getType() == Fluids.FLOWING_WATER) 
				&& validTop(world.getBlockState(placeAt.above()), block.defaultBlockState().setValue(WallTorchBlock.FACING, face).setValue(BlockWallTorchSlab.HALF, Half.TOP) ))
		{

			soundType = block.getSoundType(block.defaultBlockState(), world, pos, event.getPlayer());
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundSource.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			event.getPlayer().swing(event.getHand());
			
			if (block instanceof SimpleWaterloggedBlock)
			{
				world.setBlockAndUpdate(placeAt, block.defaultBlockState()
						.setValue(WallTorchBlock.FACING, face)
						.setValue(BlockWallTorchSlab.HALF, Half.TOP)
						.setValue(BlockStateProperties.WATERLOGGED, (world.getFluidState(placeAt).getType() == Fluids.WATER) ));
			}
			else
				world.setBlockAndUpdate(placeAt, block.defaultBlockState().setValue(WallTorchBlock.FACING, face).setValue(BlockWallTorchSlab.HALF, Half.TOP));
			
			if(!event.getPlayer().isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}
	}
	static boolean validTop(BlockState state, BlockState torchState)
	{
		if(
				state.getBlock() instanceof AirBlock
				|| state.getBlock() instanceof LiquidBlock
				||(state.getBlock() instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.TOP)
				|| (state.getBlock() instanceof StairBlock && state.getValue(StairBlock.HALF) == Half.TOP && state.getValue(StairBlock.FACING) == torchState.getValue(BlockWallTorchSlab.FACING))
				|| state.getBlock() instanceof EndRodBlock
				|| state.getBlock() instanceof TorchBlock
				|| state.getBlock() instanceof WallSignBlock
				|| state.getBlock() instanceof LadderBlock
				|| state.getBlock() instanceof WebBlock
				|| state.getBlock() instanceof VineBlock
				|| state.getBlock() instanceof RedstoneWallTorchBlock
				|| state.getBlock() instanceof StoneButtonBlock
				|| state.getBlock() instanceof WoodButtonBlock
				)
			return true;
		
		return false;
	}
	static double blockHalf(Player playerIn, BlockPos pos, Direction face)
	{
		double angleX = Math.toRadians(playerIn.getYRot());
		double angleY = Math.toRadians(playerIn.getXRot());
		
		double sinYaw = Math.sin(angleX);
		double cosYaw = Math.cos(angleX);
		
		double sinPitch = Math.sin(angleY);
		double cosPitch = Math.cos(angleY);
		
		Vector3d directionAngle = new Vector3d (cosPitch * cosYaw, sinPitch, cosPitch * sinYaw);
		Double yOffset = playerIn.getEyePosition(1).y - (pos.getY() + 0.5);
		
		if(face == Direction.NORTH) //Z axis offset by 1
		{			
			double magnatude = (playerIn.getEyePosition(1).z - (pos.getZ()) ) / directionAngle.x;
			return magnatude * directionAngle.y + yOffset;
		}
		else if(face == Direction.SOUTH)
		{
			double magnatude = (playerIn.getEyePosition(1).z - (pos.getZ() +1) ) / directionAngle.x;
			return magnatude * directionAngle.y + yOffset;
		}
		else if(face == Direction.WEST)
		{
			double magnatude = (playerIn.getEyePosition(1).x - (pos.getX()) ) / -directionAngle.z;
			return magnatude * directionAngle.y + yOffset;
		}
		else if(face == Direction.EAST)
		{
			double magnatude = (playerIn.getEyePosition(1).x - (pos.getX() +1) ) / -directionAngle.z;
			return magnatude * directionAngle.y + yOffset;
		}
		
		return 0;
	}
	
	public static void registerPlaceEntry(ResourceLocation itemName, Supplier<Block> torchSlabSupplier)
	{
		if(!PLACE_ENTRIES.containsKey(itemName) && torchSlabSupplier != null)
			PLACE_ENTRIES.put(itemName, torchSlabSupplier);
	}
}
