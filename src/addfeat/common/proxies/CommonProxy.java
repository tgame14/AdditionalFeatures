package addfeat.common.proxies;

import addfeat.common.client.RenderDroid;
import addfeat.common.entities.EntityDroid;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class CommonProxy {

	public void initSounds() {
		
	}
	
	public void initRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityDroid.class, new RenderDroid());
		
	}
}