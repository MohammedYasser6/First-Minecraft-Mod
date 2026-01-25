package net.mohammed.firstmod.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent BEEF_SANDWICH = new FoodComponent.Builder().nutrition(12).saturationModifier(0.9f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,400),0.20f).build();
}
