# Drums Mod

![2022-04-10_14 45 06](https://user-images.githubusercontent.com/11550396/162641324-a07c0977-af02-47b0-803b-c6bc01aee852.png)

 [![](https://cf.way2muchnoise.eu/versions/drums.svg)](https://www.curseforge.com/minecraft/mc-mods/drums) [![Download on CurseForge](https://cf.way2muchnoise.eu/title/Drums_Download_on%20CurseForge%20->.svg)](https://www.curseforge.com/minecraft/mc-mods/drums)

This is a very small mod for [Fabric](https://fabricmc.net/), [NeoForge](https://neoforged.net/) and (for Minecraft 1.20.1) [Minecraft Forge](https://github.com/MinecraftForge/MinecraftForge) that simply adds a craftable Drum block that makes a loud resonant drum noise when hit.
The block cannot be destroyed with a stick or with an open hand, allowing you to play it without breaking it. The crafting recipe involves 6 planks and 2 leather, and is added to your recipe book automatically. That's it!

The networking logic makes synchronizing your playing with others work pretty well in low-latency servers. You can adjust the volume (distance heard) and pitch using the auto-generated `config/drums.properties` file (on Forge 1.20.1, the per-world `serverconfig/drumsConfig.toml` file).

## Supported versions

| Minecraft | Loader | Source |
|-----------|--------|--------|
| 26.3 | Fabric (requires Fabric API) | `common/` + `fabric/` |
| 26.3 | NeoForge | `common/` + `neoforge/` |
| 1.20.1 | Forge | `forge-1.20.1/` (standalone legacy project) |

## Building

The Fabric and NeoForge versions share one codebase using a multi-loader layout: almost all of the mod lives in `common/`, and `fabric/` and `neoforge/` are thin entrypoints for each loader. Building needs Java 25:

```sh
./gradlew build
# -> fabric/build/libs/drums-fabric-26.3-<version>.jar
# -> neoforge/build/libs/drums-neoforge-26.3-<version>.jar
```

Run a dev client with `./gradlew :fabric:runClient` or `./gradlew :neoforge:runClient`.

The legacy Forge 1.20.1 version is a separate Gradle project that needs Java 17:

```sh
cd forge-1.20.1 && ./gradlew build
# -> forge-1.20.1/build/libs/drums-1.3-1.20.1.jar
```

## Inspiration and Personal Meaning
This mod was inspired by time with a friend, exploring how we could flip the script of how we play Minecraft. Instead of playing in the default, colonial, extractive way, we have been exploring how we can practice our own decolonization in-game. This has in part looked like ritualizing the felling of a tree or the harvesting of an animal's life, showing gratitude and reverence for the gifts of the Earth. For us, this drum represents the first in-game mechanic intended specifically for ceremony.

I'm a colonial settler. My decolonization journey is early and endless, and I am grateful to have the indirect guidance of Indigenous voices such as:
- [All My Relations Podcast](https://www.allmyrelationspodcast.com/)
- [Braiding Sweetgrass by Robin Wall Kimmerer](https://www.robinwallkimmerer.com/books)
- [Sand Talk by Tyson Yunkaporta](https://www.harpercollins.com/products/sand-talk-tyson-yunkaporta)

## Maintenance / Contributing
I may or may not add features and upgrade to future MC versions, but expect that I won't. However, if you are excited to add a feature or do some other maintenance, I welcome you to submit a pull request. This is a very small mod, and should be beginner-friendly as far as Minecraft mods go.

**Licensed under Creative Commons Attribution 4.0 International**
