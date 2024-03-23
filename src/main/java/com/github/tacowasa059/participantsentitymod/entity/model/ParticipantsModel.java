package com.github.tacowasa059.participantsentitymod.entity.model;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.github.tacowasa059.participantsentitymod.entity.ParticipantsEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticipantsModel<P extends CreatureEntity> extends AgeableModel<ParticipantsEntity> {
	public BipedModel.ArmPose leftArmPose;
	public BipedModel.ArmPose rightArmPose;
	public boolean isSneak;

	public float swimAnimation;
	private final ModelRenderer bipedHead;
	private final ModelRenderer bipedHeadwear;
	private final ModelRenderer bipedBody;
	private final ModelRenderer bipedRightArm;
	private final ModelRenderer bipedLeftArm;
	private final ModelRenderer bipedRightLeg;
	private final ModelRenderer bipedLeftLeg;


	public ParticipantsModel(boolean isSlime) {
		super(RenderType::getEntityCutoutNoCull, true, 16.0F, 0.0F, 2.0F, 2.0F, 24.0F);
		this.leftArmPose = BipedModel.ArmPose.EMPTY;
		this.rightArmPose = BipedModel.ArmPose.EMPTY;

		textureWidth = 64;
		textureHeight = 64;

		bipedHead = new ModelRenderer(this);
		bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		bipedHeadwear = new ModelRenderer(this);
		bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHeadwear.setTextureOffset(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);


		bipedBody = new ModelRenderer(this);
		bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		bipedBody.setTextureOffset(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);


		bipedRightArm = new ModelRenderer(this);
		bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		if(isSlime){
			bipedRightArm.setTextureOffset(40, 16).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);
			bipedRightArm.setTextureOffset(40, 32).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.25F, false);
		}else{
			bipedRightArm.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			bipedRightArm.setTextureOffset(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		}

		bipedLeftArm = new ModelRenderer(this);
		bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		if(isSlime){
			bipedLeftArm.setTextureOffset(32, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);
			bipedLeftArm.setTextureOffset(48, 48).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.25F, false);
		}
		else{
			bipedLeftArm.setTextureOffset(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
			bipedLeftArm.setTextureOffset(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
		}

		bipedRightLeg = new ModelRenderer(this);
		bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		bipedRightLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		bipedRightLeg.setTextureOffset(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		bipedLeftLeg = new ModelRenderer(this);
		bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		bipedLeftLeg.setTextureOffset(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		bipedLeftLeg.setTextureOffset(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(this.bipedHead);
	}
	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.bipedBody, this.bipedRightArm, this.bipedLeftArm, this.bipedRightLeg, this.bipedLeftLeg, this.bipedHeadwear);
	}

	public void setLivingAnimations(ParticipantsEntity p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
		this.swimAnimation = p_212843_1_.getSwimAnimation(p_212843_4_);
		super.setLivingAnimations(p_212843_1_, p_212843_2_, p_212843_3_, p_212843_4_);
	}

	@Override
	public void setRotationAngles(ParticipantsEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
		boolean lvt_7_1_ = p_225597_1_.getTicksElytraFlying() > 4;
		boolean lvt_8_1_ = p_225597_1_.isActualySwimming();
		this.bipedHead.rotateAngleY = p_225597_5_ * 0.017453292F;
		if (lvt_7_1_) {
			this.bipedHead.rotateAngleX = -0.7853982F;
		} else if (this.swimAnimation > 0.0F) {
			if (lvt_8_1_) {
				this.bipedHead.rotateAngleX = this.rotLerpRad(this.swimAnimation, this.bipedHead.rotateAngleX, -0.7853982F);
			} else {
				this.bipedHead.rotateAngleX = this.rotLerpRad(this.swimAnimation, this.bipedHead.rotateAngleX, p_225597_6_ * 0.017453292F);
			}
		} else {
			this.bipedHead.rotateAngleX = p_225597_6_ * 0.017453292F;
		}

		this.bipedBody.rotateAngleY = 0.0F;
		this.bipedRightArm.rotationPointZ = 0.0F;
		this.bipedRightArm.rotationPointX = -5.0F;
		this.bipedLeftArm.rotationPointZ = 0.0F;
		this.bipedLeftArm.rotationPointX = 5.0F;
		float lvt_9_1_ = 1.0F;
		if (lvt_7_1_) {
			lvt_9_1_ = (float)p_225597_1_.getMotion().lengthSquared();
			lvt_9_1_ /= 0.2F;
			lvt_9_1_ *= lvt_9_1_ * lvt_9_1_;
		}

		if (lvt_9_1_ < 1.0F) {
			lvt_9_1_ = 1.0F;
		}

		this.bipedRightArm.rotateAngleX = MathHelper.cos(p_225597_2_ * 0.6662F + 3.1415927F) * 2.0F * p_225597_3_ * 0.5F / lvt_9_1_;
		this.bipedLeftArm.rotateAngleX = MathHelper.cos(p_225597_2_ * 0.6662F) * 2.0F * p_225597_3_ * 0.5F / lvt_9_1_;
		this.bipedRightArm.rotateAngleZ = 0.0F;
		this.bipedLeftArm.rotateAngleZ = 0.0F;
		this.bipedRightLeg.rotateAngleX = MathHelper.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_ / lvt_9_1_;
		this.bipedLeftLeg.rotateAngleX = MathHelper.cos(p_225597_2_ * 0.6662F + 3.1415927F) * 1.4F * p_225597_3_ / lvt_9_1_;
		this.bipedRightLeg.rotateAngleY = 0.0F;
		this.bipedLeftLeg.rotateAngleY = 0.0F;
		this.bipedRightLeg.rotateAngleZ = 0.0F;
		this.bipedLeftLeg.rotateAngleZ = 0.0F;
		ModelRenderer var10000;
		if (this.isSitting) {
			var10000 = this.bipedRightArm;
			var10000.rotateAngleX += -0.62831855F;
			var10000 = this.bipedLeftArm;
			var10000.rotateAngleX += -0.62831855F;
			this.bipedRightLeg.rotateAngleX = -1.4137167F;
			this.bipedRightLeg.rotateAngleY = 0.31415927F;
			this.bipedRightLeg.rotateAngleZ = 0.07853982F;
			this.bipedLeftLeg.rotateAngleX = -1.4137167F;
			this.bipedLeftLeg.rotateAngleY = -0.31415927F;
			this.bipedLeftLeg.rotateAngleZ = -0.07853982F;
		}

		this.bipedRightArm.rotateAngleY = 0.0F;
		this.bipedLeftArm.rotateAngleY = 0.0F;
		boolean lvt_10_1_ = p_225597_1_.getPrimaryHand() == HandSide.RIGHT;
		boolean lvt_11_1_ = lvt_10_1_ ? this.leftArmPose.func_241657_a_() : this.rightArmPose.func_241657_a_();
		if (lvt_10_1_ != lvt_11_1_) {
			this.func_241655_c_(p_225597_1_);
			this.func_241654_b_(p_225597_1_);
		} else {
			this.func_241654_b_(p_225597_1_);
			this.func_241655_c_(p_225597_1_);
		}

		this.func_230486_a_(p_225597_1_, p_225597_4_);
		if (this.isSneak) {
			this.bipedBody.rotateAngleX = 0.5F;
			var10000 = this.bipedRightArm;
			var10000.rotateAngleX += 0.4F;
			var10000 = this.bipedLeftArm;
			var10000.rotateAngleX += 0.4F;
			this.bipedRightLeg.rotationPointZ = 4.0F;
			this.bipedLeftLeg.rotationPointZ = 4.0F;
			this.bipedRightLeg.rotationPointY = 12.2F;
			this.bipedLeftLeg.rotationPointY = 12.2F;
			this.bipedHead.rotationPointY = 4.2F;
			this.bipedBody.rotationPointY = 3.2F;
			this.bipedLeftArm.rotationPointY = 5.2F;
			this.bipedRightArm.rotationPointY = 5.2F;
		} else {
			this.bipedBody.rotateAngleX = 0.0F;
			this.bipedRightLeg.rotationPointZ = 0.1F;
			this.bipedLeftLeg.rotationPointZ = 0.1F;
			this.bipedRightLeg.rotationPointY = 12.0F;
			this.bipedLeftLeg.rotationPointY = 12.0F;
			this.bipedHead.rotationPointY = 0.0F;
			this.bipedBody.rotationPointY = 0.0F;
			this.bipedLeftArm.rotationPointY = 2.0F;
			this.bipedRightArm.rotationPointY = 2.0F;
		}

		ModelHelper.func_239101_a_(this.bipedRightArm, this.bipedLeftArm, p_225597_4_);
		if (this.swimAnimation > 0.0F) {
			float lvt_12_1_ = p_225597_2_ % 26.0F;
			HandSide lvt_13_1_ = this.getMainHand(p_225597_1_);
			float lvt_14_1_ = lvt_13_1_ == HandSide.RIGHT && this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
			float lvt_15_1_ = lvt_13_1_ == HandSide.LEFT && this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
			float lvt_16_2_;
			if (lvt_12_1_ < 14.0F) {
				this.bipedLeftArm.rotateAngleX = this.rotLerpRad(lvt_15_1_, this.bipedLeftArm.rotateAngleX, 0.0F);
				this.bipedRightArm.rotateAngleX = MathHelper.lerp(lvt_14_1_, this.bipedRightArm.rotateAngleX, 0.0F);
				this.bipedLeftArm.rotateAngleY = this.rotLerpRad(lvt_15_1_, this.bipedLeftArm.rotateAngleY, 3.1415927F);
				this.bipedRightArm.rotateAngleY = MathHelper.lerp(lvt_14_1_, this.bipedRightArm.rotateAngleY, 3.1415927F);
				this.bipedLeftArm.rotateAngleZ = this.rotLerpRad(lvt_15_1_, this.bipedLeftArm.rotateAngleZ, 3.1415927F + 1.8707964F * this.getArmAngleSq(lvt_12_1_) / this.getArmAngleSq(14.0F));
				this.bipedRightArm.rotateAngleZ = MathHelper.lerp(lvt_14_1_, this.bipedRightArm.rotateAngleZ, 3.1415927F - 1.8707964F * this.getArmAngleSq(lvt_12_1_) / this.getArmAngleSq(14.0F));
			} else if (lvt_12_1_ >= 14.0F && lvt_12_1_ < 22.0F) {
				lvt_16_2_ = (lvt_12_1_ - 14.0F) / 8.0F;
				this.bipedLeftArm.rotateAngleX = this.rotLerpRad(lvt_15_1_, this.bipedLeftArm.rotateAngleX, 1.5707964F * lvt_16_2_);
				this.bipedRightArm.rotateAngleX = MathHelper.lerp(lvt_14_1_, this.bipedRightArm.rotateAngleX, 1.5707964F * lvt_16_2_);
				this.bipedLeftArm.rotateAngleY = this.rotLerpRad(lvt_15_1_, this.bipedLeftArm.rotateAngleY, 3.1415927F);
				this.bipedRightArm.rotateAngleY = MathHelper.lerp(lvt_14_1_, this.bipedRightArm.rotateAngleY, 3.1415927F);
				this.bipedLeftArm.rotateAngleZ = this.rotLerpRad(lvt_15_1_, this.bipedLeftArm.rotateAngleZ, 5.012389F - 1.8707964F * lvt_16_2_);
				this.bipedRightArm.rotateAngleZ = MathHelper.lerp(lvt_14_1_, this.bipedRightArm.rotateAngleZ, 1.2707963F + 1.8707964F * lvt_16_2_);
			} else if (lvt_12_1_ >= 22.0F && lvt_12_1_ < 26.0F) {
				lvt_16_2_ = (lvt_12_1_ - 22.0F) / 4.0F;
				this.bipedLeftArm.rotateAngleX = this.rotLerpRad(lvt_15_1_, this.bipedLeftArm.rotateAngleX, 1.5707964F - 1.5707964F * lvt_16_2_);
				this.bipedRightArm.rotateAngleX = MathHelper.lerp(lvt_14_1_, this.bipedRightArm.rotateAngleX, 1.5707964F - 1.5707964F * lvt_16_2_);
				this.bipedLeftArm.rotateAngleY = this.rotLerpRad(lvt_15_1_, this.bipedLeftArm.rotateAngleY, 3.1415927F);
				this.bipedRightArm.rotateAngleY = MathHelper.lerp(lvt_14_1_, this.bipedRightArm.rotateAngleY, 3.1415927F);
				this.bipedLeftArm.rotateAngleZ = this.rotLerpRad(lvt_15_1_, this.bipedLeftArm.rotateAngleZ, 3.1415927F);
				this.bipedRightArm.rotateAngleZ = MathHelper.lerp(lvt_14_1_, this.bipedRightArm.rotateAngleZ, 3.1415927F);
			}

			lvt_16_2_ = 0.3F;
			float lvt_17_1_ = 0.33333334F;
			this.bipedLeftLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.bipedLeftLeg.rotateAngleX, 0.3F * MathHelper.cos(p_225597_2_ * 0.33333334F + 3.1415927F));
			this.bipedRightLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.bipedRightLeg.rotateAngleX, 0.3F * MathHelper.cos(p_225597_2_ * 0.33333334F));
		}

		this.bipedHeadwear.copyModelAngles(this.bipedHead);
	}

	private void func_241654_b_(ParticipantsEntity p_241654_1_) {
		switch (this.rightArmPose) {
			case EMPTY:
				this.bipedRightArm.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.9424779F;
				this.bipedRightArm.rotateAngleY = -0.5235988F;
				break;
			case ITEM:
				this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.31415927F;
				this.bipedRightArm.rotateAngleY = 0.0F;
				break;
			case THROW_SPEAR:
				this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 3.1415927F;
				this.bipedRightArm.rotateAngleY = 0.0F;
				break;
			case BOW_AND_ARROW:
				this.bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
				this.bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
				this.bipedRightArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
				this.bipedLeftArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
				break;
			case CROSSBOW_CHARGE:
				ModelHelper.func_239102_a_(this.bipedRightArm, this.bipedLeftArm, p_241654_1_, true);
				break;
			case CROSSBOW_HOLD:
				ModelHelper.func_239104_a_(this.bipedRightArm, this.bipedLeftArm, this.bipedHead, true);
		}

	}

	private void func_241655_c_(ParticipantsEntity p_241655_1_) {
		switch (this.leftArmPose) {
			case EMPTY:
				this.bipedLeftArm.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - 0.9424779F;
				this.bipedLeftArm.rotateAngleY = 0.5235988F;
				break;
			case ITEM:
				this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - 0.31415927F;
				this.bipedLeftArm.rotateAngleY = 0.0F;
				break;
			case THROW_SPEAR:
				this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - 3.1415927F;
				this.bipedLeftArm.rotateAngleY = 0.0F;
				break;
			case BOW_AND_ARROW:
				this.bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
				this.bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
				this.bipedRightArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
				this.bipedLeftArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
				break;
			case CROSSBOW_CHARGE:
				ModelHelper.func_239102_a_(this.bipedRightArm, this.bipedLeftArm, p_241655_1_, false);
				break;
			case CROSSBOW_HOLD:
				ModelHelper.func_239104_a_(this.bipedRightArm, this.bipedLeftArm, this.bipedHead, false);
		}

	}

	protected void func_230486_a_(ParticipantsEntity p_230486_1_, float p_230486_2_) {
		if (!(this.swingProgress <= 0.0F)) {
			HandSide lvt_3_1_ = this.getMainHand(p_230486_1_);
			ModelRenderer lvt_4_1_ = this.getArmForSide(lvt_3_1_);
			float lvt_5_1_ = this.swingProgress;
			this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(lvt_5_1_) * 6.2831855F) * 0.2F;
			ModelRenderer var10000;
			if (lvt_3_1_ == HandSide.LEFT) {
				var10000 = this.bipedBody;
				var10000.rotateAngleY *= -1.0F;
			}

			this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
			var10000 = this.bipedRightArm;
			var10000.rotateAngleY += this.bipedBody.rotateAngleY;
			var10000 = this.bipedLeftArm;
			var10000.rotateAngleY += this.bipedBody.rotateAngleY;
			var10000 = this.bipedLeftArm;
			var10000.rotateAngleX += this.bipedBody.rotateAngleY;
			lvt_5_1_ = 1.0F - this.swingProgress;
			lvt_5_1_ *= lvt_5_1_;
			lvt_5_1_ *= lvt_5_1_;
			lvt_5_1_ = 1.0F - lvt_5_1_;
			float lvt_6_1_ = MathHelper.sin(lvt_5_1_ * 3.1415927F);
			float lvt_7_1_ = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
			lvt_4_1_.rotateAngleX = (float)((double)lvt_4_1_.rotateAngleX - ((double)lvt_6_1_ * 1.2 + (double)lvt_7_1_));
			lvt_4_1_.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
			lvt_4_1_.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
		}
	}

	protected float rotLerpRad(float p_205060_1_, float p_205060_2_, float p_205060_3_) {
		float lvt_4_1_ = (p_205060_3_ - p_205060_2_) % 6.2831855F;
		if (lvt_4_1_ < -3.1415927F) {
			lvt_4_1_ += 6.2831855F;
		}

		if (lvt_4_1_ >= 3.1415927F) {
			lvt_4_1_ -= 6.2831855F;
		}

		return p_205060_2_ + p_205060_1_ * lvt_4_1_;
	}

	private float getArmAngleSq(float p_203068_1_) {
		return -65.0F * p_203068_1_ + p_203068_1_ * p_203068_1_;
	}

	protected ModelRenderer getArmForSide(HandSide p_187074_1_) {
		return p_187074_1_ == HandSide.LEFT ? this.bipedLeftArm : this.bipedRightArm;
	}

	public ModelRenderer getModelHead() {
		return this.bipedHead;
	}

	protected HandSide getMainHand(ParticipantsEntity p_217147_1_) {
		HandSide lvt_2_1_ = p_217147_1_.getPrimaryHand();
		return p_217147_1_.swingingHand == Hand.MAIN_HAND ? lvt_2_1_ : lvt_2_1_.opposite();
	}

	@OnlyIn(Dist.CLIENT)
	public static enum ArmPose {
		EMPTY(false),
		ITEM(false),
		BLOCK(false),
		BOW_AND_ARROW(true),
		THROW_SPEAR(false),
		CROSSBOW_CHARGE(true),
		CROSSBOW_HOLD(true);

		private final boolean field_241656_h_;

		private ArmPose(boolean p_i241257_3_) {
			this.field_241656_h_ = p_i241257_3_;
		}

		public boolean func_241657_a_() {
			return this.field_241656_h_;
		}
	}
}