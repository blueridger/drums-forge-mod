package blueridger.com.github.drums;

import blueridger.com.github.drums.block.ModBlocks;
import blueridger.com.github.drums.sound.ModSounds;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ServerBoundDrumHitPacket {
    public final BlockPos hitPos;

    public ServerBoundDrumHitPacket(BlockPos pos) {
        this.hitPos = pos;
    }

    public ServerBoundDrumHitPacket(PacketBuffer buffer) {
        this(buffer.readBlockPos());
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeBlockPos(this.hitPos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final AtomicBoolean success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            World level = ctx.get().getSender().getCommandSenderWorld();
            if (level.hasChunkAt(hitPos) && level.getBlockState(hitPos).getBlock() == ModBlocks.DRUM_BLOCK.get()) {
                level.playSound(null, hitPos, ModSounds.DRUM_SOUND.get(), SoundCategory.BLOCKS,
                        DrumsConfig.volumeEntry.get().floatValue(), DrumsConfig.pitchEntry.get().floatValue());
                success.set(true);
            }
        });
        ctx.get().setPacketHandled(true);
        return success.get();
    }
}
