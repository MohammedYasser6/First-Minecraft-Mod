package net.mohammed.firstmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.recipe.Ingredient; // Import Ingredient
import net.mohammed.firstmod.block.ModBlocks;
import net.mohammed.firstmod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        // --- SMELTING & BLASTING ---
        List<ItemConvertible> BLUE_SAPPHIRE_SMELTABLES = List.of(ModItems.RAW_BLUE_SAPPHIRE, ModBlocks.BLUE_SAPPHIRE_ORE, ModBlocks.BLUE_SAPPHIRE_DEEPSLATE_ORE);

        offerSmelting(exporter, BLUE_SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.BLUE_SAPPHIRE, 0.25f, 200, "blue_sapphire");
        offerBlasting(exporter, BLUE_SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.BLUE_SAPPHIRE, 0.25f, 100, "blue_sapphire");

        // --- COMPACTING (9 Items <-> 1 Block) ---
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.RAW_BLUE_SAPPHIRE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_BLUE_SAPPHIRE_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.BLUE_SAPPHIRE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_SAPPHIRE_BLOCK);

        // --- CUSTOM TOOLS & FOOD ---

        // Chisel
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CHISEL)
                .pattern(" S ")
                .pattern(" I ")
                .pattern(" T ")
                .input('S', ModItems.BLUE_SAPPHIRE)
                .input('I', Items.IRON_INGOT)
                .input('T', Items.STICK)
                .criterion(hasItem(ModItems.BLUE_SAPPHIRE), conditionsFromItem(ModItems.BLUE_SAPPHIRE))
                .offerTo(exporter);

        // Beef Sandwich
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BEEF_SANDWICH)
                .pattern(" B ")
                .pattern(" M ")
                .pattern(" B ")
                .input('B', Items.BREAD)
                .input('M', Items.COOKED_BEEF)
                .criterion(hasItem(Items.COOKED_BEEF), conditionsFromItem(Items.COOKED_BEEF))
                .offerTo(exporter);

        // --- BUILDING BLOCKS RECIPES ---
        // All these use the BLUE_SAPPHIRE_BLOCK as the base ingredient

        // Button (1 Block -> 1 Button)
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.BLUE_SAPPHIRE_BUTTON)
                .input(ModBlocks.BLUE_SAPPHIRE_BLOCK)
                .criterion(hasItem(ModBlocks.BLUE_SAPPHIRE_BLOCK), conditionsFromItem(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .offerTo(exporter);

        // Pressure Plate (2 Blocks -> 1 Plate)
        offerPressurePlateRecipe(exporter, ModBlocks.BLUE_SAPPHIRE_PRESSURE_PLATE, ModBlocks.BLUE_SAPPHIRE_BLOCK);

        // Fence (4 Blocks + 2 Sticks -> 3 Fences)
        createFenceRecipe(ModBlocks.BLUE_SAPPHIRE_FENCE, Ingredient.ofItems(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .criterion(hasItem(ModBlocks.BLUE_SAPPHIRE_BLOCK), conditionsFromItem(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .offerTo(exporter);

        // Fence Gate (2 Blocks + 4 Sticks -> 1 Gate)
        createFenceGateRecipe(ModBlocks.BLUE_SAPPHIRE_FENCE_GATE, Ingredient.ofItems(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .criterion(hasItem(ModBlocks.BLUE_SAPPHIRE_BLOCK), conditionsFromItem(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .offerTo(exporter);

        // Wall (6 Blocks -> 6 Walls)
        offerWallRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.BLUE_SAPPHIRE_WALL, ModBlocks.BLUE_SAPPHIRE_BLOCK);

        // Door (6 Blocks -> 3 Doors)
        createDoorRecipe(ModBlocks.BLUE_SAPPHIRE_DOOR, Ingredient.ofItems(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .criterion(hasItem(ModBlocks.BLUE_SAPPHIRE_BLOCK), conditionsFromItem(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .offerTo(exporter);

        // Trapdoor (6 Blocks -> 2 Trapdoors)
        createTrapdoorRecipe(ModBlocks.BLUE_SAPPHIRE_TRAPDOOR, Ingredient.ofItems(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .criterion(hasItem(ModBlocks.BLUE_SAPPHIRE_BLOCK), conditionsFromItem(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .offerTo(exporter);

        // Stairs (6 Blocks -> 4 Stairs)
        createStairsRecipe(ModBlocks.BLUE_SAPPHIRE_STAIRS, Ingredient.ofItems(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .criterion(hasItem(ModBlocks.BLUE_SAPPHIRE_BLOCK), conditionsFromItem(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .offerTo(exporter);

        // Slab (3 Blocks -> 6 Slabs)
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_SAPPHIRE_SLAB, ModBlocks.BLUE_SAPPHIRE_BLOCK);
    }
}