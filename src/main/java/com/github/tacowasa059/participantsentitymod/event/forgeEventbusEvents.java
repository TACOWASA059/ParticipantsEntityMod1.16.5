package com.github.tacowasa059.participantsentitymod.event;

import com.github.tacowasa059.participantsentitymod.Init.ModEntityType;
import com.github.tacowasa059.participantsentitymod.Init.ParticipantsList;
import com.github.tacowasa059.participantsentitymod.ParticipantsEntityMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ParticipantsEntityMod.MOD_ID,bus= Mod.EventBusSubscriber.Bus.FORGE)
public class forgeEventbusEvents {
    //biomeの追加
    @SubscribeEvent
    public static void addFeatureToBiomes(BiomeLoadingEvent event) {
        for (ParticipantsList participant : ParticipantsList.values()) {
            EntityType entity=ModEntityType.EntityLists.get(participant.name());
            event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(entity,
                    1, 1, 1));//スポーン試行回数,最小スポーン数、最大スポーン数
        }
    }
}
