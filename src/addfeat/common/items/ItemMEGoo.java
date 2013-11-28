package addfeat.common.items;

import addfeat.common.AddFeat;
import addfeat.common.ModInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemMEGoo extends Item {

	public ItemMEGoo(int id) {
		super(id);
		setCreativeTab(AddFeat.AddFeatTab);
		setUnlocalizedName(ItemInfo.GOO_UNLOCALIZED_NAME);
		
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":" + ItemInfo.GOO_ICON);
		
	}
	


}
