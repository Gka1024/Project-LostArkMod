package net.Locke.lostarkmod.network;

import net.Locke.lostarkmod.block.entity.StoneCarvingTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StoneCarvePacket {
    private final BlockPos pos;
    private final int option;

    public StoneCarvePacket(BlockPos pos, int option) {
        this.pos = pos;
        this.option = option;
    }

    public StoneCarvePacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.option = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeInt(option);
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeInt(option);
    }

    public static StoneCarvePacket decode(FriendlyByteBuf buf) {
        return new StoneCarvePacket(buf);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                Level level = player.level();
                BlockEntity blockEntity = level.getBlockEntity(pos);
                if (blockEntity instanceof StoneCarvingTableBlockEntity) {
                    ((StoneCarvingTableBlockEntity) blockEntity).stoneCarve(option);
                }
            }
        });
        return true;
    }
}
