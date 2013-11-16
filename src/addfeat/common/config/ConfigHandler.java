package addfeat.common.config;

import java.io.File;

import net.minecraftforge.common.Configuration;
import addfeat.common.ModInfo;
import addfeat.common.blocks.BlockInfo;
import addfeat.common.items.ItemInfo;

public class ConfigHandler {

	public static void init(File file) {
		Configuration config = new Configuration(file);

		// debug for Development, will be removed for release.
		if (file.canRead()) {
			System.out.println("[" + ModInfo.NAME
					+ "] Config Already Exists, Reading");
		} else {
			System.out.println("[" + ModInfo.NAME
					+ "] Config doesn't exist. generating...");
		}

		config.load();

		// Config Registry of Blocks

		BlockInfo.DETECTOR_ID = config.getBlock(BlockInfo.DETECTOR_KEY,
				BlockInfo.DETECTOR_DEFAULT).getInt();
		BlockInfo.BOMB_ID = config.getBlock(BlockInfo.BOMB_KEY,
				BlockInfo.BOMB_DEFAULT).getInt();
		BlockInfo.FILLER_ID = config.getBlock(BlockInfo.FILLER_KEY,
				BlockInfo.FILLER_DEFAULT).getInt();
		BlockInfo.WEATHER_ID = config.getBlock(BlockInfo.WEATHER_KEY,
				BlockInfo.WEATHER_DEFAULT).getInt();
		BlockInfo.CRATE_ID = config.getBlock(BlockInfo.CRATE_KEY,
				BlockInfo.CRATE_DEFAULT).getInt();

		// Config Registry of Blocks for Applied Thermodynamics
		BlockInfo.LIQUID_ME_ID = config.getBlock(BlockInfo.LIQUID_ME_KEY,
				BlockInfo.LIQUID_ME_DEFAULT).getInt();
		BlockInfo.JELLO_ID = config.getBlock(BlockInfo.JELLO_KEY,
				BlockInfo.JELLO_DEFAULT).getInt();

		// Config Registry for Disabling and Enabling Features
		ConfigInfo.FURNACE_RECIPES = config.get(ConfigInfo.FEATURE_KEY,
				ConfigInfo.FURNACE_RECIPES_KEY,
				ConfigInfo.FURNACE_RECIPES_DEFAULT).getBoolean(
				ConfigInfo.FURNACE_RECIPES_DEFAULT);

		ConfigInfo.LIQUID_CONSUME = config.get(ConfigInfo.FEATURE_KEY,
				ConfigInfo.LIQUID_CONSUME_KEY,
				ConfigInfo.LIQUID_CONSUME_DEFAULT).getBoolean(
				ConfigInfo.LIQUID_CONSUME_DEFAULT);

		ItemInfo.SHARD_ID = config.getItem(ItemInfo.SHARD_KEY,
				ItemInfo.SHARD_DEFAULT).getInt() - 256;
		ItemInfo.CORE_ID = config.getItem(ItemInfo.CORE_KEY,
				ItemInfo.CORE_DEFAULT).getInt() - 256;

		config.save();
		System.out.println("[" + ModInfo.NAME + "] Config Loaded");
	}
}
