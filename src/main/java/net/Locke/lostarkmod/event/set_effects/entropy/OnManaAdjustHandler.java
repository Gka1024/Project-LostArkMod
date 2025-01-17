package net.Locke.lostarkmod.event.set_effects.entropy;

import net.Locke.lostarkmod.capability.Mana;
import net.Locke.lostarkmod.capability.ManaProvider;
import net.Locke.lostarkmod.skill.SkillUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnManaAdjustHandler {
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
/*
    @SubscribeEvent
    public void onPlayerAttack(AttackEntityEvent event) {
        if (!isSkillActivated)
            return;

        Player player = event.getEntity();

        attackCount++;
        LAST_ATTACK_TIME = SkillUtil.getCurrentGameTime();
        System.out.println(LAST_ATTACK_TIME);

        if (isAttackReady) {
            SkillUtil.regainMana(player, 5);
            isAttackReady = false;
        }

    } */
}
