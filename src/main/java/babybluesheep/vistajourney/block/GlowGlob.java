package babybluesheep.vistajourney.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import java.util.Random;

public class GlowGlob extends Block implements Waterloggable{

    protected static final VoxelShape shape = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    public static final BooleanProperty waterLogged;

    public GlowGlob(Settings settings) {
        super(settings.nonOpaque().strength(0).noCollision());
        this.setDefaultState((((BlockState)this.stateManager.getDefaultState()).with(waterLogged, false)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(waterLogged);
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.createAndScheduleBlockTick(pos, this, 300);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.breakBlock(pos, false);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shape;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean)state.get(waterLogged)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(waterLogged) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return !world.isAir(pos.down());
    }

    static {
        waterLogged = Properties.WATERLOGGED;
    }
}