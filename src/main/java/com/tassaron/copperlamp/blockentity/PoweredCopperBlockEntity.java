package com.tassaron.copperlamp.blockentity;

import com.tassaron.copperlamp.CopperLampMod;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class PoweredCopperBlockEntity extends BlockEntity {
    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(1000, 1, 1) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    public PoweredCopperBlockEntity(BlockPos pos, BlockState state) {
        super(CopperLampMod.POWERED_COPPER_BLOCK_ENTITY, pos, state);
        this.energyStorage.amount = 1000;
    }

    public static void tick(World world, BlockPos pos, BlockState state, PoweredCopperBlockEntity be) {
        if (!world.isClient) {
            try (Transaction transaction = Transaction.openOuter()) {
                be.energyStorage.extract(1, transaction);
                transaction.commit();
            }
            CopperLampMod.LOGGER.info("My energy level is {}", be.energyStorage.getAmount());
            be.markDirty();
        }
    }
}
