package net.Locke.lostarkmod.network;

import net.Locke.lostarkmod.block.entity.ArmorForgingTableBlockEntity;
import net.Locke.lostarkmod.block.entity.StoneCarvingTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ArmorForgingPacket {

    private final BlockPos pos; // 블록 위치

    public ArmorForgingPacket(BlockPos pos) {
        this.pos = pos;
    }

    // 패킷 데이터를 작성하는 메서드 (클라이언트 -> 서버로 전송)
    public static void encode(ArmorForgingPacket packet, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(packet.pos);
    }

    // 패킷 데이터를 읽는 메서드 (서버에서 수신)
    public static ArmorForgingPacket decode(FriendlyByteBuf buffer) {
        BlockPos pos = buffer.readBlockPos();
        return new ArmorForgingPacket(pos);
    }

    // 서버에서 패킷을 처리하는 메서드
    public static void handle(ArmorForgingPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // 서버 측 로직
            if (context.getSender() != null) {
                // getCommandSenderWorld()로 월드를 가져옵니다.
                ServerLevel level = (ServerLevel) context.getSender().getCommandSenderWorld();
                ServerPlayer player = contextSupplier.get().getSender();
                ArmorForgingTableBlockEntity blockEntity = (ArmorForgingTableBlockEntity) level
                        .getBlockEntity(packet.pos);
                if (blockEntity != null) {
                    blockEntity.forge(player);
                }
            }
        });
        context.setPacketHandled(true);
    }
}
