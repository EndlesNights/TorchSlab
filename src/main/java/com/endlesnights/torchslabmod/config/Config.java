package com.endlesnights.torchslabmod.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.endlesnights.torchslabmod.TorchSlabMod;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class Config
{
	private final static String CATEGORY_GENERAL = "general";
	
    public static List<String> InteractiveBlockCheckList = new ArrayList<String>();
    public static boolean UpperBlockCheck;
	
    
	public static void readConfig() {
		Configuration cfg = TorchSlabMod.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
		} catch (Exception e1) {

		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}
	
	private static void initGeneralConfig(Configuration cfg)
	{
		UpperBlockCheck = cfg.getBoolean(
				"Enable Upper Torch on normal Blocks", CATEGORY_GENERAL,
				true,
				"enable/disable placement of tochblocks on the upper half of full blocks sides. This does not effect the ability to place torches on the side of top slabs."
				);
		
		InteractiveBlockCheckList = Arrays.asList(cfg.getStringList(
				"Active Block Blacklist", CATEGORY_GENERAL,
				list(),
				"add an blocks's registry name to this list to prevent the placement of a torch when activating said block without sneaking.\n"
				));
	}
	
	private static String [] list()
	{
		return new String []
				{
						"minecraft:crafting_table",
						"minecraft:furnace",
						"minecraft:loom",
						"minecraft:barrel",
						"minecraft:smoker",
						"minecraft:blast_furnace",
						"minecraft:cartography_table",
						"minecraft:fletching_table",
						"minecraft:smithing_table",
						"minecraft:jigsaw",
						"minecraft:structure_block",

						"minecraft:iron_door",
						"minecraft:wooden_door",
						"minecraft:dark_oak_door",
						"minecraft:spruce_door",
						"minecraft:birch_door",
						"minecraft:jungle_door",
						"minecraft:acacia_door",

						"minecraft:jukebox",
						"minecraft:note_block",

						"minecraft:shulker_box",
						"minecraft:white_shulker_box",
						"minecraft:orange_shulker_box",
						"minecraft:magenta_shulker_box",
						"minecraft:light_blue_shulker_box",
						"minecraft:yellow_shulker_box",
						"minecraft:lime_shulker_box",
						"minecraft:pink_shulker_box",
						"minecraft:gray_shulker_box",
						"minecraft:light_gray_shulker_box",
						"minecraft:cyan_shulker_box",
						"minecraft:purple_shulker_box",
						"minecraft:blue_shulker_box",
						"minecraft:brown_shulker_box",
						"minecraft:green_shulker_box",
						"minecraft:red_shulker_box",
						"minecraft:black_shulker_box",

						"minecraft:beacon",
						
						"minecraftfuture:stonecutter",
						"minecraftfuture:grindstone",
						"minecraftfuture:smoker",
						"minecraftfuture:barrel",
						"minecraftfuture:blast_furnace",
						"minecraftfuture:composter"
					};
	}

}
