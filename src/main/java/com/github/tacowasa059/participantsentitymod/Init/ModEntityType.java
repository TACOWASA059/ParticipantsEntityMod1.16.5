package com.github.tacowasa059.participantsentitymod.Init;

import com.github.tacowasa059.participantsentitymod.ParticipantsEntityMod;
import com.github.tacowasa059.participantsentitymod.entity.ParticipantsEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class ModEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES=DeferredRegister.create(ForgeRegistries.ENTITIES, ParticipantsEntityMod.MOD_ID);
    public static final Map<String, EntityType<ParticipantsEntity>> EntityLists=new HashMap<>();
    public static Map<EntityType<ParticipantsEntity>, String> reverseMap = new HashMap<>();
    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
