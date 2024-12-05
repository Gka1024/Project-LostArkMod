package net.Locke.lostarkmod.event;

import net.Locke.lostarkmod.event.effects.defence.DamageAdjustHandler;
import net.Locke.lostarkmod.event.effects.fortune.FortuneBoostHandler;
import net.Locke.lostarkmod.event.effects.melee_damage.AttackDamageHandler;
import net.Locke.lostarkmod.event.effects.mining_speed.BlockBreakSpeedHandler;
import net.Locke.lostarkmod.event.set_effects.salvation.InfiniteArrowHandler;
import net.Locke.lostarkmod.event.set_effects.salvation.ProjectileDamageHandler;
import net.minecraftforge.common.MinecraftForge;

public class ModEventsHandler {
    public static void registerAll() {
        MinecraftForge.EVENT_BUS.register(new ManaRegenHandler());

        MinecraftForge.EVENT_BUS.register(new BlockBreakSpeedHandler());
        MinecraftForge.EVENT_BUS.register(new FortuneBoostHandler());
        MinecraftForge.EVENT_BUS.register(new DamageAdjustHandler());
        MinecraftForge.EVENT_BUS.register(new AttackDamageHandler());

        registerSalvation();
    }

    private static void registerSalvation() {
        MinecraftForge.EVENT_BUS.register(new InfiniteArrowHandler());
        MinecraftForge.EVENT_BUS.register(new ProjectileDamageHandler());
    }
}
