package com.endlesnights.torchslabmod;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

public interface ITorchSlabCompat
{
	public void registerBlocks(RegistryEvent.Register<Block> event);
	public void registerPlaceEntries();
}
