package net.Locke.lostarkmod.item;

import net.Locke.lostarkmod.LostArkMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;;

public class Moditems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LostArkMod.MOD_ID);

    public static final RegistryObject<Item> SILLING = ITEMS.register("silling",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILLING_BOX = ITEMS.register("silling_box",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_LOSTARK = ITEMS.register("gold_lostark",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventbus) {
        ITEMS.register(eventbus);
    }
}
