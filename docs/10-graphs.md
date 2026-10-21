# 10. Graphs: relationships, routes, and dependencies

Read after queues, stacks, and heaps. Source: [GraphAlgorithms.java](../src/dsa/graphs/GraphAlgorithms.java).

## Why a graph exists

An array says “item at position i”; a tree says “parent and children.” Neither directly models a city with several roads into each junction or a task with several prerequisites. A **graph** stores vertices (entities) and edges (relationships). The idea predates Java. Java supplies arrays, lists, queues, and heaps from which we construct it; there is no general-purpose `Graph` class in the Java Collections Framework.

A directed edge `A -> B` permits travel from A to B. An undirected road requires both `A -> B` and `B -> A` in our adjacency list. A weighted edge includes a cost, such as minutes. A cycle returns to an earlier vertex; a disconnected graph has unreachable regions. Choose these meanings before coding.

## Representation and tradeoffs

Our unweighted `int[][]` stores the outgoing neighbors of each vertex. Weighted examples use `List<List<Edge>>`. Vertex IDs run from `0` to `V-1`.

| Representation | Storage | Check one edge | Visit neighbors | Useful when |
|---|---:|---:|---:|---|
| Adjacency list | O(V + E) | O(outdegree) | O(outdegree) | Few relationships relative to V² |
| Adjacency matrix | O(V²) | O(1) | O(V) | Dense graph; frequent edge existence checks |

The supplied methods validate even unreachable neighbor lists and reject invalid vertex IDs. BFS/DFS require an existing source; topological ordering accepts an empty graph. None changes the caller's graph.

## Worked scenario: fewest transfers

Consider `0 -> [1,2]`, `1 -> [3]`, `2 -> [3]`, `3 -> []`, `4 -> []`. We want the fewest edges from stop 0.

| Step | Removed from queue | Newly discovered | Distances |
|---|---|---|---|
| Start | — | 0 | `[0,-1,-1,-1,-1]` |
| 1 | 0 | 1, 2 | `[0,1,1,-1,-1]` |
| 2 | 1 | 3 | `[0,1,1,2,-1]` |
| 3 | 2 | none | unchanged |
| 4 | 3 | none | unchanged |

**Why BFS works:** FIFO processes all vertices at distance d before vertices at d+1. If an undiscovered neighbor had a shorter path, the preceding vertex of that path would already have discovered it. Mark a vertex when enqueuing it to prevent duplicate work. An unreachable stop remains `-1`.

DFS instead follows one branch before returning to another. Our iterative implementation stores a vertex and its next neighbor in each stack frame, matching recursive exploration without consuming the Java call stack. Use it for exploration and as a building block for cycle/component algorithms; its first route is not necessarily shortest. Both traversals cost O(V + E) time and O(V) extra space, including validation.

## Worked scenario: fastest delivery

Suppose `0 -> 1` costs 8, `0 -> 2` costs 2, `2 -> 1` costs 1, and `1 -> 3` costs 1. BFS favors the one-edge route to 1, but it costs 8. **Dijkstra** finds the route through 2 costing 3 and reaches 3 with total cost 4.

The priority queue extracts the smallest tentative distance. With nonnegative weights, extending another unfinished path cannot produce a cheaper route to that extracted vertex. Relaxation asks whether `distance[u] + weight` improves a neighbor. Our lazy heap can contain old entries; skip them when their stored distance differs from the latest best distance. This version costs O(V + E log(E + 1)) time and O(V + E) extra space. Costs are nonnegative `int`; path totals are `long`; `Long.MAX_VALUE` means unreachable. Negative weights are rejected; Bellman–Ford is a further-study option for graphs with negative edges.

## Worked scenario: build prerequisites

Interpret `A -> B` as “A must finish before B.” Kahn's topological algorithm repeatedly schedules a vertex with indegree zero, then removes its outgoing dependencies. Every chosen task is ready. If fewer than V tasks are scheduled, the remaining dependencies contain a directed cycle. Cost: O(V + E) time, O(V) extra space. This gives a valid order, not a parallel scheduler or resource-allocation policy.

## Practice and limits

1. Trace BFS and DFS on a diamond and a disconnected graph.
2. Add a predecessor array and reconstruct one shortest route.
3. Count islands in a grid using BFS; model land cells as vertices.
4. Return a cycle witness for invalid course prerequisites.
5. Study minimum spanning trees, Bellman–Ford, and strongly connected components.

Real route systems also handle changing traffic, turn restrictions, and huge indexes. These examples isolate the underlying graph problem; they are not full navigation services.
