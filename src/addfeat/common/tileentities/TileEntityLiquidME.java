package addfeat.common.tileentities;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import appeng.api.WorldCoord;
import appeng.api.events.GridTileLoadEvent;
import appeng.api.events.GridTileUnloadEvent;
import appeng.api.me.tiles.IGridTileEntity;
import appeng.api.me.util.IGridInterface;

public class TileEntityLiquidME extends TileEntity implements IGridTileEntity {
	
	Boolean powerStatus = true, networkReady = true;
    IGridInterface grid;
    int priority = 1;
	
	public TileEntityLiquidME() {
		
	}

	@Override
	public WorldCoord getLocation() {
		return new WorldCoord(this.xCoord, this.yCoord, this.zCoord);
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
    public void validate()
    {
            super.validate();
            MinecraftForge.EVENT_BUS.post(new GridTileLoadEvent(this, worldObj, getLocation()));
    }

    @Override
    public void invalidate()
    {
            super.invalidate();
            MinecraftForge.EVENT_BUS.post(new GridTileUnloadEvent(this, worldObj, getLocation()));
    }

}
