
package addfeat.common.logic;

import java.util.List;

import addfeat.common.tileentities.TileEntityAirIntake;
import appeng.api.TileRef;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.util.IGridInterface;


/**
 * The Class LogicCool.
 * 
 * @author tgame14
 */
public class LogicCool {
	
	/** The grid. */
	private IGridInterface grid;
	
	/** The liqui count. */
	private int liquiCount;
	
	/** The intake count. */
	private int intakeCount;
	
	/** The machine list. */
	private List machineList;
	
	/** The x. */
	private int x; 
	
	/**
	 * Instantiates a new logic cool.
	 *
	 * @param gi the gi
	 */
	protected LogicCool(IGridInterface gi) {
		grid = gi;
		machineList = grid.getMachines();
		

	}
	


	/**
	 * Gets the machine list.
	 *
	 * @return the machine list
	 */
	private List getMachineList() {
		return machineList;
	}

	/**
	 * Sets the machine list.
	 *
	 * @param List the new machine list
	 */
	private void setMachineList(List List) {
		machineList = List;
	}

	/**
	 * Gets the liqui count.
	 *
	 * @return the liqui count
	 */
	protected int getLiquiCount() {
		return liquiCount;
	}

	/**
	 * Sets the liqui count.
	 *
	 * @param liquiCount the new liqui count
	 */
	protected void setLiquiCount(int liquiCount) {
		this.liquiCount = liquiCount;
	}

	/**
	 * Gets the intake count.
	 *
	 * @return the intake count
	 */
	protected int getIntakeCount() {
		return intakeCount;
	}

	/**
	 * Sets the intake count.
	 *
	 * @param intakeCount the new intake count
	 */
	protected void setIntakeCount(int intakeCount) {
		this.intakeCount = intakeCount;
	}
	
	

}
