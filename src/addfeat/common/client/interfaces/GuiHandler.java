package addfeat.common.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import addfeat.common.AddFeat;
import addfeat.common.tileentities.TileEntityPlayerDetector;
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
			TileEntity te = world.getBlockTileEntity(x, y, z);
			if(te != null && te instanceof TileEntityPlayerDetector) {
				return new ContainerPlayerDetector(player.inventory, (TileEntityPlayerDetector)te);
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
			TileEntity te = world.getBlockTileEntity(x, y, z);
			if(te != null && te instanceof TileEntityPlayerDetector) {
				return new GuiPlayerDetector(player.inventory, (TileEntityPlayerDetector)te);
			}
			break;
		}
		return null;
		
	}
	
}
