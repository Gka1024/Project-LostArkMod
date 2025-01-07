package net.Locke.lostarkmod.skills.salvation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
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
    static long CHARGE_START_TIME = 0;
    static Boolean isSkillAvailable = false;

    public static void tick()
    {
        //checkBowSkillCooltime();
        if(isSkillHolding)
        {
            showCurrentKeyDown(getCurrentGameTime() - CHARGE_START_TIME, 30 , 40);
        }
    }

    private static long getCurrentGameTime()
    {
        if(Minecraft.getInstance().level != null)
        {
            return Minecraft.getInstance().level.getGameTime();
        }
        return 0;
    }


    public static void skillUse(Player player) {
        ItemStack item = player.getMainHandItem();
        if (item.getItem() instanceof BowItem) {
            bowSkillUse(player);
        }
        else if(item.getItem() instanceof CrossbowItem)
        {
            crossbowSkillCharge();

            isSkillHolding = true;
            CHARGE_START_TIME = getCurrentGameTime();
        }
        
    }

    public static void skillUse(Player player, int chargeTime) {
        ItemStack item = player.getMainHandItem();
        if (item.getItem() instanceof CrossbowItem) {
            crossBowSkillUse(chargeTime);
            isSkillHolding = false;
        }
    }

    private static void bowSkillUse(Player player) {

        if(isSkillAvailable)
        {
            playerKnockBack(player);
            createArrow(player, 2.0f);
            isSkillAvailable = false;

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

        Vec3 lookVector = getLookVector(player);
        arrow.setDeltaMovement(lookVector.scale(ArrowSpeed));
        arrow.setOwner(player);

        world.addFreshEntity(arrow);
    }

    private static void crossBowSkillUse(int time)
    {
        
        
    }

    private static void crossbowSkillCharge()
    {
        long curTime = Minecraft.getInstance().level.getGameTime();

    }

    private static void showCurrentKeyDown(long timeTicks, int startTick, int endTick) // 50
    {
        LocalPlayer player = Minecraft.getInstance().player;

        if(player == null)
        {
            return;
        }

        String displayString = "";

        for (int i = 0; i < 15; i++) {
            if (timeTicks > (i * 10) / 3) {
                displayString += "=";
            } else {
                displayString += "-";
            }
            if (i == (startTick * 3) / 10 || i == (startTick * 3) / 10) {
                displayString += "|";
            }
        }

        player.displayClientMessage(Component.literal(displayString), true);
    }
    
}
