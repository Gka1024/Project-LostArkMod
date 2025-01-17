package net.Locke.lostarkmod.skill;

import net.Locke.lostarkmod.capability.Mana;
import net.Locke.lostarkmod.capability.ManaProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class SkillUtil {
    public static void useMana(Player player, int manaCost) {
        player.getCapability(ManaProvider.MANA_CAPABILITY).ifPresent(manaCap -> {
            if (manaCap.useMana(manaCost)) {
                // 마나 소모 성공 시 스킬 발동
                Mana.syncManaToClient(player);

            } else {
                // 마나 부족 시 메시지 표시
                player.displayClientMessage(Component.literal("마나가 부족합니다."), true);
            }
        });
    }

    public static boolean checkPlayerMana(Player player, int manaCost) {
        return player.getCapability(ManaProvider.MANA_CAPABILITY)
                .map(manaCap -> manaCap.checkMana(manaCost))
                .orElse(false);
    }

    public static void regainMana(Player player, int mana) {
        player.getCapability(ManaProvider.MANA_CAPABILITY).ifPresent(manaCap -> {
            manaCap.manaRegen(mana);
            Mana.syncManaToClient(player);
        });
    }

    public static long getCurrentGameTime() {
        if (Minecraft.getInstance().level != null) {
            return Minecraft.getInstance().level.getGameTime();
        }
        return 0;
    }
}
