package com.chandler.dangers.common.blocks;

import net.minecraft.state.BooleanProperty;
import net.minecraft.block.Block;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoftSnow extends SnowBlock {

    public static final BooleanProperty FOOTPRINTS = BooleanProperty.create("footprints");

    public SoftSnow(Properties p_i48440_1_) {
        super(p_i48440_1_);
        this.registerDefaultState(this.defaultBlockState().setValue(FOOTPRINTS, Boolean.valueOf(false)));
    }

    @Override
    public void stepOn(World p_176199_1_, BlockPos p_176199_2_, Entity p_176199_3_) {
        if (!(p_176199_3_ instanceof ArmorStandEntity)) {
            p_176199_1_.setBlock(p_176199_2_, this.defaultBlockState().setValue(FOOTPRINTS, Boolean.valueOf(true)), 2);
        }
        super.stepOn(p_176199_1_, p_176199_2_, p_176199_3_);
    }
}
