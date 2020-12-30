package net.jmb19905.spellforgers_craft.common.blocks;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SpellCraftBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SpellforgersCraft.MODID);

    public static final RegistryObject<Block> ARCANE_WORKTABLE = BLOCKS.register("arcane_worktable", ArcaneWorktableBlock::new);

    public static final RegistryObject<Block> MANA_DARK_OAK = BLOCKS.register("mana_dark_oak", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2).harvestLevel(0).sound(SoundType.WOOD)));

}
