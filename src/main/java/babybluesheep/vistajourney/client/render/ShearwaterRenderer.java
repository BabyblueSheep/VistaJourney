package babybluesheep.vistajourney.client.render;

import babybluesheep.vistajourney.entity.Shearwater;
import babybluesheep.vistajourney.model.ShearwaterModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ShearwaterRenderer extends GeoEntityRenderer<Shearwater>
{
    public ShearwaterRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new ShearwaterModel());
        this.shadowRadius  = 0.5F;
    }

    @Override
    public RenderLayer getRenderType(Shearwater animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(this.getTextureLocation(animatable));
    }
}