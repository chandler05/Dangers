package com.chandler.dangers.common.entities;

import com.chandler.dangers.common.blocks.SoftSnowBlock;
import com.chandler.dangers.common.goals.MoveMobToBlockGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class KeeperOfTheIceEntity extends MobEntity {

    public KeeperOfTheIceEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MoveMobToBlockGoal(this, this.getAttributeValue(Attributes.ATTACK_SPEED), 30) {
            @Override
            protected boolean isValidTarget(IWorldReader p_179488_1_, BlockPos p_179488_2_) {
                BlockState a = p_179488_1_.getBlockState(p_179488_2_);
                if (a.getBlock() instanceof SoftSnowBlock) {
                    return a.getValue(SoftSnowBlock.FOOTPRINTS);
                }

                return false;
            }
        });
    }

    public static AttributeModifierMap.MutableAttribute createLivingAttributes() {
        return LivingEntity.createLivingAttributes().add(Attributes.MAX_HEALTH, 1.0f).add(Attributes.KNOCKBACK_RESISTANCE, 100.0f).add(Attributes.ATTACK_DAMAGE, 1000.0f).add(Attributes.ATTACK_SPEED, 50.0f);
    }

    @Override
    protected int calculateFallDamage(float fallDistance, float p_225508_2_) {
        if (fallDistance > 2) {
            return 10000;
        } else {
            return 0;
        }
    }

    @Override
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.isInvulnerableTo(p_70097_1_)) {
            return false;
        } else if (this.level.isClientSide) {
            return false;
        } else if (this.isDeadOrDying()) {
            return false;
        } else if (p_70097_1_ != DamageSource.FALL) {
            return false;
        } else {
            return super.hurt(p_70097_1_, p_70097_2_);
        }
    }
}
