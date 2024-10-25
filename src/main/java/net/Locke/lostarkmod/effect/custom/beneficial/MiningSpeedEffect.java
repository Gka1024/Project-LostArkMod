package net.Locke.lostarkmod.effect.custom.beneficial;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class MiningSpeedEffect extends MobEffect {
    public MiningSpeedEffect() {
        super(MobEffectCategory.BENEFICIAL, 0); // 효과의 종류와 색상 설정
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        // 이펙트가 적용될 때마다 실행되는 로직
        // 예를 들어, 체력을 회복시키거나, 속도를 증가시키는 등의 작업
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // 이펙트의 지속 시간을 처리
        return duration % 40 == 0; // 매 40틱마다 이펙트 적용
    }

}
