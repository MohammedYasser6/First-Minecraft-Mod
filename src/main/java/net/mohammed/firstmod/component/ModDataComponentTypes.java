package net.mohammed.firstmod.component;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mohammed.firstmod.FirstMod;

import java.util.function.UnaryOperator;



public class ModDataComponentTypes {


    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOpertator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(FirstMod.MOD_ID, name),
                builderOpertator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes(){
        FirstMod.LOGGER.info("registerDataComponentTypes" + FirstMod.MOD_ID);
    }
}
