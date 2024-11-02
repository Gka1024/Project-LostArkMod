package net.Locke.lostarkmod.effect.custom.beneficial;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class MeleeDamageEffect extends MobEffect {
    public MeleeDamageEffect() {
        super(MobEffectCategory.BENEFICIAL, 0); // 효과의 종류와 색상 설정
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
