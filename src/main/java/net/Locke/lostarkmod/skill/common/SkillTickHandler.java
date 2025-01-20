package net.Locke.lostarkmod.skill.common;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.skill.SkillManager;
import net.Locke.lostarkmod.skill.salvation.SalvationSkill1;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LostArkMod.MOD_ID, value = Dist.CLIENT)
public class SkillTickHandler {

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase == TickEvent.Phase.END) { // 틱 끝에 실행
            Player player = event.player;
            SkillManager.tick(player);
        }

    }

}
