package net.Locke.lostarkmod.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class AbilityStoneItem extends Item implements ICurioItem{

    public AbilityStoneItem(Properties pProperties) {
        super(pProperties);
    }

    public int opt1, opt2, opt3;

    public enum ablityType{
        MELEE_DAMAGE,
        MAGIC_DAMAGE,
        ATTACK_SPEED,
        DEFFENCE,
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

    public enum disableType{
        LESS_DAMAGE,
        SLOW_ATKSPEED,
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
                // 장착 중일 때 버프 적용
                if (slotContext.entity() instanceof Player player) {
                    // 예: 이동 속도 버프 추가
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 0, true, false));
                }
            }
        });
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;  // 장착 가능 여부를 설정
    }
    
}
