package com.tassaron.copperlamp.blockentity;

import com.tassaron.copperlamp.CopperLampMod;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.List;

public class PoweredCopperBlockEntity extends BlockEntity {
    private short tickDelay = 0;
    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(10, 10, 10) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    public PoweredCopperBlockEntity(BlockPos pos, BlockState state) {
        super(CopperLampMod.POWERED_COPPER_BLOCK_ENTITY, pos, state);
    }

    private static Box getAttackZone(BlockPos pos) {
        pos = pos.up();
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        return (new Box(i, j, k, i + 1, j + 1, k + 1));
    }

    public static void tick(World world, BlockPos pos, BlockState state, PoweredCopperBlockEntity be) {
        if (world == null || world.isClient) {
            return;
        } else if (be.tickDelay > 0) {
            be.tickDelay--;
            return;
        }
        try (Transaction transaction = Transaction.openOuter()) {
            long amountUsed = 0;
            List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class, getAttackZone(pos), (entity) -> true);
            if (nearbyEntities.size() > 0) {
                amountUsed = be.energyStorage.extract(10, transaction);
            }
            if (amountUsed > 0) {
                CopperLampMod.LOGGER.info(
                        "Powered copper block at {},{},{} used {} energy",
                        pos.getX(), pos.getY(), pos.getZ(), amountUsed
                );
                transaction.commit();
                be.markDirty();
                for (LivingEntity entity : nearbyEntities) {
                    if (!entity.getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_BOOTS)) {
                        entity.damage(DamageSource.LIGHTNING_BOLT, (float) amountUsed);
                        ((ServerWorld) world).spawnParticles(ParticleTypes.ELECTRIC_SPARK, entity.getX(), entity.getY(), entity.getZ(), 4, 0.5D, 0.25D, 0.5D, 0.0D);
                    }
                }
                be.tickDelay = 20;
            }
        }
    }
}
