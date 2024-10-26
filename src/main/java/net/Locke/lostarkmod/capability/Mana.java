package net.Locke.lostarkmod.capability;

public class Mana implements IMana {

    private int mana;
    private int regenMana = 5;
    private int maxMana = 100;

    public Mana() {
        this.mana = maxMana;
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
    public void setMaxMana(int mana) {
        this.maxMana = mana;
    }

    @Override
    public void addMana(int amount) {
        this.mana += amount;
    }

    @Override
    public void useMana(int amount) {
        this.mana -= amount;
    }

    @Override
    public int getMaxMana() {
        return this.maxMana;
    }

    @Override
    public void manaRegen() {

        if (this.mana < this.maxMana) {
            this.mana += regenMana;
        }
    }

}
