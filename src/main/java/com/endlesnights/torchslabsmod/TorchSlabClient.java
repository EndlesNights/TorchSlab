package com.endlesnights.torchslabsmod;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

@EventBusSubscriber(modid=TorchSlabsMod.MODID, bus=Bus.MOD, value=Dist.CLIENT)
public class TorchSlabClient
{
	@SubscribeEvent
	public static void onInterModProcess(InterModProcessEvent event)
	{
		for(Block b : PlaceHandlerBottomSlab.getPlaceEntryBlocks())
		{
			RenderTypeLookup.setRenderLayer(b, RenderType.func_228641_d_());
		}
		for(Block b : PlaceHandlerDuelSlab.getPlaceEntryBlocks())
		{
			RenderTypeLookup.setRenderLayer(b, RenderType.func_228641_d_());
		}
	}
}