package net.Locke.lostarkmod.effect.custom.beneficial;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class DefenceEffect extends MobEffect {

    public DefenceEffect() {
        super(MobEffectCategory.BENEFICIAL, 0); // 효과의 종류와 색상 설정
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true; // 매 틱마다 효과 적용
    }
}
// 데미지 감소 코드 구현은 event/damageReductionHandler에서 구현되어있습니다.