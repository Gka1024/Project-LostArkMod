package net.Locke.lostarkmod.skills.salvation;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SalvationSkill2 {
    public static void skillUse(Player player) {
        ItemStack item = player.getMainHandItem();
        if (item.getItem() instanceof BowItem) {
            bowSkillUse(player);
        }
    }

    public static void skillUse(Player player, int chargeTime) {
        ItemStack item = player.getMainHandItem();
        if (item.getItem() instanceof CrossbowItem) {
            crossBowSkillUse(chargeTime);
        }
    }

    private static void bowSkillUse(Player player) {
        playerKnockBack(player);
        createArrow(player, 2.0f);
    }

    private static Vec3 getLookVector(Player player) {
        return player.getLookAngle().normalize();
    }

    private static void playerKnockBack(Player player) {
        Vec3 lookVector = getLookVector(player);
        Vec3 knockbackVector = lookVector.scale(-0.1);

        player.push(knockbackVector.x, 0.2, knockbackVector.z);
        player.hurtMarked = true;
    }

    private static void createArrow(Player player, float ArrowSpeed) {
        Level world = player.level();
        Arrow arrow = new Arrow(world, player);

        Vec3 lookVector = getLookVector(player);
        arrow.setDeltaMovement(lookVector.scale(ArrowSpeed));
        arrow.setOwner(player);

        world.addFreshEntity(arrow);
    }

    private static void crossBowSkillUse(int time)
    {
        
    }
}
