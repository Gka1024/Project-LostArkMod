package net.Locke.lostarkmod.item;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, LostArkMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> LOSTARK_TAB = CREATIVE_MODE_TABS.register("lostark_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MOKOKO.get()))
                    .title(Component.translatable("creativetab.lostark_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        // 모드에 등장하는 아이템의 경우에는 뒤에 .get()을 붙일것. 마인크래프트 내에 존재하는 경우에는 하지 않아도 됨.
                        pOutput.accept(ModItems.MOKOKO.get());
                        pOutput.accept(ModItems.SILLING.get());
                        pOutput.accept(ModItems.SILLING_BOX.get());
                        pOutput.accept(ModItems.GOLD_LOSTARK.get());
                        pOutput.accept(ModItems.GOLD_PILE.get());
                        pOutput.accept(ModItems.GOLD_POCKET.get());
                        pOutput.accept(ModItems.GOLD_BOX.get());
                        pOutput.accept(ModItems.GOLD_BOX_LARGE.get());
                        pOutput.accept(ModItems.GOLDBAR_THIN.get());
                        pOutput.accept(ModItems.GOLDBAR_THICK.get());
                        pOutput.accept(ModItems.GOLDBAR_HUGE.get());
                        pOutput.accept(ModItems.DESTRUCTION_STONE.get());
                        pOutput.accept(ModItems.DESTRUCTION_STONE_HONOR.get());
                        pOutput.accept(ModItems.DESTRUCTION_STONE_GREAT_HONOR.get());
                        pOutput.accept(ModItems.GUARDIAN_STONE.get());
                        pOutput.accept(ModItems.GUARDIAN_STONE_HONOR.get());
                        pOutput.accept(ModItems.GUARDIAN_STONE_GREAT_HONOR.get());
                        pOutput.accept(ModItems.HONOR_SHARD.get());
                        pOutput.accept(ModItems.HONOR_SHARD_PACK_SMALL.get());
                        pOutput.accept(ModItems.HONOR_SHARD_PACK_MEDIUM.get());
                        pOutput.accept(ModItems.HONOR_SHARD_PACK_LARGE.get());
                        pOutput.accept(ModItems.HONOR_LEAPSTONE.get());
                        pOutput.accept(ModItems.HONOR_LEAPSTONE_GREAT.get());
                        pOutput.accept(ModItems.HONOR_LEAPSTONE_MARVELOUS.get());
                        pOutput.accept(ModItems.HONOR_LEAPSTONE_SPLENDID.get());
                        pOutput.accept(ModItems.OREHA_FUSION_MATERIAL.get());
                        pOutput.accept(ModItems.OREHA_FUSION_MATERIAL_BASIC.get());
                        pOutput.accept(ModItems.OREHA_FUSION_MATERIAL_ADVANCED.get());
                        pOutput.accept(ModItems.SOLAR_PROTECTION.get());
                        pOutput.accept(ModItems.SOLAR_BLESSING.get());
                        pOutput.accept(ModItems.SOLAR_GRACE.get());
                        pOutput.accept(ModItems.NECKLACE.get());
                        pOutput.accept(ModItems.ABILITY_STONE_UNCARVED.get());
                        pOutput.accept(ModItems.ABILITY_STONE_CARVED.get());

                        pOutput.accept(ModItems.SALVATION_HAT.get());
                        pOutput.accept(ModItems.SALVATION_CHESTPLATE.get());
                        pOutput.accept(ModItems.SALVATION_LEGGINGS.get());
                        pOutput.accept(ModItems.SALVATION_BOOTS.get());
                        pOutput.accept(ModItems.SALVATION_SHOULDER.get());

                        pOutput.accept(ModItems.ENTROPY_MASK.get());
                        pOutput.accept(ModItems.ENTROPY_CHESTPIECE.get());
                        pOutput.accept(ModItems.ENTROPY_LEGGINGS.get());
                        pOutput.accept(ModItems.ENTROPY_BOOTS.get());
                        pOutput.accept(ModItems.ENTROPY_SHOULDER.get());

                        pOutput.accept(ModItems.HALLUCINATION_HELM.get());
                        pOutput.accept(ModItems.HALLUCINATION_CHESTPIECE.get());
                        pOutput.accept(ModItems.HALLUCINATION_PANTS.get());
                        pOutput.accept(ModItems.HALLUCINATION_BOOTS.get());
                        pOutput.accept(ModItems.HALLUCINATION_SHOULDER.get());

                        pOutput.accept(ModItems.NIGHTMARE_HAT.get());
                        pOutput.accept(ModItems.NIGHTMARE_CHESTPIECE.get());
                        pOutput.accept(ModItems.NIGHTMARE_PANTS.get());
                        pOutput.accept(ModItems.NIGHTMARE_BOOTS.get());
                        pOutput.accept(ModItems.NIGHTMARE_SHOULDER.get());

                        pOutput.accept(ModItems.YEARNING_HAT.get());
                        pOutput.accept(ModItems.YEARNING_CHESTPIECE.get());
                        pOutput.accept(ModItems.YEARNING_PANTS.get());
                        pOutput.accept(ModItems.YEARNING_BOOTS.get());
                        pOutput.accept(ModItems.YEARNING_SHOULDER.get());

                        pOutput.accept(ModBlocks.SILLING_BLOCK.get());
                        pOutput.accept(ModBlocks.GOLD_BLOCK.get());
                        pOutput.accept(ModBlocks.DESTRUCTION_ORE.get());
                        pOutput.accept(ModBlocks.GUARDIAN_ORE.get());
                        pOutput.accept(ModBlocks.DESTRUCTION_DEEPSLATE_ORE.get());
                        pOutput.accept(ModBlocks.GUARDIAN_DEEPSLATE_ORE.get());
                        pOutput.accept(ModBlocks.STONE_CARVING_TABLE.get());

                        pOutput.accept(Items.RAW_GOLD);

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}