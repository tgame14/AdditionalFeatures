package addfeat.common.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import addfeat.common.blocks.BlockInfo;
import appeng.api.WorldCoord;
import appeng.api.events.GridTileLoadEvent;
import appeng.api.events.GridTileUnloadEvent;
import appeng.api.me.tiles.IGridTileEntity;
import appeng.api.me.util.IGridInterface;

public class TileEntityLiquidME extends TileEntity implements IGridTileEntity {

	private Boolean powerStatus = true, networkReady = true;
	private IGridInterface grid;
	private int timer;

	public TileEntityLiquidME() {
		this.timer = 1200;
		
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
	public void updateEntity() {

		if (!worldObj.isRemote && this.timer == 0) {
			for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				if (worldObj.getBlockTileEntity(xCoord + dir.offsetX, yCoord
						+ dir.offsetY, zCoord + dir.offsetZ) != null) {
					if (worldObj.getBlockTileEntity(xCoord + dir.offsetX,
							yCoord + dir.offsetY, zCoord + dir.offsetZ) instanceof IGridTileEntity) {
						if (!(worldObj.getBlockTileEntity(xCoord + dir.offsetX,
								yCoord + dir.offsetY, zCoord + dir.offsetZ) instanceof TileEntityLiquidME))
							worldObj.setBlock(xCoord + dir.offsetX, yCoord
									+ dir.offsetY, zCoord + dir.offsetZ,
									BlockInfo.LIQUID_ME_ID, 0, 3);

					}

				}
			}
			this.timer = 1200;
		}
		this.timer--;
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
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		compound.setShort("Timer", (short) this.timer);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		this.timer = compound.getShort("Timer");

	}

}
