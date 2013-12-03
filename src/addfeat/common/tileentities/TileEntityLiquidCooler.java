package addfeat.common.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import addfeat.common.ModInfo;
import addfeat.common.logic.CoolingFluids;
import addfeat.common.logic.LogicHeat;
import appeng.api.WorldCoord;
import appeng.api.events.GridTileLoadEvent;
import appeng.api.events.GridTileUnloadEvent;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.util.IGridInterface;

public class TileEntityLiquidCooler extends AEBaseMachine implements
		IFluidHandler {

	private boolean isActive, ticked, refreshCount;
	private IGridInterface grid;
	private int drainAmount;
	private LogicHeat logic;
	private FluidStack prevStack;

	protected FluidTank tank = new FluidTank(16000);

	public TileEntityLiquidCooler() {
		isActive = false;
		drainAmount = 5;
		
		ticked = false;
		refreshCount = true;
		prevStack = tank.getFluid();

	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		tank.writeToNBT(tag);

		isActive = tag.getBoolean("isActive");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tank.readFromNBT(tag);

		tag.setBoolean("isActive", isActive);
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean state) {
		isActive = state;
	}

	private int setDrainAmount(FluidStack fluid) {
		FluidStack coldFluid;
		int consume = 5;

		for (CoolingFluids dir : CoolingFluids.values()) {
			coldFluid = new FluidStack(FluidRegistry.getFluid(dir.fluidName),
					fluid.amount);
			if (coldFluid.isFluidEqual(fluid)) {
				consume = dir.consumePerTick;
			}
		}

		return consume;
	}

	private void callRefresh() {
		logic.setLiquidCount(grid);
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (!ticked && grid != null) {
				logic = (LogicHeat) grid.getCacheByID(ModInfo.heatCacheID);
				ticked = true;
			}

			if (isPowered()) {
				System.out.println("itsPowered");

				/*
				 * drainAmount changes per liquid in the tank. colder liquids
				 * will drain less, hotter will drain faster
				 */

				tank.drain(drainAmount, true);
				System.out.println("draining = " + drainAmount);

			} else {
				System.out.println("unactive");
				setIsActive(false);
				refreshCount = true;
			}
		}

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
	public float getPowerDrainPerTick() {
		return 0;

	}

	@Override
	public void setNetworkReady(boolean isReady) {
		networkReady = isReady;

	}

	@Override
	public boolean isMachineActive() {
		return isPowered();

	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

		if (resource == null
				|| (tank.getFluid() != null && resource.fluidID != tank
						.getFluid().fluidID)
				|| canFill(from, resource.getFluid()))
			return 0;

		if (tank.getFluid() == null) {
			drainAmount = setDrainAmount(resource);
		}

		int filled = 0;

		filled += tank.fill(resource, doFill);
		setIsActive(filled > 0);
		logic.setLiquidCount(grid);
		prevStack = resource;
		return filled;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {

		drainAmount = setDrainAmount(resource);

		if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
			callRefresh();
			return null;
		}

		FluidStack drained = resource != null ? tank.drain(resource.amount,
				doDrain) : null;

		return drained;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if (tank.getFluid() == null)
			return null;

		return drain(from, new FluidStack(tank.getFluid(), maxDrain), doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {

		FluidStack coldFluid;
		FluidStack resource = new FluidStack(fluid, 0);

		for (CoolingFluids dir : CoolingFluids.values()) {
			coldFluid = FluidRegistry.getFluidStack(dir.fluidName, 0);
			if (coldFluid != null && coldFluid.isFluidEqual(resource)) {
				return true;
			}
		}
		return false;

	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;

	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { tank.getInfo() };
	}

}
