package net.Locke.lostarkmod.effect;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.effect.custom.AddHeartEffect;
import net.Locke.lostarkmod.effect.custom.MeleeDamageEffect;
import net.Locke.lostarkmod.effect.custom.SlowAttackSpeedEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, LostArkMod.MOD_ID);

    // 이펙트 등록
    public static final RegistryObject<MobEffect> MELEE_DAMAGE = EFFECTS.register("melee_damage", () -> new MeleeDamageEffect());
    public static final RegistryObject<MobEffect> ADD_HEART = EFFECTS.register("add_heart", () -> new AddHeartEffect());
    public static final RegistryObject<MobEffect> SLOW_ATKSPEED = EFFECTS.register("slow_atkspeed", () -> new SlowAttackSpeedEffect());

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}