# 04 — Searching and sorting: ordering makes elimination possible

Previous: [Strings](03-strings.md).
Runnable source: [SearchSort.java](../src/dsa/searchsort/SearchSort.java).

## What problem are we solving?

Searching asks where a value is, or where a boundary belongs. Sorting arranges data according to an order so that later tasks become easier. Neither is a data structure: they are algorithms operating on structures.

For a short, unsorted list with one query, linear search is often sufficient. It visits each item until it finds a match, taking O(n) worst-case time and O(1) extra space. Sorting first costs time and may destroy meaningful original order; it is not automatically an optimization.

For many searches over mostly static data, sorting once and using binary search can be attractive: O(n log n) preparation plus O(log n) per query. If exact-key lookup is the only requirement, a hash map may be more suitable. Ordered range queries justify ordered structures.

## Worked scenario 1: find a product price

Sorted prices: `[3,7,11,15,20]`. Search for `15`.

```text
low=0 high=4 middle=2 → 11 < 15: discard indices 0..2
low=3 high=4 middle=3 → 15: found index 3
```

**Why discarding is safe:** every value to the left of `11` is at most `11`, so none can equal `15`. The symmetric rule applies when the middle value is too large. The invariant is: if a match exists in the still-possible region, it remains inside `[low, high]`.

Each failed comparison removes at least about half the candidates. After about `log₂(n)` steps, none remain. The iterative version uses O(log n) time and O(1) extra space.

The midpoint expression `low + (high - low) / 2` avoids the overflow risk of `low + high`. Updating to `middle + 1` or `middle - 1` guarantees progress. Input **must already be sorted**; checking it inside every search would add O(n) time. The method trusts this documented precondition.

## Worked scenario 2: find the first affordable threshold

For `[3,7,7,11,15]`, the first price at least `7` is index `1`. A normal binary search may return either `7`. `lowerBound` solves the boundary problem deliberately:

```text
target = 7
[3 | 7 7 11 15]
 <7| >=7
```

Maintain `[low, high)` as the unresolved interval. A middle value below target moves `low` right; otherwise `high` becomes `middle`, preserving that position as a possible first answer. When they meet, that index is the boundary. If all values are smaller, return `n`. Empty input returns `0`. This supports insertion positions, threshold filtering, and counting duplicates when paired with an upper bound.

## Worked scenario 3: combine sorted batches

A reporting job receives `[9,3,7,3]` and wants ordered output. Merge sort divides, sorts, and merges:

```text
[9,3,7,3]
 [9,3]  [7,3]
 [3,9]  [3,7]
 [3,3,7,9]
```

During a merge, the smallest remaining element must be at the front of one of the two sorted halves. Pick the smaller front, advance its pointer, and repeat. **Why it works:** the output prefix is sorted and contains the smallest elements already selected; neither half can hide a smaller element behind its current front.

There are O(log n) levels, each merging O(n) total elements: O(n log n) time in all cases. The implementation shares an O(n) buffer and uses O(log n) recursive stack space. It takes the left element on equality, preserving stability. Stability matters for records: sorting by score can preserve earlier arrival order among equal scores. Primitive integers themselves do not expose that distinction.

## Choosing another sort

| Algorithm | Useful lesson/use | Cost and limitation |
|---|---|---|
| Insertion sort | Small or nearly sorted input | O(n²) worst case; O(1) extra space |
| Merge sort | Predictable runtime, stable merging | O(n log n); auxiliary storage |
| Quicksort | Partitioning and selection | Expected O(n log n); O(n²) worst case without safeguards |
| Heap sort | Sorting with a heap | O(n log n); O(1) auxiliary array space, usually unstable |
| Counting sort | Small bounded integer domain | O(n+k) time/storage; poor for huge sparse ranges |

Use Java's sorting APIs for routine application work; implement algorithms here to understand their guarantees. Know the API's behavior for your element type rather than assuming every overload uses the same algorithm.

## Practice ladder

**Start:** linear search, binary search, insertion sort. **Build:** first/last occurrence, merge sorted arrays, lower/upper bounds. **Interview:** rotated-array search, kth-largest selection, binary search on a monotonic answer predicate. Always explain *why the discarded region cannot contain the answer*.

Reference: [Arrays API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Arrays.html).
