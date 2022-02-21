package com.tassaron.copperlamp.block;

import com.tassaron.copperlamp.CopperLampMod;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.Random;

import static com.tassaron.copperlamp.CopperLampMod.COPPER_TORCH_ENERGY;

public class CopperTorchBlock extends TorchBlock {
    public static final short tickDelay = 20;
    public boolean lit;

    public CopperTorchBlock(Settings settings, boolean lit) {
        super(settings, ParticleTypes.SOUL_FIRE_FLAME);
        this.lit = lit;
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (lit) {
            world.createAndScheduleBlockTick(pos, world.getBlockState(pos).getBlock(), tickDelay);
        }
    }

    public static void transmitPowerUp(ServerWorld world, BlockPos pos) {
        if (world.isClient) {
            // energy transfer is server-side
            return;
        }
        EnergyStorage es = SimpleEnergyStorage.SIDED.find(world, pos.up(), Direction.DOWN);
        if (es == null) {
            return;
        }
        try (Transaction transaction = Transaction.openOuter()) {
            long amountInserted = es.insert(COPPER_TORCH_ENERGY, transaction);
            if (amountInserted > 0) {
                CopperLampMod.LOGGER.info("Copper torch is transmitting power to x{}, y{}, z{}", pos.getX(), pos.getY(), pos.getZ());
                transaction.commit();
            }
        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (lit) {
            world.createAndScheduleBlockTick(pos, world.getBlockState(pos).getBlock(), tickDelay);
            transmitPowerUp(world, pos);
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (lit) {
            super.randomDisplayTick(state, world, pos, random);
        }
    }
}
