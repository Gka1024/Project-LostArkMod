package net.Locke.lostarkmod.event.seteffects.salvation;

import java.util.HashMap;
import java.util.Map;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.effect.ModEffects;
import net.Locke.lostarkmod.network.CrossbowArrowSpeedPacket;
import net.Locke.lostarkmod.network.ModMessages;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LostArkMod.MOD_ID)
public class InfiniteArrowHandler {

    @SubscribeEvent
    public static void onArrowNock(ArrowNockEvent event) {
        Player player = event.getEntity();
        ItemStack bow = event.getBow();

        if(!isPlayerHasSetEffect(player))
        {
            return;
        }

        if (bow.getItem() instanceof BowItem) {
            player.startUsingItem(event.getHand());
            event.setAction(new InteractionResultHolder<>(InteractionResult.SUCCESS, bow));
        }

    }

    @SubscribeEvent
    public static void onArrowLoose(ArrowLooseEvent event) {

        Player player = event.getEntity();
        ItemStack bow = event.getBow();
        Level level = player.level();
        int charge = event.getCharge();

        if(!isPlayerHasSetEffect(player))
        {
            return;
        }

        if (bow.getItem() instanceof BowItem) {
            event.setCanceled(true); // 기본 화살 발사를 취소하여 화살이 소모되지 않도록 설정

            // 화살을 생성하고 발사
            if (!level.isClientSide) {
                float power = BowItem.getPowerForTime(charge); // 당긴 시간에 따른 화살 속도
                AbstractArrow arrow = new Arrow(level, player);
                arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, power * 3.0F, 1.0F); // 화살 속도
                arrow.setCritArrow(power == 1.0F); // 최대 당겼을 때 치명타 적용
                arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY; // 화살 소모 방지
                level.addFreshEntity(arrow); // 발사된 화살 추가

                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F,
                        1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F);
            }
        }
    }

    private static float arrowSpeed = 0.125f;
    private static final Map<Player, Integer> playerUseTime = new HashMap<>();

    @SubscribeEvent
    public static void onCrossbowUse(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack crossbowStack = event.getItemStack();

        if(!isPlayerHasSetEffect(player))
        {
            return;
        }

        // 석궁 아이템인지 확인
        if (crossbowStack.getItem() instanceof CrossbowItem) {
            event.setCanceled(true); // 기본 동작 취소

            // 석궁이 장전되지 않았다면 장전 시작
            if (!CrossbowItem.isCharged(crossbowStack)) {
                player.startUsingItem(event.getHand()); // 장전 시작
                playerUseTime.put(player, 0);
                tryLoadProjectiles(player, crossbowStack);
            } else {
                CrossbowItem.performShooting(player.level(), player, event.getHand(), crossbowStack, arrowSpeed, 1.0f);
                setArrowCreativeOnly(player);

                CrossbowItem.setCharged(crossbowStack, false); // 발사 후 미장전 상태로 변경
                arrowSpeed = 0.125f;
            }
        }
    }

    public static void setArrowCreativeOnly(Player player) {
        player.level().getEntitiesOfClass(AbstractArrow.class, player.getBoundingBox().inflate(2.0)).forEach(arrow -> {
            if (arrow.getOwner() == player) { // 플레이어가 발사한 화살인지 확인
                arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY; // 크리에이티브 전용 픽업 설정
            }
        });
    }

    private static boolean isPlayerHasSetEffect(Player player)
    {
        if(player.hasEffect(ModEffects.SET_SALVATION.get()))
        {
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.level();
        if (playerUseTime.containsKey(player)) {
            if (player.isUsingItem()) {
                playerUseTime.put(player, playerUseTime.get(player) + 1);
            } else {
                if (level.isClientSide) {
                    setArrowSpeed((Math.min(playerUseTime.get(player), 30) / 10f) + 0.125f);
                    ModMessages.INSTANCE
                            .sendToServer(new CrossbowArrowSpeedPacket(Math.min(playerUseTime.get(player), 30)));
                }
            }
        }
    }

    public static void setArrowSpeed(float speed) {
        arrowSpeed = speed;
    }

    public static boolean tryLoadProjectiles(LivingEntity shooter, ItemStack crossbowStack) {
        // 멀티샷 인챈트 확인
        int multishotLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MULTISHOT, crossbowStack);
        int projectileCount = multishotLevel == 0 ? 1 : 3;

        // 가상의 화살 생성 (화살이 없어도 장전되도록)
        ItemStack virtualArrow = new ItemStack(Items.ARROW);
        CompoundTag tag = crossbowStack.getOrCreateTag();

        // 기존의 "ChargedProjectiles" 태그를 초기화
        ListTag projectiles = new ListTag();

        for (int i = 0; i < projectileCount; ++i) {
            // 장전 프로세스를 단순화하여 항상 가상의 화살을 추가
            CompoundTag ammoTag = new CompoundTag();
            virtualArrow.save(ammoTag);
            projectiles.add(ammoTag);
        }

        // 모든 화살을 장전된 상태로 설정
        tag.put("ChargedProjectiles", projectiles);
        CrossbowItem.setCharged(crossbowStack, true); // 석궁을 장전된 상태로 설정

        return true;
    }

}