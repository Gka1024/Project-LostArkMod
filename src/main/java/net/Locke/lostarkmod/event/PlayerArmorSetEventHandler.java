package net.Locke.lostarkmod.event;

import net.Locke.lostarkmod.armorset.PlayerArmorSetState;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerArmorSetEventHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        
        if (event.phase == TickEvent.Phase.END) {
            PlayerArmorSetState.checkAndAssignJobBasedOnEffects(player);
        }
    }
}