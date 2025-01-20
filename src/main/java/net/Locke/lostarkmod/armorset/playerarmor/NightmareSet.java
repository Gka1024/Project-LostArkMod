package net.Locke.lostarkmod.armorset.playerarmor;

import java.util.List;

import net.Locke.lostarkmod.armorset.ArmorSet;
import net.Locke.lostarkmod.skill.salvation.SalvationSkill1;
import net.Locke.lostarkmod.skill.salvation.SalvationSkill2;

public class NightmareSet extends ArmorSet{

    public NightmareSet()
    {
        super("NightmareSet", List.of(new SalvationSkill1(), new SalvationSkill2()));
    }

    

}
