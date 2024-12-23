package net.Locke.lostarkmod.event.set_effects.hallucination;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.effect.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;

@Mod.EventBusSubscriber(modid = LostArkMod.MOD_ID)
public class OnAttackShieldCooldown {

    private static final int COOLDOWN_REDUCTION_TICKS = 10; 

    public OnAttackShieldCooldown() {
        MinecraftForge.EVENT_BUS.register(this); // 이벤트 등록
    }

    private static boolean isPlayerHasSetEffect(Player player) {
        if (player.hasEffect(ModEffects.SET_HALLUCINATION.get())) {
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public void onPlayerAttack(AttackEntityEvent event) {
        Player player = event.getEntity();

        if (!isPlayerHasSetEffect(player)) {
            return;
        }

        ItemStack mainHandItem = player.getMainHandItem();
        if (mainHandItem.getItem() instanceof SwordItem) {
            float attackCooldown = player.getAttackStrengthScale(0f);
            if (attackCooldown >= 0.9f) {
                CompoundTag tag = player.getPersistentData();

                if (tag.contains("lastShieldTime")) {
                    long lastShieldTime = tag.getLong("lastShieldTime");
                    long newShieldTime = lastShieldTime - COOLDOWN_REDUCTION_TICKS;
                    System.out.println(newShieldTime);
                    tag.putLong("lastShieldTime", newShieldTime);
                }
            }
        }
    }
}
