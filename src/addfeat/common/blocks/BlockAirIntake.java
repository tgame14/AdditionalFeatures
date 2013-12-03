package addfeat.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import addfeat.common.AddFeat;
import addfeat.common.ModInfo;
import addfeat.common.tileentities.TileEntityAirIntake;

public class BlockAirIntake extends BlockContainer {

	public BlockAirIntake(int id) {
		super(id, Material.iron);
		setCreativeTab(AddFeat.AddFeatTab);
		setHardness(2F);
		setStepSound(Block.soundMetalFootstep);
		setUnlocalizedName(BlockInfo.BOMB_UNLOCALIZED_NAME);
		
		
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
		
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":" + BlockInfo.INTAKE_TEXTURE);
	}
	
	@Override
	public Icon getIcon(int par1, int par2) {
		return blockIcon;
	
	}
	
	
	

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityAirIntake();
	}

}
