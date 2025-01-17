package net.Locke.lostarkmod.skill.salvation;

import net.Locke.lostarkmod.skill.SkillState;

public class SalvationSkillState extends SkillState {
    public boolean isSkillActivated = false;
    public boolean isAttackReady = true;
    public long lastAttackTime = 0;
    public long manaTickCounter = 0;

}
