package com.github.tacowasa059.participantsentitymod.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.client.CSteerBoatPacket;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class ParticipantsEntity extends CreatureEntity {
    public ParticipantsEntity(EntityType<ParticipantsEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
        experienceValue = 3;
        setNoAI(false);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
    }
    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        AttributeModifierMap.MutableAttribute attribute = MobEntity.func_233666_p_();
        attribute = attribute.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2);
        attribute = attribute.createMutableAttribute(Attributes.MAX_HEALTH, 20);
        attribute = attribute.createMutableAttribute(Attributes.ARMOR, 0);
        attribute = attribute.createMutableAttribute(Attributes.ATTACK_DAMAGE, 3);
        attribute = attribute.createMutableAttribute(Attributes.FOLLOW_RANGE, 16);
        return attribute;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, false));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, true) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return (double) (4.0 + entity.getWidth() * entity.getWidth());
            }
        });
        this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
        this.targetSelector.addGoal(4, new HurtByTargetGoal(this).setCallsForHelp());
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(6, new SwimGoal(this));
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    @Override
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.player.small_fall")),
                0.15f, 1);
    }

    @Override
    public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
    }

    @Override
    public net.minecraft.util.SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
    }
    @Override
    protected boolean canFitPassenger(Entity p_184219_1_) {
        return true;
    }
//    @Override
//    public void removePassengers() {
//        if(this.getPassengers().size()<2)super.removePassengers();
//        else{
//            int n=this.getPassengers().size();
//            Entity entity0=(Entity) this.getPassengers().get(0);
//            entity0.stopRiding();
//            for(int i = 1; i <n; ++i) {
//                Entity entity=(Entity) this.getPassengers().get(0);
//                entity.stopRiding();
//                entity.startRiding(entity0);
//            }
//        }
//
//    }

    @Override
    public ActionResultType getEntityInteractionResult(PlayerEntity sourceentity, Hand hand) {
        ActionResultType retval = ActionResultType.func_233537_a_(this.world.isRemote());
        super.getEntityInteractionResult(sourceentity, hand);
        sourceentity.startRiding(this);
        return retval;
    }
    @Override
    public void updatePassenger(Entity p_226266_1_) {
        if (this.isPassenger(p_226266_1_)) {
            double d0 = this.getPosY() + this.getMountedYOffset() + p_226266_1_.getYOffset();
            if (this.getPassengers().size() > 1) {
                int i = this.getPassengers().indexOf(p_226266_1_);
                d0+=i*1.3D;
            }
            IMoveCallback p_226266_2_=Entity::setPosition;
            p_226266_2_.accept(p_226266_1_, this.getPosX(), d0, this.getPosZ());
        }
    }
    @Override
    public void tick() {
        super.tick();
        this.doBlockCollisions();
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox().grow(0.20000000298023224, -0.009999999776482582, 0.20000000298023224), EntityPredicates.pushableBy(this));
        if (!list.isEmpty()) {
            boolean flag = !this.world.isRemote && !(this.getControllingPassenger() instanceof PlayerEntity);
            for(int j = 0; j < list.size(); ++j) {
                Entity entity = (Entity)list.get(j);
                if (!entity.isPassenger(this)) {
                    if (flag  && !entity.isPassenger() && entity instanceof ParticipantsEntity && !(entity instanceof WaterMobEntity) && !(entity instanceof PlayerEntity)) {
                        entity.startRiding(this);
                    } else {
                        this.applyEntityCollision(entity);
                    }
                }
            }
        }
    }

    @Override
    public void travel(Vector3d dir) {
        Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
        if (this.isBeingRidden()) {
            this.rotationYaw = entity.rotationYaw;
            this.prevRotationYaw = this.rotationYaw;
            this.rotationPitch = entity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.15F;
            this.renderYawOffset = entity.rotationYaw;
            this.rotationYawHead = entity.rotationYaw;
            this.stepHeight = 1.0F;
            if (entity instanceof LivingEntity) {
                this.setAIMoveSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float forward = ((LivingEntity) entity).moveForward;
                float strafe = 0;
                super.travel(new Vector3d(strafe, 0, forward));
            }
            this.prevLimbSwingAmount = this.limbSwingAmount;
            double d1 = this.getPosX() - this.prevPosX;
            double d0 = this.getPosZ() - this.prevPosZ;
            float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
            if (f1 > 1.0F)
                f1 = 1.0F;
            this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
            this.limbSwing += this.limbSwingAmount;
            return;
        }
        this.stepHeight = 0.5F;
        this.jumpMovementFactor = 0.02F;
        super.travel(dir);
    }
}
