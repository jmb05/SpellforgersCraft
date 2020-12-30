package net.jmb19905.spellforgers_craft.common.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ManaCapability {

    @CapabilityInject(Mana.class)
    public static Capability<Mana> CAPABILITY_MANA = null;

    public static void register(){
        CapabilityManager.INSTANCE.register(Mana.class, new Mana.ManaNBTStorage(), Mana::createADefaultInstance);
    }

}
