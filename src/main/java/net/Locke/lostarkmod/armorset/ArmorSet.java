package net.Locke.lostarkmod.armorset;

import java.util.List;

import net.Locke.lostarkmod.skill.common.Skill;
import net.minecraft.world.entity.player.Player;

public abstract class ArmorSet {
    protected String name;
    protected List<Skill> skills;

    public ArmorSet(String name, List<Skill> skills)
    {
        this.name = name;
        this.skills = skills;
    }

    public String getName()
    {
        return this.name;
    }

    public List<Skill> getSkills()
    {
        return this.skills;
    }

    public Skill getSkill(int index)
    {
        return skills.get(index);
    }
}
