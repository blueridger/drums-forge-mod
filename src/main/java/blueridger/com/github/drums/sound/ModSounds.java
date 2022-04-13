package blueridger.com.github.drums.sound;

import blueridger.com.github.drums.Drums;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Drums.MODID);

    public static final RegistryObject<SoundEvent> DRUM_SOUND = registerSoundEvent("drum_sound");
    public static final RegistryObject<SoundEvent> DRUM_SOUND_EFFECT = registerSoundEvent("drum_sound_effect");
    public static final RegistryObject<SoundEvent> SILENCE = registerSoundEvent("silence");

    public static final ForgeSoundType DRUM_SOUND_TYPE = new ForgeSoundType(1f, 1f,
            DRUM_SOUND_EFFECT, DRUM_SOUND_EFFECT, DRUM_SOUND_EFFECT, SILENCE, DRUM_SOUND_EFFECT);


    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent((new ResourceLocation(Drums.MODID, name))));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
