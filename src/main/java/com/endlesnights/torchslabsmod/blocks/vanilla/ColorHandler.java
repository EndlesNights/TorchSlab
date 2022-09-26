package com.endlesnights.torchslabsmod.blocks.vanilla;

import com.endlesnights.torchslabsmod.TorchSlabsMod;
// com.endlesnights.torchslabsmod.blocks.bambooblock.BambooBlockCompat;
//import com.endlesnights.torchslabsmod.blocks.buzzierbees.BuzzierBeesCompat;
//import com.endlesnights.torchslabsmod.blocks.quark.QuarkCompat;

//import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.color.block.BlockColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid=TorchSlabsMod.MODID, bus=Bus.MOD, value=Dist.CLIENT)
public class ColorHandler 
{
	
	@SubscribeEvent
	public static void registerBlockColorHandler(final RegisterColorHandlersEvent.Block event)
	{
		final BlockColors blockColors = event.getBlockColors();
		
		//blockColors.register(new BlockPadTorch.ColorHandler(), VanillaCompat.pad_torch);
		
		try
		{
			blockColors.register((p_228055_0_, p_228055_1_, p_228055_2_, p_228055_3_) -> {
		         return p_228055_1_ != null && p_228055_2_ != null ? 2129968 : 7455580;
		      },
					VanillaCompat.pad_torch.get(),
					VanillaCompat.pad_soul_torch.get(),
					VanillaCompat.pad_lantern.get(),
					VanillaCompat.pad_soul_lantern.get(),
					
					VanillaCompat.pad_candle.get(),
					VanillaCompat.pad_candle_black.get(),
					VanillaCompat.pad_candle_blue.get(),
					VanillaCompat.pad_candle_brown.get(),
					VanillaCompat.pad_candle_cyan.get(),
					VanillaCompat.pad_candle_gray.get(),
					VanillaCompat.pad_candle_green.get(),
					VanillaCompat.pad_candle_light_blue.get(),
					VanillaCompat.pad_candle_light_gray.get(),
					VanillaCompat.pad_candle_lime.get(),
					VanillaCompat.pad_candle_magenta.get(),
					VanillaCompat.pad_candle_orange.get(),
					VanillaCompat.pad_candle_pink.get(),
					VanillaCompat.pad_candle_purple.get(),
					VanillaCompat.pad_candle_red.get(),
					VanillaCompat.pad_candle_white.get(),
					VanillaCompat.pad_candle_yellow.get()
					
					);
		}
		catch(Error e)
		{
			System.out.println("Error while attpeing to load base lillypad blocks:" + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error while attpeing to load base lillypad blocks:" + e.getMessage());
		}
		
//		if(ModList.get().isLoaded("buzzierbees"))
//		{
//			try
//			{
//				blockColors.register((p_228055_0_, p_228055_1_, p_228055_2_, p_228055_3_) -> {
//			         return p_228055_1_ != null && p_228055_2_ != null ? 2129968 : 7455580;
//			      },
//						BuzzierBeesCompat.pad_candle,
//						
//						BuzzierBeesCompat.pad_white_candle,
//						BuzzierBeesCompat.pad_orange_candle,
//						BuzzierBeesCompat.pad_magenta_candle,
//						BuzzierBeesCompat.pad_light_blue_candle,
//						BuzzierBeesCompat.pad_yellow_candle,
//						BuzzierBeesCompat.pad_lime_candle,
//						BuzzierBeesCompat.pad_pink_candle,
//						BuzzierBeesCompat.pad_gray_candle,
//						BuzzierBeesCompat.pad_light_gray_candle,
//						BuzzierBeesCompat.pad_cyan_candle,
//						BuzzierBeesCompat.pad_purple_candle,
//						BuzzierBeesCompat.pad_blue_candle,
//						BuzzierBeesCompat.pad_brown_candle,
//						BuzzierBeesCompat.pad_red_candle,
//						BuzzierBeesCompat.pad_black_candle,
//						
//						BuzzierBeesCompat.pad_amber_candle,
//						BuzzierBeesCompat.pad_beige_candle,
//						BuzzierBeesCompat.pad_cream_candle,
//						BuzzierBeesCompat.pad_dark_green_candle,
//						BuzzierBeesCompat.pad_forest_green_candle,
//						BuzzierBeesCompat.pad_hot_pink_candle,
//						BuzzierBeesCompat.pad_indigo_candle,
//						BuzzierBeesCompat.pad_maroon_candle,
//						BuzzierBeesCompat.pad_navy_candle,
//						BuzzierBeesCompat.pad_olive_candle,
//						BuzzierBeesCompat.pad_pale_green_candle,
//						BuzzierBeesCompat.pad_pale_pink_candle,
//						BuzzierBeesCompat.pad_sky_blue_candle,
//						BuzzierBeesCompat.pad_slate_gray_candle,
//						BuzzierBeesCompat.pad_violet_candle,
//						
//						BuzzierBeesCompat.pad_allium_scented_candle,
//						BuzzierBeesCompat.pad_azure_bluet_scented_candle,
//						BuzzierBeesCompat.pad_blue_orchid_scented_candle,
//						BuzzierBeesCompat.pad_dandelion_scented_candle,
//						BuzzierBeesCompat.pad_cornflower_scented_candle,
//						BuzzierBeesCompat.pad_lily_of_the_valley_scented_candle,
//						BuzzierBeesCompat.pad_oxeye_daisy_scented_candle,
//						BuzzierBeesCompat.pad_poppy_scented_candle,
//						BuzzierBeesCompat.pad_white_tulip_scented_candle,
//						BuzzierBeesCompat.pad_orange_tulip_scented_candle,
//						BuzzierBeesCompat.pad_pink_tulip_scented_candle,
//						BuzzierBeesCompat.pad_red_tulip_scented_candle,
//						BuzzierBeesCompat.pad_wither_rose_scented_candle,
//						
//						BuzzierBeesCompat.pad_cartwheel_scented_candle,
//						BuzzierBeesCompat.pad_bluebell_scented_candle,
//						BuzzierBeesCompat.pad_columbine_scented_candle,
//						BuzzierBeesCompat.pad_jolyce_scented_candle,
//						BuzzierBeesCompat.pad_pink_clover_scented_candle,
//						BuzzierBeesCompat.pad_white_clover_scented_candle,
//						BuzzierBeesCompat.pad_daybloom_scented_candle,
//						BuzzierBeesCompat.pad_violet_scented_candle
//	
//						);
//				}
//			catch(Error e)
//			{
//				System.out.println("Error while attpeing to load compatibility with TorchSlab & BuzzueBees. Skipping Block:" + e.getMessage());
//			}
//			catch(Exception e)
//			{
//				System.out.println("Error while attpeing to load compatibility with TorchSlab & BuzzueBees. Skipping Block:" + e.getMessage());
//			}
//		}
		
//		if(ModList.get().isLoaded("bambooblocks"))
//		{
//			try
//			{
//				blockColors.register((p_228055_0_, p_228055_1_, p_228055_2_, p_228055_3_) -> {
//			         return p_228055_1_ != null && p_228055_2_ != null ? 2129968 : 7455580;
//			      },
//						BambooBlockCompat.bamboo_torch_pad
//				);
//			}
//			catch(Error e)
//			{
//				System.out.println("Error while attpeing to load compatibility with TorchSlab & BambooBlocks. Skipping Block:" + e.getMessage());
//			}
//			catch(Exception e)
//			{
//				System.out.println("Error while attpeing to load compatibility with TorchSlab & BambooBlocks. Skipping Block:" + e.getMessage());
//			}
//		}
		
//		if(ModList.get().isLoaded("quark"))
//		{
//			try
//			{
//				blockColors.register((p_228055_0_, p_228055_1_, p_228055_2_, p_228055_3_) -> {
//			         return p_228055_1_ != null && p_228055_2_ != null ? 2129968 : 7455580;
//			      },
//						QuarkCompat.black_candle_pad,
//						QuarkCompat.blue_candle_pad,
//						QuarkCompat.brown_candle_pad,
//						QuarkCompat.cyan_candle_pad,
//						QuarkCompat.gray_candle_pad,
//						QuarkCompat.green_candle_pad,
//						QuarkCompat.light_blue_candle_pad,
//						QuarkCompat.light_gray_candle_pad,
//						QuarkCompat.lime_candle_pad,
//						QuarkCompat.magenta_candle_pad,
//						QuarkCompat.orange_candle_pad,
//						QuarkCompat.pink_candle_pad,
//						QuarkCompat.purple_candle_pad,
//						QuarkCompat.red_candle_pad,
//						QuarkCompat.white_candle_pad,
//						QuarkCompat.yellow_candle_pad
//				);
//			}
//			catch(Error e)
//			{
//				System.out.println("Error while attpeing to load compatibility with TorchSlab & Quark. Skipping Block:" + e.getMessage());
//			}
//			catch(Exception e)
//			{
//				System.out.println("Error while attpeing to load compatibility with TorchSlab & Quark. Skipping Block:" + e.getMessage());
//			}
//		}
	}
	
//	@SubscribeEvent
//	public static void registerItemColorHandler(final ColorHandlerEvent.Item event)
//	{
//		BlockColors blockColors = event.getBlockColors();
//		ItemColors itemColors = event.getItemColors();
//		
//		final IItemColor itemBlockColourHandler = (stack, tintIndex) -> {
//		    final BlockState state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
//		    return  blockColors.func_228054_a_(state, null, null, tintIndex);
//		};
//		
//		itemColors.register(itemBlockColourHandler, VanillaCompat.pad_torch);
//	}
}
