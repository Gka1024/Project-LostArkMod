package net.Locke.lostarkmod.armorset;

import java.util.HashMap;
import java.util.Map;

import net.Locke.lostarkmod.armorset.playerarmor.DefaultArmorSet;
import net.Locke.lostarkmod.armorset.playerarmor.EntropySet;
import net.Locke.lostarkmod.armorset.playerarmor.HallucinationSet;
import net.Locke.lostarkmod.armorset.playerarmor.NightmareSet;
import net.Locke.lostarkmod.armorset.playerarmor.SalvationSet;
import net.Locke.lostarkmod.armorset.playerarmor.YearningSet;
import net.Locke.lostarkmod.effect.ModEffects;
import net.Locke.lostarkmod.skill.SkillManager;
import net.Locke.lostarkmod.skill.common.Skill;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class PlayerArmorSetState {
    private static Map<Player, ArmorSet> playerArmorset = new HashMap<>();

    public static void assingnSet(Player player, ArmorSet set) {
        playerArmorset.put(player, set);
    }

    // 특정 이펙트에 따라 직업을 설정하는 메소드
    public static void checkAndAssignJobBasedOnEffects(Player player) {
        ArmorSet newSet = null;
        ArmorSet currentSet = getSet(player);
        if (player.hasEffect(ModEffects.SET_SALVATION.get())) {
            newSet = new SalvationSet();
        }
        if (player.hasEffect(ModEffects.SET_ENTROPY.get())) {
            newSet = new EntropySet();
        }
        if (player.hasEffect(ModEffects.SET_HALLUCINATION.get())) {
            newSet = new HallucinationSet();
        }
        if (player.hasEffect(ModEffects.SET_NIGHTMARE.get())) {
            newSet = new NightmareSet();
        }
        if (player.hasEffect(ModEffects.SET_YEARNING.get())) {
            newSet = new YearningSet();
        }

        if(newSet == null)
        {
            newSet = new DefaultArmorSet(); 
            playerArmorset.remove(player);
            SkillManager.removePlayerSkills(player);
        }
        else if(currentSet == null && newSet != null)
        {
            assingnSet(player, newSet);
        }
    }

    public static ArmorSet getSet(Player player)
    {
        return playerArmorset.get(player);
    }
}
