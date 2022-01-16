package com.tassaron.copperlamp;

import com.tassaron.copperlamp.block.CopperLampBlock;
import net.fabricmc.api.ModInitializer;
import com.tassaron.copperlamp.block.OxidizableCopperLampBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.function.ToIntFunction;


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

	@Override
	public void onInitialize() {
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
	}

	private static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
		return (state) -> {
			return (Boolean)state.get(Properties.LIT) ? litLevel : 0;
		};
	}
}
