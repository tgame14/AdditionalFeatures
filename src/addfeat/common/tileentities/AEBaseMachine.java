package addfeat.common.tileentities;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import addfeat.common.items.Items;
import appeng.api.WorldCoord;
import appeng.api.events.GridTileLoadEvent;
import appeng.api.events.GridTileUnloadEvent;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.util.IGridInterface;

public class AEBaseMachine extends TileEntity implements IGridMachine {

	protected boolean powerStatus = false;
	protected boolean networkReady = false;
	protected IGridInterface grid;

	public AEBaseMachine() {
		
	}

	@Override
	public WorldCoord getLocation() {
		return new WorldCoord(xCoord, yCoord, zCoord);

	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public void setPowerStatus(boolean hasPower) {
		powerStatus = hasPower;

	}

	@Override
	public boolean isPowered() {
		return powerStatus;

	}

	@Override
	public IGridInterface getGrid() {
		return grid;

	}

	@Override
	public void setGrid(IGridInterface gi) {
		grid = gi;

	}

	@Override
	public World getWorld() {
		return worldObj;

	}

	@Override
	public void validate() {
		super.validate();
		MinecraftForge.EVENT_BUS.post(new GridTileLoadEvent(this, worldObj,
				getLocation()));
	}

	@Override
	public void invalidate() {
		super.invalidate();
		MinecraftForge.EVENT_BUS.post(new GridTileUnloadEvent(this, worldObj,
				getLocation()));

	}

	@Override
	public float getPowerDrainPerTick() {
		return 0;
	}

	@Override
	public void setNetworkReady(boolean isReady) {
		networkReady = isReady;

	}

	@Override
	public boolean isMachineActive() {
		// TODO Auto-generated method stub
		return false;
	}

}
