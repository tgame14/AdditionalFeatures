package addfeat.common.client.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import addfeat.common.tileentities.TileEntityCrate;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCrate extends GuiContainer {

	public GuiCrate(InventoryPlayer invPlayer, TileEntityCrate temc) {
		super(new ContainerCrate(invPlayer, temc));
		xSize = 176;
		ySize = 154;
	}

	private static final ResourceLocation texture = new ResourceLocation(
			"addfeat", "textures/gui/guiMultiChest.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft + 1, guiTop + 1, 1, 1, xSize, ySize);
	}

}
