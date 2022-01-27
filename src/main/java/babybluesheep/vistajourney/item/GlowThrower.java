package babybluesheep.vistajourney.item;

import babybluesheep.vistajourney.entity.GlowGlobThrown;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class GlowThrower extends Item {

    public GlowThrower(Settings settings) {
        super(settings);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity)user;
            int useTime = this.getMaxUseTime(stack) - remainingUseTicks;
            if (!world.isClient) {
                stack.damage(1, playerEntity, (p) -> {
                    p.sendToolBreakStatus(user.getActiveHand());
                });
                GlowGlobThrown glowGlobThrown = new GlowGlobThrown(world, user);
                glowGlobThrown.setVelocity(user, user.getPitch(), user.getYaw(), -10.0F, 0.5F+0.012F*useTime, 1.0F);
                world.spawnEntity(glowGlobThrown);
                playerEntity.getItemCooldownManager().set(this, 10);
            }
            playerEntity.playSound(SoundEvents.BLOCK_LEVER_CLICK, 1.0F, 1.0F);
        }
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack $$3 = user.getStackInHand(hand);
        if ($$3.getDamage() >= $$3.getMaxDamage() - 1) {
            return TypedActionResult.fail($$3);
        }
        else {
            user.setCurrentHand(hand);
            return TypedActionResult.pass($$3);
        }
    }
}