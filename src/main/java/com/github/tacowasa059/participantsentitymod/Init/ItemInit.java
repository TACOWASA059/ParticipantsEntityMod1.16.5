package com.github.tacowasa059.participantsentitymod.Init;

import com.github.tacowasa059.participantsentitymod.ParticipantsEntityMod;
import com.github.tacowasa059.participantsentitymod.entity.ParticipantsEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
@Mod.EventBusSubscriber(modid = ParticipantsEntityMod.MOD_ID,bus= Mod.EventBusSubscriber.Bus.MOD)
public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ParticipantsEntityMod.MOD_ID);

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {

    }

}
