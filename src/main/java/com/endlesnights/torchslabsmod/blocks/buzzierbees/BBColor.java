package com.endlesnights.torchslabsmod.blocks.buzzierbees;

import com.endlesnights.torchslabsmod.TorchSlabsMod;

import net.minecraft.client.renderer.color.BlockColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid=TorchSlabsMod.MODID, bus=Bus.MOD, value=Dist.CLIENT)
public class BBColor
{

	@SubscribeEvent
	public static void registerBlockColorHandler(final ColorHandlerEvent.Block event)
	{
		final BlockColors blockColors = event.getBlockColors();
		
		blockColors.register((p_228055_0_, p_228055_1_, p_228055_2_, p_228055_3_) -> {
	         return p_228055_1_ != null && p_228055_2_ != null ? 2129968 : 7455580;
	      },
				BuzzierBeesCompat.pad_candle,
				
				BuzzierBeesCompat.pad_white_candle,
				BuzzierBeesCompat.pad_orange_candle,
				BuzzierBeesCompat.pad_magenta_candle,
				BuzzierBeesCompat.pad_light_blue_candle,
				BuzzierBeesCompat.pad_yellow_candle,
				BuzzierBeesCompat.pad_lime_candle,
				BuzzierBeesCompat.pad_pink_candle,
				BuzzierBeesCompat.pad_gray_candle,
				BuzzierBeesCompat.pad_light_gray_candle,
				BuzzierBeesCompat.pad_cyan_candle,
				BuzzierBeesCompat.pad_purple_candle,
				BuzzierBeesCompat.pad_blue_candle,
				BuzzierBeesCompat.pad_brown_candle,
				BuzzierBeesCompat.pad_red_candle,
				BuzzierBeesCompat.pad_black_candle,
				
				BuzzierBeesCompat.pad_amber_candle,
				BuzzierBeesCompat.pad_beige_candle,
				BuzzierBeesCompat.pad_cream_candle,
				BuzzierBeesCompat.pad_dark_green_candle,
				BuzzierBeesCompat.pad_forest_green_candle,
				BuzzierBeesCompat.pad_hot_pink_candle,
				BuzzierBeesCompat.pad_indigo_candle,
				BuzzierBeesCompat.pad_maroon_candle,
				BuzzierBeesCompat.pad_navy_candle,
				BuzzierBeesCompat.pad_olive_candle,
				BuzzierBeesCompat.pad_pale_green_candle,
				BuzzierBeesCompat.pad_pale_pink_candle,
				BuzzierBeesCompat.pad_sky_blue_candle,
				BuzzierBeesCompat.pad_slate_gray_candle,
				BuzzierBeesCompat.pad_violet_candle,
				
				BuzzierBeesCompat.pad_allium_scented_candle,
				BuzzierBeesCompat.pad_azure_bluet_scented_candle,
				BuzzierBeesCompat.pad_blue_orchid_scented_candle,
				BuzzierBeesCompat.pad_dandelion_scented_candle,
				BuzzierBeesCompat.pad_cornflower_scented_candle,
				BuzzierBeesCompat.pad_lily_of_the_valley_scented_candle,
				BuzzierBeesCompat.pad_oxeye_daisy_scented_candle,
				BuzzierBeesCompat.pad_poppy_scented_candle,
				BuzzierBeesCompat.pad_white_tulip_scented_candle,
				BuzzierBeesCompat.pad_orange_tulip_scented_candle,
				BuzzierBeesCompat.pad_pink_tulip_scented_candle,
				BuzzierBeesCompat.pad_red_tulip_scented_candle,
				BuzzierBeesCompat.pad_wither_rose_scented_candle
				);
	}
	
}
