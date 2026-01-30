package net.mohammed.firstmod.util;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mohammed.firstmod.item.custom.HammerItem;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HammerUsageEvent implements PlayerBlockBreakEvents.Before {
    // A set to track blocks currently being broken by the hammer to prevent recursion
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState blockState, @Nullable BlockEntity blockEntity) {
        if (!world.isClient && player instanceof ServerPlayerEntity serverPlayer) {
            ItemStack mainHandItem = player.getMainHandStack();

            // Check if the player is holding the Hammer
            if (mainHandItem.getItem() instanceof HammerItem) {

                // RECURSION GUARD:
                // If this block is already in our "to-be-broken" list, skip the logic
                // so we don't trigger an infinite loop.
                if (HARVESTED_BLOCKS.contains(pos)) {
                    return true;
                }

                // 1. Calculate the 3x3 grid
                // (We pass '1' as the range for a standard 3x3 area)
                List<BlockPos> positions = HammerItem.getBlocksToBeDestroyed(1, pos, serverPlayer);

                // 2. Add all these positions to the guard set
                HARVESTED_BLOCKS.addAll(positions);

                // 3. Iterate through the positions and break them
                for (BlockPos position : positions) {
                    // Skip the center block (the one the player actually hit)
                    // The game will handle breaking this one naturally after we return true.
                    if (pos.equals(position)) {
                        continue;
                    }

                    // Check if the block is valid (e.g., not Bedrock/Unbreakable)
                    BlockState state = world.getBlockState(position);
                    if (state.getHardness(world, position) >= 0) {
                        // tryBreakBlock ensures drops, stats, and checks permissions
                        serverPlayer.interactionManager.tryBreakBlock(position);
                    }
                }

                // 4. Cleanup: Remove the positions from the set so they can be mined again later
                HARVESTED_BLOCKS.removeAll(positions);
            }
        }

        return true; // Return true to allow the original block to break
    }
}