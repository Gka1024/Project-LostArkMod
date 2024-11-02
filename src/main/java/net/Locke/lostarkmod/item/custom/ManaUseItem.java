package net.Locke.lostarkmod.item.custom;

import net.Locke.lostarkmod.capability.IMana;
import net.Locke.lostarkmod.capability.Mana;
import net.Locke.lostarkmod.capability.ManaProvider;
import net.Locke.lostarkmod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ManaUseItem extends Item {

    public ManaUseItem(Properties pProperties) {
        super(pProperties);
        // TODO Auto-generated constructor stub
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        IMana mana = player.getCapability(ManaProvider.MANA_CAPABILITY).orElse(new Mana());

        if (mana.useMana(60)) {
            Minecraft.getInstance().player.displayClientMessage(
                    Component.literal("mana used success"), true);
        }
        
        
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}