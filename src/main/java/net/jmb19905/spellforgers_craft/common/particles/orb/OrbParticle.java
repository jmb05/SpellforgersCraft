package net.jmb19905.spellforgers_craft.common.particles.orb;

import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

public class OrbParticle extends SpriteTexturedParticle {

    private final IAnimatedSprite sprites;

    protected OrbParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ, Color color, double size, IAnimatedSprite sprites) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.sprites = sprites;
        setColor(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f);
        setSize((float) size, (float) size);

        final float PARTICLE_SCALE_FOR_ONE_METRE = 0.5F;
        particleScale = PARTICLE_SCALE_FOR_ONE_METRE * (float)size;

        this.particleAlpha = 1.0F;

        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        this.maxAge = (int) (Math.random() * 10.0d) + 40;
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            float f = (float) this.age / (float) this.maxAge;
            float f1 = -f + f * f * 2.0F;
            float f2 = 1.0F - f1;
            this.posX = this.posX + this.motionX * (double) f2;
            this.posY = this.posY + this.motionY * (double) f2 + (double) (0.2F - f);
            this.posZ = this.posZ + this.motionZ * (double) f2;
        }
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    protected int getBrightnessForRender(float partialTick) {
        final int BLOCK_LIGHT = 15;  // maximum brightness
        final int SKY_LIGHT = 15;    // maximum brightness
        return LightTexture.packLight(BLOCK_LIGHT, SKY_LIGHT);
    }

}
