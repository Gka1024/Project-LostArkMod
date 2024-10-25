package net.Locke.lostarkmod.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final Capability<IMana> MANA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    private final Mana mana = new Mana();
    private final LazyOptional<IMana> lazyOptional = LazyOptional.of(() -> mana);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityRegistration.MANA_CAPABILITY) {
            return lazyOptional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("mana", mana.getMana());
        tag.putInt("maxMana", mana.getMaxMana());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        mana.setMana(nbt.getInt("mana"));
        mana.setMaxMana(nbt.getInt("maxMana"));
    }
}
