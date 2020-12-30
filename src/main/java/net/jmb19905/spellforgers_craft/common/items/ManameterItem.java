package net.jmb19905.spellforgers_craft.common.items;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.jmb19905.spellforgers_craft.common.capability.Mana;
import net.jmb19905.spellforgers_craft.common.capability.ManaCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ManameterItem extends Item {

    public ManameterItem() {
        super(new Item.Properties().group(SpellforgersCraft.SpellforgersCraftItemGroup.instance).maxStackSize(1).maxDamage(500));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote){
            Mana mana = playerIn.getCapability(ManaCapability.CAPABILITY_MANA).orElse(null);
            if(!playerIn.isSneaking()) {
                playerIn.sendStatusMessage(new StringTextComponent("Your Mana: " + mana.getMana() + "/" + mana.getMaxMana()), false);
            }else{
                mana.setMana(0);
                playerIn.sendStatusMessage(new StringTextComponent("Cleared Mana"), false);
            }
            return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
        }
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
