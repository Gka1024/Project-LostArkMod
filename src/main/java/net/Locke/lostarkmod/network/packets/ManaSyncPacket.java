package net.Locke.lostarkmod.network.packets;

import net.Locke.lostarkmod.capability.ManaProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaSyncPacket {
    private final int currentMana;
    private final int maxMana;

    // 생성자
    public ManaSyncPacket(int currentMana, int maxMana) {
        this.currentMana = currentMana;
        this.maxMana = maxMana;
    }

    // 패킷 데이터 쓰기
    public static void encode(ManaSyncPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.currentMana);
        buffer.writeInt(packet.maxMana);
    }

    // 패킷 데이터 읽기
    public static ManaSyncPacket decode(FriendlyByteBuf buffer) {
        int currentMana = buffer.readInt();
        int maxMana = buffer.readInt();
        return new ManaSyncPacket(currentMana, maxMana);
    }

    // 패킷 처리
    public static void handle(ManaSyncPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // 클라이언트 측에서 마나 정보 업데이트
            Minecraft.getInstance().player.getCapability(ManaProvider.MANA_CAPABILITY).ifPresent(mana -> {
                mana.setMana(packet.currentMana); // 현재 마나 설정
                mana.setMaxMana(packet.maxMana); // 최대 마나 설정
            });
        });
        context.setPacketHandled(true);
    }
}
