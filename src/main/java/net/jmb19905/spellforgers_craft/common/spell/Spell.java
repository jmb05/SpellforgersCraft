package net.jmb19905.spellforgers_craft.common.spell;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.List;

public abstract class Spell implements INBTSerializable<CompoundNBT> {

    private final Shape shape;
    private final List<ShapeUpgrade> shapeUpgrades;
    private final HashMap<SpellEffect, List<SpellEffectUpgrade>> effects;

    public Spell(Shape shape, List<ShapeUpgrade> shapeUpgrades, HashMap<SpellEffect, List<SpellEffectUpgrade>> effects) {
        this.shape = shape;
        this.shapeUpgrades = shapeUpgrades;
        this.effects = effects;
    }

    public Shape getShape() {
        return shape;
    }

    public List<ShapeUpgrade> getShapeUpgrades() {
        return shapeUpgrades;
    }

    public HashMap<SpellEffect, List<SpellEffectUpgrade>> getEffects() {
        return effects;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("shape", shape.serializeNBT());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }
}
