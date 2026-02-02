package net.mohammed.firstmod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.mohammed.firstmod.effect.ModEffects;
import net.mohammed.firstmod.util.ModModelPredicates;

public class FirstModClient implements ClientModInitializer {

    // Make sure your image exists at this path!
    private static final Identifier SAPPHIRE_OVERLAY = Identifier.of("firstmod", "textures/misc/sapphire_overlay.png");

    @Override
    public void onInitializeClient() {
        // Only register the bow animation here.
        // We removed the HudRenderCallback because the Mixin handles it now.
        ModModelPredicates.registerModelPredicates();
    }

    // Must be 'public static' so the Mixin can call it
    public static void renderSapphireOverlay(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();

        // Check if player has the effect
        if (client.player != null && client.player.hasStatusEffect(Registries.STATUS_EFFECT.getEntry(ModEffects.SAPPHIRE_CRYSTAL))) {

            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            // --- RENDER SETUP ---
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            // --- DRAW IMAGE ---
            context.drawTexture(
                    SAPPHIRE_OVERLAY,
                    0, 0,          // x, y
                    0, 0,          // u, v
                    width, height, // draw size
                    width, height  // texture size
            );

            // --- CLEANUP ---
            RenderSystem.disableBlend();
            context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}