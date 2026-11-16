#!/usr/bin/env bash
# Builds every supported Minecraft version and copies the release jars into dist/.
#
# Each folder in versions/ is its own Gradle project. 1.20.x needs Java 17, 1.21.x needs Java 21 and
# 26.x needs Java 25; Gradle finds (or downloads) the right JDK automatically via toolchains, but Gradle
# itself must be started with Java 21 (the 26.x projects then switch Gradle to Java 25 on their own).
#
# Usage: ./build-all.sh            # build all versions
#        ./build-all.sh 1.21.1 26.3  # build only these versions
set -euo pipefail
cd "$(dirname "$0")"

versions=("$@")
if [ ${#versions[@]} -eq 0 ]; then
    versions=()
    for dir in versions/*/; do versions+=("$(basename "$dir")"); done
fi

mkdir -p dist
for version in "${versions[@]}"; do
    echo "==> Building Minecraft $version"
    (cd "versions/$version" && ./gradlew build)
    for loader in fabric forge neoforge; do
        libs="versions/$version/$loader/build/libs"
        [ -d "$libs" ] || continue
        find "$libs" -maxdepth 1 -name "drums-$loader-*.jar" ! -name '*-sources.jar' -exec cp {} dist/ \;
    done
done

echo "==> Done. Jars:"
ls -1 dist/
