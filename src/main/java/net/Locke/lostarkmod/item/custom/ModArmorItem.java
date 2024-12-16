package net.Locke.lostarkmod.item.custom;

import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ModArmorItem extends ArmorItem {

    private final String setId;
    int[] maxHshardArr = {24, 32, 48, 64};

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties, String set) {

        super(pMaterial, pType, pProperties);
        this.setId = set;
    }
/*
    @Override
    public boolean isFoil(ItemStack stack) {
        // 예: 특정 세트 태그가 있는 아이템에 효과 추가
        return stack.getOrCreateTag().contains("set_id") || super.isFoil(stack);
    }
         */

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        super.onCraftedBy(stack, level, player);

        // 세트 태그를 아이템에 추가
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("set_id")) {
            tag.putString("set_id", setId);
        }
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag unused) {
        // 초기화 시에도 태그를 추가할 수 있음
        if (!stack.getOrCreateTag().contains("set_id")) {
            stack.getOrCreateTag().putString("set_id", setId);
        }
        return super.initCapabilities(stack, unused);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents,
            TooltipFlag pIsAdvanced) {
        if (pStack.hasTag()) {
            CompoundTag tag = pStack.getOrCreateTag();
            int level = tag.getInt("armor.level");
            pTooltipComponents.add(Component.literal("Armor Level : " + Integer.toString(level)));
            String hshardBar = "| ";

            int hshard = tag.getInt("armor.hshard");
            int maxHshard = maxHshardArr[Math.min(level / 5, 3)];

            for (int i = 0; i < 16; i++) {
                if (i < (hshard * 16) / maxHshard) {
                    hshardBar += "=";
                } else {
                    hshardBar += "-";
                }
            }

            hshardBar += " |";

            pTooltipComponents.add(Component.literal(hshardBar));

        }
    }

}