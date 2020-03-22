package com.endlesnights.torchslabsmod.blocks.buzzierbees.tileentity;

import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.blocks.buzzierbees.BlockScentedCandleSlab;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TorchSlabsMod.MODID)
public class ScentedCandleSlabTileEntity extends TileEntity implements ITickableTileEntity {

    public ScentedCandleSlabTileEntity()
    {
		super(BBTileEntities.SCENTED_CANDLE_SLAB.get());
	}

    @Override
    public void tick() {
        BlockState blockstate = this.world.getBlockState(this.pos);
        double d0 = (double)(blockstate.get(BlockScentedCandleSlab.CANDLES));
        boolean water = (blockstate.get(BlockScentedCandleSlab.WATERLOGGED));
        
        
        if (water != true) {
            for (LivingEntity entity : world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos).grow(d0))) {
         	   if (entity.getActivePotionEffect(((BlockScentedCandleSlab)blockstate.getBlock()).candleEffectInstance) == null || (entity.getActivePotionEffect(((BlockScentedCandleSlab)blockstate.getBlock()).candleEffectInstance).getDuration() <= 25))  {
         		   entity.addPotionEffect(new EffectInstance(((BlockScentedCandleSlab)blockstate.getBlock()).candleEffectInstance, ((BlockScentedCandleSlab)blockstate.getBlock()).duration, ((BlockScentedCandleSlab)blockstate.getBlock()).level, true, true)); 
         	   }
            }
        }
	}
}