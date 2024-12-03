package net.Locke.lostarkmod.item.custom;

import net.Locke.lostarkmod.effect.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.HashMap;
import java.util.Map;

public class ModShoulderItem extends Item implements ICurioItem {

    private final String setId;

    // 세트 ID와 버프 매핑
    private static final Map<String, MobEffectInstance> SET_EFFECTS = new HashMap<>();

    static {
        SET_EFFECTS.put("set_salvation", new MobEffectInstance(ModEffects.SET_SALVATION.get(), -1, 0, false, true));
        SET_EFFECTS.put("set_hallucination", new MobEffectInstance(ModEffects.SET_SALVATION.get(), -1, 0, false, false));
        SET_EFFECTS.put("set_entropy", new MobEffectInstance(ModEffects.SET_SALVATION.get(), -1, 0, false, false));
        SET_EFFECTS.put("set_nightmare", new MobEffectInstance(ModEffects.SET_SALVATION.get(), -1, 0, false, false));
        SET_EFFECTS.put("set_yearning", new MobEffectInstance(ModEffects.SET_SALVATION.get(), -1, 0, false, false));
        SET_EFFECTS.put("set6", new MobEffectInstance(ModEffects.SET_SALVATION.get(), -1, 0, false, false));
    }

    public ModShoulderItem(Properties properties, String setId) {
        super(properties);
        this.setId = setId;
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag unused) {

        if (!stack.getOrCreateTag().contains("set_id")) {
            stack.getOrCreateTag().putString("set_id", setId);
        }

        return CuriosApi.createCurioProvider(new ICurio() {

            private boolean isBuffApplied = false;

            @Override
            public ItemStack getStack() {
                return stack;
            }

            @Override
            public void curioTick(SlotContext slotContext) {

                if (slotContext.entity() instanceof Player player) {
                    String setId = getSetId(stack); // 아이템의 세트 ID 가져오기
                    if (isWearingFullSet(player, setId)) {
                        if (!isBuffApplied) {
                            applyBuff(player, setId);
                            isBuffApplied = true;
                        }
                    } else {
                        isBuffApplied = false;
                        removeAllBuffs(player);
                    }
                }
            }

            @Override
            public void onUnequip(SlotContext slotContext, ItemStack newStack) {
                if (slotContext.entity() instanceof Player player) {
                    isBuffApplied = false;
                    removeAllBuffs(player);
                }
            }

        });
    }

    private String getSetId(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return tag != null ? tag.getString("set_id") : null;
    }

    private boolean isWearingFullSet(Player player, String setId) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        if (boots == null || leggings == null || breastplate == null || helmet == null) {
            return false;
        }

        String bootsId = boots.getOrCreateTag().getString("set_id");
        String leggingsId = leggings.getOrCreateTag().getString("set_id");
        String breastplateId = breastplate.getOrCreateTag().getString("set_id");
        String helmetId = helmet.getOrCreateTag().getString("set_id");

        boolean isAllArmorSame = isSetStringSame(setId, bootsId, leggingsId, breastplateId, helmetId);

        return isAllArmorSame;
    }

    private boolean isSetStringSame(String s1, String s2, String s3, String s4, String s5) {
        return s1.equals(s2) && s1.equals(s3) && s1.equals(s4) && s1.equals(s5);
    }

    private void applyBuff(Player player, String setId) {
        MobEffectInstance effect = SET_EFFECTS.get(setId);
        if (effect != null) {
            player.addEffect(new MobEffectInstance(effect));
        }
    }

    private void removeAllBuffs(Player player) {
        SET_EFFECTS.values().forEach(effect -> player.removeEffect(effect.getEffect()));
    }
}
