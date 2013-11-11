package addfeat.common.blocks;

import addfeat.common.AddFeat;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockChestStructure extends Block {
	public BlockChestStructure(int id) {
		super(id, Material.iron);
		setCreativeTab(AddFeat.AddFeatTab);
		setHardness(2F);
		setStepSound(Block.soundMetalFootstep);
		setUnlocalizedName(BlockInfo.STRUCT_UNLOCALIZED_NAME);
	}

}
