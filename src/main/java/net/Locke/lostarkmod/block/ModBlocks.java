package net.Locke.lostarkmod.block;

import java.util.Properties;
import java.util.function.Supplier;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.item.Moditems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
                        LostArkMod.MOD_ID);

        public static final RegistryObject<Block> SILLING_BLOCK = registerBlock("silling_block",
                        () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

        public static final RegistryObject<Block> GOLD_BLOCK = registerBlock("gold_block",
                        () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

        public static final RegistryObject<Block> DESTRUCTION_ORE = registerBlock("destruction_ore",
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                                        .strength(3f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

        public static final RegistryObject<Block> GUARDIAN_ORE = registerBlock("guardian_ore",
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                                        .strength(3f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

        public static final RegistryObject<Block> DESTRUCTION_DEEPSLATE_ORE = registerBlock("destruction_deepslate_ore",
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                                        .strength(4.5f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

        public static final RegistryObject<Block> GUARDIAN_DEEPSLATE_ORE = registerBlock("guardian_deepslate_ore",
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                                        .strength(4.5f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

        public static final RegistryObject<Block> STONE_CARVING_TABLE = registerBlock("stone_carving_table",
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                                        .strength(4.5f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

        private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
                RegistryObject<T> toReturn = BLOCKS.register(name, block);
                registerBlockItem(name, toReturn);
                return toReturn;
        }

        private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
                return Moditems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        }

        public static void register(IEventBus eventBus) {
                BLOCKS.register(eventBus);
        }
}
