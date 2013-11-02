package addfeat.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import cpw.mods.fml.common.registry.GameRegistry;

public class AddFurnaceRecipes {
	
	public static void init() {
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
		FurnaceRecipes.smelting().addSmelting(Item.swordIron.itemID, 0,
				new ItemStack(Item.ingotIron, 2), 0.0f);
		FurnaceRecipes.smelting().addSmelting(Item.pickaxeIron.itemID, 0,
				new ItemStack(Item.ingotIron, 3), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.shovelIron.itemID, 0,
				new ItemStack(Item.ingotIron, 2), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.hoeIron.itemID, 0,
				new ItemStack(Item.ingotIron, 2), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.axeIron.itemID, 0,
				new ItemStack(Item.ingotIron, 3), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.helmetIron.itemID, 0,
				new ItemStack(Item.ingotIron, 5), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.plateIron.itemID, 0,
				new ItemStack(Item.ingotIron, 8), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.legsIron.itemID, 0,
				new ItemStack(Item.ingotIron, 7), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.bootsIron.itemID, 0,
				new ItemStack(Item.ingotIron, 4), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.swordGold.itemID, 0,
				new ItemStack(Item.ingotGold, 2), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.pickaxeGold.itemID, 0,
				new ItemStack(Item.ingotGold, 3), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.shovelGold.itemID, 0,
				new ItemStack(Item.ingotGold, 2), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.hoeGold.itemID, 0,
				new ItemStack(Item.ingotGold, 2), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.axeGold.itemID, 0,
				new ItemStack(Item.ingotGold, 3), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.helmetGold.itemID, 0,
				new ItemStack(Item.ingotGold, 5), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.plateGold.itemID, 0,
				new ItemStack(Item.ingotGold, 8), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.legsGold.itemID, 0,
				new ItemStack(Item.ingotGold, 7), 0.0F);
		FurnaceRecipes.smelting().addSmelting(Item.bootsGold.itemID, 0,
				new ItemStack(Item.ingotGold, 4), 0.0F);
	}
}
