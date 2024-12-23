package net.Locke.lostarkmod.effect.custom.armorset;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class HallucinationSetEffect extends MobEffect { // 환각 세트입니다. 전사를 위한 세트로 디자인 예정입니다.
    public HallucinationSetEffect() {
        super(MobEffectCategory.BENEFICIAL, 0);
    }

    private static final int SHIELD_DELAY_TICKS = 100;

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            ItemStack offHand = player.getOffhandItem();

            // 방패를 들고 있는지 확인
            if (offHand.is(Items.SHIELD)) {
                CompoundTag tag = player.getPersistentData();
                long lastShieldTime = tag.getLong("lastShieldTime");
                long gameTime = player.level().getGameTime();
                float currentAbsorption = player.getAbsorptionAmount();

                if (currentAbsorption <= 0) {
                    // 보호막이 소모되었고, 대기 시간이 지난 경우
                    if (gameTime - lastShieldTime >= SHIELD_DELAY_TICKS) {
                        float shieldAmount = 2.0F + amplifier; // 보호막 값 (레벨에 따라 증가)
                        player.setAbsorptionAmount(shieldAmount); // 새로운 보호막 부여
                        tag.putLong("lastShieldTime", gameTime); // 마지막 보호막 시간 갱신
                    }
                } else {
                    // 현재 보호막이 남아있는 상태라면 마지막 시간 갱신
                    tag.putLong("lastShieldTime", gameTime);
                }
            }
        }
    }
    

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // 매 틱 효과를 적용하지 않고 일정 주기마다만 적용
        return duration % 20 == 0; // 1초마다 체크
    }

}
