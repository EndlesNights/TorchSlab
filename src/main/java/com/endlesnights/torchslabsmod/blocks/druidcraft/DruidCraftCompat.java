package com.endlesnights.torchslabsmod.blocks.druidcraft;

import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.event.PlaceHandlerTorchSlab;
import com.endlesnights.torchslabsmod.event.PlaceHandlerTorchWall;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

public class DruidCraftCompat implements ITorchSlabCompat
{
	public static Block fiery_torch_slab;
	public static Block fiery_torch_wall_slab;
	
	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{	
		fiery_torch_slab = registerBlock(new BlockFieryTorchSlab(Properties.from(Blocks.TORCH)), "druid_fiery_torch_slab");
		fiery_torch_wall_slab = registerBlock(new BlockFieryTorchWallSlab(Properties.from(Blocks.TORCH)), "druid_fiery_torch_wall_slab");
		
		
		if (FMLEnvironment.dist == Dist.CLIENT)
        {
            RenderType transparentRenderType = RenderType.getCutoutMipped();
            
            RenderTypeLookup.setRenderLayer(fiery_torch_slab, transparentRenderType);
            RenderTypeLookup.setRenderLayer(fiery_torch_wall_slab, transparentRenderType);
        }
	}
	
	@Override
	public void registerPlaceEntries()
	{
		PlaceHandlerTorchSlab.registerPlaceEntry(druidBlock("druidcraft:fiery_torch").getRegistryName(), fiery_torch_slab);
		PlaceHandlerTorchWall.registerPlaceEntry(druidBlock("druidcraft:fiery_torch").getRegistryName(), fiery_torch_wall_slab);
	}
	
    public static Block registerBlock(Block block, String name)
    {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }
    
	Block druidBlock(String regIndex)
	{
		return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(regIndex));
	}
}
