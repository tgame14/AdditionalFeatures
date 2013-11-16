package addfeat.common.events;

import appeng.api.me.util.IGridInterface;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.Event;

public class HeatEvent extends Event {
	
	/* This event will fire when heat Reaches 25%, 66% and 90%
	 * 
	 * 
	 */
	private IGridInterface grid;
	private TileEntity te;
	
	
	
	public HeatEvent() {
		
	}
}
