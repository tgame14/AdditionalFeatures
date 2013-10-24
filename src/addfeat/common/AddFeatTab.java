package addfeat.common;

import addfeat.common.blocks.Blocks;
import addfeat.common.items.ItemInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class AddFeatTab extends CreativeTabs {

	public AddFeatTab(int par1, String par2Str) {
		super(par1, par2Str);
		// TODO Auto-generated constructor stub
	}

	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {
		return Blocks.playerDetector.blockID;
	}

	public String getTranslatedTabLabel() {
		return ModInfo.NAME;
	}

}
