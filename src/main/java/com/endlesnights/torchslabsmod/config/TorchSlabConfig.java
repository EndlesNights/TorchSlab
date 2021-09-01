package com.endlesnights.torchslabsmod.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

public class TorchSlabConfig 
{
	public static ForgeConfigSpec.BooleanValue wallLanternCheck;
	public static ForgeConfigSpec.BooleanValue lowerLanternCheck;
	public static ForgeConfigSpec.BooleanValue upperBlockCheck;
	public static ForgeConfigSpec.ConfigValue<List<String>> interactiveCheckList;
	
	public static void init(ForgeConfigSpec.Builder server)
	{
		wallLanternCheck = server
				.comment("Allow the user to place lanterns on the sides of blocks, and on the sides of slabs [True / False]")
				.define("torchslabmodconfig.LanternLowerBlockCheck", true)
				;
		
		lowerLanternCheck = server
				.comment("Allow the user to place lanterns on the lower sections of blocks. Does not effect the placement of lower slabs sides, or the sides/front of stairs. [True / False]")
				.define("torchslabmodconfig.LanternLowerBlockCheck", true)
				;		
		
		upperBlockCheck = server
				.comment("Allow the placemnet of torchs on upper half of wall blocks. Does not effect the placement on upper slabs sides, or the sides/front of stairs. [True / False]")
				.define("torchslabmodconfig.TorchUpperBlockCheck", false)
				;
		
		interactiveCheckList = server
				.comment("List of interactive blocks that require the player be shift clicked when placing a block. To add additional blocks, just use the formot of \"ModID:Block_RegistryName\"")
				.define("torchslabmodconfig.interactiveCheckList", list())
				;
	}
	
	private static List<String> list()
	{
		 ArrayList<String> list = new ArrayList<String>();  
            
		 list.add("minecraft:crafting_table");
		 list.add("minecraft:furnace");
         list.add("minecraft:loom");
         list.add("minecraft:barrel");
         list.add("minecraft:smoker");
         list.add("minecraft:blast_furnace");
         list.add("minecraft:cartography_table");
         list.add("minecraft:fletching_table");
         list.add("minecraft:smithing_table");
         list.add("minecraft:jigsaw");
         list.add("minecraft:structure_block");
         
         list.add("minecraft:iron_door");
         list.add("minecraft:oak_door");
         list.add("minecraft:dark_oak_door");
         list.add("minecraft:spruce_door");
         list.add("minecraft:birch_door");
         list.add("minecraft:jungle_door");
         list.add("minecraft:acacia_door");
         
         list.add("minecraft:jukebox");
         list.add("minecraft:note_block");
         
         list.add("minecraft:shulker_box");
         list.add("minecraft:white_shulker_box");
         list.add("minecraft:orange_shulker_box");
         list.add("minecraft:magenta_shulker_box");
         list.add("minecraft:light_blue_shulker_box");
         list.add("minecraft:yellow_shulker_box");
         list.add("minecraft:lime_shulker_box");
         list.add("minecraft:pink_shulker_box");
         list.add("minecraft:gray_shulker_box");
         list.add("minecraft:light_gray_shulker_box");
         list.add("minecraft:cyan_shulker_box");
         list.add("minecraft:purple_shulker_box");
         list.add("minecraft:blue_shulker_box");
         list.add("minecraft:brown_shulker_box");
         list.add("minecraft:green_shulker_box");
         list.add("minecraft:red_shulker_box");
         list.add("minecraft:black_shulker_box");
         
         list.add("minecraft:beacon");		                
            
         return list;
	}
}
