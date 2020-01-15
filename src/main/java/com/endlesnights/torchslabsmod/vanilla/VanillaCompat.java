package com.endlesnights.torchslabsmod.vanilla;

import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.PlaceHandlerBottomSlab;
import com.endlesnights.torchslabsmod.PlaceHandlerDuelSlab;
import com.endlesnights.torchslabsmod.TorchSlabsMod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TorchSlabsMod.MODID)
public class VanillaCompat implements ITorchSlabCompat
{
	public static final Block TORCH = null;
	public static final Block REDSTONE_TORCH = null;
	public static final Block LANTERN = null;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new BlockTorchSlab(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(14).sound(SoundType.WOOD).lootFrom(Blocks.TORCH)).setRegistryName(new ResourceLocation(TorchSlabsMod.MODID, "torch")));
		event.getRegistry().register(new BlockRedstoneTorchSlab(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).lightValue(7).sound(SoundType.WOOD).lootFrom(Blocks.REDSTONE_TORCH)).setRegistryName(new ResourceLocation(TorchSlabsMod.MODID, "redstone_torch")));
											  
		event.getRegistry().register(new BlockLanternSlab(Block.Properties.create(Material.IRON).hardnessAndResistance(3.5F).sound(SoundType.LANTERN).lightValue(15).lootFrom(Blocks.LANTERN)).setRegistryName(new ResourceLocation(TorchSlabsMod.MODID, "lantern")));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandlerBottomSlab.registerPlaceEntry(Items.TORCH.getRegistryName(), TORCH);
		PlaceHandlerBottomSlab.registerPlaceEntry(Items.REDSTONE_TORCH.getRegistryName(), REDSTONE_TORCH);
		
		PlaceHandlerDuelSlab.registerPlaceEntry(Items.LANTERN.getRegistryName(), LANTERN);
	}
}