package net.Locke.lostarkmod.event;

import net.Locke.lostarkmod.capability.IMana;
import net.Locke.lostarkmod.capability.ManaProvider;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ManaRegenHandler {

    private static int flag = 0;
    Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase == TickEvent.Phase.END && event.player.level().isClientSide) {

            flag++;
            if (flag >= 20) {
                event.player.getCapability(ManaProvider.MANA_CAPABILITY).ifPresent(IMana::manaRegen);
                flag = 0;
            }
        }
    }
}
