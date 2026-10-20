# 08 — Trees: represent hierarchy, then add order when search needs it

[Back to learning path](../README.md) · [Source](../src/dsa/trees/BinarySearchTree.java)

## Distinguish the structures first

A tree is a connected acyclic structure. In a rooted tree, each non-root node has one parent. Children model hierarchy: folders, document elements, or a company's reporting structure. A **binary tree** has at most two children per node. A **binary search tree (BST)** adds an ordering rule: every key in a node's left subtree is smaller, and every key in its right subtree is larger in our set implementation.

```text
       8
     /   \
    3     10
   / \      \
  1   6      14
```

Not every binary tree is a search tree. A file-system hierarchy is typically not binary and does not automatically obey BST order. A heap is also tree-shaped but follows a different invariant; it cannot discard one side based on a search key as a BST can.

Java's language does not have a built-in tree syntax. You can define a Node class to learn traversal. `TreeMap` and `TreeSet` offer balanced ordered collections, saving applications from implementing balancing and ordered-map semantics themselves. Java did not invent the tree concept.

## Why BST search works

Search for 6 in the diagram:

1. Compare with 8: 6 is smaller, so the right subtree cannot contain it.
2. Compare with 3: 6 is larger, so the left subtree cannot contain it.
3. Reach 6 and return true.

The invariant is that, if the target exists, it is in the current subtree. The ordering rule justifies discarding the other subtree at each comparison. Reaching null proves absence. Insert follows the same path to a missing child; our implementation ignores duplicates and reports false rather than silently storing multiple equal keys.

| Operation | Unbalanced BST here |
|---|---:|
| Search, insert, delete | O(h) time, O(1) working space |
| Inorder traversal | O(n) time, O(h) working space + O(n) output |
| Height by BFS | O(n) time, O(maximum width) space |
| Whole-tree storage | O(n) |

Here h is tree height. A balanced tree has O(log n) height; this teaching BST is **not balanced**. Inserting `1,2,3,4,5` produces a chain of height 4, so searches may take O(n). Height is measured in edges: empty tree -1, singleton 0. The source uses iterative operations to avoid recursive stack overflow on a long chain.

## Why traversal order matters

- **Inorder: left, node, right.** Produces ascending BST keys because all left keys precede the node and all right keys follow it.
- **Preorder: node, left, right.** Useful when a parent must be processed before descendants; shape serialization also requires null markers or equivalent structure information.
- **Postorder: left, right, node.** Useful when a parent result depends on child results, such as subtree size or expression evaluation.
- **Level order: queue/BFS.** Visits nodes by depth, such as showing an organizational chart one level at a time.

The source implements inorder and level-order height. Implement the remaining traversals as exercises rather than assuming they are already supplied.

## Worked operation: deleting a node with two children

Deleting a leaf simply removes its parent's link. Deleting a node with one child links that child to the parent. With two children, use the **inorder successor**: the smallest key in the right subtree.

For root 8 above, successor 10 can replace 8. The replacement is still greater than all left-subtree keys and no greater than any remaining right-subtree key. Then remove the original successor, which cannot have a left child. This reduces the hard case to the zero/one-child case. The implementation also handles deleting the root and deleting a missing key.

## Real-world scenario: scheduled appointments

An application needs “the earliest appointment at or after 14:30” and all appointments within a time range. A hash map helps exact lookup but does not preserve order. An ordered map can answer ceiling/floor and range queries. Java's `TreeMap<Instant, Appointment>` models unique timestamp keys; if multiple appointments share a time, use a list per timestamp or a composite key. A simple unbalanced tree is useful for learning, while a balanced implementation is the appropriate library starting point for predictable ordered operations.

On-disk database indexes frequently use page-oriented tree families such as B-trees/B+ trees; they are not simply this object-node BST written to disk. Read the advanced-structure material before transferring in-memory complexity assumptions to storage systems.

## Practice ladder

1. Draw preorder, inorder, postorder and level order for one tree.
2. Validate a BST with global bounds; comparing only immediate children is insufficient.
3. Find lowest common ancestor in both an ordinary binary tree and a BST.
4. Compute diameter and check balance using postorder information.
5. Serialize/deserialize a binary tree and test missing-child structure.

Reference: [Java TreeMap contract](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/TreeMap.html).
