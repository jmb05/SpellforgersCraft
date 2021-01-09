package net.jmb19905.spellforgers_craft.common.recipes.spell.shape;

import com.google.gson.JsonObject;
import net.jmb19905.spellforgers_craft.common.recipes.fluid.FluidRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class ShapeRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ShapeRecipe> {

    @Override
    public ShapeRecipe read(ResourceLocation recipeId, JsonObject json) {
        Ingredient input = Ingredient.deserialize(json);
        String shape = JSONUtils.getString(json, "shape");

        return new ShapeRecipe(recipeId, input, shape);
    }

    @Nullable
    @Override
    public ShapeRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        Ingredient input = Ingredient.read(buffer);
        String shape = buffer.readString();

        return new ShapeRecipe(recipeId, input, shape);
    }

    @Override
    public void write(PacketBuffer buffer, ShapeRecipe recipe) {
        buffer.writeString(recipe.getShape());
        Ingredient input = recipe.getInput();
        input.write(buffer);
    }
}
