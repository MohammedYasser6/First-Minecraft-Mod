package net.mohammed.firstmod.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.mohammed.firstmod.FirstModClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    // Inject at "HEAD" means: Run this BEFORE drawing the hotbar/hearts
    @Inject(method = "render", at = @At("HEAD"))
    public void renderSapphireOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        // Call our static method from the Client class
        FirstModClient.renderSapphireOverlay(context);
    }
}