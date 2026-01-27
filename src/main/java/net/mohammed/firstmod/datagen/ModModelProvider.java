package net.mohammed.firstmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.mohammed.firstmod.block.ModBlocks;
import net.mohammed.firstmod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // --- SIMPLE BLOCKS ---
        // These just use 1 texture on all sides
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLUE_SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLUE_SAPPHIRE_DEEPSLATE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_BLUE_SAPPHIRE_BLOCK);

        // --- THE TEXTURE POOL ---
        // 1. Create a pool using the BLUE_SAPPHIRE_BLOCK texture
        BlockStateModelGenerator.BlockTexturePool sapphirePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BLUE_SAPPHIRE_BLOCK);

        // 2. Generate all the complex shapes using that pool
        sapphirePool.stairs(ModBlocks.BLUE_SAPPHIRE_STAIRS);
        sapphirePool.slab(ModBlocks.BLUE_SAPPHIRE_SLAB);
        sapphirePool.button(ModBlocks.BLUE_SAPPHIRE_BUTTON);
        sapphirePool.pressurePlate(ModBlocks.BLUE_SAPPHIRE_PRESSURE_PLATE);
        sapphirePool.fence(ModBlocks.BLUE_SAPPHIRE_FENCE);
        sapphirePool.fenceGate(ModBlocks.BLUE_SAPPHIRE_FENCE_GATE);
        sapphirePool.wall(ModBlocks.BLUE_SAPPHIRE_WALL);

        // --- DOORS & TRAPDOORS ---
        // These are special because they usually have their own specific textures
        // (they don't just look like the solid block)
        blockStateModelGenerator.registerDoor(ModBlocks.BLUE_SAPPHIRE_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.BLUE_SAPPHIRE_TRAPDOOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Only register "Flat" items here.
        // Block items (like holding a Fence in hand) are handled automatically by the code above.

        itemModelGenerator.register(ModItems.BLUE_SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_BLUE_SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEEF_SANDWICH, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);
    }
}