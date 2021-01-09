package net.jmb19905.spellforgers_craft.core.compatability;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CuriosCompat {

    public static ICapabilityProvider initCapability(){
        ICurio iCurio = new ICurio() {/*Could be used later*/};
        return new ICapabilityProvider() {
            private final LazyOptional<ICurio> curioOpt = LazyOptional.of(() -> iCurio);

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap,
                                                     @Nullable Direction side) {

                return CuriosCapability.ITEM.orEmpty(cap, curioOpt);
            }
        };
    }

}
