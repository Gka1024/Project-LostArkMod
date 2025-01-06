package net.Locke.lostarkmod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.Locke.lostarkmod.block.ModBlocks;
import net.Locke.lostarkmod.block.entity.ModBlockEntities;
import net.Locke.lostarkmod.client.SkillEventHandler;
import net.Locke.lostarkmod.client.ManaHUDRenderer;
import net.Locke.lostarkmod.command.CommandRegister;
import net.Locke.lostarkmod.effect.ModEffects;
import net.Locke.lostarkmod.event.ModEventsHandler;
import net.Locke.lostarkmod.item.ModCreativeItemsRegister;
import net.Locke.lostarkmod.item.ModCreativeModTabs;
import net.Locke.lostarkmod.item.ModItems;
import net.Locke.lostarkmod.network.ModMessages;
import net.Locke.lostarkmod.particle.ModParticles;
import net.Locke.lostarkmod.screen.ModMenuTypes;
import net.Locke.lostarkmod.screen.StoneCarvingTableScreen;
import net.Locke.lostarkmod.screen.ArmorForgingTableScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEffects.register(modEventBus);
        ModParticles.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(SkillEventHandler::registerKeyMappings);
    

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ModCreativeItemsRegister());
        MinecraftForge.EVENT_BUS.register(new CommandRegister());

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
        ModEventsHandler.registerAll();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
       
        
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
            MenuScreens.register(ModMenuTypes.ARMOR_FORGING_MENU.get(), ArmorForgingTableScreen::new);
            ManaHUDRenderer.register();
        }
    }
}
