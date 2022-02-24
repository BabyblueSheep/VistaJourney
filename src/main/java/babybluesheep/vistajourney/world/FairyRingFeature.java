package babybluesheep.vistajourney.world;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class FairyRingFeature extends StructureFeature<DefaultFeatureConfig> {
    public FairyRingFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec, StructureGeneratorFactory.simple(StructureGeneratorFactory.checkForBiomeOnTop(Heightmap.Type.WORLD_SURFACE_WG), FairyRingFeature::addPieces));
    }

    private static void addPieces(StructurePiecesCollector collector, net.minecraft.structure.StructurePiecesGenerator.Context<DefaultFeatureConfig> context) {
        BlockPos blockPos = new BlockPos(context.chunkPos().getOffsetX(0), 0, context.chunkPos().getOffsetZ(0));
        collector.addPiece(new FairyRingGenerator.Piece(blockPos));
    }
}