package babybluesheep.vistajourney.entity;

import babybluesheep.vistajourney.registry.VistaEntityRegistry;
import babybluesheep.vistajourney.registry.VistaItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class ShearwaterEggThrown extends ThrownItemEntity {
    public ShearwaterEggThrown(EntityType<? extends ThrownItemEntity> $$0, World $$1) {
        super($$0, $$1);
    }

    public ShearwaterEggThrown(World world, LivingEntity owner) {
        super(VistaEntityRegistry.SHEARWATER_EGG_THROWN_ENTITY_TYPE, owner, world);
    }

    public ShearwaterEggThrown(World world, double x, double y, double z) {
        super(VistaEntityRegistry.SHEARWATER_EGG_THROWN_ENTITY_TYPE, x, y, z, world);
    }

    public void handleStatus(byte status) {
        if (status == 3) {
            for(int int0 = 0; int0 < 8; ++int0) {
                this.world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()), this.getX(), this.getY(), this.getZ(), ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D, ((double)this.random.nextFloat() - 0.5D) * 0.08D);
            }
        }

    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 0.0F);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            if (this.random.nextInt(8) == 0) {
                int int1 = 1;
                if (this.random.nextInt(32) == 0) {
                    int1 = 4;
                }

                for(int int2 = 0; int2 < int1; ++int2) {
                    Shearwater shearwater = VistaEntityRegistry.SHEARWATER.create(this.world);
                    shearwater.setBreedingAge(-24000);
                    shearwater.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                    this.world.spawnEntity(shearwater);
                }
            }

            this.world.sendEntityStatus(this, (byte)3);
            this.discard();
        }

    }

    protected Item getDefaultItem() {
        return VistaItemRegistry.SHEARWATER_EGG;
    }
}