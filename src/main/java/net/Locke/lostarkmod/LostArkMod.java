package net.Locke.lostarkmod;

import com.mojang.logging.LogUtils;

import net.Locke.lostarkmod.block.ModBlocks;
import net.Locke.lostarkmod.block.entity.ModBlockEntities;
import net.Locke.lostarkmod.item.ModCreativeModTabs;
import net.Locke.lostarkmod.item.Moditems;
import net.Locke.lostarkmod.network.ModMessages;
import net.Locke.lostarkmod.screen.ModMenuTypes;
import net.Locke.lostarkmod.screen.StoneCarvingTableScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LostArkMod.MOD_ID)
public class LostArkMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "lostarkmod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public LostArkMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        Moditems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(Moditems.MOKOKO);
            event.accept(Moditems.SILLING);
            event.accept(Moditems.SILLING_BOX);
            event.accept(Moditems.GOLD_LOSTARK);
            event.accept(Moditems.DESTRUCTION_STONE);
            event.accept(Moditems.DESTRUCTION_STONE_HONOR);
            event.accept(Moditems.DESTRUCTION_STONE_GREAT_HONOR);
            event.accept(Moditems.GUARDIAN_STONE);
            event.accept(Moditems.GUARDIAN_STONE_HONOR);
            event.accept(Moditems.GUARDIAN_STONE_GREAT_HONOR);
            event.accept(Moditems.HONOR_SHARD);
            event.accept(Moditems.HONOR_SHARD_PACK_SMALL);
            event.accept(Moditems.HONOR_SHARD_PACK_MEDIUM);
            event.accept(Moditems.HONOR_SHARD_PACK_LARGE);
            event.accept(Moditems.HONOR_LEAPSTONE);
            event.accept(Moditems.HONOR_LEAPSTONE_GREAT);
            event.accept(Moditems.HONOR_LEAPSTONE_MARVELOUS);
            event.accept(Moditems.HONOR_LEAPSTONE_SPLENDID);
            event.accept(Moditems.GOLD_BOX_LARGE);
            event.accept(Moditems.GOLD_BOX);
            event.accept(Moditems.GOLD_PILE);
            event.accept(Moditems.GOLD_POCKET);
            event.accept(Moditems.GOLDBAR_HUGE);
            event.accept(Moditems.GOLDBAR_THICK);
            event.accept(Moditems.GOLDBAR_THIN);
            event.accept(Moditems.OREHA_FUSION_MATERIAL_ADVANCED);
            event.accept(Moditems.OREHA_FUSION_MATERIAL_BASIC);
            event.accept(Moditems.OREHA_FUSION_MATERIAL);
            event.accept(Moditems.SOLAR_BLESSING);
            event.accept(Moditems.SOLAR_GRACE);
            event.accept(Moditems.NECKLACE);
            event.accept(Moditems.ABILITY_STONE);
            event.accept(Moditems.ABILITY_STONE_CARVED);
            
            event.accept(Moditems.SALVATION_HELMET);
            event.accept(Moditems.SALVATION_CHESTPLATE);
            event.accept(Moditems.SALVATION_LEGGINGS);
            event.accept(Moditems.SALVATION_BOOTS);
            
            

        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods
    // in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.STONE_CARVING_MENU.get(), StoneCarvingTableScreen::new);
        }
    }
}
