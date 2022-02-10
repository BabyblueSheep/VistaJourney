package babybluesheep.vistajourney.registry;

import babybluesheep.vistajourney.VistaJourney;
import babybluesheep.vistajourney.item.GlowThrower;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class VistaItemRegistry
{
    public static final Item ROOTED_MYCELIUM = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    public static final Item COPPER_NUGGET = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    public static final Item RUBY = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item OPAL = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    public static final Item GLOW_GLOB_ITEM = new Item(new FabricItemSettings().group(null));

    public static final Item CORN = new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(4).saturationModifier(2).build()).group(ItemGroup.FOOD));
    public static final Item BAKED_CORN = new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(5).saturationModifier(7).build()).group(ItemGroup.FOOD));
    public static final StewItem FRUIT_SALAD = new StewItem(new FabricItemSettings().food(new FoodComponent.Builder().hunger(6).saturationModifier(7).build()).group(ItemGroup.FOOD));

    public static final GlowThrower GLOWTHROWER = new GlowThrower(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1).maxDamage(32));

    public static final Item FUNGENILE_SPAWN_EGG = new SpawnEggItem(VistaEntityRegistry.FUNGENILE, 13487306, 7757407, new Item.Settings().group(ItemGroup.MISC));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "rooted_mycelium"), ROOTED_MYCELIUM);

        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "copper_nugget"), COPPER_NUGGET);

        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "ruby"), RUBY);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "opal"), OPAL);

        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "corn"), CORN);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "baked_corn"), BAKED_CORN);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "fruit_salad"), FRUIT_SALAD);

        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "glowthrower"), GLOWTHROWER);
        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "glowglob"), GLOW_GLOB_ITEM);

        Registry.register(Registry.ITEM, new Identifier(VistaJourney.MOD_ID, "fungenile_spawn_egg"), FUNGENILE_SPAWN_EGG);
    }
}