package addfeat.common.client.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import addfeat.common.network.PacketHandler;
import addfeat.common.tileentities.TileEntityCrate;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCrate extends GuiContainer {

	public GuiCrate(InventoryPlayer invPlayer, TileEntityCrate crate) {
		super(new ContainerCrate(invPlayer, crate));
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
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Crate Box", 8, 6, 0x404040);
	}
	@Override
	public void initGui() {
		super.initGui();
		
		buttonList.clear();
		
		buttonList.add(new GuiButton(0, guiLeft + 101, guiTop + 42, 60, 20, "Day Time"));
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		PacketHandler.sendButtonPacket((byte)button.id);
		
	}
	
	
	
	

}
