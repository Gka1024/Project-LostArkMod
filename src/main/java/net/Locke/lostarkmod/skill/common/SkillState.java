package net.Locke.lostarkmod.skill.common;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class SkillState {
    public long LAST_SKILL_USED_TIME;
    public boolean isSkillHolding = false;

    public void tick(Player player) {
        System.out.println("tickCounter: ");
        if (isSkillHolding) {
            showCurrentKeyDown(player, player.level().getGameTime() - LAST_SKILL_USED_TIME, 30, 40);
        } else {
            LAST_SKILL_USED_TIME = player.level().getGameTime();
        }
    }

    protected static void showCurrentKeyDown(Player player, long timeTicks, int startTick, int endTick) // 키 다운 시간 표시 최대
    {
        if (player == null) {
            return;
        }

        String displayString = "";

        for (int i = 0; i < 15; i++) {
            if (timeTicks > (i * 10) / 3) {
                displayString += "=";
            } else {
                displayString += "-";
            }
            if (i == (startTick * 3) / 10 || i == (endTick * 3) / 10) {
                displayString += "|";
            }
        }

        player.displayClientMessage(Component.literal(displayString), true);
    }

}
