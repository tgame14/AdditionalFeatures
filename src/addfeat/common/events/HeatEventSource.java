package addfeat.common.events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HeatEventSource {
	private List listeners = new ArrayList();
	
	public synchronized void addEventListener(HeatEvent listener) {
		listeners.add(listener);
		
	}
	
	public synchronized void removeEventListener(HeatEvent listener) {
		listeners.remove(listener);
		
	}
	
	private synchronized void fireEvent() {
		HeatEvent event = new HeatEvent(this);
		Iterator i = listeners.iterator();
		
		while(i.hasNext()) {
			((IHeatEventListener) i.next()).handleHeatEventHandler(event);
		}
	}

}
