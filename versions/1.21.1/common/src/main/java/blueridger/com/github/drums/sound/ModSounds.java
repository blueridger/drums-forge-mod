package blueridger.com.github.drums.sound;

import blueridger.com.github.drums.Drums;
import java.util.function.BiConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

public final class ModSounds {
    // SoundEvents are plain value objects, so they can be created eagerly and registered later by each loader.
    public static final SoundEvent DRUM_SOUND = create("drum_sound");
    public static final SoundEvent DRUM_SOUND_EFFECT = create("drum_sound_effect");
    public static final SoundEvent SILENCE = create("silence");

    // NeoForge deprecates this constructor in favour of supplier-based sounds; ours are created eagerly, so it is safe.
    @SuppressWarnings("deprecation")
    public static final SoundType DRUM_SOUND_TYPE = new SoundType(1f, 1f,
            DRUM_SOUND_EFFECT, DRUM_SOUND_EFFECT, DRUM_SOUND_EFFECT, SILENCE, DRUM_SOUND_EFFECT);

    private ModSounds() {
    }

    private static SoundEvent create(String name) {
        return SoundEvent.createVariableRangeEvent(Drums.id(name));
    }

    public static void register(BiConsumer<ResourceLocation, SoundEvent> registrar) {
        registrar.accept(DRUM_SOUND.getLocation(), DRUM_SOUND);
        registrar.accept(DRUM_SOUND_EFFECT.getLocation(), DRUM_SOUND_EFFECT);
        registrar.accept(SILENCE.getLocation(), SILENCE);
    }
}
