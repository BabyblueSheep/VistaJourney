package babybluesheep.vistajourney.registry;

import babybluesheep.vistajourney.VistaJourney;
import babybluesheep.vistajourney.world.FairyRingFeature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class VistaWorldRegistry {
    private static final ConfiguredFeature<?, ?> RUBY_ORE_GENERATION = Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, VistaBlockRegistry.RUBY_ORE.getDefaultState(), 3));
    private static final ConfiguredFeature<?, ?> DEEPSLATE_RUBY_ORE_GENERATION = Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, VistaBlockRegistry.DEEPSLATE_RUBY_ORE.getDefaultState(), 3));
    private static final PlacedFeature RUBY_ORE_GENERATION_PLACE = RUBY_ORE_GENERATION.withPlacement(CountPlacementModifier.of(20), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(-16), YOffset.fixed(96)));
    private static final PlacedFeature DEEPSLATE_RUBY_ORE_GENERATION_PLACE = DEEPSLATE_RUBY_ORE_GENERATION.withPlacement(CountPlacementModifier.of(20), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(-16), YOffset.fixed(96)));
    private static final PlacedFeature EXTRA_RUBY_ORE_GENERATION_PLACE = RUBY_ORE_GENERATION.withPlacement(CountPlacementModifier.of(5), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(32), YOffset.fixed(48)));

    private static final ConfiguredFeature<?, ?> OPAL_ORE_GENERATION = Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, VistaBlockRegistry.OPAL_ORE.getDefaultState(), 3));
    private static final ConfiguredFeature<?, ?> DEEPSLATE_OPAL_ORE_GENERATION = Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, VistaBlockRegistry.DEEPSLATE_OPAL_ORE.getDefaultState(), 3));
    private static final PlacedFeature OPAL_ORE_GENERATION_PLACE = OPAL_ORE_GENERATION.withPlacement(CountPlacementModifier.of(20), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(-16), YOffset.fixed(96)));
    private static final PlacedFeature DEEPSLATE_OPAL_ORE_GENERATION_PLACE = DEEPSLATE_OPAL_ORE_GENERATION.withPlacement(CountPlacementModifier.of(20), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(-16), YOffset.fixed(96)));
    private static final PlacedFeature EXTRA_OPAL_ORE_GENERATION_PLACE = OPAL_ORE_GENERATION.withPlacement(CountPlacementModifier.of(5), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(32), YOffset.fixed(48)));

    private static final Feature<DefaultFeatureConfig> FAIRY_RING_FEATURE = new FairyRingFeature(DefaultFeatureConfig.CODEC);
    private static final ConfiguredFeature<?, ?> FAIRY_RING_CONFIG = FAIRY_RING_FEATURE.configure(new DefaultFeatureConfig());
    private static final PlacedFeature FAIRY_RING_PLACED_FEATURE = FAIRY_RING_CONFIG.withPlacement(RarityFilterPlacementModifier.of(100), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(1));

    public static final Feature<HugeMushroomFeatureConfig> HUGE_VIOLET_WEBCAP = new HugeRedMushroomFeature(HugeMushroomFeatureConfig.CODEC);
    public static final ConfiguredFeature<?, ?> HUGE_VIOLET_WEBCAP_CONFIG = HUGE_VIOLET_WEBCAP.configure(new HugeMushroomFeatureConfig(BlockStateProvider.of(VistaBlockRegistry.VIOLET_WEBCAP_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(VistaBlockRegistry.VIOLET_WEBCAP_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 1));
    public static final PlacedFeature HUGE_VIOLET_WEBCAP_PLACED = HUGE_VIOLET_WEBCAP_CONFIG.withPlacement(RarityFilterPlacementModifier.of(20), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(1));
    
    public static void registerFeatures()
    {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(VistaJourney.MOD_ID, "ruby_ore_generation"), RUBY_ORE_GENERATION);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(VistaJourney.MOD_ID, "ruby_ore_generation"), RUBY_ORE_GENERATION_PLACE);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.DESERT, Biome.Category.MESA), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(VistaJourney.MOD_ID, "ruby_ore_generation")));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(VistaJourney.MOD_ID, "deepslate_ruby_ore_generation"), DEEPSLATE_RUBY_ORE_GENERATION);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(VistaJourney.MOD_ID, "deepslate_ruby_ore_generation"), DEEPSLATE_RUBY_ORE_GENERATION_PLACE);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.DESERT, Biome.Category.MESA), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(VistaJourney.MOD_ID, "deepslate_ruby_ore_generation")));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(VistaJourney.MOD_ID, "extra_ruby_ore_generation"), EXTRA_RUBY_ORE_GENERATION_PLACE);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.DESERT, Biome.Category.MESA), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(VistaJourney.MOD_ID, "extra_ruby_ore_generation")));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(VistaJourney.MOD_ID, "opal_ore_generation"), OPAL_ORE_GENERATION);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(VistaJourney.MOD_ID, "opal_ore_generation"), OPAL_ORE_GENERATION_PLACE);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.ICY), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(VistaJourney.MOD_ID, "opal_ore_generation")));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(VistaJourney.MOD_ID, "deepslate_opal_ore_generation"), DEEPSLATE_OPAL_ORE_GENERATION);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(VistaJourney.MOD_ID, "deepslate_opal_ore_generation"), DEEPSLATE_OPAL_ORE_GENERATION_PLACE);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.ICY), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(VistaJourney.MOD_ID, "deepslate_opal_ore_generation")));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(VistaJourney.MOD_ID, "extra_opal_ore_generation"), EXTRA_OPAL_ORE_GENERATION_PLACE);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.ICY), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(VistaJourney.MOD_ID, "extra_opal_ore_generation")));

        Registry.register(Registry.FEATURE, new Identifier(VistaJourney.MOD_ID, "fairy_ring_feature"), FAIRY_RING_FEATURE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(VistaJourney.MOD_ID, "fairy_ring_feature"), FAIRY_RING_CONFIG);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(VistaJourney.MOD_ID, "fairy_ring_feature"), FAIRY_RING_PLACED_FEATURE);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(VistaJourney.MOD_ID, "fairy_ring_feature")));

        Registry.register(Registry.FEATURE, new Identifier(VistaJourney.MOD_ID, "huge_violet_webcap"), HUGE_VIOLET_WEBCAP);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(VistaJourney.MOD_ID, "huge_violet_webcap"), HUGE_VIOLET_WEBCAP_CONFIG);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(VistaJourney.MOD_ID, "huge_violet_webcap"), HUGE_VIOLET_WEBCAP_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.MUSHROOM), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(VistaJourney.MOD_ID, "huge_violet_webcap")));
    }
}