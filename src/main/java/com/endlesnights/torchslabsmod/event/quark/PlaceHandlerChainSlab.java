package com.endlesnights.torchslabsmod.event.quark;

import java.util.Collection;
import java.util.HashMap;

import com.endlesnights.naturalslabsmod.blocks.FenceSlabBlock;
import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.blocks.quark.BlockChainSlab;
import com.endlesnights.torchslabsmod.blocks.quark.BlockChainSlab.ChainType;

import net.minecraft.block.Block;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SlabBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid=TorchSlabsMod.MODID)
public class PlaceHandlerChainSlab
{
	private static final HashMap<ResourceLocation,Block> PLACE_ENTRIES = new HashMap<>();
	
	@SubscribeEvent
	public static void onBlockEntityPlace(RightClickBlock event)
	{	
		ItemStack held = event.getItemStack();
		ResourceLocation rl = held.getItem().getRegistryName();

		if(PLACE_ENTRIES.containsKey(rl))
			placeChain(event, held, PLACE_ENTRIES.get(rl));
	}

	private static void placeChain(RightClickBlock event, ItemStack held, Block block)
	{		
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		BlockPos placeAt = pos.offset(face);
		World world = event.getWorld();		

		if(
				face == Direction.DOWN 
				&& ((world.getBlockState(pos).getBlock() instanceof SlabBlock && world.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.TOP)
				|| (ModList.get().isLoaded("naturalslabsmod") && world.getBlockState(pos).getBlock() instanceof FenceSlabBlock)
				|| (world.getBlockState(pos).getBlock() instanceof BlockChainSlab))
				&& (world.isAirBlock(placeAt) || world.getFluidState(placeAt).getFluid() == Fluids.WATER || world.getFluidState(placeAt).getFluid() == Fluids.FLOWING_WATER) )
		{	
			
			world.setBlockState(placeAt, block.getDefaultState()
					.with(BlockStateProperties.WATERLOGGED, (world.getFluidState(placeAt).getFluid() == Fluids.WATER))
					.with(BlockChainSlab.TYPE, (world.getBlockState(pos).getBlock() instanceof SlabBlock ? ChainType.TOP : ChainType.BOTTOM)));


			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), block.getSoundType(world.getBlockState(pos)).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			event.getPlayer().swingArm(event.getHand());
			
			if(!event.getPlayer().isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}
	}
	
	public static void registerPlaceEntry(ResourceLocation itemName, Block chainSlab)
	{
		if(!PLACE_ENTRIES.containsKey(itemName) && chainSlab != null)
			PLACE_ENTRIES.put(itemName, chainSlab);
	}

	public static Collection<Block> getPlaceEntryBlocks()
	{
		return PLACE_ENTRIES.values();
	}
}