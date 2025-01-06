package net.Locke.lostarkmod.network.packets;

import net.Locke.lostarkmod.event.set_effects.salvation.InfiniteArrowHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;
public class CrossbowArrowSpeedPacket {
    private final int arrowSpeed;

    public CrossbowArrowSpeedPacket(int speed) {
        this.arrowSpeed = speed;
    }

    public static void encode(CrossbowArrowSpeedPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.arrowSpeed);
    }

    public static CrossbowArrowSpeedPacket decode(FriendlyByteBuf buffer) {
        return new CrossbowArrowSpeedPacket(buffer.readInt());
    }

    public static void handle(CrossbowArrowSpeedPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
           Player player = context.getSender();
            if(player != null)
            {
                float speed = packet.arrowSpeed / 10f + 0.125f;
                InfiniteArrowHandler.setArrowSpeed(speed);
            }

        });
        context.setPacketHandled(true);
    }
}
