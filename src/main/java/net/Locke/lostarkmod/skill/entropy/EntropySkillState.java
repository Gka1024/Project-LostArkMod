package net.Locke.lostarkmod.skill.entropy;

import net.Locke.lostarkmod.skill.SkillState;

public class EntropySkillState extends SkillState {
    public boolean isSkillActivated = false;
    public boolean isAttackReady = true;
    public long lastAttackTime = 0;
    public long manaTickCounter = 0;

}
