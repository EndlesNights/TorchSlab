package com.endlesnights.torchslabsmod.vanilla;

import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.PlaceHandlerEndRod;
import com.endlesnights.torchslabsmod.PlaceHandlerLanternSlab;
import com.endlesnights.torchslabsmod.PlaceHandlerLanternWall;
import com.endlesnights.torchslabsmod.PlaceHandlerTorchSlab;
import com.endlesnights.torchslabsmod.PlaceHandlerTorchWall;
import com.endlesnights.torchslabsmod.TorchSlabsMod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TorchSlabsMod.MODID)
public class VanillaCompat implements ITorchSlabCompat
{
	public static final Block TORCH = null;
	public static final Block REDSTONE_TORCH = null;
	public static final Block LANTERN = null;
	
	public static Block wall_torch_slab = null;
	public static Block wall_lantern = null;
	
	public static Block end_rod_slab = null;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{

		event.getRegistry().register(new BlockTorchSlab(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(14).sound(SoundType.WOOD).lootFrom(Blocks.TORCH)).setRegistryName(new ResourceLocation(TorchSlabsMod.MODID, "torch")));
		event.getRegistry().register(new BlockRedstoneTorchSlab(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(7).sound(SoundType.WOOD).lootFrom(Blocks.REDSTONE_TORCH)).setRegistryName(new ResourceLocation(TorchSlabsMod.MODID, "redstone_torch")));
					
		event.getRegistry().register(new BlockLanternSlab(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5F).sound(SoundType.LANTERN).lightValue(15).func_226896_b_().lootFrom(Blocks.LANTERN)).setRegistryName(new ResourceLocation(TorchSlabsMod.MODID, "lantern")));
	
		wall_torch_slab = registerBlock(new BlockWallTorchSlab(Block.Properties.from(Blocks.WALL_TORCH).lootFrom(Blocks.TORCH)), "wall_torch_slab");
		wall_lantern = registerBlock(new BlockWallLanternSlab(Block.Properties.from(Blocks.LANTERN).lootFrom(Blocks.LANTERN)), "wall_lantern" );
		end_rod_slab = registerBlock(new BlockEndRodSlab(Block.Properties.from(Blocks.END_ROD)), "end_rod_slab");
		
		if (FMLEnvironment.dist == Dist.CLIENT)
        {
            RenderType transparentRenderType = RenderType.func_228641_d_();
            
            RenderTypeLookup.setRenderLayer(wall_torch_slab, transparentRenderType);
            RenderTypeLookup.setRenderLayer(wall_lantern, transparentRenderType);
        }
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandlerTorchSlab.registerPlaceEntry(Items.TORCH.getRegistryName(), TORCH);
		PlaceHandlerTorchWall.registerPlaceEntry(Items.TORCH.getRegistryName(), wall_torch_slab);
		
		PlaceHandlerTorchSlab.registerPlaceEntry(Items.REDSTONE_TORCH.getRegistryName(), REDSTONE_TORCH);
		
		PlaceHandlerLanternSlab.registerPlaceEntry(Items.LANTERN.getRegistryName(), LANTERN);
		PlaceHandlerLanternWall.registerPlaceEntry(Items.LANTERN.getRegistryName(), wall_lantern);
		
		PlaceHandlerEndRod.registerPlaceEntry(Items.END_ROD.getRegistryName(), end_rod_slab);
		
	}
	
    public static Block registerBlock(Block block, String name)
    {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }
}