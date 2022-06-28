package com.endlesnights.torchslabsmod;

import net.minecraftforge.eventbus.api.IEventBus;

public interface ITorchSlabCompat 
{
	void registerBlocks(IEventBus bus);
	void registerPlaceEntries();
	void registerRenderTypes();
}
