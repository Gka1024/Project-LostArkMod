package net.Locke.lostarkmod.armorset.playerarmor;

import java.util.List;

import net.Locke.lostarkmod.armorset.ArmorSet;
import net.Locke.lostarkmod.skill.salvation.*;

public class SalvationSet extends ArmorSet {

    public SalvationSet() {
        super("Salvation", List.of(new SalvationSkill1(), new SalvationSkillBow(), new SalvationSkillCrossbow()));
    }

}
