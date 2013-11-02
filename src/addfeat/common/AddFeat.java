package addfeat.common;

import net.minecraft.creativetab.CreativeTabs;
import addfeat.common.blocks.Blocks;
import addfeat.common.client.interfaces.GuiHandler;
import addfeat.common.config.ConfigHandler;
import addfeat.common.entities.Entities;
import addfeat.common.items.Items;
import addfeat.common.proxies.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
@NetworkMod(channels = { ModInfo.CHANNEL }, clientSideRequired = true, serverSideRequired = false)
public class AddFeat {

	public static final CreativeTabs AddFeatTab = new AddFeatTab(
			CreativeTabs.getNextID(), ModInfo.NAME);

	@Instance(ModInfo.ID)
	public static AddFeat instance;

	@SidedProxy(clientSide = "addfeat.common.proxies.ProxyClient", serverSide = "addfeat.common.proxies.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		Items.init();
		Blocks.init();
		Blocks.registerTileEntities();

		proxy.initSounds();
		proxy.initRenderers();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		Items.addNames();
		Blocks.addNames();
		
		AddFurnaceRecipes.init();
		Items.registerRecipes();
		Entities.init();
		new GuiHandler();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("[" + ModInfo.NAME + "] loaded Correctly");
		// add Version Checker later (very later).
	}

}