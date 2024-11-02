package net.Locke.lostarkmod.effect.custom.harmful;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class DamageIncomeEffect extends MobEffect {
    public DamageIncomeEffect() {
        super(MobEffectCategory.HARMFUL, 0); // 효과의 종류와 색상 설정
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // 매 틱마다 효과 적용
    }

}
// 데미지 증가 코드 구현은 event/DamageAdjustHandler.java에서 관리하고 있습니다
