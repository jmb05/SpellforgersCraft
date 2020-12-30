package net.jmb19905.spellforgers_craft.core;

import net.jmb19905.spellforgers_craft.common.capability.Mana;
import net.jmb19905.spellforgers_craft.common.capability.ManaCapability;
import net.jmb19905.spellforgers_craft.common.network.ManaMessage;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class Util {

    //attacker code is from Item#rayCast
    public static BlockRayTraceResult rayTraceBlocks(World worldIn, LivingEntity entity, RayTraceContext.FluidMode fluidMode, double reach) {
        float f = entity.rotationPitch;
        float f1 = entity.rotationYaw;
        Vector3d vec3d = entity.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * ((float)Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vector3d vec3d1 = vec3d.add((double)f6 * reach, (double)f5 * reach, (double)f7 * reach);
        return worldIn.rayTraceBlocks(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.OUTLINE, fluidMode, entity));
    }
}
