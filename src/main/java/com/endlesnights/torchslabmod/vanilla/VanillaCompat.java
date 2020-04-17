package com.endlesnights.torchslabmod.vanilla;

import com.endlesnights.torchslabmod.ITorchSlabCompat;
import com.endlesnights.torchslabmod.PlaceHandlerLanternSlab;
import com.endlesnights.torchslabmod.PlaceHandlerTorch;
import com.endlesnights.torchslabmod.TorchSlabMod;
import com.endlesnights.torchslabmod.futuremc.BlockLanternSlab;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import thedarkcolour.futuremc.init.Init;

@ObjectHolder(TorchSlabMod.MODID)
public class VanillaCompat implements ITorchSlabCompat
{
	public static final Block TORCH = null;
	
	
	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new BlockTorchSlab().setRegistryName(TorchSlabMod.MODID, "torch").setUnlocalizedName("torch"));
		
	}
	
	@Override
	public void registerPlaceEntries()
	{
		PlaceHandlerTorch.registerPlaceEntry(Blocks.TORCH.getRegistryName(), TORCH.getDefaultState());
	}
}
