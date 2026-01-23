package net.mohammed.firstmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.mohammed.firstmod.FirstMod;

public class ModBlocks {

    public static final Block BLUE_SAPPHIRE_BLOCK =registerBlock("blue_sapphire_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .luminance(state -> 10) // Glows slightly
                    .slipperiness(0.98f)    // Slippery like ice
            ));
    public static final Block RAW_BLUE_SAPPHIRE_BLOCK =registerBlock("raw_blue_sapphire_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)

            ));

    private static Block registerBlock(String name, Block block){
        registerBlocksItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(FirstMod.MOD_ID,name),block);
    }

    private static void registerBlocksItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(FirstMod.MOD_ID,name),
        new BlockItem(block, new Item.Settings()));
    }
    public static void registerModBlocks(){
        FirstMod.LOGGER.info("Registering Mod Blocks for my first mod" +FirstMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.BLUE_SAPPHIRE_BLOCK);
            fabricItemGroupEntries.add(ModBlocks.RAW_BLUE_SAPPHIRE_BLOCK);
        });
    }
}
