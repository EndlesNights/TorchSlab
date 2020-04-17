package com.endlesnights.torchslabmod.futuremc;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class BlockLanternSlab extends Block
{
	public static PropertyBool HANGING = PropertyBool.create("hanging");
    private static final AxisAlignedBB SITTING_AABB = BlockBase.makeAABB(5.0D, -8.0D, 5.0D, 11.0D, 1.0D, 11.0D);
    private static final AxisAlignedBB HANGING_AABB = BlockBase.makeAABB(5.0D, 9.0D, 5.0D, 11.0D, 18.0D, 11.0D);
	
	public BlockLanternSlab()
	{
		super(Material.IRON);
		this.setHardness(5.0F);
		this.setHarvestLevel("pickaxe", 0);
		this.setLightLevel(1.0F);
		this.setSoundType(SoundType.METAL); 
		
		this.setDefaultState(getDefaultState().withProperty(HANGING, false));
		
	}

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        if (canBlockStay(worldIn, pos)) {
            return getDefaultState().withProperty(HANGING, facing == EnumFacing.DOWN);
        }
        return getDefaultState().withProperty(HANGING, isBlockInvalid(worldIn, pos.down()));
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
    	if(state == this.getDefaultState())
		{
			if(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockSlab 
					&& worldIn.getBlockState(pos.down()).getProperties().containsValue(BlockSlab.EnumBlockHalf.BOTTOM))
			{
				return true;
			}
		}
    	else
    	{
			if(worldIn.getBlockState(pos.down()).getBlock() instanceof BlockSlab 
					&& worldIn.getBlockState(pos.down()).getProperties().containsValue(BlockSlab.EnumBlockHalf.TOP))
			{
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
        return new BlockStateContainer(this, HANGING);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(HANGING, meta != 1);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(HANGING) ? 1 : 0;
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return blockState.getValue(HANGING) ? 15 : 0;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return state.getValue(HANGING) ? HANGING_AABB : SITTING_AABB;
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
