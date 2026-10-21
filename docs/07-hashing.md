# 07 — Hash maps and sets: remember what you have already seen

[Back to learning path](../README.md) · [Algorithms](../src/dsa/hashing/HashingProblems.java) · [LRU cache](../src/dsa/hashing/LruCache.java)

## Why hashing exists

Suppose you repeatedly ask, “Have I seen product 42?” Searching an unsorted list each time costs O(n). A hash table computes a hash from the key, uses it to choose a bucket, and checks candidate keys there. It spends memory to make lookup, insertion, and deletion **expected O(1)** under suitable hashing. Collisions happen when different keys lead to the same bucket; equality checks distinguish them.

A **map** associates keys with values, such as product → count. A **set** records membership, such as unique product IDs. Java provides `Map`/`Set` interfaces and `HashMap`/`HashSet` implementations of these older computer-science concepts. `Map` is not a subtype of `Collection`.

```text
key → hash computation → bucket → equality check → associated value
```

Hashing is not sorting, encryption, or a guarantee of uniqueness. `HashMap` iteration order is unspecified. `LinkedHashMap` preserves a defined encounter order; `TreeMap` maintains sorted keys and supports range/navigation operations with O(log n) basic operations. Our frequency example uses `LinkedHashMap` so its demonstration output follows first-seen key order.

## Java equality: the contract behind correct lookup

For a custom key, implement compatible `equals` and `hashCode`: equal objects must have equal hash codes. Unequal objects may share hashes. Avoid changing fields involved in equality/hash computation while an object is a key: it may then reside in the wrong bucket for its new hash. Immutable keys, including suitable records, simplify reasoning. Hash collections have boxing/object overhead for primitive integers; a small known value range may be better served by an `int[]` frequency table.

Expected constant time is not a universal worst-case promise. Hash computation and equality can themselves depend on key length. Table resizing costs O(n) occasionally. The algorithms here use integer/long keys with bounded-size operations and describe expected time.

## Worked problem: Two Sum without reusing an element

Input `[2, 7, 11, 15]`, target 9:

| Index | Value | Needed | Previously seen | Action |
|---|---:|---:|---|---|
| 0 | 2 | 7 | empty | Remember 2 → 0 |
| 1 | 7 | 2 | 2 → 0 | Return indices 0 and 1 |

Before processing index i, the map contains only earlier indices. If its complement is present, the pair is valid and distinct. If no complement exists, remembering the current value preserves the invariant. Query **before** insertion so `[3]` cannot produce target 6 using itself twice. `[3,3]` may validly do so with distinct indices. Long subtraction prevents integer overflow from manufacturing a false complement. Cost: expected O(n) time, O(n) space; no solution returns an empty array.

## Real-world scenario: zero-net transaction intervals

A ledger has changes `[1, -1, 0]`. How many contiguous intervals net to zero? Prefix sums are `[0, 1, 0, 0]`. A range from l to r sums to `prefix[r+1] - prefix[l]`. Thus, for each current prefix S, count earlier prefixes equal to `S - target`.

| New value | Running sum | Earlier matching prefix count | Total intervals |
|---|---:|---:|---:|
| 1 | 1 | 0 | 0 |
| -1 | 0 | 1 | 1 |
| 0 | 0 | 2 | 3 |

Start with `{0:1}` to represent the empty prefix. Add the current prefix only **after** querying to avoid counting an empty interval. Frequencies matter: a set loses repeated prefixes. This works with negative values, unlike a basic positive-only shrinking-window strategy. The implementation uses long for both sums and counts and handles a target whose subtraction would overflow. It runs in expected O(n) time and O(n) space. Monetary software must additionally specify exact units, rounding, and accounting rules; this is the interval-counting component.

## Combining structures: LRU cache

The included `LruCache<K,V>` maps keys to doubly linked nodes. Reading/updating a key moves it to the front. Exceeding capacity evicts the tail. With capacity 2: put A, put B, read A, put C → B is evicted. The map finds a node; links change recency without scanning. Maintaining both structures consistently gives expected O(1) get/put and O(capacity) storage. Null keys/values are rejected, so null from get means miss. It is deliberately single-threaded and has no expiry. Java's access-order `LinkedHashMap` can express a similar policy with less handwritten code.

## Practice ladder

1. Detect duplicates and count frequencies; compare an array, set, and map.
2. Group anagrams; define the supported character alphabet.
3. Find the longest consecutive sequence without sorting.
4. Count subarrays summing to k, including negatives and repeated zero prefixes.
5. Extend the LRU exercise with explicit removal; preserve map/list agreement after every operation.

Reference: [Java HashMap contract](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/HashMap.html).
