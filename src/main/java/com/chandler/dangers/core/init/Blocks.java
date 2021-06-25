package com.chandler.dangers.core.init;

import com.chandler.dangers.Dangers;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Blocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Dangers.MOD_ID);

    public static final RegistryObject<Block> SOFT_SNOW = BLOCKS.register("soft_snow",
            () -> new Block(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.SNOW_BLOCK)));
}
