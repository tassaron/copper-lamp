package com.tassaron.copperlamp.block;

import com.tassaron.copperlamp.CopperLampMod;
import com.tassaron.copperlamp.blockentity.PoweredCopperBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PoweredCopperBlock extends BlockWithEntity implements BlockEntityProvider {

    public PoweredCopperBlock(AbstractBlock.Settings settings) {
            super(settings);
    }

    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        PoweredCopperBlockEntity be = (PoweredCopperBlockEntity)world.getBlockEntity(pos);
        if (be.energyStorage.getAmount() < 1) {
            return;
        }
        if (entity instanceof LivingEntity
                && !((LivingEntity)entity).getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_BOOTS)) {
            entity.damage(DamageSource.LIGHTNING_BOLT, 1.0F);
        }

        super.onSteppedOn(world, pos, state, entity);
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
