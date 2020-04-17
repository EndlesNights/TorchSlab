package com.endlesnights.torchslabmod.futuremc;

import com.endlesnights.torchslabmod.ITorchSlabCompat;
import com.endlesnights.torchslabmod.PlaceHandlerLanternSlab;
import com.endlesnights.torchslabmod.PlaceHandlerLanternWall;
import com.endlesnights.torchslabmod.TorchSlabMod;
import com.endlesnights.torchslabmod.vanilla.BlockTorchSlab;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import thedarkcolour.futuremc.init.Init;

@ObjectHolder(TorchSlabMod.MODID)
public class FutureMCCompat implements ITorchSlabCompat
{
	public static final Block lantern_slab = null;
	public static final Block lantern_wall = null;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(new BlockLanternSlab().setRegistryName(TorchSlabMod.MODID, "lantern_slab").setUnlocalizedName("lantern_slab"));
		event.getRegistry().register(new BlockLanternWall().setRegistryName(TorchSlabMod.MODID, "lantern_wall").setUnlocalizedName("lantern_wall"));
	}

	@Override
	public void registerPlaceEntries()
	{		
		PlaceHandlerLanternSlab.registerPlaceEntry(Init.LANTERN.getRegistryName(),lantern_slab.getDefaultState());
		PlaceHandlerLanternWall.registerPlaceEntry(Init.LANTERN.getRegistryName(),lantern_wall.getDefaultState());

	}

}
