package com.endlesnights.torchslabsmod.blocks.buzzierbees;

import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.event.buzziebees.PlaceHandlerCandlePad;
import com.endlesnights.torchslabsmod.event.buzziebees.PlaceHandlerCandleSlab;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
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
	
	public static Block cartwheel_scented_candle_slab = null;
	public static Block bluebell_scented_candle_slab = null;
	public static Block daybloom_scented_candle_slab = null;
	public static Block violet_scented_candle_slab = null;
	public static Block jolyce_scented_candle_slab = null;
	public static Block columbine_scented_candle_slab = null;
	public static Block white_clover_scented_candle_slab = null;
	public static Block pink_clover_scented_candle_slab = null;
	
	public static Block pad_cartwheel_scented_candle = null;
	public static Block pad_bluebell_scented_candle = null;
	public static Block pad_daybloom_scented_candle = null;
	public static Block pad_violet_scented_candle = null;
	public static Block pad_jolyce_scented_candle = null;
	public static Block pad_columbine_scented_candle = null;
	public static Block pad_white_clover_scented_candle = null;
	public static Block pad_pink_clover_scented_candle = null;
	
	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{	
		pad_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_candle");
		candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "candle_slab");
		
		white_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "white_candle_slab");
		orange_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "orange_candle_slab");
		magenta_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "magenta_candle_slab");
		light_blue_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "light_blue_candle_slab");
		yellow_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "yellow_candle_slab");
		lime_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "lime_candle_slab");
		pink_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "pink_candle_slab");
		gray_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "gray_candle_slab");
		light_gray_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "light_gray_candle_slab");
		cyan_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "cyan_candle_slab");
		purple_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "purple_candle_slab");
		blue_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "blue_candle_slab");
		brown_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "brown_candle_slab");
		green_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "green_candle_slab");
		red_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "red_candle_slab");
		black_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "black_candle_slab");
		
		amber_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "amber_candle_slab");
		beige_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "beige_candle_slab");
		cream_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "cream_candle_slab");
		dark_green_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "dark_green_candle_slab");
		forest_green_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "forest_green_candle_slab");
		hot_pink_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "hot_pink_candle_slab");
		indigo_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "indigo_candle_slab");
		maroon_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "maroon_candle_slab");
		navy_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "navy_candle_slab");
		olive_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "olive_candle_slab");
		pale_green_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "pale_green_candle_slab");
		pale_pink_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "pale_pink_candle_slab");
		sky_blue_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "sky_blue_candle_slab");
		slate_gray_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "slate_gray_candle_slab");
		violet_candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "violet_candle_slab");
		
		pad_white_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_white_candle");
		pad_orange_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_orange_candle");
		pad_magenta_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_magenta_candle");
		pad_light_blue_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_light_blue_candle");
		pad_yellow_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_yellow_candle");
		pad_lime_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_lime_candle");
		pad_pink_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_pink_candle");
		pad_gray_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_gray_candle");
		pad_light_gray_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_light_gray_candle");
		pad_cyan_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_cyan_candle");
		pad_purple_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_purple_candle");
		pad_blue_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_blue_candle");
		pad_brown_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_brown_candle");
		pad_green_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_green_candle");
		pad_red_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_red_candle");
		pad_black_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_black_candle");
		
		pad_amber_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_amber_candle");
		pad_beige_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_beige_candle");
		pad_cream_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_cream_candle");
		pad_dark_green_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_dark_green_candle");
		pad_forest_green_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_forest_green_candle");
		pad_hot_pink_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_hot_pink_candle");
		pad_indigo_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_indigo_candle");
		pad_maroon_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_maroon_candle");
		pad_navy_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_navy_candle");
		pad_olive_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_olive_candle");
		pad_pale_green_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_pale_green_candle");
		pad_pale_pink_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_pale_pink_candle");
		pad_sky_blue_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_sky_blue_candle");
		pad_slate_gray_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_slate_gray_candle");
		pad_violet_candle = registerBlock(new BlockPadCandle(Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_violet_candle");
		
		allium_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.FIRE_RESISTANCE, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "allium_scented_candle_slab");
		azure_bluet_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.BLINDNESS, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "azure_bluet_scented_candle_slab");
		blue_orchid_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.SATURATION, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "blue_orchid_scented_candle_slab");
		dandelion_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.SATURATION, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "dandelion_scented_candle_slab");
		cornflower_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.JUMP_BOOST, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "cornflower_scented_candle_slab");
		lily_of_the_valley_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.POISON, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "lily_of_the_valley_scented_candle_slab");
		oxeye_daisy_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.REGENERATION, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "oxeye_daisy_scented_candle_slab");
		poppy_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.NIGHT_VISION, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "poppy_scented_candle_slab");
		white_tulip_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.WEAKNESS, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "white_tulip_scented_candle_slab");
		orange_tulip_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.WEAKNESS, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "orange_tulip_scented_candle_slab");
		pink_tulip_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.WEAKNESS, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "pink_tulip_scented_candle_slab");
		red_tulip_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.WEAKNESS, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "red_tulip_scented_candle_slab");
		wither_rose_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.WITHER, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "wither_rose_scented_candle_slab");
		
		pad_allium_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.FIRE_RESISTANCE, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_allium_scented_candle");
		pad_azure_bluet_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.BLINDNESS, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_azure_bluet_scented_candle");
		pad_blue_orchid_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.SATURATION, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_blue_orchid_scented_candle");
		pad_dandelion_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.SATURATION, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_dandelion_scented_candle");
		pad_cornflower_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.JUMP_BOOST, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_cornflower_scented_candle");
		pad_lily_of_the_valley_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.POISON, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_lily_of_the_valley_scented_candle");
		pad_oxeye_daisy_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.REGENERATION, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_oxeye_daisy_scented_candle");
		pad_poppy_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.NIGHT_VISION, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_poppy_scented_candle");
		pad_white_tulip_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.WEAKNESS, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_white_tulip_scented_candle");
		pad_orange_tulip_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.WEAKNESS, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_orange_tulip_scented_candle");
		pad_pink_tulip_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.WEAKNESS, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_pink_tulip_scented_candle");
		pad_red_tulip_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.WEAKNESS, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_red_tulip_scented_candle");
		pad_wither_rose_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.WITHER, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_wither_rose_scented_candle");

		cartwheel_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.SPEED, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "cartwheel_scented_candle_slab");
		bluebell_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.WATER_BREATHING, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "bluebell_scented_candle_slab");
		daybloom_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.GLOWING, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "daybloom_scented_candle_slab");
		violet_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.INVISIBILITY, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "violet_scented_candle_slab");
		jolyce_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.STRENGTH, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "jolyce_scented_candle_slab");
		columbine_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.MINING_FATIGUE, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "columbine_scented_candle_slab");
		white_clover_scented_candle_slab = registerBlock(new BlockScentedCandleSlab(Effects.UNLUCK, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "white_clover_scented_candle_slab");
		pink_clover_scented_candle_slab= registerBlock(new BlockScentedCandleSlab(Effects.UNLUCK, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.1F).notSolid().sound(SoundType.WOOD)), "pink_clover_scented_candle_slab");
		
		pad_cartwheel_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.SPEED, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_cartwheel_scented_candle");
		pad_bluebell_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.WATER_BREATHING, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_bluebell_scented_candle");
		pad_daybloom_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.GLOWING, 70, 0, Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_daybloom_scented_candle");
		pad_violet_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.INVISIBILITY, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_violet_scented_candle");
		pad_jolyce_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.STRENGTH, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_jolyce_scented_candle");
		pad_columbine_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.MINING_FATIGUE, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_columbine_scented_candle");
		pad_white_clover_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.UNLUCK, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_white_clover_scented_candle");
		pad_pink_clover_scented_candle = registerBlock(new BlockPadScentedCandle(Effects.UNLUCK, 70, 0,Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).notSolid().sound(SoundType.WOOD)), "pad_pink_clover_scented_candle");		
		if (FMLEnvironment.dist == Dist.CLIENT)
        {
            RenderType transparentRenderType = RenderType.getCutoutMipped();
            
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
            
            RenderTypeLookup.setRenderLayer(pad_cartwheel_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_bluebell_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_daybloom_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_violet_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_jolyce_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_columbine_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_white_clover_scented_candle, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_pink_clover_scented_candle, transparentRenderType);
            
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
		
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.CARTWHEEL_SCENTED_CANDLE.getId(), cartwheel_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.BLUEBELL_SCENTED_CANDLE.getId(), bluebell_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.DAYBLOOM_SCENTED_CANDLE.getId(), daybloom_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.VIOLET_SCENTED_CANDLE.getId(), violet_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.JOLYCE_SCENTED_CANDLE.getId(), jolyce_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.COLUMBINE_SCENTED_CANDLE.getId(), columbine_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.WHITE_CLOVER_SCENTED_CANDLE.getId(), white_clover_scented_candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(BBBlocks.PINK_CLOVER_SCENTED_CANDLE.getId(), pink_clover_scented_candle_slab);
		
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.CARTWHEEL_SCENTED_CANDLE.getId(), pad_cartwheel_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.BLUEBELL_SCENTED_CANDLE.getId(), pad_bluebell_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.DAYBLOOM_SCENTED_CANDLE.getId(), pad_daybloom_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.VIOLET_SCENTED_CANDLE.getId(), pad_violet_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.JOLYCE_SCENTED_CANDLE.getId(), pad_jolyce_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.COLUMBINE_SCENTED_CANDLE.getId(), pad_columbine_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.WHITE_CLOVER_SCENTED_CANDLE.getId(), pad_white_clover_scented_candle);
		PlaceHandlerCandlePad.registerPlaceEntry(BBBlocks.PINK_CLOVER_SCENTED_CANDLE.getId(), pad_pink_clover_scented_candle);

	}
	
    public static Block registerBlock(Block block, String name)
    {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }
}
