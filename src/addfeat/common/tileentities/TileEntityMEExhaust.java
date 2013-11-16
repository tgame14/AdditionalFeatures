package addfeat.common.tileentities;

import appeng.api.WorldCoord;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.util.IGridInterface;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityMEExhaust extends TileEntity implements IGridMachine {

	private float defaultEnergy;
	private short heatPercent;
	private int exhaustHeatGen;
	private IGridInterface grid;
	
	
	public TileEntityMEExhaust() {
		this.defaultEnergy = grid.getPowerUsageAvg()
		
	}
	
	
	
	
	@Override
	public WorldCoord getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPowerStatus(boolean hasPower) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isPowered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IGridInterface getGrid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGrid(IGridInterface gi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public World getWorld() {
		// TODO Auto-generated method stub
		return null;
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
