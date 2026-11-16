package blueridger.com.github.drums.network;

import blueridger.com.github.drums.Drums;
import blueridger.com.github.drums.DrumsConfig;
import blueridger.com.github.drums.block.ModBlocks;
import blueridger.com.github.drums.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

/**
 * Sent from the client to the server when a player hits a drum, so the server can play the sound for everyone nearby.
 */
public record DrumHitPayload(BlockPos pos) implements CustomPacketPayload {
    public static final Type<DrumHitPayload> TYPE = new Type<>(Drums.id("drum_hit"));
    public static final StreamCodec<RegistryFriendlyByteBuf, DrumHitPayload> STREAM_CODEC =
            BlockPos.STREAM_CODEC.map(DrumHitPayload::new, DrumHitPayload::pos).cast();

    /** Extra leeway on top of the player's normal reach, to account for latency. */
    private static final double REACH_BUFFER = 3.0D;

    @Override
    public Type<DrumHitPayload> type() {
        return TYPE;
    }

    /** Handles the packet on the server thread. */
    public void handle(ServerPlayer player) {
        Level level = player.level();
        if (!level.isLoaded(pos)) return;
        if (!level.getBlockState(pos).is(ModBlocks.DRUM_BLOCK)) return;
        if (!player.isWithinBlockInteractionRange(pos, REACH_BUFFER)) return;

        level.playSound(null, pos, ModSounds.DRUM_SOUND, SoundSource.BLOCKS,
                DrumsConfig.volume(), DrumsConfig.pitch());
    }
}
