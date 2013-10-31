package addfeat.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import addfeat.common.AddFeat;
import addfeat.common.ModInfo;
import addfeat.common.tileentities.TileEntityPlayerDetector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlayerDetector extends BlockContainer {


	public BlockPlayerDetector(int id) {
		super(id, Material.tnt);
		setCreativeTab(AddFeat.AddFeatTab);
		setHardness(2F);
		setUnlocalizedName(BlockInfo.DETECTOR_UNLOCALIZED_NAME);
		setStepSound(soundMetalFootstep);

	}

	@SideOnly(Side.CLIENT)
	private Icon activeIcon;
	@SideOnly(Side.CLIENT)
	private Icon idleIcon;

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		activeIcon = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.BOMB_TEXTURE);
		idleIcon = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.DETECTOR_OFF);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return meta == 0 ? activeIcon : idleIcon;

	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		return new TileEntityPlayerDetector();
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess par1iBlockAccess, int par2,
			int par3, int par4, int par5) {
		return 0;
	}
	
	

}
