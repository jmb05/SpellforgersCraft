package net.jmb19905.spellforgers_craft.common.recipes.fluid;

import net.jmb19905.spellforgers_craft.common.recipes.SpellCraftRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidRecipe implements IFluidRecipe{

    private final ResourceLocation id;
    private Ingredient input;
    private String fluid;
    private final ItemStack output;
    private int delay;
    private float knockback;

    public FluidRecipe(ResourceLocation id, Ingredient input, String fluidID, ItemStack output, int delay, float knockback) {
        this.id = id;
        this.output = output;
        this.fluid = fluidID;
        this.input = input;
        this.delay = delay;
        this.knockback = knockback;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World worldIn) {
        return this.input.test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return this.output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SpellCraftRecipes.FLUID_RECIPE_SERIALIZER.get();
    }

    @Override
    public Ingredient getInput() {
        return this.input;
    }

    @Override
    public FlowingFluidBlock getFluid() {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(fluid));
        if(!(block instanceof FlowingFluidBlock)){
            throw new IllegalStateException("Block provided in recipe (" + this + ") is no FlowingFluidBlock");
        }
        return (FlowingFluidBlock) block;
    }

    @Override
    public int getDelay() {
        return delay;
    }

    @Override
    public float getKnockback() {
        return knockback;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.from(null, this.input);
    }

}
