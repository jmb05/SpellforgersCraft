package net.jmb19905.spellforgers_craft.common.particles.orb;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.jmb19905.spellforgers_craft.common.particles.SpellCraftParticles;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Locale;

public class OrbParticleData implements IParticleData {

    private Color color;
    private double size;

    public OrbParticleData(Color color, double size){
        this.color = color;
        this.size = constrainSizeToValidRange(size);
    }

    public Color getColor() {
        return color;
    }

    public double getSize() {
        return size;
    }

    @Override
    public ParticleType<?> getType() {
        return SpellCraftParticles.ORB_PARTICLE_TYPE.get();
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeInt(color.getRed());
        buffer.writeInt(color.getGreen());
        buffer.writeInt(color.getBlue());
        buffer.writeDouble(size);
    }

    @Override
    public String getParameters() {
        return String.format(Locale.ROOT, "%s %.2f %i %i %i",
                this.getType().getRegistryName(), size, color.getRed(), color.getGreen(), color.getBlue());
    }

    private static double constrainSizeToValidRange(double size) {
        final double MIN_SIZE = 0.05;
        final double MAX_SIZE = 1.0;
        return MathHelper.clamp(size, MIN_SIZE, MAX_SIZE);
    }

    public static final Codec<OrbParticleData> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("color").forGetter(d -> d.color.getRGB()),
                    Codec.DOUBLE.fieldOf("size").forGetter(d -> d.size)
            ).apply(instance, OrbParticleData::new)
    );

    private OrbParticleData(int colorRGB, double size) {
        this.color = new Color(colorRGB);
        this.size = constrainSizeToValidRange(size);
    }

    public static final IDeserializer<OrbParticleData> DESERIALIZER = new IDeserializer<OrbParticleData>() {

        @Nonnull
        @Override
        public OrbParticleData deserialize(@Nonnull ParticleType<OrbParticleData> type, @Nonnull StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            double size = constrainSizeToValidRange(reader.readDouble());

            final int MIN_COLOUR = 0;
            final int MAX_COLOUR = 255;
            reader.expect(' ');
            int red = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
            reader.expect(' ');
            int green = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
            reader.expect(' ');
            int blue = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
            Color color = new Color(red, green, blue);

            return new OrbParticleData(color, size);
        }

        @Override
        public OrbParticleData read(@Nonnull ParticleType<OrbParticleData> type, PacketBuffer buf) {

            final int MIN_COLOUR = 0;
            final int MAX_COLOUR = 255;
            int red = MathHelper.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR);
            int green = MathHelper.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR);
            int blue = MathHelper.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR);
            Color color = new Color(red, green, blue);

            double size = constrainSizeToValidRange(buf.readDouble());

            return new OrbParticleData(color, size);
        }
    };

    public static final OrbParticleData MANA = new OrbParticleData(new Color(200, 255, 255), 0.1);

}
