package addfeat.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import addfeat.common.ModInfo;
import addfeat.common.client.interfaces.ContainerCrate;
import addfeat.common.logic.LogicHeat;
import addfeat.common.tileentities.TileEntityCrate;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);

		EntityPlayer entityPlayer = (EntityPlayer) player;

		byte packetId = reader.readByte();

		switch (packetId) {
		case 0:
			byte buttonId = reader.readByte();
			Container container = entityPlayer.openContainer;
			if (container != null && container instanceof ContainerCrate) {
				TileEntityCrate crate = ((ContainerCrate) container).getCrate();
				crate.receiveButtonEvent(buttonId);
			}
			break;
			
		case 1:
			byte effectId = reader.readByte();
			int x = reader.readInt();
			int y = reader.readInt();
			int z = reader.readInt();
			
			LogicHeat logic = new LogicHeat();
			if(logic != null) {
				logic.receiveEntityFXPacket(effectId, x, y, z);
			}
		}

	}

	public static void sendButtonPacket(byte id) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);

		try {
			dataStream.writeByte((byte) 0);
			dataStream.writeByte(id);

			PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(
					ModInfo.CHANNEL, byteStream.toByteArray()));
		} catch (IOException ex) {
			System.err.append("Failed to send button click packet");
		}
	}

	public static void sendEffectPacket(byte id, int x, int y, int z,
			int dimID) {

		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);

		try {
			dataStream.writeByte((byte) 1);
			dataStream.writeByte(id);
			dataStream.writeInt(x);
			dataStream.writeInt(y);
			dataStream.writeInt(z);

			PacketDispatcher.sendPacketToAllAround(
					x,
					y,
					z,
					100,
					dimID,
					PacketDispatcher.getPacket(ModInfo.CHANNEL,
							byteStream.toByteArray()));

		} catch (IOException ex) {
			System.err.append("Failed To Send Smoke Effect Packet");
			
		}
	}
}
