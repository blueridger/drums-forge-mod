package blueridger.com.github.drums.block;

import blueridger.com.github.drums.Drums;
import blueridger.com.github.drums.sound.ModSounds;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public final class ModBlocks {
    public static final ResourceLocation DRUM_ID = Drums.id("drum_block");

    // Blocks and items must be constructed while their registry is open, so they are created in register().
    public static Block DRUM_BLOCK;
    public static Item DRUM_ITEM;

    private ModBlocks() {
    }

    public static void registerBlocks(BiConsumer<ResourceLocation, Block> registrar) {
        DRUM_BLOCK = new DrumBlock(BlockBehaviour.Properties.of()
                .ignitedByLava()
                .mapColor(MapColor.WOOD)
                .strength(3F, 3F)
                .sound(ModSounds.DRUM_SOUND_TYPE));
        registrar.accept(DRUM_ID, DRUM_BLOCK);
    }

    public static void registerItems(BiConsumer<ResourceLocation, Item> registrar) {
        DRUM_ITEM = new BlockItem(DRUM_BLOCK, new Item.Properties());
        registrar.accept(DRUM_ID, DRUM_ITEM);
    }
}
