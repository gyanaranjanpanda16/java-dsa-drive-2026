# Java DSA Journey — from first array to interview reasoning

Learn **what a structure stores, why it exists, how it works, when to choose it, and how to prove your solution correct**. This repository combines a guided textbook, runnable Java examples, edge-case tests, real-world walkthroughs, and exercises for independent practice.

Start small. You do not need to finish Java before learning arrays. You also do not need to memorize every algorithm to start solving problems.

## Individual solved questions — 410 available

**[Browse the 410 completed questions and their Java answers](question-bank/README.md).** Each has a separate runnable file, explanatory comments, a lesson page, sample output, and self-tests. This partial publication contains 410 of the requested 500 questions; 90 remain unfinished. The index identifies the exact completed ranges.

Start with [Q001: print every array element](docs/questions/q001.md), then follow the numbered answers in the index. The topic-based curriculum below explains the structures and their real-world uses.

## Start here

1. Read [Start here: learning and running Java](docs/00-start-here.md).
2. Work through [Java foundations](docs/01-java-foundations.md), then [arrays](docs/02-arrays.md).
3. Follow the [roadmap](ROADMAP.md) and use the [practice ladder](PRACTICE.md).
4. Record independent attempts and revisions in [your progress log](PROGRESS.md).
5. Connect each lesson to the [real-world case studies](docs/14-real-world-scenarios.md).

## Run the code

You need a **JDK 17 or newer**. No Maven, Gradle, external libraries, account, or paid course is required.

From the repository directory, in a Bash-compatible terminal (macOS/Linux, or Git Bash/WSL on Windows):

```bash
java -version
javac -version
bash scripts/test.sh
bash scripts/run.sh dsa.arrays.ArrayProblems
```

`test.sh` compiles all source and test files, then runs every test suite. A failed check exits with an error. To run another lesson, replace the class name with the name shown in its guide. After compiling, you can also run `java -cp build/classes dsa.arrays.ArrayProblems` directly.

Your original examples still run:

```bash
bash scripts/run.sh array.learnArray
bash scripts/run.sh array.ReverseArray
bash scripts/run.sh array.MoveZerosToEnd
```

## The curriculum

Read in order on a first pass; revisit topics whenever a problem exposes a gap.

| Step | Lesson | The question it answers |
| --- | --- | --- |
| 00 | [Getting started](docs/00-start-here.md) | How do I learn, run, debug, and measure progress? |
| 01 | [Java foundations](docs/01-java-foundations.md) | What do variables, methods, references, and complexity mean? |
| 02 | [Arrays and dynamic arrays](docs/02-arrays.md) | How can I store ordered values and access them quickly? |
| 03 | [Strings and sliding windows](docs/03-strings.md) | How do I process text without repeatedly rescanning it? |
| 04 | [Searching and sorting](docs/04-searching-sorting.md) | When can ordering reduce the work? |
| 05 | [Linked lists](docs/05-linked-lists.md) | What changes when values are connected through references? |
| 06 | [Stacks, queues, and deques](docs/06-stacks-queues.md) | Which pending item should I process next? |
| 07 | [Hash maps and sets](docs/07-hashing.md) | How can I remember and retrieve previously seen information? |
| 08 | [Trees and binary search trees](docs/08-trees.md) | How do I represent hierarchy and maintain ordered search? |
| 09 | [Heaps and priority queues](docs/09-heaps.md) | How do I repeatedly select the most urgent or largest item? |
| 10 | [Graphs](docs/10-graphs.md) | How do I model routes, relationships, and prerequisites? |
| 11 | [Tries and disjoint sets](docs/11-tries-union-find.md) | How do I share prefixes or track connected groups? |
| 12 | [Fenwick and segment trees](docs/12-range-queries.md) | How do I answer range questions while data changes? |
| 13 | [Recursion, backtracking, greedy, and DP](docs/13-algorithm-patterns.md) | How do I explore choices and reuse previous work? |
| 14 | [Real-world case studies](docs/14-real-world-scenarios.md) | How does a business requirement become an algorithm? |
| 15 | [Advanced structures map](docs/15-advanced-structures-map.md) | What else exists, and when is it worth studying? |
| 16 | [Interview playbook](docs/16-interview-playbook.md) | How do I communicate and validate a solution under time pressure? |
| 17 | [Choosing Java collections](docs/17-java-collections.md) | Which Java API fits the operations I actually need? |
| 18 | [Runnable program index](docs/18-program-index.md) | Which class do I run for each lesson? |

## What is implemented, and what is practice?

- **`src/dsa/`** contains working teaching implementations and executable demonstrations.
- **`tests/dsa/`** contains independent checks, including boundary conditions.
- **`src/array/`** preserves the original three programs.
- **`PRACTICE.md`** deliberately includes unsolved exercises. These are assignments, not claims that a reference solution already exists.
- **The advanced structures map** is conceptual coverage for further study, not an implementation of every named structure.
- **The real-world case studies** are small models that show the algorithmic core. They are not production services or evidence about a specific company's internal systems.

There is no finite list of “all scenarios.” The aim is to learn a reusable decision process: identify required operations, choose a representation, state an invariant, and test the boundaries. These lessons cover the core interview curriculum plus selected advanced structures; no plan guarantees a particular job offer.

## Repository layout

```text
src/
  array/                 original beginner programs
  dsa/
    foundations/         Java building blocks
    arrays/ strings/     sequential data and common patterns
    searchsort/          ordering and searching
    linkedlist/          nodes and references
    stacksqueues/        processing order and monotonic structures
    hashing/             lookup, counting, and remembered state
    trees/ heaps/        hierarchy, search, and priority
    graphs/ tries/       relationships, connectivity, and prefixes
    rangequeries/        mutable range aggregates
    algorithms/          backtracking, greedy, and dynamic programming
tests/dsa/               executable regression checks
docs/                    explanatory lessons and worked scenarios
scripts/                 build, run, and test commands
```

## A productive practice session

Spend a small part learning a concept, most of the session attempting a problem, and the final part explaining and testing your solution. Start with a straightforward approach. Optimize only after you can explain what work is being repeated.

Before marking a problem learned, answer:

1. What does every variable or node represent?
2. What is true before and after each iteration or recursive call?
3. Why does the algorithm eventually stop with the correct result?
4. What happens on an empty input, duplicate values, extreme values, or invalid input?
5. What are the time and auxiliary-space costs, and under what assumptions?
6. Can I solve a small variation without reopening the reference code?

Use [contributing guidance](CONTRIBUTING.md) when adding your own solutions. Use the official references linked in the lessons to distinguish Java API guarantees from implementation choices.
