package babybluesheep.vistajourney.world;

import babybluesheep.vistajourney.VistaJourney;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

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
                if((x*x+(z*z) == 25) && context.getWorld().isAir(topPos2) && context.getWorld().getBlockState(topPos2.down()) == Blocks.GRASS_BLOCK.getDefaultState()) {
                    context.getWorld().setBlockState(topPos2, VistaJourney.VIOLET_WEBCAP.getDefaultState(), 3);
                }
            }
        }
        return true;
    }

}