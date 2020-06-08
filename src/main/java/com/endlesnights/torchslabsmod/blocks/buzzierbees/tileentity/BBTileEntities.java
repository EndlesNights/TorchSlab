package com.endlesnights.torchslabsmod.blocks.buzzierbees.tileentity;

import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.blocks.buzzierbees.BuzzierBeesCompat;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BBTileEntities {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, TorchSlabsMod.MODID);
	
	public static final RegistryObject<TileEntityType<ScentedCandleSlabTileEntity>> SCENTED_CANDLE_SLAB = TILE_ENTITY_TYPES.register("scented_candle_slab",
			() -> TileEntityType.Builder.create(ScentedCandleSlabTileEntity::new,
					
					BuzzierBeesCompat.allium_scented_candle_slab,
					BuzzierBeesCompat.azure_bluet_scented_candle_slab,
					BuzzierBeesCompat.blue_orchid_scented_candle_slab,
					BuzzierBeesCompat.dandelion_scented_candle_slab,
					BuzzierBeesCompat.cornflower_scented_candle_slab,
					BuzzierBeesCompat.lily_of_the_valley_scented_candle_slab,
					BuzzierBeesCompat.oxeye_daisy_scented_candle_slab,
					BuzzierBeesCompat.poppy_scented_candle_slab,
					BuzzierBeesCompat.white_tulip_scented_candle_slab,
					BuzzierBeesCompat.orange_tulip_scented_candle_slab,
					BuzzierBeesCompat.pink_tulip_scented_candle_slab,
					BuzzierBeesCompat.red_tulip_scented_candle_slab,
					BuzzierBeesCompat.wither_rose_scented_candle_slab,
					
					BuzzierBeesCompat.cartwheel_scented_candle_slab,
					BuzzierBeesCompat.bluebell_scented_candle_slab,
					BuzzierBeesCompat.columbine_scented_candle_slab,
					BuzzierBeesCompat.jolyce_scented_candle_slab,
					BuzzierBeesCompat.pink_clover_scented_candle_slab,
					BuzzierBeesCompat.white_clover_scented_candle_slab,
					BuzzierBeesCompat.daybloom_scented_candle_slab,
					BuzzierBeesCompat.violet_scented_candle_slab
					).build(null));
	
	public static final RegistryObject<TileEntityType<ScentedCandlePadTileEntity>> SCENTED_CANDLE_PAD = TILE_ENTITY_TYPES.register("scented_candle_pad",
			() -> TileEntityType.Builder.create(ScentedCandlePadTileEntity::new,
					
					BuzzierBeesCompat.pad_allium_scented_candle,
					BuzzierBeesCompat.pad_azure_bluet_scented_candle,
					BuzzierBeesCompat.pad_blue_orchid_scented_candle,
					BuzzierBeesCompat.pad_dandelion_scented_candle,
					BuzzierBeesCompat.pad_cornflower_scented_candle,
					BuzzierBeesCompat.pad_lily_of_the_valley_scented_candle,
					BuzzierBeesCompat.pad_oxeye_daisy_scented_candle,
					BuzzierBeesCompat.pad_poppy_scented_candle,
					BuzzierBeesCompat.pad_white_tulip_scented_candle,
					BuzzierBeesCompat.pad_orange_tulip_scented_candle,
					BuzzierBeesCompat.pad_pink_tulip_scented_candle,
					BuzzierBeesCompat.pad_red_tulip_scented_candle,
					BuzzierBeesCompat.pad_wither_rose_scented_candle,
					
					BuzzierBeesCompat.pad_cartwheel_scented_candle,
					BuzzierBeesCompat.pad_bluebell_scented_candle,
					BuzzierBeesCompat.pad_columbine_scented_candle,
					BuzzierBeesCompat.pad_jolyce_scented_candle,
					BuzzierBeesCompat.pad_pink_clover_scented_candle,
					BuzzierBeesCompat.pad_white_clover_scented_candle,
					BuzzierBeesCompat.pad_daybloom_scented_candle,
					BuzzierBeesCompat.pad_violet_scented_candle
					).build(null));
}
