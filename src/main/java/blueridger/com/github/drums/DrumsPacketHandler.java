package blueridger.com.github.drums;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

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
                .decoder(ServerBoundDrumHitPacket::new).consumer(ServerBoundDrumHitPacket::handle).add();
    }
}
