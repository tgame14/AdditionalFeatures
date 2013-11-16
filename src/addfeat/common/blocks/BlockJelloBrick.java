package addfeat.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import addfeat.common.AddFeat;
import addfeat.common.ModInfo;

public class BlockJelloBrick extends Block {
	
	public BlockJelloBrick(int id) {
		super(id, Material.rock);
		setHardness(2F);
		setCreativeTab(AddFeat.AddFeatTab);
		setLightOpacity(8);
		
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		
		blockIcon = iconRegister.registerIcon(ModInfo.TEXTURE_LOCATION + ":" + BlockInfo.JELLO_TEXTURE);
	}
	
	

}
