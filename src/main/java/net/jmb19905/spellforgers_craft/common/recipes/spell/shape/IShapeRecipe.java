package net.jmb19905.spellforgers_craft.common.recipes.spell.shape;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;

public interface IShapeRecipe extends IRecipe<RecipeWrapper> {

    ResourceLocation RECIPE_TYPE_ID = new ResourceLocation(SpellforgersCraft.MODID, "shape");

    @Nonnull
    @Override
    default IRecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getOptional(RECIPE_TYPE_ID).get();
    }

    @Override
    default boolean canFit(int width, int height) {
        return false;
    }

    Ingredient getInput();

    String getShape();

}
