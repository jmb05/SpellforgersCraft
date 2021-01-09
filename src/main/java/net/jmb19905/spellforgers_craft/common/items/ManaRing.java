package net.jmb19905.spellforgers_craft.common.items;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.jmb19905.spellforgers_craft.core.compatability.CuriosCompat;
import net.jmb19905.spellforgers_craft.core.compatability.ExternalMods;
import net.jmb19905.spellforgers_craft.core.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.IItemHandlerModifiable;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.Nullable;

public class ManaRing extends Item{

    private final int manaReplenishTime;

    private int lastRefill = 0;

    public ManaRing(int manaReplenishTime) {
        super(new Item.Properties().rarity(Rarity.RARE).maxStackSize(1).group(SpellforgersCraft.SpellforgersCraftItemGroup.instance));
        this.manaReplenishTime = manaReplenishTime;
    }

    public int getManaReplenishTime() {
        return manaReplenishTime;
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(!worldIn.isRemote){
            if(entityIn instanceof PlayerEntity){
                if(isInValidSlot((PlayerEntity) entityIn)){
                    lastRefill++;
                    if(lastRefill > manaReplenishTime){
                        lastRefill = 0;
                        Util.addPlayerMana((PlayerEntity) entityIn, 1);
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        if(ExternalMods.CURIOS.isLoaded()){
            return CuriosCompat.initCapability();
        }
        return super.initCapabilities(stack, nbt);
    }

    public static boolean isInValidSlot(PlayerEntity player){
        boolean hasRing = false;
        if(player.getHeldItemOffhand().getItem() instanceof ManaRing){
            hasRing = true;
        }else if(ExternalMods.CURIOS.isLoaded()){
            if(CuriosApi.getCuriosHelper().getEquippedCurios(player).isPresent()) {
                IItemHandlerModifiable itemHandler = CuriosApi.getCuriosHelper().getEquippedCurios(player).orElseGet(null);
                for (int i = 0; i < itemHandler.getSlots(); i++) {
                    if (itemHandler.getStackInSlot(i).getItem() instanceof ManaRing) {
                        hasRing = true;
                        break;
                    }
                }
            }
        }
        return hasRing;
    }

}
