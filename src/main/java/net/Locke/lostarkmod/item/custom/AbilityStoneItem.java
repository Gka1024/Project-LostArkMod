package net.Locke.lostarkmod.item.custom;

import net.Locke.lostarkmod.effect.ModEffects;
import net.Locke.lostarkmod.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
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
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
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

            private boolean isBuffApplied = false;

            @Override
            public ItemStack getStack() {
                return stack;
            }

            @Override
            public void curioTick(SlotContext slotContext) {
                if (slotContext.entity() instanceof Player player) {
                    if (!isBuffApplied) {
                        AbilityStoneEffects(player, stack);
                        isBuffApplied = true;
                    }
                }
            }

            @Override
            public void onUnequip(SlotContext slotContext, ItemStack newStack) {
                if (slotContext.entity() instanceof Player player) {
                    isBuffApplied = false;
                    AbilityStoneRemoved(player, stack);
                }
            }
        });
    }

    private void AbilityStoneEffects(Player player, ItemStack stack) {
        int duration = -1;
        CompoundTag tag = stack.getOrCreateTag();
        int opt1 = tag.getInt("opt1.index");
        int opt2 = tag.getInt("opt2.index");
        int opt3 = tag.getInt("opt3.index");

        int opt1Level = tag.getInt("opt1.level");
        int opt2Level = tag.getInt("opt2.level");
        int opt3Level = tag.getInt("opt3.level");

        if (opt1Level != 0)
            ApplyBuff(player, duration, opt1, opt1Level - 1, false, false);

        if (opt2Level != 0)
            ApplyBuff(player, duration, opt2, opt2Level - 1, false, false);

        if (opt3Level != 0)
            ApplyBuff(player, duration, opt3, opt3Level - 1, true, false);

    }

    private void AbilityStoneRemoved(Player player, ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        int opt1 = tag.getInt("opt1.index");
        int opt2 = tag.getInt("opt2.index");
        int opt3 = tag.getInt("opt3.index");

        System.out.println(Integer.toString(opt1) + " " + Integer.toString(opt2) + " " + Integer.toString(opt3));

        ApplyBuff(player, 1, opt1, 0, false, true);
        ApplyBuff(player, 1, opt2, 0, false, true);
        ApplyBuff(player, 1, opt3, 0, true, true);
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

    private void ApplyBuff(Player player, int duration, int option, int amplifier, boolean isDisable,
            boolean isRemove) {

        MobEffect effect = MobEffects.LUCK; // 초기화 목적으로 설정해두었습니다.

        if (!isDisable) {
            switch (option) {
                case 1:
                    effect = ModEffects.MELEE_DAMAGE.get();
                    break;
                case 2:
                    effect = ModEffects.MAGIC_DAMAGE.get();
                    break;
                case 3:
                    effect = ModEffects.ATTACK_SPEED.get();
                    break;
                case 4:
                    effect = ModEffects.DEFENCE.get();
                    break;
                case 5:
                    effect = ModEffects.MOVE_SPEED.get();
                    break;
                case 6:
                    effect = ModEffects.ADD_HEART.get();
                    break;
                case 7:
                    effect = ModEffects.ADD_MANA.get();
                    break;
                case 8:
                    effect = ModEffects.BUFF_ATTACK.get();
                    break;
                case 9:
                    effect = ModEffects.BUFF_SHIELD.get();
                    break;
                case 10:
                    effect = ModEffects.MINING_SPEED.get();
                    break;
                case 11:
                    effect = ModEffects.RANGED_DAMAGE.get();
                    break;
                case 12:
                    effect = ModEffects.MANA_REGEN.get();
                    break;
                case 13:
                    effect = ModEffects.HEALTH_REGEN.get();
                    break;
                case 14:
                    effect = ModEffects.LESS_COOLDOWN.get();
                    break;
                case 15:
                    effect = ModEffects.LUCKY.get();
                    break;

                default:
                    break;
            }
        } else if (isDisable) {
            switch (option) {
                case 1:
                    effect = ModEffects.LESS_DAMAGE.get();
                    break;
                case 2:
                    effect = ModEffects.SLOW_ATKSPEED.get();
                    break;
                case 3:
                    effect = ModEffects.DAMAGE_INCOME.get();
                    break;
                case 4:
                    effect = ModEffects.SLOW_MOVESPEED.get();
                    break;
                case 5:
                    effect = ModEffects.REMOVE_HEART.get();
                    break;

                default:
                    break;
            }
        }
        
        if (!isRemove) {
            player.addEffect(new MobEffectInstance(effect, duration, amplifier));

        } else if (isRemove) {
            player.removeEffect(effect);
        }
    }
}
