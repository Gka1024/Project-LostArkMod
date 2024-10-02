package net.Locke.lostarkmod.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ModCapabilities {
    public static Capability<CustomEquipmentCapability> CUSTOM_EQUIPMENT = null;

    public static void register() {
        CUSTOM_EQUIPMENT = CapabilityManager.get(new CapabilityToken<>() {});
    }
}