# Roadmap: beginner to interview preparation

This is a suggested 24-week first pass at roughly 90–120 minutes a day, six days per week. Extend or compress it based on the checkpoints, not the calendar. It is not a promise of mastery or employment within 24 weeks.

| Weeks | Learn and practise | Evidence before progressing |
| --- | --- | --- |
| 1–2 | Java variables, types, conditions, loops, methods, arrays, references | Write and run small methods without copying; explain index bounds and return values |
| 3–4 | Arrays, strings, linear search, prefix sums, Big O | Solve basic array tasks and explain mutation, duplicates, empty inputs, and overflow |
| 5–7 | Hashing, two pointers, sliding windows, sorting, binary search | Recognize when each pattern applies and state its invariant |
| 8–10 | Linked lists, stacks, queues, deques, recursion | Draw pointer changes; choose FIFO/LIFO; explain recursive base cases and stack space |
| 11–13 | Trees, BSTs, traversal, heaps | Compare unbalanced and balanced trees; solve traversal and top-K problems |
| 14–16 | Graph representation, BFS/DFS, topological order, Dijkstra, union-find | Distinguish reachability, unweighted shortest paths, weighted paths, and connectivity |
| 17–18 | Tries, backtracking, greedy proofs | Model shared prefixes; enumerate choices; justify a greedy rule with a proof |
| 19–21 | Dynamic programming, Fenwick/segment trees as an extension | Define states and transitions; compare recomputing, caching, and incremental updates |
| 22–24 | Mixed unseen problems, mocks, revision, role-specific preparation | Explain, implement, and test under realistic time limits; identify weak areas honestly |

## Weekly rhythm

- Four sessions: one topic and one or two carefully chosen problems per session.
- One session: re-solve previous mistakes without notes, then try a variation.
- One session: mixed practice or a mock interview; review reasoning afterward.
- One rest or catch-up day. Consistency should remain sustainable.

During early weeks, spend more time on Java fluency. Later, reduce tutorial time and increase independent problem solving. Build a small Java application alongside this curriculum when you can comfortably write classes; interview exercises alone do not teach all software engineering.

## Checkpoints

**Foundation:** explain why array indices stop at `length - 1`; trace a swap; write a method that does not accidentally mutate its input.

**Patterns:** explain why binary search requires an ordered/monotonic condition; why a negative number can break a positive-only sum window; why a hash map may use more memory than sorting.

**Structures:** implement a basic singly linked list and binary search tree once, then use standard collections when they fit. Explain the operation you need rather than choosing a structure by familiarity.

**Algorithms:** explain why BFS layers produce minimum edge counts; why Dijkstra rejects negative weights; how DP differs from storing arbitrary results.

**Interview:** solve unfamiliar problems with a clear contract, a correct baseline, a justified improvement, readable code, and focused tests. Use a 35–45 minute mock as practice, not a universal company scoring rule.

## Beyond DSA

Prepare clear examples of ownership, failure, collaboration, and technical tradeoffs. Know your projects and resume claims. Add system design, object-oriented design, databases, networking, or concurrency according to the specific role and level. Verify each company's current interview guidance before preparing for a particular loop.

## When to move forward

Move on after you can solve several varied examples independently and explain the common idea. If a new topic depends on a shaky prerequisite, revisit that prerequisite. A checklist count or green GitHub calendar does not prove readiness.
