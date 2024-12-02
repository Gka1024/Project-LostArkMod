package net.Locke.lostarkmod.utils;

import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;

public class CuriosUtils {

    public boolean isDesiredItemEquipped(Player player, ItemStack desiredItem) {
        LazyOptional<ICuriosItemHandler> curiosInventory = CuriosApi.getCuriosInventory(player);
        return curiosInventory.map(handler -> handler.isEquipped(desiredItem.getItem())).orElse(false);
    }
}
