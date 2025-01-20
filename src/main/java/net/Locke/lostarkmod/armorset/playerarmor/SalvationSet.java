package net.Locke.lostarkmod.armorset.playerarmor;

import java.util.List;

import net.Locke.lostarkmod.armorset.ArmorSet;
import net.Locke.lostarkmod.skill.salvation.SalvationSkill1;
import net.Locke.lostarkmod.skill.salvation.SalvationSkill2;
import net.minecraft.world.entity.player.Player;

public class SalvationSet extends ArmorSet {

    public SalvationSet() {
        super("Salvation", List.of(new SalvationSkill1(), new SalvationSkill2()));
    }

}
