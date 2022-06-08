package com.tassaron.copperlamp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.math.random.Random;

public class WallCopperTorchBlock extends WallTorchBlock {
    public static final DirectionProperty FACING;
    public boolean lit;

    public WallCopperTorchBlock(Settings settings, boolean lit) {
        super(settings, ParticleTypes.SOUL_FIRE_FLAME);
        this.lit = lit;
        this.lootTableId = new Identifier("copperlamp", lit ? "blocks/lit_copper_torch" : "blocks/copper_torch");
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (lit) {
            world.createAndScheduleBlockTick(pos, world.getBlockState(pos).getBlock(), CopperTorchBlock.tickDelay);
        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (lit) {
            world.createAndScheduleBlockTick(pos, world.getBlockState(pos).getBlock(), CopperTorchBlock.tickDelay);
            CopperTorchBlock.transmitPowerUp(world, pos);
        }
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (lit) {
            super.randomDisplayTick(state, world, pos, random);
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
    }
}
