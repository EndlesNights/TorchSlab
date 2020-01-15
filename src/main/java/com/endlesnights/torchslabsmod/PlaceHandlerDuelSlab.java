package com.endlesnights.torchslabsmod;

import java.util.Collection;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
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
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=TorchSlabsMod.MODID)
public class PlaceHandlerDuelSlab 
{
private static final HashMap<ResourceLocation,Block> PLACE_ENTRIES = new HashMap<>();
	
	@SubscribeEvent
	public static void onBlockEntityPlace(RightClickBlock event)
	{	
		ItemStack held = event.getItemStack();
		ResourceLocation rl = held.getItem().getRegistryName();

		if(PLACE_ENTRIES.containsKey(rl))
			placeLantern(event, held, PLACE_ENTRIES.get(rl));
	}

	private static void placeLantern(RightClickBlock event, ItemStack held, Block block)
	{		
		BlockPos pos = event.getPos();
		Direction face = event.getFace();
		BlockPos placeAt = pos.offset(face);
		World world = event.getWorld();
		
		if(face != Direction.UP && face != Direction.DOWN)
		{
			if(world.getBlockState(placeAt.offset(Direction.DOWN)).getProperties().contains(SlabBlock.TYPE) 
					&& world.getBlockState(placeAt.offset(Direction.DOWN)).get(SlabBlock.TYPE) == SlabType.BOTTOM
					&& (world.isAirBlock(placeAt) || world.getFluidState(placeAt).getFluid() == Fluids.WATER) )
			{
				face = Direction.UP;
				pos = placeAt.offset(Direction.DOWN);
			}
			else if(world.getBlockState(placeAt.offset(Direction.UP)).getProperties().contains(SlabBlock.TYPE) 
					&& world.getBlockState(placeAt.offset(Direction.UP)).get(SlabBlock.TYPE) == SlabType.TOP
					&& (world.isAirBlock(placeAt) || world.getFluidState(placeAt).getFluid() == Fluids.WATER) )
			{
				face = Direction.DOWN;
				pos = placeAt.offset(Direction.UP);
			}
			else 
			{
				return;
			}
		}
		
		
		if(world.getBlockState(pos).getProperties().contains(SlabBlock.TYPE)
				&& ((world.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.BOTTOM && face == Direction.UP)
				|| (world.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.TOP) && face == Direction.DOWN)
				&& (world.isAirBlock(placeAt) || world.getFluidState(placeAt).getFluid() == Fluids.WATER) )
		{			
			
			if(face == Direction.DOWN)
				world.setBlockState(placeAt, block.getDefaultState().with(BlockStateProperties.HANGING, true));
			else
				world.setBlockState(placeAt, block.getDefaultState());
			
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundType.LANTERN.getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			event.getPlayer().swingArm(event.getHand());

			if(!event.getPlayer().isCreative())
				held.shrink(1);
		}

	}

	public static void registerPlaceEntry(ResourceLocation itemName, Block torchSlab)
	{
		if(!PLACE_ENTRIES.containsKey(itemName))
			PLACE_ENTRIES.put(itemName, torchSlab);
	}

	public static Collection<Block> getPlaceEntryBlocks()
	{
		return PLACE_ENTRIES.values();
	}
}
