package net.Locke.lostarkmod.capability;

public interface IMana {
    int getMana();
    void setMana(int mana);
    void addMana(int amount);
    void useMana(int amount);
}
