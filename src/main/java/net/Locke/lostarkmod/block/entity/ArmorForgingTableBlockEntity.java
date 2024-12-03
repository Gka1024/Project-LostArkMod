package net.Locke.lostarkmod.block.entity;

import java.util.Set;

import javax.annotation.Nullable;

import org.antlr.v4.parse.ANTLRParser.elementOptions_return;
import org.jetbrains.annotations.NotNull;
import org.joml.Math;

import net.Locke.lostarkmod.item.ModItems;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ArmorForgingTableBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(9) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            if (slot == ARMOR_SLOT) {
                return isAllowedArmorItem(stack.getItem()) || isAllowedWeaponItem(stack.getItem());
            }

            if (slot == STONE_SLOT) {
                return isAllowedStoneItem(stack.getItem());
            }

            if (slot == LEAPSTONE_SLOT) {
                return isAllowedLeapstoneItem(stack.getItem());
            }

            if (slot == OREHA_SLOT) {
                return isAllowedOrehaItem(stack.getItem());
            }

            if (slot == LEAPSTONE_SLOT) {
                return isAllowedLeapstoneItem(stack.getItem());
            }

            if (slot == BLESSING_SLOT)
                return stack.getItem() == ModItems.SOLAR_BLESSING.get();
            if (slot == GRACE_SLOT)
                return stack.getItem() == ModItems.SOLAR_GRACE.get();
            if (slot == PROTECTION_SLOT)
                return stack.getItem() == ModItems.SOLAR_PROTECTION.get();

            if (slot == BOOK_SLOT)
                return stack.getItem() == ModItems.GUIDE_BOOK.get();
            if (slot == HSHARD_SLOT)
                return stack.getItem() == ModItems.HONOR_SHARD.get();

            return false;
        };
    };

    private final Set<Item> allowedArmorItems = Set.of(
            ModItems.SALVATION_HAT.get(),
            ModItems.SALVATION_CHESTPLATE.get(),
            ModItems.SALVATION_LEGGINGS.get(),
            ModItems.SALVATION_BOOTS.get(),
            ModItems.SALVATION_SHOULDER.get(),

            ModItems.ENTROPY_MASK.get(),
            ModItems.ENTROPY_CHESTPIECE.get(),
            ModItems.ENTROPY_LEGGINGS.get(),
            ModItems.ENTROPY_BOOTS.get(),
            ModItems.ENTROPY_SHOULDER.get(),

            ModItems.HALLUCINATION_HELM.get(),
            ModItems.HALLUCINATION_CHESTPIECE.get(),
            ModItems.HALLUCINATION_PANTS.get(),
            ModItems.HALLUCINATION_BOOTS.get(),
            ModItems.HALLUCINATION_SHOULDER.get(),

            ModItems.NIGHTMARE_HAT.get(),
            ModItems.NIGHTMARE_CHESTPIECE.get(),
            ModItems.NIGHTMARE_PANTS.get(),
            ModItems.NIGHTMARE_BOOTS.get(),
            ModItems.NIGHTMARE_SHOULDER.get(),

            ModItems.YEARNING_HAT.get(),
            ModItems.YEARNING_CHESTPIECE.get(),
            ModItems.YEARNING_PANTS.get(),
            ModItems.YEARNING_BOOTS.get(),
            ModItems.YEARNING_SHOULDER.get());

    private final Set<Item> allowedWeaponItems = Set.of(

    );

    private final Set<Item> allowedStoneItem = Set.of(
            ModItems.DESTRUCTION_STONE.get(),
            ModItems.DESTRUCTION_STONE_HONOR.get(),
            ModItems.DESTRUCTION_STONE_GREAT_HONOR.get(),

            ModItems.GUARDIAN_STONE.get(),
            ModItems.GUARDIAN_STONE_HONOR.get(),
            ModItems.GUARDIAN_STONE_GREAT_HONOR.get()

    );

    private final Set<Item> allowedLeapstoneItem = Set.of(
            ModItems.HONOR_LEAPSTONE.get(),
            ModItems.HONOR_LEAPSTONE_GREAT.get(),
            ModItems.HONOR_LEAPSTONE_MARVELOUS.get(),
            ModItems.HONOR_LEAPSTONE_SPLENDID.get());

    private final Set<Item> allowedOrehaItem = Set.of(
            ModItems.OREHA_FUSION_MATERIAL_ADVANCED.get(),
            ModItems.OREHA_FUSION_MATERIAL_BASIC.get(),
            ModItems.OREHA_FUSION_MATERIAL.get());

    private boolean isAllowedArmorItem(Item item) {
        return allowedArmorItems.contains(item);
    }

    private boolean isAllowedWeaponItem(Item item) {
        return allowedWeaponItems.contains(item);
    }

    private boolean isAllowedStoneItem(Item item) {
        return allowedStoneItem.contains(item);
    }

    private boolean isAllowedLeapstoneItem(Item item) {
        return allowedLeapstoneItem.contains(item);
    }

    private boolean isAllowedOrehaItem(Item item) {
        return allowedOrehaItem.contains(item);
    }

    private static int ARMOR_SLOT = 0; // 장비
    private static int STONE_SLOT = 1; // 수호석 | 파괴석
    private static int LEAPSTONE_SLOT = 2; // 명예의 돌파석
    private static int OREHA_SLOT = 3; // 오레하 융화재료
    private static int BLESSING_SLOT = 4; // 가호1
    private static int GRACE_SLOT = 5; // 가호2
    private static int PROTECTION_SLOT = 6; // 가호3
    private static int BOOK_SLOT = 7; // 책(강화확률)
    private static int HSHARD_SLOT = 8; // 명예의 파편

    int[] stone_use_array = { 16, 24, 32 };
    int[] leap_use_array = { 8, 12, 16 };
    int[] oreha_use_array = { 6, 10, 15 };

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

        ItemStack item = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = item.getOrCreateTag();

        if (!isForgable())
            return;

        if (!isResourceAppropriate(getItemLevel(), isAllowedWeaponItem(item.getItem())))
            return;

        if (!isResourceEnough(getItemLevel()))
            return;

        consumeResource(getItemLevel());
        if (getItemLevel() != 0) {
            consumeSolarResource();
        }

        float ranNum = (float) (Math.random() * 100); // 난수 생성
        ranNum = Math.round(ranNum * 100) / 100.0f;

        float curForgeProb = getBasicProbability();
        float addForgeProb = addProbability();
        float addFailProb = getFailureProbability();

        curForgeProb = Math.min(curForgeProb + addForgeProb + addFailProb, 100.0f);

        System.out
                .println(Float.toString(ranNum) + " / " + Float.toString(curForgeProb) + " " + (ranNum < curForgeProb));

        if (ranNum < curForgeProb || getItemLevel() == 0 || getArtisanEnergy() >= 100) {
            // 성공했을 때
            increaseItemLevel();
            setArtisanEnergy(0);
            setFailureAttempt(0);
        } else {
            // 실패했을 때
            putArtisanEnergy();
            putFailureAttempt();
            return;
        }

    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pBlockState) {
        if (hasArmor()) {
            if (!isForgable()) {
                if (hasEnoughItems(HSHARD_SLOT, 1)) {
                    removeItemFromSlot(HSHARD_SLOT, 1);
                    increaseHShard();
                    setChanged();
                }
            } else {

            }
        }
    }

    private void increaseItemLevel() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();

        if (isForgable()) {
            int curLevel = tag.getInt("armor.level");
            tag.putInt("armor.level", curLevel + 1);
            resetHShard();
            resetItemForgable();
        }
    }

    private void HShardFull(CompoundTag tag) {
        if (tag.getInt("armor.hshard") == 64) {
            tag.putBoolean("armor.forgable", true);
        }
    }

    private void increaseHShard() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();

        int curHShard = tag.getInt("armor.hshard");
        tag.putInt("armor.hshard", curHShard + 1);

        HShardFull(tag);
    }

    private void resetHShard() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();
        tag.putInt("armor.hshard", 0);
    }

    private void resetItemForgable() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();

        tag.putBoolean("armor.forgable", false);
    }

    private float addProbability() {
        float rtnNum = 0;

        rtnNum += addProbability(BLESSING_SLOT);
        rtnNum += addProbability(GRACE_SLOT);
        rtnNum += addProbability(PROTECTION_SLOT);
        /*
         * 각 아이템은 8개, 5개, 3개를 최대로 넣을 수 있습니다.
         * 아이템을 전부 넣으면 장비의 레벨에 따라 3%, 2%, 1% 확률이 올라갑니다.
         * 더 적게 넣는것도 가능합니다.
         */
        return rtnNum;
    }

    private float addProbability(int slot) {
        int level = getItemLevel();
        float probability = 0;
        int index = slot - 4;

        int quantity = getItemQuantity(slot);
        int multiplyer = 0;
        float baseProbability = 0;

        switch (level / 5) {
            case 0:
                multiplyer = 3;
                break;

            case 1:
                multiplyer = 2;
                break;

            case 2:
                multiplyer = 1;
                break;

            default:
                multiplyer = 1;
                break;
        }

        switch (index) {
            case 0:
                baseProbability = 0.125f;
                quantity = Math.min(quantity, 8);
                break;

            case 1:
                baseProbability = 0.2f;
                quantity = Math.min(quantity, 5);
                break;

            case 2:
                baseProbability = 0.33f;
                quantity = Math.min(quantity, 3);
                break;

            default:
                break;
        }

        probability = quantity * multiplyer * baseProbability;

        if (index == 2) {
            probability = Math.round(probability * 10) / 10.0f;
        } else {
            probability = Math.round(probability * 1000) / 1000.0f;
        }

        return probability;
    }

    private boolean isResourceAppropriate(int level, boolean isWeapon) {
        boolean flag = false;
        switch (level) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                if (itemHandler.getStackInSlot(STONE_SLOT)
                        .getItem() == (isWeapon ? ModItems.DESTRUCTION_STONE.get() : ModItems.GUARDIAN_STONE) &&
                        itemHandler.getStackInSlot(LEAPSTONE_SLOT).getItem() == ModItems.HONOR_LEAPSTONE.get() &&
                        itemHandler.getStackInSlot(OREHA_SLOT).getItem() == ModItems.OREHA_FUSION_MATERIAL.get()) {
                    flag = true;
                }
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                if (itemHandler.getStackInSlot(STONE_SLOT)
                        .getItem() == (isWeapon ? ModItems.DESTRUCTION_STONE_HONOR.get()
                                : ModItems.GUARDIAN_STONE_HONOR)
                        &&
                        itemHandler.getStackInSlot(LEAPSTONE_SLOT).getItem() == ModItems.HONOR_LEAPSTONE_GREAT.get() &&
                        itemHandler.getStackInSlot(OREHA_SLOT).getItem() == ModItems.OREHA_FUSION_MATERIAL_BASIC
                                .get()) {
                    flag = true;
                }
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                if (itemHandler.getStackInSlot(STONE_SLOT)
                        .getItem() == (isWeapon ? ModItems.DESTRUCTION_STONE_GREAT_HONOR.get()
                                : ModItems.GUARDIAN_STONE_GREAT_HONOR)
                        &&
                        itemHandler.getStackInSlot(LEAPSTONE_SLOT).getItem() == ModItems.HONOR_LEAPSTONE_MARVELOUS.get()
                        &&
                        itemHandler.getStackInSlot(OREHA_SLOT).getItem() == ModItems.OREHA_FUSION_MATERIAL_ADVANCED
                                .get()) {
                    flag = true;
                }
            default:
                if (itemHandler.getStackInSlot(STONE_SLOT)
                        .getItem() == (isWeapon ? ModItems.DESTRUCTION_STONE_GREAT_HONOR.get()
                                : ModItems.GUARDIAN_STONE_GREAT_HONOR)
                        &&
                        itemHandler.getStackInSlot(LEAPSTONE_SLOT).getItem() == ModItems.HONOR_LEAPSTONE_SPLENDID.get()
                        &&
                        itemHandler.getStackInSlot(OREHA_SLOT).getItem() == ModItems.OREHA_FUSION_MATERIAL_ADVANCED
                                .get()) {
                    flag = true;
                }

                break;
        }

        return flag;
    }

    public int getAppropriateResource(int level, boolean isWeapon, int slot) {
        switch (level) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                if (slot == STONE_SLOT) {
                    if (isWeapon) {
                        return 8;
                    } else {
                        return 4;
                    }
                } else if (slot == OREHA_SLOT) {
                    return 0;
                } else if (slot == LEAPSTONE_SLOT) {
                    return 12;
                }
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                if (slot == STONE_SLOT) {
                    if (isWeapon) {
                        return 9;
                    } else {
                        return 5;
                    }
                } else if (slot == OREHA_SLOT) {
                    return 1;
                } else if (slot == LEAPSTONE_SLOT) {
                    return 13;
                }
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                if (slot == STONE_SLOT) {
                    if (isWeapon) {
                        return 10;
                    } else {
                        return 6;
                    }
                } else if (slot == OREHA_SLOT) {
                    return 2;
                } else if (slot == LEAPSTONE_SLOT) {
                    return 14;
                }
            default:
                if (slot == STONE_SLOT) {
                    if (isWeapon) {
                        return 10;
                    } else {
                        return 6;
                    }
                } else if (slot == OREHA_SLOT) {
                    return 2;
                } else if (slot == LEAPSTONE_SLOT) {
                    return 15;
                }

                break;
        }
        return 16;
    }

    private boolean isResourceEnough(int level) {

        boolean flag = true;
        if (!hasEnoughItems(STONE_SLOT, stone_use_array[Math.min(level / 5, 2)])) {
            flag = false;
        }
        if (!hasEnoughItems(LEAPSTONE_SLOT, leap_use_array[Math.min(level / 5, 2)])) {
            flag = false;
        }
        if (!hasEnoughItems(OREHA_SLOT, oreha_use_array[Math.min(level / 5, 2)])) {
            flag = false;
        }

        return flag;

    }

    private void consumeResource(int level) {
        removeItemFromSlot(STONE_SLOT, stone_use_array[Math.min(level / 5, 2)]);
        removeItemFromSlot(LEAPSTONE_SLOT, leap_use_array[Math.min(level / 5, 2)]);
        removeItemFromSlot(OREHA_SLOT, oreha_use_array[Math.min(level / 5, 2)]);
    }

    private void consumeSolarResource() {
        removeItemFromSlot(BLESSING_SLOT, 8);
        removeItemFromSlot(GRACE_SLOT, 5);
        removeItemFromSlot(PROTECTION_SLOT, 3);
    }

    private void putArtisanEnergy() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();
        float prob = getBasicProbability() + getAdditionalProbability();
        prob *= 0.05f;

        tag.putFloat("armor.artisan", tag.getFloat("armor.artisan") + prob);
    }

    private void setArtisanEnergy(int energy) {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();
        tag.putFloat("armor.artisan", energy);
    }

    private void putFailureAttempt() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();
        tag.putInt("armor.attempt", tag.getInt("armor.attempt") + 1);
    }

    private int getFailureAttempt() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();
        return tag.getInt("armor.attempt");
    }

    private void setFailureAttempt(int attempt) {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();
        tag.putInt("armor.attempt", attempt);
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

    public int getItemLevel() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();

        return tag.getInt("armor.level");
    }

    public int getBasicProbability() {
        int level = getItemLevel();
        switch (level) {
            case 0:
                return 100;

            case 1:
            case 2:
            case 3:
            case 4:
                return 20;

            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return 15;

            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return 10;

            case 15:
                return 5;

            default:
                return 5;
        }
    }

    public boolean isForgable() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();

        return tag.getBoolean("armor.forgable");
    }

    public int getHShard() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();

        return tag.getInt("armor.hshard");
    }

    public boolean hasArmor() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        return !armor.isEmpty();
    }

    public String getItemName() {
        if (itemHandler.getStackInSlot(ARMOR_SLOT).isEmpty()) {
            return "";
        }

        return itemHandler.getStackInSlot(ARMOR_SLOT).getHoverName().getString();
    }

    public int getResourceQuantity(int index) {

        int level = getItemLevel();

        switch (index) {
            case 0:
                return stone_use_array[Math.min(level / 3, 2)];

            case 1:
                return leap_use_array[Math.min(level / 3, 2)];

            case 2:
                return oreha_use_array[Math.min(level / 3, 2)];

            default:
                return 0;
        }
    }

    public int getItemQuantity(int slot) {
        ItemStack stack = itemHandler.getStackInSlot(slot);
        return stack.getCount();
    }

    public float getAdditionalProbability() {
        return addProbability();
    }

    public float getAdditionalProbability(int index) {
        return addProbability(index + 4);
    }

    public float getArtisanEnergy() {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        CompoundTag tag = armor.getOrCreateTag();
        return tag.getFloat("armor.artisan");
    }

    public float getFailureProbability() {
        float prob = getBasicProbability();
        prob *= 0.1f;
        return prob * getFailureAttempt();
    }

    public boolean isItemWeapon()
    {
        ItemStack armor = itemHandler.getStackInSlot(ARMOR_SLOT);
        return !isAllowedArmorItem(armor.getItem());
    }

    // 블록엔티티 기본 코드들입니다.
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