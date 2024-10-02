package net.Locke.lostarkmod.item.custom;

import net.minecraft.world.item.Item;

public class AbilityStone extends Item{

    public AbilityStone(Properties pProperties) {
        super(pProperties);
    }

    public enum ablityType{
        RAISE_ATTACK,

    }

    public int opt1, opt2, opt3;
}
