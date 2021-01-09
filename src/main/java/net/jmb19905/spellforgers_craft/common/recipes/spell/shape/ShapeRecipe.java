package net.jmb19905.spellforgers_craft.common.recipes.spell.shape;

import net.jmb19905.spellforgers_craft.common.recipes.SpellCraftRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class ShapeRecipe implements IShapeRecipe{

    private final ResourceLocation id;
    private Ingredient input;
    private String shape;

    public ShapeRecipe(ResourceLocation id, Ingredient input, String shape) {
        this.id = id;
        this.input = input;
        this.shape = shape;
    }

    @Override
    public Ingredient getInput() {
        return input;
    }

    @Override
    public String getShape() {
        return shape;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return null;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SpellCraftRecipes.SHAPE_RECIPE_SERIALIZER.get();
    }
}
