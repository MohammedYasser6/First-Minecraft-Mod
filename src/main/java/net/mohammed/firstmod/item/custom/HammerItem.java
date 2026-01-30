package net.mohammed.firstmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends MiningToolItem {
    public HammerItem(ToolMaterial material, Settings settings) {
        // Hammers are effectively Pickaxes, so we use the Pickaxe Mineable tag
        super(material, BlockTags.PICKAXE_MINEABLE, settings);
    }

    /**
     * Calculates the 3x3 area of blocks to destroy based on the player's perspective.
     * @param range The radius (1 = 3x3, 2 = 5x5)
     * @param initialBlockPos The center block that was hit
     * @param player The player using the tool
     * @return A list of BlockPos representing the 3x3 grid
     */
    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initialBlockPos, ServerPlayerEntity player) {
        List<BlockPos> positions = new ArrayList<>();

        // Raycast to find out which face of the block the player hit (Up, North, East, etc.)
        BlockHitResult hit = (BlockHitResult) player.raycast(20, 0, false);
        Direction side = hit.getSide();

        // If hitting Top/Bottom (Up/Down) -> Mine the X/Z plane (Flat ground)
        if(side == Direction.DOWN || side == Direction.UP) {
            for(int x = -range; x <= range; x++) {
                for(int z = -range; z <= range; z++) {
                    positions.add(new BlockPos(initialBlockPos.getX() + x, initialBlockPos.getY(), initialBlockPos.getZ() + z));
                }
            }
        }

        // If hitting Front/Back (North/South) -> Mine the X/Y plane (Wall facing you)
        if(side == Direction.NORTH || side == Direction.SOUTH) {
            for(int x = -range; x <= range; x++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initialBlockPos.getX() + x, initialBlockPos.getY() + y, initialBlockPos.getZ()));
                }
            }
        }

        // If hitting Side (East/West) -> Mine the Y/Z plane (Wall facing you)
        if(side == Direction.EAST || side == Direction.WEST) {
            for(int z = -range; z <= range; z++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initialBlockPos.getX(), initialBlockPos.getY() + y, initialBlockPos.getZ() + z));
                }
            }
        }

        return positions;
    }
}