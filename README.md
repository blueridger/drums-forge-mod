# Drums Mod

![2022-04-10_14 45 06](https://user-images.githubusercontent.com/11550396/162641324-a07c0977-af02-47b0-803b-c6bc01aee852.png)

 [![](https://cf.way2muchnoise.eu/versions/drums.svg)](https://www.curseforge.com/minecraft/mc-mods/drums) [![Download on CurseForge](https://cf.way2muchnoise.eu/title/Drums_Download_on%20CurseForge%20->.svg)](https://www.curseforge.com/minecraft/mc-mods/drums)

This is a very small mod for [Fabric](https://fabricmc.net/), [NeoForge](https://neoforged.net/) and [Minecraft Forge](https://github.com/MinecraftForge/MinecraftForge) that simply adds a craftable Drum block that makes a loud resonant drum noise when hit.
The block cannot be destroyed with a stick or with an open hand, allowing you to play it without breaking it. The crafting recipe involves 6 planks and 2 leather, and is added to your recipe book automatically. That's it!

The networking logic makes synchronizing your playing with others work pretty well in low-latency servers. You can adjust the volume (distance heard) and pitch in the auto-generated `config/drums.properties` file.

## Supported versions

| Minecraft | Fabric (needs Fabric API) | NeoForge | Forge |
|-----------|:-------------------------:|:--------:|:-----:|
| 26.3      | ✅ | ✅ |    |
| 26.2      | ✅ | ✅ |    |
| 26.1.2    | ✅ | ✅ |    |
| 1.21.11   | ✅ | ✅ |    |
| 1.21 – 1.21.1 | ✅ | ✅ (1.21.1) |    |
| 1.20 – 1.20.1 | ✅ |    | ✅ (1.20.1) |

These are the versions with the most players and mods. Download the jar whose name matches your loader and Minecraft version, e.g. `drums-neoforge-1.21.1-<version>.jar`.

### TerraFirmaCraft

With [TerraFirmaCraft](https://www.curseforge.com/minecraft/mc-mods/terrafirmacraft) installed (1.20.1 Forge or 1.21.1 NeoForge), the drum is crafted from 6 TFC lumber and 2 leather in the same shape instead of planks, and it counts as a Large / Medium item in TFC inventories.

## Building

Each Minecraft version lives in its own folder under `versions/`, and each is a standalone Gradle project using a multi-loader layout: almost all of the mod is in `common/`, and `fabric/`, `neoforge/` and `forge/` are thin entrypoints for each loader. Minecraft changed a lot between these versions, so each folder has its own copy of the (small) code adapted to that version.

Build everything (jars are collected in `dist/`):

```sh
./build-all.sh              # all versions
./build-all.sh 1.21.1 26.3  # just some
```

Or build/run one version directly:

```sh
cd versions/1.21.1
./gradlew build                 # -> fabric/build/libs and neoforge/build/libs
./gradlew :fabric:runClient     # or :neoforge:runClient / :forge:runClient
```

Start Gradle with Java 21 (the 26.x projects switch Gradle itself to Java 25 automatically). The Java version each Minecraft version compiles with (17 for 1.20.1, 21 for 1.21.x, 25 for 26.x) is downloaded automatically if you don't have it.

## Inspiration and Personal Meaning
This mod was inspired by time with a friend, exploring how we could flip the script of how we play Minecraft. Instead of playing in the default, colonial, extractive way, we have been exploring how we can practice our own decolonization in-game. This has in part looked like ritualizing the felling of a tree or the harvesting of an animal's life, showing gratitude and reverence for the gifts of the Earth. For us, this drum represents the first in-game mechanic intended specifically for ceremony.

I'm a colonial settler. My decolonization journey is early and endless, and I am grateful to have the indirect guidance of Indigenous voices such as:
- [All My Relations Podcast](https://www.allmyrelationspodcast.com/)
- [Braiding Sweetgrass by Robin Wall Kimmerer](https://www.robinwallkimmerer.com/books)
- [Sand Talk by Tyson Yunkaporta](https://www.harpercollins.com/products/sand-talk-tyson-yunkaporta)

## Maintenance / Contributing
I may or may not add features and upgrade to future MC versions, but expect that I won't. However, if you are excited to add a feature or do some other maintenance, I welcome you to submit a pull request. This is a very small mod, and should be beginner-friendly as far as Minecraft mods go.

**Licensed under Creative Commons Attribution 4.0 International**
