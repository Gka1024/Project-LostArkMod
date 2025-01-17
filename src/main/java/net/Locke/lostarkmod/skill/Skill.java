package net.Locke.lostarkmod.skill;

import net.minecraft.world.entity.player.Player;

public abstract class Skill {
    private final String SkillName;
    private final int manaCost;
    private int coolDownTime;
    private int currentCoolDown;
    private boolean isActive = false;

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
        if (currentCoolDown > 0) {
            currentCoolDown--;
        }
    }

    public void useSkill(Player player) {
        if (currentCoolDown != 0) {
            return;
        }

        if (!SkillUtil.checkPlayerMana(player, manaCost)) {
            return;
        }

        SkillUtil.useMana(player, manaCost);
        activateSkill(player);
        currentCoolDown = coolDownTime;

    }

    protected abstract void activateSkill(Player player); // 스킬 발동

    protected void applyEffects(Player player) {
        // 지속 효과용 스킬
    }

    protected void deactivateSkill(Player player) {
        isActive = false;
    } // 스킬 해제

    public boolean isActive() {
        return isActive;
    }

    public String getSkillName() {
        return SkillName;
    }
}
