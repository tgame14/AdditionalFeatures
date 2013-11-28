package addfeat.common.logic;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public enum CoolingFluids {
	WATER(10000, "water"), SEEDOIL(3, "seedoil"), HONEY(2, "honey"), ICE(
			5, "ice"), SHORTMEAD(1, "short.mead"), FUEL(8, "fuel");

	public final int consumePerTick;
	public final String fluidName;

	private CoolingFluids(int consume, String fluid) {
		this.consumePerTick = consume;
		this.fluidName = fluid;
	}

}
