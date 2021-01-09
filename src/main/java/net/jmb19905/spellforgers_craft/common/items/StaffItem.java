package net.jmb19905.spellforgers_craft.common.items;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.jmb19905.spellforgers_craft.common.spell.Spell;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.UseAction;
import net.minecraft.world.World;

import java.util.List;

public class StaffItem extends Item{

    public StaffItem() {
        super(new Item.Properties().group(SpellforgersCraft.SpellforgersCraftItemGroup.instance)
        .maxDamage(500).maxStackSize(1).rarity(Rarity.RARE).setNoRepair());
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 7200;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if(timeLeft >= 60){

        }
    }

}
