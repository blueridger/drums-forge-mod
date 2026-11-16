package blueridger.com.github.drums.neoforge;

import blueridger.com.github.drums.Drums;
import blueridger.com.github.drums.client.DrumsClient;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = Drums.MODID, dist = Dist.CLIENT)
public class DrumsNeoForgeClient {

    public DrumsNeoForgeClient(IEventBus modBus) {
        NeoForge.EVENT_BUS.addListener(DrumsNeoForgeClient::onClientTick);
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        DrumsClient.onClientTick(Minecraft.getInstance(), ClientPacketDistributor::sendToServer);
    }
}
