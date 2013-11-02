package addfeat.common.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import addfeat.common.tileentities.TileEntityCan;

public class ContainerCan extends Container {

	private TileEntityCan can;

	public ContainerCan(InventoryPlayer invPlayer, TileEntityCan can) {
		this.can = can;
	}
	

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return can.isUseableByPlayer(entityplayer);
	}
	
}
