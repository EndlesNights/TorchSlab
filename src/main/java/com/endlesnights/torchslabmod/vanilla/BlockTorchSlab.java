package com.endlesnights.torchslabmod.vanilla;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;


import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.BlockEndRod;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockVine;
import net.minecraft.block.BlockWallSign;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTorchSlab extends BlockTorch
{

	public static final PropertyEnum<BlockTorchSlab.EnumBlockHalf> HALF = PropertyEnum.<BlockTorchSlab.EnumBlockHalf>create("half", BlockTorchSlab.EnumBlockHalf.class);
    public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>()
    {
        public boolean apply(@Nullable EnumFacing p_apply_1_)
        {
            return p_apply_1_ != EnumFacing.DOWN;
        }
    });
    
	protected static final AxisAlignedBB STANDING_AABB = new AxisAlignedBB(0.4000000059604645D, -0.5D, 0.4000000059604645D, 0.6000000238418579D, 0.2000000238418579D, 0.6000000238418579D);
   
	protected static final AxisAlignedBB TORCH_BOTTOM_NORTH_AABB = new AxisAlignedBB(0.3499999940395355D, 0.20000000298023224D, 0.699999988079071D, 0.6499999761581421D, 0.800000011920929D, 1.0D);
    protected static final AxisAlignedBB TORCH_BOTTOM_SOUTH_AABB = new AxisAlignedBB(0.3499999940395355D, 0.20000000298023224D, 0.0D, 0.6499999761581421D, 0.800000011920929D, 0.30000001192092896D);
    protected static final AxisAlignedBB TORCH_BOTTOM_WEST_AABB = new AxisAlignedBB(0.699999988079071D, 0.20000000298023224D, 0.3499999940395355D, 1.0D, 0.800000011920929D, 0.6499999761581421D);
    protected static final AxisAlignedBB TORCH_BOTTOM_EAST_AABB = new AxisAlignedBB(0.0D, 0.20000000298023224D, 0.3499999940395355D, 0.30000001192092896D, 0.800000011920929D, 0.6499999761581421D);
    
    protected static final AxisAlignedBB TORCH_TOP_NORTH_AABB = new AxisAlignedBB(0.3499999940395355D, 0.70000000298023224D, 0.699999988079071D, 0.6499999761581421D, 1.300000011920929D, 1.0D);
    protected static final AxisAlignedBB TORCH_TOP_SOUTH_AABB = new AxisAlignedBB(0.3499999940395355D, 0.70000000298023224D, 0.0D, 0.6499999761581421D, 1.300000011920929D, 0.30000001192092896D);
    protected static final AxisAlignedBB TORCH_TOP_WEST_AABB = new AxisAlignedBB(0.699999988079071D, 0.70000000298023224D, 0.3499999940395355D, 1.0D, 1.300000011920929D, 0.6499999761581421D);
    protected static final AxisAlignedBB TORCH_TOP_EAST_AABB = new AxisAlignedBB(0.0D, 0.70000000298023224D, 0.3499999940395355D, 0.30000001192092896D, 1.300000011920929D, 0.6499999761581421D);
	
    public BlockTorchSlab()
	{		
    	//super(Material.CIRCUITS);
    	this.setTickRandomly(true);
    	this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP).withProperty(HALF, BlockTorchSlab.EnumBlockHalf.BOTTOM));
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		this.setHardness(0.0F);
		this.setLightLevel(0.9375F);
		this.setSoundType(SoundType.WOOD);        
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		if(state.getValue(HALF) == BlockTorchSlab.EnumBlockHalf.BOTTOM)
		{
			switch ((EnumFacing)state.getValue(FACING))
			{
	        	case EAST:
	                return TORCH_BOTTOM_EAST_AABB;
	            case WEST:
	                return TORCH_BOTTOM_WEST_AABB;
	            case SOUTH:
	                return TORCH_BOTTOM_SOUTH_AABB;
	            case NORTH:
	                return TORCH_BOTTOM_NORTH_AABB;
	            default:
	                return STANDING_AABB;
	        }
		}
		else
		{
			switch ((EnumFacing)state.getValue(FACING))
			{
	        	case EAST:
	                return TORCH_TOP_EAST_AABB;
	            case WEST:
	                return TORCH_TOP_WEST_AABB;
	            case SOUTH:
	                return TORCH_TOP_SOUTH_AABB;
	            case NORTH:
	                return TORCH_TOP_NORTH_AABB;
	            default:
	                return STANDING_AABB;
	        }
		}

    }
	
	@Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
		//return true;
        for (EnumFacing enumfacing : FACING.getAllowedValues())
        {
            if (this.canPlaceAt(worldIn, pos, enumfacing))
            {
                return true;
            }
        }

        return false;
    }	
	
    public static boolean canPlaceAt(World worldIn, BlockPos pos, EnumFacing facing)
    {
    	return true;
    }
	
	private static boolean canPlaceOn(World worldIn, BlockPos pos)
	{
		return true;
	}
    
	protected boolean checkForDrop(World world, BlockPos pos, IBlockState state)
	{
		return true;
	}
	
	static boolean validTop(IBlockState state, IBlockState torchState)
	{
		if(
				state.getBlock() instanceof BlockAir
				|| state.getBlock() instanceof BlockFluidBase
				||(state.getBlock() instanceof BlockSlab && state.getProperties().containsValue(BlockSlab.EnumBlockHalf.TOP))
				|| (state.getBlock() instanceof BlockStairs 
						&& state.getProperties().containsValue(BlockStairs.EnumHalf.TOP)
						&& state.getProperties().containsValue(BlockHorizontal.FACING) == torchState.getProperties().containsValue(BlockHorizontal.FACING))
				|| state.getBlock() instanceof BlockEndRod
				|| state.getBlock() instanceof BlockTorch
				|| state.getBlock() instanceof BlockWallSign
				|| state.getBlock() instanceof BlockLadder
				|| state.getBlock() instanceof BlockWeb
				|| state.getBlock() instanceof BlockVine
				|| state.getBlock() instanceof BlockRedstoneTorch
				|| state.getBlock() instanceof BlockButtonStone
				|| state.getBlock() instanceof BlockButtonWood
				|| state.getBlock() instanceof BlockButton
				)
			return true;
		
		return false;
	}
	
	@Override
	protected boolean onNeighborChangeInternal(World worldIn, BlockPos pos, IBlockState state)
	{
		EnumFacing enumfacing = (EnumFacing)state.getValue(FACING).getOpposite();
		
		if(state == this.getDefaultState())
		{
			if(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockSlab 
					&& worldIn.getBlockState(pos.down()).getProperties().containsValue(BlockSlab.EnumBlockHalf.BOTTOM))
			{
				return false;
			}
		}
		else if(state.getProperties().containsValue(BlockSlab.EnumBlockHalf.BOTTOM))
		{
			if((worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockSlab   &&  worldIn.getBlockState(pos.offset(enumfacing)).getProperties().containsValue(BlockSlab.EnumBlockHalf.BOTTOM))
				|| (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockStairs  && worldIn.getBlockState(pos.offset(enumfacing)).getProperties().containsValue(BlockStairs.EnumHalf.BOTTOM))
				|| worldIn.getBlockState(pos.offset(enumfacing)).getBlock().isSideSolid(worldIn.getBlockState(pos.offset(enumfacing)), worldIn, pos, enumfacing))
			{
				return false;
			}
		}
		else if(state.getProperties().containsValue(BlockSlab.EnumBlockHalf.TOP) && validTop(worldIn.getBlockState(pos.up()), state))
		{
			if((worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockSlab && worldIn.getBlockState(pos.offset(enumfacing)).getProperties().containsValue(BlockSlab.EnumBlockHalf.TOP))
					|| (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockStairs  && worldIn.getBlockState(pos.offset(enumfacing)).getProperties().containsValue(BlockStairs.EnumHalf.TOP))
					|| worldIn.getBlockState(pos.offset(enumfacing)).getBlock().isSideSolid(worldIn.getBlockState(pos.offset(enumfacing)), worldIn, pos, enumfacing))
				{
					return false;
				}
		}
		
		this.dropBlockAsItem(worldIn, pos, state, 0);
		worldIn.setBlockToAir(pos);
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d3 = 0.22D;
		double d4 = 0.27D;

		if (enumfacing.getAxis().isHorizontal())
		{
			if(stateIn.getValue(HALF) == BlockTorchSlab.EnumBlockHalf.TOP)
				d1 += 0.5D;
			
			EnumFacing enumfacing1 = enumfacing.getOpposite();
		    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.27D * (double)enumfacing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * (double)enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
		    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.27D * (double)enumfacing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * (double)enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
		}
		else
		{
			d1 -= 0.5D;
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}
	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState iblockstate = this.getDefaultState();

		switch (meta)
		{
		case 1:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(HALF, EnumBlockHalf.BOTTOM);
			break;
		case 2:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(HALF, EnumBlockHalf.BOTTOM);
			break;
		case 3:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(HALF, EnumBlockHalf.BOTTOM);
			break;
		case 4:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, EnumBlockHalf.BOTTOM);
			break;
		case 5:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(HALF, EnumBlockHalf.TOP);
			break;
		case 6:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(HALF, EnumBlockHalf.TOP);
			break;
		case 7:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(HALF, EnumBlockHalf.TOP);
			break;
		case 8:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, EnumBlockHalf.TOP);
			break;
		case 9:
			default:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.UP).withProperty(HALF, EnumBlockHalf.BOTTOM);
		}

		return iblockstate;
	}
	
	public int getMetaFromState(IBlockState state)
	{
	    int i = 0;
		if(state.getValue(HALF) == BlockTorchSlab.EnumBlockHalf.BOTTOM)
		{
		    switch ((EnumFacing)state.getValue(FACING))
		    {
		        case EAST:
		            i = i | 1;
		            break;
		        case WEST:
		            i = i | 2;
		            break;
		        case SOUTH:
		            i = i | 3;
		            break;
		        case NORTH:
		            i = i | 4;
		            break;
		        case DOWN:
		        case UP:
		        default:
		        	i = i | 9;
			}
		}
		else
		{
		    switch ((EnumFacing)state.getValue(FACING))
		    {
		        case EAST:
		            i = i | 5;
		            break;
		        case WEST:
		            i = i | 6;
		            break;
		        case SOUTH:
		            i = i | 7;
		            break;
		        case NORTH:
		            i = i | 8;
		            break;
		        case DOWN:
		        case UP:
		        default:
		        	i = i | 9;
			}
		}
	
	    return i;
	}
	
	
	@Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, HALF});
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(Blocks.TORCH);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Blocks.TORCH);
	}
	
    public static enum EnumBlockHalf implements IStringSerializable
    {
        TOP("top"),
        BOTTOM("bottom");

        private final String name;

        private EnumBlockHalf(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }
}
