package com.endlesnights.torchslabsmod.blocks.vanilla;

import com.endlesnights.torchslabsmod.TorchSlabsMod;

import net.minecraft.client.renderer.color.BlockColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid=TorchSlabsMod.MODID, bus=Bus.MOD, value=Dist.CLIENT)
public class VanillaColor 
{
	
	@SubscribeEvent
	public static void registerBlockColorHandler(final ColorHandlerEvent.Block event)
	{
		final BlockColors blockColors = event.getBlockColors();
		
		//blockColors.register(new BlockPadTorch.ColorHandler(), VanillaCompat.pad_torch);
		blockColors.register((p_228055_0_, p_228055_1_, p_228055_2_, p_228055_3_) -> {
	         return p_228055_1_ != null && p_228055_2_ != null ? 2129968 : 7455580;
	      }, VanillaCompat.pad_torch, VanillaCompat.pad_lantern);
	}
	
//	@SubscribeEvent
//	public static void registerItemColorHandler(final ColorHandlerEvent.Item event)
//	{
//		BlockColors blockColors = event.getBlockColors();
//		ItemColors itemColors = event.getItemColors();
//		
//		final IItemColor itemBlockColourHandler = (stack, tintIndex) -> {
//		    final BlockState state = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
//		    return  blockColors.func_228054_a_(state, null, null, tintIndex);
//		};
//		
//		itemColors.register(itemBlockColourHandler, VanillaCompat.pad_torch);
//	}
}
