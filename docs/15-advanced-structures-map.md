# 15 — The wider data-structure map

Core interview structures are implemented in this repository. The structures below extend the map; unless explicitly linked to source, these are **conceptual study notes and future exercises**, not implemented classes. Study them when the workload or role needs them. “All data structures” is not a finite curriculum: variants continue to be developed.

## First distinguish interface from representation

A list describes an ordered sequence. An array list and a linked list are representations with different operation costs. A queue describes removal order; a circular array and linked nodes can both implement it. A graph describes relationships; an adjacency matrix, adjacency list, or edge list represents it. A map associates keys with values; hashing and search trees provide different implementations.

Choosing by a class name before identifying operations reverses the design process.

| Structure | Core mechanism / invariant | Useful workload | Cost or limitation |
| --- | --- | --- | --- |
| Dynamic array | Capacity can exceed logical size; resize copies elements | Indexed access, append-heavy collections | Indexed access O(1), append amortized O(1), middle shifts O(n) |
| Doubly linked list | Each node connects to its predecessor and successor | Relink a known node, recency order | O(1) relink once located; finding by value/index O(n) |
| Circular buffer | Wrap head/tail positions modulo capacity | Bounded FIFO streams | O(1) ends; explicitly distinguish full from empty |
| Monotonic stack/deque | Remove candidates dominated by newer values | Next-greater and sliding maximum | Usually O(n) total since each index enters/exits once |
| AVL tree | Subtree heights differ by at most one | Ordered lookups and updates | O(log n) operations; rotations maintain balance |
| Red-black tree | Coloring rules bound tree height | Ordered maps and sets | O(log n) lookup/update; more complex balancing |
| B-tree / B+ tree | Many keys per node reduce tree height; B+ variants link leaf-level data | Storage-oriented ordered indexes | Optimized around page/block access; details depend on implementation |
| Skip list | Multiple linked levels let searches skip ranges | Ordered maps, including concurrent designs | Expected O(log n); randomized structure and extra links |
| Trie | Paths encode prefixes | Prefix membership and enumeration | O(key length) path work; child storage may use much memory |
| Radix tree | Compress single-child chains into edge labels | Sparse prefix indexes | More involved splitting and prefix comparison |
| Suffix array / suffix tree | Index all suffixes of a sequence | Many substring queries over mostly static text | Construction/storage tradeoffs; not just prefix search |
| Fenwick tree | Store binary-aligned prefix aggregates | Point increments and range sums | O(log n) update/query; operation must fit prefix combination |
| Segment tree | Each node summarizes an interval | Range aggregate and point update | O(log n) operations and O(n) storage |
| Lazy segment tree | Store deferred range updates with segment summaries | Many range updates and queries | O(log n) for supported operations; tags must compose correctly |
| Sparse table | Precompute aggregates over power-of-two blocks | Static range min/max | O(n log n) preprocessing/space; O(1) idempotent queries; updates expensive |
| Disjoint set / union-find | Parent forest identifies components | Incremental undirected connectivity | O(α(n)) amortized with both optimizations; no general deletion |
| Bit set / bitmap | Encode membership using individual bits | Dense bounded integer universes | Compact; operations work by machine words; sparse huge IDs can waste space |
| Bloom filter | Multiple hashes set/check bits | Fast negative membership screening | False positives possible; standard form cannot safely delete; not exact membership |
| Count-min sketch | Hash into several counter arrays | Approximate frequencies in streams | Collisions overestimate counts for nonnegative updates; error/memory tradeoff |
| LRU cache | Lookup map plus access order | Evict least recently accessed item | Expected O(1) basic access; adds eviction semantics to storage |
| LFU cache | Track frequency and tie-breaking order | Evict least frequently used item | More state and more complicated updates/aging policy |
| Interval tree | Augment ordering with subtree interval information | Find overlapping time/position intervals | Must maintain augmentation on updates; distinct from ordinary segment aggregation |
| k-d tree | Recursively partition points by coordinate | Spatial search in low dimensions | Performance can degrade in high dimensions or unfavorable data |
| R-tree | Group objects by bounding rectangles | Spatial indexing of areas/objects | Overlapping boxes can increase search work |
| Persistent structure | Share unchanged nodes between versions | Snapshots, immutable histories | Extra allocations and retention; persistent means versions survive, not necessarily disk storage |
| Rope | Tree of text chunks | Large text edits and concatenation | More complex than a string; balancing and chunk policy matter |

## Worked comparison: exact set or Bloom filter?

Suppose a storage service wants to avoid unnecessary disk reads for keys that are absent. An exact in-memory hash set is straightforward but might exceed the memory budget. A Bloom filter can say “definitely absent” or “possibly present.” Only the second answer triggers the slower exact lookup.

All bits for an inserted key are set, so an absent required bit rules the key out. Collisions can cause all bits for a never-inserted key to be set, producing a false positive. Thus the filter cannot replace exact authorization, payment deduplication, or any decision requiring certain presence. Without errors or unsupported deletion, the ordinary insertion-only filter has no false negatives. This is a tradeoff in certainty for memory, not a faster exact hash set.

## Worked comparison: static minimum or changing sum?

For fixed sensor readings and millions of minimum queries, a sparse table can justify its preprocessing. For changing totals, use a Fenwick tree if updates are point increments and queries are sums. For changing minimums, a segment tree works because summaries combine with `min`. Subtracting two prefix minimums does not recover an interval minimum: minimum lacks the inverse that sum has.

## Java connections

`TreeMap` and `TreeSet` offer sorted map/set behavior; `BitSet` offers packed bits; `ConcurrentSkipListMap` offers concurrent sorted-map behavior. Java's standard library does not provide every specialized tree or graph algorithm as a ready-made collection. A library's data structure alone does not guarantee application-level atomicity, durability, or business correctness.

Use [the collections guide](17-java-collections.md) first. Implement a balanced tree or probabilistic structure later to deepen understanding, not as a prerequisite for learning array problems.
