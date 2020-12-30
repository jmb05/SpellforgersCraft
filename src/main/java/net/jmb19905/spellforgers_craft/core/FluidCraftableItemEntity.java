package net.jmb19905.spellforgers_craft.core;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;

public class FluidCraftableItemEntity extends ItemEntity {

    private final FlowingFluidBlock craftingFluid;
    private Item result;
    private float knockback;
    private int delay = 10;
    private int currentDelay = 0;

    public FluidCraftableItemEntity(World worldIn, double x, double y, double z, ItemStack input, FlowingFluidBlock craftingFluid, Item result, float knockback) {
        super(worldIn, x, y, z, input);
        this.craftingFluid = craftingFluid;
        this.result = result;
        this.knockback = knockback;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void resetDelay(){
        currentDelay = 0;
    }

    @Override
    public void tick() {
        if(!getEntityWorld().isRemote){
            if(getEntityWorld().getBlockState(getPosition()).getBlock() == craftingFluid){
                if(currentDelay >= delay) {
                    setDead();
                    ItemEntity newItem = new ItemEntity(getEntityWorld(), getPosX(), getPosY(), getPosZ(), new ItemStack(result, getItem().getCount()));
                    getEntityWorld().addEntity(newItem);
                    newItem.setMotion(0.1f * this.rand.nextFloat(), 0.4f, 0.1f * this.rand.nextFloat());
                    AxisAlignedBB box = new AxisAlignedBB(newItem.getPosX() - 4, newItem.getPosY() - 3, newItem.getPosZ() - 4, newItem.getPosX() + 4, newItem.getPosY() + 4, newItem.getPosZ() + 4);
                    List<LivingEntity> entities = getEntityWorld().getEntitiesWithinAABB(LivingEntity.class, box, EntityPredicates.IS_ALIVE);
                    for(LivingEntity entity : entities){
                        //From LivingEntity#attackEntityFrom
                        double d1 = newItem.getPosX() - entity.getPosX();
                        double d0;
                        for(d0 = newItem.getPosZ() - entity.getPosZ(); d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D) {
                            d1 = (Math.random() - Math.random()) * 0.01D;
                        }
                        //From LivingEntity#applyKnockback (I have altered it)
                        float strength = (float)(knockback * (1.0D - entity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE)));
                        entity.isAirBorne = true;
                        Vector3d vector3d = entity.getMotion();
                        Vector3d vector3d1 = (new Vector3d(d1, 0.0D, d0)).normalize().scale(strength);
                        entity.setMotion(vector3d.x / 2.0D - vector3d1.x, entity.isOnGround() ? Math.min(0.4D, vector3d.y / 2.0D + (double)strength) : vector3d.y, vector3d.z / 2.0D - vector3d1.z);
                        entity.velocityChanged = true;
                    }
                }else{
                    currentDelay++;
                }
            }else if(currentDelay > 0){
                resetDelay();
            }
        }
        super.tick();
    }
}
