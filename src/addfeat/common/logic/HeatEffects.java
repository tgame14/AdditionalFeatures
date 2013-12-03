
package addfeat.common.logic;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.world.World;
import addfeat.common.fluids.Fluids;
import addfeat.common.network.PacketHandler;
import appeng.api.TileRef;
import appeng.api.me.util.IGridInterface;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/**
 * The Class HeatEffects.
 * 
 * This Class handles the effects that occur from overheating, including client, server and other effects in the way.
 * 
 *  Create an instance (object) of this class in main LogicBase for usage. be sure to put the IGridInterface in construction.
 *  
 */
public class HeatEffects {

	/** The rnd. */
	Random rnd = new Random();

	/** The grid. */
	private IGridInterface grid;
	
	/** The particle and melt tickers. */
	private int meltTicker, particleTicker;
	
	/** The calc, instance of LogicCalc class for usage. */
	private LogicCalc calc;
	
	/**
	 * Instantiates a new heat effects.
	 *
	 * @param gi the gi
	 */
	protected HeatEffects(IGridInterface gi) {
		grid = gi;
		meltTicker = 0;
		particleTicker = 0;
		calc = new LogicCalc(grid);
	}

	/**
	 * Gets the particle ticker.
	 *
	 * @return the particle ticker
	 */
	public int getParticleTicker() {
		return particleTicker;
	}

	/**
	 * Sets the particle ticker.
	 *
	 * @param value the new particle ticker
	 */
	public void setParticleTicker(int value) {
		particleTicker = value;
	}

	/**
	 * Decr particle ticker.
	 */
	private void decrParticleTicker() {
		particleTicker--;
	}

	/**
	 * Gets the melt ticker.
	 *
	 * @return the melt ticker
	 */
	public int getMeltTicker() {
		return meltTicker;
	}

	/**
	 * Sets the melt ticker.
	 *
	 * @param value the new melt ticker
	 */
	public void setMeltTicker(int value) {
		meltTicker = value;
	}

	/**
	 * Decr melt ticker.
	 */
	private void decrMeltTicker() {
		meltTicker--;
	}

	/**
	 * On over heat.
	 * 
	 * this method groups inside it the registry of all overHeat methods that are called Dependent on
	 * the param its given.
	 * 
	 * 
	 * it calls different Stage's of effects based on param, When stage 1 is higher than stage 3.
	 *
	 * @param heatValue the heat value
	 */
	protected void OnOverHeat(float heatValue) {
		if (heatValue >= 0.1) {
			StageThree();
		}

		if (heatValue >= 0.5) {
			StageTwo();
		}

		if (heatValue >= 0.9) {
			StageOne();
		}
	}

	/**
	 * Stage three.
	 * 
	 * Stage 3 of overheating, When stage 1 is the worst. stage 3 causes smoke particles around the network.
	 * through sending packets to the client.
	 */
	protected void StageThree() {

		if (getMeltTicker() <= 0) {
			TileRef machine;
			do {
				machine = getRandomAETile(grid, rnd);
			}
			while(calc.isSafeFromMelt(machine));
			
			World machineWorld = machine.getCoord().getWorld();

			machineWorld.destroyBlock(machine.x, machine.y, machine.z, false);
			machineWorld.setBlock(machine.x, machine.y, machine.z,
					Fluids.fluidME.getBlockID());
			setMeltTicker(10000);
		}
		decrMeltTicker();

	}

	/**
	 * Stage two.
	 * 
	 * Generates flame particles through packets sent to client.
	 */
	protected void StageTwo() {

		if (getParticleTicker() == 5) {
			TileRef tile = getRandomAETile(grid, rnd);

			PacketHandler.sendEffectPacket((byte) 1, tile.x, tile.y, tile.z,
					tile.getCoord().getWorld().provider.dimensionId);

		}

	}

	/**
	 * Stage one.
	 */
	protected void StageOne() {

		if (getParticleTicker() <= 0) {
			TileRef tile = getRandomAETile(grid, rnd);

			PacketHandler.sendEffectPacket((byte) 0, tile.x, tile.y, tile.z,
					tile.getCoord().getWorld().provider.dimensionId);

			setParticleTicker(10);
		}
		decrParticleTicker();
	}

	/**
	 * Gets a random ae tile.
	 *
	 * @param the instance of grid
	 * @param a random number generator
	 * @return the random ae TileRef
	 */
	public TileRef getRandomAETile(IGridInterface grid, Random rand) {
		int lengthOfList = grid.getMachines().size();
		int indexOfList;

		if (lengthOfList == 1) {
			indexOfList = 0;
		} else {
			indexOfList = rand.nextInt(lengthOfList);
		}

		TileRef machine = grid.getMachines().get(indexOfList);
		return machine;
	}

	/**
	 * Receive entity fx packet.
	 * 
	 * receiving end on client to spawn particles.
	 *
	 * @param effectId the effect id
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	@SideOnly(Side.CLIENT)
	public void receiveEntityFXPacket(byte effectId, int x, int y, int z) {

		float xCoord = x + 0.5F;
		float yCoord = y + 1.1F;
		float zCoord = z + 0.5F;

		World world = Minecraft.getMinecraft().theWorld;

		switch (effectId) {
		case 0:
			for (int i = 0; i < 15; i++) {
				float xRandom = rnd.nextFloat() * 0.6F - 0.3F;
				float zRandom = rnd.nextFloat() * -0.6F - -0.3F;
				Minecraft.getMinecraft().effectRenderer
						.addEffect(new EntitySmokeFX(world,
								(double) (xCoord + xRandom), yCoord,
								(double) (zCoord + zRandom), 0.0, 0.0, 0.0));
			}
			break;
		case 1:
			for (int i = 0; i < 15; i++) {
				float xRandom = rnd.nextFloat() * 0.6F - 0.3F;
				float zRandom = rnd.nextFloat() * -0.6F - -0.3F;
				Minecraft.getMinecraft().effectRenderer
						.addEffect(new EntityFlameFX(world,
								(double) (xCoord + xRandom), yCoord,
								(double) (zCoord + zRandom), 0.0, 0.0, 0.0));
			}

			break;

		}

	}

}
