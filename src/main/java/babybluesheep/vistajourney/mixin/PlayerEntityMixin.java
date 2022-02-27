package babybluesheep.vistajourney.mixin;

import babybluesheep.vistajourney.event.PlayerHurtCallback;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Shadow @Final protected static TrackedData<Byte> PLAYER_MODEL_PARTS;

    @Inject(method = "damage", at = @At(value = "TAIL"), cancellable = true)
    private void damageInject(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        ActionResult result = PlayerHurtCallback.EVENT.invoker().interact((PlayerEntity) (Object) this, source);

        if(result == ActionResult.FAIL) {
            info.cancel();
        }

    }
}