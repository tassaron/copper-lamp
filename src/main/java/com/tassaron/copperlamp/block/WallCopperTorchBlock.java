package com.tassaron.copperlamp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

public class WallCopperTorchBlock extends WallTorchBlock {
    public static final DirectionProperty FACING;
    public static final BooleanProperty LIT;

    public WallCopperTorchBlock(Settings settings, DefaultParticleType particle) {
        super(settings, particle);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(LIT, true));
        this.lootTableId = new Identifier("copperlamp","blocks/copper_torch");
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        world.createAndScheduleBlockTick(pos, world.getBlockState(pos).getBlock(), CopperTorchBlock.tickDelay);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        world.createAndScheduleBlockTick(pos, world.getBlockState(pos).getBlock(), CopperTorchBlock.tickDelay);
        CopperTorchBlock.transmitPowerUp(world, pos);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, LIT});
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        LIT = CopperTorchBlock.LIT;
    }
}
