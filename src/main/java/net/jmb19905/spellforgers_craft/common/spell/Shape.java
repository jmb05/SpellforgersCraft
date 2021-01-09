package net.jmb19905.spellforgers_craft.common.spell;

import net.jmb19905.spellforgers_craft.common.registry.SpellCraftRegistries;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;

import java.util.Objects;

public abstract class Shape extends ForgeRegistryEntry<Shape> implements INBTSerializable<StringNBT> {

    public abstract void cast(LivingEntity livingEntity, Spell spell);

    @Override
    public StringNBT serializeNBT() {
        return StringNBT.valueOf(Objects.requireNonNull(getRegistryName()).toString());
    }

    @Override
    public void deserializeNBT(StringNBT nbt) {
        SpellCraftRegistries.SHAPES.getValue(new ResourceLocation(nbt.getString()));
    }
}
