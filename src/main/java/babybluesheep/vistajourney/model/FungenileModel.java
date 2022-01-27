package babybluesheep.vistajourney.model;

import babybluesheep.vistajourney.entity.Fungenile;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FungenileModel extends AnimatedGeoModel<Fungenile>
{
    @Override
    public Identifier getModelLocation(Fungenile object)
    {
        return new Identifier("vistajourney", "geo/entity/fungenile.geo.json");
    }

    @Override
    public Identifier getTextureLocation(Fungenile object)
    {
        if (object.isSheared()) {
            return new Identifier("vistajourney", "textures/entity/skinned_fungenile.png");
        }
        else {
            return new Identifier("vistajourney", "textures/entity/fungenile.png");
        }
    }

    @Override
    public Identifier getAnimationFileLocation(Fungenile object)
    {
        return new Identifier("vistajourney", "animations/entity/fungenile.animation.json");
    }
}