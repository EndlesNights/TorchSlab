package com.endlesnights.torchslabsmod.blocks.buzzierbees;

import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.bagel.buzzierbees.core.util.PropertyUtils;
import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.event.buzziebees.PlaceHandlerCandlePad;
import com.endlesnights.torchslabsmod.event.buzziebees.PlaceHandlerCandleSlab;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

public class BuzzierBeesCompat implements ITorchSlabCompat
{	
	public static Block candle_slab = null;
	public static Block pad_candle = null;
	
	public static Block white_candle_slab = null;
	public static Block orange_candle_slab = null;
	public static Block magenta_candle_slab = null;
	public static Block light_blue_candle_slab = null;
	public static Block yellow_candle_slab = null;
	public static Block lime_candle_slab = null;
	public static Block pink_candle_slab = null;
	public static Block gray_candle_slab = null;
	public static Block light_gray_candle_slab = null;
	public static Block cyan_candle_slab = null;
	public static Block purple_candle_slab = null;
	public static Block blue_candle_slab = null;
	public static Block brown_candle_slab = null;
	public static Block green_candle_slab = null;
	public static Block red_candle_slab = null;
	public static Block black_candle_slab = null;
	
	public static Block amber_candle_slab = null;
	public static Block beige_candle_slab = null;
	public static Block cream_candle_slab = null;
	public static Block dark_green_candle_slab = null;
	public static Block forest_green_candle_slab = null;
	public static Block hot_pink_candle_slab = null;
	public static Block indigo_candle_slab = null;
	public static Block maroon_candle_slab = null;
	public static Block navy_candle_slab = null;
	public static Block olive_candle_slab = null;
	public static Block pale_green_candle_slab = null;
	public static Block pale_pink_candle_slab = null;
	public static Block sky_blue_candle_slab = null;
	public static Block slate_gray_candle_slab = null;
	public static Block violet_candle_slab = null;
	
	public static Block pad_white_candle = null;
	public static Block pad_orange_candle = null;
	public static Block pad_magenta_candle = null;
	public static Block pad_light_blue_candle = null;
	public static Block pad_yellow_candle = null;
	public static Block pad_lime_candle = null;
	public static Block pad_pink_candle = null;
	public static Block pad_gray_candle = null;
	public static Block pad_light_gray_candle = null;
	public static Block pad_cyan_candle = null;
	public static Block pad_purple_candle = null;
	public static Block pad_blue_candle = null;
	public static Block pad_brown_candle = null;
	public static Block pad_green_candle = null;
	public static Block pad_red_candle = null;
	public static Block pad_black_candle = null;
	
	public static Block pad_amber_candle = null;
	public static Block pad_beige_candle = null;
	public static Block pad_cream_candle = null;
	public static Block pad_dark_green_candle = null;
	public static Block pad_forest_green_candle = null;
	public static Block pad_hot_pink_candle = null;
	public static Block pad_indigo_candle = null;
	public static Block pad_maroon_candle = null;
	public static Block pad_navy_candle = null;
	public static Block pad_olive_candle = null;
	public static Block pad_pale_green_candle = null;
	public static Block pad_pale_pink_candle = null;
	public static Block pad_sky_blue_candle = null;
	public static Block pad_slate_gray_candle = null;
	public static Block pad_violet_candle = null;
	
	public static Block allium_scented_candle_slab = null;
	public static Block azure_bluet_scented_candle_slab = null;
	public static Block blue_orchid_scented_candle_slab = null;
	public static Block dandelion_scented_candle_slab = null;
	public static Block cornflower_scented_candle_slab = null;
	public static Block lily_of_the_valley_scented_candle_slab = null;
	public static Block oxeye_daisy_scented_candle_slab = null;
	public static Block poppy_scented_candle_slab = null;
	public static Block white_tulip_scented_candle_slab = null;
	public static Block orange_tulip_scented_candle_slab = null;
	public static Block pink_tulip_scented_candle_slab = null;
	public static Block red_tulip_scented_candle_slab = null;
	public static Block wither_rose_scented_candle_slab = null;
	
	public static Block pad_allium_scented_candle = null;
	public static Block pad_azure_bluet_scented_candle = null;
	public static Block pad_blue_orchid_scented_candle = null;
	public static Block pad_dandelion_scented_candle = null;
	public static Block pad_cornflower_scented_candle = null;
	public static Block pad_lily_of_the_valley_scented_candle = null;
	public static Block pad_oxeye_daisy_scented_candle = null;
	public static Block pad_poppy_scented_candle = null;
	public static Block pad_white_tulip_scented_candle = null;
	public static Block pad_orange_tulip_scented_candle = null;
	public static Block pad_pink_tulip_scented_candle = null;
	public static Block pad_red_tulip_scented_candle = null;
	public static Block pad_wither_rose_scented_candle = null;
	
	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{	
		
		pad_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.CANDLE.get()), "pad_candle");
		candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.CANDLE.get(), pad_candle), "candle_slab");
		
		white_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.WHITE_CANDLE.get(), pad_white_candle), "white_candle_slab");
		orange_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.ORANGE_CANDLE.get(), pad_orange_candle), "orange_candle_slab");
		magenta_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.MAGENTA_CANDLE.get(), pad_magenta_candle), "magenta_candle_slab");
		light_blue_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.LIGHT_BLUE_CANDLE.get(), pad_light_blue_candle), "light_bluecandle_slab");
		yellow_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.YELLOW_CANDLE.get(), pad_yellow_candle), "yellow_candle_slab");
		lime_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.LIME_CANDLE.get(), pad_lime_candle), "lime_candle_slab");
		pink_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.PINK_CANDLE.get(), pad_pink_candle), "pink_candle_slab");
		gray_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.GRAY_CANDLE.get(), pad_gray_candle), "gray_candle_slab");
		light_gray_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.LIGHT_GRAY_CANDLE.get(), pad_light_gray_candle), "light_graycandle_slab");
		cyan_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.CYAN_CANDLE.get(), pad_cyan_candle), "cyan_candle_slab");
		purple_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.PURPLE_CANDLE.get(), pad_purple_candle), "purple_candle_slab");
		blue_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.BLUE_CANDLE.get(), pad_blue_candle), "blue_candle_slab");
		brown_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.BROWN_CANDLE.get(), pad_brown_candle), "brown_candle_slab");
		green_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.GREEN_CANDLE.get(), pad_green_candle), "green_candle_slab");
		red_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.RED_CANDLE.get(), pad_red_candle), "red_candle_slab");
		black_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.BLACK_CANDLE.get(), pad_black_candle), "black_candle_slab");
		
		amber_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.AMBER_CANDLE.get(), pad_amber_candle), "amber_candle_slab");
		beige_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.BEIGE_CANDLE.get(), pad_beige_candle), "beige_candle_slab");
		cream_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.CREAM_CANDLE.get(), pad_cream_candle), "cream_candle_slab");
		dark_green_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.DARK_GREEN_CANDLE.get(), pad_dark_green_candle), "dark_green_candle_slab");
		forest_green_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.FOREST_GREEN_CANDLE.get(), pad_forest_green_candle), "forest_green_candle_slab");
		hot_pink_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.HOT_PINK_CANDLE.get(), pad_hot_pink_candle), "hot_pink_candle_slab");
		indigo_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.INDIGO_CANDLE.get(), pad_indigo_candle), "indigo_candle_slab");
		maroon_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.MAROON_CANDLE.get(), pad_maroon_candle ), "maroon_candle_slab");
		navy_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.NAVY_CANDLE.get(), pad_navy_candle), "navy_candle_slab");
		olive_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.OLIVE_CANDLE.get(), pad_olive_candle), "olive_candle_slab");
		pale_green_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.PALE_GREEN_CANDLE.get(), pad_pale_green_candle), "pale_green_candle_slab");
		pale_pink_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.PALE_PINK_CANDLE.get(), pad_pale_pink_candle), "pale_pink_candle_slab");
		sky_blue_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.SKY_BLUE_CANDLE.get(), pad_sky_blue_candle), "sky_blue_candle_slab");
		slate_gray_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.SLATE_GRAY_CANDLE.get(), pad_slate_gray_candle), "slate_gray_candle_slab");
		violet_candle_slab = registerBlock(new BlockCandleSlab(PropertyUtils.CANDLE, BBBlocks.VIOLET_CANDLE.get(), pad_violet_candle), "violet_candle_slab");
		
		pad_white_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.WHITE_CANDLE.get()), "pad_white_candle");
		pad_orange_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.ORANGE_CANDLE.get()), "pad_orange_candle");
		pad_magenta_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.MAGENTA_CANDLE.get()), "pad_magenta_candle");
		pad_light_blue_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.LIGHT_BLUE_CANDLE.get()), "pad_light_bluecandle");
		pad_yellow_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.YELLOW_CANDLE.get()), "pad_yellow_candle");
		pad_lime_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.LIME_CANDLE.get()), "pad_lime_candle");
		pad_pink_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.PINK_CANDLE.get()), "pad_pink_candle");
		pad_gray_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.GRAY_CANDLE.get()), "pad_gray_candle");
		pad_light_gray_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.LIGHT_GRAY_CANDLE.get()), "pad_light_graycandle");
		pad_cyan_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.CYAN_CANDLE.get()), "pad_cyan_candle");
		pad_purple_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.PURPLE_CANDLE.get()), "pad_purple_candle");
		pad_blue_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.BLUE_CANDLE.get()), "pad_blue_candle");
		pad_brown_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.BROWN_CANDLE.get()), "pad_brown_candle");
		pad_green_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.GREEN_CANDLE.get()), "pad_green_candle");
		pad_red_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.RED_CANDLE.get()), "pad_red_candle");
		pad_black_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.BLACK_CANDLE.get()), "pad_black_candle");
		
		pad_amber_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.AMBER_CANDLE.get()), "pad_amber_candle");
		pad_beige_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.BEIGE_CANDLE.get()), "pad_beige_candle");
		pad_cream_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.CREAM_CANDLE.get()), "pad_cream_candle");
		pad_dark_green_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.DARK_GREEN_CANDLE.get()), "pad_dark_green_candle");
		pad_forest_green_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.FOREST_GREEN_CANDLE.get()), "pad_forest_green_candle");
		pad_hot_pink_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.HOT_PINK_CANDLE.get()), "pad_hot_pink_candle");
		pad_indigo_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.INDIGO_CANDLE.get()), "pad_indigo_candle");
		pad_maroon_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.MAROON_CANDLE.get()), "pad_maroon_candle");
		pad_navy_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.NAVY_CANDLE.get()), "pad_navy_candle");
		pad_olive_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.OLIVE_CANDLE.get()), "pad_olive_candle");
		pad_pale_green_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.PALE_GREEN_CANDLE.get()), "pad_pale_green_candle");
		pad_pale_pink_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.PALE_PINK_CANDLE.get()), "pad_pale_pink_candle");
		pad_sky_blue_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.SKY_BLUE_CANDLE.get()), "pad_sky_blue_candle");
		pad_slate_gray_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.SLATE_GRAY_CANDLE.get()), "pad_slate_gray_candle");
		pad_violet_candle = registerBlock(new BlockPadCandle(PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.VIOLET_CANDLE.get()), "pad_violet_candle");
		
		allium_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.FIRE_RESISTANCE, 70, 0,PropertyUtils.CANDLE, BBBlocks.ALLIUM_SCENTED_CANDLE.get(), pad_allium_scented_candle), "allium_scented_candle_slab");
		azure_bluet_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.BLINDNESS, 70, 0,PropertyUtils.CANDLE, BBBlocks.AZURE_BLUET_SCENTED_CANDLE.get(), pad_azure_bluet_scented_candle), "azure_bluet_scented_candle_slab");
		blue_orchid_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.SATURATION, 70, 0,PropertyUtils.CANDLE, BBBlocks.BLUE_ORCHID_SCENTED_CANDLE.get(), pad_blue_orchid_scented_candle), "blue_orchid_scented_candle_slab");
		dandelion_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.SATURATION, 70, 0,PropertyUtils.CANDLE, BBBlocks.DANDELION_SCENTED_CANDLE.get(), pad_dandelion_scented_candle), "dandelion_scented_candle_slab");
		cornflower_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.JUMP_BOOST, 70, 0,PropertyUtils.CANDLE, BBBlocks.CORNFLOWER_SCENTED_CANDLE.get(), pad_cornflower_scented_candle), "cornflower_scented_candle_slab");
		lily_of_the_valley_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.POISON, 70, 0, PropertyUtils.CANDLE, BBBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE.get(), pad_lily_of_the_valley_scented_candle), "lily_of_the_valley_scented_candle_slab");
		oxeye_daisy_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.REGENERATION, 70, 0, PropertyUtils.CANDLE, BBBlocks.OXEYE_DAISY_SCENTED_CANDLE.get(), pad_oxeye_daisy_scented_candle), "oxeye_daisy_scented_candle_slab");
		poppy_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.NIGHT_VISION, 70, 0, PropertyUtils.CANDLE, BBBlocks.POPPY_SCENTED_CANDLE.get(), pad_poppy_scented_candle), "poppy_scented_candle_slab");
		white_tulip_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.WEAKNESS, 70, 0,PropertyUtils.CANDLE, BBBlocks.WHITE_TULIP_SCENTED_CANDLE.get(), pad_white_tulip_scented_candle), "white_tulip_scented_candle_slab");
		orange_tulip_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.WEAKNESS, 70, 0,PropertyUtils.CANDLE, BBBlocks.ORANGE_TULIP_SCENTED_CANDLE.get(), pad_orange_tulip_scented_candle), "orange_tulip_scented_candle_slab");
		pink_tulip_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.WEAKNESS, 70, 0,PropertyUtils.CANDLE, BBBlocks.PINK_TULIP_SCENTED_CANDLE.get(), pad_pink_tulip_scented_candle), "pink_tulip_scented_candle_slab");
		red_tulip_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.WEAKNESS, 70, 0,PropertyUtils.CANDLE, BBBlocks.RED_TULIP_SCENTED_CANDLE.get(), pad_red_tulip_scented_candle), "red_tulip_scented_candle_slab");
		wither_rose_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.WITHER, 70, 0,PropertyUtils.CANDLE, BBBlocks.WITHER_ROSE_SCENTED_CANDLE.get(), pad_wither_rose_scented_candle), "wither_rose_scented_candle_slab");
		
		pad_allium_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.FIRE_RESISTANCE, 70, 0, PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.ALLIUM_SCENTED_CANDLE.get()), "pad_allium_scented_candle");
		pad_azure_bluet_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.BLINDNESS, 70, 0, PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.AZURE_BLUET_SCENTED_CANDLE.get()), "pad_azure_bluet_scented_candle");
		pad_blue_orchid_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.SATURATION, 70, 0, PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.BLUE_ORCHID_SCENTED_CANDLE.get()), "pad_blue_orchid_scented_candle");
		pad_dandelion_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.SATURATION, 70, 0, PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.DANDELION_SCENTED_CANDLE.get()), "pad_dandelion_scented_candle");
		pad_cornflower_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.JUMP_BOOST, 70, 0, PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.CORNFLOWER_SCENTED_CANDLE.get()), "pad_cornflower_scented_candle");
		pad_lily_of_the_valley_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.POISON, 70, 0, PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE.get()), "pad_lily_of_the_valley_scented_candle");
		pad_oxeye_daisy_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.REGENERATION, 70, 0, PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.OXEYE_DAISY_SCENTED_CANDLE.get()), "pad_oxeye_daisy_scented_candle");
		pad_poppy_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.NIGHT_VISION, 70, 0, PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.POPPY_SCENTED_CANDLE.get()), "pad_poppy_scented_candle");
		pad_white_tulip_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.WEAKNESS, 70, 0,PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.WHITE_TULIP_SCENTED_CANDLE.get()), "pad_white_tulip_scented_candle");
		pad_orange_tulip_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.WEAKNESS, 70, 0,PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.ORANGE_TULIP_SCENTED_CANDLE.get()), "pad_orange_tulip_scented_candle");
		pad_pink_tulip_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.WEAKNESS, 70, 0,PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.PINK_TULIP_SCENTED_CANDLE.get()), "pad_pink_tulip_scented_candle");
		pad_red_tulip_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.WEAKNESS, 70, 0,PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.RED_TULIP_SCENTED_CANDLE.get()), "pad_red_tulip_scented_candle");
		pad_wither_rose_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.WITHER, 70, 0,PropertyUtils.CANDLE.hardnessAndResistance(0.0F), BBBlocks.WITHER_ROSE_SCENTED_CANDLE.get()), "pad_wither_rose_scented_candle");

		if (FMLEnvironment.dist == Dist.CLIENT)
        {
            RenderType transparentRenderType = RenderType.func_228641_d_();
            
            RenderTypeLookup.setRenderLayer(pad_candle, transparentRenderType);
            
            RenderTypeLookup.setRenderLayer(pad_white_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_orange_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_magenta_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_light_blue_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_yellow_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_lime_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_pink_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_gray_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_light_gray_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_cyan_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_purple_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_blue_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_brown_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_green_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_red_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_black_candle, transparentRenderType);
            
            RenderTypeLookup.setRenderLayer(pad_amber_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_beige_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_cream_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_dark_green_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_forest_green_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_hot_pink_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_indigo_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_maroon_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_navy_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_olive_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_pale_green_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_pale_pink_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_sky_blue_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_slate_gray_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_violet_candle, transparentRenderType);
            
            RenderTypeLookup.setRenderLayer(pad_allium_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_azure_bluet_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_blue_orchid_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_dandelion_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_cornflower_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_lily_of_the_valley_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_oxeye_daisy_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_poppy_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_white_tulip_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_orange_tulip_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_pink_tulip_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_red_tulip_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_wither_rose_scented_candle, transparentRenderType);
        }
	}
	
	@Override
	public void registerPlaceEntries()
	{
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.CANDLE.getId(), candle_slab);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.CANDLE.getId(), pad_candle);
		
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.WHITE_CANDLE.getId(), white_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.ORANGE_CANDLE.getId(), orange_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.MAGENTA_CANDLE.getId(), magenta_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.LIGHT_BLUE_CANDLE.getId(), light_blue_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.YELLOW_CANDLE.getId(), yellow_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.LIME_CANDLE.getId(), lime_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.PINK_CANDLE.getId(), pink_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.GRAY_CANDLE.getId(), gray_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.LIGHT_GRAY_CANDLE.getId(), light_gray_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.CYAN_CANDLE.getId(), cyan_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.PURPLE_CANDLE.getId(), purple_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.BLUE_CANDLE.getId(), blue_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.BROWN_CANDLE.getId(), brown_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.GREEN_CANDLE.getId(), green_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.RED_CANDLE.getId(), red_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.BLACK_CANDLE.getId(), black_candle_slab);
		
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.AMBER_CANDLE.getId(), amber_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.BEIGE_CANDLE.getId(), beige_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.CREAM_CANDLE.getId(), cream_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.DARK_GREEN_CANDLE.getId(), dark_green_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.FOREST_GREEN_CANDLE.getId(), forest_green_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.HOT_PINK_CANDLE.getId(), hot_pink_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.INDIGO_CANDLE.getId(), indigo_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.MAROON_CANDLE.getId(), maroon_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.NAVY_CANDLE.getId(), navy_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.OLIVE_CANDLE.getId(), olive_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.PALE_GREEN_CANDLE.getId(), pale_green_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.PALE_PINK_CANDLE.getId(), pale_pink_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.SKY_BLUE_CANDLE.getId(), sky_blue_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.SLATE_GRAY_CANDLE.getId(), slate_gray_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.VIOLET_CANDLE.getId(), violet_candle_slab);
		
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.WHITE_CANDLE.getId(), pad_white_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.ORANGE_CANDLE.getId(), pad_orange_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.MAGENTA_CANDLE.getId(), pad_magenta_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.LIGHT_BLUE_CANDLE.getId(), pad_light_blue_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.YELLOW_CANDLE.getId(), pad_yellow_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.LIME_CANDLE.getId(), pad_lime_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.PINK_CANDLE.getId(), pad_pink_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.GRAY_CANDLE.getId(), pad_gray_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.LIGHT_GRAY_CANDLE.getId(), pad_light_gray_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.CYAN_CANDLE.getId(), pad_cyan_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.PURPLE_CANDLE.getId(), pad_purple_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.BLUE_CANDLE.getId(), pad_blue_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.BROWN_CANDLE.getId(), pad_brown_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.GREEN_CANDLE.getId(), pad_green_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.RED_CANDLE.getId(), pad_red_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.BLACK_CANDLE.getId(), pad_black_candle);
		
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.AMBER_CANDLE.getId(), pad_amber_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.BEIGE_CANDLE.getId(), pad_beige_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.CREAM_CANDLE.getId(), pad_cream_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.DARK_GREEN_CANDLE.getId(), pad_dark_green_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.FOREST_GREEN_CANDLE.getId(), pad_forest_green_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.HOT_PINK_CANDLE.getId(), pad_hot_pink_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.INDIGO_CANDLE.getId(), pad_indigo_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.MAROON_CANDLE.getId(), pad_maroon_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.NAVY_CANDLE.getId(), pad_navy_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.OLIVE_CANDLE.getId(), pad_olive_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.PALE_GREEN_CANDLE.getId(), pad_pale_green_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.PALE_PINK_CANDLE.getId(), pad_pale_pink_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.SKY_BLUE_CANDLE.getId(), pad_sky_blue_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.SLATE_GRAY_CANDLE.getId(), pad_slate_gray_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.VIOLET_CANDLE.getId(), pad_violet_candle);
		
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.ALLIUM_SCENTED_CANDLE.getId(), allium_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.AZURE_BLUET_SCENTED_CANDLE.getId(), azure_bluet_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.BLUE_ORCHID_SCENTED_CANDLE.getId(), blue_orchid_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.DANDELION_SCENTED_CANDLE.getId(), dandelion_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.CORNFLOWER_SCENTED_CANDLE.getId(), cornflower_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE.getId(), lily_of_the_valley_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.OXEYE_DAISY_SCENTED_CANDLE.getId(), oxeye_daisy_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.POPPY_SCENTED_CANDLE.getId(), poppy_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.WHITE_TULIP_SCENTED_CANDLE.getId(), white_tulip_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.ORANGE_TULIP_SCENTED_CANDLE.getId(), orange_tulip_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.PINK_TULIP_SCENTED_CANDLE.getId(), pink_tulip_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.RED_TULIP_SCENTED_CANDLE.getId(), red_tulip_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.WITHER_ROSE_SCENTED_CANDLE.getId(), wither_rose_scented_candle_slab);
		
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.ALLIUM_SCENTED_CANDLE.getId(), pad_allium_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.AZURE_BLUET_SCENTED_CANDLE.getId(), pad_azure_bluet_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.BLUE_ORCHID_SCENTED_CANDLE.getId(), pad_blue_orchid_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.DANDELION_SCENTED_CANDLE.getId(), pad_dandelion_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.CORNFLOWER_SCENTED_CANDLE.getId(), pad_cornflower_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE.getId(), pad_lily_of_the_valley_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.OXEYE_DAISY_SCENTED_CANDLE.getId(), pad_oxeye_daisy_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.POPPY_SCENTED_CANDLE.getId(), pad_poppy_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.WHITE_TULIP_SCENTED_CANDLE.getId(), pad_white_tulip_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.ORANGE_TULIP_SCENTED_CANDLE.getId(), pad_orange_tulip_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.PINK_TULIP_SCENTED_CANDLE.getId(), pad_pink_tulip_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.RED_TULIP_SCENTED_CANDLE.getId(), pad_red_tulip_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.WITHER_ROSE_SCENTED_CANDLE.getId(), pad_wither_rose_scented_candle);

	}
	
    public static Block registerBlock(Block block, String name)
    {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }
}
