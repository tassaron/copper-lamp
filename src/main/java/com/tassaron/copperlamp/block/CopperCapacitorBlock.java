package com.tassaron.copperlamp.block;

import com.tassaron.copperlamp.CopperLampMod;
import com.tassaron.copperlamp.blockentity.CopperCapacitorBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CopperCapacitorBlock extends BlockWithEntity implements BlockEntityProvider {

    public CopperCapacitorBlock(AbstractBlock.Settings settings) {
            super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CopperCapacitorBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, CopperLampMod.COPPER_CAPACITOR_BLOCK_ENTITY, (world1, pos, state1, be) -> CopperCapacitorBlockEntity.tick(world1, pos, state1, be));
    }
}
