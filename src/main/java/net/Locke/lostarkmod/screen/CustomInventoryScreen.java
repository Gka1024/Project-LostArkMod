package net.Locke.lostarkmod.screen;

import net.Locke.lostarkmod.LostArkMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CustomInventoryScreen extends AbstractContainerScreen<CustomInventoryMenu> {

      private static final ResourceLocation TEXTURE = new ResourceLocation(LostArkMod.MOD_ID,
            "textures/gui/inventory.png");

    public CustomInventoryScreen(CustomInventoryMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        //TODO Auto-generated constructor stub
    }

    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int x = 0;
        int y = 0;
        int imgWidth = 230;
        int imgHeight = 229;
        
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imgWidth, imgHeight); // 전체적인 GUI

    }

}
