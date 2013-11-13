package addfeat.common.fluids;

import addfeat.common.blocks.BlockInfo;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class Fluids {
	
	public static Material materialME;
	public static Fluid fluidME;
	
	public static void init() {
		
		fluidME = new Fluid("fluidME").setBlockID(BlockInfo.LIQUID_ME_ID);
		FluidRegistry.registerFluid(fluidME);
		
		materialME = new MaterialLiquid(MapColor.clayColor);
	}

}
