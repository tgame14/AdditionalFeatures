package addfeat.common.tileentities;

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
import addfeat.common.logic.CoolingFluids;
import appeng.api.WorldCoord;
import appeng.api.events.GridTileLoadEvent;
import appeng.api.events.GridTileUnloadEvent;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.util.IGridInterface;

public class TileEntityLiquidCooler extends TileEntity implements IGridMachine,
		IFluidHandler {

	private boolean powerStatus = false, networkReady = false, isActive;
	private IGridInterface grid;
	private int drainAmount;

	protected FluidTank tank = new FluidTank(16000);

	public TileEntityLiquidCooler() {
		isActive = false;
		drainAmount = 5;

	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean state) {
		isActive = state;
	}

	private int setDrainAmount(Fluid setFluid) {
		Fluid coldFluid;
		
		for(CoolingFluids dir : CoolingFluids.values()) {
			coldFluid = FluidRegistry.getFluid(dir.fluidName);
			if(coldFluid.equals(setFluid))
				return dir.consumePerTick;
		}
		

		return 5;
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (isPowered() && tank.getFluidAmount() > 0) {

				/*
				 * drainAmount changes per liquid in the tank. colder liquids
				 * will drain less, hotter will drain faster
				 */
				tank.drain(drainAmount, true);
				System.out.println("draining = " + drainAmount);

			} else {
				setIsActive(false);
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
						.getFluid().fluidID))
			return 0;

		int filled = 0;

		filled += tank.fill(resource, doFill);

		return filled;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {

		drainAmount = setDrainAmount(resource.getFluid());

		if (resource == null || !resource.isFluidEqual(tank.getFluid()))
			return null;

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
		System.out.println("testingFilling");
		Fluid coldFluid;

		for (CoolingFluids dir : CoolingFluids.values()) {
			coldFluid = FluidRegistry.getFluid(dir.fluidName);
			if (coldFluid.equals(fluid)) {
				System.out.println("returning true");
				return true;
			}
		}
		System.out.println("returning false");
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
