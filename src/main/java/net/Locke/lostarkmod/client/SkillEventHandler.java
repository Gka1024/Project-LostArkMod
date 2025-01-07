package net.Locke.lostarkmod.client;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.capability.IMana;
import net.Locke.lostarkmod.capability.Mana;
import net.Locke.lostarkmod.capability.ManaProvider;
import net.Locke.lostarkmod.network.ModMessages;
import net.Locke.lostarkmod.network.packets.SkillUsePacket;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LostArkMod.MOD_ID, value = Dist.CLIENT)
public class SkillEventHandler {
    private static final String CATEGORY = "key.categories.lostarkmod";
    public static final KeyMapping SKILL_KEY_1 = new KeyMapping("key.lostarkmod.use_skill1", InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_X, CATEGORY);

    public static final KeyMapping SKILL_KEY_2 = new KeyMapping("key.lostarkmod.use_skill2", InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C, CATEGORY);

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(SKILL_KEY_1);
        event.register(SKILL_KEY_2);
    }

    private static boolean isSkillKey1Pressed = false;
    private static boolean isSkillKey2Pressed = false;
    private static long key1PressTick = 0;
    private static long key2PressTick = 0;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

        if (SKILL_KEY_1.isDown()) {
            handleSkillUse(1);
            if (!isSkillKey1Pressed) {
                isSkillKey1Pressed = true;
                key1PressTick = getGameTicks();
            }

        } else {
            if (isSkillKey1Pressed) {
                isSkillKey1Pressed = false;
                long key1PressDuration = getGameTicks() - key1PressTick;
                handleKeyDownSkillUse(key1PressDuration, 1);
            }
        }

        if (SKILL_KEY_2.isDown()) {
            handleSkillUse(2);
            if (!isSkillKey2Pressed) {
                isSkillKey2Pressed = true;
                key2PressTick = getGameTicks();
            }
        } else {
            if (isSkillKey2Pressed) {
                isSkillKey2Pressed = false;
                long key2PressDuration = getGameTicks() - key2PressTick;
                handleKeyDownSkillUse(key2PressDuration, 2);
            }
        }
    }

    private static long getGameTicks() {
        if (Minecraft.getInstance().level != null) {
            return Minecraft.getInstance().level.getGameTime();
        }
        return 0;
    }

    private static void handleSkillUse(int key) {
        LocalPlayer player = Minecraft.getInstance().player;
        IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElse(new Mana());
        ModMessages.INSTANCE.sendToServer(new SkillUsePacket(mana.getMana(), key, 0));
    }

    private static void handleKeyDownSkillUse(long KeydownTime, int key) {
        LocalPlayer player = Minecraft.getInstance().player;
        IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElse(new Mana());
        ModMessages.INSTANCE.sendToServer(new SkillUsePacket(mana.getMana(), key, (int) KeydownTime));
    }

}
