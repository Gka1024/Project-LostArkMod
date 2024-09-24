package net.Locke.lostarkmod.screen;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.network.chat.Component;
import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.block.entity.StoneCarvingTableBlockEntity;
import net.Locke.lostarkmod.network.ModMessages;
import net.Locke.lostarkmod.network.RemoveItemPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;

public class StoneCarvingTableScreen extends AbstractContainerScreen<StoneCarvingTableMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(LostArkMod.MOD_ID,
            "textures/gui/stone_carving_table_gui.png");

    private String currentProbString = "75%";
    private int currentProbInt = 75;

    private int option1Count = 0;
    private int option2Count = 0;
    private int option3Count = 0;

    public StoneCarvingTableScreen(StoneCarvingTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int imgWidth = 250;
        int imgHeight = 219;
        int x = (width - imgWidth) / 2;
        int y = (height - imgHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imgWidth, imgHeight); // 전체적인 위치

        // 버튼 영역 텍스처 렌더링
        int buttonX = x + 185; // 버튼의 x 좌표
        int buttonY = y + 39; // 버튼의 y 좌표
        int buttonImgX = 0;
        int buttonImgY = 219;

        // 버튼 그리기
        guiGraphics.blit(TEXTURE, buttonX, buttonY + 00, buttonImgX, buttonImgY, 29, 25); // 첫번째 버튼
        guiGraphics.blit(TEXTURE, buttonX, buttonY + 22, buttonImgX + 29, buttonImgY, 29, 25); // 두 번째 버튼
        guiGraphics.blit(TEXTURE, buttonX, buttonY + 52, buttonImgX + 58, buttonImgY, 29, 25); // 세 번째 버튼

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int imgWidth = 250;
        int imgHeight = 219;
        int x = (width - imgWidth) / 2;
        int y = (height - imgHeight) / 2;
        int buttonX = x + 185;
        int buttonY = y + 39;

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

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private boolean isMouseOverButton(double mouseX, double mouseY, int buttonX, int buttonY) {
        return mouseX >= buttonX && mouseX <= buttonX + 29 && mouseY >= buttonY && mouseY <= buttonY + 25;
    }

    private void onButtonClick(int buttonId) {
        int ranInt = (int) (Math.random() * 100) + 1;
        boolean isSuccess = false;
        if (ranInt < currentProbInt) {
            isSuccess = true;
            if (currentProbInt != 25) {
                currentProbInt -= 10;
            }
        } else {
            if (currentProbInt != 75) {
                currentProbInt += 10;
            }
        }
        switch (buttonId) {
            case 1:
                sendRemoveItemPacket(1, 5);
                currentProbString = String.valueOf(currentProbInt) + "%";
                break;

            case 2:
                sendRemoveItemPacket(1, 5);
                currentProbString = String.valueOf(currentProbInt) + "%";
                break;

            case 3:
                sendRemoveItemPacket(1, 5);
                currentProbString = String.valueOf(currentProbInt) + "%";
                break;

            default:
                break;
        }
    }

    private void sendRemoveItemPacket(int slot, int count) {
        // BlockEntity의 위치를 가져옵니다.
        BlockPos blockPos = this.menu.getBlockEntity().getBlockPos();

        // 서버에 패킷 전송
        ModMessages.INSTANCE.sendToServer(new RemoveItemPacket(blockPos, slot, count));
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        //GUI에서 문자가 필요한 경우 그리는 코드입니다.
        guiGraphics.drawString(this.font, Component.literal(currentProbString), 153, 2, 16777215);
        
    }

}
