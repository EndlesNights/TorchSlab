package com.endlesnights.torchslabsmod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.endlesnights.torchslabsmod.config.Config;
import com.endlesnights.torchslabsmod.blocks.buzzierbees.BuzzierBeesCompat;
import com.endlesnights.torchslabsmod.blocks.buzzierbees.tileentity.BBTileEntities;
import com.endlesnights.torchslabsmod.blocks.vanilla.VanillaCompat;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(TorchSlabsMod.MODID)
@EventBusSubscriber(bus=Bus.MOD)
public class TorchSlabsMod
{
	public static final String MODID = "torchslabmod";
	public static final String NAME = "Torch Slab Mod";
	private static List<Supplier<ITorchSlabCompat>> compatList = new ArrayList<>();

	public TorchSlabsMod()
	{
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER, "torchslabmod-server.toml");
		Config.loadConfig(Config.SERVER, FMLPaths.CONFIGDIR.get().resolve("torchslabmod-server.toml").toString());
		
		compatList.add(VanillaCompat::new);

		if(ModList.get().isLoaded("buzzierbees"))
		{
			System.out.println("BuzzierBees DETECTED AND LOADED TORCHSLAB COMPAT");
			compatList.add(BuzzierBeesCompat::new);
			BBTileEntities.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
		}
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
