package net.Locke.lostarkmod.skill.salvation;

import net.Locke.lostarkmod.skill.common.Skill;
import net.Locke.lostarkmod.skill.common.SkillUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SalvationSkillCrossbow extends Skill {

    public SalvationSkillCrossbow() {
        super("Shoot", 60, 200);
    }

    private static final int CROSSBOW_SKILL_CHARGE_MIN = 30;
    private static final int CROSSBOW_SKILL_CHARGE_MAX = 40;

    @Override
    public void tick(Player player) {
        getSkillState(player).tick(player);
        super.tick(player);
    }

    @Override
    public void useSkill(Player player) {

        super.useSkill(player);
    }

    public static void crossBowSkillUse(Player player, int time) {

        if (time > CROSSBOW_SKILL_CHARGE_MIN && time < CROSSBOW_SKILL_CHARGE_MAX) {
            playerKnockBack(player);
            createArrow(player, 3.5f);
        } else {
            createArrow(player, 1.0f);
        }
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
