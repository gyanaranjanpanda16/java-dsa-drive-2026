# 12. Range queries: answer totals while data changes

Sources: [FenwickTree.java](../src/dsa/rangequeries/FenwickTree.java) and [SegmentTree.java](../src/dsa/rangequeries/SegmentTree.java).

Prerequisites: arrays, prefix sums, binary representation, and tree aggregation. These are specialized structures rather than standard Java collection classes. Their purpose is to balance repeated queries against repeated updates.

## Start with the actual workload

You store daily sales `[10,20,30,40]`. A dashboard repeatedly asks for totals across date ranges, while corrections change individual days. A plain scan costs O(k) for a range of length k. A prefix-sum array makes a query O(1), but correcting an early day changes many later prefixes, costing O(n).

| Approach | Build | Point update | Range sum | Stored space |
|---|---:|---:|---:|---:|
| Plain array | O(n) copy | O(1) | O(k) | O(n) |
| Prefix sums | O(n) | O(n) rebuild/adjust | O(1) | O(n) |
| Supplied Fenwick tree | O(n log n) | O(log n) | O(log n) | O(n) |
| Supplied segment tree | O(n) | O(log n) | O(log n) | O(n) |

If the data never changes, prefix sums are simpler. If there are few queries, an array may be enough. The extra structure is justified by the operation mix, not by being “advanced.”

## Fenwick tree: overlapping responsibility, disjoint queries

Internally index positions from 1. Define `lowbit(i) = i & -i`, the value of the lowest set bit. `tree[i]` stores a block of `lowbit(i)` values ending at i. For four values:

```text
tree[1] = day 0                       = 10
tree[2] = day 0 + day 1               = 30
tree[3] = day 2                       = 30
tree[4] = day 0 + day 1 + day 2 + day 3 = 100
```

To get the first three days, start at internal index 3: take `tree[3]`, then subtract `lowbit(3)=1`. At index 2 take `tree[2]`, then subtract 2 to reach zero. The result is `30+30=60`. These blocks do not overlap and exactly cover the requested prefix.

To correct day 2 by +5, begin at index 3 and add 5 to `tree[3]`, then jump by `lowbit(3)` to index 4 and update `tree[4]`. Those are the blocks containing that position. The last-three-days query becomes `prefixSum(4)-prefixSum(1)=105-10=95`.

**Why it works:** each prefix query removes one power-of-two block from its remaining prefix. Update visits the containing blocks needed by future queries. At most logarithmically many blocks are involved. Our external interface is zero-based: `add(index, delta)` adds a difference, rather than replacing a value. To replace 30 with 35, add 5.

## Segment tree: combine child summaries

A segment tree stores summaries formed from children. In our iterative representation, values live in the second half of a `2*n` array; every internal entry is the sum of its two children. This layout works even when n is not a power of two, though its picture is less tidy than a padded tree.

For a query, move the left and right boundaries upward. If a boundary exposes a whole node contained in the range, include its sum and move past it. The rest can be represented by parent nodes. Included nodes are disjoint and together cover exactly the requested leaves, which establishes correctness. Replacing one leaf recomputes its ancestors, restoring the sum property.

`set(index, value)` replaces a value. Both point replacement and range sum take O(log n); the supplied methods use O(1) auxiliary space. Changing the aggregate to minimum requires a suitable identity and combine operation. Range addition or assignment is **not** implemented here; that extension commonly requires lazy propagation.

## Contracts and real-world limits

Both APIs use half-open ranges `[left, right)`: include left, exclude right. Thus `[1,4)` covers indices 1, 2, 3. Empty ranges return zero, including `[0,0)` on empty structures. Invalid bounds or update indices throw. Negative values are allowed, and input arrays are copied into independent storage. Values and intermediate sums must fit `long`; arithmetic overflow is not automatically detected.

These structures can model live sales corrections, inventory totals across fixed bins, or point updates to telemetry buckets. They assume stable integer positions and in-memory, single-threaded updates. A production dashboard also needs timestamps, persistence, concurrency, and rules for out-of-order events.

## Practice

1. Compute every Fenwick block for eight values by hand.
2. Compare 100 random updates/queries against a plain array.
3. Adapt the segment tree to range minimum and point replacement.
4. Learn coordinate compression when IDs are sparse.
5. Study lazy propagation only after proving the basic range decomposition.
