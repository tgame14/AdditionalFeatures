package addfeat.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWeatherBox extends BlockContainer {

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityWeatherBox();
	}
	
}
