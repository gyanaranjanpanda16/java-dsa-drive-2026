# Practice ladder

These are independent exercises, not a claim that every solution is already implemented. Reference classes cover representative techniques; extend them by writing your own classes and tests. Read the relevant lesson before attempting a new topic. Do not mark a task complete merely because you ran provided code.

For every exercise: write the contract → trace a small example → derive a baseline → explain an improvement → implement → test → state complexity → revisit without notes. “Stretch” means a later extension, not a prerequisite to moving on.

## 1. First Java programs

- [ ] Print numbers 1 through n with a loop; define behavior for n≤0.
- [ ] Sum the first n nonnegative integers; compare a loop with a formula and discuss overflow.
- [ ] Find the maximum of three integers, including equal and negative values.
- [ ] Count decimal digits, including zero and negative inputs.
- [ ] Check whether an integer is prime; explain why divisors beyond its square root are unnecessary.
- [ ] Compute a greatest common divisor using Euclid's algorithm.
- [ ] Write a method that swaps array positions; explain why swapping two primitive parameters does not change caller variables.
- [ ] Compare two distinct `String` objects using `==` and `.equals()`.

Reference: [JavaBasics](src/dsa/foundations/JavaBasics.java).

## 2. Arrays: start here even if the problem looks small

- [ ] Traverse and print each element with its index.
- [ ] Return a `long` sum and calculate an average with an explicit empty-input policy.
- [ ] Find maximum and minimum without sorting.
- [ ] Find the second-largest distinct value; handle all-equal input.
- [ ] Find the first index containing a target using linear search.
- [ ] Reverse in place with two pointers, then write a non-mutating variation.
- [ ] Check whether values are nondecreasing.
- [ ] Count occurrences of a target.
- [ ] Move zeros to the end while preserving nonzero order.
- [ ] Rotate right by k, including k>length and empty input.
- [ ] Remove duplicates from a sorted array and return the valid prefix length.
- [ ] Merge two sorted arrays.
- [ ] Find a missing value from 0..n under a clearly stated uniqueness contract.
- [ ] Build prefix sums and answer inclusive range totals.
- [ ] Find a nonempty maximum-sum subarray, including all-negative inputs.
- [ ] Find two indices whose values sum to a target; prevent overflow and index reuse.

Reference: [ArrayProblems](src/dsa/arrays/ArrayProblems.java).

## 3. Strings, hashing, and windows

- [ ] Test a palindrome with exact character matching.
- [ ] Extend palindrome matching to ignore ASCII punctuation/case, explicitly defining the text model.
- [ ] Check whether two strings are anagrams, including repeated characters.
- [ ] Find the first non-repeating character.
- [ ] Count word frequencies with a documented tokenization rule.
- [ ] Group anagrams by a canonical key.
- [ ] Find the longest substring without repeated characters.
- [ ] Find the largest sum among all windows of length k.
- [ ] Find a minimum-length window meeting a sum threshold for positive values.
- [ ] Explain why the previous approach can fail with negative values.
- [ ] Count subarrays with sum k using prefix frequencies, including zeros and negatives.
- [ ] Stretch: minimum window containing every required character with multiplicities.

References: [StringProblems](src/dsa/strings/StringProblems.java), [HashingProblems](src/dsa/hashing/HashingProblems.java).

## 4. Search and sort

- [ ] Implement insertion sort and trace its sorted-prefix invariant.
- [ ] Implement merge sort and explain the merge step.
- [ ] Search sorted input using binary search.
- [ ] Return the first position whose value is at least a target.
- [ ] Find first and last occurrence of a repeated target.
- [ ] Find a value in a rotated sorted array; state how duplicates affect complexity.
- [ ] Find an integer square root without overflow.
- [ ] Stretch: binary-search the minimum feasible shipping capacity; prove monotonicity.

Reference: [SearchSort](src/dsa/searchsort/SearchSort.java).

## 5. Linked lists

- [ ] Build a singly linked list with add-first, add-last, search, and removal.
- [ ] Reverse it iteratively; draw every pointer change.
- [ ] Find the middle using slow/fast pointers.
- [ ] Detect a cycle and explain why fast and slow eventually meet.
- [ ] Merge two sorted lists without allocating a new node for every value.
- [ ] Remove the nth node from the end with a documented invalid-n policy.
- [ ] Stretch: build a doubly linked list with sentinel nodes.
- [ ] Implement LRU eviction and prove the recency invariant.

References: [SinglyLinkedList](src/dsa/linkedlist/SinglyLinkedList.java), [LruCache](src/dsa/hashing/LruCache.java).

## 6. Stacks, queues, and deques

- [ ] Use an `ArrayDeque` as a stack, then as a FIFO queue.
- [ ] Validate nested brackets and define behavior for non-bracket characters.
- [ ] Design a minimum stack supporting constant-time minimum lookup.
- [ ] Implement a queue using two stacks; explain amortized cost.
- [ ] Find the next strictly greater value for each position.
- [ ] Find sliding-window maximums using a deque of indices.
- [ ] Model undo/redo and clear redo history after a new edit.
- [ ] Stretch: largest rectangle in a histogram.

Reference: [StackQueueProblems](src/dsa/stacksqueues/StackQueueProblems.java).

## 7. Trees and heaps

- [ ] Traverse a binary tree in preorder, inorder, and postorder.
- [ ] Traverse it level by level with a queue.
- [ ] Compute height with an explicit node-count or edge-count convention.
- [ ] Insert, search, and delete in a BST; state a duplicate policy.
- [ ] Validate a BST using ancestor bounds, not only parent-child comparisons.
- [ ] Find the lowest common ancestor; distinguish BST from general binary tree.
- [ ] Calculate tree diameter.
- [ ] Show how sorted insertion degenerates an unbalanced BST.
- [ ] Find the kth largest value with a heap, including duplicates.
- [ ] Merge k sorted sequences.
- [ ] Find the k most frequent values.
- [ ] Stretch: maintain a running median using two heaps.

References: [BinarySearchTree](src/dsa/trees/BinarySearchTree.java), [HeapProblems](src/dsa/heaps/HeapProblems.java).

## 8. Graphs and connectivity

- [ ] Represent the same graph using an edge list, matrix, and adjacency list.
- [ ] Traverse reachable vertices with BFS and DFS.
- [ ] Count connected components in an undirected graph.
- [ ] Find unweighted shortest-path distances and reconstruct a path.
- [ ] Count islands in a grid; choose four-way or eight-way adjacency explicitly.
- [ ] Detect an undirected cycle.
- [ ] Produce a topological order or report a directed cycle.
- [ ] Find shortest distances with nonnegative weights using Dijkstra.
- [ ] Use disjoint sets to process connectivity queries.
- [ ] Stretch: build a minimum spanning tree with Kruskal and explain the cut property.
- [ ] Stretch: compare Bellman–Ford with Dijkstra on a graph with a negative edge.

References: [GraphAlgorithms](src/dsa/graphs/GraphAlgorithms.java), [DisjointSet](src/dsa/graphs/DisjointSet.java).

## 9. Recursion, backtracking, greedy, and DP

- [ ] Trace recursive factorial including stack frames and an overflow limit.
- [ ] Generate subsets of distinct values.
- [ ] Generate permutations and define duplicate handling.
- [ ] Generate valid bracket sequences with pruning.
- [ ] Solve a small maze and undo choices correctly.
- [ ] Select the maximum number of non-overlapping intervals; prove earliest-finish selection.
- [ ] Demonstrate why greedy coin change can fail.
- [ ] Solve minimum coin change with DP, including unreachable targets.
- [ ] Solve climbing stairs by recursion, memoization, and tabulation.
- [ ] Solve house robber; define whether selecting no house is permitted.
- [ ] Compute longest common subsequence length.
- [ ] Stretch: 0/1 knapsack, then explain why update direction differs for unlimited items.
- [ ] Stretch: longest increasing subsequence in O(n²), then O(n log n).

Reference: [AlgorithmPatterns](src/dsa/algorithms/AlgorithmPatterns.java).

## 10. Specialized structures and real scenarios

- [ ] Insert words into a trie; distinguish exact membership and prefix membership.
- [ ] Extend the trie to enumerate suggestions with an output limit.
- [ ] Implement a Fenwick tree and explain `i & -i`.
- [ ] Implement range sum or minimum with a segment tree and point updates.
- [ ] Choose between prefix sums and a mutable range structure for a sales dashboard.
- [ ] Choose a queue or priority queue for a scheduler and explain fairness implications.
- [ ] Compare a hash set and Bloom filter; state the error model.
- [ ] Compare an ordinary BST, balanced tree, and storage-oriented B+ tree conceptually.
- [ ] Complete two mixed mock interviews, record errors, and repeat the weak patterns.

References: [Trie](src/dsa/tries/Trie.java), [range queries](docs/12-range-queries.md), [real-world case studies](docs/14-real-world-scenarios.md).
