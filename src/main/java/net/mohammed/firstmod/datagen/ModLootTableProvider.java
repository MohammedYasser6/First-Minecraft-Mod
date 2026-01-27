package net.mohammed.firstmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.mohammed.firstmod.block.ModBlocks;
import net.mohammed.firstmod.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        // --- EXISTING BLOCKS ---
        addDrop(ModBlocks.BLUE_SAPPHIRE_BLOCK);
        addDrop(ModBlocks.RAW_BLUE_SAPPHIRE_BLOCK);
        addDrop(ModBlocks.BLUE_SAPPHIRE_ORE, oreDrops(ModBlocks.BLUE_SAPPHIRE_ORE, ModItems.RAW_BLUE_SAPPHIRE));
        addDrop(ModBlocks.BLUE_SAPPHIRE_DEEPSLATE_ORE, multipleOreDrops(ModBlocks.BLUE_SAPPHIRE_DEEPSLATE_ORE, ModItems.RAW_BLUE_SAPPHIRE, 3, 7));

        // --- NEW BLOCKS (SIMPLE DROPS) ---
        addDrop(ModBlocks.BLUE_SAPPHIRE_STAIRS);
        addDrop(ModBlocks.BLUE_SAPPHIRE_BUTTON);
        addDrop(ModBlocks.BLUE_SAPPHIRE_PRESSURE_PLATE);
        addDrop(ModBlocks.BLUE_SAPPHIRE_FENCE);
        addDrop(ModBlocks.BLUE_SAPPHIRE_FENCE_GATE);
        addDrop(ModBlocks.BLUE_SAPPHIRE_WALL);
        addDrop(ModBlocks.BLUE_SAPPHIRE_TRAPDOOR);

        // --- SPECIAL DROPS ---

        // Door: Ensures you get the item regardless of which half (top/bottom) you break
        addDrop(ModBlocks.BLUE_SAPPHIRE_DOOR, doorDrops(ModBlocks.BLUE_SAPPHIRE_DOOR));

        // Slab: Ensures if it's a "Double Slab", it drops 2 items instead of 1
        addDrop(ModBlocks.BLUE_SAPPHIRE_SLAB, slabDrops(ModBlocks.BLUE_SAPPHIRE_SLAB));
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}