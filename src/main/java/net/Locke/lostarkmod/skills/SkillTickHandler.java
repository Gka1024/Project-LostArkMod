package net.Locke.lostarkmod.skills;

import net.Locke.lostarkmod.LostArkMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LostArkMod.MOD_ID, value = Dist.CLIENT)
public class SkillTickHandler {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event)
    {
        SkillManager.tick();
    }

    
}
