package net.Locke.lostarkmod.network.packets;

import net.Locke.lostarkmod.capability.ManaProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaSyncPacket {
    private final int maxMana;

    public ManaSyncPacket(int maxMana) {
        this.maxMana = maxMana;
    }

    public static void encode(ManaSyncPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.maxMana);
    }

    public static ManaSyncPacket decode(FriendlyByteBuf buffer) {
        return new ManaSyncPacket(buffer.readInt());
    }

    public static void handle(ManaSyncPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // 클라이언트 측에서 최대 마나 업데이트
            Minecraft.getInstance().player.getCapability(ManaProvider.MANA_CAPABILITY).ifPresent(mana -> {
                mana.setMaxMana(packet.maxMana);
            });
        });
        context.setPacketHandled(true);
    }
}
