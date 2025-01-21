package net.Locke.lostarkmod.armorset.playerarmor;

import java.util.List;

import net.Locke.lostarkmod.armorset.ArmorSet;
import net.Locke.lostarkmod.skill.hallucination.HallucinationSkill1;
import net.Locke.lostarkmod.skill.hallucination.HallucinationSkill2;

public class YearningSet extends ArmorSet {

    public YearningSet() {
        super("HallucinationSet", List.of(new HallucinationSkill1(), new HallucinationSkill2()));
    }

}
