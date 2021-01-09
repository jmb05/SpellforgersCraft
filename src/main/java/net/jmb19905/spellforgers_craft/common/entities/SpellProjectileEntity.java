package net.jmb19905.spellforgers_craft.common.entities;

import net.jmb19905.spellforgers_craft.common.spell.Spell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnMobPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import java.util.UUID;

public class SpellProjectileEntity extends Entity {
    private UUID shooterUniqueID;
    private int shooterEntityID;
    private Spell spell;

    public SpellProjectileEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public void setSpell(Spell spell){

    }

    public void setShooter(Entity entity){
        if(entity != null){
            shooterUniqueID = entity.getUniqueID();
            shooterEntityID = entity.getEntityId();
        }
    }

    @Override
    protected void registerData() {

    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        if (compound.hasUniqueId("Owner")) {
            this.shooterUniqueID = compound.getUniqueId("Owner");
        }
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        if (this.shooterUniqueID != null) {
            compound.putUniqueId("Owner", this.shooterUniqueID);
        }
    }

    private Entity createInstance(){
        if (this.shooterUniqueID != null && this.world instanceof ServerWorld) {
            return ((ServerWorld) this.world).getEntityByUuid(this.shooterUniqueID);
        }else{
            return this.shooterEntityID != 0 ? this.world.getEntityByID(this.shooterEntityID) : null;
        }
    }

    protected void onImpact(RayTraceResult result) {
        RayTraceResult.Type raytraceresult$type = result.getType();
        if (raytraceresult$type == RayTraceResult.Type.ENTITY) {

        } else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
            this.setDead();
        }
    }

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        Entity entity = createInstance();
        return new SSpawnObjectPacket(this, entity == null ? 0 : entity.getEntityId());
    }


}
