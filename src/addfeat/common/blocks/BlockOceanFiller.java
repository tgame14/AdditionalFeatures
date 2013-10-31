package addfeat.common.blocks;

import addfeat.common.AddFeat;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockOceanFiller extends BlockContainer{
	public BlockOceanFiller(int id) {
		super(id, Material.iron);
		setCreativeTab(AddFeat.AddFeatTab);
		setHardness(2F);
		setStepSound(Block.soundMetalFootstep);
		setUnlocalizedName(BlockInfo.FILLER_UNLOCALIZED_NAME);
	}
	
	
	
	
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return null;
	}

}
