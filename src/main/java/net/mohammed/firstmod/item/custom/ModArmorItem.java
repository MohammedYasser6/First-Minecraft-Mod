package net.mohammed.firstmod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public class ModArmorItem extends ArmorItem {
    public ModArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()) {
            if(entity instanceof PlayerEntity player && hasFullSetOfArmorOn(player)) {
                evaluateArmorEffects(player);
            }
        }
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        // Example: Gives Speed II and Strength I
        // Duration is set to 200 ticks (10 seconds) so it doesn't flicker
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1, false, false, true));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0, false, false, true));
    }

    private boolean hasFullSetOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack chestplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !chestplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    // Optional: Add a check to ensure all pieces are from YOUR specific material
    private boolean hasCorrectArmorOn(RegistryEntry<ArmorMaterial> material, PlayerEntity player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem armorItem)) {
                return false;
            }
            if (!armorItem.getMaterial().equals(material)) {
                return false;
            }
        }
        return true;
    }
}