package net.jmb19905.spellforgers_craft.common.particles.orb;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;

public class OrbParticleType extends ParticleType<OrbParticleData> {

    private static final boolean ALWAYS_SHOW_REGARDLESS_OF_DISTANCE_FROM_PLAYER = false;

    public OrbParticleType() {
        super(ALWAYS_SHOW_REGARDLESS_OF_DISTANCE_FROM_PLAYER, OrbParticleData.DESERIALIZER);
    }

    @Override
    public Codec<OrbParticleData> func_230522_e_() {
        return OrbParticleData.CODEC;
    }
}
