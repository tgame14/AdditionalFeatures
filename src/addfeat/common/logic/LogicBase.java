package addfeat.common.logic;

import net.minecraft.nbt.NBTTagCompound;
import appeng.api.me.util.IGridCache;
import appeng.api.me.util.IGridInterface;

/**
 * The Class LogicBase.
 * 
 * @author tgame14
 */
public class LogicBase implements IGridCache {

	/** The ticked. */
	private boolean ticked;

	/** The effects. */
	private HeatEffects effects;

	/** The calculation logic. */
	protected LogicCalc calc;

	/** The cool logic. */
	protected LogicCool coolLogic;

	private float totalCoolant;

	/** The final heat after all calculations. */
	private float finalHeat;

	/**
	 * Instantiates a new logic base.
	 */
	public LogicBase() {
		ticked = false;

		finalHeat = 0;
		totalCoolant = 0;

	}

	public float getFinalHeat() {
		return finalHeat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * appeng.api.me.util.IGridCache#onUpdateTick(appeng.api.me.util.IGridInterface
	 * )
	 * 
	 * Runs all Actions that need to be done every tick on the network
	 */
	@Override
	public void onUpdateTick(IGridInterface grid) {
		// KEEP NOTICE, THIS WILL BE MOVED TO CONSTRUCTOR IN AE2
		if (!ticked && grid != null) {

			calc = new LogicCalc(grid);
			effects = new HeatEffects(grid, calc);
			coolLogic = new LogicCool(grid, calc);

			ticked = true;
		}
		
			finalHeat = calc.calcFinalHeat(calc.calcRawHeat(),
					coolLogic.calcTotalCoolant());

			effects.OnOverHeat(finalHeat);
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * appeng.api.me.util.IGridCache#reset(appeng.api.me.util.IGridInterface)
	 * 
	 * Every time the network is reset this is called. Maintaining of counts and
	 * list are done here.
	 */
	@Override
	public void reset(IGridInterface grid) {
		if (calc != null)
			calc.refreshMachineList();
		if (coolLogic != null) {
			coolLogic.refreshCoolants();
			totalCoolant = coolLogic.calcTotalCoolant();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appeng.api.me.util.IGridCache#getCacheName()
	 * 
	 * an identifier for the instance of IGridCache
	 */
	@Override
	public String getCacheName() {
		return "MadHeatPoweredBandit";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appeng.api.me.util.IGridCache#savetoNBTData()
	 * 
	 * where data is saved to nbt.
	 */
	@Override
	public NBTTagCompound savetoNBTData() {
		NBTTagCompound tag = new NBTTagCompound();

		tag.setBoolean("firstTick", ticked);
		tag.setFloat("heatFinalPercent", finalHeat);

		return tag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see appeng.api.me.util.IGridCache#loadfromNBTData(net.minecraft.nbt.
	 * NBTTagCompound)
	 * 
	 * Where data is loaded from nbt.
	 */
	@Override
	public void loadfromNBTData(NBTTagCompound data) {
		ticked = data.getBoolean("firstTick");
		finalHeat = data.getFloat("heatFinalPercent");

	}

}
