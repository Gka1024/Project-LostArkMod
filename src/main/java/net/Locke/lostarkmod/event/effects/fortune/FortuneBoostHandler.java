package net.Locke.lostarkmod.event.effects.fortune;

import java.util.HashMap;
import java.util.Map;

import net.Locke.lostarkmod.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FortuneBoostHandler {

    public FortuneBoostHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        
        if (player instanceof ServerPlayer serverPlayer) {
            ItemStack stack = player.getMainHandItem();

            if (player.hasEffect(ModEffects.LUCKY.get()) && serverPlayer.gameMode.getGameModeForPlayer() == GameType.SURVIVAL && !stack.isEmpty()) {
                // 현재 인챈트를 저장하고 포춘을 임시로 추가
                Map<Enchantment, Integer> enchants = new HashMap<>(EnchantmentHelper.getEnchantments(stack));
                int amplifier = player.getEffect(ModEffects.LUCKY.get()).getAmplifier();
                int curFortune = enchants.getOrDefault(Enchantments.BLOCK_FORTUNE, 0);
                enchants.put(Enchantments.BLOCK_FORTUNE, curFortune + Math.min(amplifier, 2));

                // 인챈트 임시 설정
                EnchantmentHelper.setEnchantments(enchants, stack);

                // 블록이 부서진 후 원래 인챈트로 복구
                if (player.level() instanceof ServerLevel serverLevel) {
                    serverLevel.getServer().execute(() -> {
                        if (curFortune == 0) {
                            enchants.remove(Enchantments.BLOCK_FORTUNE);
                        } else {
                            enchants.put(Enchantments.BLOCK_FORTUNE, curFortune);
                        }
                        EnchantmentHelper.setEnchantments(enchants, stack);
                    });
                }
            }
        }
    }
}