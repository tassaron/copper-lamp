package com.tassaron.copperlamp;

import com.tassaron.copperlamp.block.*;
import com.tassaron.copperlamp.blockentity.CopperCapacitorBlockEntity;
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
	public static final Block COPPER_TORCH = new CopperTorchBlock(FabricBlockSettings.copy(SOUL_TORCH).luminance((state) -> 0), false);
	public static final Block LIT_COPPER_TORCH = new CopperTorchBlock(FabricBlockSettings.copy(SOUL_TORCH).luminance((state) -> 15), true);
	public static final Block COPPER_WALL_TORCH = new WallCopperTorchBlock(FabricBlockSettings.copy(SOUL_TORCH).luminance((state) -> 0), false);
	public static final Block LIT_COPPER_WALL_TORCH = new WallCopperTorchBlock(FabricBlockSettings.copy(SOUL_TORCH).luminance((state) -> 15), true);
	public static final WallStandingBlockItem COPPER_TORCH_ITEM = new WallStandingBlockItem(COPPER_TORCH, COPPER_WALL_TORCH, new FabricItemSettings().group(ItemGroup.DECORATIONS));
	public static final WallStandingBlockItem LIT_COPPER_TORCH_ITEM = new WallStandingBlockItem(LIT_COPPER_TORCH, LIT_COPPER_WALL_TORCH, new FabricItemSettings().group(ItemGroup.DECORATIONS));
	public static final Block COPPER_CAPACITOR_BLOCK = new CopperCapacitorBlock(FabricBlockSettings.copy(COPPER_BLOCK));
	public static final BlockEntityType<CopperCapacitorBlockEntity> COPPER_CAPACITOR_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(CopperCapacitorBlockEntity::new, COPPER_CAPACITOR_BLOCK).build(null);

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
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "lit_copper_wall_torch"), LIT_COPPER_WALL_TORCH);
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "copper_torch"), COPPER_TORCH);
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "lit_copper_torch"), LIT_COPPER_TORCH);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "copper_torch"), COPPER_TORCH_ITEM);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "lit_copper_torch"), LIT_COPPER_TORCH_ITEM);

		LOGGER.info("Registering powered copper block");
		Registry.register(Registry.BLOCK, new Identifier("copperlamp", "copper_capacitor_block"), COPPER_CAPACITOR_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("copperlamp", "copper_capacitor_block"), new BlockItem(COPPER_CAPACITOR_BLOCK, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.BLOCK_ENTITY_TYPE, "copperlamp:copper_capacitor_block_entity", COPPER_CAPACITOR_BLOCK_ENTITY);
		EnergyStorage.SIDED.registerForBlockEntity(
				(entity, direction) -> entity.energyStorage, COPPER_CAPACITOR_BLOCK_ENTITY
		);
	}

	private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
		return (state) -> {
			return (Boolean)state.get(Properties.LIT) ? litLevel : 0;
		};
	}
}
