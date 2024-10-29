package net.Locke.lostarkmod.effect.custom.harmful;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class RemoveHeartEffect extends MobEffect {
    private static final UUID MODIFIER_UUID = UUID.randomUUID();

    public RemoveHeartEffect() {
        super(MobEffectCategory.BENEFICIAL, 0); 
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            float removeHeart = -2 * (amplifier + 1);
            playerHeartAdjust(removeHeart, player);
        }
    }

    public void playerHeartAdjust(float value, Player player) {
        AttributeModifier modifier = new AttributeModifier(MODIFIER_UUID, "health", value,
                AttributeModifier.Operation.ADDITION);

        if (!player.getAttribute(Attributes.MAX_HEALTH).hasModifier(modifier)) {
            player.getAttribute(Attributes.MAX_HEALTH).addTransientModifier(modifier);

            player.setHealth(Math.min(player.getHealth() - value, player.getHealth()));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        if (entity instanceof Player player) {
            // 이펙트가 끝날 때 수정자를 제거합니다.
            player.getAttribute(Attributes.MAX_HEALTH).removeModifier(MODIFIER_UUID);
            if (player.getHealth() > Player.MAX_HEALTH) {
                player.setHealth(Player.MAX_HEALTH);
            }
        }
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
    }
}