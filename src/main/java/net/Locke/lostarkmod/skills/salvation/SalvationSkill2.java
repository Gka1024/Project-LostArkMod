package net.Locke.lostarkmod.skills.salvation;

import net.Locke.lostarkmod.capability.IMana;
import net.Locke.lostarkmod.capability.Mana;
import net.Locke.lostarkmod.capability.ManaProvider;
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

public class SalvationSkill2 {

    private static boolean isSkillHolding = false;

    static int SKILL_BOW_DELAY_TICKS = 60;
    static long LAST_BOW_SKILL_USED_TIME = 0;
    static long LAST_CROSSBOW_SKILL_USED_TIME = 0;
    static long CHARGE_START_TIME = 0;

    static long BOW_SKILL_COOLTIME = 60; // 3sec
    static long CROSSBOW_SKILL_COOLTIME = 200; // 10sec
    static Boolean isBowSkillAvailable = true;
    static Boolean isCrossbowSkillAvailable = true;

    public static void tick() {
        checkBowSkillCooltime();
        checkCrossbowSkillCooltime();

        if (isSkillHolding && isCrossbowSkillAvailable) {
            showCurrentKeyDown(getCurrentGameTime() - CHARGE_START_TIME, 30, 40);
        }
    }

    private static long getCurrentGameTime() {
        if (Minecraft.getInstance().level != null) {
            return Minecraft.getInstance().level.getGameTime();
        }
        return 0;
    }

    public static void skillUse(Player player) {
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

    public static void skillUse(Player player, int chargeTime) {
        ItemStack item = player.getMainHandItem();
        if (item.getItem() instanceof CrossbowItem) {
            crossBowSkillUse(player, chargeTime);
            isSkillHolding = false;
        }
    }

    private static void bowSkillUse(Player player) {
        if (isBowSkillAvailable) {
            playerKnockBack(player);
            createArrow(player, 2.0f);
            LAST_BOW_SKILL_USED_TIME = getCurrentGameTime();
            isBowSkillAvailable = false;
            useMana(player, 20);
        }
    }

    private static void checkBowSkillCooltime() {
        if (LAST_BOW_SKILL_USED_TIME + BOW_SKILL_COOLTIME < getCurrentGameTime()) {
            isBowSkillAvailable = true;
        }
    }

    private static void checkCrossbowSkillCooltime() {
        if (LAST_CROSSBOW_SKILL_USED_TIME + CROSSBOW_SKILL_COOLTIME < getCurrentGameTime()) {
            isCrossbowSkillAvailable = true;
        }
    }

    private static Vec3 getLookVector(Player player) {
        return player.getLookAngle().normalize();
    }

    private static void crossBowSkillUse(Player player, int time) {

        if (isCrossbowSkillAvailable) {
            LAST_CROSSBOW_SKILL_USED_TIME = getCurrentGameTime();
            isCrossbowSkillAvailable = false;
            useMana(player, 40);
            if (time > 30 && time < 40) {
                playerKnockBack(player);
                createArrow(player, 20f);
            } else {
                createArrow(player, 2.0f);
            }
        }
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
        /*
         * Vec3 lookVector = getLookVector(player);
         * Vec3 arrowVector = lookVector.normalize();
         * arrow.setDeltaMovement(arrowVector.scale(ArrowSpeed));
         */
        arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, ArrowSpeed, 0F);
        // System.out.println(lookVector);
        // 화살 데미지는 root(speed ^2) * 2
        arrow.setOwner(player);
        arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        world.addFreshEntity(arrow);
    }

    private static void useMana(Player player, int mana) {
        IMana playerMana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElse(new Mana());
        playerMana.useMana(mana);
    }

    private static boolean checkPlayerMana(Player player, int mana) {
        IMana playerMana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElse(new Mana());
        if (playerMana.getMana() > mana) {
            return true;
        }
        return false;
    }

}
