package addfeat.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
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
		setStepSound(Block.soundStoneFootstep);
		setUnlocalizedName(BlockInfo.DETECTOR_UNLOCALIZED_NAME);
		setStepSound(soundMetalFootstep);
		

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.DETECTOR_OFF);
	}



	@Override
	public TileEntity createNewTileEntity(World world) {
		
		return new TileEntityPlayerDetector();
	}

	

}
