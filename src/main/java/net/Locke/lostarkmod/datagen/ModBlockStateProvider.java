package net.Locke.lostarkmod.datagen;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;    
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;


public class ModBlockStateProvider extends BlockStateProvider{
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper)
    {
        super(output, LostArkMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels(){
        blockWithItem(ModBlocks.SILLING_BLOCK);
        blockWithItem(ModBlocks.GOLD_BLOCK);
        blockWithItem(ModBlocks.DESTRUCTION_ORE);
        blockWithItem(ModBlocks.GUARDIAN_ORE);
        blockWithItem(ModBlocks.DESTRUCTION_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.GUARDIAN_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.STONE_CARVING_TABLE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
