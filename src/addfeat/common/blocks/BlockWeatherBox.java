package addfeat.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import addfeat.common.AddFeat;
import addfeat.common.tileentities.TileEntityWeatherBox;

public class BlockWeatherBox extends BlockContainer {
	public BlockWeatherBox(int id) {
		super(id, Material.tnt);
		setCreativeTab(AddFeat.AddFeatTab);
		setHardness(2F);
		setUnlocalizedName(BlockInfo.WEATHER_UNLOCALIZED_NAME);
		setStepSound(soundMetalFootstep);

	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityWeatherBox();
	}
	
}
