package com.endlesnights.torchslabmod.futuremc;

import java.util.Random;

import com.endlesnights.torchslabmod.vanilla.BlockTorchSlab;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thedarkcolour.core.block.BlockBase;
import thedarkcolour.futuremc.block.BlockLantern;
import thedarkcolour.futuremc.init.Init;

public class BlockLanternWall extends Block
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static PropertyBool TOP = PropertyBool.create("top");
	
	private static final AxisAlignedBB AABB_NORTH_UPPER = BlockBase.makeAABB(5.0D, 3.0D, 8.0D, 11.0D, 12.0D, 14.0D);
	private static final AxisAlignedBB AABB_EAST_UPPER = BlockBase.makeAABB(2.0D, 3.0D, 5.0D, 8.0D, 12.0D, 11.0D);
	private static final AxisAlignedBB AABB_SOUTH_UPPER = BlockBase.makeAABB(5.0D, 3.0D, 2.0D, 11.0D, 12.0D, 8.0D);
	private static final AxisAlignedBB AABB_WEST_UPPER = BlockBase.makeAABB(8.0D, 3.0D, 5.0D, 14.0D, 12.0D, 11.0D);
	
	private static final AxisAlignedBB AABB_NORTH_LOWER = BlockBase.makeAABB(5.0D, -5.0D, 8.0D, 11.0D, 4.0D, 14.0D);
	private static final AxisAlignedBB AABB_EAST_LOWER = BlockBase.makeAABB(2.0D, -5.0D, 5.0D, 8.0D, 4.0D, 11.0D);
	private static final AxisAlignedBB AABB_SOUTH_LOWER = BlockBase.makeAABB(5.0D, -5.0D, 2.0D, 11.0D, 4.0D, 8.0D);
	private static final AxisAlignedBB AABB_WEST_LOWER = BlockBase.makeAABB(8.0D, -5.0D, 5.0D, 14.0D, 4.0D, 11.0D);
	
	public BlockLanternWall()
	{
		super(Material.IRON);
		this.setHardness(5.0F);
		this.setHarvestLevel("pickaxe", 0);
		this.setLightLevel(1.0F);
		this.setSoundType(SoundType.METAL); 
		
		setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.EAST).withProperty(TOP, true));
		
	}

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getDefaultState();
    }

    private boolean isBlockInvalid(World world, BlockPos blockPos) {
        IBlockState state = world.getBlockState(blockPos);
        Block block = state.getBlock();
        return block instanceof BlockBush || world.isAirBlock(blockPos) || isPiston(state);
    }

    private boolean isPiston(IBlockState state) {
        return state.getMaterial() == Material.PISTON;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && canBlockStay(worldIn, pos);
    }

    private boolean canBlockStay(World worldIn, BlockPos pos) {
        return !(isBlockInvalid(worldIn, pos.down()) && isBlockInvalid(worldIn, pos.up()));
    }

    public boolean validGround(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
    	EnumFacing enumfacing = (EnumFacing)state.getValue(FACING).getOpposite();
    	
    	if(state.getValue(TOP))
    	{
    		if((worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockSlab && worldIn.getBlockState(pos.offset(enumfacing)).getProperties().containsValue(BlockSlab.EnumBlockHalf.TOP))
    				|| (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockStairs && worldIn.getBlockState(pos.offset(enumfacing)).getProperties().containsValue(BlockStairs.EnumHalf.TOP))
    				|| worldIn.getBlockState(pos.offset(enumfacing)).getBlock().isSideSolid(worldIn.getBlockState(pos.offset(enumfacing)), worldIn, pos, enumfacing)
    				|| worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockFence)
    			{
    				return true;
    			}	
    	}
		else if(!state.getValue(TOP))
		{
			if((worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockSlab && worldIn.getBlockState(pos.offset(enumfacing)).getProperties().containsValue(BlockSlab.EnumBlockHalf.BOTTOM))
					|| (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockStairs && worldIn.getBlockState(pos.offset(enumfacing)).getProperties().containsValue(BlockStairs.EnumHalf.BOTTOM))
					|| worldIn.getBlockState(pos.offset(enumfacing)).getBlock().isSideSolid(worldIn.getBlockState(pos.offset(enumfacing)), worldIn, pos, enumfacing)
					|| worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockFence)
				{
					if(!worldIn.getBlockState(pos.down()).getBlock().isSideSolid(worldIn.getBlockState(pos.down()), worldIn, pos.down(), enumfacing))
						return true;
				}
		}
    	
    	return false;
    }
    
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {    	 
    	if(!validGround(state, worldIn, pos, blockIn, fromPos))
    	{
    			spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(Init.LANTERN), 1));
    			worldIn.setBlockToAir(pos);
    	}
    	
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, TOP);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
		IBlockState iblockstate = this.getDefaultState();

		switch (meta)
		{
		case 1:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(TOP, false);
			break;
		case 2:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(TOP, false);
			break;
		case 3:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(TOP, false);
			break;
		case 4:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(TOP, false);
			break;
		case 5:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(TOP, true);
			break;
		case 6:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(TOP, true);
			break;
		case 7:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(TOP, true);
			break;
		case 8:
			default:
			iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(TOP, true);
			break;

		}

		return iblockstate;
    }

	@Override
    public int getMetaFromState(IBlockState state)
    {		
	    int i = 0;
		if(!state.getValue(TOP))
		{
		    switch ((EnumFacing)state.getValue(FACING))
		    {
		        case SOUTH:
		            i = i | 1;
		            break;
		        case WEST:
		            i = i | 2;
		            break;
		        case NORTH:
		            i = i | 3;
		            break;
		        case EAST:
		        default:
		            i = i | 4;
		            break;
			}
		}
		else
		{
		    switch ((EnumFacing)state.getValue(FACING))
		    {
		        case SOUTH:
		            i = i | 5;
		            break;
		        case WEST:
		            i = i | 6;
		            break;
		        case NORTH:
		            i = i | 7;
		            break;
		        case EAST:
		        default:
		            i = i | 8;
		            break;
			}
		}
	
	    return i;
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
    	if((EnumFacing)state.getValue(FACING) == EnumFacing.EAST)
    		return state.getValue(TOP) ? AABB_EAST_UPPER : AABB_EAST_LOWER;
    	else if((EnumFacing)state.getValue(FACING) == EnumFacing.SOUTH)
    		return state.getValue(TOP) ? AABB_SOUTH_UPPER: AABB_SOUTH_LOWER;
    	else if((EnumFacing)state.getValue(FACING) == EnumFacing.WEST)
    		return state.getValue(TOP) ? AABB_WEST_UPPER : AABB_WEST_LOWER;
    	else
    		return state.getValue(TOP) ? AABB_NORTH_UPPER : AABB_NORTH_LOWER;
    }

    @Override
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess source, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isTopSolid(IBlockState state) {
        return false;
    }
    
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(Init.LANTERN);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Init.LANTERN);
	}
}
