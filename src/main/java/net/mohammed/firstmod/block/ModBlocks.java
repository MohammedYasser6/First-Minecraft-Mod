package net.mohammed.firstmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.mohammed.firstmod.FirstMod;

public class ModBlocks {

    // --- BASE BLOCKS ---
    public static final Block BLUE_SAPPHIRE_BLOCK = registerBlock("blue_sapphire_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .luminance(state -> 10) // Glows
                    .slipperiness(0.98f)    // Slippery
            ));

    public static final Block RAW_BLUE_SAPPHIRE_BLOCK = registerBlock("raw_blue_sapphire_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
            ));

    public static final Block BLUE_SAPPHIRE_ORE = registerBlock("blue_sapphire_ore",
            new Block(AbstractBlock.Settings.create()
                    .strength(3f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
            ));

    public static final Block BLUE_SAPPHIRE_DEEPSLATE_ORE = registerBlock("blue_sapphire_deepslate_ore",
            new Block(AbstractBlock.Settings.create()
                    .strength(4.5f, 3f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)
            ));

    // --- NEW BUILDING BLOCKS ---

    public static final Block BLUE_SAPPHIRE_STAIRS = registerBlock("blue_sapphire_stairs",
            new StairsBlock(BLUE_SAPPHIRE_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.copy(BLUE_SAPPHIRE_BLOCK)));

    public static final Block BLUE_SAPPHIRE_SLAB = registerBlock("blue_sapphire_slab",
            new SlabBlock(AbstractBlock.Settings.copy(BLUE_SAPPHIRE_BLOCK)));

    // --- NEW REDSTONE & INTERACTIVE BLOCKS ---

    public static final Block BLUE_SAPPHIRE_BUTTON = registerBlock("blue_sapphire_button",
            new ButtonBlock(BlockSetType.IRON, 10, AbstractBlock.Settings.copy(BLUE_SAPPHIRE_BLOCK)));

    public static final Block BLUE_SAPPHIRE_PRESSURE_PLATE = registerBlock("blue_sapphire_pressure_plate",
            new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(BLUE_SAPPHIRE_BLOCK)));

    // --- FENCES & WALLS ---

    public static final Block BLUE_SAPPHIRE_FENCE = registerBlock("blue_sapphire_fence",
            new FenceBlock(AbstractBlock.Settings.copy(BLUE_SAPPHIRE_BLOCK)));

    public static final Block BLUE_SAPPHIRE_FENCE_GATE = registerBlock("blue_sapphire_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.copy(BLUE_SAPPHIRE_BLOCK)));

    public static final Block BLUE_SAPPHIRE_WALL = registerBlock("blue_sapphire_wall",
            new WallBlock(AbstractBlock.Settings.copy(BLUE_SAPPHIRE_BLOCK)));

    // --- DOORS & TRAPDOORS ---
    // Note: doors/trapdoors need nonOpaque() so you can see through parts of them if they aren't solid rectangles

    public static final Block BLUE_SAPPHIRE_DOOR = registerBlock("blue_sapphire_door",
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(BLUE_SAPPHIRE_BLOCK).nonOpaque()));

    public static final Block BLUE_SAPPHIRE_TRAPDOOR = registerBlock("blue_sapphire_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(BLUE_SAPPHIRE_BLOCK).nonOpaque()));


    // --- REGISTRATION ---

    private static Block registerBlock(String name, Block block) {
        registerBlocksItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(FirstMod.MOD_ID, name), block);
    }

    private static void registerBlocksItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(FirstMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        FirstMod.LOGGER.info("Registering Mod Blocks for my first mod " + FirstMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_BLOCK);
            fabricItemGroupEntries.add(ModBlocks.RAW_BLUE_SAPPHIRE_BLOCK);
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_DEEPSLATE_ORE);
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_ORE);

            // Add new items to creative tab
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_STAIRS);
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_SLAB);
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_BUTTON);
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_PRESSURE_PLATE);
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_FENCE);
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_FENCE_GATE);
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_WALL);
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_DOOR);
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_TRAPDOOR);
        });
    }
}