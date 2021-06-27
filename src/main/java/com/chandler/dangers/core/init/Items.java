package com.chandler.dangers.core.init;

import com.chandler.dangers.Dangers;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dangers.MOD_ID);

    public static final RegistryObject<BlockItem> SOFT_SNOW = ITEMS.register("soft_snow", () -> new BlockItem(Blocks.SOFT_SNOW_BLOCK.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
}
