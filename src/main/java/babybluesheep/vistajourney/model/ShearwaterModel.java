package babybluesheep.vistajourney.model;

import babybluesheep.vistajourney.entity.Shearwater;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShearwaterModel extends AnimatedGeoModel<Shearwater>
{
    @Override
    public Identifier getModelLocation(Shearwater object)
    {
        if(object.isBaby()) {
            return new Identifier("vistajourney", "geo/entity/baby_shearwater.geo.json");
        }
        else {
            return new Identifier("vistajourney", "geo/entity/shearwater.geo.json");
        }
    }

    @Override
    public Identifier getTextureLocation(Shearwater object)
    {
        return new Identifier("vistajourney", "textures/entity/shearwater.png");
    }

    @Override
    public Identifier getAnimationFileLocation(Shearwater object)
    {
        return new Identifier("vistajourney", "animations/entity/shearwater.animation.json");
    }
}