package net.Locke.lostarkmod.skill.hallucination;

import java.util.List;

import net.Locke.lostarkmod.effect.ModEffects;
import net.Locke.lostarkmod.skill.Skill;
import net.Locke.lostarkmod.skill.SkillUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.phys.Vec3;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;

public class HallucinationSkill1 extends Skill {

    static boolean isSkillAvailable = false;
    static long LAST_SKILL_USED_TIME = 0;

    static double SKILL_RANGE = 5.0; // 스킬 사거리
    static double SKILL_WIDTH = 2.0; // 스킬 폭
    static float SKILL_DAMAGE = 8.0f; // 데미지
    static double SKILL_KNOCKBACK = 1.5; // 밀치기 강도
    static int SKILL_MANA_COST = 0;
    static int SKILL_COOLDOWN = 80;

    static int STUN_DURATION = 30;

    @Override
    public void tick(Player player) {
        checkSkillAvailable();
    }

    public HallucinationSkill1()
    {
        super("SKILLNAME", 0, 0);
    }

    public static void skillUse(Player player) {
        if (!isSkillAvailable) {
            return;
        }

        if (SkillUtil.checkPlayerMana(player, SKILL_MANA_COST)) { // 마나 소모 확인 (예: 10 마나)
            SkillUtil.useMana(player, SKILL_MANA_COST); // 마나 소모

            List<Entity> entitiesInFront = getEntitiesInFront(player, SKILL_RANGE, SKILL_WIDTH);

            applyDamageAndKnockback(player, entitiesInFront, SKILL_DAMAGE, SKILL_KNOCKBACK);
            performShieldAction(player);

            player.level().playSound(null, player.blockPosition(), SoundEvents.PLAYER_ATTACK_STRONG,
                    SoundSource.PLAYERS, 1.0f, 1.0f);

            LAST_SKILL_USED_TIME = getCurrentGameTime();
            isSkillAvailable = false;
        } else {
            player.displayClientMessage(Component.literal("Not enough mana!"), true);
        }
    }

    private static List<Entity> getEntitiesInFront(Player player, double range, double width) {
        Vec3 lookVec = player.getLookAngle();
        Vec3 origin = player.position().add(0, player.getEyeHeight(), 0);
        Vec3 end = origin.add(lookVec.scale(range));

        AABB boundingBox = new AABB(origin, end).inflate(width, width, width);

        return player.level().getEntities(player, boundingBox, e -> e instanceof LivingEntity);
    }

    private static void applyDamageAndKnockback(Player player, List<Entity> entities, float damage,
            double knockbackStrength) {
        Vec3 playerLook = player.getLookAngle();

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity livingEntity) {
                DamageSource dmgSrc = new DamageSource(player.level().registryAccess()
                        .lookupOrThrow(Registries.DAMAGE_TYPE)
                        .getOrThrow(DamageTypes.PLAYER_ATTACK), player);
                livingEntity.hurt(dmgSrc, damage);
                livingEntity.addEffect(new MobEffectInstance(ModEffects.STUN_EFFECT.get(), STUN_DURATION, 0));
            }

            Vec3 knockback = playerLook.scale(knockbackStrength);
            entity.setDeltaMovement(entity.getDeltaMovement().add(knockback));
        }
    }

    private static void performShieldAction(Player player) {
        ItemStack offhandItem = player.getOffhandItem();
        if (offhandItem.getItem() instanceof ShieldItem) {
            player.swing(InteractionHand.OFF_HAND, true);
        }
    }

    private static void checkSkillAvailable() {
        if (LAST_SKILL_USED_TIME + SKILL_COOLDOWN < getCurrentGameTime()) {
            isSkillAvailable = true;
        }
    }

    private static long getCurrentGameTime() {
        return SkillUtil.getCurrentGameTime();
    }

    @Override
    public void useSkill(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'useSkill'");
    }

    @Override
    protected void activateSkill(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateSkill'");
    }
}
