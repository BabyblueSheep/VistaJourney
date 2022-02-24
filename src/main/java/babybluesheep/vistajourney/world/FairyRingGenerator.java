package babybluesheep.vistajourney.world;

import babybluesheep.vistajourney.registry.VistaBlockRegistry;
import babybluesheep.vistajourney.registry.VistaWorldRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;

public class FairyRingGenerator {
    public FairyRingGenerator(){
    }

    @Environment(EnvType.CLIENT)
    public static class Piece extends StructurePiece {
        public Piece(StructureContext structureContext, NbtCompound nbt) {
            super(VistaWorldRegistry.FAIRY_RING_PIECE, nbt);
        }

        public Piece(BlockPos blockPos) {
            super(VistaWorldRegistry.FAIRY_RING_PIECE, 0, new BlockBox(blockPos));
        }

        protected void writeNbt(StructureContext context, NbtCompound nbt) {
        }

        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pos) {
            BlockPos topPos = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, new BlockPos(pos.getX(), 0, pos.getZ()));
            BlockPos chestPos = new BlockPos(topPos.getX(), topPos.getY() - 5 - random.nextInt(3), topPos.getZ());
            this.addChest(world, chunkBox, random, chestPos, LootTables.BURIED_TREASURE_CHEST, (BlockState)null);

            for (int x = -5; x <= 5; x = x + 1) {
                for (int z = -5; z <= 5; z = z + 1) {
                    topPos = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR_WG, new BlockPos(pos.getX() + x, 0, pos.getZ() + z));
                    if((x*x+(z*z) > 25*0.9 && x*x+(z*z) < 25*1.1) && world.isAir(topPos) && world.getBlockState(topPos.down()) == Blocks.GRASS_BLOCK.getDefaultState()) {
                        world.setBlockState(topPos, VistaBlockRegistry.VIOLET_WEBCAP.getDefaultState(), 3);
                    }
                }
            }
        }
    }
}