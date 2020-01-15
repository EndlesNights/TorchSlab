package com.endlesnights.torchslabsmod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.endlesnights.torchslabsmod.vanilla.VanillaCompat;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;


@Mod(TorchSlabsMod.MODID)
@EventBusSubscriber(bus=Bus.MOD)
public class TorchSlabsMod
{
	public static final String MODID = "torchslabmod";
	public static final String NAME = "Torch Slab Mod";
	private static List<Supplier<ITorchSlabCompat>> compatList = new ArrayList<>();

	public TorchSlabsMod()
	{
		compatList.add(VanillaCompat::new);

	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		for(Supplier<ITorchSlabCompat> compat : compatList)
		{
			compat.get().registerBlocks(event);
		}
	}

	@SubscribeEvent
	public static void onInterModEnqueue(InterModEnqueueEvent event)
	{
		for(Supplier<ITorchSlabCompat> compat : compatList)
		{
			compat.get().registerPlaceEntries();
		}
	}
}
