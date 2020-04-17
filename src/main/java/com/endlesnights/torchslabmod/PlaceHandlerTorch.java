package com.endlesnights.torchslabmod;

import java.util.Collection;
import java.util.HashMap;

import com.endlesnights.torchslabmod.config.Config;
import com.endlesnights.torchslabmod.vanilla.BlockTorchSlab;
import com.endlesnights.torchslabmod.vanilla.BlockTorchSlab.EnumBlockHalf;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.BlockEndRod;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockVine;
import net.minecraft.block.BlockWallSign;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid=TorchSlabMod.MODID)
public class PlaceHandlerTorch
{
	private static final HashMap<ResourceLocation,IBlockState> PLACE_ENTRIES = new HashMap<>();

	@SubscribeEvent
	public static void onBlockEntityPlace(RightClickBlock event)
	{
		ItemStack held = event.getItemStack();
		ResourceLocation rl = held.getItem().getRegistryName();
		
		if(PLACE_ENTRIES.containsKey(rl))
		{
			placeTorch(event, held, PLACE_ENTRIES.get(rl));
		}
	}

	private static void placeTorch(RightClickBlock event, ItemStack held, IBlockState state)
	{
		BlockPos pos = event.getPos();
		EnumFacing face = event.getFace();
		BlockPos placeAt = pos.offset(face);
		World world = event.getWorld();
		EntityPlayer playerIn = event.getEntityPlayer();
				
		if(!playerIn.isSneaking() && Config.InteractiveBlockCheckList.contains(world.getBlockState(pos).getBlock().getRegistryName().toString()))
			{
				return;
			}
		
		if(face == EnumFacing.UP 
				&& (world.isAirBlock(placeAt)|| world.getBlockState(placeAt).getBlock() == Blocks.WATER || world.getBlockState(placeAt).getBlock() == Blocks.FLOWING_WATER)
				&& world.getBlockState(pos).getBlock() instanceof BlockSlab 
				&& world.getBlockState(pos).getProperties().containsValue(BlockSlab.EnumBlockHalf.BOTTOM))
		{
			world.setBlockState(placeAt, state);
			
			SoundType soundType;
			soundType = state.getBlock().getSoundType(state, world, pos, playerIn);
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundCategory.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			playerIn.swingArm(event.getHand());

			if(!playerIn.isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}
		else if(face.getAxis().isHorizontal())
		{
			if(world.getBlockState(pos).getBlock().isSideSolid(world.getBlockState(pos), world, pos, face) //  hasSolidSide(world.getBlockState(pos), world, pos, face)
				&& (world.isAirBlock(placeAt)|| world.getBlockState(placeAt).getBlock() == Blocks.WATER || world.getBlockState(placeAt).getBlock() == Blocks.FLOWING_WATER))
			{	
				
				
				if(blockHalf(playerIn, pos, face) >= 0 && validTop(world.getBlockState(placeAt.up()), state.withProperty(BlockTorchSlab.FACING, face).withProperty(BlockTorchSlab.HALF, EnumBlockHalf.TOP) ))
				{ 
					if(!Config.UpperBlockCheck)
						return;
					
					world.setBlockState(placeAt, state.withProperty(BlockTorchSlab.FACING, face).withProperty(BlockTorchSlab.HALF, EnumBlockHalf.TOP));
				}
				else
				{
					world.setBlockState(placeAt, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, face));
					//world.setBlockState(placeAt,state.withProperty(BlockTorchSlab.FACING, face).withProperty(BlockTorchSlab.HALF, EnumBlockHalf.BOTTOM));
				}
				
				SoundType soundType;
				soundType = state.getBlock().getSoundType(state, world, pos, playerIn);
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundCategory.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
				playerIn.swingArm(event.getHand());
				
				if(!playerIn.isCreative())
					held.shrink(1);
				event.setCanceled(true);
			}
			else if(((world.getBlockState(pos).getBlock() instanceof BlockSlab   &&  world.getBlockState(pos).getProperties().containsValue(BlockSlab.EnumBlockHalf.BOTTOM))
					|| (world.getBlockState(pos).getBlock() instanceof BlockStairs  && world.getBlockState(pos).getProperties().containsValue(BlockStairs.EnumHalf.BOTTOM)))
					&& (world.isAirBlock(placeAt)|| world.getBlockState(placeAt).getBlock() == Blocks.WATER || world.getBlockState(placeAt).getBlock() == Blocks.FLOWING_WATER))
			{
				SoundType soundType;
				soundType = state.getBlock().getSoundType(state, world, pos, playerIn);
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundCategory.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
				playerIn.swingArm(event.getHand());
				
				world.setBlockState(placeAt,state.withProperty(BlockTorchSlab.FACING, face).withProperty(BlockTorchSlab.HALF, EnumBlockHalf.BOTTOM));
				
				if(!playerIn.isCreative())
					held.shrink(1);
				event.setCanceled(true);
			}
			else if(((world.getBlockState(pos).getBlock() instanceof BlockSlab  &&  world.getBlockState(pos).getProperties().containsValue(BlockSlab.EnumBlockHalf.TOP))
					|| (world.getBlockState(pos).getBlock() instanceof BlockStairs  && world.getBlockState(pos).getProperties().containsValue(BlockStairs.EnumHalf.TOP)))
					&& (world.isAirBlock(placeAt)|| world.getBlockState(placeAt).getBlock() == Blocks.WATER || world.getBlockState(placeAt).getBlock() == Blocks.FLOWING_WATER)
					&& validTop(world.getBlockState(placeAt.up()), state.withProperty(BlockTorchSlab.FACING, face).withProperty(BlockTorchSlab.HALF, EnumBlockHalf.TOP) ))
			{
				SoundType soundType;
				soundType = state.getBlock().getSoundType(state, world, pos, playerIn);
				world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundCategory.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
				playerIn.swingArm(event.getHand());
				
				world.setBlockState(placeAt,state.withProperty(BlockTorchSlab.FACING, face).withProperty(BlockTorchSlab.HALF, EnumBlockHalf.TOP));
			
				if(!playerIn.isCreative())
					held.shrink(1);
				event.setCanceled(true);
			}
		}
	}

	static boolean validTop(IBlockState state, IBlockState torchState)
	{
		if(
				state.getBlock() instanceof BlockAir
				|| state.getBlock() instanceof BlockFluidBase
				||(state.getBlock() instanceof BlockSlab && state.getProperties().containsValue(BlockSlab.EnumBlockHalf.TOP))
				|| (state.getBlock() instanceof BlockStairs 
						&& state.getProperties().containsValue(BlockStairs.EnumHalf.TOP)
						&& state.getProperties().containsValue(BlockHorizontal.FACING) == torchState.getProperties().containsValue(BlockHorizontal.FACING))
				|| state.getBlock() instanceof BlockEndRod
				|| state.getBlock() instanceof BlockTorch
				|| state.getBlock() instanceof BlockWallSign
				|| state.getBlock() instanceof BlockLadder
				|| state.getBlock() instanceof BlockWeb
				|| state.getBlock() instanceof BlockVine
				|| state.getBlock() instanceof BlockRedstoneTorch
				|| state.getBlock() instanceof BlockButtonStone
				|| state.getBlock() instanceof BlockButtonWood
				|| state.getBlock() instanceof BlockButton
				)
			return true;
		
		return false;
	}
	
	static double blockHalf(EntityPlayer playerIn, BlockPos pos, EnumFacing face)
	{
		double angleX = Math.toRadians(playerIn.rotationYaw);
		double angleY = Math.toRadians(playerIn.rotationPitch);
		
		double sinYaw = Math.sin(angleX);
		double cosYaw = Math.cos(angleX);
		
		double sinPitch = Math.sin(angleY);
		double cosPitch = Math.cos(angleY);
		
		Vec3d directionAngle = new Vec3d (cosPitch * cosYaw, sinPitch, cosPitch * sinYaw);
		Double yOffset = playerIn.getPositionEyes(0).y - (pos.getY() + 0.5);
		
		if(face == EnumFacing.NORTH) //Z axis offset by 1
		{			
			double magnatude = (playerIn.getPositionEyes(1).z - (pos.getZ()) ) / directionAngle.x;
			return magnatude * directionAngle.y + yOffset;
		}
		else if(face == EnumFacing.SOUTH)
		{
			double magnatude = (playerIn.getPositionEyes(1).z - (pos.getZ() +1) ) / directionAngle.x;
			return magnatude * directionAngle.y + yOffset;
		}
		else if(face == EnumFacing.WEST)
		{
			double magnatude = (playerIn.getPositionEyes(1).x - (pos.getX()) ) / -directionAngle.z;
			return magnatude * directionAngle.y + yOffset;
		}
		else if(face == EnumFacing.EAST)
		{
			double magnatude = (playerIn.getPositionEyes(1).x - (pos.getX() +1) ) / -directionAngle.z;
			return magnatude * directionAngle.y + yOffset;
		}
		
		return 0;
	}
	
	public static void registerPlaceEntry(ResourceLocation itemName, IBlockState blockState)
	{
		if(!PLACE_ENTRIES.containsKey(itemName))
			PLACE_ENTRIES.put(itemName, blockState);
	}
	
	public static Collection<IBlockState> getPlaceEntryBlocks()
	{
		return PLACE_ENTRIES.values();
	}
}
