package com.endlesnights.torchslabsmod.blocks.bambooblock;

import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.event.PlaceHandlerPadLights;
import com.endlesnights.torchslabsmod.event.PlaceHandlerTorchSlab;
import com.endlesnights.torchslabsmod.event.PlaceHandlerTorchWall;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

public class BambooBlockCompat implements ITorchSlabCompat
{
	public static Block bamboo_torch_slab = null;
	public static Block bamboo_torch_wall = null;
	public static Block bamboo_torch_pad = null;
	
	@Override
	public void registerBlocks(Register<Block> event)
	{
		try
		{
			bamboo_torch_slab = registerBlock(new BlockBambooTorchSlab(), "bamboo_torch_slab");
			bamboo_torch_wall = registerBlock(new BlockBambooTorchWall(), "bamboo_torch_wall");
			bamboo_torch_pad = registerBlock(new BlockBambooTorchPad(), "bamboo_torch_pad");
			
			if (FMLEnvironment.dist == Dist.CLIENT)
	        {
	            RenderType transparentRenderType = RenderType.getCutoutMipped();
	            RenderTypeLookup.setRenderLayer(bamboo_torch_slab, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(bamboo_torch_wall, transparentRenderType);
	            RenderTypeLookup.setRenderLayer(bamboo_torch_pad, transparentRenderType);
	        }
		}
		catch(Error e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & BambooBlocks. Skipping Block:" + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & BambooBlocks. Skipping Block:" + e.getMessage());
		}

	}

	@Override
	public void registerPlaceEntries()
	{
		try
		{
			PlaceHandlerTorchSlab.registerPlaceEntry(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bambooblocks:bamboo_torch")).getRegistryName(), bamboo_torch_slab);
			PlaceHandlerTorchWall.registerPlaceEntry(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bambooblocks:bamboo_torch")).getRegistryName(), bamboo_torch_wall);
			PlaceHandlerPadLights.registerPlaceEntry(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bambooblocks:bamboo_torch")).getRegistryName(), bamboo_torch_pad);
		}
		catch(Error e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & BambooBlocks. Skipping Block:" + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & BambooBlocks. Skipping Block:" + e.getMessage());
		}
	}

    public static Block registerBlock(Block block, String name)
    {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }
}
