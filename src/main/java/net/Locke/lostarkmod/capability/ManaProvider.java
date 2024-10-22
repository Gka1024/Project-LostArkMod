package net.Locke.lostarkmod.capability;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class ManaProvider  implements ICapabilityProvider{
    public static final Capability<IMana> MANA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    private final IMana mana = new ManaCapability(100);
    private final LazyOptional<IMana> optionalMana = LazyOptional.of(() -> mana);

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCapability'");
    }

    public void loadNBTData(CompoundTag nbt)
    {
        mana.setMana(nbt.getInt("mana"));
    }

    public void saveNBTData(CompoundTag nbt)
    {
        nbt.putInt("mana", mana.getMana());
    }

}
