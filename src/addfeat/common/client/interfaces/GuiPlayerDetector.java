package addfeat.common.client.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import addfeat.common.tileentities.TileEntityCan;
import addfeat.common.tileentities.TileEntityPlayerDetector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPlayerDetector extends GuiContainer {

	public GuiPlayerDetector(InventoryPlayer invPlayer, TileEntityCan te) {
		super(new ContainerCan(invPlayer, te));
		
		xSize = 176;
		ySize = 154;
	}

	private static final ResourceLocation texture = new ResourceLocation(
			"addfeat", "textures/gui/machine.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

}
