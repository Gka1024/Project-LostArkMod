package net.Locke.lostarkmod.event;

import net.Locke.lostarkmod.capability.IMana;
import net.Locke.lostarkmod.capability.Mana;
import net.Locke.lostarkmod.capability.ManaProvider;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ManaRegenHandler {

    private static int flag = 0;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        // 서버 측에서만 실행
        if (event.phase == TickEvent.Phase.END && !event.player.level().isClientSide) {
            flag++;
            if (flag >= 20) { // 1초마다 실행
                event.player.getCapability(ManaProvider.MANA_CAPABILITY).ifPresent(mana -> {
                    mana.manaRegen(); // 마나 회복 처리
                    Mana.syncManaToClient(event.player); // 클라이언트로 동기화
                });
                flag = 0;
            }
        }
    }
}
