package blueridger.com.github.drums;

import blueridger.com.github.drums.sound.ModSounds;
import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import blueridger.com.github.drums.block.ModBlocks;
import blueridger.com.github.drums.item.ModItems;

import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("drums")
public class Drums {
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "drums";

    public static final Map<Integer, Long> latestTicks = new HashMap();
    public static final Map<Integer, Long> startingTicks = new HashMap();

    public Drums() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModSounds.register(eventBus);
        // Register the setup method for modloading
        eventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(
                ModConfig.Type.SERVER, DrumsConfig.GENERAL_SPEC, "drumsConfig.toml");
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.debug("HELLO FROM PREINIT");
        event.enqueueWork(DrumsPacketHandler::init);
    }

    @SubscribeEvent
    public void onDrumHit(PlayerInteractEvent.LeftClickBlock event) {
        Level level = event.getLevel();
        if (!level.isClientSide) return;
        if (level.getBlockState(event.getPos()).getBlock() != ModBlocks.DRUM_BLOCK.get()) return;


        int id = event.getEntity().getId();
        Long latestTick = latestTicks.put(id, level.getGameTime());
        if (latestTick == null || latestTick + 1 != level.getGameTime()) {
            startingTicks.put(id, level.getGameTime());
            DrumsPacketHandler.INSTANCE.sendToServer(new ServerBoundDrumHitPacket(event.getPos()));
        } else if ((level.getGameTime() - startingTicks.get(id)) % 5 == 0) {
            DrumsPacketHandler.INSTANCE.sendToServer(new ServerBoundDrumHitPacket(event.getPos()));
        }
    }


    @SubscribeEvent
    public void buildContents(BuildCreativeModeTabContentsEvent event) {
    	if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
    		
    		event.accept(ModBlocks.DRUM_ITEM);
    		event.accept(ModBlocks.DRUM_BLOCK);
    	}
	}

}
