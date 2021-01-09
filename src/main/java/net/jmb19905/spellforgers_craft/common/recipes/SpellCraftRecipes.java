package net.jmb19905.spellforgers_craft.common.recipes;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.jmb19905.spellforgers_craft.common.recipes.fluid.FluidRecipe;
import net.jmb19905.spellforgers_craft.common.recipes.fluid.FluidRecipeSerializer;
import net.jmb19905.spellforgers_craft.common.recipes.fluid.IFluidRecipe;
import net.jmb19905.spellforgers_craft.common.recipes.spell.shape.IShapeRecipe;
import net.jmb19905.spellforgers_craft.common.recipes.spell.shape.ShapeRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class SpellCraftRecipes {

    public static final IRecipeType<IFluidRecipe> FLUID_TYPE = registerType(IFluidRecipe.RECIPE_TYPE_ID);
    public static final IRecipeType<IShapeRecipe> SHAPE_TYPE = registerType(IShapeRecipe.RECIPE_TYPE_ID);

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(
            ForgeRegistries.RECIPE_SERIALIZERS, SpellforgersCraft.MODID);

    public static final RegistryObject<IRecipeSerializer<?>> FLUID_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("fluid", FluidRecipeSerializer::new);
    public static final RegistryObject<IRecipeSerializer<?>> SHAPE_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("shape", ShapeRecipeSerializer::new);

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Objects.requireNonNull(Registry.RECIPE_TYPE.getKey(this)).toString();
        }
    }

    private static <T extends IRecipeType<?>> T registerType(ResourceLocation recipeTypeId) {
        return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
    }

}
