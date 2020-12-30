package net.jmb19905.spellforgers_craft.common.particles;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.jmb19905.spellforgers_craft.common.particles.orb.OrbParticleData;
import net.jmb19905.spellforgers_craft.common.particles.orb.OrbParticleFactory;
import net.jmb19905.spellforgers_craft.common.particles.orb.OrbParticleType;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = SpellforgersCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SpellCraftParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SpellforgersCraft.MODID);

    public static final RegistryObject<ParticleType<OrbParticleData>> ORB_PARTICLE_TYPE = PARTICLE_TYPES.register("orb_particle", OrbParticleType::new);

    @SubscribeEvent
    public static void onParticleFactoryRegistration(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(ORB_PARTICLE_TYPE.get(), OrbParticleFactory::new);
    }

}
