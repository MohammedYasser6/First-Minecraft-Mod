package net.mohammed.firstmod.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.mohammed.firstmod.FirstMod;

public class ModTags {

    public static class Blocks {
        // Blocks that REQUIRE a Blue Sapphire tool to be mined
        public static final TagKey<Block> NEEDS_BLUE_SAPPHIRE_TOOL = createTag("needs_blue_sapphire_tool");

        // Blocks that are INCORRECT for this tool (too hard for it)
        // You use this inside your ModToolMaterials class
        public static final TagKey<Block> INCORRECT_FOR_BLUE_SAPPHIRE_TOOL = createTag("incorrect_for_blue_sapphire_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(FirstMod.MOD_ID, name));
        }
    }

    public static class Items {
        // Useful if other mods want to know what items "belong" to your mod's gems
        public static final TagKey<Item> BLUE_SAPPHIRE_ITEMS = createTag("blue_sapphire_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(FirstMod.MOD_ID, name));
        }
    }
}