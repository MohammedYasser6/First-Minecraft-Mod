package net.mohammed.firstmod.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.mohammed.firstmod.FirstMod;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> BLUE_SAPPHIRE_ARMOUR_MATERIALS =registerArmorMaterial("blue_sapphire",
            ()-> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map ->{
                map.put(ArmorItem.Type.BOOTS,4);
                map.put(ArmorItem.Type.LEGGINGS,7);
                map.put(ArmorItem.Type.CHESTPLATE,8);
                map.put(ArmorItem.Type.HELMET,6);
            }),20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,()-> Ingredient.ofItems(ModItems.BLUE_SAPPHIRE),
                    List.of(new ArmorMaterial.Layer(Identifier.of(FirstMod.MOD_ID, "blue_sapphire"))),0,0));

    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(FirstMod.MOD_ID),material.get());
    };
}
