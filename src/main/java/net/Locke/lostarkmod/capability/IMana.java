package net.Locke.lostarkmod.capability;

public interface IMana {
    int getMana();
    int getMaxMana();
    void setMaxMana(int mana);
    void setMana(int mana);
    void addMana(int amount);
    boolean checkMana(int mana);
    boolean useMana(int amount);
    void manaRegen();
    void manaRegen(int amount);
}
