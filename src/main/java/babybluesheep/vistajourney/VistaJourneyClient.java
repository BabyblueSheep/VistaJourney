package babybluesheep.vistajourney;

import babybluesheep.vistajourney.client.render.FungenileRenderer;
import babybluesheep.vistajourney.entity.EntitySpawnPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class VistaJourneyClient implements ClientModInitializer {

    public static final Identifier PacketID = new Identifier("vistajourney", "spawn_packet");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(VistaJourney.GLOW_GLOB, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VistaJourney.VIOLET_WEBCAP, RenderLayer.getCutout());

        EntityRendererRegistry.INSTANCE.register(VistaJourney.GLOW_GLOB_THROWN_ENTITY_TYPE, (context) ->
                new FlyingItemEntityRenderer(context));
        receiveEntityPacket();

        EntityRendererRegistry.INSTANCE.register(VistaJourney.FUNGENILE,
                (entityRenderDispatcher) -> new FungenileRenderer(entityRenderDispatcher));


        FabricModelPredicateProviderRegistry.register(VistaJourney.GLOWTHROWER, new Identifier("pull"), (itemStack, clientWorld, livingEntity, integer) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
        });

        FabricModelPredicateProviderRegistry.register(VistaJourney.GLOWTHROWER, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, integer) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
        });
    }

    public void receiveEntityPacket() {
        ClientSidePacketRegistry.INSTANCE.register(PacketID, (ctx, byteBuf) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUuid();
            int entityId = byteBuf.readVarInt();
            Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            ctx.getTaskQueue().execute(() -> {
                if (MinecraftClient.getInstance().world == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity e = et.create(MinecraftClient.getInstance().world);
                if (e == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                e.updateTrackedPosition(pos);
                e.setPos(pos.x, pos.y, pos.z);
                e.setPitch(pitch);
                e.setYaw(yaw);
                e.setId(entityId);
                e.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, e);
            });
        });
    }
}