#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"
if ! command -v javac >/dev/null 2>&1; then
  echo 'A JDK (Java 17 or newer) is required. Install a JDK, then try again.' >&2
  exit 1
fi
mkdir -p build/classes
# Relative source paths work even when the repository directory contains spaces.
find src tests -name '*.java' -type f | LC_ALL=C sort > build/sources.txt
javac --release 17 -Xlint:all -d build/classes @build/sources.txt
printf 'Compiled Java sources successfully.\n'
