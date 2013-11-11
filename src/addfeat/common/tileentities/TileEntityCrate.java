package addfeat.common.tileentities;

import java.util.List;

import addfeat.common.blocks.BlockInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCrate extends TileEntity implements IInventory {

	private ItemStack[] items;
	boolean isMultiblock;
	

	public TileEntityCrate() {
		items = new ItemStack[9];
		isMultiblock = false;
	}
	
	
	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack is = getStackInSlot(i);
		if (is != null) {
			if (is.stackSize <= count) {
				setInventorySlotContents(i, null);
			} else {
				is = is.splitStack(count);
				onInventoryChanged();
			}
		}

		return is;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack is = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return is;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		items[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		return "multiChest";
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

}
