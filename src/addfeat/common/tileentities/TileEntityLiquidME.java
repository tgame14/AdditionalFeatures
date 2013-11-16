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

	private boolean powerStatus = true, networkReady = true;
	private IGridInterface grid;
	private int timer;
	private int tickTimer;
	private boolean ticked;

	public TileEntityLiquidME() {
		timer = 1200;
		tickTimer = 12000;
		ticked = false;

	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (!ticked) {
				tickTimer = (int) (12000 * getBiomeCustomTemp());
				ticked = true;
			}
			checkSpread();
			solidify();
		}
	}

	private double getBiomeCustomTemp() {
		double x = (double) (worldObj.getBiomeGenForCoords(xCoord, zCoord)
				.getIntTemperature() / 65536);
		if (x < 0.11)
			return 0.11;
		return x;
	}

	private void solidify() {
		if (tickTimer == 0) {
			worldObj.setBlock(xCoord, yCoord, zCoord, BlockInfo.JELLO_ID);
		}
		tickTimer--;

	}

	private void checkSpread() {

		if (timer == 0) {
			for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				if (worldObj.getBlockTileEntity(xCoord + dir.offsetX, yCoord
						+ dir.offsetY, zCoord + dir.offsetZ) != null) {
					if (worldObj.getBlockTileEntity(xCoord + dir.offsetX,
							yCoord + dir.offsetY, zCoord + dir.offsetZ) instanceof IGridTileEntity
							&& !(worldObj.getBlockTileEntity(xCoord
									+ dir.offsetX, yCoord + dir.offsetY, zCoord
									+ dir.offsetZ) instanceof TileEntityLiquidME)) {
						worldObj.setBlock(xCoord + dir.offsetX, yCoord
								+ dir.offsetY, zCoord + dir.offsetZ,
								BlockInfo.LIQUID_ME_ID, 0, 3);
					}
				}
			}
			timer = 1200;
		}
		timer--;
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

		compound.setShort("Timer", (short) timer);
		compound.setInteger("TickTimer", tickTimer);

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		timer = compound.getShort("Timer");
		tickTimer = compound.getInteger("TickTimer");

	}

}
