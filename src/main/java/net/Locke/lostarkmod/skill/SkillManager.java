package net.Locke.lostarkmod.skill;

import java.util.HashMap;
import java.util.Map;

import net.Locke.lostarkmod.armorset.ArmorSet;
import net.Locke.lostarkmod.armorset.PlayerArmorSetState;
import net.Locke.lostarkmod.skill.entropy.*;
import net.Locke.lostarkmod.skill.hallucination.*;
import net.Locke.lostarkmod.skill.salvation.*;
import net.minecraft.world.entity.player.Player;

public class SkillManager {

    private static final Map<Player, Map<Skill, SkillState>> playerSkillStates = new HashMap<>();
    private static final Map<String, Skill> skillRegistry = new HashMap<>();

    static {
        // 스킬을 등록
        registerSkill(new EntropySkill1());
        registerSkill(new EntropySkill2());
        registerSkill(new HallucinationSkill1());
        registerSkill(new HallucinationSkill2());
        registerSkill(new SalvationSkill1());
        registerSkill(new SalvationSkill2());
    }

    private static void registerSkill(Skill skill) {
        skillRegistry.put(skill.getName(), skill);
    }

    public static Skill getSkillByName(String name) {
        return skillRegistry.get(name);
    }

    public static SkillState getSkillState(Player player, Skill skill) {
        return playerSkillStates
                .computeIfAbsent(player, k -> new HashMap<>())
                .computeIfAbsent(skill, k -> new SkillState());
    }

    public static void tick(Player player) {
        Map<Skill, SkillState> skillStates = playerSkillStates.get(player);
        if (skillStates != null) {
            skillStates.forEach((skill, state) -> skill.tick(player));
        }
    }

    public static void useSkill(Player player, int skillIndex) {
        Skill skill = getSkillByIndex(player, skillIndex);
        if (skill != null) {
            skill.useSkill(player);
        }
    }

    private static Skill getSkillByIndex(Player player, int skillIndex) {
        ArmorSet set = PlayerArmorSetState.getSet(player);
        Skill skill = set.getSkill(skillIndex);
        return skill;
    }

}
