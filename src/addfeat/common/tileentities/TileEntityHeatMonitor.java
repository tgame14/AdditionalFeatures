package addfeat.common.tileentities;

import cpw.mods.fml.common.Mod;
import addfeat.common.ModInfo;
import addfeat.common.logic.LogicBase;
import appeng.api.me.util.IGridInterface;


public class TileEntityHeatMonitor extends AEBaseMachine {
	
	private float heatPercent;
	private IGridInterface grid;
	private boolean ticked;
	private LogicBase logic;
	
	public TileEntityHeatMonitor() {
		heatPercent = 0;
		ticked = false;
		
	}
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote) {
			if(!ticked && grid != null) {
				
				logic = (LogicBase) grid.getCacheByID(ModInfo.heatCacheID);
				
				ticked = true;
			}
		}
	}

}
