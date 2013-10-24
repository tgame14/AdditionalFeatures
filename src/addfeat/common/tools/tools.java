package addfeat.common.tools;

import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;

public class tools {
	public static void init() {

		EnumToolMaterial Catalyst = EnumHelper.addToolMaterial("Catalyst", 3,
				1600, 10.0F, 4.0F, 15);
		EnumToolMaterial woodenShield = EnumHelper.addToolMaterial(
				"WoodenShield", 0, 212, 0, 0, 0);
	}

}
