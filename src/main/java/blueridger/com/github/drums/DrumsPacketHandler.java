package blueridger.com.github.drums;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class DrumsPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Drums.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void init() {
        int index = 0;
        INSTANCE.messageBuilder(ServerBoundDrumHitPacket.class, index++, NetworkDirection.PLAY_TO_SERVER).encoder(ServerBoundDrumHitPacket::encode)
                .decoder(ServerBoundDrumHitPacket::new).consumerNetworkThread(ServerBoundDrumHitPacket::handle).add();
    }
}
