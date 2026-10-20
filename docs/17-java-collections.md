# 17 — Why Java provides different collections

Arrays, trees, queues, and maps were not invented by Java developers. They solve different general problems. Java's collections framework provides reusable interfaces and implementations so application code can express what it needs without reimplementing common operations. This chapter explains design purpose, not an undocumented historical account of an individual designer's intentions.

## Interface, implementation, type

```java
List<String> names = new ArrayList<>();
Map<String, Integer> stock = new HashMap<>();
Deque<String> work = new ArrayDeque<>();
```

`List`, `Map`, and `Deque` are contracts. `ArrayList`, `HashMap`, and `ArrayDeque` are implementations. `String` and `Integer` specify element/key/value types. Declaring an interface type keeps the code focused on the required operations, while the actual implementation determines ordering, costs, and permitted values.

Java generics use reference types: `List<int>` is invalid, whereas `List<Integer>` is valid. Boxing wraps primitive values; a primitive `int[]` can avoid that overhead. Avoid raw types such as `List` without an element type, which discard useful compile-time checks.

## Choose by operations

| Need | Typical Java choice | Main tradeoff |
| --- | --- | --- |
| Fixed number of primitive values | `int[]`, `long[]` | O(1) indexed access; length cannot change |
| Growable indexed sequence | `ArrayList<E>` | O(1) get/set; append amortized O(1); middle insertion/removal O(n) |
| Node-based list with end access | `LinkedList<E>` | Doubly linked; indexed access searches, O(n) worst case |
| Stack, FIFO queue, or both ends | `ArrayDeque<E>` | Efficient end operations; no null elements; not thread-safe |
| Exact key lookup | `HashMap<K,V>` | Expected constant-time basic operations with good hashing; unspecified order |
| Unique membership | `HashSet<E>` | Exact membership without associated values; unspecified order |
| Insertion/access order with keys | `LinkedHashMap<K,V>` | Extra ordering links; useful for recency modeling |
| Sorted keys, predecessor/successor | `TreeMap<K,V>` / `TreeSet<E>` | O(log n) core operations; ordering required |
| Repeated smallest/largest selection | `PriorityQueue<E>` | O(log n) insert/remove root; O(1) peek; iteration is not sorted |
| Packed bounded integer membership | `BitSet` | Space-efficient for dense IDs; not automatically best for sparse huge IDs |
| Concurrent producers and consumers | Suitable `BlockingQueue<E>` implementation | Capacity, waiting, fairness, and concurrency policies matter |

## Why not use LinkedList for every insertion?

Changing a known node's neighboring links is constant-time. But `list.add(index, value)` must first locate that position, which can take O(n). An array list shifts elements; a linked list follows references and allocates nodes. Choose according to complete operation cost and memory behavior. A queue often needs only its two ends, where an array deque is a practical default. Java's [LinkedList API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/LinkedList.html) documents its doubly linked implementation and indexed traversal.

## Java traps worth practising

**Equality:** use `.equals()` for value equality of strings and other objects. `==` compares object references. Custom map keys must keep `equals` and `hashCode` consistent. Do not mutate fields participating in a key's equality while it is stored in a hash map.

**Overloads:** `list.remove(1)` removes index 1 from a `List<Integer>`; `list.remove(Integer.valueOf(1))` removes the first matching value. Write a tiny example and observe the difference.

**Comparators:** prefer `Integer.compare(a, b)` or comparator helpers over `a - b`, which may overflow and violate ordering. Sorted sets consider comparator-equivalent values the same for membership, so their ordering should agree with the equality semantics you intend.

**Null and emptiness:** `poll()`/`peek()` can return null for an empty deque. `remove()`/`element()` throw on emptiness. Unboxing a null `Integer` causes `NullPointerException`. Read the contract before choosing the method.

**Views and copies:** `Arrays.asList(array)` produces a fixed-size list backed by the reference array; it is not a growable copy. With a primitive `int[]`, it produces a list containing that array, not a list of boxed individual integers. `new ArrayList<>(existingList)` copies the list container, but still references the same element objects.

**Concurrency:** regular collections do not become thread-safe just because individual calls look small. Multiple calls such as “check then insert” may require an atomic operation or synchronization. Fail-fast iterators detect some misuse; they are not concurrency protection.

## Why implement structures yourself here?

Building a list, BST, trie, or range tree once exposes the invariant that library methods hide. Application code should usually use a suitable standard implementation instead of maintaining a custom structure without need. Both skills matter: understand the mechanism and know when to use the library.

## Official references

The code in this repository targets Java 17-compatible APIs. The reference pages below describe Java 25 and may also list newer methods that these examples do not use.

- [ArrayList](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/ArrayList.html): indexed access, resizing, and amortized append.
- [ArrayDeque](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/ArrayDeque.html): deque operations and limitations.
- [HashMap](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/HashMap.html): hashing assumptions and ordering contract.
- [TreeMap](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/TreeMap.html): ordered red-black-tree map.
- [PriorityQueue](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/PriorityQueue.html): heap behavior and traversal caveats.
- [LinkedHashMap](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/LinkedHashMap.html): insertion order and access order.
