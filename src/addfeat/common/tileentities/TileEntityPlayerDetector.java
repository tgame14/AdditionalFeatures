package addfeat.common.tileentities;

import net.minecraft.tileentity.TileEntity;

public class TileEntityPlayerDetector extends TileEntity {

	private boolean flag = false;

	public TileEntityPlayerDetector() {

	}

	@Override
	public void updateEntity() {
		if (worldObj.isRemote) {
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);

			if (worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 17) != null) {
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1,
						3);
			}

		}
	}

}
