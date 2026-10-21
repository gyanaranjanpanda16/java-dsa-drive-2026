# 06 — Stacks, queues, and deques: let processing order solve the problem

[Back to learning path](../README.md) · [Source](../src/dsa/stacksqueues/StackQueueProblems.java)

## Three rules, multiple implementations

A **stack** is last-in, first-out (LIFO): the newest unfinished operation is handled first. A **queue** is first-in, first-out (FIFO): the oldest waiting item is handled first. A **deque** supports insertion/removal at both ends. These describe allowed operations and ordering, not whether storage uses arrays or nodes.

```text
Stack: push A, push B, pop → B
Queue: offer A, offer B, poll → A
Deque: addFirst A, addLast B → [A, B]
```

Java offers interfaces and implementations so callers can express these requirements. `Deque<Integer> stack = new ArrayDeque<>();` makes a local stack; use `push`, `peek`, `pop`. For a queue, use `offerLast`, `peekFirst`, `pollFirst`. Mixing which end you insert into and remove from is a common bug. `ArrayDeque` is resizable-array based, prohibits null elements, and is not thread-safe. The old `Stack` class exists, but the Java API recommends deque implementations for LIFO operations.

| Operation in ArrayDeque | Cost |
|---|---:|
| Add/remove at either end | Amortized O(1) |
| Peek at an end | O(1) |
| Search/remove a specified value | O(n) |
| Store n elements | O(n) space |

Amortized O(1) allows occasional O(n) resizing; it is not a promise that each individual operation takes constant time. `pollFirst()` returns null on emptiness; `removeFirst()` throws. Choose deliberately.

## Why a stack validates nested brackets

In `{[()]}`, the opening `(` must close before `[`, which must close before `{`. The most recently opened bracket is the next one that may close: exactly LIFO.

| Input consumed | Stack, bottom → top |
|---|---|
| `{` | `{` |
| `{[` | `{ [` |
| `{[(` | `{ [ (` |
| `{[()` | `{ [` |
| `{[()]` | `{` |
| `{[()]}` | empty |

The invariant is that the stack contains the unmatched opening brackets in nesting order. A closing bracket must match its top; any other match would cross nesting boundaries. End-of-input requires an empty stack. The example API accepts only bracket characters, rejects null or other characters, and considers empty text balanced. It runs in O(n) time and O(n) space. A parser must additionally tokenize strings, comments, and escaping; bracket matching alone is not a Java syntax checker.

## Why a monotonic stack finds the next greater element

For `[2, 1, 4, 3]`, indices 0 and 1 wait on the stack until value 4 arrives at index 2. It resolves both. Index 2 then waits because 3 is smaller. Result: `[2, 2, -1, -1]`, expressed as **indices**, with -1 meaning absent. Equal values do not count as strictly greater.

Each unresolved index remains stacked because no scanned later value has beaten it. The first value that does beat it is therefore its nearest greater value to the right. Every index enters once and leaves at most once: the nested loop is O(n), not O(n²). This models “days until a warmer temperature” or “next measurement above today's value.”

## Worked scenario: a rolling sensor peak

Given readings `[1, 3, -1, -3, 5]` and window size 3, the windows have maxima `[3, 3, 5]`. Re-scanning each window costs O(nk). Our deque stores candidate **indices** with decreasing values:

1. Remove indices that have left the current window.
2. Remove smaller or equal candidates from the back.
3. Append the new index; the front is the maximum.

Discarding an older candidate is safe when a newer value is at least as large: the newer one dominates it and stays in future windows longer. Each index is inserted/removed at most once, yielding O(n) time and O(k) working space. Storing indices is essential to know when a candidate expires. This lesson assumes windows by item count; a time-based stream also needs timestamps and an explicit late-arrival policy.

## Real-world selection and limits

Use a stack for undo history or depth-first traversal, a queue for a single-process job dispatcher or BFS, and a deque when both ends matter. A production job system additionally needs persistence, retries, acknowledgements, and concurrency guarantees. FIFO alone does not give priority handling, fairness across customers, or durability. Choose a heap for priority order and an appropriate concurrent/blocking queue for shared-thread coordination.

## Practice ladder

1. Implement a fixed-capacity circular queue with wraparound indices.
2. Build a queue from two stacks; explain amortized cost of transferring elements.
3. Implement a minimum stack that supports duplicate minima.
4. Solve daily temperatures, largest histogram rectangle, then rolling-window maximum.
5. Add an undo/redo model with two stacks; new edits must invalidate redo history.

Reference: [ArrayDeque API and complexity](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/ArrayDeque.html).
