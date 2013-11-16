package addfeat.common.tileentities;

import appeng.api.WorldCoord;
import appeng.api.events.GridTileLoadEvent;
import appeng.api.events.GridTileUnloadEvent;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.util.IGridInterface;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class TileEntityMEExhaust extends TileEntity implements IGridMachine {

	private boolean powerStatus = true, networkReady = false;
	private float heatPercent;
	private IGridInterface grid;

	public TileEntityMEExhaust() {
		this.heatPercent = 0;

	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote && grid != null) {
			heatPercent = (grid.getPowerUsageAvg() / 10) / 100;
			
			System.out.println("Heat % : " + heatPercent);
			System.out.println("coldPower : " + (grid.getPowerUsageAvg() - getPowerDrainPerTick()));

		}
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
	public float getPowerDrainPerTick() {
		return heatPercent * grid.getPowerUsageAvg();

	}

	@Override
	public void setNetworkReady(boolean isReady) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isMachineActive() {
		return isPowered();

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

}
