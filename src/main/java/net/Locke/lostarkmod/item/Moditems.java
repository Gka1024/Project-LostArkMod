package net.Locke.lostarkmod.item;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.item.custom.ModArmorItem;
import net.Locke.lostarkmod.item.custom.SillingBoxItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;;

public class Moditems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        LostArkMod.MOD_ID);

        public static final RegistryObject<Item> MOKOKO = ITEMS.register("mokoko",
                        () -> new Item(new Item.Properties()));

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
        public static final RegistryObject<Item> DESTRUCTION_STONE_GREAT_HONOR_ = ITEMS.register(
                        "destruction_stone_great_honor",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> GUARDIAN_STONE = ITEMS.register("guardian_stone",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> GUARDIAN_STONE_HONOR = ITEMS.register("guardian_stone_honor",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> GUARDIAN_STONE_GREAT_HONOR = ITEMS.register(
                        "guardian_stone_great_honor",
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

        public static final RegistryObject<Item> GOLD_BOX_LARGE = ITEMS.register("gold_box_large",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> GOLD_BOX = ITEMS.register("gold_box",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> GOLD_PILE = ITEMS.register("gold_pile",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> GOLD_POCKET = ITEMS.register("gold_pocket",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> GOLDBAR_HUGE = ITEMS.register("goldbar_huge",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> GOLDBAR_THICK = ITEMS.register("goldbar_thick",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> GOLDBAR_THIN = ITEMS.register("goldbar_thin",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> OREHA_FUSION_MATERIAL_ADVANCED = ITEMS.register(
                        "oreha_fusion_material_advanced",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> OREHA_FUSION_MATERIAL_BASIC = ITEMS.register(
                        "oreha_fusion_material_basic",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> OREHA_FUSION_MATERIAL = ITEMS.register("oreha_fusion_material",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> NECKLACE = ITEMS.register("necklace",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> SOLAR_BLESSING = ITEMS.register("solar_blessing",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> SOLAR_GRACE = ITEMS.register("solar_grace",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> SOLAR_PROTECTION = ITEMS.register("solar_protection",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> ABILITY_STONE = ITEMS.register("ability_stone",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> ABILITY_STONE_CARVED = ITEMS.register("ability_stone_carved",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> SALVATION_HELMET = ITEMS.register("salvation_helmet",
                        () -> new ModArmorItem(ModArmorMaterials.SALVATION, ArmorItem.Type.HELMET,
                                        new Item.Properties()));
        public static final RegistryObject<Item> SALVATION_CHESTPLATE = ITEMS.register("salvation_chestplate",
                        () -> new ArmorItem(ModArmorMaterials.SALVATION, ArmorItem.Type.CHESTPLATE,
                                        new Item.Properties()));
        public static final RegistryObject<Item> SALVATION_LEGGINGS = ITEMS.register("salvation_leggings",
                        () -> new ArmorItem(ModArmorMaterials.SALVATION, ArmorItem.Type.LEGGINGS,
                                        new Item.Properties()));
        public static final RegistryObject<Item> SALVATION_BOOTS = ITEMS.register("salvation_boots",
                        () -> new ArmorItem(ModArmorMaterials.SALVATION, ArmorItem.Type.BOOTS, new Item.Properties()));

        public static void register(IEventBus eventbus) {
                ITEMS.register(eventbus);
        }
}
