package blueridger.com.github.drums.neoforge;

import blueridger.com.github.drums.Drums;
import blueridger.com.github.drums.DrumsConfig;
import blueridger.com.github.drums.block.ModBlocks;
import blueridger.com.github.drums.network.DrumHitPayload;
import blueridger.com.github.drums.sound.ModSounds;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Drums.MODID)
public class DrumsNeoForge {

    public DrumsNeoForge(IEventBus modBus) {
        DrumsConfig.load(FMLPaths.CONFIGDIR.get());

        modBus.addListener(DrumsNeoForge::register);
        modBus.addListener(DrumsNeoForge::registerPayloads);
        modBus.addListener(DrumsNeoForge::buildCreativeTabs);
    }

    private static void register(RegisterEvent event) {
        event.register(Registries.SOUND_EVENT, helper -> ModSounds.register(helper::register));
        event.register(Registries.BLOCK, helper -> ModBlocks.registerBlocks(helper::register));
        event.register(Registries.ITEM, helper -> ModBlocks.registerItems(helper::register));
    }

    private static void registerPayloads(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToServer(DrumHitPayload.TYPE, DrumHitPayload.STREAM_CODEC,
                (payload, context) -> payload.handle((ServerPlayer) context.player()));
    }

    private static void buildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.DRUM_ITEM);
        }
    }
}
