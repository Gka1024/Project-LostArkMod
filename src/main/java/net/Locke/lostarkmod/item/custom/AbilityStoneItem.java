package net.Locke.lostarkmod.item.custom;

import net.Locke.lostarkmod.effect.ModEffects;
import net.minecraft.nbt.CompoundTag;
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

    public int opt1, opt2, opt3;

    public enum abilityType {
        MELEE_DAMAGE, // 1
        MAGIC_DAMAGE,
        ATTACK_SPEED,
        DEFFENCE, // 2
        SPEED,
        ADD_HEART,
        ADD_MANA,
        BUFF_ATTACK,
        BUFF_SHIELD,
        MANA_REGEN,
        MORE_HEALING,
        MINING_SPEED,
        LESS_COOLDOWN,
        LUCKY
    }

    public enum disableType {
        LESS_DAMAGE,
        SLOW_ATKSPEED, // 3
        DAMAGE_INCOME,
        SLOW_MOVESPEED,
        REMOVE_HEART
    }

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
        int progress = tag.getInt("opt" + Integer.toString(index) + ".progress");

        switch (progress) {
            case 5:
            case 6:

                break;

            case 7:
            case 8:

                break;

            case 9:
            case 10:

                break;

            default:
                break;
        }

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);

        if (stack.hasTag()) {
            CompoundTag nbt = stack.getTag();
            tooltip.add(Component.literal("NBT Data: " + nbt.toString()));
        } else {
            tooltip.add(Component.literal("No NBT data."));
        }
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true; // 장착 가능 여부를 설정
    }

}
