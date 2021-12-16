package com.endlesnights.torchslabsmod.blocks.vanilla;

import com.endlesnights.torchslabsmod.ITorchSlabCompat;
import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.event.PlaceHandlerCandleSlab;
import com.endlesnights.torchslabsmod.event.PlaceHandlerEndRod;
import com.endlesnights.torchslabsmod.event.PlaceHandlerLanternSlab;
import com.endlesnights.torchslabsmod.event.PlaceHandlerLanternWall;
import com.endlesnights.torchslabsmod.event.PlaceHandlerPadCandle;
import com.endlesnights.torchslabsmod.event.PlaceHandlerPadLights;
import com.endlesnights.torchslabsmod.event.PlaceHandlerTorchSlab;
import com.endlesnights.torchslabsmod.event.PlaceHandlerTorchWall;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.Items;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.ItemBlockRenderTypes; RIP?
//import net.minecraft.item.Items;
//import net.minecraft.particles.ParticleTypes;
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
	
	
	public static Block candle_slab = null;
	public static Block candle_black_slab = null;
	public static Block candle_blue_slab = null;
	public static Block candle_brown_slab = null;
	public static Block candle_cyan_slab = null;
	public static Block candle_gray_slab = null;
	public static Block candle_green_slab = null;
	public static Block candle_light_blue_slab = null;
	public static Block candle_light_gray_slab = null;
	public static Block candle_lime_slab = null;
	public static Block candle_magenta_slab = null;
	public static Block candle_orange_slab = null;
	public static Block candle_pink_slab = null;
	public static Block candle_purple_slab = null;
	public static Block candle_red_slab = null;
	public static Block candle_white_slab = null;
	public static Block candle_yellow_slab = null;

	public static Block pad_candle = null;
	public static Block pad_candle_black = null;
	public static Block pad_candle_blue = null;
	public static Block pad_candle_brown = null;
	public static Block pad_candle_cyan = null;
	public static Block pad_candle_gray = null;
	public static Block pad_candle_green = null;
	public static Block pad_candle_light_blue = null;
	public static Block pad_candle_light_gray = null;
	public static Block pad_candle_lime = null;
	public static Block pad_candle_magenta = null;
	public static Block pad_candle_orange = null;
	public static Block pad_candle_pink = null;
	public static Block pad_candle_purple = null;
	public static Block pad_candle_red = null;
	public static Block pad_candle_white = null;
	public static Block pad_candle_yellow = null;
	
	@SuppressWarnings("deprecation")
	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		torch = registerBlock(new BlockTorchSlab(Block.Properties.copy(Blocks.TORCH).dropsLike(Blocks.TORCH), ParticleTypes.FLAME, 't'), "torch");
		soul_torch = registerBlock(new BlockTorchSlab(Block.Properties.copy(Blocks.SOUL_TORCH).dropsLike(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME, 's'), "soul_torch");
					
		lantern = registerBlock(new BlockLanternSlab(Block.Properties.copy(Blocks.LANTERN).dropsLike(Blocks.LANTERN), 'l'), "lantern");
		soul_lantern = registerBlock(new BlockLanternSlab(Block.Properties.copy(Blocks.SOUL_LANTERN).dropsLike(Blocks.SOUL_LANTERN), 's'), "soul_lantern");
	
		wall_torch_slab = registerBlock(new BlockWallTorchSlab(Block.Properties.copy(Blocks.WALL_TORCH).dropsLike(Blocks.TORCH), ParticleTypes.FLAME, 't'), "wall_torch_slab");
		wall_soul_torch_slab = registerBlock(new BlockWallTorchSlab(Block.Properties.copy(Blocks.SOUL_WALL_TORCH).dropsLike(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME, 's'), "wall_soul_torch_slab");
		wall_lantern = registerBlock(new BlockWallLanternSlab(Block.Properties.copy(Blocks.LANTERN).dropsLike(Blocks.LANTERN), 'l'), "wall_lantern" );
		wall_soul_lantern = registerBlock(new BlockWallLanternSlab(Block.Properties.copy(Blocks.SOUL_LANTERN).dropsLike(Blocks.SOUL_LANTERN), 's'), "wall_soul_lantern" );
		
		end_rod_slab = registerBlock(new BlockEndRodSlab(Block.Properties.copy(Blocks.END_ROD)), "end_rod_slab");
		
		pad_torch = registerBlock(new BlockPadTorch(Block.Properties.copy(Blocks.TORCH), ParticleTypes.FLAME , 't'), "pad_torch");
		pad_soul_torch = registerBlock(new BlockPadTorch(Block.Properties.copy(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME , 's'), "pad_soul_torch");
		
		
//		pad_lantern = registerBlock(new BlockPadLantern(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.LANTERN).func_235838_a_((p_235454_0_) -> {return 15; }).notSolid(), 'l'), "pad_lantern");

		pad_lantern = registerBlock(new BlockPadLantern((BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.LANTERN).noOcclusion().lightLevel((p_50878_) -> { return 15; })), 'l'), "pad_lantern");
		pad_soul_lantern = registerBlock(new BlockPadLantern((BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.LANTERN).noOcclusion().lightLevel((p_50878_) -> { return 10; })), 's'), "pad_soul_lantern");

//		pad_soul_lantern = registerBlock(new BlockPadLantern(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.LANTERN).func_235838_a_((p_235454_0_) -> {return 15; }).notSolid(), 's'), "pad_soul_lantern");
		
		//chain_slab = registerBlock(new BlockChainSlab(Block.Properties.from(Blocks.field_235341_dI_).lootFrom(Blocks.field_235341_dI_)),"chain_slab");
		
		candle_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.CANDLE), Items.CANDLE), "candle_slab");
		candle_black_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.BLACK_CANDLE), Items.BLACK_CANDLE), "candle_black_slab");
		candle_blue_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.BLUE_CANDLE), Items.BLUE_CANDLE), "candle_blue_slab");
		candle_brown_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.BROWN_CANDLE), Items.BROWN_CANDLE), "candle_brown_slab");
		candle_cyan_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.CYAN_CANDLE), Items.CYAN_CANDLE), "candle_cyan_slab");
		candle_gray_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.GRAY_CANDLE), Items.GRAY_CANDLE), "candle_gray_slab");
		candle_green_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.GREEN_CANDLE), Items.GREEN_CANDLE), "candle_green_slab");
		candle_light_blue_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.LIGHT_BLUE_CANDLE), Items.LIGHT_BLUE_CANDLE), "candle_light_blue_slab");
		candle_light_gray_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.LIGHT_GRAY_CANDLE), Items.LIGHT_GRAY_CANDLE), "candle_light_gray_slab");
		candle_lime_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.LIME_CANDLE), Items.LIME_CANDLE), "candle_lime_slab");
		candle_magenta_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.MAGENTA_CANDLE), Items.MAGENTA_CANDLE), "candle_magenta_slab");
		candle_orange_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.ORANGE_CANDLE), Items.ORANGE_CANDLE), "candle_orange_slab");
		candle_pink_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.PINK_CANDLE), Items.PINK_CANDLE), "candle_pink_slab");
		candle_purple_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.PURPLE_CANDLE), Items.PURPLE_CANDLE), "candle_purple_slab");
		candle_red_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.RED_CANDLE), Items.RED_CANDLE), "candle_red_slab");
		candle_white_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.WHITE_CANDLE), Items.WHITE_CANDLE), "candle_white_slab");
		candle_yellow_slab = registerBlock(new BlockCandleSlab(Block.Properties.copy(Blocks.YELLOW_CANDLE), Items.YELLOW_CANDLE), "candle_yellow_slab");

		pad_candle = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.CANDLE), Items.CANDLE), "pad_candle");
		pad_candle_black = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.BLACK_CANDLE), Items.BLACK_CANDLE), "pad_candle_black");
		pad_candle_blue = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.BLUE_CANDLE), Items.BLUE_CANDLE), "pad_candle_blue");
		pad_candle_brown = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.BROWN_CANDLE), Items.BROWN_CANDLE), "pad_candle_brown");
		pad_candle_cyan = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.CYAN_CANDLE), Items.CYAN_CANDLE), "pad_candle_cyan");
		pad_candle_gray = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.GRAY_CANDLE), Items.GRAY_CANDLE), "pad_candle_gray");
		pad_candle_green = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.GREEN_CANDLE), Items.GREEN_CANDLE), "pad_candle_green");
		pad_candle_light_blue = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.LIGHT_BLUE_CANDLE), Items.LIGHT_BLUE_CANDLE), "pad_candle_light_blue");
		pad_candle_light_gray = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.LIGHT_GRAY_CANDLE), Items.LIGHT_GRAY_CANDLE), "pad_candle_light_gray");
		pad_candle_lime = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.LIME_CANDLE), Items.LIME_CANDLE), "pad_candle_lime");
		pad_candle_magenta = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.MAGENTA_CANDLE), Items.MAGENTA_CANDLE), "pad_candle_magenta");
		pad_candle_orange = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.ORANGE_CANDLE), Items.ORANGE_CANDLE), "pad_candle_orange");
		pad_candle_pink = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.PINK_CANDLE), Items.PINK_CANDLE), "pad_candle_pink");
		pad_candle_purple = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.PURPLE_CANDLE), Items.PURPLE_CANDLE), "pad_candle_purple");
		pad_candle_red = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.RED_CANDLE), Items.RED_CANDLE), "pad_candle_red");
		pad_candle_white = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.WHITE_CANDLE), Items.WHITE_CANDLE), "pad_candle_white");
		pad_candle_yellow = registerBlock(new BlockPadCandle(Block.Properties.copy(Blocks.YELLOW_CANDLE), Items.YELLOW_CANDLE), "pad_candle_yellow");
				
		if (FMLEnvironment.dist == Dist.CLIENT)
        {
			RenderType transparentRenderType = RenderType.cutoutMipped();
			ItemBlockRenderTypes.setRenderLayer(torch, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(lantern, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(wall_torch_slab, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(wall_lantern, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_torch, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_lantern, transparentRenderType);
			
			ItemBlockRenderTypes.setRenderLayer(soul_torch, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(soul_lantern, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(wall_soul_torch_slab, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(wall_soul_lantern, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_soul_torch, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_soul_lantern, transparentRenderType);
			
			ItemBlockRenderTypes.setRenderLayer(pad_candle, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_black, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_blue, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_brown, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_cyan, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_gray, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_green, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_light_blue, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_light_gray, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_lime, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_magenta, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_orange, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_pink, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_purple, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_red, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_white, transparentRenderType);
			ItemBlockRenderTypes.setRenderLayer(pad_candle_yellow, transparentRenderType);
//			ItemBlockRenderTypes.setRenderLayer(chain_slab, transparentRenderType);
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
		
		//PlaceHandlerChainSlab.registerPlaceEntry(Items.field_234729_dO_.getRegistryName(), chain_slab);
		PlaceHandlerEndRod.registerPlaceEntry(Items.END_ROD.getRegistryName(), end_rod_slab);
		
		
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.CANDLE.getRegistryName(), candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.BLACK_CANDLE.getRegistryName(), candle_black_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.BLUE_CANDLE.getRegistryName(), candle_blue_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.BROWN_CANDLE.getRegistryName(), candle_brown_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.CYAN_CANDLE.getRegistryName(), candle_cyan_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.GRAY_CANDLE.getRegistryName(), candle_gray_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.GREEN_CANDLE.getRegistryName(), candle_green_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.LIGHT_BLUE_CANDLE.getRegistryName(), candle_light_blue_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.LIGHT_GRAY_CANDLE.getRegistryName(), candle_light_gray_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.LIME_CANDLE.getRegistryName(), candle_lime_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.MAGENTA_CANDLE.getRegistryName(), candle_magenta_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.ORANGE_CANDLE.getRegistryName(), candle_orange_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.PINK_CANDLE.getRegistryName(), candle_pink_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.PURPLE_CANDLE.getRegistryName(), candle_purple_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.RED_CANDLE.getRegistryName(), candle_red_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.WHITE_CANDLE.getRegistryName(), candle_white_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(Items.YELLOW_CANDLE.getRegistryName(), candle_yellow_slab);

		
		PlaceHandlerPadCandle.registerPlaceEntry(Items.CANDLE.getRegistryName(), pad_candle);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.BLACK_CANDLE.getRegistryName(), pad_candle_black);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.BLUE_CANDLE.getRegistryName(), pad_candle_blue);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.BROWN_CANDLE.getRegistryName(), pad_candle_brown);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.CYAN_CANDLE.getRegistryName(), pad_candle_cyan);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.GRAY_CANDLE.getRegistryName(), pad_candle_gray);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.GREEN_CANDLE.getRegistryName(), pad_candle_green);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.LIGHT_BLUE_CANDLE.getRegistryName(), pad_candle_light_blue);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.LIGHT_GRAY_CANDLE.getRegistryName(), pad_candle_light_gray);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.LIME_CANDLE.getRegistryName(), pad_candle_lime);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.MAGENTA_CANDLE.getRegistryName(), pad_candle_magenta);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.ORANGE_CANDLE.getRegistryName(), pad_candle_orange);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.PINK_CANDLE.getRegistryName(), pad_candle_pink);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.PURPLE_CANDLE.getRegistryName(), pad_candle_purple);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.RED_CANDLE.getRegistryName(), pad_candle_red);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.WHITE_CANDLE.getRegistryName(), pad_candle_white);
		PlaceHandlerPadCandle.registerPlaceEntry(Items.YELLOW_CANDLE.getRegistryName(), pad_candle_yellow);		
	}
	
    public static Block registerBlock(Block block, String name)
    {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }
}