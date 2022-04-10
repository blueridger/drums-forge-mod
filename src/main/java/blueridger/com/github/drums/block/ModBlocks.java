package blueridger.com.github.drums.block;

import java.util.function.Supplier;

import blueridger.com.github.drums.Drums;
import blueridger.com.github.drums.item.ModItems;
import blueridger.com.github.drums.sound.ModSounds;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			Drums.MODID);

	public static final RegistryObject<Block> DRUM_BLOCK = registerBlock("drum_block",
			() -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(50F, 2f).sound(ModSounds.DRUM_SOUND_TYPE)),
			CreativeModeTab.TAB_DECORATIONS);
	

	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
			CreativeModeTab tab) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn, tab);
		return toReturn;
	}

	private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block,
			CreativeModeTab tab) {
		return BLOCKS.register(name, block);
	}

	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
			CreativeModeTab tab) {
		return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
