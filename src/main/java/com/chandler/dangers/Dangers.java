package com.chandler.dangers;

import com.chandler.dangers.common.entities.KeeperOfTheIceEntity;
import com.chandler.dangers.core.init.Blocks;
import com.chandler.dangers.core.init.Entities;
import com.chandler.dangers.core.init.Items;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Dangers.MOD_ID)
public class Dangers
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "chandlers_dangers";

    public Dangers() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        Items.ITEMS.register(bus);
        Blocks.BLOCKS.register(bus);
        Entities.ENTITY_TYPES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SuppressWarnings("deprecation")
    private void setup(final FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(Entities.KEEPER_OF_THE_ICE.get(), KeeperOfTheIceEntity.createLivingAttributes().build());
        });
    }
}
