package addfeat.common.tileentities;

import appeng.api.WorldCoord;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.util.IGridInterface;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityHeatMonitor extends TileEntity implements IGridMachine {

	private boolean powerStatus, networkReady;
	private IGridInterface grid;
	
	
	
	public TileEntityHeatMonitor() {

		powerStatus = false;
		networkReady = false;
		
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNetworkReady(boolean isReady) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isMachineActive() {
		// TODO Auto-generated method stub
		return false;
	}
}
