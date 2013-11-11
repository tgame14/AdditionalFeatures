package addfeat.common.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import addfeat.common.AddFeat;
import addfeat.common.tileentities.TileEntityCan;
import addfeat.common.tileentities.TileEntityMultiChest;
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
			TileEntity te1 = world.getBlockTileEntity(x, y, z);
			if(te1 != null && te1 instanceof TileEntityCan) {
				return new ContainerCan(player.inventory, (TileEntityCan)te1);
			}
			break;
			
		case 1:
			TileEntity te2 = world.getBlockTileEntity(x, y, z);
			if(te2 != null && te2 instanceof TileEntityMultiChest) {
				return new ContainerMultiChest(player.inventory, (TileEntityMultiChest)te2);
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
			TileEntity te1 = world.getBlockTileEntity(x, y, z);
			if(te1 != null && te1 instanceof TileEntityCan) {
				return new GuiGarbageCan(player.inventory, (TileEntityCan)te1);
			}
			break;
			
		case 1:
			TileEntity te2 = world.getBlockTileEntity(x, y, z);
			if(te2 != null && te2 instanceof TileEntityMultiChest) {
				return new GuiMultiChest(player.inventory, (TileEntityMultiChest)te2);
			}
		}
		return null;
		
	}
	
	
	
}
