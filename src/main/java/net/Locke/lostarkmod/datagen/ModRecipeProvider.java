package net.Locke.lostarkmod.datagen;

import java.util.List;
import java.util.function.Consumer;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.block.ModBlocks;
import net.Locke.lostarkmod.item.Moditems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
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
        oreSmelting(pWriter, DESTRUCTION_SMELTABLES, RecipeCategory.MISC, Moditems.DESTRUCTION_STONE.get(), 0.25f, 200,
                "destruction_stone");
        oreBlasting(pWriter, DESTRUCTION_SMELTABLES, RecipeCategory.MISC, Moditems.DESTRUCTION_STONE.get(), 0.25f, 200,
                "destruction_stone");
        oreSmelting(pWriter, GUARDIAN_SMELTABLES, RecipeCategory.MISC, Moditems.GUARDIAN_STONE.get(), 0.25f, 200,
                "destruction_stone");
        oreBlasting(pWriter, GUARDIAN_SMELTABLES, RecipeCategory.MISC, Moditems.GUARDIAN_STONE.get(), 0.25f, 200,
                "destruction_stone");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Moditems.MOKOKO.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', Items.WHEAT_SEEDS)
                .unlockedBy("has_wheat_seeds", has(Items.WHEAT_SEEDS))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Moditems.SILLING.get(), 9)
                .requires(ModBlocks.SILLING_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SILLING_BLOCK.get()), has(ModBlocks.SILLING_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SILLING_BLOCK.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', Moditems.SILLING.get())
                .unlockedBy(getHasName(Moditems.SILLING.get()), has(Moditems.SILLING.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Moditems.DESTRUCTION_STONE_HONOR.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', Moditems.DESTRUCTION_STONE.get())
                .unlockedBy(getHasName(Moditems.DESTRUCTION_STONE.get()), has(Moditems.DESTRUCTION_STONE.get()))
                .save(pWriter, LostArkMod.MOD_ID + ":destruction_stone_honor_from_stone");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Moditems.DESTRUCTION_STONE_GREAT_HONOR.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', Moditems.DESTRUCTION_STONE_HONOR.get())
                .unlockedBy(getHasName(Moditems.DESTRUCTION_STONE.get()), has(Moditems.DESTRUCTION_STONE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Moditems.DESTRUCTION_STONE.get(), 9)
                .requires(Moditems.DESTRUCTION_STONE_HONOR.get())
                .unlockedBy(getHasName(Moditems.DESTRUCTION_STONE_HONOR.get()),
                        has(Moditems.DESTRUCTION_STONE_HONOR.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Moditems.DESTRUCTION_STONE_HONOR.get(), 4)
                .requires(Moditems.DESTRUCTION_STONE_GREAT_HONOR.get())
                .unlockedBy(getHasName(Moditems.DESTRUCTION_STONE_GREAT_HONOR.get()),
                        has(Moditems.DESTRUCTION_STONE_GREAT_HONOR.get()))
                .save(pWriter, LostArkMod.MOD_ID + ":destruction_stone_honor_from_great");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Moditems.GUARDIAN_STONE_HONOR.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', Moditems.GUARDIAN_STONE.get())
                .unlockedBy(getHasName(Moditems.GUARDIAN_STONE.get()), has(Moditems.GUARDIAN_STONE.get()))
                .save(pWriter, LostArkMod.MOD_ID + ":guardian_stone_honor_from_stone");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Moditems.GUARDIAN_STONE_GREAT_HONOR.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', Moditems.GUARDIAN_STONE_HONOR.get())
                .unlockedBy(getHasName(Moditems.GUARDIAN_STONE.get()), has(Moditems.GUARDIAN_STONE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Moditems.GUARDIAN_STONE.get(), 9)
                .requires(Moditems.GUARDIAN_STONE_HONOR.get())
                .unlockedBy(getHasName(Moditems.GUARDIAN_STONE_HONOR.get()),
                        has(Moditems.GUARDIAN_STONE_HONOR.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Moditems.GUARDIAN_STONE_HONOR.get(), 4)
                .requires(Moditems.GUARDIAN_STONE_GREAT_HONOR.get())
                .unlockedBy(getHasName(Moditems.GUARDIAN_STONE_GREAT_HONOR.get()),
                        has(Moditems.GUARDIAN_STONE_GREAT_HONOR.get()))
                .save(pWriter, LostArkMod.MOD_ID + ":guardian_stone_honor_from_great");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Moditems.GOLD_PILE.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', Moditems.GOLD_LOSTARK.get())
                .unlockedBy(getHasName(Moditems.GOLD_LOSTARK.get()), has(Moditems.GOLD_LOSTARK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Moditems.GOLD_LOSTARK.get(), 9)
                .requires(Moditems.GOLD_PILE.get())
                .unlockedBy(getHasName(Moditems.GOLD_PILE.get()), has(Moditems.GOLD_PILE.get()))
                .save(pWriter);

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
            RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients,
            RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer,
            RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients,
            RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup,
            String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,
                            LostArkMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

}
