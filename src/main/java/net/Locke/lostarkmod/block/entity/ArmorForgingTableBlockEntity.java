package net.Locke.lostarkmod.block.entity;

import javax.annotation.Nullable;
import net.Locke.lostarkmod.screen.ArmorForgingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class ArmorForgingTableBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(9) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private static int ARMOR_SLOT = 0; // 장비
    private static int STONE_SLOT = 1; // 수호석|파괴석
    private static int LEAPSTONE_SLOT = 2; // 명예의 돌파석
    private static int OREHA_SLOT = 3; // 오레하 융화재료
    private static int BLESSING_SLOT = 4; // 가호1
    private static int GRACE_SLOT = 5; // 가호2
    private static int PROTECTION_SLOT = 6; // 가호3
    private static int BOOK_SLOT = 7; // 책(강화확률)
    private static int HSHARD_SLOT = 8; // 명예의 파편

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    protected final ContainerData data;

    public ArmorForgingTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ARMOR_FORGING_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return 0;
            }

            @Override
            public void set(int pIndex, int pValue) {

            }

            @Override
            public int getCount() {
                return 7; // total number of data elements
            }
        };
    }

    public void forge() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();

        if(isForgable())
        {
            int curLevel = tag.getInt("armor.level");
            tag.putInt("armor.level", curLevel + 1);
        }

    }

    public boolean hasArmor() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        return !armor.isEmpty();
    }


    public void tick(Level pLevel, BlockPos pPos, BlockState pBlockState)
    {
        if(hasArmor())
        {
            if(!isForgable())
            {
                if(hasEnoughItems(HSHARD_SLOT, 1))
                {
                    removeItemFromSlot(HSHARD_SLOT, 1);
                    increaseHShard();
                    setChanged();
                }
            }
            else
            {

            }
        }
    }

    public boolean hasEnoughItems(int slot, int count) {
        ItemStack stack = itemHandler.getStackInSlot(slot); // 지정한 슬롯에서 아이템 가져오기
        return !stack.isEmpty() && stack.getCount() >= count; // 아이템이 있고, 수량이 충분한지 여부 반환
    }

    public void removeItemFromSlot(int slot, int count) {
        ItemStack stack = itemHandler.getStackInSlot(slot); // itemHandler에서 슬롯 아이템을 가져옴
        if (!stack.isEmpty() && stack.getCount() >= count) {
            stack.shrink(count); // 지정된 개수만큼 아이템을 줄임
            if (stack.isEmpty()) {
                itemHandler.setStackInSlot(slot, ItemStack.EMPTY); // 아이템이 모두 소모되었을 때 빈 슬롯으로 설정
            }
            setChanged(); // 블록 엔티티가 변경되었음을 알림 (클라이언트와 동기화)
        }
    }

    private void isHShardFull(CompoundTag tag)
    {
        if(tag.getInt("armor.hshard") == 64)
        {
            tag.putBoolean("armor.forgable", true);
        }
    }

    public int getHShard()
    {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();

       return tag.getInt("armor.hshard");
    }

    private void increaseHShard()
    {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();

        int curHShard = tag.getInt("armor.hshard");
        tag.putInt("armor.hshard", curHShard + 1);

        isHShardFull(tag);
    }

    private boolean isForgable()
    {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();

        return tag.getBoolean("armor.forgable");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
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

    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ArmorForgingTableMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.lostarkmod.armor_forging_table");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        // TODO Auto-generated method stub
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        // TODO Auto-generated method stub
        super.load(pTag);
    }

}