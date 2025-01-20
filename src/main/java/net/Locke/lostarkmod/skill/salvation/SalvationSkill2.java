package net.Locke.lostarkmod.skill.salvation;

import net.Locke.lostarkmod.skill.common.Skill;
import net.Locke.lostarkmod.skill.common.SkillUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SalvationSkill2 extends Skill {

    private static boolean isSkillHolding = false;

    static long LAST_BOW_SKILL_USED_TIME = 0;
    static int BOW_SKILL_COOLDOWN = 60; // 3sec
    static Boolean isBowSkillAvailable = true;
    static int BOW_SKILL_MANA_COST = 20;

    static long LAST_CROSSBOW_SKILL_USED_TIME = 0;
    static long CHARGE_START_TIME = 0;
    static int CROSSBOW_SKILL_COOLDOWN = 00; // 10sec
    static Boolean isCrossbowSkillAvailable = true;
    static int CROSSBOW_SKILL_MANA_COST = 40;

    static int CROSSBOW_SKILL_CHARGE_MIN = 30;
    static int CROSSBOW_SKILL_CHARGE_MAX = 40;

    public SalvationSkill2()
    {
        super("Shoot", 0, 30);
    }

    @Override
    public void tick(Player player) {
        checkBowSkillCooltime();
        checkCrossbowSkillCooltime();

        if (isSkillHolding && isCrossbowSkillAvailable) {
            showCurrentKeyDown(getCurrentGameTime() - CHARGE_START_TIME, 30, 40);
        }
    }

    @Override
    public void useSkill(Player player) {
        ItemStack item = player.getMainHandItem();
        if (item.getItem() instanceof BowItem) {
            bowSkillUse(player);
        } else if (item.getItem() instanceof CrossbowItem) {
            if (!isSkillHolding) {
                isSkillHolding = true;
                CHARGE_START_TIME = getCurrentGameTime();
            }
        }
    }

    public static void useSkill(Player player, int chargeTime) {
        ItemStack item = player.getMainHandItem();
        if (item.getItem() instanceof CrossbowItem) {
            crossBowSkillUse(player, chargeTime);
            isSkillHolding = false;
        }
    }

    private static void bowSkillUse(Player player) {

        if (!isBowSkillAvailable) {
            player.displayClientMessage(Component.literal("아직 사용할 수 없습니다."), true);
            return;
        }

        if (SkillUtil.checkPlayerMana(player, 20)) {
            playerKnockBack(player);
            createArrow(player, 2.0f);
            LAST_BOW_SKILL_USED_TIME = getCurrentGameTime();
            isBowSkillAvailable = false;
            SkillUtil.useMana(player, 20);
        } else {
            player.displayClientMessage(Component.literal("마나가 부족합니다."), true);
            return;
        }
    }

    private static void crossBowSkillUse(Player player, int time) {

        if (!isCrossbowSkillAvailable) {
            player.displayClientMessage(Component.literal("아직 사용할 수 없습니다."), true);
            return;
        }

        if (SkillUtil.checkPlayerMana(player, CROSSBOW_SKILL_MANA_COST)) {
            LAST_CROSSBOW_SKILL_USED_TIME = getCurrentGameTime();
            isCrossbowSkillAvailable = false;
            SkillUtil.useMana(player, CROSSBOW_SKILL_MANA_COST);
            if (time > CROSSBOW_SKILL_CHARGE_MIN && time < CROSSBOW_SKILL_CHARGE_MAX) {
                playerKnockBack(player);
                createArrow(player, 3.5f);
            } else {
                createArrow(player, 1.0f);
            }
        } else {
            player.displayClientMessage(Component.literal("마나가 부족합니다."), true);
            return;
        }
    }

    private static void checkBowSkillCooltime() {
        if (LAST_BOW_SKILL_USED_TIME + BOW_SKILL_COOLDOWN < getCurrentGameTime()) {
            isBowSkillAvailable = true;
        }
    }

    private static void checkCrossbowSkillCooltime() {
        if (LAST_CROSSBOW_SKILL_USED_TIME + CROSSBOW_SKILL_COOLDOWN < getCurrentGameTime()) {
            isCrossbowSkillAvailable = true;
        }
    }

    private static Vec3 getLookVector(Player player) {
        return player.getLookAngle().normalize();
    }

    private static void showCurrentKeyDown(long timeTicks, int startTick, int endTick) // 50
    {
        LocalPlayer player = Minecraft.getInstance().player;

        if (player == null) {
            return;
        }

        String displayString = "";

        for (int i = 0; i < 15; i++) {
            if (timeTicks > (i * 10) / 3) {
                displayString += "=";
            } else {
                displayString += "-";
            }
            if (i == (startTick * 3) / 10 || i == (endTick * 3) / 10) {
                displayString += "|";
            }
        }

        player.displayClientMessage(Component.literal(displayString), true);
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

    private static long getCurrentGameTime() {
        return SkillUtil.getCurrentGameTime();
    }

    

    @Override
    protected void activateSkill(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateSkill'");
    }

}
