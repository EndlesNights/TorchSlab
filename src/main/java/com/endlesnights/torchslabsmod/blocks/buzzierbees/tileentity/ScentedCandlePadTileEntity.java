package com.endlesnights.torchslabsmod.blocks.buzzierbees.tileentity;

import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.blocks.buzzierbees.BlockPadScentedCandle;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TorchSlabsMod.MODID)
public class ScentedCandlePadTileEntity extends TileEntity implements ITickableTileEntity {

    public ScentedCandlePadTileEntity()
    {
		super(BBTileEntities.SCENTED_CANDLE_PAD.get());
	}

    @Override
    public void tick()
    {
	BlockState blockstate = this.world.getBlockState(this.pos);
	double d0 = (double)(blockstate.get(BlockPadScentedCandle.CANDLES));
   
		for (LivingEntity entity : world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos).grow(d0))) {
			if (entity.getActivePotionEffect(((BlockPadScentedCandle)blockstate.getBlock()).candleEffectInstance) == null || (entity.getActivePotionEffect(((BlockPadScentedCandle)blockstate.getBlock()).candleEffectInstance).getDuration() <= 25))  {
				entity.addPotionEffect(new EffectInstance(((BlockPadScentedCandle)blockstate.getBlock()).candleEffectInstance, ((BlockPadScentedCandle)blockstate.getBlock()).duration, ((BlockPadScentedCandle)blockstate.getBlock()).level, true, true)); 
			}
		}
        
	}
}