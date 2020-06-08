package com.endlesnights.torchslabsmod.blocks.vanilla;

import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.event.PlaceHandlerEndRod;
import com.endlesnights.torchslabsmod.event.PlaceHandlerLanternSlab;
import com.endlesnights.torchslabsmod.event.PlaceHandlerLanternWall;
import com.endlesnights.torchslabsmod.event.PlaceHandlerPadLights;
import com.endlesnights.torchslabsmod.event.PlaceHandlerTorchSlab;
import com.endlesnights.torchslabsmod.event.PlaceHandlerTorchWall;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TorchSlabsMod.MODID)
public class VanillaCompat implements ITorchSlabCompat
{
	public static Block torch = null;
	public static Block redstone_torch = null;
	public static Block lantern = null;
	
	public static Block wall_torch_slab = null;
	public static Block wall_lantern = null;
	
	public static Block end_rod_slab = null;
	
	public static Block pad_torch = null;
	public static Block pad_lantern = null;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		torch = registerBlock(new BlockTorchSlab(Block.Properties.from(Blocks.TORCH).lootFrom(Blocks.TORCH)), "torch");
		redstone_torch = registerBlock(new BlockRedstoneTorchSlab(Block.Properties.from(Blocks.REDSTONE_TORCH).lootFrom(Blocks.REDSTONE_TORCH)), "redstone_torch");
					
		lantern = registerBlock(new BlockLanternSlab(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5F).sound(SoundType.LANTERN).lightValue(15).notSolid().lootFrom(Blocks.LANTERN)), "lantern");
	
		wall_torch_slab = registerBlock(new BlockWallTorchSlab(Block.Properties.from(Blocks.WALL_TORCH).lootFrom(Blocks.TORCH)), "wall_torch_slab");
		wall_lantern = registerBlock(new BlockWallLanternSlab(Block.Properties.from(Blocks.LANTERN).lootFrom(Blocks.LANTERN)), "wall_lantern" );
		end_rod_slab = registerBlock(new BlockEndRodSlab(Block.Properties.from(Blocks.END_ROD)), "end_rod_slab");
		
		pad_torch = registerBlock(new BlockPadTorch(Block.Properties.from(Blocks.TORCH)), "pad_torch");
		pad_lantern = registerBlock(new BlockPadLantern(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.LANTERN).lightValue(15).notSolid()), "pad_lantern");
		
		if (FMLEnvironment.dist == Dist.CLIENT)
        {
            RenderType transparentRenderType = RenderType.getCutoutMipped();
            
            RenderTypeLookup.setRenderLayer(torch, transparentRenderType);
            RenderTypeLookup.setRenderLayer(lantern, transparentRenderType);
            RenderTypeLookup.setRenderLayer(redstone_torch, transparentRenderType);
            RenderTypeLookup.setRenderLayer(wall_torch_slab, transparentRenderType);
            RenderTypeLookup.setRenderLayer(wall_lantern, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_torch, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pad_lantern, transparentRenderType);
        }
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandlerTorchSlab.registerPlaceEntry(Items.TORCH.getRegistryName(), torch);
		PlaceHandlerTorchWall.registerPlaceEntry(Items.TORCH.getRegistryName(), wall_torch_slab);
		
		PlaceHandlerTorchSlab.registerPlaceEntry(Items.REDSTONE_TORCH.getRegistryName(), redstone_torch);
		
		PlaceHandlerLanternSlab.registerPlaceEntry(Items.LANTERN.getRegistryName(), lantern);
		PlaceHandlerLanternWall.registerPlaceEntry(Items.LANTERN.getRegistryName(), wall_lantern);
		
		PlaceHandlerPadLights.registerPlaceEntry(Items.TORCH.getRegistryName(), pad_torch);
		PlaceHandlerPadLights.registerPlaceEntry(Items.LANTERN.getRegistryName(), pad_lantern);
		
		PlaceHandlerEndRod.registerPlaceEntry(Items.END_ROD.getRegistryName(), end_rod_slab);
	}
	
    public static Block registerBlock(Block block, String name)
    {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }
}