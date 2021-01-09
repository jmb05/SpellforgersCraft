package net.jmb19905.spellforgers_craft.common.registry;

import net.jmb19905.spellforgers_craft.common.spell.Shape;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class SpellCraftRegistries {

    public static final IForgeRegistry<Shape> SHAPES = RegistryManager.ACTIVE.getRegistry(Shape.class);

    public static class Keys {
        public static final RegistryKey<Registry<Shape>> SHAPES = RegistryKey.getOrCreateRootKey(new ResourceLocation("shape"));
    }
}
