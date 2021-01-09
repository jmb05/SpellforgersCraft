package net.jmb19905.spellforgers_craft.common.blocks;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SpellCraftBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SpellforgersCraft.MODID);

    public static final RegistryObject<Block> ARCANE_WORKTABLE = BLOCKS.register("arcane_worktable", ArcaneWorktableBlock::new);

    public static final RegistryObject<Block> MANA_DARK_OAK = BLOCKS.register("mana_dark_oak", () -> new Block(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2).harvestLevel(0).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MANA_DARK_OAK_TRAPDOOR = BLOCKS.register("mana_dark_oak_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid().setAllowsSpawn(SpellCraftBlocks::neverAllowSpawn)));

    //From Blocks
    private static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return false;
    }

}
