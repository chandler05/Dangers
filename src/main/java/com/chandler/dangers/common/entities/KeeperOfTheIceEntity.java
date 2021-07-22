package com.chandler.dangers.common.entities;

import com.chandler.dangers.common.blocks.SoftSnowBlock;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import com.chandler.dangers.common.goals.MoveMobToBlockGoal;
import com.chandler.dangers.core.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class KeeperOfTheIceEntity extends MobEntity {
    private static final DataParameter<Integer> BELL_TICK = EntityDataManager.defineId(KeeperOfTheIceEntity.class, DataSerializers.INT);
    private static final DataParameter<BlockPos> HOME_POS = EntityDataManager.defineId(KeeperOfTheIceEntity.class, DataSerializers.BLOCK_POS);
    private boolean canMove = false;

    public KeeperOfTheIceEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MoveMobToBlockGoal(this, this.getAttributeValue(Attributes.MOVEMENT_SPEED), 6, 2) {
            @Override
            protected boolean isValidTarget(IWorldReader p_179488_1_, BlockPos p_179488_2_) {
                BlockState a = p_179488_1_.getBlockState(p_179488_2_);
                if (a.getBlock() instanceof SoftSnowBlock) {
                    return a.getValue(SoftSnowBlock.FOOTPRINTS) && p_179488_1_.getBlockState(p_179488_2_.above()).isPathfindable(p_179488_1_, p_179488_2_, PathType.LAND);
                }

                return false;
            }
        });
        /*this.goalSelector.addGoal(1, new MoveMobToBlockGoal(this, this.getAttributeValue(Attributes.ATTACK_SPEED), 30) {
            @Override
            protected boolean isValidTarget(IWorldReader p_179488_1_, BlockPos p_179488_2_) {
                if (p_179488_2_ == getHomePos()) {
                    return true;
                }
                return false;
            }
        });*/
    }

    public static AttributeModifierMap.MutableAttribute createLivingAttributes() {
        return LivingEntity.createLivingAttributes().add(Attributes.MAX_HEALTH, 1.0f).add(Attributes.KNOCKBACK_RESISTANCE, 100.0f).add(Attributes.ATTACK_DAMAGE, 1000.0f).add(Attributes.MOVEMENT_SPEED, 1.5f).add(Attributes.FOLLOW_RANGE, 0.0f);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BELL_TICK, 600);
        this.entityData.define(HOME_POS, this.getOnPos());
    }

    /*@Override
    public void travel(Vector3d p_213352_1_) {
        if (this.canMove) {
            super.travel(p_213352_1_);
        }
    }*/

    public void tick() {
        if (!this.level.isClientSide) {
            if (this.getHomePos() == null) {
                this.setHomePos(this.blockPosition());
            }
            if (this.getBellTick() > 0) {
                int newBellTick = this.getBellTick() - 1;
                this.setBellTick(newBellTick);
            } else {
                playSound(SoundEvents.BELL_BLOCK, 1.0f, 1.0f);
                this.canMove = true;
                this.setBellTick(600);
            }
            if (this.getBellTick() < 500 && this.canMove && this.getHomePos() == this.blockPosition()) {
                this.canMove = false;
            }
            BlockState below = this.level.getBlockState(this.getOnPos());
            if (below.getBlock() instanceof SoftSnowBlock && below.getValue(SoftSnowBlock.FOOTPRINTS)) {
                below.setValue(SoftSnowBlock.FOOTPRINTS, false);
            }
        }
        super.tick();
    }

    public int getBellTick() {
        return this.entityData.get(BELL_TICK);
    }

    public void setBellTick(int tick) {
        this.entityData.set(BELL_TICK, tick);
    }

    public BlockPos getHomePos() { return this.entityData.get(HOME_POS); }

    public void setHomePos(BlockPos pos) { this.entityData.set(HOME_POS, pos); }

    @Override
    protected int calculateFallDamage(float fallDistance, float p_225508_2_) {
        if (fallDistance > 2) {
            return (int) Math.ceil(this.getHealth());
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
        } else if (p_70097_1_ != DamageSource.FALL && p_70097_1_ != DamageSource.OUT_OF_WORLD) {
            return false;
        } else {
            return super.hurt(p_70097_1_, p_70097_2_);
        }
    }
}
