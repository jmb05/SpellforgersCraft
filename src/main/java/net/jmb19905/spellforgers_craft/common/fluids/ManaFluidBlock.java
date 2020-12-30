package net.jmb19905.spellforgers_craft.common.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;

public class ManaFluidBlock extends FlowingFluidBlock {

    public ManaFluidBlock() {
        super(SpellCraftFluids.MANA_FLUID::get, Block.Properties.create(Material.WATER)
                .doesNotBlockMovement().hardnessAndResistance(100.0f).noDrops());
    }

}
