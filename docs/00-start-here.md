# 00 — Start here

## What you are learning

A **data structure** organizes values so certain operations are efficient. An **algorithm** is a sequence of steps for solving a problem. A **Java collection** is a reusable API and implementation of some of these ideas. Arrays, linked lists, trees, and graphs are computer-science concepts that predate Java; Java did not invent them. Java offers language features and library classes so developers do not repeatedly rebuild common storage and processing behavior.

Think of a library: a shelf organizes books, an index helps find a title, and a checkout procedure tells you what to do. Those are different responsibilities. A sorted list, hash map, or tree might all store the same books, but their operation costs differ.

## Prerequisites and first run

Install a JDK 17 or later. The JDK includes `javac`, which compiles `.java` source into bytecode, and `java`, which runs it on the JVM. A JRE alone may not include the compiler. Open a terminal in this repository and run:

```bash
java -version
javac -version
bash scripts/test.sh
bash scripts/run.sh array.learnArray
```

If `javac` is missing, install/configure a JDK and check your `PATH`. If you see “release version 17 not supported,” your compiler is older than 17. Scripts require Bash; on Windows use Git Bash or WSL, or run the equivalent commands from an IDE with `src` and `tests` as source folders.

A package is a namespace: `package dsa.arrays;` corresponds to `src/dsa/arrays/`. Run the fully qualified class name, not a source filename. `main(String[] args)` is the starting method for a demonstration. The examples use small hardcoded inputs so you can focus on the algorithm before adding keyboard input.

## Read, predict, run, change

For your first array example:

1. Predict the printed values before running it.
2. Draw five numbered boxes and store each value in its index.
3. Change one value and predict which output changes.
4. Try an empty array and a one-element array.
5. Write your own method to return the sum without looking at a solution.

Do not rewrite an entire program when it fails. Reduce it to the smallest failing input, write the expected answer, trace the variable values, and locate the first step that violates your expectation.

## Complexity without memorization

Let `n` be the input size. One array lookup does a bounded amount of work: O(1). Visiting every value once is O(n). Trying every pair takes O(n²). Repeatedly halving the remaining range gives O(log n). Sorting by common comparison algorithms often costs O(n log n).

These describe growth, not exact seconds. Two nested loops are not automatically O(n²): count how many times the inner work runs in total. A pointer that only moves forward at most `n` times can make a nested-loop sliding window O(n).

**Auxiliary space** is extra working memory, excluding input and often excluding the returned output; say which convention you use. A recursive call stack consumes space. **Amortized** O(1) means a sequence of operations averages a constant cost, even though an occasional resize takes O(n). **Expected** O(1) hashing relies on hash distribution assumptions. These are different claims.

## Contracts and deliberate limitations

Every method should say what inputs it accepts and what it returns or changes. An empty array is different from `null`: empty is a real collection containing nothing; null is an absent reference. An in-place algorithm changes its input. An immutable value cannot be changed after creation.

Read each implementation's contract. Examples intentionally choose different appropriate outcomes: search may return `-1`, a missing maximum may throw or use an optional, and an invalid range should be rejected. Do not assume a sentinel value such as `0` works when zero itself can be valid data.

## Your learning loop

Attempt → explain → test → compare → record → revisit. Review after approximately 1, 3, 7, and 14 days, adjusting when necessary. If you needed a hint, record that honestly. The target is transfer: solve a related problem with different input constraints, not reproduce remembered code.

Next: [Java foundations](01-java-foundations.md).
