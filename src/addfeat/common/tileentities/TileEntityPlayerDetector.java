package addfeat.common.tileentities;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ISpecialArmor;

public class TileEntityPlayerDetector extends TileEntity {

	public TileEntityPlayerDetector() {
		int flag = 0;
	}

	@Override
	public void updateEntity() {
		if (worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 17) != null) {
			flag = 15;
		}
		
	}
	
	

	
	
}
