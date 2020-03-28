package com.endlesnights.torchslabsmod.blocks.buzzierbees.util;

import com.bagel.buzzierbees.core.registry.BBBlocks;
import com.endlesnights.torchslabsmod.blocks.buzzierbees.BuzzierBeesCompat;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;


public class GetCandle 
{
	
	public static Block getPadParent (BlockState state)
	{
		if(state.getBlock() == BuzzierBeesCompat.pad_candle)
			return BBBlocks.CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_white_candle)
			return BBBlocks.WHITE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_orange_candle)
			return BBBlocks.ORANGE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_magenta_candle)
			return BBBlocks.MAGENTA_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_light_blue_candle)
			return BBBlocks.LIGHT_BLUE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_yellow_candle)
			return BBBlocks.YELLOW_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_lime_candle)
			return BBBlocks.LIME_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_pink_candle)
			return BBBlocks.PINK_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_gray_candle)
			return BBBlocks.GRAY_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_light_gray_candle)
			return BBBlocks.LIGHT_GRAY_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_cyan_candle)
			return BBBlocks.CYAN_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_purple_candle)
			return BBBlocks.PURPLE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_blue_candle)
			return BBBlocks.BLUE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_brown_candle)
			return BBBlocks.BROWN_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_green_candle)
			return BBBlocks.GREEN_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_red_candle)
			return BBBlocks.RED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_black_candle)
			return BBBlocks.BLACK_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_amber_candle)
			return BBBlocks.AMBER_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_beige_candle)
			return BBBlocks.BEIGE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_cream_candle)
			return BBBlocks.CREAM_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_dark_green_candle)
			return BBBlocks.DARK_GREEN_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_forest_green_candle)
			return BBBlocks.FOREST_GREEN_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_hot_pink_candle)
			return BBBlocks.HOT_PINK_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_indigo_candle)
			return BBBlocks.INDIGO_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_maroon_candle)
			return BBBlocks.MAROON_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_navy_candle)
			return BBBlocks.NAVY_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_olive_candle)
			return BBBlocks.OLIVE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_pale_green_candle)
			return BBBlocks.PALE_GREEN_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_pale_pink_candle)
			return BBBlocks.PALE_PINK_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_sky_blue_candle)
			return BBBlocks.SKY_BLUE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_slate_gray_candle)
			return BBBlocks.SLATE_GRAY_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_violet_candle)
			return BBBlocks.VIOLET_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_allium_scented_candle)
			return BBBlocks.ALLIUM_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_azure_bluet_scented_candle)
			return BBBlocks.AZURE_BLUET_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_blue_orchid_scented_candle)
			return BBBlocks.BLUE_ORCHID_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_dandelion_scented_candle)
			return BBBlocks.DANDELION_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_cornflower_scented_candle)
			return BBBlocks.CORNFLOWER_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_lily_of_the_valley_scented_candle)
			return BBBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_oxeye_daisy_scented_candle)
			return BBBlocks.OXEYE_DAISY_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_poppy_scented_candle)
			return BBBlocks.POPPY_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_white_tulip_scented_candle)
			return BBBlocks.WHITE_TULIP_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_orange_tulip_scented_candle)
			return BBBlocks.ORANGE_TULIP_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_pink_tulip_scented_candle)
			return BBBlocks.PINK_TULIP_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_red_tulip_scented_candle)
			return BBBlocks.RED_TULIP_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_wither_rose_scented_candle)
			return BBBlocks.WITHER_ROSE_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_cartwheel_scented_candle)
			return BBBlocks.CARTWHEEL_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_bluebell_scented_candle)
			return BBBlocks.BLUEBELL_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_daybloom_scented_candle)
			return BBBlocks.DAYBLOOM_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_violet_scented_candle)
			return BBBlocks.VIOLET_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_jolyce_scented_candle)
			return BBBlocks.JOLYCE_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_columbine_scented_candle)
			return BBBlocks.COLUMBINE_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_white_clover_scented_candle)
			return BBBlocks.WHITE_CLOVER_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pad_pink_clover_scented_candle)
			return BBBlocks.PINK_CLOVER_SCENTED_CANDLE.get();



		return state.getBlock();
	}
	
	public static Block getParentSlab (BlockState state)
	{
		if(state.getBlock() == BuzzierBeesCompat.candle_slab)
			return BBBlocks.CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.white_candle_slab)
			return BBBlocks.WHITE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.orange_candle_slab)
			return BBBlocks.ORANGE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.magenta_candle_slab)
			return BBBlocks.MAGENTA_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.light_blue_candle_slab)
			return BBBlocks.LIGHT_BLUE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.yellow_candle_slab)
			return BBBlocks.YELLOW_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.lime_candle_slab)
			return BBBlocks.LIME_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pink_candle_slab)
			return BBBlocks.PINK_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.gray_candle_slab)
			return BBBlocks.GRAY_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.light_gray_candle_slab)
			return BBBlocks.LIGHT_GRAY_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.cyan_candle_slab)
			return BBBlocks.CYAN_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.purple_candle_slab)
			return BBBlocks.PURPLE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.blue_candle_slab)
			return BBBlocks.BLUE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.brown_candle_slab)
			return BBBlocks.BROWN_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.green_candle_slab)
			return BBBlocks.GREEN_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.red_candle_slab)
			return BBBlocks.RED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.black_candle_slab)
			return BBBlocks.BLACK_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.amber_candle_slab)
			return BBBlocks.AMBER_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.beige_candle_slab)
			return BBBlocks.BEIGE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.cream_candle_slab)
			return BBBlocks.CREAM_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.dark_green_candle_slab)
			return BBBlocks.DARK_GREEN_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.forest_green_candle_slab)
			return BBBlocks.FOREST_GREEN_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.hot_pink_candle_slab)
			return BBBlocks.HOT_PINK_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.indigo_candle_slab)
			return BBBlocks.INDIGO_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.maroon_candle_slab)
			return BBBlocks.MAROON_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.navy_candle_slab)
			return BBBlocks.NAVY_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.olive_candle_slab)
			return BBBlocks.OLIVE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pale_green_candle_slab)
			return BBBlocks.PALE_GREEN_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pale_pink_candle_slab)
			return BBBlocks.PALE_PINK_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.sky_blue_candle_slab)
			return BBBlocks.SKY_BLUE_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.slate_gray_candle_slab)
			return BBBlocks.SLATE_GRAY_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.violet_candle_slab)
			return BBBlocks.VIOLET_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.allium_scented_candle_slab)
			return BBBlocks.ALLIUM_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.azure_bluet_scented_candle_slab)
			return BBBlocks.AZURE_BLUET_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.blue_orchid_scented_candle_slab)
			return BBBlocks.BLUE_ORCHID_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.dandelion_scented_candle_slab)
			return BBBlocks.DANDELION_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.cornflower_scented_candle_slab)
			return BBBlocks.CORNFLOWER_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.lily_of_the_valley_scented_candle_slab)
			return BBBlocks.LILY_OF_THE_VALLEY_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.oxeye_daisy_scented_candle_slab)
			return BBBlocks.OXEYE_DAISY_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.poppy_scented_candle_slab)
			return BBBlocks.POPPY_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.white_tulip_scented_candle_slab)
			return BBBlocks.WHITE_TULIP_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.orange_tulip_scented_candle_slab)
			return BBBlocks.ORANGE_TULIP_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pink_tulip_scented_candle_slab)
			return BBBlocks.PINK_TULIP_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.red_tulip_scented_candle_slab)
			return BBBlocks.RED_TULIP_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.wither_rose_scented_candle_slab)
			return BBBlocks.WITHER_ROSE_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.cartwheel_scented_candle_slab)
			return BBBlocks.CARTWHEEL_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.bluebell_scented_candle_slab)
			return BBBlocks.BLUEBELL_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.daybloom_scented_candle_slab)
			return BBBlocks.DAYBLOOM_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.violet_scented_candle_slab)
			return BBBlocks.VIOLET_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.jolyce_scented_candle_slab)
			return BBBlocks.JOLYCE_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.columbine_scented_candle_slab)
			return BBBlocks.COLUMBINE_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.white_clover_scented_candle_slab)
			return BBBlocks.WHITE_CLOVER_SCENTED_CANDLE.get();
		if(state.getBlock() == BuzzierBeesCompat.pink_clover_scented_candle_slab)
			return BBBlocks.PINK_CLOVER_SCENTED_CANDLE.get();

		return state.getBlock();	
	}
	
	public static Block getPadBlock(BlockState state)
	{
		if(state.getBlock() == BuzzierBeesCompat.candle_slab)
			return BuzzierBeesCompat.pad_candle;
		if(state.getBlock() == BuzzierBeesCompat.white_candle_slab)
			return BuzzierBeesCompat.pad_white_candle;
		if(state.getBlock() == BuzzierBeesCompat.orange_candle_slab)
			return BuzzierBeesCompat.pad_orange_candle;
		if(state.getBlock() == BuzzierBeesCompat.magenta_candle_slab)
			return BuzzierBeesCompat.pad_magenta_candle;
		if(state.getBlock() == BuzzierBeesCompat.light_blue_candle_slab)
			return BuzzierBeesCompat.pad_light_blue_candle;
		if(state.getBlock() == BuzzierBeesCompat.yellow_candle_slab)
			return BuzzierBeesCompat.pad_yellow_candle;
		if(state.getBlock() == BuzzierBeesCompat.lime_candle_slab)
			return BuzzierBeesCompat.pad_lime_candle;
		if(state.getBlock() == BuzzierBeesCompat.pink_candle_slab)
			return BuzzierBeesCompat.pad_pink_candle;
		if(state.getBlock() == BuzzierBeesCompat.gray_candle_slab)
			return BuzzierBeesCompat.pad_gray_candle;
		if(state.getBlock() == BuzzierBeesCompat.light_gray_candle_slab)
			return BuzzierBeesCompat.pad_light_gray_candle;
		if(state.getBlock() == BuzzierBeesCompat.cyan_candle_slab)
			return BuzzierBeesCompat.pad_cyan_candle;
		if(state.getBlock() == BuzzierBeesCompat.purple_candle_slab)
			return BuzzierBeesCompat.pad_purple_candle;
		if(state.getBlock() == BuzzierBeesCompat.blue_candle_slab)
			return BuzzierBeesCompat.pad_blue_candle;
		if(state.getBlock() == BuzzierBeesCompat.brown_candle_slab)
			return BuzzierBeesCompat.pad_brown_candle;
		if(state.getBlock() == BuzzierBeesCompat.green_candle_slab)
			return BuzzierBeesCompat.pad_green_candle;
		if(state.getBlock() == BuzzierBeesCompat.red_candle_slab)
			return BuzzierBeesCompat.pad_red_candle;
		if(state.getBlock() == BuzzierBeesCompat.black_candle_slab)
			return BuzzierBeesCompat.pad_black_candle;
		if(state.getBlock() == BuzzierBeesCompat.amber_candle_slab)
			return BuzzierBeesCompat.pad_amber_candle;
		if(state.getBlock() == BuzzierBeesCompat.beige_candle_slab)
			return BuzzierBeesCompat.pad_beige_candle;
		if(state.getBlock() == BuzzierBeesCompat.cream_candle_slab)
			return BuzzierBeesCompat.pad_cream_candle;
		if(state.getBlock() == BuzzierBeesCompat.dark_green_candle_slab)
			return BuzzierBeesCompat.pad_dark_green_candle;
		if(state.getBlock() == BuzzierBeesCompat.forest_green_candle_slab)
			return BuzzierBeesCompat.pad_forest_green_candle;
		if(state.getBlock() == BuzzierBeesCompat.hot_pink_candle_slab)
			return BuzzierBeesCompat.pad_hot_pink_candle;
		if(state.getBlock() == BuzzierBeesCompat.indigo_candle_slab)
			return BuzzierBeesCompat.pad_indigo_candle;
		if(state.getBlock() == BuzzierBeesCompat.maroon_candle_slab)
			return BuzzierBeesCompat.pad_maroon_candle;
		if(state.getBlock() == BuzzierBeesCompat.navy_candle_slab)
			return BuzzierBeesCompat.pad_navy_candle;
		if(state.getBlock() == BuzzierBeesCompat.olive_candle_slab)
			return BuzzierBeesCompat.pad_olive_candle;
		if(state.getBlock() == BuzzierBeesCompat.pale_green_candle_slab)
			return BuzzierBeesCompat.pad_pale_green_candle;
		if(state.getBlock() == BuzzierBeesCompat.pale_pink_candle_slab)
			return BuzzierBeesCompat.pad_pale_pink_candle;
		if(state.getBlock() == BuzzierBeesCompat.sky_blue_candle_slab)
			return BuzzierBeesCompat.pad_sky_blue_candle;
		if(state.getBlock() == BuzzierBeesCompat.slate_gray_candle_slab)
			return BuzzierBeesCompat.pad_slate_gray_candle;
		if(state.getBlock() == BuzzierBeesCompat.violet_candle_slab)
			return BuzzierBeesCompat.pad_violet_candle;
		if(state.getBlock() == BuzzierBeesCompat.allium_scented_candle_slab)
			return BuzzierBeesCompat.pad_allium_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.azure_bluet_scented_candle_slab)
			return BuzzierBeesCompat.pad_azure_bluet_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.blue_orchid_scented_candle_slab)
			return BuzzierBeesCompat.pad_blue_orchid_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.dandelion_scented_candle_slab)
			return BuzzierBeesCompat.pad_dandelion_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.cornflower_scented_candle_slab)
			return BuzzierBeesCompat.pad_cornflower_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.lily_of_the_valley_scented_candle_slab)
			return BuzzierBeesCompat.pad_lily_of_the_valley_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.oxeye_daisy_scented_candle_slab)
			return BuzzierBeesCompat.pad_oxeye_daisy_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.poppy_scented_candle_slab)
			return BuzzierBeesCompat.pad_poppy_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.white_tulip_scented_candle_slab)
			return BuzzierBeesCompat.pad_white_tulip_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.orange_tulip_scented_candle_slab)
			return BuzzierBeesCompat.pad_orange_tulip_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.pink_tulip_scented_candle_slab)
			return BuzzierBeesCompat.pad_pink_tulip_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.red_tulip_scented_candle_slab)
			return BuzzierBeesCompat.pad_red_tulip_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.wither_rose_scented_candle_slab)
			return BuzzierBeesCompat.pad_wither_rose_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.cartwheel_scented_candle_slab)
			return BuzzierBeesCompat.pad_cartwheel_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.bluebell_scented_candle_slab)
			return BuzzierBeesCompat.pad_bluebell_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.daybloom_scented_candle_slab)
			return BuzzierBeesCompat.pad_daybloom_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.violet_scented_candle_slab)
			return BuzzierBeesCompat.pad_violet_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.jolyce_scented_candle_slab)
			return BuzzierBeesCompat.pad_jolyce_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.columbine_scented_candle_slab)
			return BuzzierBeesCompat.pad_columbine_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.white_clover_scented_candle_slab)
			return BuzzierBeesCompat.pad_white_clover_scented_candle;
		if(state.getBlock() == BuzzierBeesCompat.pink_clover_scented_candle_slab)
			return BuzzierBeesCompat.pad_pink_clover_scented_candle;
		
		return state.getBlock();

	}
}
