package net.mohammed.firstmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Map;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP = Map.ofEntries(
            // Stone
            Map.entry(Blocks.STONE, Blocks.STONE_BRICKS),
            Map.entry(Blocks.STONE_BRICKS, Blocks.CHISELED_STONE_BRICKS),
            Map.entry(Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE),

            // Deepslate
            Map.entry(Blocks.DEEPSLATE, Blocks.POLISHED_DEEPSLATE),
            Map.entry(Blocks.POLISHED_DEEPSLATE, Blocks.DEEPSLATE_BRICKS),
            Map.entry(Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE_TILES),
            Map.entry(Blocks.DEEPSLATE_TILES, Blocks.CHISELED_DEEPSLATE),

            // NEW in 1.21: Tuff Variants
            Map.entry(Blocks.TUFF, Blocks.POLISHED_TUFF),
            Map.entry(Blocks.POLISHED_TUFF, Blocks.TUFF_BRICKS),

            // Blackstone
            Map.entry(Blocks.BLACKSTONE, Blocks.POLISHED_BLACKSTONE),
            Map.entry(Blocks.POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE_BRICKS),
            Map.entry(Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CHISELED_POLISHED_BLACKSTONE),

            // Nether
            Map.entry(Blocks.NETHERRACK, Blocks.NETHER_BRICKS),
            Map.entry(Blocks.NETHER_BRICKS, Blocks.CHISELED_NETHER_BRICKS),

            // End
            Map.entry(Blocks.END_STONE, Blocks.END_STONE_BRICKS),
            Map.entry(Blocks.PURPUR_BLOCK, Blocks.PURPUR_PILLAR),

            // Sandstone
            Map.entry(Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE),
            Map.entry(Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE),
            Map.entry(Blocks.RED_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE),
            Map.entry(Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE),

            // Prismarine
            Map.entry(Blocks.PRISMARINE, Blocks.PRISMARINE_BRICKS),
            Map.entry(Blocks.PRISMARINE_BRICKS, Blocks.DARK_PRISMARINE),

            // Quartz
            Map.entry(Blocks.QUARTZ_BLOCK, Blocks.CHISELED_QUARTZ_BLOCK),
            Map.entry(Blocks.CHISELED_QUARTZ_BLOCK, Blocks.QUARTZ_BRICKS),
            Map.entry(Blocks.QUARTZ_BRICKS, Blocks.QUARTZ_PILLAR),

            // Standard Polished Variants
            Map.entry(Blocks.ANDESITE, Blocks.POLISHED_ANDESITE),
            Map.entry(Blocks.GRANITE, Blocks.POLISHED_GRANITE),
            Map.entry(Blocks.DIORITE, Blocks.POLISHED_DIORITE),

            // Mud
            Map.entry(Blocks.PACKED_MUD, Blocks.MUD_BRICKS)
    );

    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();
        if (CHISEL_MAP.containsKey(clickedBlock)) {

            // Only modify the world on the server side
            if (!world.isClient()) {


                world.setBlockState(context.getBlockPos(), CHISEL_MAP.get(clickedBlock).getDefaultState());


                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));


                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }

        }
            return ActionResult.SUCCESS;
        }

    }

