package net.Locke.lostarkmod.events;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.screen.CustomInventoryMenu;
import net.Locke.lostarkmod.screen.CustomInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LostArkMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEventHandler {

    @SubscribeEvent
    public static void onGuiOpen(ScreenEvent event) {
        // 기본 인벤토리 화면인지 확인
        if (event.getScreen() instanceof InventoryScreen) {
            // 기본 인벤토리 화면을 취소하고 커스텀 GUI 화면을 열기
            event.setCanceled(true);

            // 플레이어 인벤토리 및 타이틀 정보 가져오기
            Inventory playerInventory = Minecraft.getInstance().player.getInventory();
            Component title = Component.translatable("gui.lostarkmod.custom_inventory");

            // CustomInventoryMenu 인스턴스를 생성합니다.
            CustomInventoryMenu customMenu = new CustomInventoryMenu(0, playerInventory);

            // CustomInventoryScreen 생성
            CustomInventoryScreen customScreen = new CustomInventoryScreen(customMenu, playerInventory, title);

            // 커스텀 인벤토리 화면을 엽니다.
            Minecraft.getInstance().setScreen(customScreen);
        }
    }
}
