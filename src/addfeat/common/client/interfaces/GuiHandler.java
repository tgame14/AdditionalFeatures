package addfeat.common.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import addfeat.common.AddFeat;
import addfeat.common.tileentities.TileEntityCan;
import addfeat.common.tileentities.TileEntityCrate;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
		NetworkRegistry.instance().registerGuiHandler(AddFeat.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {

		case 0:

			break;

		case 1:
			TileEntity te2 = world.getBlockTileEntity(x, y, z);
			if (te2 != null && te2 instanceof TileEntityCrate) {
				return new ContainerCrate(player.inventory,
						(TileEntityCrate) te2);
			}
			break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {
		case 0:
			
			break;

		case 1:
			TileEntity te2 = world.getBlockTileEntity(x, y, z);
			if (te2 != null && te2 instanceof TileEntityCrate) {
				return new GuiCrate(player.inventory, (TileEntityCrate) te2);
			}
		}
		return null;

	}

}
