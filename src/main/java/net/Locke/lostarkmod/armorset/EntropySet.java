package net.Locke.lostarkmod.armorset;

import java.util.List;

import net.Locke.lostarkmod.skill.entropy.EntropySkill1;
import net.Locke.lostarkmod.skill.entropy.EntropySkill2;
import net.Locke.lostarkmod.skill.salvation.SalvationSkill1;
import net.Locke.lostarkmod.skill.salvation.SalvationSkill2;
import net.minecraft.world.entity.player.Player;

public class EntropySet extends ArmorSet {

    public EntropySet() {
        super("Salvation", List.of(new EntropySkill1(), new EntropySkill2()));
    }

}
