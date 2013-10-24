package addfeat.common.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPlayerDetector extends TileEntity {
	public TileEntityPlayerDetector() {

	}

	@Override
	public void updateEntity() {
		if (worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 17) != null)
			System.out.println("YES!");
	}

	
	
}
