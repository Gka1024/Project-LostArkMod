package net.Locke.lostarkmod.armorset;

import java.util.HashMap;
import java.util.Map;

import net.Locke.lostarkmod.effect.ModEffects;
import net.Locke.lostarkmod.skill.Skill;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class PlayerArmorSetState {
    private static Map<Player, ArmorSet> playerArmorset = new HashMap<>();

    public static void assingnSet(Player player, ArmorSet set) {
        playerArmorset.put(player, set);
    }

    // 특정 이펙트에 따라 직업을 설정하는 메소드
    public static void checkAndAssignJobBasedOnEffects(Player player) {
        ArmorSet currentSet = playerArmorset.get(player);

        ArmorSet newSet = null;

        if (player.hasEffect(ModEffects.SET_SALVATION.get())) { // 예시: 방어력 이펙트를 가진 경우
            newSet = new SalvationSet();
        }

        if(currentSet == null || !currentSet.getName().equals(newSet.getName()))
        {
            assingnSet(player, newSet);
        }
    }

    public static ArmorSet getSet(Player player)
    {
        return playerArmorset.get(player);
    }
}
