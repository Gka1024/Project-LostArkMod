package net.Locke.lostarkmod.block.entity;

import net.Locke.lostarkmod.item.Moditems;
import net.Locke.lostarkmod.screen.StoneCarvingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StoneCarvingTableBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(2);

    private static final int STONE_SLOT = 0;
    private static final int SILLING_SLOT = 1;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY); 

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public StoneCarvingTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.STONE_CARVING_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> StoneCarvingTableBlockEntity.this.progress;
                    case 1 -> StoneCarvingTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> StoneCarvingTableBlockEntity.this.progress = pValue;
                    case 1 -> StoneCarvingTableBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }
    public void removeItemFromSlot(int slot, int count) {
        ItemStack stack = items.get(slot); // 슬롯에서 아이템을 가져옴
        if (!stack.isEmpty() && stack.getCount() >= count) {
            // 아이템이 충분할 때만 제거
            stack.shrink(count); // 지정된 개수만큼 아이템을 줄임
            if (stack.isEmpty()) {
                items.set(slot, ItemStack.EMPTY); // 아이템이 모두 소모되었을 때 빈 슬롯으로 설정
            }
            setChanged(); // 블록 엔티티가 변경되었음을 알림 (클라이언트와 동기화)
        } else {
            // 아이템이 부족하거나 없는 경우 처리
            System.out.println("Not enough items to remove from slot " + slot);
            // 필요시 추가 로직: 서버에서 플레이어에게 알림을 보내거나 다른 작업 수행
        }
    }

    @Override
    public @NotNull <T> LazyOptional getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new StoneCarvingTableMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.lostarkmod.stone_carving_table");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("stone_carving_table.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {

        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("stone_carving_table.progress");
    }


}
