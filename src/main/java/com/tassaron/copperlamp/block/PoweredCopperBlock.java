package com.tassaron.copperlamp.block;

import com.tassaron.copperlamp.CopperLampMod;
import com.tassaron.copperlamp.blockentity.PoweredCopperBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class PoweredCopperBlock extends BlockWithEntity implements BlockEntityProvider {

    public PoweredCopperBlock(AbstractBlock.Settings settings) {
            super(settings);
    }

    public PoweredCopperBlockEntity getBlockEntity(World world, BlockPos pos) {
        return (PoweredCopperBlockEntity) world.getBlockEntity(pos);
    }

    public boolean isPowered(PoweredCopperBlockEntity be) {
        if (be.energyStorage.getAmount() < 1) {
            return false;
        }
        return true;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!isPowered(getBlockEntity(world, pos))) {
            return;
        }
        BlockPos blockPos = pos.up();
        world.spawnParticles(ParticleTypes.ELECTRIC_SPARK, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.25D, (double)blockPos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
    }


    // BlockEntity-related Methods

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PoweredCopperBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, CopperLampMod.POWERED_COPPER_BLOCK_ENTITY, (world1, pos, state1, be) -> PoweredCopperBlockEntity.tick(world1, pos, state1, be));
    }
}
