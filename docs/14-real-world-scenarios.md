# 14 — From a real requirement to a data structure

These are simplified engineering models, not claims about how a named company implements its systems. A data structure solves one part of a service; persistence, concurrency, access control, failures, and network behavior require additional design.

For every scenario, ask: **What are the entities? Which operations happen most? How large is the data? Must order be preserved? Does it change? What must always remain true?**

## 1. Daily sales dashboard: arrays → prefix sums → Fenwick tree

**Requirement:** store sales for successive days and answer totals over a date interval. Map the first day to index 0. Suppose sales are `[4, 2, 7, 1]`.

A plain array is appropriate because each day has a stable integer position. To total days 1 through 3, sum `2 + 7 + 1 = 10`: O(length of interval) per query. This is enough for a tiny dashboard with rare queries.

With many queries and no updates, build prefix sums `[0, 4, 6, 13, 14]`. Define `prefix[i]` as the total of the first `i` days. An inclusive interval `[l, r]` totals `prefix[r + 1] - prefix[l]`. The prefix before `l` cancels, leaving exactly the requested days. Construction takes O(n); each query takes O(1); extra storage is O(n).

The runnable `ArrayProblems.rangeSum(prefix, from, to)` uses a half-open interval `[from, to)`: include `from`, exclude `to`. For inclusive days 1 through 3, call it with `from=1, to=4`. Always check endpoint conventions before using a range API.

If day 1 changes from 2 to 5, every later prefix changes. Rebuilding is O(n). A Fenwick tree instead stores overlapping binary-sized blocks. Updating a day and querying a prefix both take O(log n). To assign a new value with an additive tree, update by `newValue - oldValue`, not by `newValue`.

**Boundary:** sums use `long`; decide whether absent days mean zero or missing data. A Fenwick tree supports additive aggregates naturally, while a segment tree is more flexible for range minimum or other associative operations. Start with the simplest structure that fits the workload.

See [arrays](02-arrays.md) and [range queries](12-range-queries.md).

## 2. Text editor undo/redo: two stacks

**Requirement:** undo the most recent edit first. The order is LIFO, so use an undo stack and a redo stack, typically with `ArrayDeque` in single-threaded Java code.

After edits A, B, C, the undo stack is `[A, B, C]`, with C on top. Undo removes C, applies its inverse, and puts C on redo. A second undo reverses B. Redo reapplies B and puts it back on undo. A new edit after undo clears the redo history because the user has created a different future.

**Why it works:** the top always represents the newest action that has not yet been undone; older dependent actions remain beneath it. End operations are amortized O(1), excluding the cost of applying an edit.

**Boundary:** storing entire document snapshots is simple but can consume O(history × document size) memory. Storing commands or differences uses less space but requires a correct inverse. A stack alone does not implement collaborative editing or crash recovery.

See [stacks and queues](06-stacks-queues.md).

## 3. Print jobs and request processing: queues

**Requirement:** process accepted jobs in arrival order. Enqueue at the back, dequeue from the front: FIFO. If jobs arrive A, B, C, a single worker starts them A, B, C.

**Why it works:** each accepted job is placed behind all earlier waiting jobs. A queue avoids shifting an array after every removal. An `ArrayDeque` gives efficient ends for a local, single-threaded demonstration.

**Boundary:** FIFO start order does not imply FIFO completion with multiple workers. Unbounded queues can exhaust memory when arrivals exceed processing speed. In concurrent Java services, a suitable `BlockingQueue` can support producer/consumer coordination and bounded capacity. Durable distributed job processing needs more than an in-memory queue. Priority work requires a priority queue, changing fairness semantics.

## 4. Product lookup and duplicate detection: hash map and set

**Requirement:** find a product by exact ID, or determine whether an event ID has already been seen. Use a map for ID → product and a set for membership alone.

For events `[A, B, A]`, start with an empty set. Insert A, insert B, then reject the repeated A. Expected processing time is O(n) with O(number of distinct IDs) space, assuming suitable hashing.

**Why it works:** a hash routes equal keys to a compatible lookup location, while equality checks distinguish keys that collide. Hashes are not unique identifiers. Equal objects must have equal hash codes; unequal objects may share a hash code.

**Boundary:** this handles exact matching, not prefix search or ordered ranges. Mutable keys can become unfindable if fields used by equality/hash code change. An in-memory deduplication set needs an expiry or size policy for an infinite stream, and a restart loses it unless state is persisted.

See [hashing](07-hashing.md).

## 5. Recently used cache: hash map + doubly linked order

**Requirement:** keep at most two entries and evict the least recently used entry. A hash map locates an entry by key; linked order records recency.

Put A, then B: least-to-most recent is `[A, B]`. Read A: move A to the end, yielding `[B, A]`. Put C at capacity: remove B, then append C, yielding `[A, C]`.

**Why it works:** every successful access moves exactly that node to the most-recent end. The oldest node therefore remains at the least-recent end. Map lookup is expected O(1); unlinking a known doubly linked node and reconnecting it at an end is O(1).

**Java choice:** access-order `LinkedHashMap` can maintain this order. A custom map-plus-list implementation is useful for learning. A plain `LinkedList.remove(value)` still searches, so it does not automatically make this cache O(1).

**Boundary:** decide null handling, capacity zero, updates, and whether reads refresh recency. Production caches may need concurrency, expiration, weighted capacity, and protection from duplicate expensive loads.

See [linked lists](05-linked-lists.md) and [Java collections](17-java-collections.md).

## 6. Top scores from a stream: a bounded min-heap

**Requirement:** retain the largest three scores seen so far. Keep a min-heap of size at most three. For `[5, 1, 9, 3, 8]`, the retained sets evolve to `{5}`, `{1,5}`, `{1,5,9}`, `{3,5,9}`, `{5,8,9}`.

After inserting a score, remove the smallest if the size exceeds three. The root of the final heap is 5, the third-largest score.

**Why it works:** every discarded value is no larger than the three retained candidates at the time it is removed. Future arrivals can only increase the threshold. Processing is O(n log k), with O(k) storage, versus sorting all n values in O(n log n).

**Boundary:** equal scores count separately unless distinct scores are explicitly required. A priority queue iterator is not sorted output. Use repeated removal or sort a copy when presenting ordered results. Ranking every player with frequent arbitrary score updates may need a different structure.

See [heaps](09-heaps.md).

## 7. Delivery routes and course prerequisites: two different graph problems

**Routes:** locations are vertices; roads are edges. For A→B cost 2, B→C cost 3, A→C cost 9, the cheapest route from A to C costs 5 through B. Dijkstra settles the smallest known distance first and relaxes outgoing edges. With nonnegative costs, an unsettled detour cannot improve a settled shortest distance through a cheaper prefix. BFS would minimize the number of edges and choose the one-edge A→C route, which is not cheapest here.

**Prerequisites:** courses are vertices; prerequisite→dependent edges represent order. Repeatedly choose a vertex with zero remaining incoming edges, output it, and remove its outgoing constraints. If vertices remain but none is available, there is a directed cycle: the requirements cannot all be satisfied.

**Boundary:** roads may be directed, disconnected, or weighted; define the model before coding. Dijkstra does not handle negative weights. A topological order is not a shortest path and may not be unique. Parallel edges and self-loops must have deliberate semantics.

See [graphs](10-graphs.md).

## 8. Autocomplete: trie plus ranking

**Requirement:** find words beginning with `ca`. Insert `car`, `cat`, and `dog` into a trie, sharing the c→a path. Walk the prefix once, then explore descendants of that node.

**Why it works:** each path represents a prefix; every descendant extends that prefix. Finding its node takes O(prefix length) with bounded child lookup. Returning suggestions also costs the work needed to traverse and emit them—autocomplete is not O(prefix length) regardless of result count.

**Boundary:** a boolean word-ending marker distinguishes `car` from `cart`. A lowercase-English teaching trie does not handle all languages. Real search needs normalization, ranking, memory limits, and often fuzzy matching. A sorted list plus binary search can be simpler for a small mostly static dictionary.

See [tries](11-tries-union-find.md).

## 9. Accounts connected by shared identifiers: disjoint sets

**Requirement:** determine groups connected by shared identifiers. If A shares an identifier with B and B with C, all three belong to one connected component—even if A and C do not directly share one.

Union A with B, then B with C. `find(A) == find(C)` tells us they belong to the same component. Parent pointers lead to a representative; path compression shortens future traversals, and union by size avoids repeatedly creating tall trees. Together these yield near-constant amortized operations, conventionally O(α(n)).

**Boundary:** shared data is only a modeling rule, not proof two people are the same person. Basic union-find does not support arbitrary edge deletion or provide the actual path between members. Use a graph when you need paths or traversals.

## 10. Choosing coin denominations: dynamic programming

**Requirement:** form a target amount with as few coins as possible, with unlimited copies of positive denominations. Greedy largest-first fails for `[1, 3, 4]`, target 6: it chooses 4+1+1, while 3+3 uses two coins.

Let `dp[a]` be the minimum coins for amount a. Set `dp[0]=0`; unreachable positive amounts start as infinity. For each amount, try every coin c≤a: `dp[a] = min(dp[a], 1 + dp[a-c])` when the smaller amount is reachable.

**Why it works:** every valid solution ends with some coin. Removing that coin leaves a smaller subproblem. Trying every possible final coin includes an optimal solution, and increasing amount order makes its prerequisites available. Time O(amount × denominations), auxiliary space O(amount).

**Boundary:** this complexity is proportional to the numeric amount, not just the number of digits representing it. Limited inventory requires a different state or transition. A correct recurrence does not make a huge state space affordable.

See [algorithm patterns](13-algorithm-patterns.md).
