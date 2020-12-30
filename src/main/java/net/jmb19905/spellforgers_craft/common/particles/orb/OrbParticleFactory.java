package net.jmb19905.spellforgers_craft.common.particles.orb;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;

import javax.annotation.Nullable;

public class OrbParticleFactory implements IParticleFactory<OrbParticleData> {

    private final IAnimatedSprite sprites;

    public OrbParticleFactory(IAnimatedSprite sprite) {
        this.sprites = sprite;
    }

    @Nullable
    @Override
    public Particle makeParticle(OrbParticleData typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        OrbParticle manaParticle = new OrbParticle(worldIn, x, y, z,xSpeed, ySpeed, zSpeed, typeIn.getColor(), typeIn.getSize(), sprites);
        manaParticle.selectSpriteRandomly(sprites);
        return manaParticle;
    }
}
