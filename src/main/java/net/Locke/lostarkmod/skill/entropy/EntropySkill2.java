package net.Locke.lostarkmod.skill.entropy;

import net.Locke.lostarkmod.skill.Skill;
import net.Locke.lostarkmod.skill.SkillManager;
import net.Locke.lostarkmod.skill.SkillState;
import net.Locke.lostarkmod.skill.SkillUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class EntropySkill2 extends Skill {

    private static final int ATTACK_INTERVAL = 20;
    private static final int TICK_INTERVAL_TIME = 10;
    private static final int SKILL_MANA_PER_TICK = 5;

    public EntropySkill2() {
        super("SkillName", 5, 0);
    }

    @Override
    public void tick(Player player) {
        /* 
        EntropySkillState state = SkillManager.getState(player);
        checkAttackReady(state);
        entropyTick(player, state);*/
    }

    @Override
    public void useSkill(Player player) {

        
            
    }

    @Override
    protected void activateSkill(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateSkill'");
    }

    private static void activateSkill(Player player, EntropySkillState state) {
        player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 0,
                false, false, false));

        state.isSkillActivated = true;
        state.manaTickCounter = 0;
    }

    private static void deactivateSkill(Player player, EntropySkillState state) {
        player.removeEffect(MobEffects.MOVEMENT_SPEED);
        state.isSkillActivated = false;
    }

    public static void entropyTick(Player player, EntropySkillState state) {
        if (state.isSkillActivated) {
            if (!SkillUtil.checkPlayerMana(player, SKILL_MANA_PER_TICK)) {
                deactivateSkill(player, state);
                return;
            }

            state.manaTickCounter++;
            if (state.manaTickCounter >= TICK_INTERVAL_TIME) {
                state.manaTickCounter = 0;
                SkillUtil.useMana(player, SKILL_MANA_PER_TICK);
            }
        }
    }

    private static void checkAttackReady(EntropySkillState state) {
        if (!state.isAttackReady) {
            if (state.lastAttackTime + ATTACK_INTERVAL <= SkillUtil.getCurrentGameTime()) {
                state.isAttackReady = true;
            }
        }
    }

    

}
