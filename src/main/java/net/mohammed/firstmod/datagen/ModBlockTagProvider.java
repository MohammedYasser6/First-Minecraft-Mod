package net.mohammed.firstmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.mohammed.firstmod.block.ModBlocks;
import net.mohammed.firstmod.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        // 1. TOOL REQUIREMENT: Pickaxe Mineable
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.BLUE_SAPPHIRE_BLOCK)
                .add(ModBlocks.RAW_BLUE_SAPPHIRE_BLOCK)
                .add(ModBlocks.BLUE_SAPPHIRE_ORE)
                .add(ModBlocks.BLUE_SAPPHIRE_DEEPSLATE_ORE)
                // New Building Blocks
                .add(ModBlocks.BLUE_SAPPHIRE_STAIRS)
                .add(ModBlocks.BLUE_SAPPHIRE_SLAB)
                .add(ModBlocks.BLUE_SAPPHIRE_WALL)
                .add(ModBlocks.BLUE_SAPPHIRE_FENCE)
                .add(ModBlocks.BLUE_SAPPHIRE_FENCE_GATE)
                .add(ModBlocks.BLUE_SAPPHIRE_DOOR)
                .add(ModBlocks.BLUE_SAPPHIRE_TRAPDOOR)
                .add(ModBlocks.BLUE_SAPPHIRE_PRESSURE_PLATE)
                .add(ModBlocks.BLUE_SAPPHIRE_BUTTON)
                // Lamp
                .add(ModBlocks.BLUE_SAPPHIRE_LAMP);

        // 2. TIER REQUIREMENT: Needs Iron Tool
        // (Ensures the lamp drops nothing if mined by hand/wood)
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BLUE_SAPPHIRE_DEEPSLATE_ORE)
                .add(ModBlocks.BLUE_SAPPHIRE_ORE)
                .add(ModBlocks.BLUE_SAPPHIRE_BLOCK)
                .add(ModBlocks.RAW_BLUE_SAPPHIRE_BLOCK)
                .add(ModBlocks.BLUE_SAPPHIRE_STAIRS)
                .add(ModBlocks.BLUE_SAPPHIRE_SLAB)
                .add(ModBlocks.BLUE_SAPPHIRE_WALL)
                .add(ModBlocks.BLUE_SAPPHIRE_FENCE)
                .add(ModBlocks.BLUE_SAPPHIRE_FENCE_GATE)
                .add(ModBlocks.BLUE_SAPPHIRE_DOOR)
                .add(ModBlocks.BLUE_SAPPHIRE_TRAPDOOR)
                .add(ModBlocks.BLUE_SAPPHIRE_PRESSURE_PLATE)
                .add(ModBlocks.BLUE_SAPPHIRE_BUTTON)
                // Lamp
                .add(ModBlocks.BLUE_SAPPHIRE_LAMP);

        // 3. SHAPE TAGS (Required for connections to work!)

        // Fences
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.BLUE_SAPPHIRE_FENCE);

        // Fence Gates
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.BLUE_SAPPHIRE_FENCE_GATE);

        // Walls
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.BLUE_SAPPHIRE_WALL);

        // Doors (Helps AI pathfinding)
        getOrCreateTagBuilder(BlockTags.DOORS)
                .add(ModBlocks.BLUE_SAPPHIRE_DOOR);

        // Trapdoors (Helps AI pathfinding)
        getOrCreateTagBuilder(BlockTags.TRAPDOORS)
                .add(ModBlocks.BLUE_SAPPHIRE_TRAPDOOR);
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_BLUE_SAPPHIRE_TOOL)
                .add(ModBlocks.BLUE_SAPPHIRE_BLOCK);
    }
}