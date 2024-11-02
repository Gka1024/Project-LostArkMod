package net.Locke.lostarkmod.effect.custom.beneficial;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class MiningSpeedEffect extends MobEffect {

    public MiningSpeedEffect() {
        super(MobEffectCategory.BENEFICIAL, 0); // 효과의 종류와 색상 설정
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
} // 코드 구현은 event/BlockBreakSpeedHandler.java 에서 구현되어 있습니다.
