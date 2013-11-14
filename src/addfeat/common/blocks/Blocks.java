package addfeat.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlowing;
import net.minecraft.block.BlockStationary;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import addfeat.common.fluids.Fluids;
import addfeat.common.items.Items;
import addfeat.common.tileentities.TileEntityBomb;
import addfeat.common.tileentities.TileEntityCrate;
import addfeat.common.tileentities.TileEntityOceanFiller;
import addfeat.common.tileentities.TileEntityPlayerDetector;
import addfeat.common.tileentities.TileEntityWeatherBox;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	public static Block playerDetector;
	public static Block bomb;
	public static Block oceanFiller;
	public static Block weatherBox;
	public static Block crate;
	public static Block fluidME;
	

	public static void init() {
		playerDetector = new BlockPlayerDetector(BlockInfo.DETECTOR_ID);
		GameRegistry.registerBlock(playerDetector, BlockInfo.DETECTOR_KEY);

		bomb = new BlockBomb(BlockInfo.BOMB_ID);
		GameRegistry.registerBlock(bomb, BlockInfo.BOMB_KEY);

		oceanFiller = new BlockOceanFiller(BlockInfo.FILLER_ID);
		GameRegistry.registerBlock(oceanFiller, BlockInfo.FILLER_KEY);

		weatherBox = new BlockWeatherBox(BlockInfo.WEATHER_ID);
		GameRegistry.registerBlock(weatherBox, BlockInfo.WEATHER_KEY);

		crate = new BlockCrate(BlockInfo.CRATE_ID);
		GameRegistry.registerBlock(crate, BlockInfo.CRATE_KEY);
		
		fluidME = new BlockFluidME(BlockInfo.LIQUID_ME_ID);
		GameRegistry.registerBlock(fluidME, BlockInfo.LIQUID_ME_KEY);
		
	}

	public static void addNames() {
		LanguageRegistry.addName(playerDetector, BlockInfo.DETECTOR_NAME);
		LanguageRegistry.addName(bomb, BlockInfo.BOMB_NAME);
		LanguageRegistry.addName(oceanFiller, BlockInfo.FILLER_NAME);
		LanguageRegistry.addName(weatherBox, BlockInfo.WEATHER_NAME);
		LanguageRegistry.addName(crate, BlockInfo.CRATE_NAME);
		LanguageRegistry.addName(fluidME, BlockInfo.LIQUID_ME_NAME);
	}

	public static void registerTileEntities() {
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
	}

	public static void registerRecipes() {

		GameRegistry.addRecipe(new ItemStack(playerDetector, 1, 0), "XYX",
				"YZY", "XYX", 'X', Item.glowstone, 'Y', Block.blockRedstone,
				'Z', new ItemStack(Items.Core, 1, 0));

		GameRegistry.addRecipe(new ItemStack(oceanFiller, 1, 0), "XYX", "YZY",
				"XYX", 'X', Item.bucketWater, 'Z', new ItemStack(Items.Core, 1,
						2), 'Y', Item.poisonousPotato);
	}
	
	
}
