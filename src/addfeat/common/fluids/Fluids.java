package addfeat.common.fluids;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import addfeat.common.blocks.BlockFluidME;
import addfeat.common.blocks.BlockInfo;

public class Fluids {

	public static Material materialME;
	public static Fluid fluidME;

	@ForgeSubscribe
	public static void registerFluids() {

		fluidME = new Fluid("fluidME").setBlockID(BlockInfo.LIQUID_ME_ID);
		FluidRegistry.registerFluid(fluidME);

		materialME = new MaterialLiquid(MapColor.clayColor);
		
	}
	
	public static void initFluidProperties() {
		//Temperature does nothing, will be useful when applying HEAT in Applied Thermodynamics
		
		fluidME.setViscosity(5000).setDensity(2000).setTemperature(372);
		
	}
	
	public static void addNames() {
		LanguageRegistry.addName(fluidME, "Liquid ME");
	}
	

}
