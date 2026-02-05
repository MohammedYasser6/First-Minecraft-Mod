package net.mohammed.firstmod.potion;

import net.mohammed.firstmod.FirstMod;
import net.mohammed.firstmod.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotions {

    // Matches the style of the image, but adds the necessary '.getEntry()' fix for 1.21+
    public static final RegistryEntry<Potion> SAPPHIRE_CRYSTAL_POTION = registerPotion("sapphire_crystal_potion",
            new Potion(new StatusEffectInstance(
                    Registries.STATUS_EFFECT.getEntry(ModEffects.SAPPHIRE_CRYSTAL),
                    1200,
                    0
            )));

    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(FirstMod.MOD_ID, name), potion);
    }

    public static void registerPotions() {
        FirstMod.LOGGER.info("Registering Mod Potions for " + FirstMod.MOD_ID);
    }
}