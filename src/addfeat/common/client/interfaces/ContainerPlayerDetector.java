package addfeat.common.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import addfeat.common.tileentities.TileEntityPlayerDetector;

public class ContainerPlayerDetector extends Container {

	private TileEntityPlayerDetector detector;

	public ContainerPlayerDetector(InventoryPlayer invPlayer, TileEntityPlayerDetector detector) {
		this.detector = detector;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return detector.isUseableByPlayer(entityplayer);
	}
	
}
