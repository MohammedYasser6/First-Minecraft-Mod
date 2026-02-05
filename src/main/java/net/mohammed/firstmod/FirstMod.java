package net.mohammed.firstmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder; // <--- ADDED
import net.minecraft.potion.Potions; // <--- ADDED
import net.mohammed.firstmod.block.ModBlocks;
import net.mohammed.firstmod.component.ModDataComponentTypes;
import net.mohammed.firstmod.effect.ModEffects;
import net.mohammed.firstmod.item.ModItems;
import net.mohammed.firstmod.potion.ModPotions;
import net.mohammed.firstmod.util.HammerUsageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstMod implements ModInitializer {
	public static final String MOD_ID = "firstmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModDataComponentTypes.registerDataComponentTypes();

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

		ModEffects.registerEffects();
		ModPotions.registerPotions();

		// --- BREWING RECIPES ---
		FabricBrewingRecipeRegistryBuilder.BUILD.register((builder) -> {
			// Recipe: Awkward Potion + Sapphire Item -> Sapphire Crystal Potion
			builder.registerPotionRecipe(
					Potions.AWKWARD,               // Input Potion (Bottle)
					ModItems.BLUE_SAPPHIRE,             // Ingredient (Change this to your actual item!)
					ModPotions.SAPPHIRE_CRYSTAL_POTION // Output Potion
			);
		});
	}
}
//nothing changed 