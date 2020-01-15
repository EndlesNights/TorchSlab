package com.endlesnights.torchslabsmod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.endlesnights.torchslabsmod.druidcraft.DruidcraftCompat;
import com.endlesnights.torchslabsmod.druidcraft.modernity.ModernityCompat;
import com.endlesnights.torchslabsmod.upgradeaquatic.UpgradeAquaticCompat;
//import com.endlesnights.torchslabsmod.quark.QuarkCompat;
import com.endlesnights.torchslabsmod.vanilla.VanillaCompat;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
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
		System.out.println("CompatList Add Vanilla List");
		
		
		if(ModList.get().isLoaded("druidcraft"))
		{
			System.out.println("DRUIDCRAFT DETECTED AND LOADED TORCHSLAB COMPAT");
			compatList.add(DruidcraftCompat::new);
		}

		if(ModList.get().isLoaded("upgrade_aquatic"))
		{
			System.out.println("UpgradeAquatic  DETECTED AND LOADED TORCHSLAB COMPAT");
			compatList.add(UpgradeAquaticCompat::new);
		}

		if(ModList.get().isLoaded("modernity"))
		{
			System.out.println("Modernity DETECTED AND LOADED TORCHSLAB COMPAT");
			compatList.add(ModernityCompat::new);
		}

		
//		if(ModList.get().isLoaded("quark"))
//		{
//			System.out.println("QUARK DETECTED AND LOADED TORCHSLAB COMPAT");
//			compatList.add(QuarkCompat::new);
//		}

		



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
