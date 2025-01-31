package net.Locke.lostarkmod.skill.common;

import net.Locke.lostarkmod.skill.SkillManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public abstract class Skill {
    private final String SkillName;
    private final int manaCost;
    private int coolDownTime;
    private int currentCoolDown;
    protected boolean isActive = false;

    public Skill(String SkillName, int manaCost, int coolDownTime) {
        this.SkillName = SkillName;
        this.manaCost = manaCost;
        this.coolDownTime = coolDownTime;
        this.currentCoolDown = 0;
    }

    public String getName() {
        return SkillName;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void tick(Player player) {
        if(currentCoolDown > 0) {
            System.out.println("CoolDown: " + currentCoolDown);
            currentCoolDown--;
        }
        getSkillState(player).tick(player);   
    }

    public void useSkill(Player player) {
        
        if (!SkillUtil.checkPlayerMana(player, manaCost)) {
            return;
        }

        SkillUtil.useMana(player, manaCost);
        activateSkill(player);
        currentCoolDown = coolDownTime;

    }

    public void useSkill(Player player, int time)
    {
        useSkill(player);
    }

    public int getCoolDownTime()
    {
        return coolDownTime;
    }

    protected SkillState getSkillState(Player player) {
        return SkillManager.getSkillState(player, this);
    }

    protected void activateSkill(Player player)
    {
        
    } // 스킬 발동

    protected void applyEffects(Player player) {
        // 지속 효과용 스킬
    }

    protected void deactivateSkill(Player player) {
    } // 스킬 해제

    public boolean isActive() {
        return isActive;
    }

    protected void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getSkillName() {
        return SkillName;
    }

    public boolean isSkillReady(Player player) {
        return currentCoolDown == 0;
    }

    public void applySkillCooldown(Player player) {
        currentCoolDown = coolDownTime;
    }
}
