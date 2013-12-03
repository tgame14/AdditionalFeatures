package addfeat.common;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import addfeat.common.blocks.Blocks;
import addfeat.common.client.interfaces.GuiHandler;
import addfeat.common.client.renderers.Renderers;
import addfeat.common.config.ConfigHandler;
import addfeat.common.config.ConfigInfo;
import addfeat.common.entities.Entities;
import addfeat.common.events.EventBusListener;
import addfeat.common.fluids.Fluids;
import addfeat.common.items.Items;
import addfeat.common.logic.LogicBase;
import addfeat.common.network.PacketHandler;
import addfeat.common.proxies.CommonProxy;
import addfeat.common.tileentities.TileEntities;
import appeng.api.Util;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "required-after:AppliedEnergistics")
@NetworkMod(channels = { ModInfo.CHANNEL }, packetHandler = PacketHandler.class, clientSideRequired = true, serverSideRequired = false)
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
		
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			Renderers.init();
		}
		
		Fluids.registerFluids();
		Fluids.initFluidProperties();
		Items.init();
		Blocks.init();
		TileEntities.init();

		proxy.initSounds();
		proxy.initRenderers();

		EventBusListener.init();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

		Items.addNames();
		Blocks.addNames();

		if (ConfigInfo.FURNACE_RECIPES)
			AddFurnaceRecipes.init();

		Items.registerRecipes();
		Blocks.registerRecipes();
		Entities.init();
		new GuiHandler();

		
		ModInfo.heatCacheID = Util.getAppEngApi().getGridCacheRegistry()
				.registerGridCache(LogicBase.class);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("[" + ModInfo.NAME + "] loaded Correctly");

	}

}