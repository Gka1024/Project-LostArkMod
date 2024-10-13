package net.Locke.lostarkmod.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
            CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, LostArkMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider pProvider) {
        TagKey<Item> abilityStoneTag = ItemTags.create(new ResourceLocation("curios", "ability_stone"));
        this.tag(abilityStoneTag).add(ModItems.ABILITY_STONE_CARVED.get());
    }

}
