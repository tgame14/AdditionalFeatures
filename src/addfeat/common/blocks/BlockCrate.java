package addfeat.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import addfeat.common.AddFeat;
import addfeat.common.tileentities.TileEntityCrate;
import cpw.mods.fml.common.network.FMLNetworkHandler;

public class BlockCrate extends BlockContainer {

	public BlockCrate(int id) {
		super(id, Material.iron);
		setCreativeTab(AddFeat.AddFeatTab);
		setHardness(2F);
		setStepSound(Block.soundMetalFootstep);
		setUnlocalizedName(BlockInfo.CRATE_UNLOCALIZED_NAME);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (!par1World.isRemote) {
			FMLNetworkHandler.openGui(par5EntityPlayer, AddFeat.instance, 1,
					par1World, par2, par3, par4);
		}
		
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCrate();
	}

}
