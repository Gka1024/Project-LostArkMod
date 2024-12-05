package net.Locke.lostarkmod.screen;

import org.joml.Math;

import com.mojang.blaze3d.systems.RenderSystem;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.block.entity.ArmorForgingTableBlockEntity;
import net.Locke.lostarkmod.network.ArmorForgingPacket;
import net.Locke.lostarkmod.network.ModMessages;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;

public class ArmorForgingTableScreen extends AbstractContainerScreen<ArmorForgingTableMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(LostArkMod.MOD_ID,
            "textures/gui/armor_forging_table_gui.png");
    private static final ResourceLocation SHADOW = new ResourceLocation(LostArkMod.MOD_ID,
            "textures/gui/armor_forging_table_shadow.png");

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
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int imgWidth = 230;
        int imgHeight = 229;
        int x = (width - imgWidth) / 2;
        int y = (height - imgHeight) / 2;
        int buttonX = x + 68;
        int buttonY = y + 39;

        // 첫 번째 버튼 클릭 처리
        if (isMouseOverButton(mouseX, mouseY, buttonX, buttonY)) {
            onButtonClick();
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean isMouseOverButton(double mouseX, double mouseY, int buttonX, int buttonY) {
        return mouseX >= buttonX && mouseX <= buttonX + 19 && mouseY >= buttonY && mouseY <= buttonY + 13;
    }

    private void renderUnderline(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY, int x, int y) {
        int lineX = x + 50;
        int lineY = y + 49;

        guiGraphics.blit(TEXTURE, lineX, lineY, 111, 242, 18, 1);
        guiGraphics.blit(TEXTURE, lineX + 38, lineY, 111, 242, 18, 1);

    }

    private void onButtonClick() {
        if (!blockEntity.hasArmor()) {
            return;
        }

        sendForgePacket();
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int imgWidth = 230;
        int imgHeight = 229;
        int x = (width - imgWidth) / 2;
        int y = (height - imgHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imgWidth, imgHeight); // mainRender

        renderHshardGauge(guiGraphics, pPartialTick, pMouseX, pMouseY, x, y);
        renderUnderline(guiGraphics, pPartialTick, pMouseX, pMouseY, x, y);
        renderArrow(guiGraphics, pPartialTick, pMouseX, pMouseY, x, y);
        renderArtisanGauge(guiGraphics, pPartialTick, pMouseX, pMouseY, x, y);
        renderShadow(guiGraphics, pPartialTick, pMouseX, pMouseY, x, y);
    }

    private void renderHshardGauge(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY, int x,
            int y) {
        int shardGaugeX = x + 15;
        int shardGaugeY = y + 39;

        guiGraphics.blit(TEXTURE, shardGaugeX, shardGaugeY, 230, 38, 13, 52);

        if (blockEntity.hasArmor()) {
            int hshard = blockEntity.getHShard();
            int hshardRatio = (hshard * 52) / 64;
            guiGraphics.blit(TEXTURE, shardGaugeX + 2, shardGaugeY + 52 - hshardRatio, 244, 38 + 52 - hshardRatio, 8,
                    hshardRatio);
        }
    }

    private void renderArtisanGauge(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY, int x,
            int y) {
        int gaugeX = x + 49;
        int gaugeY = y + 53;

        guiGraphics.blit(TEXTURE, gaugeX, gaugeY, 0, 230, 57, 10);

        int artisanRatio = (int) (blockEntity.getArtisanEnergy() * 55) / 64;

        guiGraphics.blit(TEXTURE, gaugeX + 1, gaugeY + 1, 75, 231, artisanRatio, 8);
    }

    private void renderArrow(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY, int x, int y) {
        int arrowX = x + 70;
        int arrowY = y + 40;

        guiGraphics.blit(TEXTURE, arrowX, arrowY, 57, 229, 16, 11);

        if (blockEntity.hasArmor()) {
            if (blockEntity.isForgable()) {
                guiGraphics.blit(TEXTURE, arrowX + 2, arrowY + 1, 133, 230, 12, 9);
            } else {
                guiGraphics.blit(TEXTURE, arrowX + 2, arrowY + 1, 146, 230, 12, 9);
            }
        }
    }

    private void renderShadow(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY, int x, int y) {
        if (blockEntity.hasArmor()) {
            int shadowX = x + 36;
            int shadowY = y + 81;

            int stoneIndex = blockEntity.getAppropriateResource(blockEntity.getItemLevel(), blockEntity.isItemWeapon(),
                    1);
            int leapIndex = blockEntity.getAppropriateResource(blockEntity.getItemLevel(), blockEntity.isItemWeapon(),
                    2);
            int orehaIndex = blockEntity.getAppropriateResource(blockEntity.getItemLevel(), blockEntity.isItemWeapon(),
                    3);

            guiGraphics.blit(SHADOW, shadowX, shadowY, (stoneIndex % 4) * 16, (stoneIndex / 4) * 16, 16, 16);
            guiGraphics.blit(SHADOW, shadowX + 34, shadowY, (leapIndex % 4) * 16, (leapIndex / 4) * 16, 16, 16);
            guiGraphics.blit(SHADOW, shadowX + 68, shadowY, (orehaIndex % 4) * 16, (orehaIndex / 4) * 16, 16, 16);
        }

    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {
        guiGraphics.drawString(this.font, Component.literal(blockEntity.getItemName()), 13, -8,
                0xffffff);

        if (blockEntity.hasArmor()) {
            guiGraphics.drawCenteredString(this.font,
                    Component.literal("+" + Integer.toString(blockEntity.getItemLevel())), 30, 9, 0xffffff);

            guiGraphics.drawCenteredString(this.font,
                    Component.literal(Integer.toString(blockEntity.getResourceQuantity(0))), 18, 69, 0xffffff);

            guiGraphics.drawCenteredString(this.font,
                    Component.literal(Integer.toString(blockEntity.getResourceQuantity(1))), 52, 69, 0xffffff);

            guiGraphics.drawCenteredString(this.font,
                    Component.literal(Integer.toString(blockEntity.getResourceQuantity(2))), 86, 69, 0xffffff);

            guiGraphics.drawString(this.font,
                    Component.literal(" - " + Integer.toString(Math.min(blockEntity.getItemQuantity(4), 8))), 156, -18,
                    0xffffff);
            guiGraphics.drawString(this.font,
                    Component.literal(Float.toString(blockEntity.getAdditionalProbability(0)) + " %"), 158, -9,
                    0xffffff);

            guiGraphics.drawString(this.font,
                    Component.literal(" - " + Integer.toString(Math.min(blockEntity.getItemQuantity(5), 5))), 156, 4,
                    0xffffff);
            guiGraphics.drawString(this.font,
                    Component.literal(Float.toString(blockEntity.getAdditionalProbability(1)) + " %"), 158, 13,
                    0xffffff);

            guiGraphics.drawString(this.font,
                    Component.literal(" - " + Integer.toString(Math.min(blockEntity.getItemQuantity(6), 3))), 156, 26,
                    0xffffff);
            guiGraphics.drawString(this.font,
                    Component.literal(Float.toString(blockEntity.getAdditionalProbability(2)) + " %"), 158, 35,
                    0xffffff);

            guiGraphics.drawString(this.font,
                    Component.literal("+ " + Float.toString(blockEntity.getAdditionalProbability()) + " %"), 150, 46,
                    0xffffff);

            MutableComponent totalProbability = Component.literal("[ "
                    + Float.toString(Math.min(blockEntity.getBasicProbability() + blockEntity.getAdditionalProbability()
                            + blockEntity.getFailureProbability(), 100))
                    + " % ]");
            guiGraphics.drawString(this.font, totalProbability,
                    52 - this.font.width(totalProbability) / 2, 37, 0xffffff, false);
        }
    }

    private void sendForgePacket() {
        BlockPos blockPos = this.menu.getBlockEntity().getBlockPos();
        ModMessages.INSTANCE.sendToServer(new ArmorForgingPacket(blockPos));
    }
}
