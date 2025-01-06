package net.Locke.lostarkmod.network.packets;

import net.Locke.lostarkmod.block.entity.StoneCarvingTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RemoveItemPacket {
    private final BlockPos pos; // 블록 위치
    private final int slot; // 슬롯 번호
    private final int count; // 제거할 아이템 개수

    public RemoveItemPacket(BlockPos pos, int slot, int count) {
        this.pos = pos;
        this.slot = slot;
        this.count = count;
    }

    // 패킷 데이터를 작성하는 메서드 (클라이언트 -> 서버로 전송)
    public static void encode(RemoveItemPacket packet, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(packet.pos);
        buffer.writeInt(packet.slot);
        buffer.writeInt(packet.count);
    }

    // 패킷 데이터를 읽는 메서드 (서버에서 수신)
    public static RemoveItemPacket decode(FriendlyByteBuf buffer) {
        BlockPos pos = buffer.readBlockPos();
        int slot = buffer.readInt();
        int count = buffer.readInt();
        return new RemoveItemPacket(pos, slot, count);
    }

    // 서버에서 패킷을 처리하는 메서드
    public static void handle(RemoveItemPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // 서버 측 로직
            if (context.getSender() != null) {
                // getCommandSenderWorld()로 월드를 가져옵니다.
                ServerLevel level = (ServerLevel) context.getSender().getCommandSenderWorld();
                StoneCarvingTableBlockEntity blockEntity = (StoneCarvingTableBlockEntity) level
                        .getBlockEntity(packet.pos);
                if (blockEntity != null) {
                    // 지정한 슬롯에서 아이템 제거
                    blockEntity.removeItemFromSlot(packet.slot, packet.count);
                }
            }
        });
        context.setPacketHandled(true);
    }
}
