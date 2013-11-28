package addfeat.common.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import addfeat.common.entities.EntityDroid;

public class RenderDroid extends Render {

	public void renderDroid(EntityDroid droid, double x, double y, double z,
			float yaw, float partialTickTime) {

		GL11.glPushMatrix();

		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glScalef(-1F, -1F, 1F);
		
		
		GL11.glPopMatrix();
		
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float yaw, float partialTickTime) {
		renderDroid((EntityDroid) entity, x, y, z, yaw, partialTickTime);


	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
