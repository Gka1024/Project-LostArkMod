package net.Locke.lostarkmod.effect.custom.beneficial;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import java.util.UUID;

public class AttackSpeedEffect extends MobEffect {
    private static final UUID MODIFIER_UUID = UUID.randomUUID();

    public AttackSpeedEffect() {
        super(MobEffectCategory.BENEFICIAL, 0); // 효과의 종류와 색상 설정
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            double attackSpeedIncrease = 0.1 * (amplifier + 1); // 증폭 수준에 따라 증가
            
            playerAttackSpeedAdjust(attackSpeedIncrease, player);
        }
    }

    public void playerAttackSpeedAdjust(double value, Player player) {
        AttributeModifier modifier = new AttributeModifier(MODIFIER_UUID, "attackspeed", value, AttributeModifier.Operation.ADDITION);
        if(!player.getAttribute(Attributes.ATTACK_SPEED).hasModifier(modifier))
        {
            player.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(modifier);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if (pLivingEntity instanceof Player player) {
            // 이펙트가 끝날 때 수정자를 제거합니다.
            player.getAttribute(Attributes.ATTACK_SPEED).removeModifier(MODIFIER_UUID);
        }
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; 
    }
}