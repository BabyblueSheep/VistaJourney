package babybluesheep.vistajourney.registry;

import babybluesheep.vistajourney.VistaJourney;
import babybluesheep.vistajourney.entity.Fungenile;
import babybluesheep.vistajourney.entity.GlowGlobThrown;
import babybluesheep.vistajourney.entity.Shearwater;
import babybluesheep.vistajourney.entity.ShearwaterEggThrown;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class VistaEntityRegistry {
    public static final EntityType<GlowGlobThrown> GLOW_GLOB_THROWN_ENTITY_TYPE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(VistaJourney.MOD_ID, "glow_glob_thrown"),
            FabricEntityTypeBuilder.<GlowGlobThrown>create(SpawnGroup.MISC, GlowGlobThrown::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<ShearwaterEggThrown> SHEARWATER_EGG_THROWN_ENTITY_TYPE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(VistaJourney.MOD_ID, "shearwater_egg_thrown"),
            FabricEntityTypeBuilder.<ShearwaterEggThrown>create(SpawnGroup.MISC, ShearwaterEggThrown::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<Fungenile> FUNGENILE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(VistaJourney.MOD_ID, "fungenile"),
            FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, Fungenile::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build()
    );
    public static final EntityType<Shearwater> SHEARWATER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(VistaJourney.MOD_ID, "shearwater"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, Shearwater::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    public static void registerEntities()
    {
        FabricDefaultAttributeRegistry.register(FUNGENILE, Fungenile.createFungenileAttributes());
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.MUSHROOM), SpawnGroup.AMBIENT, FUNGENILE, 15, 0, 1);
        FabricDefaultAttributeRegistry.register(SHEARWATER, Shearwater.createShearwaterAttributes());
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.BEACH), SpawnGroup.CREATURE, SHEARWATER, 25, 4, 8);
    }
}