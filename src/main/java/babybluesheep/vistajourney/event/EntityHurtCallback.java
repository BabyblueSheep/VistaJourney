package babybluesheep.vistajourney.event;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;

import static net.fabricmc.fabric.api.event.EventFactory.createArrayBacked;

public interface EntityHurtCallback {

    Event<EntityHurtCallback> EVENT = createArrayBacked(EntityHurtCallback.class,
            (listeners) -> (entity, source) -> {
                for (EntityHurtCallback listener : listeners) {
                    ActionResult result = listener.interact(entity, source);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult interact(LivingEntity entity, DamageSource source);
}