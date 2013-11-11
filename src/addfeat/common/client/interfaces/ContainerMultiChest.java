package addfeat.common.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import addfeat.common.tileentities.TileEntityMultiChest;

public class ContainerMultiChest extends Container {

	private TileEntityMultiChest temc;

	public ContainerMultiChest(InventoryPlayer invPlayer,
			TileEntityMultiChest temc) {
		this.temc = temc;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return temc.isUseableByPlayer(entityplayer);
	}

}
