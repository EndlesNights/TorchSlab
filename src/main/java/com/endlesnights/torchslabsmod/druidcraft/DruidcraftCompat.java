package com.endlesnights.torchslabsmod.druidcraft;

import com.vulp.druidcraft.registry.ItemRegistry;

import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.PlaceHandlerBottomSlab;
import com.endlesnights.torchslabsmod.TorchSlabsMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;

public class DruidcraftCompat implements ITorchSlabCompat
{
	public static Block BLOCKFIERYTORCHSLAB = null;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(BLOCKFIERYTORCHSLAB = new BlockFieryTorchSlab(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0f).lightValue(15).sound(SoundType.BAMBOO)).setRegistryName(new ResourceLocation(TorchSlabsMod.MODID, "druidcraft_fiery_torch")));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandlerBottomSlab.registerPlaceEntry(ItemRegistry.fiery_torch.getRegistryName(), BLOCKFIERYTORCHSLAB);
	}

}
