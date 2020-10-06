package com.endlesnights.torchslabsmod.blocks.vanilla;

import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.event.PlaceHandlerChainSlab;
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
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TorchSlabsMod.MODID)
public class VanillaCompat implements ITorchSlabCompat
{
	public static Block torch = null;
	public static Block soul_torch = null;
	public static Block lantern = null;
	public static Block soul_lantern = null;
	
	public static Block chain_slab = null;
	
	public static Block wall_torch_slab = null;
	public static Block wall_soul_torch_slab = null;
	public static Block wall_lantern = null;
	public static Block wall_soul_lantern = null;
	
	public static Block end_rod_slab = null;
	
	public static Block pad_torch = null;
	public static Block pad_soul_torch = null;
	public static Block pad_lantern = null;
	public static Block pad_soul_lantern = null;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		torch = registerBlock(new BlockTorchSlab(Block.Properties.from(Blocks.TORCH).lootFrom(Blocks.TORCH), ParticleTypes.FLAME, 't'), "torch");
		soul_torch = registerBlock(new BlockTorchSlab(Block.Properties.from(Blocks.SOUL_TORCH).lootFrom(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME, 's'), "soul_torch");
					
		lantern = registerBlock(new BlockLanternSlab(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5F).sound(SoundType.LANTERN).setLightLevel((p_235454_0_) -> {return 15; }).notSolid().lootFrom(Blocks.LANTERN), 'l'), "lantern");
		soul_lantern = registerBlock(new BlockLanternSlab(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5F).sound(SoundType.LANTERN).setLightLevel((p_235454_0_) -> {return 15; }).notSolid().lootFrom(Blocks.SOUL_LANTERN), 's'), "soul_lantern");
	
		wall_torch_slab = registerBlock(new BlockWallTorchSlab(Block.Properties.from(Blocks.WALL_TORCH).lootFrom(Blocks.TORCH), ParticleTypes.FLAME, 't'), "wall_torch_slab");
		wall_soul_torch_slab = registerBlock(new BlockWallTorchSlab(Block.Properties.from(Blocks.WALL_TORCH).lootFrom(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME, 's'), "wall_soul_torch_slab");
		wall_lantern = registerBlock(new BlockWallLanternSlab(Block.Properties.from(Blocks.LANTERN).lootFrom(Blocks.LANTERN), 'l'), "wall_lantern" );
		wall_soul_lantern = registerBlock(new BlockWallLanternSlab(Block.Properties.from(Blocks.SOUL_LANTERN).lootFrom(Blocks.SOUL_LANTERN), 's'), "wall_soul_lantern" );
		
		end_rod_slab = registerBlock(new BlockEndRodSlab(Block.Properties.from(Blocks.END_ROD)), "end_rod_slab");
		
		pad_torch = registerBlock(new BlockPadTorch(Block.Properties.from(Blocks.TORCH), ParticleTypes.FLAME , 't'), "pad_torch");
		pad_soul_torch = registerBlock(new BlockPadTorch(Block.Properties.from(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME , 's'), "pad_soul_torch");
		pad_lantern = registerBlock(new BlockPadLantern(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.LANTERN).setLightLevel((p_235454_0_) -> {return 15; }).notSolid(), 'l'), "pad_lantern");
		pad_soul_lantern = registerBlock(new BlockPadLantern(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.LANTERN).setLightLevel((p_235454_0_) -> {return 15; }).notSolid(), 's'), "pad_soul_lantern");

		// TODO: Needs fixing (collision box)
//		chain_slab = registerBlock(new BlockChainSlab(Block.Properties.from(Blocks.CHAIN).lootFrom(Blocks.CHAIN)),"chain_slab");
		if (FMLEnvironment.dist == Dist.CLIENT)
        {
			RenderType transparentRenderType = RenderType.getCutoutMipped();
            
			RenderTypeLookup.setRenderLayer(torch, transparentRenderType);
			RenderTypeLookup.setRenderLayer(lantern, transparentRenderType);
			RenderTypeLookup.setRenderLayer(wall_torch_slab, transparentRenderType);
			RenderTypeLookup.setRenderLayer(wall_lantern, transparentRenderType);
			RenderTypeLookup.setRenderLayer(pad_torch, transparentRenderType);
			RenderTypeLookup.setRenderLayer(pad_lantern, transparentRenderType);
			
			RenderTypeLookup.setRenderLayer(soul_torch, transparentRenderType);
			RenderTypeLookup.setRenderLayer(soul_lantern, transparentRenderType);
			RenderTypeLookup.setRenderLayer(wall_soul_torch_slab, transparentRenderType);
			RenderTypeLookup.setRenderLayer(wall_soul_lantern, transparentRenderType);
			RenderTypeLookup.setRenderLayer(pad_soul_torch, transparentRenderType);
			RenderTypeLookup.setRenderLayer(pad_soul_lantern, transparentRenderType);
			
//			RenderTypeLookup.setRenderLayer(chain_slab, transparentRenderType);
        }
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandlerTorchSlab.registerPlaceEntry(Items.TORCH.getRegistryName(), torch);
		PlaceHandlerTorchWall.registerPlaceEntry(Items.TORCH.getRegistryName(), wall_torch_slab);
		PlaceHandlerTorchSlab.registerPlaceEntry(Items.SOUL_TORCH.getRegistryName(), soul_torch);
		PlaceHandlerTorchWall.registerPlaceEntry(Items.SOUL_TORCH.getRegistryName(), wall_soul_torch_slab);
				
		PlaceHandlerLanternSlab.registerPlaceEntry(Items.LANTERN.getRegistryName(), lantern);
		PlaceHandlerLanternWall.registerPlaceEntry(Items.LANTERN.getRegistryName(), wall_lantern);
		PlaceHandlerLanternSlab.registerPlaceEntry(Items.SOUL_LANTERN.getRegistryName(), soul_lantern);
		PlaceHandlerLanternWall.registerPlaceEntry(Items.SOUL_LANTERN.getRegistryName(), wall_soul_lantern);
		
		PlaceHandlerPadLights.registerPlaceEntry(Items.TORCH.getRegistryName(), pad_torch);
		PlaceHandlerPadLights.registerPlaceEntry(Items.SOUL_TORCH.getRegistryName(), pad_soul_torch);
		PlaceHandlerPadLights.registerPlaceEntry(Items.LANTERN.getRegistryName(), pad_lantern);
		PlaceHandlerPadLights.registerPlaceEntry(Items.SOUL_LANTERN.getRegistryName(), pad_soul_lantern);
		
//		PlaceHandlerChainSlab.registerPlaceEntry(Items.CHAIN.getRegistryName(), chain_slab);
		PlaceHandlerEndRod.registerPlaceEntry(Items.END_ROD.getRegistryName(), end_rod_slab);
	}
	
    public static Block registerBlock(Block block, String name)
    {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }
}