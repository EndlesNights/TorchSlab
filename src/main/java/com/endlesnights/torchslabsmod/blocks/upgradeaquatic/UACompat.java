package com.endlesnights.torchslabsmod.blocks.upgradeaquatic;

import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.event.PlaceHandlerTorchSlab;
import com.endlesnights.torchslabsmod.event.PlaceHandlerTorchWall;
import com.endlesnights.torchslabsmod.event.upgradeaquatic.PlaceHandlerToothLantern;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

public class UACompat implements ITorchSlabCompat
{
	public static Block TOOTH_LANTERN = null;
	
	public static Block JELLY_TORCH_SLAB_PINK              = null;
	public static Block JELLY_TORCH_SLAB_PURPLE            = null;
	public static Block JELLY_TORCH_SLAB_BLUE              = null;
	public static Block JELLY_TORCH_SLAB_GREEN             = null;
	public static Block JELLY_TORCH_SLAB_YELLOW            = null;
	public static Block JELLY_TORCH_SLAB_ORANGE            = null;
	public static Block JELLY_TORCH_SLAB_RED               = null;
    public static Block JELLY_TORCH_SLAB_WHITE             = null;
    
	public static Block JELLY_TORCH_WALL_SLAB_PINK              = null;
	public static Block JELLY_TORCH_WALL_SLAB_PURPLE            = null;
	public static Block JELLY_TORCH_WALL_SLAB_BLUE              = null;
	public static Block JELLY_TORCH_WALL_SLAB_GREEN             = null;
	public static Block JELLY_TORCH_WALL_SLAB_YELLOW            = null;
	public static Block JELLY_TORCH_WALL_SLAB_ORANGE            = null;
	public static Block JELLY_TORCH_WALL_SLAB_RED               = null;
    public static Block JELLY_TORCH_WALL_SLAB_WHITE             = null;
    
	@Override
	public void registerBlocks(Register<Block> event)
	{
		try
		{			
			event.getRegistry().register(JELLY_TORCH_SLAB_PINK = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.PINK).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_slab_pink"));
			event.getRegistry().register(JELLY_TORCH_SLAB_PURPLE = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.PURPLE).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_slab_purple"));
			event.getRegistry().register(JELLY_TORCH_SLAB_BLUE = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.BLUE).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_slab_blue"));
			event.getRegistry().register(JELLY_TORCH_SLAB_GREEN = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.GREEN).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_slab_green"));
			event.getRegistry().register(JELLY_TORCH_SLAB_YELLOW = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.YELLOW).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_slab_yellow"));
			event.getRegistry().register(JELLY_TORCH_SLAB_ORANGE = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.ORANGE).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_slab_orange"));
			event.getRegistry().register(JELLY_TORCH_SLAB_RED = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.RED).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_slab_red"));
			event.getRegistry().register(JELLY_TORCH_SLAB_WHITE = new BlockJellyTorchSlab(Properties.from(Blocks.TORCH).sound(SoundType.METAL), BlockJellyTorchSlab.JellyTorchType.WHITE).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_slab_white"));
		
			event.getRegistry().register(JELLY_TORCH_WALL_SLAB_PINK = new BlockWallJellyTorchSlab(Properties.from(Blocks.WALL_TORCH).sound(SoundType.METAL), BlockWallJellyTorchSlab.JellyTorchType.PINK).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_wall_pink"));
			event.getRegistry().register(JELLY_TORCH_WALL_SLAB_PURPLE = new BlockWallJellyTorchSlab(Properties.from(Blocks.WALL_TORCH).sound(SoundType.METAL), BlockWallJellyTorchSlab.JellyTorchType.PURPLE).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_wall_purple"));
			event.getRegistry().register(JELLY_TORCH_WALL_SLAB_BLUE = new BlockWallJellyTorchSlab(Properties.from(Blocks.WALL_TORCH).sound(SoundType.METAL), BlockWallJellyTorchSlab.JellyTorchType.BLUE).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_wall_blue"));
			event.getRegistry().register(JELLY_TORCH_WALL_SLAB_GREEN = new BlockWallJellyTorchSlab(Properties.from(Blocks.WALL_TORCH).sound(SoundType.METAL), BlockWallJellyTorchSlab.JellyTorchType.GREEN).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_wall_green"));
			event.getRegistry().register(JELLY_TORCH_WALL_SLAB_YELLOW = new BlockWallJellyTorchSlab(Properties.from(Blocks.WALL_TORCH).sound(SoundType.METAL), BlockWallJellyTorchSlab.JellyTorchType.YELLOW).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_wall_yellow"));
			event.getRegistry().register(JELLY_TORCH_WALL_SLAB_ORANGE = new BlockWallJellyTorchSlab(Properties.from(Blocks.WALL_TORCH).sound(SoundType.METAL), BlockWallJellyTorchSlab.JellyTorchType.ORANGE).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_wall_orange"));
			event.getRegistry().register(JELLY_TORCH_WALL_SLAB_RED = new BlockWallJellyTorchSlab(Properties.from(Blocks.WALL_TORCH).sound(SoundType.METAL), BlockWallJellyTorchSlab.JellyTorchType.RED).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_wall_red"));
			event.getRegistry().register(JELLY_TORCH_WALL_SLAB_WHITE = new BlockWallJellyTorchSlab(Properties.from(Blocks.WALL_TORCH).sound(SoundType.METAL), BlockWallJellyTorchSlab.JellyTorchType.WHITE).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_jelly_torch_wall_white"));		

			if (FMLEnvironment.dist == Dist.CLIENT)
	        {
	            RenderType translucentRenderType = RenderType.getTranslucent();
	            
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_SLAB_PINK, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_SLAB_PURPLE, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_SLAB_BLUE, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_SLAB_GREEN, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_SLAB_YELLOW, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_SLAB_ORANGE, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_SLAB_RED, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_SLAB_WHITE, translucentRenderType);
	            
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_WALL_SLAB_PINK, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_WALL_SLAB_PURPLE, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_WALL_SLAB_BLUE, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_WALL_SLAB_GREEN, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_WALL_SLAB_YELLOW, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_WALL_SLAB_ORANGE, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_WALL_SLAB_RED, translucentRenderType);
	            RenderTypeLookup.setRenderLayer(JELLY_TORCH_WALL_SLAB_WHITE, translucentRenderType);

	        }
			
			event.getRegistry().register(TOOTH_LANTERN = new BlockToothLanternSlab(Properties.from(UABlocks.TOOTH_LANTERN.get()).lootFrom(UABlocks.TOOTH_LANTERN.get())).setRegistryName(TorchSlabsMod.MODID, "upgrade_aquatic_tooth_lantern"));
		}
		catch(Error e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & Upgrade Aquatic. Skipping Block:" + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & Upgrade Aquatic. Skipping Block:" + e.getMessage());
		}
		
		

	
	}

	@Override
	public void registerPlaceEntries()
	{
		
		try
		{
			PlaceHandlerTorchSlab.registerPlaceEntry(UABlocks.PINK_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_SLAB_PINK);
			PlaceHandlerTorchSlab.registerPlaceEntry(UABlocks.PURPLE_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_SLAB_PURPLE);
			PlaceHandlerTorchSlab.registerPlaceEntry(UABlocks.BLUE_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_SLAB_BLUE);
			PlaceHandlerTorchSlab.registerPlaceEntry(UABlocks.GREEN_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_SLAB_GREEN);
			PlaceHandlerTorchSlab.registerPlaceEntry(UABlocks.YELLOW_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_SLAB_YELLOW);
			PlaceHandlerTorchSlab.registerPlaceEntry(UABlocks.ORANGE_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_SLAB_ORANGE);
			PlaceHandlerTorchSlab.registerPlaceEntry(UABlocks.RED_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_SLAB_RED);
			PlaceHandlerTorchSlab.registerPlaceEntry(UABlocks.WHITE_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_SLAB_WHITE);
			
			PlaceHandlerTorchWall.registerPlaceEntry(UABlocks.PINK_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_WALL_SLAB_PINK);
			PlaceHandlerTorchWall.registerPlaceEntry(UABlocks.PURPLE_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_WALL_SLAB_PURPLE);
			PlaceHandlerTorchWall.registerPlaceEntry(UABlocks.BLUE_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_WALL_SLAB_BLUE);
			PlaceHandlerTorchWall.registerPlaceEntry(UABlocks.GREEN_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_WALL_SLAB_GREEN);
			PlaceHandlerTorchWall.registerPlaceEntry(UABlocks.YELLOW_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_WALL_SLAB_YELLOW);
			PlaceHandlerTorchWall.registerPlaceEntry(UABlocks.ORANGE_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_WALL_SLAB_ORANGE);
			PlaceHandlerTorchWall.registerPlaceEntry(UABlocks.RED_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_WALL_SLAB_RED);
			PlaceHandlerTorchWall.registerPlaceEntry(UABlocks.WHITE_JELLY_TORCH.get().getRegistryName(), JELLY_TORCH_WALL_SLAB_WHITE);	
			
			PlaceHandlerToothLantern.registerPlaceEntry(UABlocks.TOOTH_LANTERN.get().getRegistryName(), TOOTH_LANTERN);
		}
		catch(Error e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & Upgrade Aquatic. Skipping Block:" + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error while attpeing to load compatibility with TorchSlab & Upgrade Aquatic. Skipping Block:" + e.getMessage());
		}
	}
	
    public static Block registerBlock(Block block, String name)
    {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }

}
