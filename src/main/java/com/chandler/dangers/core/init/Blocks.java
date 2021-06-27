package com.chandler.dangers.core.init;

import com.chandler.dangers.Dangers;
import com.chandler.dangers.common.blocks.SoftSnowBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Blocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Dangers.MOD_ID);

    public static final RegistryObject<Block> SOFT_SNOW_BLOCK = BLOCKS.register("soft_snow",
            SoftSnowBlock::new);
}
