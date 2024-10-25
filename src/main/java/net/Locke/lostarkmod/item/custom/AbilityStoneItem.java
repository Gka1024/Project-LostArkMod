package net.Locke.lostarkmod.item.custom;

import net.Locke.lostarkmod.effect.ModEffects;
import net.Locke.lostarkmod.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class AbilityStoneItem extends Item implements ICurioItem {

    public AbilityStoneItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // 서버에서만 동작 - 랜덤한 버프를 부여하는 과정
        if (!level.isClientSide()) {
            ItemStack itemInHand = player.getItemInHand(hand);
            CompoundTag tag = itemInHand.getTag();
            ItemStack newStone = new ItemStack(ModItems.ABILITY_STONE_UNCARVED.get());

            if (tag == null) {
                tag = newStone.getOrCreateTag();
                int opt1Index = (int) (Math.random() * 15) + 1;
                int opt2Index = (int) (Math.random() * 14) + 1;
                int opt3Index = (int) (Math.random() * 5) + 1;

                if (opt1Index <= opt2Index) {
                    opt2Index++;
                }

                tag.putInt("opt1.index", opt1Index);
                tag.putInt("opt2.index", opt2Index);
                tag.putInt("opt3.index", opt3Index);
                tag.putDouble("random", Math.random());

                boolean addedSuccessfully = player.getInventory().add(newStone);

                if (addedSuccessfully) {
                    itemInHand.shrink(1); // SillingBoxItem 하나만 사라지도록 감소
                } else {
                    player.sendSystemMessage(Component.literal("INVENTORY FULL!"));
                    return InteractionResultHolder.fail(itemInHand);
                }
            }
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    public enum abilityType { // ~15
        MELEE_DAMAGE,
        MAGIC_DAMAGE,
        ATTACK_SPEED,
        DEFFENCE,
        SPEED,
        ADD_HEART,
        ADD_MANA,
        BUFF_ATTACK,
        BUFF_SHIELD,
        MINING_SPEED,
        RANGED_DAMAGE,
        MANA_REGEN,
        HEALTH_REGEN,
        LESS_COOLDOWN,
        LUCKY
    }

    public enum disableType { // ~5
        LESS_DAMAGE,
        SLOW_ATKSPEED,
        DAMAGE_INCOME,
        SLOW_MOVESPEED,
        REMOVE_HEART
    }

    abilityType[] abilities = abilityType.values();
    disableType[] disables = disableType.values();

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag unused) {
        return CuriosApi.createCurioProvider(new ICurio() {

            @Override
            public ItemStack getStack() {
                return stack;
            }

            @Override
            public void curioTick(SlotContext slotContext) {
                // applyBuffs(player, stack, 1);
            }
        });
    }

    private void ApplyBuffs(Player player, ItemStack stack, int index) {
        CompoundTag tag = stack.getOrCreateTag();
        int opt1 = tag.getInt("opt1.index");
        int opt2 = tag.getInt("opt2.index");
        int opt3 = tag.getInt("opt3.index");

        int opt1Level = tag.getInt("opt1.level");
        int opt2Level = tag.getInt("opt2.level");
        int opt3Level = tag.getInt("opt3.level");

        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);

        if (stack.hasTag()) {
            CompoundTag tag = stack.getTag();
            tooltip.add(Component.literal(
                    "Ability 1: " + abilities[tag.getInt("opt1.index") - 1] + " Lv" + tag.getInt("opt1.level")));
            tooltip.add(Component.literal(
                    "Ability 2: " + abilities[tag.getInt("opt2.index") - 1] + " Lv" + tag.getInt("opt2.level")));
            tooltip.add(Component
                    .literal("Disable : " + disables[tag.getInt("opt3.index") - 1] + " Lv" + tag.getInt("opt3.level")));

        } else {
            tooltip.add(Component.literal("No NBT data."));
        }
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true; // 장착 가능 여부를 설정
    }

}
