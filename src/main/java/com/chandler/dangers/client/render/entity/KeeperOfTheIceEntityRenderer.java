
package com.chandler.dangers.client.render.entity;

import com.chandler.dangers.Dangers;
import com.chandler.dangers.client.render.model.KeeperOfTheIceEntityModel;
import com.chandler.dangers.common.entities.KeeperOfTheIceEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class KeeperOfTheIceEntityRenderer extends MobRenderer<KeeperOfTheIceEntity, KeeperOfTheIceEntityModel<KeeperOfTheIceEntity>> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(Dangers.MOD_ID, "textures/entity/keeperoftheice/keeper_of_the_ice.png");

    public KeeperOfTheIceEntityRenderer(EntityRendererManager manager) {
        super(manager, new KeeperOfTheIceEntityModel<>(), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(KeeperOfTheIceEntity entity) {
        return TEXTURE;
    }
}