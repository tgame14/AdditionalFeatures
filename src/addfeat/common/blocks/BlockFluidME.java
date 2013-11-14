package addfeat.common.blocks;

import java.util.Random;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;
import addfeat.common.AddFeat;
import addfeat.common.ModInfo;
import addfeat.common.fluids.Fluids;
import addfeat.common.tileentities.TileEntityCrate;
import addfeat.common.tileentities.TileEntityLiquidME;
import appeng.api.WorldCoord;
import appeng.api.me.tiles.IGridTileEntity;
import appeng.api.me.util.IGridInterface;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidME extends BlockFluidClassic implements ITileEntityProvider {

	@SideOnly(Side.CLIENT)
	protected Icon stillIcon;
	@SideOnly(Side.CLIENT)
	protected Icon flowingIcon;

	public BlockFluidME(int id) {
		super(id, Fluids.fluidME, Fluids.materialME);

		setCreativeTab(AddFeat.AddFeatTab);
		setUnlocalizedName(BlockInfo.LIQUID_ME_UNLOCALIZED_NAME);
		setHardness(1000F);
		
	}
	
	@Override
	public int tickRate(World world) {
		return  10000;
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
				if (world.getBlockTileEntity(x + dir.offsetX, y + dir.offsetY,
						z + dir.offsetZ) != null) {
					if (world.getBlockTileEntity(x + dir.offsetX, y
							+ dir.offsetY, z + dir.offsetZ) instanceof IGridTileEntity) {
						world.setBlock(x + dir.offsetX, y + dir.offsetY, z
								+ dir.offsetZ, BlockInfo.LIQUID_ME_ID, 0, 3);
					}

				}

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

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityLiquidME();
	}
	
	
	

}