package blueridger.com.github.drums.fabric;

import blueridger.com.github.drums.client.DrumsClient;
import blueridger.com.github.drums.network.DrumHit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;

public class DrumsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(mc -> DrumsClient.onClientTick(mc,
                pos -> ClientPlayNetworking.send(DrumHit.CHANNEL, PacketByteBufs.create().writeBlockPos(pos))));
    }
}
