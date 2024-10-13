package net.Locke.lostarkmod.datagen;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LostArkMod.MOD_ID, existingFileHelper);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.MOKOKO);
        simpleItem(ModItems.SILLING);
        simpleItem(ModItems.SILLING_BOX);
        simpleItem(ModItems.GOLD_LOSTARK);
        simpleItem(ModItems.DESTRUCTION_STONE);
        simpleItem(ModItems.DESTRUCTION_STONE_HONOR);
        simpleItem(ModItems.DESTRUCTION_STONE_GREAT_HONOR);
        simpleItem(ModItems.GUARDIAN_STONE);
        simpleItem(ModItems.GUARDIAN_STONE_HONOR);
        simpleItem(ModItems.GUARDIAN_STONE_GREAT_HONOR);
        simpleItem(ModItems.HONOR_SHARD);
        simpleItem(ModItems.HONOR_SHARD_PACK_SMALL);
        simpleItem(ModItems.HONOR_SHARD_PACK_MEDIUM);
        simpleItem(ModItems.HONOR_SHARD_PACK_LARGE);
        simpleItem(ModItems.HONOR_LEAPSTONE);
        simpleItem(ModItems.HONOR_LEAPSTONE_GREAT);
        simpleItem(ModItems.HONOR_LEAPSTONE_MARVELOUS);
        simpleItem(ModItems.HONOR_LEAPSTONE_SPLENDID);
        simpleItem(ModItems.GOLD_BOX_LARGE);
        simpleItem(ModItems.GOLD_BOX);
        simpleItem(ModItems.GOLD_PILE);
        simpleItem(ModItems.GOLD_POCKET);
        simpleItem(ModItems.GOLDBAR_HUGE);
        simpleItem(ModItems.GOLDBAR_THICK);
        simpleItem(ModItems.GOLDBAR_THIN);
        simpleItem(ModItems.OREHA_FUSION_MATERIAL_ADVANCED);
        simpleItem(ModItems.OREHA_FUSION_MATERIAL_BASIC);
        simpleItem(ModItems.OREHA_FUSION_MATERIAL);
        simpleItem(ModItems.NECKLACE);
        simpleItem(ModItems.SOLAR_BLESSING);
        simpleItem(ModItems.SOLAR_GRACE);
        simpleItem(ModItems.SOLAR_PROTECTION);
        simpleItem(ModItems.ABILITY_STONE_UNCARVED);
        simpleItem(ModItems.ABILITY_STONE_CARVED);
        simpleItem(ModItems.SALVATION_HELMET);
        simpleItem(ModItems.SALVATION_CHESTPLATE);
        simpleItem(ModItems.SALVATION_LEGGINGS);
        simpleItem(ModItems.SALVATION_BOOTS);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                        new ResourceLocation(LostArkMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
