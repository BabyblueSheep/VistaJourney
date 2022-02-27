package babybluesheep.vistajourney.registry;

import babybluesheep.vistajourney.VistaJourney;
import babybluesheep.vistajourney.item.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class VistaItemRegistry
{
    public static ClaymoreItem CLAYMORE = new ClaymoreItem(ClaymoreMaterial.INSTANCE, 9, -3.3F, new Item.Settings().group(ItemGroup.COMBAT));
    public static SickleItem SICKLE = new SickleItem(SickleMaterial.INSTANCE, 6, -2.2F, new Item.Settings().group(ItemGroup.COMBAT));
    public static DaggerItem DAGGER = new DaggerItem(DaggerMaterial.INSTANCE, 3, -1.5F, new Item.Settings().group(ItemGroup.COMBAT));

    public static final Item ROOTED_MYCELIUM = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    public static final ShearwaterEgg SHEARWATER_EGG = new ShearwaterEgg(new FabricItemSettings().group(ItemGroup.MISC));

    public static final Item COPPER_NUGGET = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    public static final Item RUBY = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item OPAL = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    public static final Item GLOW_GLOB_ITEM = new Item(new FabricItemSettings().group(null));

    public static final Item RAW_SHEARWATER = new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(1).saturationModifier(0.3F).snack().meat().build()).group(ItemGroup.FOOD));
    public static final Item COOKED_SHEARWATER = new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.6F).snack().meat().build()).group(ItemGroup.FOOD));
    public static final Item CORN = new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(3).saturationModifier(0.3F).build()).group(ItemGroup.FOOD));
    public static final Item BAKED_CORN = new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(5).saturationModifier(0.5F).build()).group(ItemGroup.FOOD));
    public static final StewItem FRUIT_SALAD = new StewItem(new FabricItemSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(0.7F).build()).group(ItemGroup.FOOD));

    public static final GlowThrower GLOWTHROWER = new GlowThrower(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(32));

    public static final Item FUNGENILE_SPAWN_EGG = new SpawnEggItem(VistaEntityRegistry.FUNGENILE, 13487306, 7757407, new Item.Settings().group(ItemGroup.MISC));
    public static final Item SHEARWATER_SPAWN_EGG = new SpawnEggItem(VistaEntityRegistry.SHEARWATER, 12633800, 14066099, new Item.Settings().group(ItemGroup.MISC));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "claymore"), CLAYMORE);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "sickle"), SICKLE);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "dagger"), DAGGER);

        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "shearwater_egg"), SHEARWATER_EGG);

        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "rooted_mycelium"), ROOTED_MYCELIUM);

        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "copper_nugget"), COPPER_NUGGET);

        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "ruby"), RUBY);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "opal"), OPAL);

        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "raw_shearwater"), RAW_SHEARWATER);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "cooked_shearwater"), COOKED_SHEARWATER);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "corn"), CORN);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "baked_corn"), BAKED_CORN);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "fruit_salad"), FRUIT_SALAD);

        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "glowthrower"), GLOWTHROWER);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "glowglob"), GLOW_GLOB_ITEM);

        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "fungenile_spawn_egg"), FUNGENILE_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "shearwater_spawn_egg"), SHEARWATER_SPAWN_EGG);
    }
}