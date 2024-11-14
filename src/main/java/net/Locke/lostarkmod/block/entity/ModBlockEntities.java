package net.Locke.lostarkmod.block.entity;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, LostArkMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<StoneCarvingTableBlockEntity>> STONE_CARVING_BE = BLOCK_ENTITIES
            .register("stone_carving_be", () -> BlockEntityType.Builder
                    .of(StoneCarvingTableBlockEntity::new, ModBlocks.STONE_CARVING_TABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<ArmorForgingTableBlockEntity>> ARMOR_FORGING_BE = BLOCK_ENTITIES
            .register("armor_forging_be", () -> BlockEntityType.Builder
                    .of(ArmorForgingTableBlockEntity::new, ModBlocks.ARMOR_FORGING_TABLE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
