package babybluesheep.vistajourney.registry;

import babybluesheep.vistajourney.VistaJourney;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class VistaLootRegistry
{
    private static final Identifier VILLAGE_PLAINS_CHEST_LOOT = LootTables.VILLAGE_PLAINS_CHEST;

    public static final Identifier FAIRY_RING_CHEST_LOOT  = new Identifier(VistaJourney.MOD_ID, "chests/fairy_ring");

    public static void registryLoot()
    {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (VILLAGE_PLAINS_CHEST_LOOT.equals(id)) {
                table.pool(FabricLootPoolBuilder.builder()
                        .rolls(UniformLootNumberProvider.create(0, 2))
                        .bonusRolls(UniformLootNumberProvider.create(0, 2))
                        .with(ItemEntry.builder(VistaItemRegistry.CORN)));
            }
        });
    }
}