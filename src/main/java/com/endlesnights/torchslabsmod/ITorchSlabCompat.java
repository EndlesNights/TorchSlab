package com.endlesnights.torchslabsmod;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;

public interface ITorchSlabCompat 
{
	public void registerBlocks(RegistryEvent.Register<Block> event);
	public void registerPlaceEntries();
}
