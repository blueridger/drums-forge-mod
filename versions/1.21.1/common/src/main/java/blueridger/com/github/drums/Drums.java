package blueridger.com.github.drums;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loader-independent constants and helpers shared by the Fabric and NeoForge entrypoints.
 */
public final class Drums {
    public static final String MODID = "drums";
    public static final String MOD_NAME = "Drums";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    private Drums() {
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
