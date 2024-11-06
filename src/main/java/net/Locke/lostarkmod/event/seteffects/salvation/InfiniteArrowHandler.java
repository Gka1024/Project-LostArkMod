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
                                                                                                               // 조절
                arrow.setCritArrow(power == 1.0F); // 최대 당겼을 때 치명타 적용
                arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY; // 화살 소모 방지
                level.addFreshEntity(arrow); // 발사된 화살 추가

                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F,
                        1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F);
            }
        }
    }

    private static final int CHARGE_DURATION = 20; // 20틱 이상 우클릭 유지 시 장전
    private static final Map<Player, Integer> playerUseTime = new HashMap<>();

    @SubscribeEvent
    public static void onCrossbowUse(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack crossbowStack = event.getItemStack();

        if (crossbowStack.getItem() instanceof CrossbowItem) {
            event.setCanceled(true);
            System.out.println("crossbow use");

            if (!CrossbowItem.isCharged(crossbowStack)) {
                player.startUsingItem(event.getHand()); // 장전 애니메이션 시작
                playerUseTime.put(player, 0);
            } else {
                // 석궁이 장전된 상태라면 발사
                CrossbowItem.performShooting(player.level(), player, event.getHand(), crossbowStack, 1.0f, 1.0f);
                CrossbowItem.setCharged(crossbowStack, false); // 발사 후 석궁을 다시 미장전 상태로 설정
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (playerUseTime.containsKey(player)) {
            if (player.isUsingItem()) {
                int curTime = playerUseTime.get(player) + 1;
                playerUseTime.put(player, curTime);

            } else {
                ItemStack crossbow = player.getUseItem();
                int useTime = playerUseTime.get(player);

                if (useTime >= 20) {
                    crossbow.getOrCreateTag().putBoolean("Charged", true);
                    tryLoadProjectiles(player, crossbow);
                    player.stopUsingItem();
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 1.0F, 1.0F);
                }

                playerUseTime.remove(player);
            }
        }

    }

    private static boolean tryLoadProjectiles(LivingEntity pShooter, ItemStack pCrossbowStack) {
        System.out.println("tryloadProjectile");
        int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MULTISHOT, pCrossbowStack);
        int j = i == 0 ? 1 : 3;
        boolean flag = pShooter instanceof Player && ((Player) pShooter).getAbilities().instabuild;
        ItemStack itemstack = pShooter.getProjectile(pCrossbowStack);
        ItemStack itemstack1 = itemstack.copy();

        for (int k = 0; k < j; ++k) {
            if (k > 0) {
                itemstack = itemstack1.copy();
            }

            if (itemstack.isEmpty() && flag) {
                itemstack = new ItemStack(Items.ARROW);
                itemstack1 = itemstack.copy();
            }

            if (!loadProjectile(pShooter, pCrossbowStack, itemstack, k > 0, flag)) {
                return false;
            }
        }

        return true;
    }

    private static boolean loadProjectile(LivingEntity pShooter, ItemStack pCrossbowStack, ItemStack pAmmoStack,
            boolean pHasAmmo, boolean pIsCreative) {
        System.out.println("loadProjectile");
        System.out.println(pHasAmmo);

        
        if (pAmmoStack.isEmpty()) 
        {

            System.out.println("empty");

            return false;
        } 
        else 
        {
            boolean flag = pIsCreative && pAmmoStack.getItem() instanceof ArrowItem;
            ItemStack itemstack;

            if (!flag && !pIsCreative && !pHasAmmo) 
            {
                itemstack = pAmmoStack.split(1);
                if (pAmmoStack.isEmpty() && pShooter instanceof Player) 
                {
                    ((Player) pShooter).getInventory().removeItem(pAmmoStack);
                }
            } 
            else 
            {
                itemstack = pAmmoStack.copy();
            }

            addChargedProjectile(pCrossbowStack, itemstack);
            return true;
        }
    }

    private static void addChargedProjectile(ItemStack pCrossbowStack, ItemStack pAmmoStack) {
        System.out.println("addChargedProjectile");
        CompoundTag compoundtag = pCrossbowStack.getOrCreateTag();
        ListTag listtag;
        if (compoundtag.contains("ChargedProjectiles", 9)) {
            listtag = compoundtag.getList("ChargedProjectiles", 10);
        } else {
            listtag = new ListTag();
        }

        CompoundTag compoundtag1 = new CompoundTag();
        pAmmoStack.save(compoundtag1);
        listtag.add(compoundtag1);
        compoundtag.put("ChargedProjectiles", listtag);
    }
}