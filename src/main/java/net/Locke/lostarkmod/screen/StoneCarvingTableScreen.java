package net.Locke.lostarkmod.screen;

import net.minecraft.network.chat.Component;

import com.mojang.blaze3d.systems.RenderSystem;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.block.entity.StoneCarvingTableBlockEntity;
import net.Locke.lostarkmod.network.StoneCarvePacket;
import net.Locke.lostarkmod.network.ModMessages;
import net.Locke.lostarkmod.network.RemoveItemPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class StoneCarvingTableScreen extends AbstractContainerScreen<StoneCarvingTableMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(LostArkMod.MOD_ID,
            "textures/gui/stone_carving_table_gui.png");
    private static final ResourceLocation ICON_TEXTURE = new ResourceLocation(LostArkMod.MOD_ID,
            "textures/gui/buff_icon.png");

    StoneCarvingTableBlockEntity blockEntity = (StoneCarvingTableBlockEntity) this.menu.getBlockEntity();

    public StoneCarvingTableScreen(StoneCarvingTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
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
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int imgWidth = 230;
        int imgHeight = 229;
        int x = (width - imgWidth) / 2;
        int y = (height - imgHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imgWidth, imgHeight); // 전체적인 GUI

        renderButton(guiGraphics, pPartialTick, pMouseX, pMouseY, x, y);
        renderStoneSuccess(guiGraphics, pPartialTick, pMouseX, pMouseY, x, y);
        renderImage(guiGraphics, pPartialTick, pMouseX, pMouseY, x, y);

    }

    private void renderButton(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY, int x, int y) {

        int buttonX = x + 185;
        int buttonY = y + 42;
        int buttonImgX = 58;
        int buttonImgY = 228;

        guiGraphics.blit(TEXTURE, buttonX, buttonY, buttonImgX, buttonImgY, 29, 25); // 버튼 1
        guiGraphics.blit(TEXTURE, buttonX, buttonY + 22, buttonImgX, buttonImgY, 29, 25); // 버튼 2
        guiGraphics.blit(TEXTURE, buttonX, buttonY + 52, buttonImgX, buttonImgY, 29, 25); // 버튼 3
    }

    private void renderStoneSuccess(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY, int x,
            int y) {
        int jewelX = x + 43;
        int jewelY = y + 55;
        int jewelImgX = 97;
        int jewelImgY = 229;

        if (getByteArray(1) != null && getByteArray(1).length != 0) {
            byte[] opt1Arr = getByteArray(1);

            for (int i = 0; i < 10; i++) {
                if (opt1Arr[i] == 1) { // 성공했을때
                    guiGraphics.blit(TEXTURE, jewelX + (14 * i), jewelY + (22 * 0), jewelImgX,
                            jewelImgY, 8, 8);
                } else if (opt1Arr[i] == 2) { // 실패했을때
                    guiGraphics.blit(TEXTURE, jewelX + (14 * i), jewelY + (22 * 0), jewelImgX +
                            10, jewelImgY, 8, 8);
                }
            }
        }

        if (getByteArray(2) != null && getByteArray(2).length != 0) {
            byte[] opt2Arr = getByteArray(2);

            for (int i = 0; i < 10; i++) {
                if (opt2Arr[i] == 1) { // 성공했을때
                    guiGraphics.blit(TEXTURE, jewelX + (14 * i), jewelY + (22 * 1), jewelImgX,
                            jewelImgY, 8, 8);
                } else if (opt2Arr[i] == 2) { // 실패했을때
                    guiGraphics.blit(TEXTURE, jewelX + (14 * i), jewelY + (22 * 1), jewelImgX +
                            10, jewelImgY, 8, 8);
                }
            }
        }

        if (getByteArray(3) != null && getByteArray(3).length != 0) {
            byte[] opt3Arr = getByteArray(3);

            for (int i = 0; i < 10; i++) {
                if (opt3Arr[i] == 1) { // 성공했을때
                    guiGraphics.blit(TEXTURE, jewelX + (14 * i), jewelY + 52, jewelImgX - 10,
                            jewelImgY, 8, 8);
                } else if (opt3Arr[i] == 2) { // 실패했을때
                    guiGraphics.blit(TEXTURE, jewelX + (14 * i), jewelY + 52, jewelImgX + 10,
                            jewelImgY, 8, 8);
                }
            }
        }
    }

    private void renderImage(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY, int x, int y) {
        int imageX = x + 17;
        int imageY = y + 45;

        int opt1Index = getOptionIndex(1) - 1;
        int opt2Index = getOptionIndex(2) - 1;
        int opt3Index = getOptionIndex(3) - 1;
        int offset = 22;

        if (opt1Index != -1)
            guiGraphics.blit(ICON_TEXTURE, imageX, imageY, opt1Index % 10 * offset, opt1Index / 10 * offset, 18, 18); // 버프
                                                                                                                      // 1

        if (opt2Index != -1)
            guiGraphics.blit(ICON_TEXTURE, imageX, imageY + 22, opt2Index % 10 * offset, opt2Index / 10 * offset, 18,
                    18); // 버프 2

        if (opt3Index != -1)
            guiGraphics.blit(ICON_TEXTURE, imageX, imageY + 53, (opt3Index % 10 + 5) * offset, offset, 18, 18); // 디버프

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int imgWidth = 230;
        int imgHeight = 229;
        int x = (width - imgWidth) / 2;
        int y = (height - imgHeight) / 2;
        int buttonX = x + 185;
        int buttonY = y + 42;

        // 첫 번째 버튼 클릭 처리
        if (isMouseOverButton(mouseX, mouseY, buttonX, buttonY)) {
            onButtonClick(1);
            return true;
        }

        // 두 번째 버튼 클릭 처리
        if (isMouseOverButton(mouseX, mouseY, buttonX, buttonY + 22)) {
            onButtonClick(2);
            return true;
        }

        // 세 번째 버튼 클릭 처리
        if (isMouseOverButton(mouseX, mouseY, buttonX, buttonY + 52)) {
            onButtonClick(3);
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean isMouseOverButton(double mouseX, double mouseY, int buttonX, int buttonY) {
        return mouseX >= buttonX && mouseX <= buttonX + 29 && mouseY >= buttonY && mouseY <= buttonY + 25;
    }

    private void onButtonClick(int buttonId) {

        Minecraft mc = Minecraft.getInstance();

        // 슬롯 1번에 아이템이 5개 이상 있는지 확인
        if (!blockEntity.hasEnoughItems(1, 2)) {
            // 아이템이 충분하지 않으면 알림을 출력하고 함수 종료
            mc.player.displayClientMessage(Component.literal("Not enough Silling"), true);
            return;
        }

        if (!blockEntity.isStoneExist()) {
            mc.player.displayClientMessage(Component.literal("No Stones"), true);
            return;
        }

        if (!blockEntity.isCarvable(buttonId)) {
            mc.player.displayClientMessage(Component.literal("No Proceeds"), true);
            return;
        }

        sendRemoveItemPacket(1, 2);
        sendStoneCarvePacket(buttonId);

    }

    private void sendRemoveItemPacket(int slot, int count) {
        // BlockEntity의 위치를 가져옵니다.
        BlockPos blockPos = this.menu.getBlockEntity().getBlockPos();

        // 서버에 패킷 전송
        ModMessages.INSTANCE.sendToServer(new RemoveItemPacket(blockPos, slot, count));
    }

    private void sendStoneCarvePacket(int slot) {
        // BlockEntity의 위치를 가져옵니다.
        BlockPos blockPos = this.menu.getBlockEntity().getBlockPos();

        // 서버에 패킷 전송
        ModMessages.INSTANCE.sendToServer(new StoneCarvePacket(blockPos, slot));
    }

    private int getCurrentProbability() {
        return blockEntity.getCurrentProbability();
    }

    private int getCurrentSuccess(int index) {
        return blockEntity.getCurrent(index);
    }

    private int getOptionIndex(int index) {
        return blockEntity.getOptionIndex(index);
    }

    private boolean isStoneHasNBT() {
        return blockEntity.isStoneHasNBT();
    }

    private byte[] getByteArray(int index) {
        return blockEntity.getByteArray(index);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // GUI에서 문자가 필요한 경우 그리는 코드입니다.
        guiGraphics.drawString(this.font, Component.literal(Integer.toString(getCurrentProbability()) + "%"), 164, 3,
                16777215); // 확률 표시
        guiGraphics.drawString(this.font, Component.literal("2 Silling"), 10, -13, 16777215); // 실링 표시

        if (!isStoneHasNBT())
            guiGraphics.drawString(this.font, Component.literal("Right Click to Proceed"), 10, 0, 16777215); // 안내문 표시

        // 진행률 표시
        guiGraphics.drawString(this.font, Component.literal("+" + Integer.toString(getCurrentSuccess(1))), 142, 13,
                41961);
        guiGraphics.drawString(this.font, Component.literal("+" + Integer.toString(getCurrentSuccess(2))), 142, 35,
                41961);
        guiGraphics.drawString(this.font, Component.literal("+" + Integer.toString(getCurrentSuccess(3))), 142, 65,
                15602975);

    }

}
