package net.Locke.lostarkmod.event;

import net.Locke.lostarkmod.effect.ModEffects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.world.entity.player.Player;

public class DamageReduction {

    public DamageReduction() {
        // 이벤트 핸들러 등록
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.hasEffect(ModEffects.DEFENCE.get())) {
                int amplifier = player.getEffect(ModEffects.DEFENCE.get()).getAmplifier();
                float reductionRatio = 0.1f * (amplifier + 1); // 증폭 수준에 따라 저항 증가
                event.setAmount(event.getAmount() * (1 - reductionRatio));
            }
        }
    }
}