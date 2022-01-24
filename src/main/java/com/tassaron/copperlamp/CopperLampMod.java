package com.tassaron.copperlamp;

import com.tassaron.copperlamp.block.*;
import com.tassaron.copperlamp.blockentity.PoweredCopperBlockEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallStandingBlockItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.reborn.energy.api.EnergyStorage;

import java.util.function.ToIntFunction;

import static net.minecraft.block.Blocks.*;


public class CopperLampMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("copperlamp");
	public static final Block COPPER_LAMP = new OxidizableCopperLampBlock(Oxidizable.OxidationLevel.UNAFFECTED, FabricBlockSettings.of(Material.REDSTONE_LAMP).luminance(createLightLevelFromLitBlockState(15)).strength(0.3F).sounds(BlockSoundGroup.GLASS));
	public static final Block EXPOSED_COPPER_LAMP = new OxidizableCopperLampBlock(Oxidizable.OxidationLevel.EXPOSED, FabricBlockSettings.of(Material.REDSTONE_LAMP).luminance(createLightLevelFromLitBlockState(15)).strength(0.3F).sounds(BlockSoundGroup.GLASS));
	public static final Block WEATHERED_COPPER_LAMP = new OxidizableCopperLampBlock(Oxidizable.OxidationLevel.WEATHERED, FabricBlockSettings.of(Material.REDSTONE_LAMP).luminance(createLightLevelFromLitBlockState(15)).strength(0.3F).sounds(BlockSoundGroup.GLASS));
	public static final Block OXIDIZED_COPPER_LAMP = new OxidizableCopperLampBlock(Oxidizable.OxidationLevel.OXIDIZED, FabricBlockSettings.of(Material.REDSTONE_LAMP).luminance(createLightLevelFromLitBlockState(15)).strength(0.3F).sounds(BlockSoundGroup.GLASS));
	public static final Block WAXED_COPPER_LAMP = new CopperLampBlock(FabricBlockSettings.of(Material.REDSTONE_LAMP).luminance(createLightLevelFromLitBlockState(15)).strength(0.3F).sounds(BlockSoundGroup.GLASS));
	public static final Block WAXED_EXPOSED_COPPER_LAMP = new CopperLampBlock(FabricBlockSettings.of(Material.REDSTONE_LAMP).luminance(createLightLevelFromLitBlockState(15)).strength(0.3F).sounds(BlockSoundGroup.GLASS));
	public static final Block WAXED_WEATHERED_COPPER_LAMP = new CopperLampBlock(FabricBlockSettings.of(Material.REDSTONE_LAMP).luminance(createLightLevelFromLitBlockState(15)).strength(0.3F).sounds(BlockSoundGroup.GLASS));
	public static final Block WAXED_OXIDIZED_COPPER_LAMP = new CopperLampBlock(FabricBlockSettings.of(Material.REDSTONE_LAMP).luminance(createLightLevelFromLitBlockState(15)).strength(0.3F).sounds(BlockSoundGroup.GLASS));
	public static final Block COPPER_TORCH = new CopperTorchBlock(FabricBlockSettings.copy(SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME);
	public static final Block COPPER_WALL_TORCH = new WallCopperTorchBlock(FabricBlockSettings.copy(SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME);
	public static final Block POWERED_COPPER_BLOCK = new PoweredCopperBlock(FabricBlockSettings.of(Material.METAL, MapColor.ORANGE).requiresTool().strength(3.0F, 6.0F).sounds(BlockSoundGroup.COPPER));
	public static BlockEntityType<PoweredCopperBlockEntity> POWERED_COPPER_BLOCK_ENTITY;

	@Override
	public void onInitialize() {
		LOGGER.info("Registering oxidizable copper lamp blocks");
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "copper_lamp"), COPPER_LAMP);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "copper_lamp"), new BlockItem(COPPER_LAMP, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "exposed_copper_lamp"), EXPOSED_COPPER_LAMP);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "exposed_copper_lamp"), new BlockItem(EXPOSED_COPPER_LAMP, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "weathered_copper_lamp"), WEATHERED_COPPER_LAMP);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "weathered_copper_lamp"), new BlockItem(WEATHERED_COPPER_LAMP, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "oxidized_copper_lamp"), OXIDIZED_COPPER_LAMP);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "oxidized_copper_lamp"), new BlockItem(OXIDIZED_COPPER_LAMP, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "waxed_copper_lamp"), WAXED_COPPER_LAMP);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "waxed_copper_lamp"), new BlockItem(WAXED_COPPER_LAMP, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "waxed_exposed_copper_lamp"), WAXED_EXPOSED_COPPER_LAMP);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "waxed_exposed_copper_lamp"), new BlockItem(WAXED_EXPOSED_COPPER_LAMP, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "waxed_weathered_copper_lamp"), WAXED_WEATHERED_COPPER_LAMP);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "waxed_weathered_copper_lamp"), new BlockItem(WAXED_WEATHERED_COPPER_LAMP, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "waxed_oxidized_copper_lamp"), WAXED_OXIDIZED_COPPER_LAMP);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "waxed_oxidized_copper_lamp"), new BlockItem(WAXED_OXIDIZED_COPPER_LAMP, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_LAMP, EXPOSED_COPPER_LAMP);
		OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_LAMP, WEATHERED_COPPER_LAMP);
		OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_LAMP, OXIDIZED_COPPER_LAMP);
		OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_LAMP, WAXED_COPPER_LAMP);
		OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_LAMP, WAXED_EXPOSED_COPPER_LAMP);
		OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_LAMP, WAXED_WEATHERED_COPPER_LAMP);
		OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_LAMP, WAXED_OXIDIZED_COPPER_LAMP);

		LOGGER.info("Registering copper torch");
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "copper_wall_torch"), COPPER_WALL_TORCH);
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "copper_torch"), COPPER_TORCH);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "copper_torch"), new WallStandingBlockItem(COPPER_TORCH, COPPER_WALL_TORCH, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

		LOGGER.info("Registering powered copper block");
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "powered_copper_block"), POWERED_COPPER_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "powered_copper_block"), new BlockItem(POWERED_COPPER_BLOCK, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		POWERED_COPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "copperlamp:powered_copper_block_entity", FabricBlockEntityTypeBuilder.create(PoweredCopperBlockEntity::new, POWERED_COPPER_BLOCK).build(null));
		EnergyStorage.SIDED.registerForBlockEntity(
				(entity, direction) -> entity.energyStorage, POWERED_COPPER_BLOCK_ENTITY
		);
	}

	private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
		return (state) -> {
			return (Boolean)state.get(Properties.LIT) ? litLevel : 0;
		};
	}
}
