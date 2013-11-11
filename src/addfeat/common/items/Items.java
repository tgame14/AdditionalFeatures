package addfeat.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {

	public static Item Shard;
	public static Item Core;

	public static void init() {
		Shard = new ItemShard(ItemInfo.SHARD_ID);

		Core = new ItemCoreLapis(ItemInfo.CORE_ID);
	}

	public static void addNames() {
		LanguageRegistry.addName(Shard, ItemInfo.SHARD_NAME);

		for (int i = 0; i < ItemInfo.CORE_NAMES.length; i++) {
			LanguageRegistry.addName(new ItemStack(Core, 1, i),
					ItemInfo.CORE_NAMES[i]);
		}
	}

	public static void registerRecipes() {
		GameRegistry.addRecipe(new ItemStack(Core, 1, 0), "XXX", "XYX", "XXX",
				'Y', Item.diamond, 'X', Block.blockLapis);
		GameRegistry.addRecipe(new ItemStack(Core, 1, 1), "XXX", "XYX", "XXX",
				'Y', new ItemStack(Core, 1, 0), 'X', Item.diamond);
		GameRegistry.addRecipe(new ItemStack(Core, 1, 2), "XXX", "XYX", "XXX",
				'Y', new ItemStack(Core, 1, 1), 'X', new ItemStack(Core, 1, 0));

	}

}
