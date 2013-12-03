package addfeat.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import addfeat.common.AddFeat;
import addfeat.common.ModInfo;
import addfeat.common.client.renderers.ATBlockRendererHelper;
import addfeat.common.tileentities.TileEntityHeatMonitor;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMonitorME extends BlockContainer {

	public BlockMonitorME(int id) {
		super(id, Material.rock);

		setHardness(2F);
		setCreativeTab(AddFeat.AddFeatTab);
		setUnlocalizedName(BlockInfo.MONITOR_UNLOCALIZED_NAME);

	}

	@SideOnly(Side.CLIENT)
	public Icon sideIcon;
	@SideOnly(Side.CLIENT)
	public Icon bottomIcon;
	@SideOnly(Side.CLIENT)
	public Icon topIcon;
	@SideOnly(Side.CLIENT)
	public Icon frontIconFull;
	@SideOnly(Side.CLIENT)
	public Icon frontIconNearFull;
	@SideOnly(Side.CLIENT)
	public Icon frontIconNearEmpty;
	@SideOnly(Side.CLIENT)
	public Icon frontIconEmpty;

	@Override
	public void registerIcons(IconRegister register) {

		frontIconFull = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.MONITOR_TEXTURES[1]);
		frontIconNearFull = register.registerIcon(ModInfo.TEXTURE_LOCATION
				+ ":" + BlockInfo.MONITOR_TEXTURES[2]);
		frontIconNearEmpty = register.registerIcon(ModInfo.TEXTURE_LOCATION
				+ ":" + BlockInfo.MONITOR_TEXTURES[3]);
		frontIconEmpty = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.MONITOR_TEXTURES[4]);

		sideIcon = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.EXHAUST_TEXTURES[1]);
		bottomIcon = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.MONITOR_TEXTURES[0]);
		topIcon = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.MONITOR_TEXTURES[0]);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase player, ItemStack itemstack) {
		super.onBlockPlacedBy(world, x, y, z, player, itemstack);
		int l = MathHelper
				.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			System.out.println(2);
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
			System.out.println(5);
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
			System.out.println(3);
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
			System.out.println(4);
		}
	}

	/*
	 * @Override public int getRenderType() { return
	 * ATBlockRendererHelper.myRenderID; }
	 */

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityHeatMonitor();

	}

}
