package net.Locke.lostarkmod.skill;

public class SkillState {
    public long LAST_SKILL_USED_TIME;
    public int coolDownTime;
    public int tickCounter;

    public void resetCooltime()
    {
        this.coolDownTime = 0;
        this.tickCounter = 0;
    }

    public void tick()
    {
        if (this.coolDownTime > 0)
        {
            this.tickCounter++;
            if (this.tickCounter >= this.coolDownTime)
            {
                this.coolDownTime = 0;
                this.tickCounter = 0;
            }
        }
    }

    public boolean isSkillReady()
    {
        return this.coolDownTime == 0;
    }

}
