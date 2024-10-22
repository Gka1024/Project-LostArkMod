package net.Locke.lostarkmod.capability;

public class ManaCapability implements IMana {
    private int mana;
    private int maxMana;

    public ManaCapability(int mana)
    {
        this.mana = mana;
        this.maxMana = 100;
    }

    @Override
    public int getMana() {
        return this.mana;
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public int getMaxMana() {
        return this.maxMana;
    }

    @Override
    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

}
