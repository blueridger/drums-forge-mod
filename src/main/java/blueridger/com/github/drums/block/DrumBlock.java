package blueridger.com.github.drums.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class DrumBlock extends Block {

	public DrumBlock(Properties p_i48440_1_) {
		super(p_i48440_1_);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public float getDestroyProgress(BlockState pState, Player pPlayer, BlockGetter pLevel, BlockPos pPos) {
		if (pPlayer.getMainHandItem().getItem() == Items.STICK || pPlayer.getMainHandItem().getItem() == Items.AIR)
			return 0.0F;
		return super.getDestroyProgress(pState,pPlayer,pLevel,pPos);
	}

}
