package net.Locke.lostarkmod.datagen.loot;

import java.util.Set;

import net.Locke.lostarkmod.block.ModBlocks;
import net.Locke.lostarkmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SILLING_BLOCK.get());
        this.dropSelf(ModBlocks.GOLD_BLOCK.get());

        this.dropSelf(ModBlocks.STONE_CARVING_TABLE.get());

        this.add(ModBlocks.DESTRUCTION_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DESTRUCTION_ORE.get(), ModItems.DESTRUCTION_STONE.get()));
        this.add(ModBlocks.GUARDIAN_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.GUARDIAN_ORE.get(), ModItems.GUARDIAN_STONE.get()));
        this.add(ModBlocks.DESTRUCTION_DEEPSLATE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DESTRUCTION_DEEPSLATE_ORE.get(), ModItems.DESTRUCTION_STONE.get()));
        this.add(ModBlocks.GUARDIAN_DEEPSLATE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.GUARDIAN_DEEPSLATE_ORE.get(), ModItems.GUARDIAN_STONE.get()));

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

}
