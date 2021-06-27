package com.chandler.dangers.common.blocks;

import net.minecraft.block.*;
import net.minecraft.state.BooleanProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SoftSnowBlock extends Block {
    public static final BooleanProperty FOOTPRINTS = BooleanProperty.create("footprints");

    public SoftSnowBlock() {
        super(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.SNOW_BLOCK));
        this.registerDefaultState(this.stateDefinition.any().setValue(FOOTPRINTS, Boolean.valueOf(false)));
    }

    @Override
    public void stepOn(World p_176199_1_, BlockPos p_176199_2_, Entity p_176199_3_) {
        if (!(p_176199_3_ instanceof ArmorStandEntity) && p_176199_1_.getBlockState(p_176199_2_.above()).isAir()) {
            p_176199_1_.setBlock(p_176199_2_, this.stateDefinition.any().setValue(FOOTPRINTS, Boolean.valueOf(true)), 2);
        }
        super.stepOn(p_176199_1_, p_176199_2_, p_176199_3_);

    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FOOTPRINTS);
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isClientSide) {
            if (worldIn.getBlockState(pos.above()).is(Blocks.SNOW) || worldIn.getBlockState(pos.above()).getBlock() instanceof SoftSnowBlock || worldIn.getBlockState(pos.above()).is(Blocks.SNOW_BLOCK)) {
                worldIn.setBlock(pos, state.setValue(FOOTPRINTS, Boolean.valueOf(false)),2);
            }
        }

    }

    public void randomTick(BlockState p_225542_1_, ServerWorld p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
        if (p_225542_2_.getBrightness(LightType.BLOCK, p_225542_3_) > 11) {
            dropResources(p_225542_1_, p_225542_2_, p_225542_3_);
            p_225542_2_.removeBlock(p_225542_3_, false);
        }

    }
}
