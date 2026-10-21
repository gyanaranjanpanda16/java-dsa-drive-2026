# 09 — Heaps and priority queues: efficiently choose what comes next

[Back to learning path](../README.md) · [Source](../src/dsa/heaps/HeapProblems.java)

## Motivation: you need the next extreme, not a fully sorted collection

Suppose tasks arrive continuously with different priorities. A FIFO queue chooses the oldest task, which may not be the most urgent. Sorting all tasks after every arrival does unnecessary work. A **priority queue** specifies that removal returns the best-priority item. A **binary heap** is a common implementation.

A min-heap maintains a complete binary tree: all levels are filled except possibly the last, which fills left to right. Every parent is no larger than either child. Therefore, the root is the minimum. The siblings and unrelated branches do not have to be sorted.

```text
         2                array: [2, 5, 3, 9, 7, 8]
       /   \              parent(i) = (i - 1) / 2, for i > 0
      5     3             left(i)   = 2*i + 1
     / \   /              right(i)  = 2*i + 2
    9   7 8
```

An array avoids node references, and completeness keeps height O(log n). Insertion appends then sifts upward until parent order holds. Removing the minimum replaces the root with the last value and sifts downward, swapping with the smaller child. Only one root-to-leaf path changes, so each operation takes O(log n).

| Operation | Binary heap / Java PriorityQueue |
|---|---:|
| Peek minimum | O(1) |
| Insert / remove minimum | O(log n) |
| Search for an arbitrary value | O(n) |
| Build a heap with bottom-up heapify | O(n) |
| Storage | O(n) |

Bottom-up heapify is linear because most nodes are close to leaves and move little; inserting n values separately can instead take O(n log n). Java's `PriorityQueue` supplies this priority-queue behavior. Iteration is **not sorted**; repeatedly polling produces priority order and consumes the queue. For a max-heap, supply a reverse comparator. Do not compare integers using `a - b`: overflow can break ordering; use `Integer.compare` or comparator helpers.

## Worked problem: kth largest in a stream

For `[3,2,1,5,6,4]` and k = 3, retain only the three largest values seen in a **min-heap**:

| Seen prefix | Retained values, shown sorted for explanation | Current third largest |
|---|---|---:|
| 3,2,1 | 1,2,3 | 1 |
| …,5 | 2,3,5 | 2 |
| …,6 | 3,5,6 | 3 |
| …,4 | 4,5,6 | 4 |

After each insertion, remove the smallest if there are more than k elements. The invariant is that the retained multiset contains the k largest values of the processed prefix (or the whole prefix if shorter). Discarded smaller values cannot improve the final top k. The minimum among retained values is exactly kth largest. Duplicates count separately: the second-largest element of `[5,5,1]` is 5. Cost is O(n log(k+1)) time and O(k) space; the `+1` makes the k=1 linear scan case explicit.

## Real-world scenario: merge ordered event feeds

Three services provide sorted event timestamps: A `[1,4,9]`, B `[2,3,8]`, C `[5,7]`. To merge them, keep one cursor per nonempty feed in a min-heap. Poll timestamp 1 from A, then replace it with A's next timestamp 4. Next poll 2 from B and replace it with 3.

Why does this work? Each feed's head is its smallest remaining item. Consequently the minimum of those heads is the smallest remaining item globally. Replacing only the chosen feed's head preserves the invariant. With N events across m feeds, merging costs O(m + N log(m+1)) time and O(m) working space, plus output. Our code validates sorted input, supports empty feeds, and does not mutate inputs.

A live distributed event system also needs to define timestamp accuracy, late events and when a feed is considered complete. This batch merge does not solve those separate concerns.

## Further applications and limits

Use priority queues for an event simulation, top-k results, or the frontier in Dijkstra's algorithm. Add a tie-breaker such as arrival sequence if equally urgent tasks need stable FIFO ordering; the heap does not guarantee that by itself. `PriorityQueue` is not thread-safe. Removing or updating an arbitrary task is not automatically O(log n); a basic Java priority queue lacks an exposed decrease-key operation. Lazy stale entries or an indexed heap are alternative designs. A heap also does not answer “all values between x and y” as naturally as a balanced search tree.

## Practice ladder

1. Implement sift-up and sift-down on an integer array.
2. Find top-k frequent products using a map plus a heap.
3. Merge k sorted linked lists using node cursors.
4. Maintain a running median with one min-heap and one max-heap.
5. Schedule tasks with equal priorities; make tie behavior explicit and test it.

Reference: [Java PriorityQueue contract](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/PriorityQueue.html).
