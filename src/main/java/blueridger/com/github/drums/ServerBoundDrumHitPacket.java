package blueridger.com.github.drums;

import blueridger.com.github.drums.block.ModBlocks;
import blueridger.com.github.drums.sound.ModSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ServerBoundDrumHitPacket {
    public final BlockPos hitPos;

    public ServerBoundDrumHitPacket(BlockPos pos) {
        this.hitPos = pos;
    }

    public ServerBoundDrumHitPacket(FriendlyByteBuf buffer) {
        this(buffer.readBlockPos());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.hitPos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            Level level = ctx.get().getSender().level;
            if (level.hasChunkAt(hitPos) && level.getBlockState(hitPos).getBlock() == ModBlocks.DRUM_BLOCK.get()) {
                level.playSound(null, hitPos, ModSounds.DRUM_SOUND.get(), SoundSource.BLOCKS,
                        DrumsConfig.volumeEntry.get().floatValue(), DrumsConfig.pitchEntry.get().floatValue());
                success.set(true);
            }
        });
        ctx.get().setPacketHandled(true);
        return success.get();
    }
}
