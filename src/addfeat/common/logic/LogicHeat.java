package addfeat.common.logic;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import addfeat.common.fluids.Fluids;
import addfeat.common.tileentities.TileEntityMEExhaust;
import appeng.api.Blocks;
import appeng.api.TileRef;
import appeng.api.WorldCoord;
import appeng.api.exceptions.AppEngTileMissingException;
import appeng.api.me.tiles.ICellContainer;
import appeng.api.me.util.IGridCache;
import appeng.api.me.util.IGridInterface;

public class LogicHeat implements IGridCache {

	Random rnd = new Random();
	
	private float heatPercent;
	private short meltTicker = 0;

	private boolean isBareBones(IGridInterface grid) {
		for (int i = 0; i < grid.getMachines().size(); i++) {
			if (!(isSafeFromMelt(grid.getMachines().get(i)))) {
				return false;
			}
		}
		return true;
	}

	private boolean isSafeFromMelt(TileRef machine) {
		int x = machine.x;
		int y = machine.y;
		int z = machine.z;

		int machineBlockID = machine.getCoord().getWorld().getBlockId(x, y, z);
		int controllerID = Blocks.blkController.itemID;
		Object te = null;
		try {
			te = machine.getTile();

		} catch (AppEngTileMissingException e) {
			e.printStackTrace();

		}

		if (machineBlockID == controllerID || te instanceof ICellContainer
				|| te instanceof TileEntityMEExhaust) {
			return true;

		}
		return false;

	}

	private float calculateHeat(IGridInterface grid) {
		float usage = (grid.getPowerUsageAvg() / 10) / 100;
		return usage;
		
	}
	
	private void OnOverHeat(IGridInterface grid) {
		if (!(isBareBones(grid))) {
			TileRef machine = getRandomAETile(grid);
			World machineWorld = machine.getCoord().getWorld(); 

			if (heatPercent >= 0.0 && meltTicker == 0) {
				
				machineWorld.destroyBlock(machine.x, machine.y, machine.z, false);
				machineWorld.setBlock(machine.x, machine.y, machine.z, Fluids.fluidME.getBlockID());
				meltTicker = 800;
			}

			meltTicker--;

			if (heatPercent >= 0.66) {

			}

			if (heatPercent >= 0.90) {

			}
		}

	}
	
	private TileRef getRandomAETile(IGridInterface grid) {
		TileRef machine = grid.getMachines().get(
				rnd.nextInt(grid.getMachines().size()));

		return machine;
	}

	@Override
	public void onUpdateTick(IGridInterface grid) {
		heatPercent = calculateHeat(grid);
		grid.useMEEnergy(heatPercent, "HEAT Effects");
		
		OnOverHeat(grid);

	}

	@Override
	public String getCacheName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset(IGridInterface grid) {
		// TODO Auto-generated method stub

	}

	@Override
	public NBTTagCompound savetoNBTData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadfromNBTData(NBTTagCompound data) {
		// TODO Auto-generated method stub

	}

}
