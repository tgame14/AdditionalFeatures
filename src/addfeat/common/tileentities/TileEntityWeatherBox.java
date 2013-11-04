package addfeat.common.tileentities;

import net.minecraft.tileentity.TileEntity;

public class TileEntityWeatherBox extends TileEntity {
	
	
	public TileEntityWeatherBox() {
		
	}
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote) {
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
			if(worldObj.isRaining())
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
			
		}
	}
	
	
	
	
}
