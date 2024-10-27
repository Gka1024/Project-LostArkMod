package net.Locke.lostarkmod.effect.custom.beneficial;

import net.Locke.lostarkmod.capability.IMana;
import net.Locke.lostarkmod.capability.Mana;
import net.Locke.lostarkmod.capability.ManaProvider;
import net.Locke.lostarkmod.network.ManaSyncPacket;
import net.Locke.lostarkmod.network.ModMessages;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

public class AddManaEffect extends MobEffect {
    public AddManaEffect() {
        super(MobEffectCategory.BENEFICIAL, 0); // 효과의 종류와 색상 설정
    }

    int originalMaxMana = 100;

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player && !player.level().isClientSide) {
            IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElse(new Mana());

            if (!player.getPersistentData().getBoolean("AddManaEffectApplied")) {
                originalMaxMana = mana.getMaxMana();

                int newMana = mana.getMaxMana() + (amplifier + 1) * 10;

                mana.setMaxMana(newMana);
                player.getPersistentData().putBoolean("AddManaEffectApplied", true);

                ModMessages.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
                        new ManaSyncPacket(newMana));

            }
        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        if (entity instanceof Player player) {
            IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElse(new Mana());
            mana.setMaxMana(originalMaxMana);

            // 이펙트가 끝날 때 플래그 초기화
            player.getPersistentData().putBoolean("AddManaEffectApplied", false);

            ModMessages.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
                    new ManaSyncPacket(originalMaxMana));
        }
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}