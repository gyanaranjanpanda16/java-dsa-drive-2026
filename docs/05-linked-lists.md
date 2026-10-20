# 05 — Linked lists: change connections instead of shifting elements

[Back to learning path](../README.md) · [Source](../src/dsa/linkedlist/SinglyLinkedList.java)

## The problem this structure solves

An array gives fast indexed access, but inserting near its beginning requires shifting later elements. A linked list stores individual **nodes** connected by references. Changing a connection can insert or remove a node without moving every later value. This idea predates Java; Java's `LinkedList` provides an implementation of the `List` and `Deque` interfaces rather than introducing linked lists to computer science.

```text
head                              tail
 ↓                                 ↓
[10 | next] → [20 | next] → [30 | null]
```

A node contains a value and a reference to the next node. `head` locates the first node. Keeping `tail` locates the last one without traversal. An empty list has both references set to `null`. These are references, not raw addresses you calculate in Java.

## How operations work, and their actual costs

| Operation | Singly linked list here | Why |
|---|---:|---|
| Add at front | O(1) | New node points to old head; head changes |
| Add at back | O(1) | The stored tail points to the new node |
| Find a value / access index i | O(n) worst case | Follow links one at a time |
| Remove first matching value | O(n) | Finding its predecessor requires traversal |
| Remove after a known predecessor | O(1) | Bypass one node |
| Reverse | O(n) time, O(1) extra space | Rewire each link once |

The whole structure occupies O(n) space, including per-node reference and object overhead. “Linked-list deletion is O(1)” is incomplete: it is O(1) only when the required node/predecessor is already available. Removing by index or by value usually includes O(n) searching. Our API returns a copy from `toArray()`, so changing that copy does not modify the list.

## Worked trace: reversing a chain

Start with `10 → 20 → 30 → null`. Track `previous`, `current`, and saved `next`:

| Step | Reversed prefix | Untouched suffix |
|---|---|---|
| Before loop | empty | 10 → 20 → 30 |
| Process 10 | 10 → null | 20 → 30 |
| Process 20 | 20 → 10 → null | 30 |
| Process 30 | 30 → 20 → 10 → null | empty |

Before replacing `current.next`, save it. Otherwise, the remainder becomes unreachable from the traversal. The loop invariant is: **previous heads exactly the reversed processed prefix; current heads the unchanged remaining suffix**. One iteration preserves this statement and processes one more node. When current becomes null, the prefix is the entire reversed list. Update tail to the old head so later appends still work.

## Real-world scenario: bounded cache recency

Imagine a cache holding users A, B, C. Whenever B is read, it should become most recently used. A plain list would need to search for B; a plain hash map does not record recency. Combine a map from key to node with a **doubly linked list**:

```text
most recent ← B ⇄ C ⇄ A → least recent
```

The map finds B in expected O(1). Its `previous` and `next` references allow O(1) unlinking and reinsertion at the front. The tail identifies the eviction candidate. See the runnable [LRU cache](../src/dsa/hashing/LruCache.java) and [hashing lesson](07-hashing.md). This is an educational in-memory mechanism, not a distributed cache with expiry, persistence, or concurrency control.

## Variants and choosing in Java

- **Singly linked:** one next reference; simple forward traversal, needs predecessor for deletion.
- **Doubly linked:** previous and next; bidirectional traversal and deletion of a known node, with more memory and links to maintain.
- **Circular:** last node connects back to first; useful for cyclic traversal, but normal null-terminated loops no longer work.
- **ArrayList:** usually a better starting point for indexed reads and traversal because it avoids a node object per element.
- **ArrayDeque:** usually a better starting point for a local stack or queue; it avoids per-node allocation and provides efficient end operations.

Java's `LinkedList` is doubly linked. It does not expose its internal nodes as public handles; do not assume arbitrary `remove(value)` is O(1). Learn custom nodes to understand interview pointer problems, then choose library collections based on workload.

## Practise and check yourself

1. Trace removal from an empty list, a singleton, the head, and the tail.
2. Implement finding the middle using slow and fast pointers; state which middle you return for even length.
3. Detect a cycle with Floyd's two-pointer method and explain why speeds one and two must meet inside a cycle.
4. Merge two sorted chains by relinking nodes; decide whether inputs may be mutated.
5. Reverse nodes in groups of k; draw links before writing code.

Ready to progress: explain why saving `next` matters, why tail must change on reversal, and when list insertion loses its apparent advantage to the cost of finding the location.

Reference: [Java LinkedList contract](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/LinkedList.html).
