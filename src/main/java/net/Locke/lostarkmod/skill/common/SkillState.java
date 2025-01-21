package net.Locke.lostarkmod.skill.common;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class SkillState {
    public long LAST_SKILL_USED_TIME;
    public int coolDownTime;
    public int tickCounter;
    public boolean isSkillHolding;

    public void resetCooltime()
    {
        this.coolDownTime = 0;
        this.tickCounter = 0;
    }

    public void setCooltime(int coolDownTime)
    {
        this.coolDownTime = coolDownTime;
    }

    public void tick(Player player)
    {
        if (this.coolDownTime > 0)
        {
            this.tickCounter++;
            if (this.tickCounter >= this.coolDownTime)
            {
                this.coolDownTime = 0;
                this.tickCounter = 0;
            }
        }
        if(isSkillHolding)
        {

        }
    }

    public boolean isManaAvailable(Player player, int manaCost)
    {
        return SkillUtil.checkPlayerMana(player, manaCost);
    }

    public boolean isSkillReady()
    {
        return this.coolDownTime == 0;
    }

    protected static void showCurrentKeyDown(Player player, long timeTicks, int startTick, int endTick) // 키 다운 시간 표시 최대 50틱
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
