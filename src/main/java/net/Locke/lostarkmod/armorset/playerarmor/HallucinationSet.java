package net.Locke.lostarkmod.armorset.playerarmor;

import java.util.List;

import net.Locke.lostarkmod.armorset.ArmorSet;
import net.Locke.lostarkmod.skill.hallucination.*;

public class HallucinationSet extends ArmorSet {

    public HallucinationSet() {
        super("HallucinationSet", List.of(new HallucinationSkill1(), new HallucinationSkill2()));
    }

}
