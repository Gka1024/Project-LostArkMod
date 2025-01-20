package net.Locke.lostarkmod.armorset.playerarmor;

import java.util.List;

import net.Locke.lostarkmod.armorset.ArmorSet;
import net.Locke.lostarkmod.skill.entropy.EntropySkill1;
import net.Locke.lostarkmod.skill.entropy.EntropySkill2;

public class EntropySet extends ArmorSet {

    public EntropySet() {
        super("Salvation", List.of(new EntropySkill1(), new EntropySkill2()));
    }

}
