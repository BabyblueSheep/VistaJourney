package babybluesheep.vistajourney.registry;

import babybluesheep.vistajourney.VistaJourney;
import babybluesheep.vistajourney.enchantment.BleedingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class VistaEnchantmentRegistry
{
    public static final Enchantment BLEEDING = new BleedingEnchantment(Enchantment.Rarity.UNCOMMON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});

    public static void registerEnchs() {
        Registry.register(Registry.ENCHANTMENT, new Identifier(VistaJourney.MOD_ID, "bleeding_enchantment"), BLEEDING);
    }
}