package addfeat.common.tileentities;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import addfeat.common.items.Items;
import appeng.api.WorldCoord;
import appeng.api.events.GridTileLoadEvent;
import appeng.api.events.GridTileUnloadEvent;
import appeng.api.me.tiles.IGridMachine;
import appeng.api.me.tiles.IGridTileEntity;
import appeng.api.me.util.IGridInterface;

public class TileEntityMEExhaust extends AEBaseMachine {

	private boolean powerStatus = false, networkReady = false;
	private IGridInterface grid;
	private ItemStack gooStack;

	Random gen = new Random();
	private int gooTimer;

	public TileEntityMEExhaust() {
		gooTimer = 0;
		gooStack = new ItemStack(Items.Goo);

	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (gooTimer <= 0 && powerStatus) {
				worldObj.spawnEntityInWorld(new EntityItem(worldObj,
						xCoord + 0.5, yCoord + 2.0, zCoord + 0.5, gooStack));

				gooTimer = gen.nextInt(2000);
			}
			gooTimer--;
		}
	}

}
