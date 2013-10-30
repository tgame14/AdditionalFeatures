package addfeat.common.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import addfeat.common.tileentities.TileEntityPlayerDetector;

public class ContainerPlayerDetector extends Container {

	private TileEntityPlayerDetector detector;

	public ContainerPlayerDetector(InventoryPlayer invPlayer,
			TileEntityPlayerDetector detector) {
		this.detector = detector;

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(invPlayer, i, 8 + 18 * i, 130));
		}

		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < 9; i++) {
				addSlotToContainer(new Slot(invPlayer, i + j * 9 + 9,
						8 + 18 * i, 72 + j * 18));
			}
		}

		for (int i = 0; i < 3; i++) {
			addSlotToContainer(new Slot(detector, i, 8 + 18 * i, 17));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return detector.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		return null;
	}

}
