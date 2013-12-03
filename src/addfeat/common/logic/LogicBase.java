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

/**
 * @author tgame14
 * 
 */
public class LogicBase implements IGridCache {

	private boolean ticked;
	private HeatEffects effects;
	protected LogicCalc calc;
	protected LogicCool coolLogic;
	private float finalHeat;
	private int liquiCount, intakeCount;

	public LogicBase() {
		ticked = false;

		liquiCount = 0;
		intakeCount = 0;

		finalHeat = 0;

	}

	@Override
	public void onUpdateTick(IGridInterface grid) {
		if (!ticked && grid != null) {
			effects = new HeatEffects(grid);
			calc = new LogicCalc(grid);
			coolLogic = new LogicCool(grid);

			ticked = true;
		}
		finalHeat = calc.calcRawHeat();

		effects.OnOverHeat(finalHeat);

	}

	public HeatEffects getEffects() {
		return effects;
	}

	public LogicCool getCoolLogic() {
		return coolLogic;
	}

	@Override
	public void reset(IGridInterface grid) {

	}

	@Override
	public String getCacheName() {
		return "MadHeatPoweredBandit";
	}

	@Override
	public NBTTagCompound savetoNBTData() {
		NBTTagCompound tag = new NBTTagCompound();

		tag.setBoolean("firstTick", ticked);
		tag.setFloat("heatFinalPercent", finalHeat);

		return tag;
	}

	@Override
	public void loadfromNBTData(NBTTagCompound data) {
		ticked = data.getBoolean("firstTick");
		finalHeat = data.getFloat("heatFinalPercent");

	}

}
