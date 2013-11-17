package addfeat.common.tileentities;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import addfeat.common.fluids.Fluids;
import appeng.api.Blocks;
import appeng.api.TileRef;
import appeng.api.WorldCoord;
import appeng.api.events.GridTileLoadEvent;
import appeng.api.events.GridTileUnloadEvent;
import appeng.api.me.tiles.ICellContainer;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.util.IGridInterface;

public class TileEntityMEExhaust extends TileEntity implements IGridMachine {

	private boolean powerStatus = true, networkReady = false;
	private float heatPercent;
	private IGridInterface grid;
	private byte ticker;
	private short meltTicker;
	private boolean bareBones;
	private WorldCoord w;

	Random gen = new Random();

	public TileEntityMEExhaust() {
		this.heatPercent = 0;
		this.ticker = 10;
		this.meltTicker = 0;

	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote && grid != null) {

			heatPercent = (grid.getPowerUsageAvg() / 10) / 100;

			/*
			 * System.out.println("Heat % : " + heatPercent);
			 * System.out.println("coldPower : " + (grid.getPowerUsageAvg() -
			 * getPowerDrainPerTick()));
			 */
			if (ticker == 0) {
				OnOverHeat();
				ticker = 10;
			}
			ticker--;
		}
	}

	private boolean isBareBones() {
		for (int i = 0; i < grid.getMachines().size(); i++) {
			if (!(isSafeFromMelt(grid.getMachines().get(i)))) {
				System.out.println("barebonesfalse");
				return false;
			}
		}
		System.out.println("barebonetrue");
		return true;
	}

	private boolean isSafeFromMelt(TileRef machine) {
		final int x = machine.x;
		final int y = machine.y;
		final int z = machine.z;

		final int machineBlockID = worldObj.getBlockId(x, y, z);
		final int controllerID = Blocks.blkController.itemID;
		final TileEntity te = worldObj.getBlockTileEntity(x, y, z);

		if (machineBlockID == controllerID || te instanceof ICellContainer
				|| te instanceof TileEntityMEExhaust) {
			return true;
		}
		return false;
	}

	private void OnOverHeat() {
		if (!(isBareBones())) {
			WorldCoord w = getRandomAETile();

			if (heatPercent >= 0.0 && meltTicker == 0) {
				worldObj.destroyBlock(w.x, w.y, w.z, false);
				worldObj.setBlock(w.x, w.y, w.z, Fluids.fluidME.getBlockID());
				meltTicker = 800;
			}

			meltTicker--;

			if (heatPercent >= 0.66) {

			}

			if (heatPercent >= 0.90) {

			}
		}

	}

	private WorldCoord getRandomAETile() {
		TileRef machine = grid.getMachines().get(
				gen.nextInt(grid.getMachines().size()));

		return new WorldCoord(machine.x, machine.y, machine.z);
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
