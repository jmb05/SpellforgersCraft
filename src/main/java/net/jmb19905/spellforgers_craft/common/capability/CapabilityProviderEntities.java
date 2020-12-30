package net.jmb19905.spellforgers_craft.common.capability;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.jmb19905.spellforgers_craft.core.NBTTypesSFC;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class CapabilityProviderEntities implements ICapabilitySerializable<INBT> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Mana mana = new Mana();

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(ManaCapability.CAPABILITY_MANA == cap){
            return (LazyOptional<T>) LazyOptional.of(() -> mana);
        }
        return LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put(SpellforgersCraft.MODID + "_mana_cap", Objects.requireNonNull(ManaCapability.CAPABILITY_MANA.writeNBT(mana, null)));
        return nbt;
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        if(nbt.getId() != NBTTypesSFC.COMPOUND_NBT_ID){
            LOGGER.warn("Unexpected NBT type: " + nbt);
            return;
        }
        CompoundNBT compoundNBT = (CompoundNBT) nbt;
        CompoundNBT manaNBT = compoundNBT.getCompound(SpellforgersCraft.MODID + "_mana_cap");

        ManaCapability.CAPABILITY_MANA.readNBT(mana, null, manaNBT);
    }
}
