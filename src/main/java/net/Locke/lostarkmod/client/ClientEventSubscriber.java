package net.Locke.lostarkmod.client;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.Locke.lostarkmod.LostArkMod;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LostArkMod.MOD_ID, value = Dist.CLIENT)
public class ClientEventSubscriber {
    private static final String CATEGORY = "key.categories.lostarkmod";
    public static final KeyMapping SKILL_KEY = new KeyMapping("key.lostarkmod.use_skill1", InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_R, CATEGORY);

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event)
    {
        event.register(SKILL_KEY);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event)
    {
        if(SKILL_KEY.consumeClick())
        {
            handleSkillUse();
        }
    }

    private static void handleSkillUse()
    {
        
    }
}
