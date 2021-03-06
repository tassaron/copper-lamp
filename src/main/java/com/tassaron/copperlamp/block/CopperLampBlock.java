package com.tassaron.copperlamp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;


public class CopperLampBlock extends RedstoneLampBlock {
    public static final BooleanProperty LIT = BooleanProperty.of("lit");

    public CopperLampBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(LIT, true));
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(LIT, !(ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos())));
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            boolean bl = (Boolean)state.get(LIT);
            if (bl == world.isReceivingRedstonePower(pos)) {
                if (bl) {
                    world.createAndScheduleBlockTick(pos, this, 4);
                } else {
                    world.setBlockState(pos, (BlockState)state.cycle(LIT), Block.NOTIFY_LISTENERS);
                }
            }

        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if ((Boolean)state.get(LIT) && world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, (BlockState)state.cycle(LIT), Block.NOTIFY_LISTENERS);
        }
    }
}
