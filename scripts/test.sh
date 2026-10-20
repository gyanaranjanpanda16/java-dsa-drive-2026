#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"
bash scripts/build.sh
count=0
while IFS= read -r source; do
  class="${source#tests/}"
  class="${class%.java}"
  class="${class//\//.}"
  printf '\nRunning %s\n' "$class"
  java -ea -cp build/classes "$class"
  count=$((count + 1))
done < <(find tests -name '*Test.java' -type f | LC_ALL=C sort)
if [ "$count" -eq 0 ]; then
  echo 'No test suites found.' >&2
  exit 1
fi
printf '\nAll %s test suites passed.\n' "$count"
