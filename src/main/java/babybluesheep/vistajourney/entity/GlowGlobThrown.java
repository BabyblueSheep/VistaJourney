package babybluesheep.vistajourney.entity;

import babybluesheep.vistajourney.entity.EntitySpawnPacket;
import babybluesheep.vistajourney.VistaJourney;
import babybluesheep.vistajourney.VistaJourneyClient;
import babybluesheep.vistajourney.block.GlowGlob;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.StateManager;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class GlowGlobThrown extends ThrownItemEntity {

    public GlowGlobThrown(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public GlowGlobThrown(World world, LivingEntity owner) {
        super(VistaJourney.GLOW_GLOB_THROWN_ENTITY_TYPE, owner, world);
    }

    public GlowGlobThrown(World world, double x, double y, double z) {
        super(VistaJourney.GLOW_GLOB_THROWN_ENTITY_TYPE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return VistaJourney.GLOW_GLOB_thisisliterallyjustsothaticanrenderglowglobsgod;
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
            this.world.setBlockState(this.getBlockPos(), VistaJourney.GLOW_GLOB.getDefaultState());
        }
    }
}