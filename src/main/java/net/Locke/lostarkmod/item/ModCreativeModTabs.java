package net.Locke.lostarkmod.item;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LostArkMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> LOSTARK_TAB = CREATIVE_MODE_TABS.register("lostark_tab",
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(Moditems.SILLING.get()))
            .title(Component.translatable("creativetab.lostark_tab"))
            .displayItems((pParameters, pOutput) -> {
                //모드에 등장하는 아이템의 경우에는 뒤에 .get()을 붙일것. 마인크래프트 내에 존재하는 경우에는 하지 않아도 됨.
                pOutput.accept(Moditems.SILLING.get());
                pOutput.accept(Moditems.SILLING_BOX.get());
                pOutput.accept(Moditems.GOLD_LOSTARK.get());

                pOutput.accept(ModBlocks.SILLING_BLOCK.get());
                pOutput.accept(ModBlocks.GOLD_BLOCK.get());

                pOutput.accept(Items.RAW_GOLD);

            })
        .build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}