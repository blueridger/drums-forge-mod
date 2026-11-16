package blueridger.com.github.drums;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * A tiny loader-independent config stored in {@code config/drums.properties}.
 */
public final class DrumsConfig {
    private static final String FILE_NAME = "drums.properties";

    private static final double DEFAULT_VOLUME = 8D;
    private static final double DEFAULT_PITCH = 1D;

    private static float volume = (float) DEFAULT_VOLUME;
    private static float pitch = (float) DEFAULT_PITCH;

    private DrumsConfig() {
    }

    /** How far away the drum can be heard. 1.0 = fades over a 16 block radius, 2.0 = 32 blocks, etc. */
    public static float volume() {
        return volume;
    }

    /** The pitch of the drum. A lower number represents a lower pitch. */
    public static float pitch() {
        return pitch;
    }

    public static void load(Path configDir) {
        Path file = configDir.resolve(FILE_NAME);
        Properties props = new Properties();
        if (Files.exists(file)) {
            try (Reader reader = Files.newBufferedReader(file)) {
                props.load(reader);
            } catch (IOException | IllegalArgumentException e) {
                Drums.LOG.warn("Could not read {}, using defaults", file, e);
            }
        }

        volume = (float) read(props, "volume", DEFAULT_VOLUME, 0D, 100D);
        pitch = (float) read(props, "pitch", DEFAULT_PITCH, 0D, 5D);

        try {
            Files.createDirectories(configDir);
            try (Writer writer = Files.newBufferedWriter(file)) {
                writer.write("# Drums mod config\n");
                writer.write("\n");
                writer.write("# How far away the drum can be heard.\n");
                writer.write("# 1.0 = fades over 16 block radius, 2.0 = fades over 32 block radius, etc\n");
                writer.write("# Range: 0.0 ~ 100.0\n");
                writer.write("volume=" + volume + "\n");
                writer.write("\n");
                writer.write("# The pitch of the drum.\n");
                writer.write("# A lower number represents a lower pitch.\n");
                writer.write("# Range: 0.0 ~ 5.0\n");
                writer.write("pitch=" + pitch + "\n");
            }
        } catch (IOException e) {
            Drums.LOG.warn("Could not write {}", file, e);
        }
    }

    private static double read(Properties props, String key, double def, double min, double max) {
        String raw = props.getProperty(key);
        if (raw == null) return def;
        try {
            double value = Double.parseDouble(raw.trim());
            if (Double.isNaN(value)) return def;
            return Math.max(min, Math.min(max, value));
        } catch (NumberFormatException e) {
            Drums.LOG.warn("Invalid value '{}' for '{}' in drums config, using default {}", raw, key, def);
            return def;
        }
    }
}
