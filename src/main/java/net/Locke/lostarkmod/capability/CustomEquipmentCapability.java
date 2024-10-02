package net.Locke.lostarkmod.capability;

import net.minecraft.world.item.ItemStack;

public class CustomEquipmentCapability {

    private ItemStack abilityStoneSlot = ItemStack.EMPTY; // 목걸이 슬롯

    public ItemStack getAbilityStoneSlot() {
        return abilityStoneSlot;
    }

    public void setAbilityStoneSlot(ItemStack stack) {
        this.abilityStoneSlot = stack;
    }

}
