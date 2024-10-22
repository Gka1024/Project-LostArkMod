package net.Locke.lostarkmod.capability;

public class Mana implements IMana{

    private int mana;

    public Mana()
    {
        this.mana = 100;
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
    public void addMana(int amount) {
        this.mana += amount;
    }

    @Override
    public void useMana(int amount) {
        this.mana -= amount;
    }

}
