package blueridger.com.github.drums.forge;

import blueridger.com.github.drums.Drums;
import blueridger.com.github.drums.DrumsConfig;
import blueridger.com.github.drums.block.ModBlocks;
import blueridger.com.github.drums.network.DrumHit;
import blueridger.com.github.drums.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.RegisterEvent;

import java.util.function.Supplier;

@Mod(Drums.MODID)
public class DrumsForge {
    private static final String PROTOCOL_VERSION = "2";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            DrumHit.CHANNEL, () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    // FMLJavaModLoadingContext.get() is deprecated on newer Forge 47.x, but the replacement (constructor injection)
    // does not exist on older 47.x builds, so keep using it to stay compatible with every 1.20.1 Forge version.
    @SuppressWarnings("removal")
    public DrumsForge() {
        DrumsConfig.load(FMLPaths.CONFIGDIR.get());

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(DrumsForge::register);
        modBus.addListener(DrumsForge::commonSetup);
        modBus.addListener(DrumsForge::buildCreativeTabs);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            DrumsForgeClient.init();
        }
    }

    private static void register(RegisterEvent event) {
        event.register(Registries.SOUND_EVENT, helper -> ModSounds.register(helper::register));
        event.register(Registries.BLOCK, helper -> ModBlocks.registerBlocks(helper::register));
        event.register(Registries.ITEM, helper -> ModBlocks.registerItems(helper::register));
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> CHANNEL.messageBuilder(BlockPos.class, 0, NetworkDirection.PLAY_TO_SERVER)
                .encoder((pos, buf) -> buf.writeBlockPos(pos))
                .decoder(buf -> buf.readBlockPos())
                .consumerMainThread(DrumsForge::handleDrumHit)
                .add());
    }

    private static void handleDrumHit(BlockPos pos, Supplier<NetworkEvent.Context> ctx) {
        ServerPlayer player = ctx.get().getSender();
        if (player != null) {
            DrumHit.handle(player, pos);
        }
        ctx.get().setPacketHandled(true);
    }

    private static void buildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.DRUM_ITEM);
        }
    }
}
