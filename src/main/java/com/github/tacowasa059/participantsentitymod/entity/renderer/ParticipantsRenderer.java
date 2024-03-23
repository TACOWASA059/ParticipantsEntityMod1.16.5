package com.github.tacowasa059.participantsentitymod.entity.renderer;

import com.github.tacowasa059.participantsentitymod.Init.ModEntityType;
import com.github.tacowasa059.participantsentitymod.ParticipantsEntityMod;
import com.github.tacowasa059.participantsentitymod.entity.ParticipantsEntity;
import com.github.tacowasa059.participantsentitymod.entity.model.ParticipantsModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ParticipantsRenderer extends MobRenderer<ParticipantsEntity, ParticipantsModel<ParticipantsEntity>> {


    public ParticipantsRenderer(EntityRendererManager p_i50961_1_, String name,boolean isSlim) {
        super(p_i50961_1_, new ParticipantsModel<ParticipantsEntity>(isSlim), 0.5F);
        this.addLayer(new GlowingLayer<>(this,getEntityTexture_FromString(name)));
    }

    @Override
    public ResourceLocation getEntityTexture(ParticipantsEntity participantsEntity) {
        String name= ModEntityType.reverseMap.get(participantsEntity.getType());
        return getEntityTexture_FromString(name);
    }
    public ResourceLocation getEntityTexture_FromString(String name) {
        return new ResourceLocation(ParticipantsEntityMod.MOD_ID,
                "textures/entities/"+name+".png");
    }

    @OnlyIn(Dist.CLIENT)
    private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
        ResourceLocation location;
        public GlowingLayer(IEntityRenderer<T, M> er, ResourceLocation location) {
            super(er);
            this.location=location;
        }

        public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
                           float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(location));
            this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }
    }
}
