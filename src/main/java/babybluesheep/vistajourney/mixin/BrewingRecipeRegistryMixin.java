package babybluesheep.vistajourney.mixin;

import babybluesheep.vistajourney.registry.VistaItemRegistry;
import babybluesheep.vistajourney.registry.VistaPotionRegistry;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingRecipeRegistryMixin {

    @Inject(method = "registerDefaults", at = @At(value = "TAIL"))
    private static void registerDefaultsInject(CallbackInfo ci) {
        BrewingRecipeRegistryInvoker.invokedRegisterPotionRecipe(Potions.AWKWARD, VistaItemRegistry.ROOTED_MYCELIUM, VistaPotionRegistry.PLAGUE_POTION);
    }
}