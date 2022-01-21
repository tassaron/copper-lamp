package com.tassaron.copperlamp.blockentity;

import com.tassaron.copperlamp.CopperLampMod;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.List;

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

    private static Box getAttackZone(BlockPos pos) {
        pos = pos.up();
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        return (new Box((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1)));
    }

    public static void tick(World world, BlockPos pos, BlockState state, PoweredCopperBlockEntity be) {
        if (world == null || world.isClient) {
            return;
        }
        CopperLampMod.LOGGER.info("Powered copper block has {} energy", be.energyStorage.getAmount());
        try (Transaction transaction = Transaction.openOuter()) {
            // Use energy and extract more energy from adjacent blocks
            long amountUsed = 0;
            long amountGained = 0;
            List<LivingEntity> nearbyEntities = world.getEntitiesByClass(LivingEntity.class, getAttackZone(pos), (entity) -> true);
            if (nearbyEntities.size() > 0) {
                amountUsed = be.energyStorage.extract(1, transaction);
            }
            for (Direction side : Direction.values()) {
                amountGained = EnergyStorageUtil.move(
                        EnergyStorage.SIDED.find(world, pos.offset(side), side.getOpposite()),
                        be.energyStorage,
                        1,
                        transaction
                );
                if (amountUsed > 0 || amountGained > 0) {
                    CopperLampMod.LOGGER.info("Powered copper block used {} energy and gained {} energy", amountUsed, amountGained);
                    transaction.commit();
                    be.markDirty();
                    break;
                }
            }
            if (amountUsed > 0){
                for (LivingEntity entity : nearbyEntities) {
                    if (!entity.getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_BOOTS)) {
                        entity.damage(DamageSource.LIGHTNING_BOLT, 1.0F);
                    }
                }
            }
        }
    }
}
