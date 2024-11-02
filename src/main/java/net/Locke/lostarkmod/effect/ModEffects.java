package net.Locke.lostarkmod.effect;

import net.Locke.lostarkmod.LostArkMod;
import net.Locke.lostarkmod.effect.custom.beneficial.*;
import net.Locke.lostarkmod.effect.custom.harmful.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, LostArkMod.MOD_ID);

    public static final RegistryObject<MobEffect> ADD_HEART = EFFECTS.register("add_heart", () -> new AddHeartEffect());
    public static final RegistryObject<MobEffect> ADD_MANA = EFFECTS.register("add_mana", () -> new AddManaEffect());
    public static final RegistryObject<MobEffect> ATTACK_SPEED = EFFECTS.register("attack_speed", () -> new AttackSpeedEffect());
    public static final RegistryObject<MobEffect> BUFF_ATTACK = EFFECTS.register("buff_attack", () -> new BuffAttackEffect());
    public static final RegistryObject<MobEffect> BUFF_SHIELD = EFFECTS.register("buff_shield", () -> new BuffShieldEffect());
    public static final RegistryObject<MobEffect> DEFENCE = EFFECTS.register("defence", () -> new DefenceEffect());
    public static final RegistryObject<MobEffect> HEALTH_REGEN = EFFECTS.register("health_regeneration", () -> new HealthRegenerationEffect());
    public static final RegistryObject<MobEffect> LESS_COOLDOWN = EFFECTS.register("less_cooldown", () -> new LessCooldownEffect());
    public static final RegistryObject<MobEffect> LUCKY = EFFECTS.register("lucky", () -> new LuckyEffect());
    public static final RegistryObject<MobEffect> MAGIC_DAMAGE = EFFECTS.register("magic_damage", () -> new MagicDamageEffect());
    public static final RegistryObject<MobEffect> MANA_REGEN = EFFECTS.register("mana_regeneration", () -> new ManaRegenerationEffect());
    public static final RegistryObject<MobEffect> MELEE_DAMAGE = EFFECTS.register("melee_damage", () -> new MeleeDamageEffect());
    public static final RegistryObject<MobEffect> MINING_SPEED = EFFECTS.register("mining_speed", () -> new MiningSpeedEffect());
    public static final RegistryObject<MobEffect> RANGED_DAMAGE = EFFECTS.register("ranged_damage", () -> new RangedDamageEffect());
    public static final RegistryObject<MobEffect> MOVE_SPEED = EFFECTS.register("move_speed", () -> new MoveSpeedEffect());

    public static final RegistryObject<MobEffect> DAMAGE_INCOME = EFFECTS.register("damage_income", () -> new DamageIncomeEffect());
    public static final RegistryObject<MobEffect> LESS_DAMAGE = EFFECTS.register("less_damage", () -> new LessDamageEffect());
    public static final RegistryObject<MobEffect> REMOVE_HEART = EFFECTS.register("remove_heart", () -> new RemoveHeartEffect());
    public static final RegistryObject<MobEffect> SLOW_ATKSPEED = EFFECTS.register("slow_atkspeed", () -> new SlowAttackSpeedEffect());
    public static final RegistryObject<MobEffect> SLOW_MOVESPEED = EFFECTS.register("slow_movespeed", () -> new SlowMoveSpeedEffect());

    public static Object[] abilities;

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }
}