package com.endlesnights.torchslabmod;

import java.util.Collection;
import java.util.HashMap;

import com.endlesnights.torchslabmod.config.Config;
import com.endlesnights.torchslabmod.futuremc.BlockLanternSlab;
import com.endlesnights.torchslabmod.futuremc.BlockLanternWall;
import com.endlesnights.torchslabmod.vanilla.BlockTorchSlab;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.BlockEndRod;
import net.minecraft.block.BlockFence;
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
import net.minecraft.block.BlockSlab.EnumBlockHalf;
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
public class PlaceHandlerLanternSlab
{
	private static final HashMap<ResourceLocation,IBlockState> PLACE_ENTRIES = new HashMap<>();

	@SubscribeEvent
	public static void onBlockEntityPlace(RightClickBlock event)
	{
		ItemStack held = event.getItemStack();
		ResourceLocation rl = held.getItem().getRegistryName();
		
		if(PLACE_ENTRIES.containsKey(rl))
		{
			placeLantern(event, held, PLACE_ENTRIES.get(rl));
		}
	}

	private static void placeLantern(RightClickBlock event, ItemStack held, IBlockState state)
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
		else if(face == EnumFacing.DOWN 
				&& (world.isAirBlock(placeAt)|| world.getBlockState(placeAt).getBlock() == Blocks.WATER || world.getBlockState(placeAt).getBlock() == Blocks.FLOWING_WATER)
				&& world.getBlockState(pos).getBlock() instanceof BlockSlab 
				&& world.getBlockState(pos).getProperties().containsValue(BlockSlab.EnumBlockHalf.TOP))
		{
			world.setBlockState(placeAt, state.withProperty(BlockLanternSlab.HANGING, true));
			
			SoundType soundType;
			soundType = state.getBlock().getSoundType(state, world, pos, playerIn);
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), soundType.getPlaceSound(), SoundCategory.BLOCKS, soundType.getVolume(), soundType.getPitch() - 0.2F);
			playerIn.swingArm(event.getHand());

			if(!playerIn.isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}
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
