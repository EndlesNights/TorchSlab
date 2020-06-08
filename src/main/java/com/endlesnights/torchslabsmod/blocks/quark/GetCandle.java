package com.endlesnights.torchslabsmod.blocks.quark;

import net.minecraft.block.Block;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class GetCandle
{
	public static Block getParent(DyeColor color)
	{
		switch(color)
		{
			default:
			case BLACK:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:black_candle"));
			case BLUE:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:blue_candle"));
			case BROWN:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:brown_candle"));
			case CYAN:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:cyan_candle"));
			case GRAY:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:gray_candle"));
			case GREEN:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:green_candle"));
			case LIGHT_BLUE:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:light_blue_candle"));
			case LIGHT_GRAY:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:light_gray_candle"));
			case LIME:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:lime_candle"));
			case MAGENTA:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:magenta_candle"));
			case ORANGE:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:orange_candle"));
			case PINK:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:pink_candle"));
			case PURPLE:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:purple_candle"));
			case RED:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:red_candle"));
			case WHITE:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:white_candle"));
			case YELLOW:
				return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark:yellow_candle"));
		}
	}
	
	public static Block getPad(DyeColor color)
	{
		switch(color)
		{
			default:
			case BLACK:
				return QuarkCompat.black_candle_pad;
			case BLUE:
				return QuarkCompat.black_candle_pad;
			case BROWN:
				return QuarkCompat.brown_candle_pad;
			case CYAN:
				return QuarkCompat.cyan_candle_pad;
			case GRAY:
				return QuarkCompat.gray_candle_pad;
			case GREEN:
				return QuarkCompat.green_candle_pad;
			case LIGHT_BLUE:
				return QuarkCompat.light_blue_candle_pad;
			case LIGHT_GRAY:
				return QuarkCompat.light_gray_candle_pad;
			case LIME:
				return QuarkCompat.lime_candle_pad;
			case MAGENTA:
				return QuarkCompat.magenta_candle_pad;
			case ORANGE:
				return QuarkCompat.orange_candle_pad;
			case PINK:
				return QuarkCompat.pink_candle_pad;
			case PURPLE:
				return QuarkCompat.purple_candle_pad;
			case RED:
				return QuarkCompat.red_candle_pad;
			case WHITE:
				return QuarkCompat.white_candle_pad;
			case YELLOW:
				return QuarkCompat.yellow_candle_pad;
		}
	}
}
