package addfeat.common.tileentities;

import addfeat.common.blocks.BlockInfo;
import addfeat.common.config.ConfigInfo;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntities {

	public static void init() {
		GameRegistry.registerTileEntity(TileEntityBomb.class,
				BlockInfo.BOMB_TE_KEY);

		GameRegistry.registerTileEntity(TileEntityPlayerDetector.class,
				BlockInfo.DETECTOR_TE_KEY);

		GameRegistry.registerTileEntity(TileEntityOceanFiller.class,
				BlockInfo.FILLER_TE_KEY);

		GameRegistry.registerTileEntity(TileEntityWeatherBox.class,
				BlockInfo.WEATHER_TE_KEY);
		GameRegistry.registerTileEntity(TileEntityCrate.class,
				BlockInfo.CRATE_TE_KEY);

		if (ConfigInfo.LIQUID_CONSUME)
			GameRegistry.registerTileEntity(TileEntityLiquidME.class,
					BlockInfo.LIQUID_TE_KEY);

		GameRegistry.registerTileEntity(TileEntityMEExhaust.class,
				BlockInfo.EXHAUST_TE_KEY);

		GameRegistry.registerTileEntity(TileEntityLiquidCooler.class,
				BlockInfo.LIQUICOOL_TE_KEY);
	}
	
}
