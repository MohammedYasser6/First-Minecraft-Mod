package net.mohammed.firstmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SapphireCrystalEffect extends StatusEffect {
    public SapphireCrystalEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        // This gives the visual blue "freeze" overlay (like Powder Snow)
        // 140 is the minimum ticks to show the full frozen overlay

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        // Run every tick to force the visual freeze
        return true;
    }
}