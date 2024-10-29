package net.Locke.lostarkmod.effect.custom.beneficial;

import net.Locke.lostarkmod.capability.IMana;
import net.Locke.lostarkmod.capability.Mana;
import net.Locke.lostarkmod.capability.ManaProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ManaRegenerationEffect extends MobEffect {
    public ManaRegenerationEffect() {
        super(MobEffectCategory.BENEFICIAL, 0); // 효과의 종류와 색상 설정
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if(entity instanceof Player player)
        {
            IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElse(new Mana());
            mana.manaRegen((amplifier + 1 ) * 5);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // 이펙트의 지속 시간을 처리
        return duration % 40 == 0; // 매 40틱마다 이펙트 적용
    }

}
