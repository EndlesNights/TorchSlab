package com.endlesnights.torchslabsmod.event;

import java.util.Collection;
import java.util.HashMap;

import com.endlesnights.torchslabsmod.TorchSlabsMod;
import com.endlesnights.torchslabsmod.blocks.vanilla.BlockWallTorchSlab;
import com.endlesnights.torchslabsmod.config.Config;
import com.endlesnights.torchslabsmod.config.TorchSlabConfig;

import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.loading.FMLPaths;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid=TorchSlabsMod.MODID)
public class PlaceHandlerLanternWall
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
		PlayerEntity playerIn = event.getPlayer();
		
		Config.loadConfig(Config.SERVER, FMLPaths.CONFIGDIR.get().resolve("torchslabmod-server.toml").toString());
		if(!TorchSlabConfig.wallLanternCheck.get()
				|| (!playerIn.isSteppingCarefully() && TorchSlabConfig.interactiveCheckList.get().contains(world.getBlockState(pos).getBlock().getRegistryName().toString())))
			return;
		
		if((face != Direction.UP && face != Direction.DOWN)
				&& Block.hasSolidSide(world.getBlockState(pos), world, pos, face)
				&& (world.isAirBlock(placeAt) || world.getFluidState(placeAt).getFluid() == Fluids.WATER || world.getFluidState(placeAt).getFluid() == Fluids.FLOWING_WATER) )
		{
			
			if(blockHalf(playerIn, pos, face) <= 0 && (!Block.hasSolidSide(world.getBlockState(placeAt.down()), world, placeAt.down(), face.getOpposite()))
					&& TorchSlabConfig.lowerLanternCheck.get())
			{
				world.setBlockState(placeAt, block.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, face).with(BlockWallTorchSlab.HALF, Half.BOTTOM));
			}
			else
			{
				world.setBlockState(placeAt, block.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, face).with(BlockWallTorchSlab.HALF, Half.TOP));
			}
			
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), block.getSoundType(world.getBlockState(pos)).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			event.getPlayer().swingArm(event.getHand());
			
			if(!event.getPlayer().isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}
		else if(((world.getBlockState(pos).getBlock() instanceof SlabBlock  && world.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.BOTTOM)
				|| (world.getBlockState(pos).getBlock() instanceof StairsBlock && world.getBlockState(pos).get(StairsBlock.HALF) == Half.BOTTOM )
				|| (world.getBlockState(pos).getBlock() instanceof FenceBlock && blockHalf(playerIn, pos, face) <= 0))
				&& (face != Direction.UP && face != Direction.DOWN)
				&& (world.isAirBlock(placeAt) || world.getFluidState(placeAt).getFluid() == Fluids.WATER || world.getFluidState(placeAt).getFluid() == Fluids.FLOWING_WATER)
				&& (!Block.hasSolidSide(world.getBlockState(placeAt.down()), world, placeAt.down(), face.getOpposite())) )
		{
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), block.getSoundType(world.getBlockState(pos)).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			event.getPlayer().swingArm(event.getHand());
			
			world.setBlockState(placeAt, block.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, face).with(BlockWallTorchSlab.HALF, Half.BOTTOM));
		
			if(!event.getPlayer().isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}
		else if(((world.getBlockState(pos).getBlock() instanceof SlabBlock && world.getBlockState(pos).get(SlabBlock.TYPE) == SlabType.TOP)
				|| (world.getBlockState(pos).getBlock() instanceof StairsBlock && world.getBlockState(pos).get(StairsBlock.HALF) == Half.TOP)
				|| (world.getBlockState(pos).getBlock() instanceof FenceBlock && blockHalf(playerIn, pos, face) >= 0))
				&& (face != Direction.UP && face != Direction.DOWN)
				&& (world.isAirBlock(placeAt) || world.getFluidState(placeAt).getFluid() == Fluids.WATER || world.getFluidState(placeAt).getFluid() == Fluids.FLOWING_WATER) )
		{

			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), block.getSoundType(world.getBlockState(pos)).getPlaceSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
			event.getPlayer().swingArm(event.getHand());
			
			world.setBlockState(placeAt, block.getDefaultState().with(WallTorchBlock.HORIZONTAL_FACING, face).with(BlockWallTorchSlab.HALF, Half.TOP));
		
			if(!event.getPlayer().isCreative())
				held.shrink(1);
			event.setCanceled(true);
		}

	}

	static double blockHalf(PlayerEntity playerIn, BlockPos pos, Direction face)
	{
		double angleX = Math.toRadians(playerIn.rotationYaw);
		double angleY = Math.toRadians(playerIn.rotationPitch);
		
		double sinYaw = Math.sin(angleX);
		double cosYaw = Math.cos(angleX);
		
		double sinPitch = Math.sin(angleY);
		double cosPitch = Math.cos(angleY);
		
		Vector3d directionAngle = new Vector3d (cosPitch * cosYaw, sinPitch, cosPitch * sinYaw);
		Double yOffset = playerIn.getEyePosition(1).y - (pos.getY() + 0.5);
		
		if(face == Direction.NORTH)
		{			
			double magnatude = (playerIn.getEyePosition(1).z - (pos.getZ()) ) / directionAngle.x;
			return magnatude * directionAngle.y + yOffset;
		}
		else if(face == Direction.SOUTH)
		{
			double magnatude = (playerIn.getEyePosition(1).z - (pos.getZ() +1) ) / directionAngle.x;
			return magnatude * directionAngle.y + yOffset;
		}
		else if(face == Direction.WEST)
		{
			double magnatude = (playerIn.getEyePosition(1).x - (pos.getX()) ) / -directionAngle.z;
			return magnatude * directionAngle.y + yOffset;
		}
		else if(face == Direction.EAST)
		{
			double magnatude = (playerIn.getEyePosition(1).x - (pos.getX() +1) ) / -directionAngle.z;
			return magnatude * directionAngle.y + yOffset;
		}
		
		return 0;
	}
	
	public static void registerPlaceEntry(ResourceLocation itemName, Block torchSlab)
	{
		if(!PLACE_ENTRIES.containsKey(itemName) && torchSlab != null)
			PLACE_ENTRIES.put(itemName, torchSlab);
	}

	public static Collection<Block> getPlaceEntryBlocks()
	{
		return PLACE_ENTRIES.values();
	}
}

