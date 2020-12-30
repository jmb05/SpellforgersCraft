package net.jmb19905.spellforgers_craft.common.items;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.jmb19905.spellforgers_craft.common.capability.Mana;
import net.jmb19905.spellforgers_craft.common.capability.ManaCapability;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ManaBottle extends Item {

    private static final Food food = new Food.Builder().hunger(1).saturation(1).setAlwaysEdible().build();

    public ManaBottle() {
        super(new Item.Properties().maxStackSize(1).group(SpellforgersCraft.SpellforgersCraftItemGroup.instance)
                .containerItem(Items.GLASS_BOTTLE)
                .food(food));
    }

    @Override
    public boolean isFood() {
        return true;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if(!worldIn.isRemote){
            if(entityLiving instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) entityLiving;
                player.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE, 1));
                player.attackEntityFrom(DamageSource.MAGIC, 1);
                setPlayerMana(player, 10);
            }
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    private void setPlayerMana(PlayerEntity playerEntity, int manaLvl){
        Mana mana = playerEntity.getCapability(ManaCapability.CAPABILITY_MANA).orElse(null);
        int newManaLvl = mana.getMana() + manaLvl;
        if(newManaLvl <= mana.getMaxMana()) {
            mana.setMana(newManaLvl);
        }else{
            mana.setMaxMana(mana.getMaxMana());
        }
    }

}
