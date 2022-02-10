package babybluesheep.vistajourney.registry;

import babybluesheep.vistajourney.effect.Plague;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class VistaPotionRegistry {
    public static final StatusEffect PLAGUE_EFFECT = new Plague();
    public static final Potion PLAGUE_POTION = new Potion(new StatusEffectInstance(PLAGUE_EFFECT, 1800));

    public static void registerPotions()
    {
        Registry.register(Registry.POTION, new Identifier("minecraft", "plague_potion"), PLAGUE_POTION);

        Registry.register(Registry.STATUS_EFFECT, new Identifier("vistajourney", "plague"), PLAGUE_EFFECT);
    }
}