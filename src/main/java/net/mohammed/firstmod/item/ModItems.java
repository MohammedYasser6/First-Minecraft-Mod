package net.mohammed.firstmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mohammed.firstmod.FirstMod;
import net.mohammed.firstmod.item.custom.ChiselItem;
import net.mohammed.firstmod.item.custom.HammerItem; // Import your Hammer class

public class ModItems {
    // --- INGREDIENTS ---
    public static final Item BLUE_SAPPHIRE = registerItem("blue_sapphire", new Item(new Item.Settings()));
    public static final Item RAW_BLUE_SAPPHIRE = registerItem("raw_blue_sapphire", new Item(new Item.Settings()));

    // --- CUSTOM TOOLS ---
    public static final Item CHISEL = registerItem("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));

    // --- HAMMER ---
    // High Damage (7), Very Slow Speed (-3.4f)
    public static final Item BLUE_SAPPHIRE_HAMMER = registerItem("blue_sapphire_hammer",
            new HammerItem(ModToolMaterials.BLUE_SAPPHIRE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.BLUE_SAPPHIRE, 7, -3.4f))));

    // --- BLUE SAPPHIRE TOOLS & WEAPONS ---
    // Sword: 3 Attack Damage, -2.4 Attack Speed
    public static final Item BLUE_SAPPHIRE_SWORD = registerItem("blue_sapphire_sword",
            new SwordItem(ModToolMaterials.BLUE_SAPPHIRE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.BLUE_SAPPHIRE, 3, -2.4f))));

    // Pickaxe: 1 Attack Damage, -2.8 Attack Speed
    public static final Item BLUE_SAPPHIRE_PICKAXE = registerItem("blue_sapphire_pickaxe",
            new PickaxeItem(ModToolMaterials.BLUE_SAPPHIRE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.BLUE_SAPPHIRE, 1, -2.8f))));

    // Shovel: 1.5 Attack Damage, -3.0 Attack Speed
    public static final Item BLUE_SAPPHIRE_SHOVEL = registerItem("blue_sapphire_shovel",
            new ShovelItem(ModToolMaterials.BLUE_SAPPHIRE, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.BLUE_SAPPHIRE, 1.5f, -3.0f))));

    // Axe: 6 Attack Damage, -3.1 Attack Speed (Heavy hitter)
    public static final Item BLUE_SAPPHIRE_AXE = registerItem("blue_sapphire_axe",
            new AxeItem(ModToolMaterials.BLUE_SAPPHIRE, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.BLUE_SAPPHIRE, 6, -3.1f))));

    // Hoe: 0 Attack Damage, -3.0 Attack Speed
    public static final Item BLUE_SAPPHIRE_HOE = registerItem("blue_sapphire_hoe",
            new HoeItem(ModToolMaterials.BLUE_SAPPHIRE, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.BLUE_SAPPHIRE, 0, -3.0f))));


    // --- FOOD ---
    public static final Item BEEF_SANDWICH = registerItem("beef_sandwich", new Item(new Item.Settings().food(ModFoodComponents.BEEF_SANDWICH)));


    // --- REGISTRATION HELPER ---
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(FirstMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FirstMod.LOGGER.info("Registering Mod Items for " + FirstMod.MOD_ID);

        // Add to Ingredients Tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(BLUE_SAPPHIRE);
            fabricItemGroupEntries.add(RAW_BLUE_SAPPHIRE);
        });

        // Add to Tools Tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(CHISEL);
            fabricItemGroupEntries.add(BLUE_SAPPHIRE_HAMMER); // Added Hammer here
            fabricItemGroupEntries.add(BLUE_SAPPHIRE_PICKAXE);
            fabricItemGroupEntries.add(BLUE_SAPPHIRE_AXE);
            fabricItemGroupEntries.add(BLUE_SAPPHIRE_SHOVEL);
            fabricItemGroupEntries.add(BLUE_SAPPHIRE_HOE);
        });

        // Add to Combat Tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(BLUE_SAPPHIRE_SWORD);
            fabricItemGroupEntries.add(BLUE_SAPPHIRE_AXE);
            fabricItemGroupEntries.add(BLUE_SAPPHIRE_HAMMER); // Optional: Hammers are good weapons too!
        });

        // Add to Food Tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(BEEF_SANDWICH);
        });
    }
}