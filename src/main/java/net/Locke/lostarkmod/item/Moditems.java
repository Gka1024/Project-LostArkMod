package net.Locke.lostarkmod.item;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.item.custom.AbilityStoneItem;
import net.Locke.lostarkmod.item.custom.ModArmorItem;
import net.Locke.lostarkmod.item.custom.SillingBoxItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;;

public class ModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        LostArkMod.MOD_ID);

        public static final RegistryObject<Item> MOKOKO = ITEMS.register("mokoko",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> SILLING = ITEMS.register("silling",
                        () -> new Item(new Item.Properties().stacksTo(128)));
        public static final RegistryObject<Item> SILLING_BOX = ITEMS.register("silling_box",
                        () -> new SillingBoxItem(new Item.Properties()));
        public static final RegistryObject<Item> GOLD_LOSTARK = ITEMS.register("gold_lostark",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> DESTRUCTION_STONE = ITEMS.register("destruction_stone",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> DESTRUCTION_STONE_HONOR = ITEMS.register("destruction_stone_honor",
                        () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> DESTRUCTION_STONE_GREAT_HONOR = ITEMS.register(
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
        public static final RegistryObject<Item> ABILITY_STONE_UNCARVED = ITEMS.register("ability_stone_uncarved",
                        () -> new AbilityStoneItem(new Item.Properties()));
        public static final RegistryObject<Item> ABILITY_STONE_CARVED = ITEMS.register("ability_stone_carved",
                        () -> new AbilityStoneItem(new Item.Properties().stacksTo(1)));

        public static final RegistryObject<Item> SALVATION_HAT = ITEMS.register("salvation_hat",
                        () -> new ModArmorItem(ModArmorMaterials.SALVATION, ArmorItem.Type.HELMET,
                                        new Item.Properties()));
        public static final RegistryObject<Item> SALVATION_CHESTPLATE = ITEMS.register("salvation_chestpiece",
                        () -> new ArmorItem(ModArmorMaterials.SALVATION, ArmorItem.Type.CHESTPLATE,
                                        new Item.Properties()));
        public static final RegistryObject<Item> SALVATION_LEGGINGS = ITEMS.register("salvation_leggings",
                        () -> new ArmorItem(ModArmorMaterials.SALVATION, ArmorItem.Type.LEGGINGS,
                                        new Item.Properties()));
        public static final RegistryObject<Item> SALVATION_BOOTS = ITEMS.register("salvation_boots",
                        () -> new ArmorItem(ModArmorMaterials.SALVATION, ArmorItem.Type.BOOTS, new Item.Properties()));
        public static final RegistryObject<Item> SALVATION_SHOULDER = ITEMS.register("salvation_shoulder",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> ENTROPY_MASK = ITEMS.register("entropy_mask",
                        () -> new ModArmorItem(ModArmorMaterials.ENTROPY, ArmorItem.Type.HELMET,
                                        new Item.Properties()));
        public static final RegistryObject<Item> ENTROPY_CHESTPIECE = ITEMS.register("entropy_chestpiece",
                        () -> new ArmorItem(ModArmorMaterials.ENTROPY, ArmorItem.Type.CHESTPLATE,
                                        new Item.Properties()));
        public static final RegistryObject<Item> ENTROPY_LEGGINGS = ITEMS.register("entropy_leggings",
                        () -> new ArmorItem(ModArmorMaterials.ENTROPY, ArmorItem.Type.LEGGINGS,
                                        new Item.Properties()));
        public static final RegistryObject<Item> ENTROPY_BOOTS = ITEMS.register("entropy_boots",
                        () -> new ArmorItem(ModArmorMaterials.ENTROPY, ArmorItem.Type.BOOTS, new Item.Properties()));
        public static final RegistryObject<Item> ENTROPY_SHOULDER = ITEMS.register("entropy_shoulder",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> HALLUCINATION_HELM = ITEMS.register("hallucination_helm",
                        () -> new ModArmorItem(ModArmorMaterials.HALLUCINATION, ArmorItem.Type.HELMET,
                                        new Item.Properties()));
        public static final RegistryObject<Item> HALLUCINATION_CHESTPIECE = ITEMS.register("hallucination_chestplate",
                        () -> new ArmorItem(ModArmorMaterials.HALLUCINATION, ArmorItem.Type.CHESTPLATE,
                                        new Item.Properties()));
        public static final RegistryObject<Item> HALLUCINATION_PANTS = ITEMS.register("hallucination_pants",
                        () -> new ArmorItem(ModArmorMaterials.HALLUCINATION, ArmorItem.Type.LEGGINGS,
                                        new Item.Properties()));
        public static final RegistryObject<Item> HALLUCINATION_BOOTS = ITEMS.register("hallucination_boots",
                        () -> new ArmorItem(ModArmorMaterials.HALLUCINATION, ArmorItem.Type.BOOTS,
                                        new Item.Properties()));
        public static final RegistryObject<Item> HALLUCINATION_SHOULDER = ITEMS.register("hallucination_shoulder",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> NIGHTMARE_HAT = ITEMS.register("nightmare_hat",
                        () -> new ModArmorItem(ModArmorMaterials.NIGHTMARE, ArmorItem.Type.HELMET,
                                        new Item.Properties()));
        public static final RegistryObject<Item> NIGHTMARE_CHESTPIECE = ITEMS.register("nightmare_chestpiece",
                        () -> new ArmorItem(ModArmorMaterials.NIGHTMARE, ArmorItem.Type.CHESTPLATE,
                                        new Item.Properties()));
        public static final RegistryObject<Item> NIGHTMARE_PANTS = ITEMS.register("nightmare_pants",
                        () -> new ArmorItem(ModArmorMaterials.NIGHTMARE, ArmorItem.Type.LEGGINGS,
                                        new Item.Properties()));
        public static final RegistryObject<Item> NIGHTMARE_BOOTS = ITEMS.register("nightmare_boots",
                        () -> new ArmorItem(ModArmorMaterials.NIGHTMARE, ArmorItem.Type.BOOTS, new Item.Properties()));
        public static final RegistryObject<Item> NIGHTMARE_SHOULDER = ITEMS.register("nightmare_shoulder",
                        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> YEARNING_HAT = ITEMS.register("yearning_hat",
                        () -> new ModArmorItem(ModArmorMaterials.YEARNING, ArmorItem.Type.HELMET,
                                        new Item.Properties()));
        public static final RegistryObject<Item> YEARNING_CHESTPIECE = ITEMS.register("yearning_chestpiece",
                        () -> new ArmorItem(ModArmorMaterials.YEARNING, ArmorItem.Type.CHESTPLATE,
                                        new Item.Properties()));
        public static final RegistryObject<Item> YEARNING_PANTS = ITEMS.register("yearning_pants",
                        () -> new ArmorItem(ModArmorMaterials.YEARNING, ArmorItem.Type.LEGGINGS,
                                        new Item.Properties()));
        public static final RegistryObject<Item> YEARNING_BOOTS = ITEMS.register("yearning_boots",
                        () -> new ArmorItem(ModArmorMaterials.YEARNING, ArmorItem.Type.BOOTS, new Item.Properties()));
        public static final RegistryObject<Item> YEARNING_SHOULDER = ITEMS.register("yearning_shoulder",
                        () -> new Item(new Item.Properties()));

        public static void register(IEventBus eventbus) {
                ITEMS.register(eventbus);
        }
}
