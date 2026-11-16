package blueridger.com.github.drums.forge;

import blueridger.com.github.drums.client.DrumsClient;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

/** Client-only setup; only loaded on the physical client. */
final class DrumsForgeClient {
    private DrumsForgeClient() {
    }

    static void init() {
        MinecraftForge.EVENT_BUS.addListener(DrumsForgeClient::onClientTick);
    }

    private static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        DrumsClient.onClientTick(Minecraft.getInstance(), DrumsForge.CHANNEL::sendToServer);
    }
}
