package babybluesheep.vistajourney.mixin;

import babybluesheep.vistajourney.event.EntityHurtCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "damage", at = @At(value = "TAIL"), cancellable = true)
    private void damageInject(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        ActionResult result = EntityHurtCallback.EVENT.invoker().interact((LivingEntity) (Object) this, source);

        if(result == ActionResult.FAIL) {
            info.cancel();
        }
    }
}