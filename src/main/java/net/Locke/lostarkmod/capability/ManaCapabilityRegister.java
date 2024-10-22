package net.Locke.lostarkmod.capability;

import net.Locke.lostarkmod.LostArkMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LostArkMod.MOD_ID)
public class ManaCapabilityRegister {

    public static void register(IEventBus modEventBus)
    {
        modEventBus.register(IMana.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof Player)
        {
            event.addCapability(new ResourceLocation(LostArkMod.MOD_ID, "mana"), new ManaProvider());
        }
    }
}
