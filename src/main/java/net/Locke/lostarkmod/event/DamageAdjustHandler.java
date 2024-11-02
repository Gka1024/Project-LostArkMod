package net.Locke.lostarkmod.event;

import net.Locke.lostarkmod.effect.ModEffects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.world.entity.player.Player;

public class DamageAdjustHandler {

    public DamageAdjustHandler() {
        // 이벤트 핸들러 등록
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.hasEffect(ModEffects.DEFENCE.get()) && !player.hasEffect(ModEffects.DAMAGE_INCOME.get())) {
                int amplifier = player.getEffect(ModEffects.DEFENCE.get()).getAmplifier();
                float damageRatio = 0.1f * (amplifier + 1); // 증폭 수준에 따라 저항 증가
                event.setAmount(event.getAmount() * (1 - damageRatio));
            }
            else if(!player.hasEffect(ModEffects.DEFENCE.get()) && player.hasEffect(ModEffects.DAMAGE_INCOME.get()))
            {
                int amplifier = player.getEffect(ModEffects.DAMAGE_INCOME.get()).getAmplifier();
                float damageRatio = 0.1f * (amplifier + 1); // 증폭 수준에 따라 받는 데미지 증가
                event.setAmount(event.getAmount() * (1 + damageRatio));
            }
            else if(player.hasEffect(ModEffects.DEFENCE.get()) && player.hasEffect(ModEffects.DAMAGE_INCOME.get()))
            {
                int defAmp = player.getEffect(ModEffects.DEFENCE.get()).getAmplifier() + 1;
                int hurtAmp = player.getEffect(ModEffects.DAMAGE_INCOME.get()).getAmplifier() + 1;

                float damageRatio = (hurtAmp - defAmp) * 0.1f;
                event.setAmount(event.getAmount() * (1 + damageRatio));
            }
        }
    }
}