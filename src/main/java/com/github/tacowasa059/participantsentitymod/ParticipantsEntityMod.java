package com.github.tacowasa059.participantsentitymod;

import com.github.tacowasa059.participantsentitymod.Init.ItemInit;
import com.github.tacowasa059.participantsentitymod.Init.ModEntityType;
import com.github.tacowasa059.participantsentitymod.Init.ModItemGroup;
import com.github.tacowasa059.participantsentitymod.Init.ParticipantsList;
import com.github.tacowasa059.participantsentitymod.entity.ParticipantsEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ParticipantsEntityMod.MOD_ID)
public class ParticipantsEntityMod {
    public static final String MOD_ID="participantsentitymod";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public ParticipantsEntityMod() {
        new ModEntityType();
        // Register the setup method for modloading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        modEventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading

        modEventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        modEventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);



        for (ParticipantsList participant : ParticipantsList.values()) {
            String name= participant.name();
            EntityType<ParticipantsEntity> entityType = EntityType.Builder.create(ParticipantsEntity::new,
                            EntityClassification.MONSTER).size(0.6f, 2f)
                    .setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
                    .build(new ResourceLocation(ParticipantsEntityMod.MOD_ID, name).toString());
            ModEntityType.EntityLists.put(name,entityType);

            SpawnEggItem eggItem = new SpawnEggItem(entityType,
                    ParticipantsList.valueOf(name).getprimaryColor(), ParticipantsList.valueOf(name).getsecondaryColor(),
                    new Item.Properties().group(ModItemGroup.ParticipantsEntity));
            ItemInit.ITEMS.register(name+"_spawn_egg",
                    () -> eggItem
            );
        };

        ItemInit.ITEMS.register(modEventBus);

        ModEntityType.EntityLists.forEach((name,entityType)->{
            RegistryObject<EntityType<ParticipantsEntity>> registry_entityType = ModEntityType.ENTITY_TYPES.register(name,
                    ()->entityType);
        });
        ModEntityType.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModEntityType.EntityLists.forEach((name, entityType) -> {
            ModEntityType.reverseMap.put(entityType, name);
        });
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().debug);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("participantsentitymod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
