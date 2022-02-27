package babybluesheep.vistajourney;

import babybluesheep.vistajourney.event.PlayerHurtCallback;
import babybluesheep.vistajourney.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import software.bernie.geckolib3.GeckoLib;

public class VistaJourney implements ModInitializer {
	public static final String MOD_ID = "vistajourney";

	@Override
	public void onInitialize() {
		GeckoLib.initialize();

		VistaBlockRegistry.registerBlocks();
		VistaEntityRegistry.registerEntities();
		VistaItemRegistry.registerItems();
		VistaLootRegistry.registryLoot();
		VistaPotionRegistry.registerPotions();
		VistaWorldRegistry.registerFeatures();
		VistaSoundRegistry.registerSounds();

		PlayerHurtCallback.EVENT.register((player, source) -> {
			if (source.getSource() != null) {
				if (source.getSource().isLiving()) {
					if (player.hasStatusEffect(VistaPotionRegistry.PLAGUE_EFFECT)) {
						((LivingEntity) source.getSource()).addStatusEffect(new StatusEffectInstance(VistaPotionRegistry.PLAGUE_EFFECT, 5));
					}
				}
			}
			return ActionResult.PASS;
		});

		AttackEntityCallback.EVENT.register((PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) ->
		{
			if(entity.isLiving()){
				if (player.hasStatusEffect(VistaPotionRegistry.PLAGUE_EFFECT)) {
					((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(VistaPotionRegistry.PLAGUE_EFFECT, 1));
				}
			}

			ItemStack itemStack = player.getStackInHand(hand);
			if (itemStack.isOf(VistaItemRegistry.GLOWTHROWER)){

			}

			return ActionResult.PASS;
		});
	}
}
