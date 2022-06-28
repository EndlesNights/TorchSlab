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
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class VanillaCompat implements ITorchSlabCompat
{
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TorchSlabsMod.MODID);

	public static RegistryObject<Block> torch = null;
	public static RegistryObject<Block> soul_torch = null;
	public static RegistryObject<Block> lantern = null;
	public static RegistryObject<Block> soul_lantern = null;

	public static RegistryObject<Block> chain_slab = null;

	public static RegistryObject<Block> wall_torch_slab = null;
	public static RegistryObject<Block> wall_soul_torch_slab = null;
	public static RegistryObject<Block> wall_lantern = null;
	public static RegistryObject<Block> wall_soul_lantern = null;

	public static RegistryObject<Block> end_rod_slab = null;

	public static RegistryObject<Block> pad_torch = null;
	public static RegistryObject<Block> pad_soul_torch = null;
	public static RegistryObject<Block> pad_lantern = null;
	public static RegistryObject<Block> pad_soul_lantern = null;


	public static RegistryObject<Block> candle_slab = null;
	public static RegistryObject<Block> candle_black_slab = null;
	public static RegistryObject<Block> candle_blue_slab = null;
	public static RegistryObject<Block> candle_brown_slab = null;
	public static RegistryObject<Block> candle_cyan_slab = null;
	public static RegistryObject<Block> candle_gray_slab = null;
	public static RegistryObject<Block> candle_green_slab = null;
	public static RegistryObject<Block> candle_light_blue_slab = null;
	public static RegistryObject<Block> candle_light_gray_slab = null;
	public static RegistryObject<Block> candle_lime_slab = null;
	public static RegistryObject<Block> candle_magenta_slab = null;
	public static RegistryObject<Block> candle_orange_slab = null;
	public static RegistryObject<Block> candle_pink_slab = null;
	public static RegistryObject<Block> candle_purple_slab = null;
	public static RegistryObject<Block> candle_red_slab = null;
	public static RegistryObject<Block> candle_white_slab = null;
	public static RegistryObject<Block> candle_yellow_slab = null;

	public static RegistryObject<Block> pad_candle = null;
	public static RegistryObject<Block> pad_candle_black = null;
	public static RegistryObject<Block> pad_candle_blue = null;
	public static RegistryObject<Block> pad_candle_brown = null;
	public static RegistryObject<Block> pad_candle_cyan = null;
	public static RegistryObject<Block> pad_candle_gray = null;
	public static RegistryObject<Block> pad_candle_green = null;
	public static RegistryObject<Block> pad_candle_light_blue = null;
	public static RegistryObject<Block> pad_candle_light_gray = null;
	public static RegistryObject<Block> pad_candle_lime = null;
	public static RegistryObject<Block> pad_candle_magenta = null;
	public static RegistryObject<Block> pad_candle_orange = null;
	public static RegistryObject<Block> pad_candle_pink = null;
	public static RegistryObject<Block> pad_candle_purple = null;
	public static RegistryObject<Block> pad_candle_red = null;
	public static RegistryObject<Block> pad_candle_white = null;
	public static RegistryObject<Block> pad_candle_yellow = null;

	@SuppressWarnings("deprecation")
	@Override
	public void registerBlocks(IEventBus bus)
	{
		torch = registerBlock("torch", () -> new BlockTorchSlab(Block.Properties.copy(Blocks.TORCH).dropsLike(Blocks.TORCH), ParticleTypes.FLAME, 't'));
		soul_torch = registerBlock("soul_torch", () -> new BlockTorchSlab(Block.Properties.copy(Blocks.SOUL_TORCH).dropsLike(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME, 's'));

		lantern = registerBlock("lantern", () -> new BlockLanternSlab(Block.Properties.copy(Blocks.LANTERN).dropsLike(Blocks.LANTERN), 'l'));
		soul_lantern = registerBlock("soul_lantern", () -> new BlockLanternSlab(Block.Properties.copy(Blocks.SOUL_LANTERN).dropsLike(Blocks.SOUL_LANTERN), 's'));

		wall_torch_slab = registerBlock("wall_torch_slab", () -> new BlockWallTorchSlab(Block.Properties.copy(Blocks.WALL_TORCH).dropsLike(Blocks.TORCH), ParticleTypes.FLAME, 't'));
		wall_soul_torch_slab = registerBlock("wall_soul_torch_slab", () -> new BlockWallTorchSlab(Block.Properties.copy(Blocks.SOUL_WALL_TORCH).dropsLike(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME, 's'));
		wall_lantern = registerBlock("wall_lantern", () -> new BlockWallLanternSlab(Block.Properties.copy(Blocks.LANTERN).dropsLike(Blocks.LANTERN), 'l'));
		wall_soul_lantern = registerBlock("wall_soul_lantern", () -> new BlockWallLanternSlab(Block.Properties.copy(Blocks.SOUL_LANTERN).dropsLike(Blocks.SOUL_LANTERN), 's'));

		end_rod_slab = registerBlock("end_rod_slab", () -> new BlockEndRodSlab(Block.Properties.copy(Blocks.END_ROD)));

		pad_torch = registerBlock("pad_torch", () -> new BlockPadTorch(Block.Properties.copy(Blocks.TORCH), ParticleTypes.FLAME , 't'));
		pad_soul_torch = registerBlock("pad_soul_torch", () -> new BlockPadTorch(Block.Properties.copy(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME , 's'));


//		pad_lantern = registerBlock(() -> new BlockPadLantern(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.LANTERN).func_235838_a_((p_235454_0_) -> {return 15; }).notSolid(), 'l'), "pad_lantern");

		pad_lantern = registerBlock("pad_lantern", () -> new BlockPadLantern((BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.LANTERN).noOcclusion().lightLevel((p_50878_) -> { return 15; })), 'l'));
		pad_soul_lantern = registerBlock("pad_soul_lantern", () -> new BlockPadLantern((BlockBehaviour.Properties.of(Material.PLANT).instabreak().sound(SoundType.LANTERN).noOcclusion().lightLevel((p_50878_) -> { return 10; })), 's'));

//		pad_soul_lantern = registerBlock(() -> new BlockPadLantern(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).sound(SoundType.LANTERN).func_235838_a_((p_235454_0_) -> {return 15; }).notSolid(), 's'), "pad_soul_lantern");

		//chain_slab = registerBlock(() -> new BlockChainSlab(Block.Properties.from(Blocks.field_235341_dI_).lootFrom(Blocks.field_235341_dI_)),"chain_slab");

		candle_slab = registerBlock("candle_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.CANDLE), Items.CANDLE));
		candle_black_slab = registerBlock("candle_black_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.BLACK_CANDLE), Items.BLACK_CANDLE));
		candle_blue_slab = registerBlock("candle_blue_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.BLUE_CANDLE), Items.BLUE_CANDLE));
		candle_brown_slab = registerBlock("candle_brown_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.BROWN_CANDLE), Items.BROWN_CANDLE));
		candle_cyan_slab = registerBlock("candle_cyan_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.CYAN_CANDLE), Items.CYAN_CANDLE));
		candle_gray_slab = registerBlock("candle_gray_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.GRAY_CANDLE), Items.GRAY_CANDLE));
		candle_green_slab = registerBlock("candle_green_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.GREEN_CANDLE), Items.GREEN_CANDLE));
		candle_light_blue_slab = registerBlock("candle_light_blue_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.LIGHT_BLUE_CANDLE), Items.LIGHT_BLUE_CANDLE));
		candle_light_gray_slab = registerBlock("candle_light_gray_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.LIGHT_GRAY_CANDLE), Items.LIGHT_GRAY_CANDLE));
		candle_lime_slab = registerBlock("candle_lime_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.LIME_CANDLE), Items.LIME_CANDLE));
		candle_magenta_slab = registerBlock("candle_magenta_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.MAGENTA_CANDLE), Items.MAGENTA_CANDLE));
		candle_orange_slab = registerBlock("candle_orange_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.ORANGE_CANDLE), Items.ORANGE_CANDLE));
		candle_pink_slab = registerBlock("candle_pink_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.PINK_CANDLE), Items.PINK_CANDLE));
		candle_purple_slab = registerBlock("candle_purple_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.PURPLE_CANDLE), Items.PURPLE_CANDLE));
		candle_red_slab = registerBlock("candle_red_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.RED_CANDLE), Items.RED_CANDLE));
		candle_white_slab = registerBlock("candle_white_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.WHITE_CANDLE), Items.WHITE_CANDLE));
		candle_yellow_slab = registerBlock("candle_yellow_slab", () -> new BlockCandleSlab(Block.Properties.copy(Blocks.YELLOW_CANDLE), Items.YELLOW_CANDLE));

		pad_candle = registerBlock("pad_candle", () -> new BlockPadCandle(Block.Properties.copy(Blocks.CANDLE), Items.CANDLE));
		pad_candle_black = registerBlock("pad_candle_black", () -> new BlockPadCandle(Block.Properties.copy(Blocks.BLACK_CANDLE), Items.BLACK_CANDLE));
		pad_candle_blue = registerBlock("pad_candle_blue", () -> new BlockPadCandle(Block.Properties.copy(Blocks.BLUE_CANDLE), Items.BLUE_CANDLE));
		pad_candle_brown = registerBlock("pad_candle_brown", () -> new BlockPadCandle(Block.Properties.copy(Blocks.BROWN_CANDLE), Items.BROWN_CANDLE));
		pad_candle_cyan = registerBlock("pad_candle_cyan", () -> new BlockPadCandle(Block.Properties.copy(Blocks.CYAN_CANDLE), Items.CYAN_CANDLE));
		pad_candle_gray = registerBlock("pad_candle_gray", () -> new BlockPadCandle(Block.Properties.copy(Blocks.GRAY_CANDLE), Items.GRAY_CANDLE));
		pad_candle_green = registerBlock("pad_candle_green", () -> new BlockPadCandle(Block.Properties.copy(Blocks.GREEN_CANDLE), Items.GREEN_CANDLE));
		pad_candle_light_blue = registerBlock("pad_candle_light_blue", () -> new BlockPadCandle(Block.Properties.copy(Blocks.LIGHT_BLUE_CANDLE), Items.LIGHT_BLUE_CANDLE));
		pad_candle_light_gray = registerBlock("pad_candle_light_gray", () -> new BlockPadCandle(Block.Properties.copy(Blocks.LIGHT_GRAY_CANDLE), Items.LIGHT_GRAY_CANDLE));
		pad_candle_lime = registerBlock("pad_candle_lime", () -> new BlockPadCandle(Block.Properties.copy(Blocks.LIME_CANDLE), Items.LIME_CANDLE));
		pad_candle_magenta = registerBlock("pad_candle_magenta", () -> new BlockPadCandle(Block.Properties.copy(Blocks.MAGENTA_CANDLE), Items.MAGENTA_CANDLE));
		pad_candle_orange = registerBlock("pad_candle_orange", () -> new BlockPadCandle(Block.Properties.copy(Blocks.ORANGE_CANDLE), Items.ORANGE_CANDLE));
		pad_candle_pink = registerBlock("pad_candle_pink", () -> new BlockPadCandle(Block.Properties.copy(Blocks.PINK_CANDLE), Items.PINK_CANDLE));
		pad_candle_purple = registerBlock("pad_candle_purple", () -> new BlockPadCandle(Block.Properties.copy(Blocks.PURPLE_CANDLE), Items.PURPLE_CANDLE));
		pad_candle_red = registerBlock("pad_candle_red", () -> new BlockPadCandle(Block.Properties.copy(Blocks.RED_CANDLE), Items.RED_CANDLE));
		pad_candle_white = registerBlock("pad_candle_white", () -> new BlockPadCandle(Block.Properties.copy(Blocks.WHITE_CANDLE), Items.WHITE_CANDLE));
		pad_candle_yellow = registerBlock("pad_candle_yellow", () -> new BlockPadCandle(Block.Properties.copy(Blocks.YELLOW_CANDLE), Items.YELLOW_CANDLE));

		BLOCKS.register(bus);
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandlerTorchSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.TORCH), torch);
		PlaceHandlerTorchWall.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.TORCH), wall_torch_slab);
		PlaceHandlerTorchSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.SOUL_TORCH), soul_torch);
		PlaceHandlerTorchWall.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.SOUL_TORCH), wall_soul_torch_slab);

		PlaceHandlerLanternSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.LANTERN), lantern);
		PlaceHandlerLanternWall.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.LANTERN), wall_lantern);
		PlaceHandlerLanternSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.SOUL_LANTERN), soul_lantern);
		PlaceHandlerLanternWall.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.SOUL_LANTERN), wall_soul_lantern);

		PlaceHandlerPadLights.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.TORCH), pad_torch);
		PlaceHandlerPadLights.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.SOUL_TORCH), pad_soul_torch);
		PlaceHandlerPadLights.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.LANTERN), pad_lantern);
		PlaceHandlerPadLights.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.SOUL_LANTERN), pad_soul_lantern);

		//PlaceHandlerChainSlab.registerPlaceEntry(Items.field_234729_dO_.getRegistryName(), chain_slab);
		PlaceHandlerEndRod.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.END_ROD), end_rod_slab);


		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.CANDLE), candle_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.BLACK_CANDLE), candle_black_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.BLUE_CANDLE), candle_blue_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.BROWN_CANDLE), candle_brown_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.CYAN_CANDLE), candle_cyan_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.GRAY_CANDLE), candle_gray_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.GREEN_CANDLE), candle_green_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.LIGHT_BLUE_CANDLE), candle_light_blue_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.LIGHT_GRAY_CANDLE), candle_light_gray_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.LIME_CANDLE), candle_lime_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.MAGENTA_CANDLE), candle_magenta_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.ORANGE_CANDLE), candle_orange_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.PINK_CANDLE), candle_pink_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.PURPLE_CANDLE), candle_purple_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.RED_CANDLE), candle_red_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.WHITE_CANDLE), candle_white_slab);
		PlaceHandlerCandleSlab.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.YELLOW_CANDLE), candle_yellow_slab);


		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.CANDLE), pad_candle);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.BLACK_CANDLE), pad_candle_black);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.BLUE_CANDLE), pad_candle_blue);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.BROWN_CANDLE), pad_candle_brown);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.CYAN_CANDLE), pad_candle_cyan);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.GRAY_CANDLE), pad_candle_gray);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.GREEN_CANDLE), pad_candle_green);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.LIGHT_BLUE_CANDLE), pad_candle_light_blue);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.LIGHT_GRAY_CANDLE), pad_candle_light_gray);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.LIME_CANDLE), pad_candle_lime);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.MAGENTA_CANDLE), pad_candle_magenta);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.ORANGE_CANDLE), pad_candle_orange);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.PINK_CANDLE), pad_candle_pink);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.PURPLE_CANDLE), pad_candle_purple);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.RED_CANDLE), pad_candle_red);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.WHITE_CANDLE), pad_candle_white);
		PlaceHandlerPadCandle.registerPlaceEntry(ForgeRegistries.ITEMS.getKey(Items.YELLOW_CANDLE), pad_candle_yellow);
	}

	@Override
	public void registerRenderTypes()
	{
		RenderType transparentRenderType = RenderType.cutoutMipped();
		ItemBlockRenderTypes.setRenderLayer(torch.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(lantern.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(wall_torch_slab.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(wall_lantern.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_torch.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_lantern.get(), transparentRenderType);

		ItemBlockRenderTypes.setRenderLayer(soul_torch.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(soul_lantern.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(wall_soul_torch_slab.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(wall_soul_lantern.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_soul_torch.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_soul_lantern.get(), transparentRenderType);

		ItemBlockRenderTypes.setRenderLayer(pad_candle.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_black.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_blue.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_brown.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_cyan.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_gray.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_green.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_light_blue.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_light_gray.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_lime.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_magenta.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_orange.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_pink.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_purple.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_red.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_white.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(pad_candle_yellow.get(), transparentRenderType);
	}

	public static RegistryObject<Block> registerBlock(String name, Supplier<Block> blockSupplier)
	{
		return BLOCKS.register(name, blockSupplier);
	}
}
