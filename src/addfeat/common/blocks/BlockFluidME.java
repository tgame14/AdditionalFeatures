package addfeat.common.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import addfeat.common.AddFeat;
import addfeat.common.ModInfo;
import addfeat.common.fluids.Fluids;
import appeng.api.me.tiles.IGridTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidME extends BlockFluidClassic {

	@SideOnly(Side.CLIENT)
	protected Icon stillIcon;
	@SideOnly(Side.CLIENT)
	protected Icon flowingIcon;

	private int timer;

	public BlockFluidME(int id) {
		super(id, Fluids.fluidME, Fluids.materialME);

		setCreativeTab(AddFeat.AddFeatTab);
		setUnlocalizedName(BlockInfo.LIQUID_ME_UNLOCALIZED_NAME);
		setHardness(1000F);
		this.timer = 100;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		return (side <= 1) ? stillIcon : flowingIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		stillIcon = iconRegister.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.LIQUID_STILL_TEXTURE);
		flowingIcon = iconRegister.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.LIQUID_FLOW_TEXTURE);

	}

	@Override
	public void onNeighborTileChange(World world, int x, int y, int z,
			int tileX, int tileY, int tileZ) {
		super.onNeighborTileChange(world, x, y, z, tileX, tileY, tileZ);

		if (!world.isRemote) {
			if (world.getBlockTileEntity(tileX, tileY, tileZ) instanceof IGridTileEntity)
				meltBlock(tileX, tileY, tileZ, world);
		}

	}

	private void meltBlock(int x, int y, int z, World world) {

		world.setBlock(x, y, z, BlockInfo.LIQUID_ME_ID);

	}

	private TileEntity initializeTE(TileEntity te, int x, int y, int z) {
		return te.worldObj.getBlockTileEntity(x, y, z);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z,
			Entity entity) {
		if (!world.isRemote) {
			if (entity instanceof EntityLivingBase) {
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(
						19, 200));
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(
						05, 200));
			}
		}

	}

}