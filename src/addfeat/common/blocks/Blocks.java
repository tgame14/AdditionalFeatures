package addfeat.common.blocks;

import net.minecraft.block.Block;
import addfeat.common.tileentities.TileEntityBomb;
import addfeat.common.tileentities.TileEntityOceanFiller;
import addfeat.common.tileentities.TileEntityPlayerDetector;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	public static Block playerDetector;
	public static Block bomb;
	public static Block oceanFiller;

	public static void init() {
		playerDetector = new BlockPlayerDetector(BlockInfo.DETECTOR_ID);
		GameRegistry.registerBlock(playerDetector, BlockInfo.DETECTOR_KEY);

		bomb = new BlockBomb(BlockInfo.BOMB_ID);
		GameRegistry.registerBlock(bomb, BlockInfo.BOMB_KEY);

		oceanFiller = new BlockOceanFiller(BlockInfo.FILLER_ID);
		GameRegistry.registerBlock(oceanFiller, BlockInfo.FILLER_KEY);
	}

	public static void addNames() {
		LanguageRegistry.addName(playerDetector, BlockInfo.DETECTOR_NAME);
		LanguageRegistry.addName(bomb, BlockInfo.BOMB_NAME);
		LanguageRegistry.addName(oceanFiller, BlockInfo.FILLER_NAME);
	}

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityBomb.class,
				BlockInfo.BOMB_TE_KEY);

		GameRegistry.registerTileEntity(TileEntityPlayerDetector.class,
				BlockInfo.DETECTOR_TE_KEY);

		GameRegistry.registerTileEntity(TileEntityOceanFiller.class,
				BlockInfo.FILLER_TE_KEY);
	}

}
