package babybluesheep.vistajourney.client.render;

import babybluesheep.vistajourney.entity.Fungenile;
import babybluesheep.vistajourney.model.FungenileModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FungenileRenderer extends GeoEntityRenderer<Fungenile>
{
    public FungenileRenderer(EntityRendererFactory.Context renderManager)
    {
        super(renderManager, new FungenileModel());
        this.shadowRadius  = 0.3F;
    }
}