package blueridger.com.github.drums.fabric;

import blueridger.com.github.drums.client.DrumsClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class DrumsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(mc -> DrumsClient.onClientTick(mc, ClientPlayNetworking::send));
    }
}
