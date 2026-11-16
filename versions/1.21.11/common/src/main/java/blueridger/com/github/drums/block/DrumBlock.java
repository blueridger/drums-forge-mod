package blueridger.com.github.drums.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DrumBlock extends Block {

    public DrumBlock(Properties properties) {
        super(properties);
    }

    /** Whether the player is holding something that plays the drum instead of breaking it. */
    public static boolean isDrumstick(ItemStack stack) {
        return stack.isEmpty() || stack.is(Items.STICK);
    }

    @Override
    protected float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        if (isDrumstick(player.getMainHandItem())) {
            return 0.0F;
        }
        return super.getDestroyProgress(state, player, level, pos);
    }
}
