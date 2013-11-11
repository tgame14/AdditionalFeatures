package addfeat.common.blocks;

import addfeat.common.AddFeat;
import addfeat.common.tileentities.TileEntityCan;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGarbageCan extends BlockContainer{
	public BlockGarbageCan(int id) {
		super(id, Material.iron);
		setCreativeTab(AddFeat.AddFeatTab);
		setHardness(2F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName(BlockInfo.GARBAGE_UNLOCALIZED_NAME);
	}
		
	

	
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCan();
	}

}
