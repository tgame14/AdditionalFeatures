/*
 * 
 */
package addfeat.common.logic;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

// TODO: Auto-generated Javadoc
/**
 * The Enum CoolingFluids.
 */
public enum CoolingFluids {
	
	/** The water. */
	WATER(10000, "water"), 
 /** The seedoil. */
 SEEDOIL(3, "seedoil"), 
 /** The honey. */
 HONEY(2, "honey"), 
 /** The ice. */
 ICE(
			5, "ice"), 
 /** The shortmead. */
 SHORTMEAD(1, "short.mead"), 
 /** The fuel. */
 FUEL(8, "fuel");

	/** The consume per tick. */
	public final int consumePerTick;
	
	/** The fluid name. */
	public final String fluidName;

	/**
	 * Instantiates a new cooling fluids.
	 *
	 * @param consume the consume
	 * @param fluid the fluid
	 */
	private CoolingFluids(int consume, String fluid) {
		this.consumePerTick = consume;
		this.fluidName = fluid;
	}

}
