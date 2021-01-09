package net.jmb19905.spellforgers_craft.common.recipes.fluid;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.Objects;

public class FluidRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FluidRecipe> {

    @Override
    public FluidRecipe read(ResourceLocation recipeId, JsonObject json) {
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
        String fluid = JSONUtils.getString(json, "fluid");
        Ingredient input = Ingredient.deserialize(JSONUtils.getJsonObject(json, "input"));
        int delay = JSONUtils.getInt(json, "delay");
        float knockback = JSONUtils.getFloat(json, "knockback");

        return new FluidRecipe(recipeId, input, fluid, output, delay, knockback);
    }

    @Override
    public FluidRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ItemStack output = buffer.readItemStack();
        String fluid = buffer.readString();
        Ingredient input = Ingredient.read(buffer);
        int delay = buffer.readInt();
        float knockback = buffer.readFloat();

        return new FluidRecipe(recipeId, input, fluid, output, delay, knockback);
    }

    @Override
    public void write(PacketBuffer buffer, FluidRecipe recipe) {
        buffer.writeFloat(recipe.getKnockback());
        buffer.writeInt(recipe.getDelay());
        Ingredient input = recipe.getIngredients().get(0);
        input.write(buffer);
        buffer.writeString(Objects.requireNonNull(recipe.getFluid().getRegistryName()).toString());
        buffer.writeItemStack(recipe.getRecipeOutput(), false);
    }

}
