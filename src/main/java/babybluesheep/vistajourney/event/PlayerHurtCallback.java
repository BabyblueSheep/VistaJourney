package babybluesheep.vistajourney.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

import static net.fabricmc.fabric.api.event.EventFactory.createArrayBacked;

public interface PlayerHurtCallback {

    Event<PlayerHurtCallback> EVENT = createArrayBacked(PlayerHurtCallback.class,
            (listeners) -> (player, source) -> {
                for (PlayerHurtCallback listener : listeners) {
                    ActionResult result = listener.interact(player, source);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult interact(PlayerEntity player, DamageSource source);
}