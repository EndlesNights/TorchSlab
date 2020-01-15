package com.endlesnights.torchslabsmod.upgradeaquatic;

import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.PlaceHandlerBottomSlab;
import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.SoundType;
import net.minecraftforge.event.RegistryEvent.Register;

public class UpgradeAquaticCompat  implements ITorchSlabCompat
{	
	public static Block JELLY_TORCH_SLAB_PINK              = null;
	public static Block JELLY_TORCH_SLAB_PURPLE            = null;
	public static Block JELLY_TORCH_SLAB_BLUE              = null;
	public static Block JELLY_TORCH_SLAB_GREEN             = null;
	public static Block JELLY_TORCH_SLAB_YELLOW            = null;
	public static Block JELLY_TORCH_SLAB_ORANGE            = null;
	public static Block JELLY_TORCH_SLAB_RED               = null;
    public static Block JELLY_TORCH_SLAB_WHITE             = null;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		
		event.getRegistry().register(JELLY_TORCH_SLAB_PINK = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.PINK).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_pink"));
		event.getRegistry().register(JELLY_TORCH_SLAB_PURPLE = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.PURPLE).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_purple"));
		event.getRegistry().register(JELLY_TORCH_SLAB_BLUE = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.BLUE).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_blue"));
		event.getRegistry().register(JELLY_TORCH_SLAB_GREEN = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.GREEN).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_green"));
		event.getRegistry().register(JELLY_TORCH_SLAB_YELLOW = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.YELLOW).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_yellow"));
		event.getRegistry().register(JELLY_TORCH_SLAB_ORANGE = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.ORANGE).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_orange"));
		event.getRegistry().register(JELLY_TORCH_SLAB_RED = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.RED).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_red"));
		event.getRegistry().register(JELLY_TORCH_SLAB_WHITE = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.WHITE).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_white"));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandlerBottomSlab.registerPlaceEntry(UABlocks.JELLY_TORCH_PINK.getRegistryName(), JELLY_TORCH_SLAB_PINK);
		PlaceHandlerBottomSlab.registerPlaceEntry(UABlocks.JELLY_TORCH_PURPLE.getRegistryName(), JELLY_TORCH_SLAB_PURPLE);
		PlaceHandlerBottomSlab.registerPlaceEntry(UABlocks.JELLY_TORCH_BLUE.getRegistryName(), JELLY_TORCH_SLAB_BLUE);
		PlaceHandlerBottomSlab.registerPlaceEntry(UABlocks.JELLY_TORCH_GREEN.getRegistryName(), JELLY_TORCH_SLAB_GREEN);
		PlaceHandlerBottomSlab.registerPlaceEntry(UABlocks.JELLY_TORCH_YELLOW.getRegistryName(), JELLY_TORCH_SLAB_YELLOW);
		PlaceHandlerBottomSlab.registerPlaceEntry(UABlocks.JELLY_TORCH_ORANGE.getRegistryName(), JELLY_TORCH_SLAB_ORANGE);
		PlaceHandlerBottomSlab.registerPlaceEntry(UABlocks.JELLY_TORCH_RED.getRegistryName(), JELLY_TORCH_SLAB_RED);
		PlaceHandlerBottomSlab.registerPlaceEntry(UABlocks.JELLY_TORCH_WHITE.getRegistryName(), JELLY_TORCH_SLAB_WHITE);
	}
}