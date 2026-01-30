package net.mohammed.firstmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.mohammed.firstmod.item.ModItems;
import net.mohammed.firstmod.util.ModTags; // Import your custom tags

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        // --- TOOL CATEGORIES ---
        // This allows them to receive the correct enchantments (Efficiency, Fortune, Sharpness, etc.)

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.BLUE_SAPPHIRE_SWORD);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.BLUE_SAPPHIRE_PICKAXE);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.BLUE_SAPPHIRE_AXE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.BLUE_SAPPHIRE_SHOVEL);

        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.BLUE_SAPPHIRE_HOE);

        // --- GAMEPLAY MECHANICS ---

        // Allow Blue Sapphire to be used to power Beacons (like Iron, Gold, Diamond)
        getOrCreateTagBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.BLUE_SAPPHIRE);

        // --- CUSTOM TAGS ---

        // Group your items under your custom tag
        getOrCreateTagBuilder(ModTags.Items.BLUE_SAPPHIRE_ITEMS)
                .add(ModItems.BLUE_SAPPHIRE)
                .add(ModItems.RAW_BLUE_SAPPHIRE);

        // --- armor trims
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.BLUE_SAPPHIRE_HELMET)
                .add(ModItems.BLUE_SAPPHIRE_CHESTPLATE)
                .add(ModItems.BLUE_SAPPHIRE_LEGGINGS)
                .add(ModItems.BLUE_SAPPHIRE_BOOTS);

        // --- SPECIFIC ARMOR SLOTS (Add these too!) ---
        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR).add(ModItems.BLUE_SAPPHIRE_HELMET);
        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR).add(ModItems.BLUE_SAPPHIRE_CHESTPLATE);
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR).add(ModItems.BLUE_SAPPHIRE_LEGGINGS);
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR).add(ModItems.BLUE_SAPPHIRE_BOOTS);

    }
}