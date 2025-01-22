package net.Locke.lostarkmod.skill.salvation;

import net.Locke.lostarkmod.skill.common.Skill;
import net.Locke.lostarkmod.skill.common.SkillUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SalvationSkillBow extends Skill{

    public SalvationSkillBow() {
        super("Shoot", 20, 60);
    }

    public static void bowSkillUse(Player player) {
        playerKnockBack(player);
        createArrow(player, 2.0f);
    }

    @Override
    public void useSkill(Player player) {
        super.useSkill(player);
        bowSkillUse(player);
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

        arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, ArrowSpeed, 0.1F);
        arrow.setOwner(player);
        arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        world.addFreshEntity(arrow);
    }

}
