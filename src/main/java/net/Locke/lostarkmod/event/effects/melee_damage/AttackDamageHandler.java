package net.Locke.lostarkmod.event.effects.melee_damage;

import net.Locke.lostarkmod.effect.ModEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AttackDamageHandler {

    public AttackDamageHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerAttack(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (player.hasEffect(ModEffects.MELEE_DAMAGE.get())) {
                int amplifier = player.getEffect(ModEffects.MELEE_DAMAGE.get()).getAmplifier();

                float getDamage = event.getAmount();
                float newDamage = getDamage * (amplifier * 0.1f + 1);

                event.setAmount(newDamage);
            }
        }
    }
}
