package net.Locke.lostarkmod.item.custom;

import java.util.Random;
import net.Locke.lostarkmod.item.Moditems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SillingBoxItem extends Item {

    public SillingBoxItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // 서버에서만 동작
        if (!level.isClientSide()) {
            // 랜덤한 개수 생성 (32부터 64까지)
            int min = 32;
            int max = 64;
            int randomAmount = getRandomIntInRange(min, max);

            // 플레이어가 사용하는 아이템 가져오기
            ItemStack itemInHand = player.getItemInHand(hand);

            // 특정 아이템 생성
            ItemStack itemToGive = new ItemStack(Moditems.SILLING.get(), randomAmount); // 원하는 아이템으로 변경
            ItemStack leftoverItem = itemToGive.copy(); // 남은 아이템을 저장

            // 아이템 지급 시도
            boolean addedSuccessfully = player.getInventory().add(itemToGive);

            if (addedSuccessfully) 
            {
                player.sendSystemMessage(Component.literal("SILLING : " + randomAmount + " has Given"));
                itemInHand.shrink(1); // SillingBoxItem 하나만 사라지도록 감소
            } 
            else 
            {
                // 지급 실패 시, 남은 아이템을 플레이어에게 반환
                player.getInventory().add(leftoverItem);
                player.sendSystemMessage(Component.literal("INVENTORY FULL!"));
                return InteractionResultHolder.fail(itemInHand);
            }
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
    // 특정 범위의 랜덤한 정수를 생성하는 메서드
    public static int getRandomIntInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("최솟값이 최댓값보다 커야 합니다.");
        }
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}