package net.Locke.lostarkmod.effect.custom.armorset;

import net.Locke.lostarkmod.effect.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class EntropySetEffect extends MobEffect { // 사멸 세트입니다. 암살자를 위한 세트로 디자인 예정입니다. // 이동 속도 증가 && 뒤에서 공격 시 데미지 증가
    public EntropySetEffect() {
        super(MobEffectCategory.BENEFICIAL, 0);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();

            if (mainHandItem.getItem() instanceof AxeItem && offHandItem.isEmpty()) {
                if (!entity.hasEffect(ModEffects.MELEE_DAMAGE.get())) {
                    entity.addEffect(new net.minecraft.world.effect.MobEffectInstance(ModEffects.MELEE_DAMAGE.get(), -1, 0,
                            false, false, false));
                }
            } 
            else {
                entity.removeEffect(ModEffects.MELEE_DAMAGE.get());
            }
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        if (entity instanceof Player player) {
        }
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // 매 틱 효과를 적용하지 않고 일정 주기마다만 적용
        return duration % 20 == 0; // 1초마다 체크
    }
}
