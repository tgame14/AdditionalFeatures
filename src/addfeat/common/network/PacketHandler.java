package addfeat.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import addfeat.common.ModInfo;
import addfeat.common.entities.EntitySpaceship;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
		
		int entityId = reader.readInt();
		
		EntityPlayer entityPlayer = (EntityPlayer)player;
		Entity entity = entityPlayer.worldObj.getEntityByID(entityId);
		if (entity != null && entity instanceof EntitySpaceship) {
			((EntitySpaceship)entity).doDrop();
		}
	}

	public static void sendShipPacket(EntitySpaceship spaceship) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);

		try {
			dataStream.writeInt(spaceship.entityId);
						
			PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(ModInfo.CHANNEL, byteStream.toByteArray()));		
		}catch(IOException ex) {
			System.err.append("Failed to send spaceship drop packet");
		}
		
	}
	
}
