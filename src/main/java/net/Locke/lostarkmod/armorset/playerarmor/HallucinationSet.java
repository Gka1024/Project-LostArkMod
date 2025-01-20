package net.Locke.lostarkmod.armorset.playerarmor;

import java.util.List;

import net.Locke.lostarkmod.armorset.ArmorSet;
import net.Locke.lostarkmod.skill.salvation.SalvationSkill1;
import net.Locke.lostarkmod.skill.salvation.SalvationSkill2;

public class HallucinationSet extends ArmorSet {

    public HallucinationSet() {
        super("HallucinationSet", List.of(new SalvationSkill1(), new SalvationSkill2()));
    }

}
