package babybluesheep.vistajourney.world;

import babybluesheep.vistajourney.registry.VistaBlockRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FairyRingFeature extends Feature<DefaultFeatureConfig> {
    public FairyRingFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        context.getConfig();
        BlockPos topPos1 = context.getWorld().getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, context.getOrigin());
        context.getWorld().setBlockState(topPos1.down(5), Blocks.EMERALD_ORE.getDefaultState(), 3);
        for (int x = -5; x <= 5; x = x + 1) {
            for (int z = -5; z <= 5; z = z + 1) {
                BlockPos currentPos = new BlockPos(context.getOrigin().getX() + x, context.getOrigin().getY(), context.getOrigin().getZ() + z);
                BlockPos topPos2 = context.getWorld().getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, currentPos);
                if((x*x+(z*z) > 25*0.9 && x*x+(z*z) < 25*1.1) && context.getWorld().isAir(topPos2) && context.getWorld().getBlockState(topPos2.down()) == Blocks.GRASS_BLOCK.getDefaultState()) {
                    context.getWorld().setBlockState(topPos2, VistaBlockRegistry.VIOLET_WEBCAP.getDefaultState(), 3);
                }
            }
        }
        return true;
    }

}