package babybluesheep.vistajourney.effect;

import babybluesheep.vistajourney.registry.VistaPotionRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;

public class Bleeding extends StatusEffect {
    public Bleeding() {
        super(
                StatusEffectCategory.HARMFUL,
                14230821);
    }

    @Override
    public boolean canApplyUpdateEffect(int remainingTicks, int amplifier) {
        return remainingTicks % 40 == 0;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        int poisonAmplifier;
        int witherAmplifier;
        int plagueAmplifier;
        if (entity.hasStatusEffect(StatusEffects.POISON)){
            poisonAmplifier = 1;
        }
        else {
            poisonAmplifier = 0;
        }
        if (entity.hasStatusEffect(StatusEffects.WITHER)){
            witherAmplifier = 1;
        }
        else {
            witherAmplifier = 0;
        }
        if (entity.hasStatusEffect(VistaPotionRegistry.PLAGUE_EFFECT)){
            plagueAmplifier = 1;
        }
        else {
            plagueAmplifier = 0;
        }

        entity.damage(DamageSource.MAGIC, 2+amplifier+poisonAmplifier+witherAmplifier+plagueAmplifier);
    }
}