package net.Locke.lostarkmod.client;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.capability.IMana;
import net.Locke.lostarkmod.capability.Mana;
import net.Locke.lostarkmod.capability.ManaProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;

@Mod.EventBusSubscriber
public class ManaHUDRenderer {

    private static final ResourceLocation MANA_TEXTURE = new ResourceLocation(LostArkMod.MOD_ID,
            "textures/gui/mana_texture.png");

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new ManaHUDRenderer());
    }

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Post event) {
        // 현재 게임 인스턴스를 가져옵니다.
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
    
        // 플레이어가 null인지 확인 
        if (player == null) return;
    
        GameType gameMode = mc.gameMode.getPlayerMode();
        if (gameMode != GameType.SURVIVAL) return;
    
        // Mana 값을 가져오는 로직 
        player.getCapability(ManaProvider.MANA_CAPABILITY).ifPresent(mana -> {
            int currentMana = mana.getMana(); // 현재 마나
            int maxMana = mana.getMaxMana(); // 최대 마나
    
            GuiGraphics guiGraphics = event.getGuiGraphics();
            int width = mc.getWindow().getGuiScaledWidth();
            int height = mc.getWindow().getGuiScaledHeight();
    
            // 현재 마나 및 최대 마나 출력 (디버그용)
            //System.out.println("Current Mana: " + currentMana + ", Max Mana: " + maxMana);
    
            renderMana(guiGraphics, currentMana, maxMana, width, height);
        });
    }

    private static void renderMana(GuiGraphics guiGraphics, int currentMana, int maxMana, int width, int height) {

        for (int i = 0; i < 10; i++) {
            guiGraphics.blit(MANA_TEXTURE, width / 2 + 10 + i * 8, height - 49, 1, 11, 8, 8);
        }

        int currentManaRatio = (currentMana * 10) / maxMana;

        for (int i = 0; i < currentManaRatio; i++) {
            guiGraphics.blit(MANA_TEXTURE, width / 2 + 82 - i * 8, height - 49, 1, 1, 8, 8);
        }
    }

    

}
