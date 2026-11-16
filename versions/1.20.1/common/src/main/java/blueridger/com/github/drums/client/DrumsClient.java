package blueridger.com.github.drums.client;

import blueridger.com.github.drums.block.ModBlocks;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * Client-side drum playing: while the attack button is held on a drum, send a hit to the server
 * immediately and then every {@link #REPEAT_TICKS} ticks.
 */
public final class DrumsClient {
    private static final int REPEAT_TICKS = 5;

    private static BlockPos heldPos = null;
    private static int heldTicks = 0;

    private DrumsClient() {
    }

    /** Call once at the end of every client tick. */
    public static void onClientTick(Minecraft mc, Consumer<BlockPos> sendToServer) {
        BlockPos target = findTargetedDrum(mc);
        if (target == null) {
            heldPos = null;
            return;
        }

        if (!target.equals(heldPos)) {
            heldPos = target;
            heldTicks = 0;
            sendToServer.accept(target);
        } else if (++heldTicks % REPEAT_TICKS == 0) {
            sendToServer.accept(target);
        }
    }

    private static BlockPos findTargetedDrum(Minecraft mc) {
        if (mc.player == null || mc.level == null || mc.getConnection() == null) return null;
        if (mc.screen != null || mc.player.isSpectator()) return null;
        if (!mc.options.keyAttack.isDown()) return null;
        if (!(mc.hitResult instanceof BlockHitResult hit) || hit.getType() != HitResult.Type.BLOCK) return null;

        BlockPos pos = hit.getBlockPos();
        return mc.level.getBlockState(pos).is(ModBlocks.DRUM_BLOCK) ? pos : null;
    }
}
