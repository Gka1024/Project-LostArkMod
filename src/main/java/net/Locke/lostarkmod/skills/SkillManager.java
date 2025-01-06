package net.Locke.lostarkmod.skills;

import net.Locke.lostarkmod.effect.ModEffects;
import net.Locke.lostarkmod.skills.salvation.*;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;

public class SkillManager {
    public static void executeSkill(ServerPlayer player, int key)
    {
        if(player.hasEffect(ModEffects.SET_SALVATION.get()))
        {
            if(key == 1)
            {
                SalvationSkill1.skillUse(player);
            }
            else if(key == 2)
            {
                SalvationSkill2.skillUse(player);
            }
        }
    }

    public static void executeKeydownSkill(ServerPlayer player, int key, int chargeTime)
    {
        if(player.hasEffect(ModEffects.SET_SALVATION.get()))
        {
            if(key == 2)
            {
                if(player.getMainHandItem().getItem() instanceof CrossbowItem)
                SalvationSkill2.skillUse(player, chargeTime);
            }
        }
    }
}
