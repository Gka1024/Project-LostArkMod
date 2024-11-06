package net.Locke.lostarkmod.event.effects.mining_speed;

import net.Locke.lostarkmod.effect.ModEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BlockBreakSpeedHandler {

    public BlockBreakSpeedHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBreakSpeed(BreakSpeed event)
    {
        Player player  = event.getEntity();
        if(player.hasEffect(ModEffects.MINING_SPEED.get()))
        {
            int amplifier = player.getEffect(ModEffects.MINING_SPEED.get()).getAmplifier();
            float newSpeed = event.getOriginalSpeed() * (amplifier * 0.1f + 1);
            event.setNewSpeed(newSpeed);
        }
    }

}
