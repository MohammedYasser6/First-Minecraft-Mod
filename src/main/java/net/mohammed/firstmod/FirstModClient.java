package net.mohammed.firstmod;

import net.fabricmc.api.ClientModInitializer;
import net.mohammed.firstmod.util.ModModelPredicates;

public class FirstModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();
    }
}
