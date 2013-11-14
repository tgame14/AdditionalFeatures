package addfeat.common.blocks;

import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
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

	public BlockFluidME(int id) {
		super(id, Fluids.fluidME, Fluids.materialME);

		setCreativeTab(AddFeat.AddFeatTab);
		setUnlocalizedName(BlockInfo.LIQUID_ME_UNLOCALIZED_NAME);
		setHardness(1000F);
		setTickRandomly(true);

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
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		if (!world.isRemote) {
			for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				if(world.getBlockTileEntity(dir.offsetX, dir.offsetY, dir.offsetZ) instanceof IGridTileEntity)
					world.setBlock(dir.offsetX, dir.offsetY, dir.offsetZ, BlockInfo.LIQUID_ME_ID);
			}
		}

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