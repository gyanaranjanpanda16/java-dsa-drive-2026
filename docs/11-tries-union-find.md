# 11. Tries and disjoint sets: two specialized questions

Sources: [Trie.java](../src/dsa/tries/Trie.java) and [DisjointSet.java](../src/dsa/graphs/DisjointSet.java).

These structures solve different problems. A trie answers questions about shared prefixes. A disjoint set answers whether elements belong to the same connected group. Neither was invented for Java; Java lets us express their nodes, arrays, and operations.

## Trie: why an exact-key map is not enough

A hash set can answer “does `cart` exist?” efficiently, but “which words start with `ca`?” normally requires examining many keys. A trie shares the path corresponding to a prefix:

```text
root
 └─ c
    └─ a
       ├─ r [word]
       │  └─ t [word]
       └─ t [word]
```

Each edge consumes one character. A terminal marker distinguishes the complete word `car` from the prefix `ca`. Without that marker, a path for `cart` would incorrectly make `car` a stored word even if it had never been inserted.

### Worked scenario: product-prefix search

Insert `car`, `cart`, and `cat`. To test `ca`, start at the root, follow child c, then child a. `startsWith("ca")` is true because the path exists; `contains("ca")` is false because that node is not terminal. To retrieve suggestions, first reach that node, then enumerate terminal descendants. **The supplied implementation performs insert, exact search, and prefix existence; ranked suggestion enumeration is an exercise.**

**Why it works:** after consuming i characters, the current node represents exactly that prefix. Induction over characters shows that reaching a terminal node after the last character means the complete word was inserted. A missing child proves no stored word uses that prefix.

Our trie supports lowercase English `a`–`z` only. It validates the entire input before insertion, so an invalid suffix cannot leave partially inserted nodes. Null is rejected. The empty prefix always exists; the empty word exists only after `insert("")`. Duplicate insertion changes nothing.

For length L, insertion and lookup take O(L) time. Lookup uses O(1) auxiliary space. Each newly created node stores 26 references; a trie containing P distinct prefix nodes uses O(26P) storage. This fixed alphabet makes indexing simple but wastes space on sparse branches. A map of children supports broader alphabets at different memory/lookup costs. Unicode normalization, case folding, and ranking require explicit product decisions. A trie alone does not understand spelling, synonyms, or popularity.

## Disjoint set: track connectivity without storing every route

Suppose computers 0–4 begin disconnected. Connections arrive: `(0,1)`, `(1,2)`, `(3,4)`. We want to ask “can 0 communicate with 2?” and “how many networks remain?” Running a fresh graph traversal for each question repeats work.

A disjoint-set union structure (DSU/union-find) maintains a forest of parent pointers. Each group has a representative root. Initially `parent[i] = i`, each size is 1, and the component count is 5.

1. `union(0,1)` attaches one root to the other; components become 4.
2. `union(1,2)` joins the group containing 1 with singleton 2; components become 3.
3. `union(3,4)` leaves 2 components, with sizes 3 and 2.
4. `union(0,2)` returns false because both already have the same representative.

**Why it works:** initially every singleton is correct. Union joins exactly two sets by connecting their roots; all old members still reach the appropriate representative. Two elements are connected precisely when their roots match. Path compression changes intermediate parents to the root without changing membership. Union by size attaches the smaller group under the larger, limiting tree growth.

Construction uses O(n) time and memory. Union/find operations have amortized O(α(n)) time with both optimizations, where α is the inverse Ackermann function; this is extremely slow-growing, but not literally a worst-case O(1) guarantee. Our iterative `find` avoids recursive stack growth.

## When to choose, and when to avoid

| Need | Choose | Limitation |
|---|---|---|
| Prefix existence | Trie | More memory than many exact-key structures |
| Exact key lookup only | Hash set/map | Prefix queries need additional indexing |
| Connectivity under edge additions | DSU | No route reconstruction or ordinary edge deletion |
| Actual path or directed reachability | Graph traversal | More work per fresh query |

DSU helps detect redundant undirected connections and supports Kruskal's minimum spanning tree algorithm. Account merging is another model when a shared identifier is accepted as evidence of belonging together; real identity decisions need domain rules.

## Practice

1. Draw the trie after inserting `a`, `an`, `ant`, and `all`.
2. Add word enumeration and deletion without destroying shared prefixes.
3. Use DSU to count connected components after each new cable.
4. Use DSU to detect the edge that first forms an undirected cycle.
5. Implement Kruskal using sorted weighted edges; explain why DSU alone does not select the cheapest edges.
