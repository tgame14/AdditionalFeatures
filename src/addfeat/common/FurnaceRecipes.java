package addfeat.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class FurnaceRecipes {
	public static void init() {

		GameRegistry.addSmelting(Item.bootsDiamond.itemID, new ItemStack(
				Item.diamond, 3), 0.0F);
		GameRegistry.addSmelting(Item.legsDiamond.itemID, new ItemStack(
				Item.diamond, 6), 0.0F);
		GameRegistry.addSmelting(Item.plateDiamond.itemID, new ItemStack(
				Item.diamond, 7), 0.0F);
		GameRegistry.addSmelting(Item.helmetDiamond.itemID, new ItemStack(
				Item.diamond, 4), 0.0F);

		GameRegistry.addSmelting(Item.bootsIron.itemID, new ItemStack(
				Item.ingotIron, 3), 0.0F);
		GameRegistry.addSmelting(Item.legsIron.itemID, new ItemStack(
				Item.ingotIron, 6), 0.0F);
		GameRegistry.addSmelting(Item.plateIron.itemID, new ItemStack(
				Item.ingotIron, 7), 0.0F);
		GameRegistry.addSmelting(Item.helmetIron.itemID, new ItemStack(
				Item.ingotIron, 4), 0.0F);

		GameRegistry.addSmelting(Item.bootsGold.itemID, new ItemStack(
				Item.ingotGold, 3), 0.0F);
		GameRegistry.addSmelting(Item.legsGold.itemID, new ItemStack(
				Item.ingotGold, 6), 0.0F);
		GameRegistry.addSmelting(Item.plateGold.itemID, new ItemStack(
				Item.ingotGold, 7), 0.0F);
		GameRegistry.addSmelting(Item.helmetGold.itemID, new ItemStack(
				Item.ingotGold, 4), 0.0F);

		GameRegistry.addSmelting(Item.bucketEmpty.itemID, new ItemStack(
				Item.ingotIron, 2), 0.0F);
		GameRegistry.addSmelting(Item.minecartEmpty.itemID, new ItemStack(
				Item.ingotIron, 5), 0.0F);
		GameRegistry.addSmelting(Item.doorIron.itemID, new ItemStack(
				Item.ingotIron, 6), 0.0F);
		GameRegistry.addSmelting(Block.anvil.blockID, new ItemStack(
				Item.ingotIron, 30), 0.0F);
		GameRegistry.addSmelting(Block.hopperBlock.blockID, new ItemStack(
				Item.ingotIron, 5), 0.0F);
	}
}
