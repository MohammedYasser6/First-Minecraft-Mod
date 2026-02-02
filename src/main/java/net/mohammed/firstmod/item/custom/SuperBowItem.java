package net.mohammed.firstmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;

import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class SuperBowItem extends BowItem {
    public SuperBowItem(Settings settings) {
        super(settings);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        // Spectral Insight: Make nearby enemies glow while drawing the bow
        if (!world.isClient && remainingUseTicks % 10 == 0) {
            Box box = user.getBoundingBox().expand(20.0);
            List<LivingEntity> entities = world.getNonSpectatingEntities(LivingEntity.class, box);
            for (LivingEntity entity : entities) {
                if (entity != user) {
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 40, 0, false, false));
                }
            }
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            boolean bl = playerEntity.getAbilities().creativeMode || stack.getEnchantments().getLevel(world.getRegistryManager().get(net.minecraft.registry.RegistryKeys.ENCHANTMENT).getEntry(net.minecraft.enchantment.Enchantments.INFINITY).get()) > 0;
            ItemStack itemStack = playerEntity.getProjectileType(stack);

            if (!itemStack.isEmpty() || bl) {
                int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
                float f = getPullProgress(i);
                if (!((double)f < 0.1)) {
                    if (!world.isClient) {
                        ArrowItem arrowItem = (ArrowItem)(itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity, stack);

                        // --- SUPER STATS ---
                        // f * 4.5f makes the arrow fly much faster (Vanilla is 3.0f)
                        persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, f * 4.5F, 1.0F);

                        // Adding 12.0 damage ensures it one-shots Zombies/Skeletons (20 HP)
                        persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + 12.0D);

                        if (f == 1.0F) {
                            persistentProjectileEntity.setCritical(true);
                        }

                        stack.damage(1, playerEntity, LivingEntity.getSlotForHand(user.getActiveHand()));
                        world.spawnEntity(persistentProjectileEntity);
                    }

                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(),
                            SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!bl && !playerEntity.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                        if (itemStack.isEmpty()) {
                            playerEntity.getInventory().removeOne(itemStack);
                        }
                    }

                    playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }
}