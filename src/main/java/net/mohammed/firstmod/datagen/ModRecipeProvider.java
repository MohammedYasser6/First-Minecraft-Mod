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
import net.minecraft.recipe.Ingredient;
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

        // --- CUSTOM ITEMS ---

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

        // --- BUILDING BLOCKS ---

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.BLUE_SAPPHIRE_BUTTON)
                .input(ModBlocks.BLUE_SAPPHIRE_BLOCK)
                .criterion(hasItem(ModBlocks.BLUE_SAPPHIRE_BLOCK), conditionsFromItem(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .offerTo(exporter);

        offerPressurePlateRecipe(exporter, ModBlocks.BLUE_SAPPHIRE_PRESSURE_PLATE, ModBlocks.BLUE_SAPPHIRE_BLOCK);

        createFenceRecipe(ModBlocks.BLUE_SAPPHIRE_FENCE, Ingredient.ofItems(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .criterion(hasItem(ModBlocks.BLUE_SAPPHIRE_BLOCK), conditionsFromItem(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .offerTo(exporter);

        createFenceGateRecipe(ModBlocks.BLUE_SAPPHIRE_FENCE_GATE, Ingredient.ofItems(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .criterion(hasItem(ModBlocks.BLUE_SAPPHIRE_BLOCK), conditionsFromItem(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .offerTo(exporter);

        offerWallRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.BLUE_SAPPHIRE_WALL, ModBlocks.BLUE_SAPPHIRE_BLOCK);

        createDoorRecipe(ModBlocks.BLUE_SAPPHIRE_DOOR, Ingredient.ofItems(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .criterion(hasItem(ModBlocks.BLUE_SAPPHIRE_BLOCK), conditionsFromItem(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .offerTo(exporter);

        createTrapdoorRecipe(ModBlocks.BLUE_SAPPHIRE_TRAPDOOR, Ingredient.ofItems(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .criterion(hasItem(ModBlocks.BLUE_SAPPHIRE_BLOCK), conditionsFromItem(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .offerTo(exporter);

        createStairsRecipe(ModBlocks.BLUE_SAPPHIRE_STAIRS, Ingredient.ofItems(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .criterion(hasItem(ModBlocks.BLUE_SAPPHIRE_BLOCK), conditionsFromItem(ModBlocks.BLUE_SAPPHIRE_BLOCK))
                .offerTo(exporter);

        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_SAPPHIRE_SLAB, ModBlocks.BLUE_SAPPHIRE_BLOCK);

        // Lamp
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.BLUE_SAPPHIRE_LAMP)
                .pattern("SSS")
                .pattern("SRS")
                .pattern("SSS")
                .input('S', ModItems.BLUE_SAPPHIRE)
                .input('R', Items.REDSTONE)
                .criterion(hasItem(ModItems.BLUE_SAPPHIRE), conditionsFromItem(ModItems.BLUE_SAPPHIRE))
                .offerTo(exporter);

        // --- BLUE SAPPHIRE TOOLS ---

        // Pickaxe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BLUE_SAPPHIRE_PICKAXE)
                .pattern("SSS")
                .pattern(" T ")
                .pattern(" T ")
                .input('S', ModItems.BLUE_SAPPHIRE)
                .input('T', Items.STICK)
                .criterion(hasItem(ModItems.BLUE_SAPPHIRE), conditionsFromItem(ModItems.BLUE_SAPPHIRE))
                .offerTo(exporter);

        // Sword
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.BLUE_SAPPHIRE_SWORD)
                .pattern(" S ")
                .pattern(" S ")
                .pattern(" T ")
                .input('S', ModItems.BLUE_SAPPHIRE)
                .input('T', Items.STICK)
                .criterion(hasItem(ModItems.BLUE_SAPPHIRE), conditionsFromItem(ModItems.BLUE_SAPPHIRE))
                .offerTo(exporter);

        // Axe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BLUE_SAPPHIRE_AXE)
                .pattern("SS ")
                .pattern("ST ")
                .pattern(" T ")
                .input('S', ModItems.BLUE_SAPPHIRE)
                .input('T', Items.STICK)
                .criterion(hasItem(ModItems.BLUE_SAPPHIRE), conditionsFromItem(ModItems.BLUE_SAPPHIRE))
                .offerTo(exporter);

        // Shovel
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BLUE_SAPPHIRE_SHOVEL)
                .pattern(" S ")
                .pattern(" T ")
                .pattern(" T ")
                .input('S', ModItems.BLUE_SAPPHIRE)
                .input('T', Items.STICK)
                .criterion(hasItem(ModItems.BLUE_SAPPHIRE), conditionsFromItem(ModItems.BLUE_SAPPHIRE))
                .offerTo(exporter);

        // Hoe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.BLUE_SAPPHIRE_HOE)
                .pattern("SS ")
                .pattern(" T ")
                .pattern(" T ")
                .input('S', ModItems.BLUE_SAPPHIRE)
                .input('T', Items.STICK)
                .criterion(hasItem(ModItems.BLUE_SAPPHIRE), conditionsFromItem(ModItems.BLUE_SAPPHIRE))
                .offerTo(exporter);
    }
}