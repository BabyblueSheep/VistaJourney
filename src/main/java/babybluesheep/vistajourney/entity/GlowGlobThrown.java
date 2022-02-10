package babybluesheep.vistajourney.entity;

import babybluesheep.vistajourney.VistaJourneyClient;
import babybluesheep.vistajourney.registry.VistaBlockRegistry;
import babybluesheep.vistajourney.registry.VistaEntityRegistry;
import babybluesheep.vistajourney.registry.VistaItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class GlowGlobThrown extends ThrownItemEntity {

    public GlowGlobThrown(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public GlowGlobThrown(World world, LivingEntity owner) {
        super(VistaEntityRegistry.GLOW_GLOB_THROWN_ENTITY_TYPE, owner, world);
    }

    public GlowGlobThrown(World world, double x, double y, double z) {
        super(VistaEntityRegistry.GLOW_GLOB_THROWN_ENTITY_TYPE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return VistaItemRegistry.GLOW_GLOB_ITEM;
    }

    protected float getGravity() {
        return 0.05F;
    }

    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, VistaJourneyClient.PacketID);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.kill();
        }

    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if ((this.world.isAir(getBlockPos()) || this.world.isWater(getBlockPos())) && !this.world.isAir(getBlockPos().down())){
            this.world.setBlockState(this.getBlockPos(), VistaBlockRegistry.GLOW_GLOB.getDefaultState());
        }
    }
}