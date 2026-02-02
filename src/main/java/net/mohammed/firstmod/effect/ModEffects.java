package net.mohammed.firstmod.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mohammed.firstmod.FirstMod;

public class ModEffects {
    public static StatusEffect SAPPHIRE_CRYSTAL;

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register(Registries.STATUS_EFFECT, Identifier.of(FirstMod.MOD_ID, name),
                new SapphireCrystalEffect(StatusEffectCategory.HARMFUL, 0x00FFFF) // Cyan Color
                        // 1. REMOVE MOVEMENT (0 Speed)
                        .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                                Identifier.of(FirstMod.MOD_ID, "sapphire_freeze_move"), -1.0D,
                                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)

                        // 2. REMOVE JUMPING (Cannot Jump)
                        .addAttributeModifier(EntityAttributes.GENERIC_JUMP_STRENGTH,
                                Identifier.of(FirstMod.MOD_ID, "sapphire_freeze_jump"), -1.0D,
                                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }

    public static void registerEffects() {
        SAPPHIRE_CRYSTAL = registerStatusEffect("sapphire_crystal");
    }
}