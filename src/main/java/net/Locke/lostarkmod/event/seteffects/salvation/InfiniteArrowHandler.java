package net.Locke.lostarkmod.event.seteffects.salvation;

import java.util.HashMap;
import java.util.Map;

import net.Locke.lostarkmod.LostArkMod;
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
import net.minecraft.world.item.ArrowItem;
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

        if (bow.getItem() instanceof BowItem) {
            System.out.println("bow Use");
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

    private static final int CHARGE_DURATION = 25;
    private static final Map<Player, Integer> playerUseTime = new HashMap<>();
    private static boolean isPlayerUsingCrossbow = false;

    @SubscribeEvent
    public static void onCrossbowUse(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack crossbowStack = event.getItemStack();

        // 석궁 아이템인지 확인
        if (crossbowStack.getItem() instanceof CrossbowItem) {
            event.setCanceled(true); // 기본 동작 취소

            // 석궁이 장전되지 않았다면 장전 시작
            if (!CrossbowItem.isCharged(crossbowStack)) {
                player.startUsingItem(event.getHand()); // 장전 시작
                playerUseTime.put(player, 0);
                //tryLoadProjectiles(player, crossbowStack);
                System.out.println("Crossbow charging started without consuming arrows.");
            } else {
                // 석궁이 이미 장전된 상태라면 발사
                CrossbowItem.performShooting(player.level(), player, event.getHand(), crossbowStack, 1.0f, 1.0f);
                CrossbowItem.setCharged(crossbowStack, false); // 발사 후 미장전 상태로 변경
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        ItemStack item = player.getUseItem();
        if (player.isUsingItem() && playerUseTime.containsKey(player)) { // 장전 하는 중
            playerUseTime.put(player, playerUseTime.get(player) + 1);
            isPlayerUsingCrossbow = true;
        } else if (isPlayerUsingCrossbow && !player.isUsingItem()) { // 장전이 끝난 시점
            isPlayerUsingCrossbow = false;
            int chargetime = playerUseTime.remove(player);
            tryLoadProjectiles(player, item);
            // 여기서 tryLoadProjectiles를 실행해야 하는건 맞는데, player가 client-side에서밖에 실행되지 않아 화살이 계속 소모됨. 차후 패킷을 구현하여 문제 수정 예정
        }
    }

    private static boolean tryLoadProjectiles(LivingEntity shooter, ItemStack crossbowStack) {
        // 멀티샷 인챈트 확인
        int multishotLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MULTISHOT, crossbowStack);
        int quickChargeLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, crossbowStack);
        int projectileCount = multishotLevel == 0 ? 1 : 3;
        int maxChargeDuration = 25 - quickChargeLevel * 5;

        // 크리에이티브 모드 여부 확인
        boolean isCreativeMode = shooter instanceof Player && ((Player) shooter).getAbilities().instabuild;

        // 가상의 화살 생성 (화살이 없어도 장전되도록)
        ItemStack virtualArrow = new ItemStack(Items.ARROW);

        for (int i = 0; i < projectileCount; ++i) {
            if (!loadProjectile(shooter, crossbowStack, virtualArrow, i > 0, isCreativeMode)) {
                return false; // 실패 시 false 반환
            }
        }

        CrossbowItem.setCharged(crossbowStack, true); // 석궁을 장전된 상태로 설정
        System.out.println("Crossbow charged without consuming arrows.");
        return true;
    }

    private static boolean loadProjectile(LivingEntity shooter, ItemStack crossbowStack, ItemStack ammoStack,
            boolean hasAmmo, boolean isCreative) {
        // 아무런 아이템도 소모하지 않고 바로 성공 처리
        addChargedProjectile(crossbowStack, new ItemStack(Items.ARROW)); // 가상으로 화살을 추가
        return true;
    }

    private static void addChargedProjectile(ItemStack crossbowStack, ItemStack ammo) {
        CompoundTag tag = crossbowStack.getOrCreateTag();
        ListTag projectiles = tag.contains("ChargedProjectiles", 9) ? tag.getList("ChargedProjectiles", 10)
                : new ListTag();

        CompoundTag ammoTag = new CompoundTag();
        ammo.save(ammoTag);
        projectiles.add(ammoTag);

        tag.put("ChargedProjectiles", projectiles);
    }

}