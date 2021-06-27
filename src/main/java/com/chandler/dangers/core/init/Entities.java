package com.chandler.dangers.core.init;

import com.chandler.dangers.Dangers;
import com.chandler.dangers.common.entities.KeeperOfTheIceEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Entities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            Dangers.MOD_ID);

    public static final RegistryObject<EntityType<KeeperOfTheIceEntity>> KEEPER_OF_THE_ICE = ENTITY_TYPES.register("keeper_of_the_ice",
            () -> EntityType.Builder.of(KeeperOfTheIceEntity::new, EntityClassification.MISC).sized(1.0f, 1.0f)
                    .build(new ResourceLocation(Dangers.MOD_ID, "keeper_of_the_ice").toString()));

}
