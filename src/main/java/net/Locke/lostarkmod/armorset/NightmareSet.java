package net.Locke.lostarkmod.armorset;

import java.util.List;

import net.Locke.lostarkmod.skill.salvation.SalvationSkill1;
import net.Locke.lostarkmod.skill.salvation.SalvationSkill2;
import net.minecraft.world.entity.player.Player;

public class NightmareSet extends ArmorSet{

    public NightmareSet()
    {
        super("NightmareSet", List.of(new SalvationSkill1(), new SalvationSkill2()));
    }

    

}
