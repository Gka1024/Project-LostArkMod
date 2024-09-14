package net.Locke.lostarkmod.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.block.ModBlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, LostArkMod.MOD_ID, existingFileHelper);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void addTags(Provider pProvider) {
        // this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES).add(ModBlocks.SILLING_BLOCK.get()).addtag(Tags.Blocks.ORES);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SILLING_BLOCK.get(),
                        ModBlocks.DESTRUCTION_ORE.get(),
                        ModBlocks.GUARDIAN_ORE.get(),
                        ModBlocks.DESTRUCTION_DEEPSLATE_ORE.get(),
                        ModBlocks.GUARDIAN_DEEPSLATE_ORE.get()

                );

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SILLING_BLOCK.get(),
                        ModBlocks.DESTRUCTION_ORE.get(),
                        ModBlocks.DESTRUCTION_DEEPSLATE_ORE.get(),
                        ModBlocks.GUARDIAN_ORE.get(),
                        ModBlocks.GUARDIAN_DEEPSLATE_ORE.get()

                );

    }
}
