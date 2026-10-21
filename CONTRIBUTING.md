# Adding a learning solution

1. Pick an exercise from `PRACTICE.md` and attempt it before reading a reference.
2. Place the solution in the appropriate `src/dsa/<topic>/` package. Use a descriptive PascalCase class name and clear method names.
3. State accepted input, mutation behavior, return value, and invalid-input policy.
4. Explain the baseline, the improvement, and the correctness argument in the lesson or a short method comment. Comments should explain reasoning rather than repeat Java syntax.
5. State time and auxiliary-space complexity, including recursion and output size where relevant.
6. Include a tiny deterministic `main` demonstration and meaningful tests under `tests/dsa/`. Test normal behavior, important boundary cases, and rejected inputs.
7. Run `bash scripts/test.sh`. Update the learning log with whether the attempt was independent.

Use Java 17-compatible features and the standard library. Do not commit generated `.class` files, build output, credentials, IDE settings, or copied proprietary problem statements. Describe exercises in your own words and link to a problem page if appropriate.

Tests are standalone Java classes whose names end in `Test` and contain a `main` method. Throw `AssertionError` when a check fails; a plain printed answer is not an automated test. Prefer a small independent oracle or a meaningful invariant over simply repeating the implementation in the test.

Never claim that a custom linked list, heap, or graph demo is a production implementation. Document performance assumptions and limitations so future readers can distinguish a teaching model from a deployed service.
