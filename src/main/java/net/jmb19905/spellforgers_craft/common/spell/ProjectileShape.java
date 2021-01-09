package net.jmb19905.spellforgers_craft.common.spell;

import net.jmb19905.spellforgers_craft.core.Util;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;

public class ProjectileShape extends Shape{

    @Override
    public void cast(LivingEntity livingEntity, Spell spell) {

        RayTraceResult rayTrace = Util.rayTraceBlocks(livingEntity.world, livingEntity, RayTraceContext.FluidMode.NONE, 10);

    }

    public static class LengthUpgrade extends ShapeUpgrade{

    }

}
