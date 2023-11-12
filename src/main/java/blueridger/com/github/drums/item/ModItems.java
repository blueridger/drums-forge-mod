package blueridger.com.github.drums.item;

import blueridger.com.github.drums.Drums;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			Drums.MODID);

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
