package net.Locke.lostarkmod.datagen;

import java.util.List;
import java.util.function.Consumer;

import net.Locke.lostarkmod.block.ModBlocks;
import net.Locke.lostarkmod.item.Moditems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> DESTRUCTION_SMELTABLES = List.of(Moditems.DESTRUCTION_STONE.get(),
            ModBlocks.DESTRUCTION_ORE.get(), ModBlocks.DESTRUCTION_DEEPSLATE_ORE.get());
    private static final List<ItemLike> GUARDIAN_SMELTABLES = List.of(Moditems.GUARDIAN_STONE.get(),
            ModBlocks.GUARDIAN_ORE.get(), ModBlocks.GUARDIAN_DEEPSLATE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, DESTRUCTION_SMELTABLES, null, null, 0, 0, getName());
        oreBlasting(pWriter, DESTRUCTION_SMELTABLES, null, null, 0, 0, getName());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Moditems.DESTRUCTION_STONE_HONOR.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', Moditems.DESTRUCTION_STONE.get())
                .unlockedBy(getHasName(Moditems.DESTRUCTION_STONE.get()), has(Moditems.DESTRUCTION_STONE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Moditems.DESTRUCTION_STONE.get(), 9)
                .requires(Moditems.DESTRUCTION_STONE_HONOR.get())
                .unlockedBy(getHasName(Moditems.DESTRUCTION_STONE_HONOR.get()),
                        has(Moditems.DESTRUCTION_STONE_HONOR.get()))
                .save(pWriter);

    }

}
