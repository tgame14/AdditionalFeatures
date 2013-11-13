package addfeat.common.fluids;

import addfeat.common.blocks.BlockInfo;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class FluidLiquidME extends BlockFluidClassic {

	public FluidLiquidME(int id, Fluid fluid, Material material) {
		super(BlockInfo.LIQUID_ME_ID, Fluids.fluidME, Fluids.materialME);
		
	}

}
