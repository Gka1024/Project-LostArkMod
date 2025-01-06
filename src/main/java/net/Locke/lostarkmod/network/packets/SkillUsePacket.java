package net.Locke.lostarkmod.network.packets;

import java.util.function.Supplier;

import net.Locke.lostarkmod.skills.SkillManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class SkillUsePacket {

    int mana = 0;
    int skillNum = 0;
    int chargeTime = 0;
    

    public SkillUsePacket(int currentMana, int num, int chargeTime)
    {
        this.mana = currentMana;
        this.skillNum = num;
        this.chargeTime = chargeTime;
    }

    public static void encode(SkillUsePacket packet, FriendlyByteBuf buffer)
    {
        buffer.writeInt(packet.mana);
        buffer.writeInt(packet.skillNum);
        buffer.writeInt(packet.chargeTime);
    }


    public static SkillUsePacket decode(FriendlyByteBuf buffer)
    {
        int mana = buffer.readInt();
        int skillNum = buffer.readInt();
        int chargeTime = buffer.readInt();
        return new SkillUsePacket(mana, skillNum, chargeTime);
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier)
    {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if(player != null)
            {
                if(chargeTime == 0)
                {
                    handleSkillUse(player, skillNum);
                }
                else
                {
                    handleKeyDownSkillUse(player, skillNum, chargeTime);
                }
            }
        });
        context.setPacketHandled(true);
    }

    private void handleSkillUse(ServerPlayer player, int skillNum)
    {
        SkillManager.executeSkill(player, skillNum);
    }

    private void handleKeyDownSkillUse(ServerPlayer player, int skillNum, int chargeTime)
    {
        SkillManager.executeKeydownSkill(player, skillNum, chargeTime);
    }
}
