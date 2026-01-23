package net.mohammed.firstmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mohammed.firstmod.FirstMod;

public class ModItems {
    public static final Item BLUE_SAPPHIRE = registerItem("blue_sapphire", new Item(new Item.Settings()));
    public static final Item RAW_BLUE_SAPPHIRE = registerItem("raw_blue_sapphire", new Item(new Item.Settings()));


    private static Item registerItem(String name ,Item item){
        return Registry.register(Registries.ITEM, Identifier.of(FirstMod.MOD_ID, name),item);
    }

    public static void registerModItems(){
        FirstMod.LOGGER.info("Registering Mod Items for " + FirstMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(BLUE_SAPPHIRE);
            fabricItemGroupEntries.add(RAW_BLUE_SAPPHIRE);
        });
    }
}
