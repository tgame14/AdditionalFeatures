package addfeat.common.logic;

import java.util.List;

import appeng.api.TileRef;
import appeng.api.exceptions.AppEngTileMissingException;
import appeng.api.me.tiles.ICellContainer;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.util.IGridInterface;

/**
 * @author tgame14
 * 
 */
public class LogicCalc {

	private IGridInterface grid;

	protected LogicCalc(IGridInterface gi) {
		grid = gi;

	}

	protected float calcRawHeat() {
		float raw = (grid.getPowerUsageAvg() / 2) / 100;
		return raw;
	}

	/**
	 * this is where the math is done for calculation of coolants. it is based
	 * on mathematical equations that are the base for sets and such, Deeper
	 * explanation of the math behind it can be supplied from
	 * 
	 * See {@linktourl http://en.wikipedia.org/wiki/Geometric_progression}
	 * 
	 * @author tgame14
	 * 
	 * @param int Amount of Active Coolants
	 * @param float percent decrease per machine (has to be < 1 )
	 * 
	 * @return double cooling value of coolant
	 */
	private float calcCoolantValue(int activeCoolants, float decrPercent) {
		float firstValue = calcRawHeat() * decrPercent;

		float totalCoolant = (float) ((firstValue
				* (Math.pow(decrPercent, activeCoolants)) - 1) / (decrPercent - 1));

		return totalCoolant;

	}

	protected int calcAmountOfMachine(TileRef tile) {
		int count = 0;

		List list = grid.getMachines();
		Object te = null;

		try {
			te = tile.getTile();
		} catch (AppEngTileMissingException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == tile) {
				count++;
			}

		}
		return count;
	}

	/*
	 * protected int calcLiquidCoolantActive() {
	 * 
	 * }
	 */

	protected float calcFinalHeat(float rawHeat, float coolant) {

		return rawHeat - coolant;
	}

	protected boolean isBareBones() {
		List<TileRef<IGridMachine>> list = grid.getMachines();

		for (int i = 0; i < list.size(); i++) {
			if (!(isSafeFromMelt(list.get(i)))) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean isSafeFromMelt(TileRef tile) {
		Object te = null;

		try {
			te = tile.getTile();
		} catch (AppEngTileMissingException e) {
			e.printStackTrace();
		}

		if (te instanceof ICellContainer || te == grid.getController()) {
			return true;
		}
		return false;
	}

}
