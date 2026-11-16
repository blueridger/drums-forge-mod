package blueridger.com.github.drums.sound;

import blueridger.com.github.drums.Drums;
import java.util.function.BiConsumer;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

public final class ModSounds {
    // SoundEvents are plain value objects, so they can be created eagerly and registered later by each loader.
    public static final SoundEvent DRUM_SOUND = create("drum_sound");
    public static final SoundEvent DRUM_SOUND_EFFECT = create("drum_sound_effect");
    public static final SoundEvent SILENCE = create("silence");

    public static final SoundType DRUM_SOUND_TYPE = new SoundType(1f, 1f,
            DRUM_SOUND_EFFECT, DRUM_SOUND_EFFECT, DRUM_SOUND_EFFECT, SILENCE, DRUM_SOUND_EFFECT);

    private ModSounds() {
    }

    private static SoundEvent create(String name) {
        return SoundEvent.createVariableRangeEvent(Drums.id(name));
    }

    public static void register(BiConsumer<Identifier, SoundEvent> registrar) {
        registrar.accept(DRUM_SOUND.location(), DRUM_SOUND);
        registrar.accept(DRUM_SOUND_EFFECT.location(), DRUM_SOUND_EFFECT);
        registrar.accept(SILENCE.location(), SILENCE);
    }
}
