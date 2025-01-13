package net.Locke.lostarkmod.skills.hallucination;

import java.util.List;

import net.Locke.lostarkmod.effect.ModEffects;
import net.Locke.lostarkmod.skills.SkillUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class HallucinationSkill2 {
    private static final double SKILL_RANGE = 5.0d;
    private static final float SKILL_DAMAGE = 2.0f;
    private static final long LAST_SKILL_USED_TIME = 0;
    private static final int SKILL_COOLDOWN = 200;
    private static final int SKILL_MANA_COST = 60;
    private static boolean isSkillAvailable = true;

    public static void tick() {
        checkSkillAvailable();
    }

    public static void skillUse(Player player) {
        if (!isSkillAvailable) {
            return;
        }

        if (SkillUtil.checkPlayerMana(player, SKILL_MANA_COST)) {
            SkillUtil.useMana(player, SKILL_MANA_COST);
            Level level = player.level();
            List<LivingEntity> EnemyList = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(SKILL_RANGE),
                    entity -> entity != player && entity.isAlive());

            for (LivingEntity entity : EnemyList) {
                DamageSource dmgSrc = new DamageSource(player.level().registryAccess()
                        .lookupOrThrow(Registries.DAMAGE_TYPE)
                        .getOrThrow(DamageTypes.PLAYER_ATTACK), player);

                entity.hurt(dmgSrc, SKILL_DAMAGE);
                entity.addEffect(new MobEffectInstance(ModEffects.STUN_EFFECT.get(), 1, 0));

                if (entity instanceof Mob mob) {
                    mob.setTarget(player);
                }

                player.level().playSound(null, player.blockPosition(), SoundEvents.POLAR_BEAR_WARNING,
                        SoundSource.PLAYERS, 1.0f, 1.5f);
            }

        } else {
            Minecraft.getInstance().player.displayClientMessage(Component.literal("마나가 부족합니다."), true);
        }

    }

    private static void checkSkillAvailable() {
        if (LAST_SKILL_USED_TIME + SKILL_COOLDOWN < getCurrentGameTime()) {
            isSkillAvailable = true;
        }
    }

    private static long getCurrentGameTime() {
        return SkillUtil.getCurrentGameTime();
    }
}
