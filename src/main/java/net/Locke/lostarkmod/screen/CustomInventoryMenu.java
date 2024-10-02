package net.Locke.lostarkmod.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class CustomInventoryMenu extends AbstractContainerMenu {

    // Constructor
    public CustomInventoryMenu(int id, Inventory playerInventory) {
        super(ModMenuTypes.CUSTOM_INVENTORY_MENU.get(), id);
        // 슬롯 구성이나 인벤토리 설정 추가
    }

    public CustomInventoryMenu(int id, Inventory playerInventory, FriendlyByteBuf buffer) {
        this(id, playerInventory); // 기존 생성자를 호출하여 기본 초기화 진행
        // 추가 데이터를 처리하거나 슬롯 설정을 수행
        // buffer.readInt(), buffer.readItemStack() 등을 사용해 데이터 처리
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quickMoveStack'");
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stillValid'");
    }

    

    // 슬롯 설정 및 아이템 처리 로직 추가 가능
}
