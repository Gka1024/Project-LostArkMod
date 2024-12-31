package net.Locke.lostarkmod.effect.custom.armorset;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class EntropySetEffect extends MobEffect { // 사멸 세트입니다. 암살자를 위한 세트로 디자인 예정입니다. // 이동 속도 증가 && 뒤에서 공격 시 데미지 증가
    public EntropySetEffect() {
        super(MobEffectCategory.BENEFICIAL, 0);
    }
}
