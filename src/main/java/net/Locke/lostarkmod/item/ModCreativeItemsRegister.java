package net.Locke.lostarkmod.item;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModCreativeItemsRegister {

    @SubscribeEvent
    public static void registerItemsToTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            addGeneralItems(event);
            addCurrencyItems(event);
            addSalvationArmor(event);
            addEntropyArmor(event);
            addHallucinationArmor(event);
            addNightmareArmor(event);
            addYearningArmor(event);
        }
    }

    private static void addGeneralItems(BuildCreativeModeTabContentsEvent event) {
        event.accept(ModItems.MOKOKO);
        event.accept(ModItems.SILLING);
        event.accept(ModItems.SILLING_BOX);
        event.accept(ModItems.GOLD_LOSTARK);
        event.accept(ModItems.GUIDE_BOOK);
        event.accept(ModItems.MANA_CHECK);
        event.accept(ModItems.MANA_USE);
    }

    private static void addCurrencyItems(BuildCreativeModeTabContentsEvent event) {
        event.accept(ModItems.DESTRUCTION_STONE);
        event.accept(ModItems.DESTRUCTION_STONE_HONOR);
        event.accept(ModItems.DESTRUCTION_STONE_GREAT_HONOR);
        event.accept(ModItems.GUARDIAN_STONE);
        event.accept(ModItems.GUARDIAN_STONE_HONOR);
        event.accept(ModItems.GUARDIAN_STONE_GREAT_HONOR);
        event.accept(ModItems.HONOR_SHARD);
        event.accept(ModItems.HONOR_SHARD_PACK_SMALL);
        event.accept(ModItems.HONOR_SHARD_PACK_MEDIUM);
        event.accept(ModItems.HONOR_SHARD_PACK_LARGE);
        event.accept(ModItems.HONOR_LEAPSTONE);
        event.accept(ModItems.HONOR_LEAPSTONE_GREAT);
        event.accept(ModItems.HONOR_LEAPSTONE_MARVELOUS);
        event.accept(ModItems.HONOR_LEAPSTONE_SPLENDID);
        event.accept(ModItems.GOLD_BOX_LARGE);
        event.accept(ModItems.GOLD_BOX);
        event.accept(ModItems.GOLD_PILE);
        event.accept(ModItems.GOLD_POCKET);
        event.accept(ModItems.GOLDBAR_HUGE);
        event.accept(ModItems.GOLDBAR_THICK);
        event.accept(ModItems.GOLDBAR_THIN);
        event.accept(ModItems.OREHA_FUSION_MATERIAL_ADVANCED);
        event.accept(ModItems.OREHA_FUSION_MATERIAL_BASIC);
        event.accept(ModItems.OREHA_FUSION_MATERIAL);
        event.accept(ModItems.SOLAR_BLESSING);
        event.accept(ModItems.SOLAR_GRACE);
    }

    private static void addSalvationArmor(BuildCreativeModeTabContentsEvent event) {
        event.accept(ModItems.SALVATION_HAT);
        event.accept(ModItems.SALVATION_CHESTPLATE);
        event.accept(ModItems.SALVATION_LEGGINGS);
        event.accept(ModItems.SALVATION_BOOTS);
        event.accept(ModItems.SALVATION_SHOULDER);
    }

    private static void addEntropyArmor(BuildCreativeModeTabContentsEvent event) {
        event.accept(ModItems.ENTROPY_MASK);
        event.accept(ModItems.ENTROPY_CHESTPIECE);
        event.accept(ModItems.ENTROPY_LEGGINGS);
        event.accept(ModItems.ENTROPY_BOOTS);
        event.accept(ModItems.ENTROPY_SHOULDER);
    }

    private static void addHallucinationArmor(BuildCreativeModeTabContentsEvent event) {
        event.accept(ModItems.HALLUCINATION_HELM);
        event.accept(ModItems.HALLUCINATION_CHESTPIECE);
        event.accept(ModItems.HALLUCINATION_PANTS);
        event.accept(ModItems.HALLUCINATION_BOOTS);
        event.accept(ModItems.HALLUCINATION_SHOULDER);
    }

    private static void addNightmareArmor(BuildCreativeModeTabContentsEvent event) {
        event.accept(ModItems.NIGHTMARE_HAT);
        event.accept(ModItems.NIGHTMARE_CHESTPIECE);
        event.accept(ModItems.NIGHTMARE_PANTS);
        event.accept(ModItems.NIGHTMARE_BOOTS);
        event.accept(ModItems.NIGHTMARE_SHOULDER);
    }

    private static void addYearningArmor(BuildCreativeModeTabContentsEvent event) {
        event.accept(ModItems.YEARNING_HAT);
        event.accept(ModItems.YEARNING_CHESTPIECE);
        event.accept(ModItems.YEARNING_PANTS);
        event.accept(ModItems.YEARNING_BOOTS);
        event.accept(ModItems.YEARNING_SHOULDER);
    }
}
