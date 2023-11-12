package blueridger.com.github.drums.block;

import java.util.function.Supplier;

import org.slf4j.Logger;

import blueridger.com.github.drums.Drums;
import blueridger.com.github.drums.item.ModItems;
import blueridger.com.github.drums.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			Drums.MODID);

	public static final RegistryObject<Block> DRUM_BLOCK = registerBlock("drum_block",
			() -> new DrumBlock(Block.Properties.of(Material.WOOD).strength(3.0F, 3.0F)
					.sound(ModSounds.DRUM_SOUND_TYPE)));
	public static final RegistryObject<Item> DRUM_ITEM = registerBlockItem("drum_block", DRUM_BLOCK);
	

	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		return BLOCKS.register(name, block);
	}

	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
		return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)));
	}

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}

}
