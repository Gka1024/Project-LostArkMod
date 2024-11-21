package net.Locke.lostarkmod.screen;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.block.entity.ArmorForgingTableBlockEntity;
import net.Locke.lostarkmod.network.ArmorForgingPacket;
import net.Locke.lostarkmod.network.ModMessages;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ArmorForgingTableScreen extends AbstractContainerScreen<ArmorForgingTableMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(LostArkMod.MOD_ID,
            "textures/gui/armor_forging_table_gui.png");

    ArmorForgingTableBlockEntity blockEntity = (ArmorForgingTableBlockEntity) this.menu.getBlockEntity();

    public ArmorForgingTableScreen(ArmorForgingTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int imgWidth = 230;
        int imgHeight = 229;
        int x = (width - imgWidth) / 2;
        int y = (height - imgHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imgWidth, imgHeight); // mainRender

        renderGauge(guiGraphics, pPartialTick, pMouseX, pMouseY, x, y);
        renderButton(guiGraphics, pPartialTick, pMouseX, pMouseY, x, y);
        renderUnderline(guiGraphics, pPartialTick, pMouseX, pMouseY, x, y);
        renderArrow(guiGraphics, pPartialTick, pMouseX, pMouseY, x, y);
    }

    private void renderGauge(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY, int x, int y) {
        int gaugeX = x + 49;
        int gaugeY = y + 53;

        guiGraphics.blit(TEXTURE, gaugeX, gaugeY, 0, 230, 57, 10);

        int shardGaugeX = x + 15;
        int shardGaugeY = y + 39;
        
        guiGraphics.blit(TEXTURE, shardGaugeX, shardGaugeY, 230, 38, 13, 52);

        if(blockEntity.hasArmor())
        {
            int hshard = blockEntity.getHShard();
            int hshardRatio = (hshard * 52) / 64;
            guiGraphics.blit(TEXTURE, shardGaugeX + 2, shardGaugeY + 52 - hshardRatio, 244, 38 + 52 - hshardRatio, 8, hshardRatio);
            System.out.println(hshardRatio);
        }
    }

    private void renderButton(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY, int x, int y) {
        int buttonX = x + 60;
        int buttonY = y + 65;

        guiGraphics.blit(TEXTURE, buttonX, buttonY, 0, 241, 37, 12);

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int imgWidth = 230;
        int imgHeight = 229;
        int x = (width - imgWidth) / 2;
        int y = (height - imgHeight) / 2;
        int buttonX = x + 60;
        int buttonY = y + 65;

        // 첫 번째 버튼 클릭 처리
        if (isMouseOverButton(mouseX, mouseY, buttonX, buttonY)) {
            onButtonClick();
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean isMouseOverButton(double mouseX, double mouseY, int buttonX, int buttonY) {
        return mouseX >= buttonX && mouseX <= buttonX + 37 && mouseY >= buttonY && mouseY <= buttonY + 12;
    }

    private void renderUnderline(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY, int x, int y) {
        int lineX = x + 50;
        int lineY = y + 49;

        guiGraphics.blit(TEXTURE, lineX, lineY, 111, 242, 18, 1);
        guiGraphics.blit(TEXTURE, lineX + 38, lineY, 111, 242, 18, 1);

    }

    private void renderArrow(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY, int x, int y) {
        int lineX = x + 70;
        int lineY = y + 40;

        guiGraphics.blit(TEXTURE, lineX, lineY, 57, 229, 16, 11);

    }

    private void onButtonClick()
    {
        if(!blockEntity.hasArmor())
        {
            return;
        }

        sendForgePacket();
    }

    private void sendForgePacket()
    {
        BlockPos blockPos = this.menu.getBlockEntity().getBlockPos();
        ModMessages.INSTANCE.sendToServer(new ArmorForgingPacket(blockPos));
    }
}
