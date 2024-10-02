package net.Locke.lostarkmod.screen;

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
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;

public class StoneCarvingTableScreen extends AbstractContainerScreen<StoneCarvingTableMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(LostArkMod.MOD_ID,
            "textures/gui/stone_carving_table_gui.png");
    private static final ResourceLocation ICON_TEXTURE = new ResourceLocation(LostArkMod.MOD_ID,
            "textures/gui/buff_icon.png");

    private String currentProbString = "75%";
    private int currentProbInt = 75;
    private int stoneArray[][] = new int[3][10];
    private int opt1Index = 0, opt2Index = 0, opt3Index = 0;
    private int opt1Cur = 0, opt2Cur = 0, opt3Cur = 0;

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

    private boolean isMouseOverButton(double mouseX, double mouseY, int buttonX, int buttonY) {
        return mouseX >= buttonX && mouseX <= buttonX + 29 && mouseY >= buttonY && mouseY <= buttonY + 25;
    }

    private void onButtonClick(int buttonId) {

        StoneCarvingTableBlockEntity blockEntity = (StoneCarvingTableBlockEntity) this.menu.getBlockEntity();

        // 슬롯 1번에 아이템이 5개 이상 있는지 확인
        if (!blockEntity.hasEnoughItems(1, 5)) {
            // 아이템이 충분하지 않으면 알림을 출력하고 함수 종료
            Minecraft.getInstance().player.displayClientMessage(
                    Component.literal("Not enough items to proceed."), true);
            return;
        }

        int ranInt = (int) (Math.random() * 100) + 1;
        boolean isSuccess = false;

        switch (buttonId) {
            case 1:
                if (opt1Index == 10)
                    return;
                break;

            case 2:
                if (opt2Index == 10)
                    return;
                break;

            case 3:
                if (opt3Index == 10)
                    return;
                break;

            default:
                break;
        }

        if (ranInt <= currentProbInt) {
            isSuccess = true;
            if (currentProbInt != 25) {
                currentProbInt -= 10;
            }
        } else {
            if (currentProbInt != 75) {
                currentProbInt += 10;
            }
        }
        sendRemoveItemPacket(1, 5);
        currentProbString = String.valueOf(currentProbInt) + "%";

        switch (buttonId) {
            case 1:
                if (isSuccess) {
                    stoneArray[0][opt1Index++] = 1;
                    opt1Cur++;
                } else {
                    stoneArray[0][opt1Index++] = 2;
                }
                playButtonSound(isSuccess, false);
                break;

            case 2:
                if (opt2Index == 10) {
                    return;
                }
                if (isSuccess) {
                    stoneArray[1][opt2Index++] = 1;
                    opt2Cur++;
                } else {
                    stoneArray[1][opt2Index++] = 2;
                }
                playButtonSound(isSuccess, false);
                break;

            case 3:
                if (opt3Index == 10) {
                    return;
                }
                if (isSuccess) {
                    stoneArray[2][opt3Index++] = 1;
                    opt3Cur++;
                } else {
                    stoneArray[2][opt3Index++] = 2;
                }
                playButtonSound(isSuccess, true);
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

    private void playButtonSound(boolean isSuccess, boolean is3rd) {
        BlockPos pos = this.menu.getBlockEntity().getBlockPos();

        if (!is3rd) {
            Minecraft.getInstance().level.playSound(
                    Minecraft.getInstance().player, pos,
                    isSuccess ? SoundEvents.AMETHYST_BLOCK_BREAK : SoundEvents.GLASS_BREAK,
                    net.minecraft.sounds.SoundSource.BLOCKS);
        } else {
            Minecraft.getInstance().level.playSound(
                    Minecraft.getInstance().player, pos,
                    isSuccess ? SoundEvents.DEEPSLATE_BREAK : SoundEvents.GLASS_BREAK,
                    net.minecraft.sounds.SoundSource.BLOCKS);
        }

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        // RenderSystem.setShader(GameRenderer::getPositionTexShader);
        // RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        // RenderSystem.setShaderTexture(0, TEXTURE);

        int imgWidth = 230;
        int imgHeight = 229;
        int x = (width - imgWidth) / 2;
        int y = (height - imgHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imgWidth, imgHeight); // 전체적인 GUI

        int buttonX = x + 185;
        int buttonY = y + 42;
        int buttonImgX = 58;
        int buttonImgY = 228;

        guiGraphics.blit(TEXTURE, buttonX, buttonY, buttonImgX, buttonImgY, 29, 25); // 버튼 1
        guiGraphics.blit(TEXTURE, buttonX, buttonY + 22, buttonImgX, buttonImgY, 29, 25); // 버튼 2
        guiGraphics.blit(TEXTURE, buttonX, buttonY + 52, buttonImgX, buttonImgY, 29, 25); // 버튼 3

        int jewelX = x + 43;
        int jewelY = y + 55;
        int jewelImgX = 97;
        int jewelImgY = 229;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 10; j++) {
                if (stoneArray[i][j] == 1) { // 성공했을때
                    guiGraphics.blit(TEXTURE, jewelX + (14 * j), jewelY + (22 * i), jewelImgX, jewelImgY, 8, 8);
                } else if (stoneArray[i][j] == 2) { // 실패했을때
                    guiGraphics.blit(TEXTURE, jewelX + (14 * j), jewelY + (22 * i), jewelImgX + 10, jewelImgY, 8, 8);
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            if (stoneArray[2][i] == 1) { // 성공했을때(기분나쁨)
                guiGraphics.blit(TEXTURE, jewelX + (14 * i), jewelY + 52, jewelImgX - 10, jewelImgY, 8, 8); // 버튼 1
            } else if (stoneArray[2][i] == 2) { // 실패했을때 (기분좋음)
                guiGraphics.blit(TEXTURE, jewelX + (14 * i), jewelY + 52, jewelImgX + 10, jewelImgY, 8, 8); // 버튼 1
            }
        }
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

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // GUI에서 문자가 필요한 경우 그리는 코드입니다.
        guiGraphics.drawString(this.font, Component.literal(currentProbString), 164, 3, 16777215); // 확률 표시
        guiGraphics.drawString(this.font, Component.literal("5 Silling"), 10, -13, 16777215); // 실링 표시

        // 진행률 표시
        guiGraphics.drawString(this.font, Component.literal("+" + Integer.toString(opt1Cur)), 142, 13, 41961);
        guiGraphics.drawString(this.font, Component.literal("+" + Integer.toString(opt2Cur)), 142, 35, 41961);
        guiGraphics.drawString(this.font, Component.literal("+" + Integer.toString(opt3Cur)), 142, 65, 15602975);

    }

}
