package net.Locke.lostarkmod.skill;

import java.util.HashMap;
import java.util.Map;

import net.Locke.lostarkmod.armorset.*;
import net.Locke.lostarkmod.armorset.playerarmor.*;
import net.Locke.lostarkmod.skill.common.Skill;
import net.Locke.lostarkmod.skill.common.SkillState;
import net.Locke.lostarkmod.skill.entropy.*;
import net.Locke.lostarkmod.skill.hallucination.*;
import net.Locke.lostarkmod.skill.salvation.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class SkillManager {

    private static final Map<Player, Map<Skill, SkillState>> playerSkillStates = new HashMap<>();
    private static final Map<String, Skill> skillRegistry = new HashMap<>();

    static {
        // 스킬을 등록
        registerSkill();
    }

    private static void registerSkill() {
        registerSkill(new EntropySkill1());
        registerSkill(new EntropySkill2());
        registerSkill(new HallucinationSkill1());
        registerSkill(new HallucinationSkill2());
        registerSkill(new SalvationSkill1());
        registerSkill(new SalvationSkillBow());
        registerSkill(new SalvationSkillCrossbow());
    }

    private static void registerSkill(Skill skill) {
        skillRegistry.put(skill.getName(), skill);
    }

    public static void removePlayerSkills(Player player) {
        playerSkillStates.remove(player);
    }

    public static Skill getSkillByName(String name) {
        return skillRegistry.get(name);
    }

    public static SkillState getSkillState(Player player, Skill skill) {
        return playerSkillStates
                .computeIfAbsent(player, k -> new HashMap<>())
                .computeIfAbsent(skill, k -> new SkillState());
    }

    public static void setSkillState(Player player, Skill skill, SkillState state) {
        playerSkillStates
                .computeIfAbsent(player, k -> new HashMap<>())
                .put(skill, state);
    }

    public static void tick(Player player) {
        Map<Skill, SkillState> skillStates = playerSkillStates.get(player);
        if (skillStates != null) {
            skillStates.forEach((skill, state) -> skill.tick(player));
        }
    }

    public static void useSkill(Player player, int skillIndex) {
        Skill skill = getSkillByIndex(player, skillIndex);
        ArmorSet set = PlayerArmorSetState.getSet(player);
        if (skill != null && set != null) {
            if (set.getName() == "Salvation" && skillIndex == 2) {
                if (player.getMainHandItem().getItem() == Items.CROSSBOW) {
                    skill = getSkillByIndex(player, skillIndex + 1);
                }
            }

            if (skill.isSkillReady(player)) {
                skillActivate(player, skill);
            }

        }

    }

    public static void useSkill(Player player, int skillIndex, int chargeTime) {
        Skill skill = getSkillByIndex(player, skillIndex);
        ArmorSet set = PlayerArmorSetState.getSet(player);
        if (skill != null && set != null) {
            if (set.getName() == "Salvation" && skillIndex == 2) {
                if (player.getMainHandItem().getItem() == Items.CROSSBOW) {
                    skill = getSkillByIndex(player, skillIndex + 1);
                }
            }
            if (skill instanceof SalvationSkillCrossbow) {
                applySkillCooldown(player, skill);
                skill.useSkill(player, chargeTime);
            }
        }
    }

    private static void skillActivate(Player player, Skill skill)
    {
        applySkillCooldown(player, skill);
        skill.useSkill(player);
    }

    private static void applySkillCooldown(Player player, Skill skill)
    {
        SkillState state = getSkillState(player, skill);
    }

    private static Skill getSkillByIndex(Player player, int skillIndex) {
        ArmorSet set = PlayerArmorSetState.getSet(player);
        Skill skill = set.getSkill(skillIndex - 1);
        return skill;
    }

}
