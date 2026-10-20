#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
if [ "$#" -lt 1 ]; then
  echo 'Usage: bash scripts/run.sh dsa.arrays.ArrayProblems [program arguments]' >&2
  exit 1
fi
bash "$ROOT/scripts/build.sh"
java -cp "$ROOT/build/classes" "$@"
