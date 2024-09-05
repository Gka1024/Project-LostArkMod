package net.Locke.lostarkmod.item;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.item.custom.SillingBoxItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;;

public class Moditems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        LostArkMod.MOD_ID);

        public static final RegistryObject<Item> SILLING = ITEMS.register("silling",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> SILLING_BOX = ITEMS.register("silling_box",
                        () -> new SillingBoxItem(new Item.Properties()));
        public static final RegistryObject<Item> GOLD_LOSTARK = ITEMS.register("gold_lostark",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> DESTRUCTION_STONE = ITEMS.register("destruction_stone",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> DESTRUCTION_STONE_HONOR = ITEMS.register("destruction_stone_honor",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> DESTRUCTION_STONE_GREAT_HONOR_ = ITEMS.register("destruction_stone_great_honor",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> GUARDIAN_STONE = ITEMS.register("guardian_stone",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> GUARDIAN_STONE_HONOR = ITEMS.register("guardian_stone_honor",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> GUARDIAN_STONE_GREAT_HONOR = ITEMS.register("guardian_stone_great_honor",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> HONOR_SHARD = ITEMS.register("honor_shard",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> HONOR_SHARD_PACK_SMALL = ITEMS.register("honor_shard_pack_small",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> HONOR_SHARD_PACK_MEDIUM = ITEMS.register("honor_shard_pack_medium",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> HONOR_SHARD_PACK_LARGE = ITEMS.register("honor_shard_pack_large",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> HONOR_LEAPSTONE = ITEMS.register("honor_leapstone",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> HONOR_LEAPSTONE_GREAT = ITEMS.register("honor_leapstone_great",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> HONOR_LEAPSTONE_MARVELOUS = ITEMS.register("honor_leapstone_marvelous",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> HONOR_LEAPSTONE_SPLENDID = ITEMS.register("honor_leapstone_splendid",
                        () -> new Item(new Item.Properties()));

        public static void register(IEventBus eventbus) {
                ITEMS.register(eventbus);
        }
}
