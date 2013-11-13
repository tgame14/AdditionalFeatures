package addfeat.common.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraftforge.fluids.BlockFluidClassic;
import addfeat.common.AddFeat;
import addfeat.common.ModInfo;
import addfeat.common.fluids.Fluids;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidME extends BlockFluidClassic {

	public BlockFluidME(int id) {
		super(id, Fluids.fluidME, Fluids.materialME);
		
		setCreativeTab(AddFeat.AddFeatTab);
		setUnlocalizedName(BlockInfo.LIQUID_ME_UNLOCALIZED_NAME);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(ModInfo.TEXTURE_LOCATION + ":" + BlockInfo.LIQUID_ME_TEXTURE);
		
	}

	
}