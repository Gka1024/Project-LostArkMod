package net.Locke.lostarkmod.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkDirection;

public class ModMessages {
        private static final String PROTOCOL_VERSION = "1";
        public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
                        new ResourceLocation("lostarkmod", "main"),
                        () -> PROTOCOL_VERSION,
                        PROTOCOL_VERSION::equals,
                        PROTOCOL_VERSION::equals);

        public static void register() {
                int id = 0;

                INSTANCE.messageBuilder(RemoveItemPacket.class, id++, NetworkDirection.PLAY_TO_SERVER)
                                .encoder(RemoveItemPacket::encode)
                                .decoder(RemoveItemPacket::decode)
                                .consumerMainThread(RemoveItemPacket::handle)
                                .add();

                INSTANCE.messageBuilder(StoneCarvePacket.class, id++, NetworkDirection.PLAY_TO_SERVER)
                                .encoder(StoneCarvePacket::encode)
                                .decoder(StoneCarvePacket::decode)
                                .consumerMainThread(StoneCarvePacket::handle)
                                .add();

                INSTANCE.messageBuilder(ArmorForgingPacket.class, id++, NetworkDirection.PLAY_TO_SERVER)
                                .encoder(ArmorForgingPacket::encode)
                                .decoder(ArmorForgingPacket::decode)
                                .consumerMainThread(ArmorForgingPacket::handle)
                                .add();

                INSTANCE.messageBuilder(ManaSyncPacket.class, id++, NetworkDirection.PLAY_TO_CLIENT)
                                .encoder(ManaSyncPacket::encode)
                                .decoder(ManaSyncPacket::decode)
                                .consumerMainThread(ManaSyncPacket::handle)
                                .add();

                INSTANCE.messageBuilder(CrossbowArrowSpeedPacket.class, id++, NetworkDirection.PLAY_TO_SERVER)
                                .encoder(CrossbowArrowSpeedPacket::encode)
                                .decoder(CrossbowArrowSpeedPacket::decode)
                                .consumerMainThread(CrossbowArrowSpeedPacket::handle)
                                .add();

                INSTANCE.messageBuilder(SkillUsePacket.class, id++, NetworkDirection.PLAY_TO_SERVER)
                                .encoder(SkillUsePacket::encode)
                                .decoder(SkillUsePacket::decode)
                                .consumerMainThread(SkillUsePacket::handle)
                                .add();
        }
}
