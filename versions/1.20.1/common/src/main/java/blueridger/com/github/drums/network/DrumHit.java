package blueridger.com.github.drums.network;

import blueridger.com.github.drums.Drums;
import blueridger.com.github.drums.DrumsConfig;
import blueridger.com.github.drums.block.ModBlocks;
import blueridger.com.github.drums.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * The "player hit a drum" message sent from the client to the server. Each loader sends it over its own
 * networking API (the packet only carries the drum's {@link BlockPos}); the server-side logic lives here.
 */
public final class DrumHit {
    public static final ResourceLocation CHANNEL = Drums.id("drum_hit");

    /** Maximum distance (squared) between a player and a drum they hit; normal reach plus leeway for latency. */
    private static final double MAX_DISTANCE_SQR = 8.0D * 8.0D;

    private DrumHit() {
    }

    /** Handles a drum hit on the server thread. */
    public static void handle(ServerPlayer player, BlockPos pos) {
        Level level = player.level();
        if (!level.isLoaded(pos)) return;
        if (!level.getBlockState(pos).is(ModBlocks.DRUM_BLOCK)) return;
        if (player.distanceToSqr(Vec3.atCenterOf(pos)) > MAX_DISTANCE_SQR) return;

        level.playSound(null, pos, ModSounds.DRUM_SOUND, SoundSource.BLOCKS,
                DrumsConfig.volume(), DrumsConfig.pitch());
    }
}
