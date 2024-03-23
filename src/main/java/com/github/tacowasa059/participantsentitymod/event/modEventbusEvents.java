package com.github.tacowasa059.participantsentitymod.event;

import com.github.tacowasa059.participantsentitymod.Init.ItemInit;
import com.github.tacowasa059.participantsentitymod.Init.ModEntityType;
import com.github.tacowasa059.participantsentitymod.Init.ParticipantsList;
import com.github.tacowasa059.participantsentitymod.ParticipantsEntityMod;
import com.github.tacowasa059.participantsentitymod.entity.ParticipantsEntity;
import com.github.tacowasa059.participantsentitymod.entity.renderer.ParticipantsRenderer;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = ParticipantsEntityMod.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class modEventbusEvents {
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        for (ParticipantsList participant : ParticipantsList.values()) {
            EntityType entity=ModEntityType.EntityLists.get(participant.name());
            //地上
            EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                    MonsterEntity::canMonsterSpawn);
            //地下
            DungeonHooks.addDungeonMob(entity, 5);
        }
    }
    @OnlyIn(value = Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderer(FMLClientSetupEvent event){
        for (ParticipantsList participant : ParticipantsList.values()) {
            EntityType entity=ModEntityType.EntityLists.get(participant.name());
            RenderingRegistry.registerEntityRenderingHandler(entity,
                    (renderManagerIn)-> new ParticipantsRenderer(renderManagerIn, participant.name(),participant.isIsslim()));
        }
    }
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        for (ParticipantsList participant : ParticipantsList.values()) {
            EntityType<ParticipantsEntity> entityType = ModEntityType.EntityLists.get(participant.name());
            AttributeModifierMap.MutableAttribute attributes = ParticipantsEntity.setCustomAttributes();
            event.put(entityType, attributes.create());
        }
    }
}
