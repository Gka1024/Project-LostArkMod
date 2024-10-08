package net.Locke.lostarkmod.screen;

import net.Locke.lostarkmod.LostArkMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
        public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES,
                        LostArkMod.MOD_ID);

        public static final RegistryObject<MenuType<StoneCarvingTableMenu>> STONE_CARVING_MENU = registerMenuType(
                        "stone_carving_menu", StoneCarvingTableMenu::new);

                        
        // 메뉴 타입을 등록하는 공통 메서드
        private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(String name,
                        IContainerFactory<T> factory) {
                return MENUS.register(name, () -> IForgeMenuType.create(factory));
        }

        public static void register(IEventBus eventBus) {
                MENUS.register(eventBus);
        }
}