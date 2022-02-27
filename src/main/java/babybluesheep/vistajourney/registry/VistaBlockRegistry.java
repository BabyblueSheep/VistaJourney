package babybluesheep.vistajourney.registry;

import babybluesheep.vistajourney.VistaJourney;
import babybluesheep.vistajourney.block.GlowGlob;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class VistaBlockRegistry
{
    public static final Block RUBY_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0F, 3.0F));
    public static final Block DEEPSLATE_RUBY_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(4.5F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE));
    public static final Block RUBY_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MapColor.RED).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL));
    public static final Block OPAL_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0F, 3.0F));
    public static final Block DEEPSLATE_OPAL_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(4.5F, 3.0F).sounds(BlockSoundGroup.DEEPSLATE));
    public static final Block OPAL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MapColor.PINK).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL));

    public static final Block STONE_BRICK_TILES = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(1.5F, 6.0F));

    public static final Block RED_SHROOMBRICKS = new Block(FabricBlockSettings.of(Material.STONE, MapColor.RED).requiresTool().strength(2.0F, 6.0F));
    public static final Block BROWN_SHROOMBRICKS = new Block(FabricBlockSettings.of(Material.STONE, MapColor.BROWN).requiresTool().strength(2.0F, 6.0F));
    public static final Block VIOLET_SHROOMBRICKS = new Block(FabricBlockSettings.of(Material.STONE, MapColor.TERRACOTTA_PURPLE).requiresTool().strength(2.0F, 6.0F));

    public static final MushroomBlock VIOLET_WEBCAP_BLOCK = new MushroomBlock(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE).strength(0.2F).sounds(BlockSoundGroup.WOOD));
    public static final MushroomBlock VIOLET_WEBCAP_STEM = new MushroomBlock(FabricBlockSettings.of(Material.WOOD, MapColor.GRAY).strength(0.2F).sounds(BlockSoundGroup.WOOD));
    public static final MushroomPlantBlock VIOLET_WEBCAP = new MushroomPlantBlock(FabricBlockSettings.of(Material.PLANT, MapColor.GRAY).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS), () -> VistaWorldRegistry.HUGE_VIOLET_WEBCAP_CONFIG);

    public static final GlowGlob GLOW_GLOB = new GlowGlob(FabricBlockSettings.of(Material.DECORATION).lightLevel(15).sounds(BlockSoundGroup.HONEY));

    public static void registerBlocks()
    {
        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "stone_brick_tiles"), STONE_BRICK_TILES);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "stone_brick_tiles"), new BlockItem(STONE_BRICK_TILES, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "violet_webcap_block"), VIOLET_WEBCAP_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "violet_webcap_block"), new BlockItem(VIOLET_WEBCAP_BLOCK, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "violet_webcap_stem"), VIOLET_WEBCAP_STEM);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "violet_webcap_stem"), new BlockItem(VIOLET_WEBCAP_STEM, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "violet_webcap"), VIOLET_WEBCAP);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "violet_webcap"), new BlockItem(VIOLET_WEBCAP, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "red_shroombricks"), RED_SHROOMBRICKS);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "red_shroombricks"), new BlockItem(RED_SHROOMBRICKS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "brown_shroombricks"), BROWN_SHROOMBRICKS);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "brown_shroombricks"), new BlockItem(BROWN_SHROOMBRICKS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "violet_shroombricks"), VIOLET_SHROOMBRICKS);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "violet_shroombricks"), new BlockItem(VIOLET_SHROOMBRICKS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "glow_glob"), GLOW_GLOB);

        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "ruby_ore"), RUBY_ORE);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "ruby_ore"), new BlockItem(RUBY_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "deepslate_ruby_ore"), DEEPSLATE_RUBY_ORE);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "deepslate_ruby_ore"), new BlockItem(DEEPSLATE_RUBY_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "ruby_block"), RUBY_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "ruby_block"), new BlockItem(RUBY_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "opal_ore"), OPAL_ORE);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "opal_ore"), new BlockItem(OPAL_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "deepslate_opal_ore"), DEEPSLATE_OPAL_ORE);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "deepslate_opal_ore"), new BlockItem(DEEPSLATE_OPAL_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier(VistaJourney.MOD_ID, "opal_block"), OPAL_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "opal_block"), new BlockItem(OPAL_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}