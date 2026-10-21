# 02 — Arrays: positions, scans, and patterns

Previous: [Java foundations](01-java-foundations.md). Next: [Strings](03-strings.md).
Runnable source: [ArrayProblems.java](../src/dsa/arrays/ArrayProblems.java).

## What problem does an array solve?

Suppose a store records one sales total per day. Separate variables `day1`, `day2`, and so on make iteration and arbitrary lookup awkward. An array groups same-typed elements under one name and gives each an integer position.

```text
index:     0   1   2   3
sales:   [ 5 | 2 | 7 | 1 ]
```

`sales[2]` directly addresses the third slot. Java arrays have a fixed length, zero-based indices, runtime bounds checks, and default element values. An `int[]` initially contains zeros; an object array initially contains null references. Think of indexed slots, while remembering the Java language does not require you to manage raw addresses or promise a particular JVM object layout.

## Why does Java also provide ArrayList?

An array is a good fit when the size is known, primitives matter, or a grid/table is naturally indexed. `ArrayList<Integer>` provides a resizable sequence when item count changes. It maintains an internal array with spare capacity; growing occasionally copies elements. Append is **amortized O(1)**, although an individual growth can cost O(n). `ArrayList` provides conveniences at the cost of boxing primitives and capacity overhead.

| Operation | Array | ArrayList |
|---|---|---|
| Read/write existing index | O(1) | O(1) |
| Find an unsorted value | O(n) | O(n) |
| Insert/remove in middle, preserving order | O(n) shifting; logical size needed | O(n) shifting |
| Append | Requires free logical slot or new array | Amortized O(1) |
| Resize | Allocate another array | Library manages capacity |

An array is a poor choice for very sparse identifiers: a billion possible customer IDs should not automatically become a billion-element array. A map may better represent the few IDs actually present.

## Worked scenario 1: compact a display

Treat zero as an unused slot: `[0, 3, 0, 7, 2] → [3, 7, 2, 0, 0]`.

`moveZeros` maintains two roles: `read` scans every slot, and `write` identifies where the next retained value belongs.

| Read value | Write action | Retained prefix |
|---:|---|---|
| 0 | Skip | `[]` |
| 3 | Set index 0 | `[3]` |
| 0 | Skip | `[3]` |
| 7 | Set index 1 | `[3,7]` |
| 2 | Set index 2 | `[3,7,2]` |

Fill the remaining slots with zeros. **Why it works:** the retained prefix always contains exactly the nonzero values seen so far, in their original order. `write` never overtakes `read`, so unread input is safe. Time O(n), extra space O(1). In actual sensor data, zero may be a valid measurement; only compact it if the domain explicitly marks zero as empty.

## Worked scenario 2: repeated sales questions

For sales `[5,2,7,1]`, build prefix sums `[0,5,7,14,15]`. `prefix[i]` means the sum before index `i`. The sum of days `[1,4)` is `prefix[4] - prefix[1] = 15 - 5 = 10`.

**Why it works:** subtracting removes the same leading portion from both totals. Build once in O(n) time and space, then answer each range in O(1). The half-open interval includes `from` and excludes `to`; `[2,2)` sums to zero. After modifying a sale, the prefix array becomes stale. Frequent updates suggest a Fenwick or segment tree instead.

## Worked scenario 3: best consecutive profit period

Daily changes `[-2,3,-1,4,-5]` have best contiguous sum `6` from `[3,-1,4]`. Kadane's algorithm tracks the best sum **ending here**. At the next value, either start fresh or extend that previous segment. Those are all possible cases; choose the larger, then update the global best. This is O(n) time and O(1) space. All-negative input must return the least-negative element, not zero, because the requested segment is nonempty.

## Other implemented tools and traps

- Reverse swaps matching ends. Rotation combines three reversals; negative shifts mean left rotation.
- Second-largest **distinct** ignores duplicates and has no answer if fewer than two distinct values exist.
- Two-sum on sorted input moves the left pointer when the sum is too small and the right when too large. Ordering justifies discarding that endpoint.
- Empty arrays are valid. Maximum and maximum-subarray return an empty optional; sum returns zero.
- Aggregate arithmetic uses `long`. Mutating methods change the caller's array; clone first if needed.

## Practice ladder

**Start:** traversal, sum, max, linear search, reverse, sorted check. **Build:** second distinct maximum, zero compaction, remove sorted duplicates, rotate, prefix sums. **Interview:** two-sum, maximum subarray, product except self, merge intervals, trapping rain water. For each, explain a brute-force method before optimizing.

Reference: [ArrayList API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/ArrayList.html).
