package babybluesheep.vistajourney.registry;

import babybluesheep.vistajourney.VistaJourney;
import babybluesheep.vistajourney.effect.Bleeding;
import babybluesheep.vistajourney.effect.Plague;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class VistaPotionRegistry {
    public static final StatusEffect PLAGUE_EFFECT = new Plague();
    public static final Potion PLAGUE_POTION = new Potion(new StatusEffectInstance(PLAGUE_EFFECT, 1800));

    public static final StatusEffect BLEEDING_EFFECT = new Bleeding();

    public static void registerPotions()
    {
        Registry.register(Registry.POTION, new Identifier("minecraft", "plague_potion"), PLAGUE_POTION);

        Registry.register(Registry.STATUS_EFFECT, new Identifier(VistaJourney.MOD_ID, "plague"), PLAGUE_EFFECT);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(VistaJourney.MOD_ID, "bleeding"), BLEEDING_EFFECT);
    }
}