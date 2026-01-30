package net.mohammed.firstmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;
import net.mohammed.firstmod.block.ModBlocks;
import net.mohammed.firstmod.block.custom.BlueSapphireLampBlock;
import net.mohammed.firstmod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // --- SIMPLE BLOCKS ---
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLUE_SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLUE_SAPPHIRE_DEEPSLATE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_BLUE_SAPPHIRE_BLOCK);

        // --- THE TEXTURE POOL ---
        BlockStateModelGenerator.BlockTexturePool sapphirePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BLUE_SAPPHIRE_BLOCK);

        // Generate all the complex shapes using that pool
        sapphirePool.stairs(ModBlocks.BLUE_SAPPHIRE_STAIRS);
        sapphirePool.slab(ModBlocks.BLUE_SAPPHIRE_SLAB);
        sapphirePool.button(ModBlocks.BLUE_SAPPHIRE_BUTTON);
        sapphirePool.pressurePlate(ModBlocks.BLUE_SAPPHIRE_PRESSURE_PLATE);
        sapphirePool.fence(ModBlocks.BLUE_SAPPHIRE_FENCE);
        sapphirePool.fenceGate(ModBlocks.BLUE_SAPPHIRE_FENCE_GATE);
        sapphirePool.wall(ModBlocks.BLUE_SAPPHIRE_WALL);

        // --- DOORS & TRAPDOORS ---
        blockStateModelGenerator.registerDoor(ModBlocks.BLUE_SAPPHIRE_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.BLUE_SAPPHIRE_TRAPDOOR);

        // --- BLUE SAPPHIRE LAMP LOGIC ---
        Identifier lampOffIdentifier = TexturedModel.CUBE_ALL.upload(ModBlocks.BLUE_SAPPHIRE_LAMP, blockStateModelGenerator.modelCollector);
        Identifier lampOnIdentifier = blockStateModelGenerator.createSubModel(ModBlocks.BLUE_SAPPHIRE_LAMP, "_on", Models.CUBE_ALL, TextureMap::all);

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.BLUE_SAPPHIRE_LAMP)
                .coordinate(BlockStateVariantMap.create(BlueSapphireLampBlock.CLICKED)
                        .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, lampOffIdentifier))
                        .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, lampOnIdentifier))));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // --- ITEMS (Held Flat) ---
        itemModelGenerator.register(ModItems.BLUE_SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_BLUE_SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BEEF_SANDWICH, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHISEL, Models.GENERATED);

        // --- TOOLS (Held by Handle) ---
        itemModelGenerator.register(ModItems.BLUE_SAPPHIRE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BLUE_SAPPHIRE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BLUE_SAPPHIRE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BLUE_SAPPHIRE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BLUE_SAPPHIRE_HOE, Models.HANDHELD);

        // Added Hammer here
        itemModelGenerator.register(ModItems.BLUE_SAPPHIRE_HAMMER, Models.HANDHELD);
        // Add armor
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.BLUE_SAPPHIRE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.BLUE_SAPPHIRE_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.BLUE_SAPPHIRE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.BLUE_SAPPHIRE_CHESTPLATE));
    }
}