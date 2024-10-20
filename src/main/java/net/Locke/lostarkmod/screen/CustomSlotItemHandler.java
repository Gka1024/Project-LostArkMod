package net.Locke.lostarkmod.screen;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CustomSlotItemHandler extends SlotItemHandler{
    private final int maxStackSize;

    public CustomSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, int maxStackSize) {
        super(itemHandler, index, xPosition, yPosition);
        this.maxStackSize = maxStackSize;
    }

    @Override
    public int getMaxStackSize()
    {
        return this.maxStackSize;
    }

}
