package net.Locke.lostarkmod.datagen;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.item.Moditems;
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
        simpleItem(Moditems.MOKOKO);
        simpleItem(Moditems.SILLING);
        simpleItem(Moditems.SILLING_BOX);
        simpleItem(Moditems.GOLD_LOSTARK);
        simpleItem(Moditems.DESTRUCTION_STONE);
        simpleItem(Moditems.DESTRUCTION_STONE_HONOR);
        simpleItem(Moditems.DESTRUCTION_STONE_GREAT_HONOR);
        simpleItem(Moditems.GUARDIAN_STONE);
        simpleItem(Moditems.GUARDIAN_STONE_HONOR);
        simpleItem(Moditems.GUARDIAN_STONE_GREAT_HONOR);
        simpleItem(Moditems.HONOR_SHARD);
        simpleItem(Moditems.HONOR_SHARD_PACK_SMALL);
        simpleItem(Moditems.HONOR_SHARD_PACK_MEDIUM);
        simpleItem(Moditems.HONOR_SHARD_PACK_LARGE);
        simpleItem(Moditems.HONOR_LEAPSTONE);
        simpleItem(Moditems.HONOR_LEAPSTONE_GREAT);
        simpleItem(Moditems.HONOR_LEAPSTONE_MARVELOUS);
        simpleItem(Moditems.HONOR_LEAPSTONE_SPLENDID);
        simpleItem(Moditems.GOLD_BOX_LARGE);
        simpleItem(Moditems.GOLD_BOX);
        simpleItem(Moditems.GOLD_PILE);
        simpleItem(Moditems.GOLD_POCKET);
        simpleItem(Moditems.GOLDBAR_HUGE);
        simpleItem(Moditems.GOLDBAR_THICK);
        simpleItem(Moditems.GOLDBAR_THIN);
        simpleItem(Moditems.OREHA_FUSION_MATERIAL_ADVANCED);
        simpleItem(Moditems.OREHA_FUSION_MATERIAL_BASIC);
        simpleItem(Moditems.OREHA_FUSION_MATERIAL);
        simpleItem(Moditems.NECKLACE);
        simpleItem(Moditems.SOLAR_BLESSING);
        simpleItem(Moditems.SOLAR_GRACE);
        simpleItem(Moditems.SOLAR_PROTECTION);
        simpleItem(Moditems.ABILITY_STONE);
        simpleItem(Moditems.ABILITY_STONE_CARVED);
        simpleItem(Moditems.SALVATION_HELMET);
        simpleItem(Moditems.SALVATION_CHESTPLATE);
        simpleItem(Moditems.SALVATION_LEGGINGS);
        simpleItem(Moditems.SALVATION_BOOTS);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                        new ResourceLocation(LostArkMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
