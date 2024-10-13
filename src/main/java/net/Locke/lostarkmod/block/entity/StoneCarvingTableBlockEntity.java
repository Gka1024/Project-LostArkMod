package net.Locke.lostarkmod.block.entity;

import net.Locke.lostarkmod.item.ModItems;
import net.Locke.lostarkmod.screen.StoneCarvingTableMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
    protected final ContainerData data;

    private int currentProbability;
    private int opt1Cur, opt2Cur, opt3Cur; // 현재 얼마나 성공했는가
    private int opt1Prg, opt2Prg, opt3Prg; // 현재 얼마나 진행했는가
    private byte[] opt1Arr = new byte[10], opt2Arr = new byte[10], opt3Arr = new byte[10];
    private int opt1Index, opt2Index, opt3Index; // 어떤 옵션인가

    public StoneCarvingTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.STONE_CARVING_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> StoneCarvingTableBlockEntity.this.currentProbability;
                    case 1 -> 30; // maximum progress
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> StoneCarvingTableBlockEntity.this.opt1Cur = pValue;
                    case 1 -> StoneCarvingTableBlockEntity.this.opt2Cur = pValue;
                    case 2 -> StoneCarvingTableBlockEntity.this.opt3Cur = pValue;
                }
            }

            @Override
            public int getCount() {
                return 7;
            }
        };
    }

    public boolean hasEnoughItems(int slot, int count) {
        ItemStack stack = itemHandler.getStackInSlot(slot); // 지정한 슬롯에서 아이템 가져오기
        return !stack.isEmpty() && stack.getCount() >= count; // 아이템이 있고, 수량이 충분한지 여부 반환
    }

    public boolean isStoneExist() {
        ItemStack stack = itemHandler.getStackInSlot(0);
        return !stack.isEmpty();
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

    public void stoneCarve(int option) {

        ItemStack abilityStone = itemHandler.getStackInSlot(STONE_SLOT);

        if (isNew(abilityStone)) {
            //arrayClear(0);
            currentProbability = 75;
        }

        int ranInt = (int) (Math.random() * 100) + 1; // 난수 생성
        boolean isSuccess = currentProbability > ranInt;
        int curIncrease = isSuccess ? 1 : 0;

        CompoundTag tag = abilityStone.getOrCreateTag();

        if (!abilityStone.isEmpty() && abilityStone.getItem() == ModItems.ABILITY_STONE_UNCARVED.get()) {

            String key = "opt" + Integer.toString(option);

            int cur = tag.getInt(key + ".current"); // 얼마나 성공했는가?
            int prg = tag.getInt(key + ".progress"); // 얼마나 진행했는가?
            byte[] arr = tag.getByteArray(key + ".array"); // 어떻게 진행했는가?

            if (arr.length == 0) {
                arr = new byte[10];
            }

            tag.putInt(key + ".current", cur + curIncrease);

            tag.putInt(key + ".progress", prg + 1);

            arr[prg] = isSuccess ? (byte) 1 : 2;
            tag.putByteArray(key + ".array", arr);

            if (isSuccess && currentProbability > 25)
                currentProbability -= 10;
            else if (!isSuccess && currentProbability < 75)
                currentProbability += 10;

            tag.putInt("probability", currentProbability);

            playSoundBasedOnSuccess(this.worldPosition, option == 3, isSuccess);

            
            if(isCarveComplete())
            {
                MakeStone();
            }

            setChanged(); // 블록 엔티티 변경 알림
        }
    }

    private boolean isNew(ItemStack abilityStone)
    {
        CompoundTag tag = abilityStone.getOrCreateTag();
        if(tag.getInt("opt1.progress") != 0 || tag.getInt("opt1.progress") != 0|| tag.getInt("opt1.progress") != 0 )
        {
            return false;
        }
        return true;
    }

    public void playSoundBasedOnSuccess(BlockPos pos, boolean is3rd, boolean isSuccess) {

        SoundEvent soundEvent = isSuccess ? is3rd ? SoundEvents.DEEPSLATE_BREAK : SoundEvents.AMETHYST_BLOCK_BREAK
                : SoundEvents.GLASS_BREAK;

        // 사운드 재생
        Minecraft.getInstance().level.playSound(
                Minecraft.getInstance().player, // 사운드를 듣는 대상 플레이어
                pos, // 사운드가 발생하는 위치
                soundEvent, // 재생할 사운드
                net.minecraft.sounds.SoundSource.BLOCKS // 사운드의 소스(블록 관련 사운드)
        );

    }

    public int getCurrentProbability() {
        ItemStack abilityStone = itemHandler.getStackInSlot(STONE_SLOT);
        if (abilityStone.hasTag()) {
            return abilityStone.getTag().getInt("probability");
        }

        return 75;
    }

    public boolean isCarvable(int index) {
        ItemStack abilityStone = itemHandler.getStackInSlot(STONE_SLOT);
        CompoundTag tag = abilityStone.getOrCreateTag();

        return tag.getInt("opt" + Integer.toString(index) + ".progress") < 10;
    }

    public byte[] getByteArray(int index) {
        ItemStack abilityStone = itemHandler.getStackInSlot(STONE_SLOT);

        if (abilityStone.hasTag()) {
            CompoundTag tag = abilityStone.getTag();
            return tag.getByteArray("opt" + Integer.toString(index) + ".array");
        }

        return null;
    }

    public int getCurrent(int index) {
        ItemStack abilityStone = itemHandler.getStackInSlot(STONE_SLOT);
        if (abilityStone.hasTag()) {
            CompoundTag tag = abilityStone.getTag();
            return tag.getInt("opt" + Integer.toString(index) + ".current");
        }
        return 0;
    }
/* 
    private void arrayClear(int index) {
        switch (index) {
            case 1:
                opt1Arr = new byte[10];
            case 2:
                opt2Arr = new byte[10];
            case 3:
                opt3Arr = new byte[10];
            default:
                opt1Arr = new byte[10];
                opt2Arr = new byte[10];
                opt3Arr = new byte[10];
        }
    }
*/
    public boolean isCarveComplete() {
        ItemStack abilityStone = itemHandler.getStackInSlot(STONE_SLOT);
        CompoundTag tag = abilityStone.getTag();

        if (tag.getInt("opt1.progress") == 10 && tag.getInt("opt2.progress") == 10
                && tag.getInt("opt3.progress") == 10) {
            return true;
        }

        return false;
    }

    private void MakeStone()
    {
        ItemStack abilityStone = itemHandler.getStackInSlot(STONE_SLOT);
        CompoundTag tag = abilityStone.getTag();

        ItemStack newStone = new ItemStack(ModItems.ABILITY_STONE_CARVED.get());

        CompoundTag nbt = new CompoundTag();

        int opt1Level = ((tag.getInt("opt1.current") + 1) / 2) - 2;
        int opt2Level = ((tag.getInt("opt2.current") + 1) / 2) - 2;
        int opt3Level = ((tag.getInt("opt3.current") + 1) / 2) - 2;
        
        nbt.putInt("opt1.level", opt1Level > 0 ? opt1Level : 0);
        nbt.putInt("opt2.level", opt2Level > 0 ? opt2Level : 0);
        nbt.putInt("opt3.level", opt3Level > 0 ? opt3Level : 0);

        newStone.setTag(nbt);

        itemHandler.setStackInSlot(STONE_SLOT, newStone);
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

        CompoundTag optsTag = new CompoundTag();

        pTag.putInt("probability", currentProbability);

        pTag.putInt("opt1.current", opt1Cur);
        pTag.putInt("opt2.current", opt2Cur);
        pTag.putInt("opt3.current", opt3Cur);

        pTag.putInt("opt1.progress", opt1Prg);
        pTag.putInt("opt2.progress", opt2Prg);
        pTag.putInt("opt3.progress", opt3Prg);

        pTag.putByteArray("opt1.array", opt1Arr);
        pTag.putByteArray("opt2.array", opt2Arr);
        pTag.putByteArray("opt3.array", opt3Arr);

        pTag.put("options", optsTag);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {

        super.load(pTag);
        
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        
        ItemStack abilityStone = itemHandler.getStackInSlot(STONE_SLOT);
        CompoundTag tag = abilityStone.getOrCreateTag();

        currentProbability = tag.getInt("probability");

        opt1Prg = tag.getInt("opt1.progress");
        opt2Prg = tag.getInt("opt2.progress");
        opt3Prg = tag.getInt("opt3.progress");

        opt1Cur = tag.getInt("opt1.current");
        opt2Cur = tag.getInt("opt2.current");
        opt3Cur = tag.getInt("opt3.current");

        opt1Arr = tag.getByteArray("opt1.array");
        opt2Arr = tag.getByteArray("opt2.array");
        opt3Arr = tag.getByteArray("opt3.array"); 
    }

}