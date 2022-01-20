package com.tassaron.copperlamp.blockentity;

import com.tassaron.copperlamp.CopperLampMod;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class PoweredCopperBlockEntity extends BlockEntity {
    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(1, 1, 1) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    public PoweredCopperBlockEntity(BlockPos pos, BlockState state) {
        super(CopperLampMod.POWERED_COPPER_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, PoweredCopperBlockEntity be) {
        if (world == null || world.isClient) {
            return;
        }
        try (Transaction transaction = Transaction.openOuter()) {
            // Try to consume 1 energy from this block
            long amountTransferred = be.energyStorage.extract(1, transaction);
            if (amountTransferred > 0) {
                transaction.commit();
                be.markDirty();
            }
            // Try to extract 1 energy from adjacent storages
            for (Direction side : Direction.values()) {
                if (be.energyStorage.getAmount() > 0) {
                    break;
                }
                amountTransferred = EnergyStorageUtil.move(
                        EnergyStorage.SIDED.find(world, pos.offset(side), side.getOpposite()),
                        be.energyStorage,
                        1,
                        transaction
                );
                if (amountTransferred > 0) {
                    transaction.commit();
                    be.markDirty();
                }
            }
        }
        //CopperLampMod.LOGGER.info("My energy level is {}", be.energyStorage.getAmount());
    }
}
