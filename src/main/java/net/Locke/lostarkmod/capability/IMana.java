package net.Locke.lostarkmod.capability;

public interface IMana {
    int getMana();
    int getMaxMana();
    void setMaxMana(int mana);
    void setMana(int mana);
    void addMana(int amount);
    void useMana(int amount);
    void manaRegen();
}
