package addfeat.common.config;

import java.io.File;

import net.minecraftforge.common.Configuration;
import addfeat.common.ModInfo;
import addfeat.common.blocks.BlockInfo;
import addfeat.common.items.ItemInfo;

public class ConfigHandler {

	public static void init(File file) {
		Configuration config = new Configuration(file);

		if (file.canRead()) {
			System.out.println("[" + ModInfo.NAME
					+ "] Config Already Exists, Reading");
		} else {
			System.out.println("[" + ModInfo.NAME
					+ "] Config doesn't exist. generating...");
		}

		config.load();

		BlockInfo.DETECTOR_ID = config.getBlock(BlockInfo.DETECTOR_KEY,
				BlockInfo.DETECTOR_DEFAULT).getInt();
		BlockInfo.BOMB_ID = config.getBlock(BlockInfo.BOMB_KEY,
				BlockInfo.BOMB_DEFAULT).getInt();
		BlockInfo.FILLER_ID = config.getBlock(BlockInfo.FILLER_KEY,
				BlockInfo.FILLER_DEFAULT).getInt();

		ItemInfo.SHARD_ID = config.getItem(ItemInfo.SHARD_KEY,
				ItemInfo.SHARD_DEFAULT).getInt() - 256;
		ItemInfo.CORE_ID = config.getItem(ItemInfo.CORE_KEY,
				ItemInfo.CORE_DEFAULT).getInt() - 256;

		config.save();
		System.out.println("[" + ModInfo.NAME + "] Config Loaded");
	}

}
