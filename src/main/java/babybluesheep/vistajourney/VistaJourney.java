package babybluesheep.vistajourney;

import babybluesheep.vistajourney.block.GlowGlob;
import babybluesheep.vistajourney.effect.Plague;
import babybluesheep.vistajourney.entity.Fungenile;
import babybluesheep.vistajourney.entity.GlowGlobThrown;
import babybluesheep.vistajourney.event.PlayerHurtCallback;
import babybluesheep.vistajourney.item.GlowThrower;
import babybluesheep.vistajourney.world.FairyRingFeature;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import software.bernie.geckolib3.GeckoLib;

public class VistaJourney implements ModInitializer {
	public static final EntityType<GlowGlobThrown> GLOW_GLOB_THROWN_ENTITY_TYPE = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("vistajourney", "glow_glob_thrown"),
			FabricEntityTypeBuilder.<GlowGlobThrown>create(SpawnGroup.MISC, GlowGlobThrown::new)
					.dimensions(EntityDimensions.fixed(0.25F, 0.25F))
					.trackRangeBlocks(4).trackedUpdateRate(10)
					.build()
	);

	public static final EntityType<Fungenile> FUNGENILE = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("vistajourney", "fungenile"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, Fungenile::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);

	public static final Block RUBY_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0F, 3.0F));
	public static final Block DEEPSLATE_RUBY_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(4.5F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE));
	public static final Block RUBY_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MapColor.RED).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL));
	public static final Block OPAL_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0F, 3.0F));
	public static final Block DEEPSLATE_OPAL_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(4.5F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE));

	public static final Block STONE_BRICK_TILES = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(1.5F, 6.0F));

	public static final MushroomBlock VIOLET_WEBCAP_BLOCK = new MushroomBlock(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE).strength(0.2F).sounds(BlockSoundGroup.WOOD));
	public static final MushroomBlock VIOLET_WEBCAP_STEM = new MushroomBlock(FabricBlockSettings.of(Material.WOOD, MapColor.GRAY).strength(0.2F).sounds(BlockSoundGroup.WOOD));
	public static final MushroomPlantBlock VIOLET_WEBCAP = new MushroomPlantBlock(FabricBlockSettings.of(Material.PLANT, MapColor.GRAY).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS), () -> VistaJourney.HUGE_VIOLET_WEBCAP_CONFIG);

	public static final GlowGlob GLOW_GLOB = new GlowGlob(FabricBlockSettings.of(Material.DECORATION).lightLevel(15).sounds(BlockSoundGroup.HONEY));


	public static final Item ROOTED_MYCELIUM = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item COPPER_NUGGET = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item RUBY = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item OPAL = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item GLOW_GLOB_thisisliterallyjustsothaticanrenderglowglobsgod = new Item(new FabricItemSettings().group(null));

	public static final Item CORN = new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(4).saturationModifier(2).build()).group(ItemGroup.FOOD));
	public static final Item BAKED_CORN = new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(5).saturationModifier(7).build()).group(ItemGroup.FOOD));
	public static final StewItem FRUIT_SALAD = new StewItem(new FabricItemSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(7).build()).group(ItemGroup.FOOD));

	public static final GlowThrower GLOWTHROWER = new GlowThrower(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(32));

	public static final Item FUNGENILE_SPAWN_EGG = new SpawnEggItem(FUNGENILE, 13487306, 7757407, new Item.Settings().group(ItemGroup.MISC));

	///i hate ore gen
	private static final ConfiguredFeature<?, ?> RUBY_ORE_GENERATION = Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, VistaJourney.RUBY_ORE.getDefaultState(), 3));
	private static final ConfiguredFeature<?, ?> DEEPSLATE_RUBY_ORE_GENERATION = Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, VistaJourney.DEEPSLATE_RUBY_ORE.getDefaultState(), 3));
	private static final PlacedFeature RUBY_ORE_GENERATION_PLACE = RUBY_ORE_GENERATION.withPlacement(CountPlacementModifier.of(20), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(-16), YOffset.fixed(96)));
	private static final PlacedFeature DEEPSLATE_RUBY_ORE_GENERATION_PLACE = DEEPSLATE_RUBY_ORE_GENERATION.withPlacement(CountPlacementModifier.of(20), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(-16), YOffset.fixed(96)));
	private static final PlacedFeature EXTRA_RUBY_ORE_GENERATION_PLACE = RUBY_ORE_GENERATION.withPlacement(CountPlacementModifier.of(5), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(32), YOffset.fixed(48)));

	private static final ConfiguredFeature<?, ?> OPAL_ORE_GENERATION = Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_OVERWORLD, VistaJourney.OPAL_ORE.getDefaultState(), 3));
	private static final ConfiguredFeature<?, ?> DEEPSLATE_OPAL_ORE_GENERATION = Feature.ORE.configure(new OreFeatureConfig(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, VistaJourney.DEEPSLATE_OPAL_ORE.getDefaultState(), 3));
	private static final PlacedFeature OPAL_ORE_GENERATION_PLACE = OPAL_ORE_GENERATION.withPlacement(CountPlacementModifier.of(20), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(-16), YOffset.fixed(96)));
	private static final PlacedFeature DEEPSLATE_OPAL_ORE_GENERATION_PLACE = DEEPSLATE_OPAL_ORE_GENERATION.withPlacement(CountPlacementModifier.of(20), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(-16), YOffset.fixed(96)));
	private static final PlacedFeature EXTRA_OPAL_ORE_GENERATION_PLACE = OPAL_ORE_GENERATION.withPlacement(CountPlacementModifier.of(5), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(32), YOffset.fixed(48)));

	private static final Feature<DefaultFeatureConfig> FAIRY_RING_FEATURE = new FairyRingFeature(DefaultFeatureConfig.CODEC);
	private static final ConfiguredFeature<?, ?> FAIRY_RING_CONFIG = FAIRY_RING_FEATURE.configure(new DefaultFeatureConfig());
	private static final PlacedFeature FAIRY_RING_PLACED_FEATURE = FAIRY_RING_CONFIG.withPlacement(RarityFilterPlacementModifier.of(50), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(1));

	private static final Feature<HugeMushroomFeatureConfig> HUGE_VIOLET_WEBCAP = new HugeRedMushroomFeature(HugeMushroomFeatureConfig.CODEC);
	private static final ConfiguredFeature<?, ?> HUGE_VIOLET_WEBCAP_CONFIG = HUGE_VIOLET_WEBCAP.configure(new HugeMushroomFeatureConfig(BlockStateProvider.of(VistaJourney.VIOLET_WEBCAP_BLOCK.getDefaultState().with(MushroomBlock.DOWN, false)), BlockStateProvider.of(VistaJourney.VIOLET_WEBCAP_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), 2));
	private static final PlacedFeature HUGE_VIOLET_WEBCAP_PLACED = HUGE_VIOLET_WEBCAP_CONFIG.withPlacement(RarityFilterPlacementModifier.of(20), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(1));


	public static final StatusEffect PLAGUE_EFFECT = new Plague();
	public static final Potion PLAGUE_POTION = new Potion(new StatusEffectInstance(PLAGUE_EFFECT, 1800));

	@Override
	public void onInitialize() {
		GeckoLib.initialize();

        FabricDefaultAttributeRegistry.register(FUNGENILE, Fungenile.createFungenileAttributes());
		BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.MUSHROOM), SpawnGroup.CREATURE, FUNGENILE, 15, 0, 1);

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("vistajourney", "ruby_ore_generation"), RUBY_ORE_GENERATION);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("vistajourney", "ruby_ore_generation"), RUBY_ORE_GENERATION_PLACE);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.DESERT, Biome.Category.MESA), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("vistajourney", "ruby_ore_generation")));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("vistajourney", "deepslate_ruby_ore_generation"), DEEPSLATE_RUBY_ORE_GENERATION);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("vistajourney", "deepslate_ruby_ore_generation"), DEEPSLATE_RUBY_ORE_GENERATION_PLACE);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.DESERT, Biome.Category.MESA), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("vistajourney", "deepslate_ruby_ore_generation")));
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("vistajourney", "extra_ruby_ore_generation"), EXTRA_RUBY_ORE_GENERATION_PLACE);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.DESERT, Biome.Category.MESA), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("vistajourney", "extra_ruby_ore_generation")));

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("vistajourney", "opal_ore_generation"), OPAL_ORE_GENERATION);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("vistajourney", "opal_ore_generation"), OPAL_ORE_GENERATION_PLACE);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.ICY), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("vistajourney", "opal_ore_generation")));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("vistajourney", "deepslate_opal_ore_generation"), DEEPSLATE_OPAL_ORE_GENERATION);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("vistajourney", "deepslate_opal_ore_generation"), DEEPSLATE_OPAL_ORE_GENERATION_PLACE);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.ICY), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("vistajourney", "deepslate_opal_ore_generation")));
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("vistajourney", "extra_opal_ore_generation"), EXTRA_OPAL_ORE_GENERATION_PLACE);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.ICY), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("vistajourney", "extra_opal_ore_generation")));

		Registry.register(Registry.FEATURE, new Identifier("vistajourney", "fairy_ring_feature"), FAIRY_RING_FEATURE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("vistajourney", "fairy_ring_feature"), FAIRY_RING_CONFIG);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("vistajourney", "fairy_ring_feature"), FAIRY_RING_PLACED_FEATURE);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.FOREST), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("vistajourney", "fairy_ring_feature")));

		Registry.register(Registry.FEATURE, new Identifier("vistajourney", "huge_violet_webcap"), HUGE_VIOLET_WEBCAP);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("vistajourney", "huge_violet_webcap"), HUGE_VIOLET_WEBCAP_CONFIG);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("vistajourney", "huge_violet_webcap"), HUGE_VIOLET_WEBCAP_PLACED);
		BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.MUSHROOM), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier("vistajourney", "huge_violet_webcap")));


		Registry.register(Registry.BLOCK, new Identifier("vistajourney", "stone_brick_tiles"), STONE_BRICK_TILES);
		Registry.register(Registry.ITEM, new Identifier("vistajourney", "stone_brick_tiles"), new BlockItem(STONE_BRICK_TILES, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

		Registry.register(Registry.BLOCK, new Identifier("vistajourney", "violet_webcap_block"), VIOLET_WEBCAP_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("vistajourney", "violet_webcap_block"), new BlockItem(VIOLET_WEBCAP_BLOCK, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.BLOCK, new Identifier("vistajourney", "violet_webcap_stem"), VIOLET_WEBCAP_STEM);
		Registry.register(Registry.ITEM, new Identifier("vistajourney", "violet_webcap_stem"), new BlockItem(VIOLET_WEBCAP_STEM, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
		Registry.register(Registry.BLOCK, new Identifier("vistajourney", "violet_webcap"), VIOLET_WEBCAP);
		Registry.register(Registry.ITEM, new Identifier("vistajourney", "violet_webcap"), new BlockItem(VIOLET_WEBCAP, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

		Registry.register(Registry.BLOCK, new Identifier("vistajourney", "glow_glob"), GLOW_GLOB);

		Registry.register(Registry.BLOCK, new Identifier("vistajourney", "ruby_ore"), RUBY_ORE);
		Registry.register(Registry.ITEM, new Identifier("vistajourney", "ruby_ore"), new BlockItem(RUBY_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.BLOCK, new Identifier("vistajourney", "deepslate_ruby_ore"), DEEPSLATE_RUBY_ORE);
		Registry.register(Registry.ITEM, new Identifier("vistajourney", "deepslate_ruby_ore"), new BlockItem(DEEPSLATE_RUBY_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.BLOCK, new Identifier("vistajourney", "opal_ore"), OPAL_ORE);
		Registry.register(Registry.ITEM, new Identifier("vistajourney", "opal_ore"), new BlockItem(OPAL_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
		Registry.register(Registry.BLOCK, new Identifier("vistajourney", "deepslate_opal_ore"), DEEPSLATE_OPAL_ORE);
		Registry.register(Registry.ITEM, new Identifier("vistajourney", "deepslate_opal_ore"), new BlockItem(DEEPSLATE_OPAL_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));


		Registry.register(Registry.ITEM, new Identifier("vistajourney", "rooted_mycelium"), ROOTED_MYCELIUM);

		Registry.register(Registry.ITEM, new Identifier("vistajourney", "copper_nugget"), COPPER_NUGGET);

		Registry.register(Registry.ITEM, new Identifier("vistajourney", "ruby"), RUBY);
		Registry.register(Registry.ITEM, new Identifier("vistajourney", "opal"), OPAL);

		Registry.register(Registry.ITEM, new Identifier("vistajourney", "corn"), CORN);
		Registry.register(Registry.ITEM, new Identifier("vistajourney", "baked_corn"), BAKED_CORN);
		Registry.register(Registry.ITEM, new Identifier("vistajourney", "fruit_salad"), FRUIT_SALAD);

		Registry.register(Registry.ITEM, new Identifier("vistajourney", "glowthrower"), GLOWTHROWER);
		Registry.register(Registry.ITEM, new Identifier("vistajourney", "yourenotmeanttohavethis"), GLOW_GLOB_thisisliterallyjustsothaticanrenderglowglobsgod);

		Registry.register(Registry.ITEM, new Identifier("vistajourney", "fungenile_spawn_egg"), FUNGENILE_SPAWN_EGG);


		Registry.register(Registry.POTION, new Identifier("minecraft", "plague_potion"), PLAGUE_POTION);

		Registry.register(Registry.STATUS_EFFECT, new Identifier("vistajourney", "plague"), PLAGUE_EFFECT);


		PlayerHurtCallback.EVENT.register((player, source) -> {
			if (source.getSource() != null) {
				if (source.getSource().isLiving()) {
					if (player.hasStatusEffect(PLAGUE_EFFECT)) {
						((LivingEntity) source.getSource()).addStatusEffect(new StatusEffectInstance(PLAGUE_EFFECT, 5));
					}
				}
			}
			return ActionResult.PASS;
		});

		AttackEntityCallback.EVENT.register((PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) ->
		{
			if(entity.isLiving()){
				if (player.hasStatusEffect(PLAGUE_EFFECT)) {
					((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(PLAGUE_EFFECT, 1));
				}
			}
			return ActionResult.PASS;
		});
	}
}
