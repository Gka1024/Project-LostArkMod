package net.Locke.lostarkmod.network;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class SkillUsePacket {

    int mana = 0;
    public SkillUsePacket(int currentMana)
    {
        this.mana = currentMana;
    }

    public static void encode(SkillUsePacket packet, FriendlyByteBuf buffer)
    {
        buffer.writeInt(packet.mana);
    }


    public static SkillUsePacket decode(FriendlyByteBuf buffer)
    {
        return new SkillUsePacket(buffer.readInt());
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if( player != null)
            {
                handleSkillUse(player);
            }
        });
        context.setPacketHandled(true);
    }

    private void handleSkillUse(ServerPlayer player)
    {
        
    }
}
