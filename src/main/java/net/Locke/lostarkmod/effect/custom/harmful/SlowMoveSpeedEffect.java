package net.Locke.lostarkmod.effect.custom.harmful;

import java.util.UUID;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class SlowMoveSpeedEffect extends MobEffect {
    private static final UUID MODIFIER_UUID = UUID.randomUUID();

    public SlowMoveSpeedEffect() {
        super(MobEffectCategory.HARMFUL, 0); // 효과의 종류와 색상 설정
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            double increaseSpeed = -0.01 * (amplifier + 1);
            PlayerSpeedAdjust(increaseSpeed, player);
        }
    }

    private void PlayerSpeedAdjust(double speed, Player player) {
        AttributeModifier speedModifier = new AttributeModifier(MODIFIER_UUID, "sloweffect", speed,
                AttributeModifier.Operation.ADDITION);

        if (!player.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(speedModifier)) {
            player.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(speedModifier);
        }

    }

    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if(pLivingEntity instanceof Player player)
        {
            player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(MODIFIER_UUID);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }


}
