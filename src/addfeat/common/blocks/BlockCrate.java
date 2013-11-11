package addfeat.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import addfeat.common.AddFeat;
import addfeat.common.tileentities.TileEntityCrate;
import cpw.mods.fml.common.network.FMLNetworkHandler;

public class BlockCrate extends BlockContainer {
	

	private static boolean flag;
	
	public BlockCrate(int id) {
		super(id, Material.iron);
		setCreativeTab(AddFeat.AddFeatTab);
		setHardness(2F);
		setStepSound(Block.soundMetalFootstep);
		setUnlocalizedName(BlockInfo.CRATE_UNLOCALIZED_NAME);
		this.flag = false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y,
			int z, int nextID) {
		if(nextID == this.blockID) {
			flag = true;
		}
		
	}
	
	
	public static boolean isFlag() {
		return flag;
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
	public void breakBlock(World world, int x, int y, int z,
			int id, int meta) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if(te != null && te instanceof IInventory) {
			IInventory inventory = (IInventory)te;
			
			for(int i = 0; i < inventory.getSizeInventory(); i++) {
				ItemStack stack = inventory.getStackInSlot(i);
				
				if(stack != null) {
					float spawnX = x + world.rand.nextFloat();
					float spawnY = y + world.rand.nextFloat();
					float spawnZ = y + world.rand.nextFloat();
					
					EntityItem droppedItem = new EntityItem(world, spawnX, spawnY, spawnZ, stack);
					
					float mult = 0.05F;
					
					droppedItem.motionX = (-0.5F + world.rand.nextFloat()) * mult;
					droppedItem.motionY = (4 + world.rand.nextFloat()) * mult;
					droppedItem.motionZ = (-0.5F + world.rand.nextFloat()) * mult;
					
					world.spawnEntityInWorld(droppedItem);
			}
		}
		
		super.breakBlock(world, x, y, z, id, meta);
			
		}	
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCrate();
	}

}
