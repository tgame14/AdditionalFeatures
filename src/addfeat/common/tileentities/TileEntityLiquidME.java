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
	private int spreadTimer;
	private int solidTimer;
	private boolean ticked;

	public TileEntityLiquidME() {
		spreadTimer = 1200;
		solidTimer = 12000;
		ticked = false;

	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (!ticked) {
				solidTimer = (int) (12000 * getBiomeCustomTemp());
				ticked = true;
			}
			checkSpread();
			solidify();
		}
	}

	private double getBiomeCustomTemp() {
		final double temp = worldObj.getBiomeGenForCoords(xCoord, zCoord)
				.getIntTemperature() / 65536.0;
		if (temp < 0.11)
			return 0.11;
		return temp;
	}

	private void solidify() {
		if (solidTimer == 0 && worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 0) {
			worldObj.setBlock(xCoord, yCoord, zCoord, BlockInfo.JELLO_ID);
		}
		solidTimer--;

	}

	private void checkSpread() {
		if (spreadTimer == 0) {
			for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				final int x = this.xCoord + dir.offsetX;
				final int y = this.yCoord + dir.offsetY;
				final int z = this.zCoord + dir.offsetZ;
				final TileEntity te = this.worldObj.getBlockTileEntity(x, y, z);

				if (te != null && te instanceof IGridTileEntity
						&& !(te instanceof TileEntityLiquidME)) {
					this.worldObj.setBlock(x, y, z, BlockInfo.LIQUID_ME_ID, 0,
							3);
				}
			}
			spreadTimer = 1200;
		}
		spreadTimer--;
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

		compound.setShort("spreadTimer", (short) spreadTimer);
		compound.setInteger("solidTimer", solidTimer);

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		spreadTimer = compound.getShort("spreadTimer");
		solidTimer = compound.getInteger("solidTimer");

	}

}