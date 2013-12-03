package addfeat.common.logic;

import java.util.Random;

import org.omg.PortableServer.ServantLocatorPackage.CookieHolder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import addfeat.common.fluids.Fluids;
import addfeat.common.network.PacketHandler;
import addfeat.common.tileentities.TileEntityLiquidCooler;
import appeng.api.Blocks;
import appeng.api.TileRef;
import appeng.api.exceptions.AppEngTileMissingException;
import appeng.api.me.tiles.ICellContainer;
import appeng.api.me.util.IGridCache;
import appeng.api.me.util.IGridInterface;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
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
	
	/** The calc. */
	protected LogicCalc calc;
	
	/** The cool logic. */
	protected LogicCool coolLogic;
	
	/** The final heat. */
	private float finalHeat;
	
	

	/**
	 * Instantiates a new logic base.
	 */
	public LogicBase() {
		ticked = false;

		finalHeat = 0;

	}

	/* (non-Javadoc)
	 * @see appeng.api.me.util.IGridCache#onUpdateTick(appeng.api.me.util.IGridInterface)
	 * 
	 * Runs all Actions that need to be done every tick on the network
	 */
	@Override
	public void onUpdateTick(IGridInterface grid) {
		//KEEP NOTICE, THIS WILL BE MOVED TO CONSTRUCTOR IN AE2
		if (!ticked && grid != null) {
			
			calc = new LogicCalc(grid);
			effects = new HeatEffects(grid, calc);
			coolLogic = new LogicCool(grid, calc);

			ticked = true;
		}
		
		finalHeat = calc.calcFinalHeat(calc.calcRawHeat(), coolLogic.calcTotalCoolant());

		effects.OnOverHeat(finalHeat);

	}

	/**
	 * Gets the instance of HeatEffects for this grid.
	 *
	 * @return HeatEffects the effects
	 */
	public HeatEffects getEffects() {
		return effects;
	}

	/**
	 * Gets the instance of LogicCool for this grid.
	 *
	 * @return LogicCool the cool logic
	 */
	public LogicCool getCoolLogic() {
		return coolLogic;
	}

	/* (non-Javadoc)
	 * @see appeng.api.me.util.IGridCache#reset(appeng.api.me.util.IGridInterface)
	 * 
	 * Every time the network is reset this is called. Maintaining of counts and list are done here.
	 */
	@Override
	public void reset(IGridInterface grid) {
		calc.refreshMachineList();
		coolLogic.refreshCoolants();
		
		
	}

	/* (non-Javadoc)
	 * @see appeng.api.me.util.IGridCache#getCacheName()
	 * 
	 * an identifier for the instance of IGridCache
	 */
	@Override
	public String getCacheName() {
		return "MadHeatPoweredBandit";
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
	 * @see appeng.api.me.util.IGridCache#loadfromNBTData(net.minecraft.nbt.NBTTagCompound)
	 * 
	 * Where data is loaded from nbt.
	 */
	@Override
	public void loadfromNBTData(NBTTagCompound data) {
		ticked = data.getBoolean("firstTick");
		finalHeat = data.getFloat("heatFinalPercent");

	}

}
