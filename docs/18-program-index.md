# 18 — Runnable program index

Run any class below from the repository root with:

```bash
bash scripts/run.sh <fully.qualified.ClassName>
```

The source files contain method contracts and complexity notes. Read the matching topic guide before exploring methods. Original programs are preserved under `array`; the structured lessons are under `dsa`.

| Program | Source |
| --- | --- |
| `array.MoveZerosToEnd` | [MoveZerosToEnd](../src/array/MoveZerosToEnd.java) |
| `array.ReverseArray` | [ReverseArray](../src/array/ReverseArray.java) |
| `array.learnArray` | [learnArray](../src/array/learnArray.java) |
| `dsa.algorithms.AlgorithmPatterns` | [AlgorithmPatterns](../src/dsa/algorithms/AlgorithmPatterns.java) |
| `dsa.arrays.ArrayProblems` | [ArrayProblems](../src/dsa/arrays/ArrayProblems.java) |
| `dsa.foundations.JavaBasics` | [JavaBasics](../src/dsa/foundations/JavaBasics.java) |
| `dsa.graphs.DisjointSet` | [DisjointSet](../src/dsa/graphs/DisjointSet.java) |
| `dsa.graphs.GraphAlgorithms` | [GraphAlgorithms](../src/dsa/graphs/GraphAlgorithms.java) |
| `dsa.hashing.HashingProblems` | [HashingProblems](../src/dsa/hashing/HashingProblems.java) |
| `dsa.hashing.LruCache` | [LruCache](../src/dsa/hashing/LruCache.java) |
| `dsa.heaps.HeapProblems` | [HeapProblems](../src/dsa/heaps/HeapProblems.java) |
| `dsa.linkedlist.SinglyLinkedList` | [SinglyLinkedList](../src/dsa/linkedlist/SinglyLinkedList.java) |
| `dsa.rangequeries.FenwickTree` | [FenwickTree](../src/dsa/rangequeries/FenwickTree.java) |
| `dsa.rangequeries.SegmentTree` | [SegmentTree](../src/dsa/rangequeries/SegmentTree.java) |
| `dsa.searchsort.SearchSort` | [SearchSort](../src/dsa/searchsort/SearchSort.java) |
| `dsa.stacksqueues.StackQueueProblems` | [StackQueueProblems](../src/dsa/stacksqueues/StackQueueProblems.java) |
| `dsa.strings.StringProblems` | [StringProblems](../src/dsa/strings/StringProblems.java) |
| `dsa.trees.BinarySearchTree` | [BinarySearchTree](../src/dsa/trees/BinarySearchTree.java) |
| `dsa.tries.Trie` | [Trie](../src/dsa/tries/Trie.java) |

## Verify your work

Run `bash scripts/test.sh` after changing a solution. The test suites contain fixed edge cases and deterministic randomized comparisons with simpler reference algorithms. A passing suite establishes the checked behavior, not a proof that every conceivable input or production use is covered.

To compile without running demos: `bash scripts/build.sh`. After compiling once, use `java -cp build/classes <fully.qualified.ClassName>` for a faster repeat run.
