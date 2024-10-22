package net.Locke.lostarkmod.capability;

import net.Locke.lostarkmod.LostArkMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CapabilityRegistration {

    public static Capability<IMana> MANA_CAPABILITY = null;

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IMana.class); // IMana 인터페이스를 Capability로 등록
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) { // Player 엔티티에만 붙이기
            event.addCapability(new ResourceLocation(LostArkMod.MOD_ID, "mana"), new ManaProvider());
        }
    }
}
