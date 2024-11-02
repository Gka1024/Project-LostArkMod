package net.Locke.lostarkmod.effect.custom.beneficial;

import java.util.UUID;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class LuckyEffect extends MobEffect {
    private static final UUID MODIFIER_UUID = UUID.randomUUID();

    public LuckyEffect() {
        super(MobEffectCategory.BENEFICIAL, 0); // 효과의 종류와 색상 설정
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            float addLuck = (amplifier + 1) * 0.1f;
            playerLuckAdjust(addLuck, player);
        }
    }

    public void playerLuckAdjust(float value, Player player) {
        AttributeModifier modifier = new AttributeModifier(MODIFIER_UUID, "luck", value,
                AttributeModifier.Operation.ADDITION);
        if (!player.getAttribute(Attributes.LUCK).hasModifier(modifier)) {
            player.getAttribute(Attributes.LUCK).addTransientModifier(modifier);
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        if(pLivingEntity instanceof Player player)
        {
            player.getAttribute(Attributes.LUCK).removeModifier(MODIFIER_UUID);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; 
    }

}
// 추가로 현재 들고 있는 아이템에 행운을 부여합니다. 1레벨은 0, 2레벨은 1, 3레벨은 2
// 부여하는 코드는 event/FortuneBoostHandler.java에 정의되어 있습니다.