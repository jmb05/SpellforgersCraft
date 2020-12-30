package net.jmb19905.spellforgers_craft.common.fluids;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.jmb19905.spellforgers_craft.common.blocks.SpellCraftBlocks;
import net.jmb19905.spellforgers_craft.common.items.SpellCraftItems;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SpellCraftFluids {

    public static final ResourceLocation MANA_STILL_RESOURCE = new ResourceLocation(SpellforgersCraft.MODID, "blocks/mana_still");
    public static final ResourceLocation MANA_FLOWING_RESOURCE = new ResourceLocation(SpellforgersCraft.MODID, "blocks/mana_flowing");
    public static final ResourceLocation MANA_OVERLAY_RESOURCE = new ResourceLocation(SpellforgersCraft.MODID, "blocks/mana_overlay");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, SpellforgersCraft.MODID);

    public static final RegistryObject<FlowingFluid> MANA_FLUID = FLUIDS.register("mana_fluid", () -> new ForgeFlowingFluid.Source(SpellCraftFluids.MANA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> MANA_FLOWING = FLUIDS.register("mana_flowing", () -> new ForgeFlowingFluid.Flowing(SpellCraftFluids.MANA_PROPERTIES));

    public static final ForgeFlowingFluid.Properties MANA_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> MANA_FLUID.get(), () -> MANA_FLOWING.get(),
            FluidAttributes.builder(MANA_STILL_RESOURCE, MANA_FLOWING_RESOURCE).density(5).luminosity(10).rarity(Rarity.RARE)
                    .sound(SoundEvents.BLOCK_WATER_AMBIENT).sound(SoundEvents.ITEM_BUCKET_FILL, SoundEvents.ITEM_BUCKET_EMPTY).overlay(MANA_OVERLAY_RESOURCE).color(0x00ffff))
            .block(() -> SpellCraftFluids.MANA_FLUID_BLOCK.get()).bucket(SpellCraftItems.MANA_BUCKET);

    public static final RegistryObject<FlowingFluidBlock> MANA_FLUID_BLOCK = SpellCraftBlocks.BLOCKS.register("mana", () -> new ManaFluidBlock());

}
