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

    public ModItemModelProvider(PackOutput output,  ExistingFileHelper existingFileHelper) {
        super(output, LostArkMod.MOD_ID, existingFileHelper);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected void registerModels() {
        simpleItem(Moditems.SILLING);
    }


    private ItemModelBuilder simpleItem (RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
            new ResourceLocation("item/generated")).texture("layer0",
            new ResourceLocation(LostArkMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
