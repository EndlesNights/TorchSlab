package com.endlesnights.torchslabsmod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import com.endlesnights.torchslabsmod.config.Config;
//import com.endlesnights.torchslabsmod.blocks.bambooblock.BambooBlockCompat;
//import com.endlesnights.torchslabsmod.blocks.buzzierbees.BuzzierBeesCompat;
//import com.endlesnights.torchslabsmod.blocks.buzzierbees.tileentity.BBTileEntities;
//import com.endlesnights.torchslabsmod.blocks.druidcraft.DruidCraftCompat;
//import com.endlesnights.torchslabsmod.blocks.quark.QuarkCompat;
//import com.endlesnights.torchslabsmod.blocks.upgradeaquatic.UACompat;
import com.endlesnights.torchslabsmod.blocks.vanilla.VanillaCompat;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(TorchSlabsMod.MODID)
@EventBusSubscriber(bus = Bus.MOD)
public class TorchSlabsMod
{
	public static final String MODID = "torchslabmod";
	public static final String NAME = "Torch Slab Mod";
	private static final List<Supplier<ITorchSlabCompat>> compatList = new ArrayList<>(Arrays.asList(
			VanillaCompat::new
	));

	public TorchSlabsMod()
	{

		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER, "torchslabmod-server.toml");
		Config.loadConfig(Config.SERVER, FMLPaths.CONFIGDIR.get().resolve("torchslabmod-server.toml").toString());

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		for(Supplier<ITorchSlabCompat> compat : compatList)
		{
			compat.get().registerBlocks(bus);
		}

//		if(ModList.get().isLoaded("buzzierbees"))
//		{
//			System.out.println("BuzzierBees Mod DETECTED AND LOADED TORCHSLAB COMPAT");
//			compatList.add(BuzzierBeesCompat::new);
//			BBTileEntities.TILE_ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
//		}
//		
//		if(ModList.get().isLoaded("bambooblocks"))
//		{
//			System.out.println("Bamboo Blocks Mod DETECTED AND LOADED TORCHSLAB COMPAT");
//			compatList.add(BambooBlockCompat::new);
//		}
//		
//		if(ModList.get().isLoaded("quark"))
//		{
//			System.out.println("Quark Mod DETECTED AND LOADED TORCHSLAB COMPAT");
//			compatList.add(QuarkCompat::new);
//		}
//		
//		if(ModList.get().isLoaded("upgrade_aquatic"))
//		{
//			System.out.println("Upgrade Aquatic Mod DETECTED AND LOADED TORCHSLAB COMPAT");
//			compatList.add(UACompat::new);
//		}
//		
//		if(ModList.get().isLoaded("druidcraft"))
//		{
//			System.out.println("Druid Craft Mod DETECTED AND LOADED TORCHSLAB COMPAT");
//			compatList.add(DruidCraftCompat::new);
//		}
	}

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event)
	{
		for(Supplier<ITorchSlabCompat> compat : compatList)
		{
			compat.get().registerRenderTypes();
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
