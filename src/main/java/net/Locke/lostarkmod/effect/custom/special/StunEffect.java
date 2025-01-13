package net.Locke.lostarkmod.effect.custom.special;

import java.util.UUID;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class StunEffect extends MobEffect {
    private static final UUID MODIFIER_UUID = UUID.randomUUID();

    public StunEffect() {
        super(MobEffectCategory.HARMFUL, 0); // 효과의 종류와 색상 설정
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.setDeltaMovement(0, entity.getDeltaMovement().y, 0);
        entity.setJumping(false);

        if (!entity.hasEffect(MobEffects.BLINDNESS)) {
        entity.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                MobEffects.BLINDNESS, -1, 0, false, false, false)); // showIcon=false
    }

    if (!entity.hasEffect(MobEffects.JUMP)) {
        entity.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                MobEffects.JUMP, -1, 128, false, false, false)); // showIcon=false
    }
        EntityStop(entity);
    }

    private void EntityStop(LivingEntity entity) {
        AttributeModifier speedModifier = new AttributeModifier(MODIFIER_UUID, "speedeffect", -10,
                AttributeModifier.Operation.ADDITION);

        if (!entity.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(speedModifier)) {
            entity.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(speedModifier);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        pLivingEntity.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(MODIFIER_UUID);
        pLivingEntity.removeEffect(MobEffects.BLINDNESS);
        pLivingEntity.removeEffect(MobEffects.JUMP);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // 매 틱마다 효과 적용
    }

}