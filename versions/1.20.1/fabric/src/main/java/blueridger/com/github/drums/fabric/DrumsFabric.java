package blueridger.com.github.drums.fabric;

import blueridger.com.github.drums.DrumsConfig;
import blueridger.com.github.drums.block.ModBlocks;
import blueridger.com.github.drums.network.DrumHit;
import blueridger.com.github.drums.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;

public class DrumsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        DrumsConfig.load(FabricLoader.getInstance().getConfigDir());

        ModSounds.register((id, sound) -> Registry.register(BuiltInRegistries.SOUND_EVENT, id, sound));
        ModBlocks.registerBlocks((id, block) -> Registry.register(BuiltInRegistries.BLOCK, id, block));
        ModBlocks.registerItems((id, item) -> Registry.register(BuiltInRegistries.ITEM, id, item));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register(entries -> entries.accept(ModBlocks.DRUM_ITEM));

        ServerPlayNetworking.registerGlobalReceiver(DrumHit.CHANNEL, (server, player, handler, buf, responseSender) -> {
            BlockPos pos = buf.readBlockPos();
            server.execute(() -> DrumHit.handle(player, pos));
        });
    }
}
