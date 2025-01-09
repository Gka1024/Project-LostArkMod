package net.Locke.lostarkmod.effect.custom.armorset;

import net.Locke.lostarkmod.capability.IMana;
import net.Locke.lostarkmod.capability.Mana;
import net.Locke.lostarkmod.capability.ManaProvider;
import net.Locke.lostarkmod.network.ModMessages;
import net.Locke.lostarkmod.network.packets.ManaSyncPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;

public class NightmareSetEffect extends MobEffect { // 악몽 세트입니다. 마법사를 위한 세트로 디자인 예정입니다.
    public NightmareSetEffect() {
        super(MobEffectCategory.BENEFICIAL, 0);
    }

    int originalMaxMana = 100;

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {

            if (!player.level().isClientSide) {
                IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElse(new Mana());

                if (!player.getPersistentData().getBoolean("NightmareEffectApplied")) {
                    originalMaxMana = mana.getMaxMana();

                    int curMana = mana.getMana();
                    int newMana = mana.getMaxMana() + (amplifier + 1) * 10;

                    mana.setMaxMana(newMana);
                    player.getPersistentData().putBoolean("NightmareEffectApplied", true);

                    ModMessages.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
                            new ManaSyncPacket(curMana, newMana));

                }
            }
            CompoundTag tag = player.getPersistentData();
            int manaGenDelayTime = 10;
            long lastManaGenTime = tag.getLong("lastManaGenTime");
            long gameTime = player.level().getGameTime();

            if (lastManaGenTime + manaGenDelayTime < gameTime) {
                IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElse(new Mana());
                mana.manaRegen(5);
                lastManaGenTime = gameTime;
            }

        }
    }

    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        if (entity instanceof Player player) {
            IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElse(new Mana());
            mana.setMaxMana(originalMaxMana);
            
            // 이펙트가 끝날 때 플래그 초기화
            player.getPersistentData().putBoolean("NightmareEffectApplied", false);

            ModMessages.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
                    new ManaSyncPacket(mana.getMana(), originalMaxMana));
        }
        super.removeAttributeModifiers(entity, attributeMap, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
