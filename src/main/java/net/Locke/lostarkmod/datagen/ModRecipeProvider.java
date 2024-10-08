package net.Locke.lostarkmod.datagen;

import java.util.List;
import java.util.function.Consumer;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.block.ModBlocks;
import net.Locke.lostarkmod.item.ModItems;
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
    private static final List<ItemLike> DESTRUCTION_SMELTABLES = List.of(ModItems.DESTRUCTION_STONE.get(),
            ModBlocks.DESTRUCTION_ORE.get(), ModBlocks.DESTRUCTION_DEEPSLATE_ORE.get());
    private static final List<ItemLike> GUARDIAN_SMELTABLES = List.of(ModItems.GUARDIAN_STONE.get(),
            ModBlocks.GUARDIAN_ORE.get(), ModBlocks.GUARDIAN_DEEPSLATE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, DESTRUCTION_SMELTABLES, RecipeCategory.MISC, ModItems.DESTRUCTION_STONE.get(), 0.25f, 200,
                "destruction_stone");
        oreBlasting(pWriter, DESTRUCTION_SMELTABLES, RecipeCategory.MISC, ModItems.DESTRUCTION_STONE.get(), 0.25f, 200,
                "destruction_stone");
        oreSmelting(pWriter, GUARDIAN_SMELTABLES, RecipeCategory.MISC, ModItems.GUARDIAN_STONE.get(), 0.25f, 200,
                "guardian_stone");
        oreBlasting(pWriter, GUARDIAN_SMELTABLES, RecipeCategory.MISC, ModItems.GUARDIAN_STONE.get(), 0.25f, 200,
                "guardian_stone");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MOKOKO.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', Items.WHEAT_SEEDS)
                .unlockedBy("has_wheat_seeds", has(Items.WHEAT_SEEDS))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SILLING.get(), 9)
                .requires(ModBlocks.SILLING_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SILLING_BLOCK.get()), has(ModBlocks.SILLING_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SILLING_BLOCK.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', ModItems.SILLING.get())
                .unlockedBy(getHasName(ModItems.SILLING.get()), has(ModItems.SILLING.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DESTRUCTION_STONE_HONOR.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', ModItems.DESTRUCTION_STONE.get())
                .unlockedBy(getHasName(ModItems.DESTRUCTION_STONE.get()), has(ModItems.DESTRUCTION_STONE.get()))
                .save(pWriter, LostArkMod.MOD_ID + ":destruction_stone_honor_from_stone");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DESTRUCTION_STONE_GREAT_HONOR.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', ModItems.DESTRUCTION_STONE_HONOR.get())
                .unlockedBy(getHasName(ModItems.DESTRUCTION_STONE.get()), has(ModItems.DESTRUCTION_STONE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DESTRUCTION_STONE.get(), 9)
                .requires(ModItems.DESTRUCTION_STONE_HONOR.get())
                .unlockedBy(getHasName(ModItems.DESTRUCTION_STONE_HONOR.get()),
                        has(ModItems.DESTRUCTION_STONE_HONOR.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DESTRUCTION_STONE_HONOR.get(), 4)
                .requires(ModItems.DESTRUCTION_STONE_GREAT_HONOR.get())
                .unlockedBy(getHasName(ModItems.DESTRUCTION_STONE_GREAT_HONOR.get()),
                        has(ModItems.DESTRUCTION_STONE_GREAT_HONOR.get()))
                .save(pWriter, LostArkMod.MOD_ID + ":destruction_stone_honor_from_great");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GUARDIAN_STONE_HONOR.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', ModItems.GUARDIAN_STONE.get())
                .unlockedBy(getHasName(ModItems.GUARDIAN_STONE.get()), has(ModItems.GUARDIAN_STONE.get()))
                .save(pWriter, LostArkMod.MOD_ID + ":guardian_stone_honor_from_stone");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GUARDIAN_STONE_GREAT_HONOR.get())
                .pattern("DD")
                .pattern("DD")
                .define('D', ModItems.GUARDIAN_STONE_HONOR.get())
                .unlockedBy(getHasName(ModItems.GUARDIAN_STONE.get()), has(ModItems.GUARDIAN_STONE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GUARDIAN_STONE.get(), 9)
                .requires(ModItems.GUARDIAN_STONE_HONOR.get())
                .unlockedBy(getHasName(ModItems.GUARDIAN_STONE_HONOR.get()),
                        has(ModItems.GUARDIAN_STONE_HONOR.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GUARDIAN_STONE_HONOR.get(), 4)
                .requires(ModItems.GUARDIAN_STONE_GREAT_HONOR.get())
                .unlockedBy(getHasName(ModItems.GUARDIAN_STONE_GREAT_HONOR.get()),
                        has(ModItems.GUARDIAN_STONE_GREAT_HONOR.get()))
                .save(pWriter, LostArkMod.MOD_ID + ":guardian_stone_honor_from_great");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLD_PILE.get())
                .pattern("DDD")
                .pattern("DDD")
                .pattern("DDD")
                .define('D', ModItems.GOLD_LOSTARK.get())
                .unlockedBy(getHasName(ModItems.GOLD_LOSTARK.get()), has(ModItems.GOLD_LOSTARK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.GOLD_LOSTARK.get(), 9)
                .requires(ModItems.GOLD_PILE.get())
                .unlockedBy(getHasName(ModItems.GOLD_PILE.get()), has(ModItems.GOLD_PILE.get()))
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
