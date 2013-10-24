package addfeat.common.proxies;

import addfeat.common.client.RenderSpaceship;
import addfeat.common.entities.EntitySpaceship;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ProxyClient extends CommonProxy {

	@Override
	public void initSounds() {
		
	}
	
	
	@Override
	public void initRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySpaceship.class, new RenderSpaceship());
	}

}