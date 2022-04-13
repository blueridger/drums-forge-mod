package blueridger.com.github.drums;

import net.minecraftforge.common.ForgeConfigSpec;

public class DrumsConfig {
    public static final ForgeConfigSpec GENERAL_SPEC;
    public static ForgeConfigSpec.DoubleValue volumeEntry;
    public static ForgeConfigSpec.DoubleValue pitchEntry;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        GENERAL_SPEC = configBuilder.build();
    }

    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        volumeEntry = builder
                .comment("How far away the drum can be heard.",
                         "1.0 = fades over 16 block radius, 2.0 = fades over 32 block radius, etc")
                .defineInRange("volume", 8D, 0D, 100D);
        pitchEntry = builder
                .comment("The pitch of the drum.",
                        "A lower number represents a lower pitch.")
                .defineInRange("pitch", 1D, 0D, 5D);
    }
}
