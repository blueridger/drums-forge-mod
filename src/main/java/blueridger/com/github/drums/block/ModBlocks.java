package blueridger.com.github.drums.block;

import java.util.function.Supplier;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import blueridger.com.github.drums.Drums;
import blueridger.com.github.drums.item.ModItems;
import blueridger.com.github.drums.sound.ModSounds;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    private static final Logger LOGGER = LogUtils.getLogger();
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			Drums.MODID);

	public static final RegistryObject<Block> DRUM_BLOCK = registerBlock("drum_block",
			() -> new DrumBlock(BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.WOOD).strength(3F, 3f)
					.sound(ModSounds.DRUM_SOUND_TYPE)));
	public static final RegistryObject<Item> DRUM_ITEM = registerBlockItem("drum_block", DRUM_BLOCK);
	

	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		return BLOCKS.register(name, block);
	}

	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
		return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}

}
