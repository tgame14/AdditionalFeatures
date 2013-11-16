package addfeat.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import addfeat.common.AddFeat;
import addfeat.common.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockExhaustME extends BlockContainer {

	public BlockExhaustME(int id) {
		super(id, Material.piston);

		setHardness(2.0F);
		setCreativeTab(AddFeat.AddFeatTab);
		setUnlocalizedName(BlockInfo.EXHAUST_UNLOCALIZED_NAME);
	}

	@SideOnly(Side.CLIENT)
	protected Icon top;
	@SideOnly(Side.CLIENT)
	protected Icon side;
	@SideOnly(Side.CLIENT)
	protected Icon bot;

	@Override
	public void registerIcons(IconRegister register) {

		side = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.EXHAUST_TEXTURES[0]);
		top = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.EXHAUST_TEXTURES[1]);
		bot = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.EXHAUST_TEXTURES[2]);
	}
	
	@Override
	public Icon getIcon(int _side, int meta) {
		if(_side <= 1)
			return _side == 0 ? bot : side;
		return top;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityMEExhaust();
		
	}

}
