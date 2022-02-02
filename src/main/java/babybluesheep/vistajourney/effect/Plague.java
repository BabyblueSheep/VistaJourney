package babybluesheep.vistajourney.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

public class Plague extends StatusEffect {
    public Plague() {
        super(
                StatusEffectCategory.HARMFUL,
                10303662);
    }

    @Override
    public boolean canApplyUpdateEffect(int remainingTicks, int amplifier) {
        return remainingTicks % 25 == 0;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            entity.damage(DamageSource.MAGIC, amplifier);
        }
        if (entity instanceof MobEntity) {
            entity.damage(DamageSource.MAGIC, 2+2*amplifier);
        }
    }
}