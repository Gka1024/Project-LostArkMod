package net.Locke.lostarkmod.skill.salvation;

import net.Locke.lostarkmod.skill.common.Skill;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SalvationSkill1 extends Skill {

    @Override
    public void tick(Player player) {

    }

    public SalvationSkill1() {
        super("Change", 0, 0);
    }

    @Override
    public void useSkill(Player player) {
        ItemStack item = player.getMainHandItem();
        if (item.getItem() instanceof BowItem) {
            ItemStack crossbow = new ItemStack(Items.CROSSBOW);

            player.setItemInHand(InteractionHand.MAIN_HAND, crossbow);
        }
        if (item.getItem() instanceof CrossbowItem) {
            ItemStack bow = new ItemStack(Items.BOW);

            player.setItemInHand(InteractionHand.MAIN_HAND, bow);
        }
    }

}
