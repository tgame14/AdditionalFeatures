package addfeat.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;
import addfeat.common.blocks.Blocks;
import addfeat.common.client.interfaces.GuiHandler;
import addfeat.common.config.ConfigHandler;
import addfeat.common.config.ConfigInfo;
import addfeat.common.entities.Entities;
import addfeat.common.fluids.Fluids;
import addfeat.common.items.Items;
import addfeat.common.network.EventBusListener;
import addfeat.common.proxies.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "required-after:AppliedEnergistics")
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

		Fluids.registerFluids();
		Fluids.initFluidProperties();
		Items.init();
		Blocks.init();
		Blocks.registerTileEntities();

		proxy.initSounds();
		proxy.initRenderers();
		
		EventBusListener.init();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

		Items.addNames();
		Blocks.addNames();

		if(ConfigInfo.FURNACE_RECIPES)
			AddFurnaceRecipes.init();
		
		Items.registerRecipes();
		Blocks.registerRecipes();
		Entities.init();
		new GuiHandler();

	}

	

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println("[" + ModInfo.NAME + "] loaded Correctly");

	}

}