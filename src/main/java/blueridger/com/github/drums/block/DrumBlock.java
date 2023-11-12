package blueridger.com.github.drums.block;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class DrumBlock extends Block {

	public DrumBlock(Properties p_i48440_1_) {
		super(p_i48440_1_);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public float getDestroyProgress(BlockState pState, PlayerEntity pPlayer, IBlockReader pLevel, BlockPos pPos) {
		if (pPlayer.getMainHandItem().getItem() == Items.STICK || pPlayer.getMainHandItem().getItem() == Items.AIR)
			return 0.0F;
		return super.getDestroyProgress(pState,pPlayer,pLevel,pPos);
	}

}
