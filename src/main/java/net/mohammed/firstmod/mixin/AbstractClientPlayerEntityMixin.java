package net.mohammed.firstmod.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.mohammed.firstmod.item.ModItems; // Make sure to import your ModItems!
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {

    // Dummy constructor to satisfy the compiler
    public AbstractClientPlayerEntityMixin(net.minecraft.world.World world, net.minecraft.util.math.BlockPos pos, float yaw, com.mojang.authlib.GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "getFovMultiplier", at = @At("HEAD"), cancellable = true)
    private void onGetFovMultiplier(CallbackInfoReturnable<Float> cir) {
        if (this.isUsingItem()) {
            ItemStack activeStack = this.getActiveItem();

            // CHECK: Is the player using the Super Bow?
            if (activeStack.isOf(ModItems.SUPER_BOW)) {
                int useTime = this.getItemUseTime();
                float f = (float)useTime / 20.0F; // 20.0F = 1 second for full draw

                // This math makes the zoom smooth (same as vanilla bow)
                if (f > 1.0F) {
                    f = 1.0F;
                } else {
                    f *= f;
                }

                // ZOOM LOGIC:
                // Vanilla bow uses 0.15F (15% zoom).
                // Change 0.35F to make it zoom MORE (Sniper style).
                f *= 0.35F;

                // Return the new FOV value
                cir.setReturnValue(1.0F - f);
            }
        }
    }
}