package net.jmb19905.spellforgers_craft.common.capability;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class Mana {

    private int mana;
    private int maxMana;

    public Mana(){
        this(0, 100);
    }

    public Mana(int mana, int maxMana){
        this.mana = mana;
        this.maxMana = maxMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public static class ManaNBTStorage implements Capability.IStorage<Mana>{

        @Nullable
        @Override
        public INBT writeNBT(Capability<Mana> capability, Mana instance, Direction side) {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt(SpellforgersCraft.MODID + "_maxMana", instance.maxMana);
            nbt.putInt(SpellforgersCraft.MODID + "_mana", instance.mana);
            return nbt;
        }

        @Override
        public void readNBT(Capability<Mana> capability, Mana instance, Direction side, INBT nbt) {
            int mana = 0;
            int maxMana = 100;
            if(nbt.getType() == CompoundNBT.TYPE){
                mana = ((CompoundNBT) nbt).getInt(SpellforgersCraft.MODID + "_mana");
                maxMana = ((CompoundNBT) nbt).getInt(SpellforgersCraft.MODID + "_maxMana");
            }
            instance.setMana(mana);
            instance.setMaxMana(maxMana);
        }
    }

    public static Mana createADefaultInstance(){
        return new Mana();
    }

}
