package com.endlesnights.torchslabsmod.blocks.quark;

import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.event.PlaceHandlerPadLights;
import com.endlesnights.torchslabsmod.event.PlaceHandlerTorchSlab;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

public class QuarkCompat  implements ITorchSlabCompat
{
	public static Block black_candle = null;
	public static Block blue_candle = null;
	public static Block brown_candle = null;
	public static Block cyan_candle = null;
	public static Block gray_candle = null;
	public static Block green_candle = null;
	public static Block light_blue_candle = null;
	public static Block light_gray_candle = null;
	public static Block lime_candle = null;
	public static Block magenta_candle = null;
	public static Block orange_candle = null;
	public static Block pink_candle = null;
	public static Block purple_candle = null;
	public static Block red_candle = null;
	public static Block white_candle = null;
	public static Block yellow_candle = null;
	
	public static Block black_candle_slab = null;
	public static Block blue_candle_slab = null;
	public static Block brown_candle_slab = null;
	public static Block cyan_candle_slab = null;
	public static Block gray_candle_slab = null;
	public static Block green_candle_slab = null;
	public static Block light_blue_candle_slab = null;
	public static Block light_gray_candle_slab = null;
	public static Block lime_candle_slab = null;
	public static Block magenta_candle_slab = null;
	public static Block orange_candle_slab = null;
	public static Block pink_candle_slab = null;
	public static Block purple_candle_slab = null;
	public static Block red_candle_slab = null;
	public static Block white_candle_slab = null;
	public static Block yellow_candle_slab = null;
	
	public static Block black_candle_pad = null;
	public static Block blue_candle_pad = null;
	public static Block brown_candle_pad = null;
	public static Block cyan_candle_pad = null;
	public static Block gray_candle_pad = null;
	public static Block green_candle_pad = null;
	public static Block light_blue_candle_pad = null;
	public static Block light_gray_candle_pad = null;
	public static Block lime_candle_pad = null;
	public static Block magenta_candle_pad = null;
	public static Block orange_candle_pad = null;
	public static Block pink_candle_pad = null;
	public static Block purple_candle_pad = null;
	public static Block red_candle_pad = null;
	public static Block white_candle_pad = null;
	public static Block yellow_candle_pad = null;
	
	@Override
	public void registerBlocks(Register<Block> event)
	{		
		
		try
		{
			event.getRegistry().register(black_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:black_candle")),DyeColor.BLACK).setRegistryName(TorchSlabsMod.MODID, "quark_black_candle_slab"));
			event.getRegistry().register(blue_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:blue_candle")),DyeColor.BLUE).setRegistryName(TorchSlabsMod.MODID, "quark_blue_candle_slab"));
			event.getRegistry().register(brown_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:brown_candle")),DyeColor.BROWN).setRegistryName(TorchSlabsMod.MODID, "quark_brown_candle_slab"));
			event.getRegistry().register(cyan_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:cyan_candle")),DyeColor.CYAN).setRegistryName(TorchSlabsMod.MODID, "quark_cyan_candle_slab"));
			event.getRegistry().register(gray_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:gray_candle")),DyeColor.GRAY).setRegistryName(TorchSlabsMod.MODID, "quark_gray_candle_slab"));
			event.getRegistry().register(green_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:green_candle")),DyeColor.GREEN).setRegistryName(TorchSlabsMod.MODID, "quark_green_candle_slab"));
			event.getRegistry().register(light_blue_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:light_blue_candle")),DyeColor.LIGHT_BLUE).setRegistryName(TorchSlabsMod.MODID, "quark_light_blue_candle_slab"));
			event.getRegistry().register(light_gray_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:light_gray_candle")),DyeColor.LIGHT_GRAY).setRegistryName(TorchSlabsMod.MODID, "quark_light_gray_candle_slab"));
			event.getRegistry().register(lime_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:lime_candle")),DyeColor.LIME).setRegistryName(TorchSlabsMod.MODID, "quark_lime_candle_slab"));
			event.getRegistry().register(magenta_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:magenta_candle")),DyeColor.MAGENTA).setRegistryName(TorchSlabsMod.MODID, "quark_magenta_candle_slab"));
			event.getRegistry().register(orange_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:orange_candle")),DyeColor.ORANGE).setRegistryName(TorchSlabsMod.MODID, "quark_orange_candle_slab"));
			event.getRegistry().register(pink_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:pink_candle")),DyeColor.PINK).setRegistryName(TorchSlabsMod.MODID, "quark_pink_candle_slab"));
			event.getRegistry().register(purple_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:purple_candle")),DyeColor.PURPLE).setRegistryName(TorchSlabsMod.MODID, "quark_purple_candle_slab"));
			event.getRegistry().register(red_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:red_candle")),DyeColor.RED).setRegistryName(TorchSlabsMod.MODID, "quark_red_candle_slab"));
			event.getRegistry().register(white_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:white_candle")),DyeColor.WHITE).setRegistryName(TorchSlabsMod.MODID, "quark_white_candle_slab"));
			event.getRegistry().register(yellow_candle_slab = new BlockCandleSlab(
					Properties.from(quarkBlock("quark:yellow_candle")),DyeColor.YELLOW).setRegistryName(TorchSlabsMod.MODID, "quark_yellow_candle_slab"));
			
			event.getRegistry().register(black_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:black_candle")),DyeColor.BLACK).setRegistryName(TorchSlabsMod.MODID, "quark_black_candle_pad"));
			event.getRegistry().register(blue_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:blue_candle")),DyeColor.BLUE).setRegistryName(TorchSlabsMod.MODID, "quark_blue_candle_pad"));
			event.getRegistry().register(brown_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:brown_candle")),DyeColor.BROWN).setRegistryName(TorchSlabsMod.MODID, "quark_brown_candle_pad"));
			event.getRegistry().register(cyan_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:cyan_candle")),DyeColor.CYAN).setRegistryName(TorchSlabsMod.MODID, "quark_cyan_candle_pad"));
			event.getRegistry().register(gray_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:gray_candle")),DyeColor.GRAY).setRegistryName(TorchSlabsMod.MODID, "quark_gray_candle_pad"));
			event.getRegistry().register(green_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:green_candle")),DyeColor.GREEN).setRegistryName(TorchSlabsMod.MODID, "quark_green_candle_pad"));
			event.getRegistry().register(light_blue_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:light_blue_candle")),DyeColor.LIGHT_BLUE).setRegistryName(TorchSlabsMod.MODID, "quark_light_blue_candle_pad"));
			event.getRegistry().register(light_gray_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:light_gray_candle")),DyeColor.LIGHT_GRAY).setRegistryName(TorchSlabsMod.MODID, "quark_light_gray_candle_pad"));
			event.getRegistry().register(lime_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:lime_candle")),DyeColor.LIME).setRegistryName(TorchSlabsMod.MODID, "quark_lime_candle_pad"));
			event.getRegistry().register(magenta_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:magenta_candle")),DyeColor.MAGENTA).setRegistryName(TorchSlabsMod.MODID, "quark_magenta_candle_pad"));
			event.getRegistry().register(orange_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:orange_candle")),DyeColor.ORANGE).setRegistryName(TorchSlabsMod.MODID, "quark_orange_candle_pad"));
			event.getRegistry().register(pink_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:pink_candle")),DyeColor.PINK).setRegistryName(TorchSlabsMod.MODID, "quark_pink_candle_pad"));
			event.getRegistry().register(purple_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:purple_candle")),DyeColor.PURPLE).setRegistryName(TorchSlabsMod.MODID, "quark_purple_candle_pad"));
			event.getRegistry().register(red_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:red_candle")),DyeColor.RED).setRegistryName(TorchSlabsMod.MODID, "quark_red_candle_pad"));
			event.getRegistry().register(white_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:white_candle")),DyeColor.WHITE).setRegistryName(TorchSlabsMod.MODID, "quark_white_candle_pad"));
			event.getRegistry().register(yellow_candle_pad = new BlockCandlePad(
					Properties.from(quarkBlock("quark:yellow_candle")),DyeColor.YELLOW).setRegistryName(TorchSlabsMod.MODID, "quark_yellow_candle_pad"));
			
			
			if (FMLEnvironment.dist == Dist.CLIENT)
	        {
	            RenderType transparentRenderType = RenderType.getCutoutMipped();
	            
	            RenderTypeLookup.setRenderLayer(black_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(blue_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(brown_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(cyan_candle_pad, transparentRenderType);
	            
	            RenderTypeLookup.setRenderLayer(gray_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(green_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(light_blue_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(light_gray_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(lime_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(magenta_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(orange_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(pink_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(purple_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(red_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(white_candle_pad, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(yellow_candle_pad, transparentRenderType);
	        }
		
		}
		catch(Error e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & Quark. Skipping Block:" + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & Quark. Skipping Block:" + e.getMessage());
		}
		
	}

	@Override
	public void registerPlaceEntries()
	{		
		
		try
		{
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:black_candle").getRegistryName(), black_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:blue_candle").getRegistryName(), blue_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:brown_candle").getRegistryName(), brown_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:cyan_candle").getRegistryName(), cyan_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:gray_candle").getRegistryName(), gray_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:green_candle").getRegistryName(), green_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:light_blue_candle").getRegistryName(), light_blue_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:light_gray_candle").getRegistryName(), light_gray_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:lime_candle").getRegistryName(), lime_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:magenta_candle").getRegistryName(), magenta_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:orange_candle").getRegistryName(), orange_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:pink_candle").getRegistryName(), pink_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:purple_candle").getRegistryName(), purple_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:red_candle").getRegistryName(), red_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:white_candle").getRegistryName(), white_candle_slab);
			PlaceHandlerTorchSlab.registerPlaceEntry(quarkBlock("quark:yellow_candle").getRegistryName(), yellow_candle_slab);
			
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:black_candle").getRegistryName(), black_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:blue_candle").getRegistryName(), blue_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:brown_candle").getRegistryName(), brown_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:cyan_candle").getRegistryName(), cyan_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:gray_candle").getRegistryName(), gray_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:green_candle").getRegistryName(), green_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:light_blue_candle").getRegistryName(), light_blue_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:light_gray_candle").getRegistryName(), light_gray_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:lime_candle").getRegistryName(), lime_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:magenta_candle").getRegistryName(), magenta_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:orange_candle").getRegistryName(), orange_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:pink_candle").getRegistryName(), pink_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:purple_candle").getRegistryName(), purple_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:red_candle").getRegistryName(), red_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:white_candle").getRegistryName(), white_candle_pad);
			PlaceHandlerPadLights.registerPlaceEntry(quarkBlock("quark:yellow_candle").getRegistryName(), yellow_candle_pad);
		}
		catch(Error e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & Quark. Skipping Block:" + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & Quark. Skipping Block:" + e.getMessage());
		}
	}

	
	Block quarkBlock(String regIndex)
	{
		return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(regIndex));
	}
}
