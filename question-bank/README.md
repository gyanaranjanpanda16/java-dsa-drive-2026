# Solved Java question bank

**500 completed and tested questions out of the requested 500.** All IDs Q001–Q500 are included.

Every listed question has its own runnable Java file, explanatory comments, a lesson page, sample output, and at least three self-test checks. These are educational exercises covering common techniques, not verified records of questions asked by a particular employer. Programs use Java 17-compatible standard-library APIs.

## Available programs

| Number range | Completed | Coverage |
| --- | ---: | --- |
| Q001–Q125 | 125 | Java basics, arrays, matrices, and Java collection APIs |
| Q126–Q250 | 125 | Strings, linked lists, stacks, queues, hashing, searching, and sorting |
| Q251–Q375 | 125 | Trees, BSTs, heaps, graphs, tries, Fenwick trees, and segment trees |
| Q376–Q500 | 125 | Recursion, backtracking, DP, greedy algorithms, binary search, bits, mathematics, and design |
| **Total** | **500** | **Only completed programs appear below.** |

For the underlying concepts and real-world tradeoffs, read the [topic-based curriculum](../README.md), [Java collections guide](../docs/17-java-collections.md), and [real-world case studies](../docs/14-real-world-scenarios.md).

## Start with the first program

- [Q001 — Print every array element: explanation](../docs/questions/q001.md)
- [Q001 — Runnable, commented Java answer](../src/questions/q001/Q001PrintElements.java)

From the repository root:

```bash
bash scripts/run.sh questions.q001.Q001PrintElements
bash scripts/test.sh
```

The first command prints `10 20 30`. The second compiles everything, runs the curriculum tests, and verifies all 500 question self-tests and demonstration outputs. No external Java library is required.

Read the question before opening its answer. Write a small example, attempt a baseline, identify the invariant, then compare the commented implementation. Revisit questions independently instead of counting a copied solution as learned. The stated input contracts and complexity limits matter.

The JSON manifests store exact sample outputs; the TSV index drives the Java test runner. Maintainers can regenerate navigation with `python3 scripts/update-question-index.py` after verifying new manifest entries, then rerun all tests. Python is only needed for rebuilding navigation, not for running the Java programs.

## Browse completed answers

| ID | Question | Topic | Level | Java answer |
| --- | --- | --- | --- | --- |
| Q001 | [Print every array element in order](../docs/questions/q001.md) | Arrays — first steps | Beginner | [Code](../src/questions/q001/Q001PrintElements.java) |
| Q002 | [Print array indices beside values](../docs/questions/q002.md) | Arrays — first steps | Beginner | [Code](../src/questions/q002/Q002PrintIndices.java) |
| Q003 | [Print an array backwards without changing it](../docs/questions/q003.md) | Arrays — first steps | Beginner | [Code](../src/questions/q003/Q003ReversePrint.java) |
| Q004 | [Select elements at even indices](../docs/questions/q004.md) | Arrays — first steps | Beginner | [Code](../src/questions/q004/Q004EvenPositions.java) |
| Q005 | [Count elements with a loop](../docs/questions/q005.md) | Java loops | Beginner | [Code](../src/questions/q005/Q005CountElements.java) |
| Q006 | [Sum array values safely in long](../docs/questions/q006.md) | Java arithmetic | Beginner | [Code](../src/questions/q006/Q006SumElements.java) |
| Q007 | [Count even values](../docs/questions/q007.md) | Java conditionals | Beginner | [Code](../src/questions/q007/Q007CountEven.java) |
| Q008 | [Count positive negative and zero values](../docs/questions/q008.md) | Java conditionals | Beginner | [Code](../src/questions/q008/Q008SignCounts.java) |
| Q009 | [Find the maximum without sorting](../docs/questions/q009.md) | Array scans | Beginner | [Code](../src/questions/q009/Q009Maximum.java) |
| Q010 | [Find the minimum without sorting](../docs/questions/q010.md) | Array scans | Beginner | [Code](../src/questions/q010/Q010Minimum.java) |
| Q011 | [Compute an array average](../docs/questions/q011.md) | Java numeric types | Beginner | [Code](../src/questions/q011/Q011Average.java) |
| Q012 | [Count decimal digits of an integer](../docs/questions/q012.md) | Java arithmetic | Beginner | [Code](../src/questions/q012/Q012DigitCount.java) |
| Q013 | [Sum decimal digits](../docs/questions/q013.md) | Java arithmetic | Beginner | [Code](../src/questions/q013/Q013DigitSum.java) |
| Q014 | [Reverse decimal digits into a long](../docs/questions/q014.md) | Java arithmetic | Beginner | [Code](../src/questions/q014/Q014ReverseInteger.java) |
| Q015 | [Check whether an integer is a palindrome](../docs/questions/q015.md) | Java arithmetic | Beginner | [Code](../src/questions/q015/Q015IntegerPalindrome.java) |
| Q016 | [Calculate a bounded factorial](../docs/questions/q016.md) | Java loops | Beginner | [Code](../src/questions/q016/Q016Factorial.java) |
| Q017 | [Find a Fibonacci number iteratively](../docs/questions/q017.md) | Java state updates | Beginner | [Code](../src/questions/q017/Q017Fibonacci.java) |
| Q018 | [Check whether an integer is prime](../docs/questions/q018.md) | Number theory basics | Beginner | [Code](../src/questions/q018/Q018PrimeCheck.java) |
| Q019 | [Compute the greatest common divisor](../docs/questions/q019.md) | Number theory basics | Beginner | [Code](../src/questions/q019/Q019GreatestCommonDivisor.java) |
| Q020 | [Compute the least common multiple](../docs/questions/q020.md) | Number theory basics | Beginner | [Code](../src/questions/q020/Q020LeastCommonMultiple.java) |
| Q021 | [Compute a nonnegative integer power](../docs/questions/q021.md) | Java arithmetic | Beginner | [Code](../src/questions/q021/Q021Power.java) |
| Q022 | [Build a multiplication table](../docs/questions/q022.md) | Java loops | Beginner | [Code](../src/questions/q022/Q022MultiplicationTable.java) |
| Q023 | [Print a growing star triangle](../docs/questions/q023.md) | Nested loops | Beginner | [Code](../src/questions/q023/Q023Triangle.java) |
| Q024 | [Print a rectangular matrix row by row](../docs/questions/q024.md) | Matrices — first steps | Beginner | [Code](../src/questions/q024/Q024MatrixRows.java) |
| Q025 | [Copy an array without aliasing](../docs/questions/q025.md) | Java references | Beginner | [Code](../src/questions/q025/Q025ArrayCopy.java) |
| Q026 | [Find the first occurrence of a value](../docs/questions/q026.md) | Array search | Beginner | [Code](../src/questions/q026/Q026FirstOccurrence.java) |
| Q027 | [Find the last occurrence of a value](../docs/questions/q027.md) | Array search | Beginner | [Code](../src/questions/q027/Q027LastOccurrence.java) |
| Q028 | [Count occurrences of one target](../docs/questions/q028.md) | Array counting | Beginner | [Code](../src/questions/q028/Q028TargetFrequency.java) |
| Q029 | [Find the second distinct maximum](../docs/questions/q029.md) | Array extrema | Beginner | [Code](../src/questions/q029/Q029SecondDistinctMaximum.java) |
| Q030 | [Count distinct values](../docs/questions/q030.md) | Arrays and sets | Beginner | [Code](../src/questions/q030/Q030DistinctCount.java) |
| Q031 | [Reverse an array by swapping endpoints](../docs/questions/q031.md) | Two pointers | Beginner | [Code](../src/questions/q031/Q031ReverseArray.java) |
| Q032 | [Check nondecreasing array order](../docs/questions/q032.md) | Array invariants | Beginner | [Code](../src/questions/q032/Q032SortedCheck.java) |
| Q033 | [Rotate an array left by one](../docs/questions/q033.md) | Array transformations | Beginner | [Code](../src/questions/q033/Q033RotateOne.java) |
| Q034 | [Rotate right by any signed distance](../docs/questions/q034.md) | Modular indexing | Beginner | [Code](../src/questions/q034/Q034RotateByK.java) |
| Q035 | [Move zeros to the end stably](../docs/questions/q035.md) | Two pointers | Beginner | [Code](../src/questions/q035/Q035MoveZeros.java) |
| Q036 | [Remove all occurrences while preserving order](../docs/questions/q036.md) | Array compaction | Beginner | [Code](../src/questions/q036/Q036RemoveValue.java) |
| Q037 | [Remove duplicates from sorted input](../docs/questions/q037.md) | Sorted arrays | Beginner | [Code](../src/questions/q037/Q037SortedDeduplication.java) |
| Q038 | [Merge two sorted arrays](../docs/questions/q038.md) | Two-pointer merging | Beginner | [Code](../src/questions/q038/Q038MergeSortedArrays.java) |
| Q039 | [Find the sorted distinct intersection](../docs/questions/q039.md) | Sets and arrays | Beginner | [Code](../src/questions/q039/Q039DistinctIntersection.java) |
| Q040 | [Find the sorted distinct union](../docs/questions/q040.md) | Sets and arrays | Beginner | [Code](../src/questions/q040/Q040DistinctUnion.java) |
| Q041 | [Find the missing value in zero through n](../docs/questions/q041.md) | XOR invariants | Beginner | [Code](../src/questions/q041/Q041MissingNumber.java) |
| Q042 | [Find the first value repeated during a scan](../docs/questions/q042.md) | Hashing | Beginner | [Code](../src/questions/q042/Q042FirstRepeated.java) |
| Q043 | [Find distinct indices adding to a target](../docs/questions/q043.md) | Hash maps | Beginner | [Code](../src/questions/q043/Q043TwoSumIndices.java) |
| Q044 | [Count index pairs with a target sum](../docs/questions/q044.md) | Hash frequencies | Beginner | [Code](../src/questions/q044/Q044PairCount.java) |
| Q045 | [Build a prefix-sum array](../docs/questions/q045.md) | Prefix sums | Beginner | [Code](../src/questions/q045/Q045PrefixTotals.java) |
| Q046 | [Answer multiple half-open range-sum queries](../docs/questions/q046.md) | Prefix sums | Beginner | [Code](../src/questions/q046/Q046BatchRangeSums.java) |
| Q047 | [Find an equilibrium index](../docs/questions/q047.md) | Prefix balance | Beginner | [Code](../src/questions/q047/Q047EquilibriumIndex.java) |
| Q048 | [Find values greater than all values to their right](../docs/questions/q048.md) | Reverse scans | Beginner | [Code](../src/questions/q048/Q048Leaders.java) |
| Q049 | [Find a maximum nonempty contiguous sum](../docs/questions/q049.md) | Kadane algorithm | Beginner | [Code](../src/questions/q049/Q049MaximumSubarray.java) |
| Q050 | [Find a minimum nonempty contiguous sum](../docs/questions/q050.md) | Kadane variant | Beginner | [Code](../src/questions/q050/Q050MinimumSubarray.java) |
| Q051 | [Find a maximum-product contiguous subarray](../docs/questions/q051.md) | Array dynamic programming | Medium | [Code](../src/questions/q051/Q051MaximumProduct.java) |
| Q052 | [Compute products excluding each position](../docs/questions/q052.md) | Prefix and suffix products | Medium | [Code](../src/questions/q052/Q052ProductExceptSelf.java) |
| Q053 | [Maximize profit from one buy and sell](../docs/questions/q053.md) | Greedy array scan | Easy | [Code](../src/questions/q053/Q053SingleTradeProfit.java) |
| Q054 | [Maximize profit with unlimited transactions](../docs/questions/q054.md) | Greedy differences | Medium | [Code](../src/questions/q054/Q054UnlimitedTradeProfit.java) |
| Q055 | [Calculate water trapped between bars](../docs/questions/q055.md) | Two pointers | Hard | [Code](../src/questions/q055/Q055TrappedRainwater.java) |
| Q056 | [Find the maximum water-container area](../docs/questions/q056.md) | Two pointers | Medium | [Code](../src/questions/q056/Q056ContainerArea.java) |
| Q057 | [Find the largest sum of exactly k consecutive values](../docs/questions/q057.md) | Sliding windows | Easy | [Code](../src/questions/q057/Q057FixedWindowSum.java) |
| Q058 | [Find a shortest positive-sum threshold window](../docs/questions/q058.md) | Sliding windows | Medium | [Code](../src/questions/q058/Q058MinimumPositiveWindow.java) |
| Q059 | [Sort an array containing only zero one and two](../docs/questions/q059.md) | Dutch national flag | Medium | [Code](../src/questions/q059/Q059SortThreeValues.java) |
| Q060 | [Place even values before odd values stably](../docs/questions/q060.md) | Array partitioning | Beginner | [Code](../src/questions/q060/Q060StableParityPartition.java) |
| Q061 | [Find a value occurring more than half the time](../docs/questions/q061.md) | Boyer Moore voting | Easy | [Code](../src/questions/q061/Q061MajorityElement.java) |
| Q062 | [Count all values in first-appearance order](../docs/questions/q062.md) | Ordered hash maps | Beginner | [Code](../src/questions/q062/Q062FrequencyTable.java) |
| Q063 | [Find the longest run of ones](../docs/questions/q063.md) | Array runs | Beginner | [Code](../src/questions/q063/Q063ConsecutiveOnes.java) |
| Q064 | [Find the longest binary window after at most k zero flips](../docs/questions/q064.md) | Sliding windows | Medium | [Code](../src/questions/q064/Q064OnesAfterFlips.java) |
| Q065 | [Count contiguous subarrays with an integer target sum](../docs/questions/q065.md) | Prefix frequency maps | Medium | [Code](../src/questions/q065/Q065CountTargetSubarrays.java) |
| Q066 | [Find a longest interval with a target sum](../docs/questions/q066.md) | Prefix sums | Medium | [Code](../src/questions/q066/Q066LongestTargetSubarray.java) |
| Q067 | [Rotate a square matrix clockwise](../docs/questions/q067.md) | Matrix transformations | Medium | [Code](../src/questions/q067/Q067RotateSquare.java) |
| Q068 | [Transpose a rectangular matrix](../docs/questions/q068.md) | Matrix indexing | Beginner | [Code](../src/questions/q068/Q068Transpose.java) |
| Q069 | [Sum both diagonals without double-counting the center](../docs/questions/q069.md) | Matrix scans | Beginner | [Code](../src/questions/q069/Q069DiagonalTotal.java) |
| Q070 | [Read a rectangular matrix in clockwise spiral order](../docs/questions/q070.md) | Matrix boundaries | Medium | [Code](../src/questions/q070/Q070SpiralTraversal.java) |
| Q071 | [Search a matrix sorted by rows and columns](../docs/questions/q071.md) | Staircase search | Medium | [Code](../src/questions/q071/Q071SortedMatrixSearch.java) |
| Q072 | [Zero rows and columns containing an original zero](../docs/questions/q072.md) | Matrix marking | Medium | [Code](../src/questions/q072/Q072ZeroMatrixRowsColumns.java) |
| Q073 | [Merge overlapping closed intervals](../docs/questions/q073.md) | Sorting and intervals | Medium | [Code](../src/questions/q073/Q073MergeIntervals.java) |
| Q074 | [Insert one interval into a disjoint sorted interval list](../docs/questions/q074.md) | Interval scans | Medium | [Code](../src/questions/q074/Q074InsertInterval.java) |
| Q075 | [Find the next lexicographic permutation](../docs/questions/q075.md) | Lexicographic ordering | Medium | [Code](../src/questions/q075/Q075NextPermutation.java) |
| Q076 | [Insert replace and remove ArrayList elements by position](../docs/questions/q076.md) | Java Collections: ArrayList | Beginner | [Code](../src/questions/q076/Q076ArrayListEdits.java) |
| Q077 | [Remove even values safely while iterating a list](../docs/questions/q077.md) | Java Collections: Iterator | Beginner | [Code](../src/questions/q077/Q077IteratorRemoval.java) |
| Q078 | [Insert follow-up tasks during list traversal](../docs/questions/q078.md) | Java Collections: ListIterator | Beginner | [Code](../src/questions/q078/Q078ListIteratorInsertion.java) |
| Q079 | [Delete a half-open range through a backed list view](../docs/questions/q079.md) | Java Collections: subList | Beginner | [Code](../src/questions/q079/Q079SubListDeletion.java) |
| Q080 | [Filter a list using set membership while preserving duplicates](../docs/questions/q080.md) | Java Collections: retainAll | Beginner | [Code](../src/questions/q080/Q080RetainAllowed.java) |
| Q081 | [Rotate a list with the Collections utility](../docs/questions/q081.md) | Java Collections: Collections.rotate | Beginner | [Code](../src/questions/q081/Q081RotateList.java) |
| Q082 | [Sort integers by magnitude with numeric tie-breaking](../docs/questions/q082.md) | Java Collections: Comparator | Beginner | [Code](../src/questions/q082/Q082AbsoluteComparator.java) |
| Q083 | [Sort words by length while preserving equal-length order](../docs/questions/q083.md) | Java Collections: stable sorting | Beginner | [Code](../src/questions/q083/Q083StableLengthSort.java) |
| Q084 | [Insert into a sorted ArrayList using binarySearch](../docs/questions/q084.md) | Java Collections: binarySearch | Beginner | [Code](../src/questions/q084/Q084SortedListInsertion.java) |
| Q085 | [Create a detached immutable List.copyOf snapshot](../docs/questions/q085.md) | Java Collections: List.copyOf | Beginner | [Code](../src/questions/q085/Q085ImmutableSnapshot.java) |
| Q086 | [Observe a fixed-size list backed by an array](../docs/questions/q086.md) | Java Collections: Arrays.asList | Beginner | [Code](../src/questions/q086/Q086FixedSizeList.java) |
| Q087 | [Remove the first and last matching occurrences](../docs/questions/q087.md) | Java Collections: LinkedList and Deque | Beginner | [Code](../src/questions/q087/Q087LinkedListOccurrences.java) |
| Q088 | [Retain only the most recent events](../docs/questions/q088.md) | Java Collections: ArrayDeque | Beginner | [Code](../src/questions/q088/Q088RecentHistory.java) |
| Q089 | [Simulate fair round-robin task scheduling](../docs/questions/q089.md) | Java Collections: queue scheduling | Easy | [Code](../src/questions/q089/Q089RoundRobin.java) |
| Q090 | [Implement text undo and redo with two stacks](../docs/questions/q090.md) | Java Collections: two deques | Easy | [Code](../src/questions/q090/Q090UndoRedo.java) |
| Q091 | [Trace legacy Stack push peek and pop](../docs/questions/q091.md) | Java Collections: Stack | Beginner | [Code](../src/questions/q091/Q091LegacyStack.java) |
| Q092 | [Find values present in exactly one set](../docs/questions/q092.md) | Java Collections: HashSet | Beginner | [Code](../src/questions/q092/Q092SymmetricDifference.java) |
| Q093 | [Deduplicate values while preserving first-seen order](../docs/questions/q093.md) | Java Collections: LinkedHashSet | Beginner | [Code](../src/questions/q093/Q093FirstSeenOrder.java) |
| Q094 | [Select a sorted half-open set range](../docs/questions/q094.md) | Java Collections: TreeSet | Beginner | [Code](../src/questions/q094/Q094BoundedSetRange.java) |
| Q095 | [Allocate the nearest free seat with ordered neighbors](../docs/questions/q095.md) | Java Collections: NavigableSet | Easy | [Code](../src/questions/q095/Q095NearestSeat.java) |
| Q096 | [Combine and revoke finite permission flags](../docs/questions/q096.md) | Java Collections: EnumSet | Beginner | [Code](../src/questions/q096/Q096EnumPermissions.java) |
| Q097 | [Find free slots in a bounded universe](../docs/questions/q097.md) | Java Collections: BitSet | Beginner | [Code](../src/questions/q097/Q097BitSetAvailability.java) |
| Q098 | [Reserve available inventory atomically within one operation](../docs/questions/q098.md) | Java Collections: HashMap | Beginner | [Code](../src/questions/q098/Q098InventoryReservation.java) |
| Q099 | [Group names with computeIfAbsent](../docs/questions/q099.md) | Java Collections: Map.computeIfAbsent | Beginner | [Code](../src/questions/q099/Q099GroupByInitial.java) |
| Q100 | [Aggregate transaction totals with Map.merge](../docs/questions/q100.md) | Java Collections: Map.merge | Beginner | [Code](../src/questions/q100/Q100MergeTotals.java) |
| Q101 | [Build a reverse lookup index for shared values](../docs/questions/q101.md) | Java Collections: inverse map | Beginner | [Code](../src/questions/q101/Q101InvertIndex.java) |
| Q102 | [Maintain an access-order cache with LinkedHashMap](../docs/questions/q102.md) | Java Collections: LinkedHashMap | Easy | [Code](../src/questions/q102/Q102AccessOrderCache.java) |
| Q103 | [Look up the latest effective price at a timestamp](../docs/questions/q103.md) | Java Collections: TreeMap.floorEntry | Beginner | [Code](../src/questions/q103/Q103EffectivePrice.java) |
| Q104 | [Total map values within an inclusive key range](../docs/questions/q104.md) | Java Collections: NavigableMap.subMap | Beginner | [Code](../src/questions/q104/Q104RangeTotalMap.java) |
| Q105 | [Count fixed categories with EnumMap](../docs/questions/q105.md) | Java Collections: EnumMap | Beginner | [Code](../src/questions/q105/Q105EnumCounters.java) |
| Q106 | [Compare value equality with object identity as map keys](../docs/questions/q106.md) | Java Collections: IdentityHashMap | Beginner | [Code](../src/questions/q106/Q106IdentityKeys.java) |
| Q107 | [Use weak keys while retaining live owners](../docs/questions/q107.md) | Java Collections: WeakHashMap | Beginner | [Code](../src/questions/q107/Q107WeakKeyCache.java) |
| Q108 | [Count two workers updates without lost increments](../docs/questions/q108.md) | Java Collections: ConcurrentHashMap | Beginner | [Code](../src/questions/q108/Q108ConcurrentCounter.java) |
| Q109 | [Keep the first registered value with putIfAbsent](../docs/questions/q109.md) | Java Collections: ConcurrentMap | Beginner | [Code](../src/questions/q109/Q109FirstRegistration.java) |
| Q110 | [Read an ordered concurrent-map range](../docs/questions/q110.md) | Java Collections: ConcurrentSkipListMap | Beginner | [Code](../src/questions/q110/Q110ConcurrentOrderedRange.java) |
| Q111 | [Observe a CopyOnWriteArrayList iterator snapshot](../docs/questions/q111.md) | Java Collections: CopyOnWriteArrayList | Beginner | [Code](../src/questions/q111/Q111CopyOnWriteSnapshot.java) |
| Q112 | [Deduplicate a rarely changed observer registry](../docs/questions/q112.md) | Java Collections: CopyOnWriteArraySet | Beginner | [Code](../src/questions/q112/Q112CopyOnWriteSet.java) |
| Q113 | [Drain a concurrent FIFO with poll](../docs/questions/q113.md) | Java Collections: ConcurrentLinkedQueue | Beginner | [Code](../src/questions/q113/Q113ConcurrentQueueDrain.java) |
| Q114 | [Handle backpressure with nonblocking queue offers](../docs/questions/q114.md) | Java Collections: ArrayBlockingQueue | Beginner | [Code](../src/questions/q114/Q114BoundedQueueOffers.java) |
| Q115 | [Take a bounded batch from a linked blocking queue](../docs/questions/q115.md) | Java Collections: LinkedBlockingQueue | Beginner | [Code](../src/questions/q115/Q115QueueBatchDrain.java) |
| Q116 | [Consume a priority queue in sorted removal order](../docs/questions/q116.md) | Java Collections: PriorityBlockingQueue | Beginner | [Code](../src/questions/q116/Q116BlockingPriorityOrder.java) |
| Q117 | [Poll due tasks from a DelayQueue using a logical clock](../docs/questions/q117.md) | Java Collections: DelayQueue | Beginner | [Code](../src/questions/q117/Q117DueTasks.java) |
| Q118 | [Observe that a SynchronousQueue has no storage buffer](../docs/questions/q118.md) | Java Collections: SynchronousQueue | Beginner | [Code](../src/questions/q118/Q118RendezvousQueue.java) |
| Q119 | [Distinguish direct transfer from ordinary queueing](../docs/questions/q119.md) | Java Collections: LinkedTransferQueue | Beginner | [Code](../src/questions/q119/Q119TransferVsEnqueue.java) |
| Q120 | [Traverse a legacy Vector with Enumeration](../docs/questions/q120.md) | Java Collections: Vector and Enumeration | Beginner | [Code](../src/questions/q120/Q120VectorEnumeration.java) |
| Q121 | [Contrast a read-only live view with a detached snapshot](../docs/questions/q121.md) | Java Collections: unmodifiableList | Beginner | [Code](../src/questions/q121/Q121UnmodifiableLiveView.java) |
| Q122 | [See why nCopies does not clone mutable elements](../docs/questions/q122.md) | Java Collections: Collections.nCopies | Beginner | [Code](../src/questions/q122/Q122RepeatedReference.java) |
| Q123 | [Sort nullable names with missing values last](../docs/questions/q123.md) | Java Collections: Comparator.nullsLast | Beginner | [Code](../src/questions/q123/Q123NullSafeComparator.java) |
| Q124 | [Preserve arrival order among equal-priority tasks](../docs/questions/q124.md) | Java Collections: PriorityQueue comparator | Beginner | [Code](../src/questions/q124/Q124StablePriorityQueue.java) |
| Q125 | [Reject a raw-type insertion at the collection boundary](../docs/questions/q125.md) | Java Collections: checkedList | Beginner | [Code](../src/questions/q125/Q125CheckedList.java) |
| Q126 | [Print every character with its index](../docs/questions/q126.md) | Strings | Beginner | [Code](../src/questions/q126/Q126Characters.java) |
| Q127 | [Reverse text using two pointers](../docs/questions/q127.md) | Strings | Beginner | [Code](../src/questions/q127/Q127Reverse.java) |
| Q128 | [Check an exact palindrome](../docs/questions/q128.md) | Strings | Beginner | [Code](../src/questions/q128/Q128Palindrome.java) |
| Q129 | [Count ASCII vowels and consonants](../docs/questions/q129.md) | Strings | Beginner | [Code](../src/questions/q129/Q129Letters.java) |
| Q130 | [Count whitespace-delimited words](../docs/questions/q130.md) | Strings | Beginner | [Code](../src/questions/q130/Q130Words.java) |
| Q131 | [Toggle ASCII letter case](../docs/questions/q131.md) | Strings | Beginner | [Code](../src/questions/q131/Q131ToggleCase.java) |
| Q132 | [Remove whitespace characters](../docs/questions/q132.md) | Strings | Beginner | [Code](../src/questions/q132/Q132RemoveWhitespace.java) |
| Q133 | [Count characters in sorted order](../docs/questions/q133.md) | Strings | Easy | [Code](../src/questions/q133/Q133CharFrequency.java) |
| Q134 | [Find the first non-repeating character index](../docs/questions/q134.md) | Strings | Easy | [Code](../src/questions/q134/Q134FirstUnique.java) |
| Q135 | [Find the first repeated encounter](../docs/questions/q135.md) | Strings | Easy | [Code](../src/questions/q135/Q135FirstRepeat.java) |
| Q136 | [Check exact anagrams](../docs/questions/q136.md) | Strings | Easy | [Code](../src/questions/q136/Q136Anagram.java) |
| Q137 | [Remove repeated characters stably](../docs/questions/q137.md) | Strings | Easy | [Code](../src/questions/q137/Q137Deduplicate.java) |
| Q138 | [Find the longest common prefix](../docs/questions/q138.md) | Strings | Easy | [Code](../src/questions/q138/Q138CommonPrefix.java) |
| Q139 | [Reverse word order and normalize spaces](../docs/questions/q139.md) | Strings | Easy | [Code](../src/questions/q139/Q139ReverseWords.java) |
| Q140 | [Recognize a rotation of a string](../docs/questions/q140.md) | Strings | Easy | [Code](../src/questions/q140/Q140Rotation.java) |
| Q141 | [Count characters in sorted order](../docs/questions/q141.md) | Strings | Easy | [Code](../src/questions/q141/Q141CharacterFrequencies.java) |
| Q142 | [Find the longest substring without repeated chars](../docs/questions/q142.md) | Strings | Medium | [Code](../src/questions/q142/Q142LongestUnique.java) |
| Q143 | [Find the longest common prefix of strings](../docs/questions/q143.md) | Strings | Easy | [Code](../src/questions/q143/Q143CommonPrefix.java) |
| Q144 | [Find the first exact substring match](../docs/questions/q144.md) | Strings | Easy | [Code](../src/questions/q144/Q144NaiveSubstring.java) |
| Q145 | [Find a substring using KMP failure links](../docs/questions/q145.md) | Strings | Medium | [Code](../src/questions/q145/Q145KmpSubstring.java) |
| Q146 | [Encode runs as character-count pairs](../docs/questions/q146.md) | Strings | Easy | [Code](../src/questions/q146/Q146RunLength.java) |
| Q147 | [Encode ASCII spaces as percent-20](../docs/questions/q147.md) | Strings | Easy | [Code](../src/questions/q147/Q147SpaceEncoding.java) |
| Q148 | [Parse and clamp a signed decimal prefix](../docs/questions/q148.md) | Strings | Medium | [Code](../src/questions/q148/Q148ParseInteger.java) |
| Q149 | [Evaluate a valid Roman numeral](../docs/questions/q149.md) | Strings | Easy | [Code](../src/questions/q149/Q149RomanValue.java) |
| Q150 | [Find a longest palindromic substring](../docs/questions/q150.md) | Strings | Medium | [Code](../src/questions/q150/Q150LongestPalindrome.java) |
| Q151 | [Group words with equal character multiplicities](../docs/questions/q151.md) | Strings | Medium | [Code](../src/questions/q151/Q151GroupAnagrams.java) |
| Q152 | [Find a longest substring with at most k distinct chars](../docs/questions/q152.md) | Strings | Medium | [Code](../src/questions/q152/Q152AtMostKDistinct.java) |
| Q153 | [Find a shortest substring covering required characters](../docs/questions/q153.md) | Strings | Hard | [Code](../src/questions/q153/Q153MinimumCover.java) |
| Q154 | [Compare arbitrarily large version components](../docs/questions/q154.md) | Strings | Medium | [Code](../src/questions/q154/Q154CompareVersions.java) |
| Q155 | [Add binary strings without numeric parsing](../docs/questions/q155.md) | Strings | Medium | [Code](../src/questions/q155/Q155AddBinary.java) |
| Q156 | [Multiply decimal strings by positional arithmetic](../docs/questions/q156.md) | Strings | Medium | [Code](../src/questions/q156/Q156MultiplyDecimal.java) |
| Q157 | [Read a zigzag layout row by row](../docs/questions/q157.md) | Strings | Medium | [Code](../src/questions/q157/Q157Zigzag.java) |
| Q158 | [Check a one-to-one character substitution](../docs/questions/q158.md) | Strings | Easy | [Code](../src/questions/q158/Q158Isomorphic.java) |
| Q159 | [Match pattern symbols bijectively to words](../docs/questions/q159.md) | Strings | Easy | [Code](../src/questions/q159/Q159WordPattern.java) |
| Q160 | [Find starts containing a permutation of equal-length words](../docs/questions/q160.md) | Strings | Hard | [Code](../src/questions/q160/Q160ConcatenatedWords.java) |
| Q161 | [Traverse a singly linked list](../docs/questions/q161.md) | Linked lists | Easy | [Code](../src/questions/q161/Q161Traverse.java) |
| Q162 | [Count nodes of an acyclic list](../docs/questions/q162.md) | Linked lists | Easy | [Code](../src/questions/q162/Q162Length.java) |
| Q163 | [Find the first index of a list value](../docs/questions/q163.md) | Linked lists | Easy | [Code](../src/questions/q163/Q163Search.java) |
| Q164 | [Insert a new head node](../docs/questions/q164.md) | Linked lists | Easy | [Code](../src/questions/q164/Q164Prepend.java) |
| Q165 | [Append a new tail node](../docs/questions/q165.md) | Linked lists | Easy | [Code](../src/questions/q165/Q165Append.java) |
| Q166 | [Insert before a zero-based list position](../docs/questions/q166.md) | Linked lists | Easy | [Code](../src/questions/q166/Q166InsertAt.java) |
| Q167 | [Remove the first node](../docs/questions/q167.md) | Linked lists | Easy | [Code](../src/questions/q167/Q167DeleteHead.java) |
| Q168 | [Remove the final node](../docs/questions/q168.md) | Linked lists | Easy | [Code](../src/questions/q168/Q168DeleteTail.java) |
| Q169 | [Remove a node at a specified position](../docs/questions/q169.md) | Linked lists | Easy | [Code](../src/questions/q169/Q169DeleteAt.java) |
| Q170 | [Remove every node containing a selected value](../docs/questions/q170.md) | Linked lists | Easy | [Code](../src/questions/q170/Q170DeleteValue.java) |
| Q171 | [Reverse a singly linked list in place](../docs/questions/q171.md) | Linked lists | Easy | [Code](../src/questions/q171/Q171Reverse.java) |
| Q172 | [Find the second middle node of a list](../docs/questions/q172.md) | Linked lists | Easy | [Code](../src/questions/q172/Q172Middle.java) |
| Q173 | [Find the kth node from the end](../docs/questions/q173.md) | Linked lists | Easy | [Code](../src/questions/q173/Q173KthFromEnd.java) |
| Q174 | [Detect a cycle using two moving pointers](../docs/questions/q174.md) | Linked lists | Easy | [Code](../src/questions/q174/Q174DetectCycle.java) |
| Q175 | [Find the entry value of a reference cycle](../docs/questions/q175.md) | Linked lists | Easy | [Code](../src/questions/q175/Q175CycleEntry.java) |
| Q176 | [Merge two sorted linked lists by relinking](../docs/questions/q176.md) | Linked lists | Easy | [Code](../src/questions/q176/Q176MergeSorted.java) |
| Q177 | [Remove repeated values from a sorted list](../docs/questions/q177.md) | Linked lists | Easy | [Code](../src/questions/q177/Q177DeduplicateSorted.java) |
| Q178 | [Remove later duplicate values from an unsorted list](../docs/questions/q178.md) | Linked lists | Easy | [Code](../src/questions/q178/Q178DeduplicateUnsorted.java) |
| Q179 | [Insert a value into a sorted list](../docs/questions/q179.md) | Linked lists | Easy | [Code](../src/questions/q179/Q179SortedInsert.java) |
| Q180 | [Check whether list values form a palindrome](../docs/questions/q180.md) | Linked lists | Easy | [Code](../src/questions/q180/Q180Palindrome.java) |
| Q181 | [Find the first shared node of two acyclic lists](../docs/questions/q181.md) | Linked lists | Easy | [Code](../src/questions/q181/Q181Intersection.java) |
| Q182 | [Rotate a list right by k positions](../docs/questions/q182.md) | Linked lists | Easy | [Code](../src/questions/q182/Q182Rotate.java) |
| Q183 | [Swap neighboring nodes by rewiring links](../docs/questions/q183.md) | Linked lists | Easy | [Code](../src/questions/q183/Q183SwapPairs.java) |
| Q184 | [Reverse a doubly linked list](../docs/questions/q184.md) | Linked lists | Medium | [Code](../src/questions/q184/Q184DoublyReverse.java) |
| Q185 | [Count nodes in a circular list](../docs/questions/q185.md) | Linked lists | Easy | [Code](../src/questions/q185/Q185CircularLength.java) |
| Q186 | [Reverse values through a stack](../docs/questions/q186.md) | Stacks, queues, and deques | Beginner | [Code](../src/questions/q186/Q186StackReverse.java) |
| Q187 | [Validate nesting of three bracket types](../docs/questions/q187.md) | Stacks, queues, and deques | Easy | [Code](../src/questions/q187/Q187BalancedBrackets.java) |
| Q188 | [Evaluate a valid postfix arithmetic expression](../docs/questions/q188.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q188/Q188Postfix.java) |
| Q189 | [Find the next strictly greater value to the right](../docs/questions/q189.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q189/Q189NextGreater.java) |
| Q190 | [Find the next strictly smaller index to the right](../docs/questions/q190.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q190/Q190NextSmaller.java) |
| Q191 | [Find the nearest greater index on the left](../docs/questions/q191.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q191/Q191PreviousGreater.java) |
| Q192 | [Compute consecutive stock-price spans](../docs/questions/q192.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q192/Q192StockSpan.java) |
| Q193 | [Count days until a strictly warmer temperature](../docs/questions/q193.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q193/Q193WarmerDays.java) |
| Q194 | [Find the largest rectangle in a histogram](../docs/questions/q194.md) | Stacks, queues, and deques | Hard | [Code](../src/questions/q194/Q194Histogram.java) |
| Q195 | [Find every fixed-window maximum with a deque](../docs/questions/q195.md) | Stacks, queues, and deques | Hard | [Code](../src/questions/q195/Q195WindowMaximum.java) |
| Q196 | [Maintain stack minima through pushes and pops](../docs/questions/q196.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q196/Q196MinimumStack.java) |
| Q197 | [Implement FIFO removal with two stacks](../docs/questions/q197.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q197/Q197QueueFromStacks.java) |
| Q198 | [Implement LIFO behavior with queue rotation](../docs/questions/q198.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q198/Q198StackFromQueue.java) |
| Q199 | [Simulate a bounded circular-array queue](../docs/questions/q199.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q199/Q199CircularQueue.java) |
| Q200 | [Read a deque by alternating front and back](../docs/questions/q200.md) | Stacks, queues, and deques | Easy | [Code](../src/questions/q200/Q200AlternatingEnds.java) |
| Q201 | [Generate the first n positive binary representations](../docs/questions/q201.md) | Stacks, queues, and deques | Easy | [Code](../src/questions/q201/Q201BinaryNumbers.java) |
| Q202 | [Track the earliest nonrepeating character after each arrival](../docs/questions/q202.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q202/Q202FirstUniqueStream.java) |
| Q203 | [Reverse the first k queue values only](../docs/questions/q203.md) | Stacks, queues, and deques | Easy | [Code](../src/questions/q203/Q203ReverseQueuePrefix.java) |
| Q204 | [Interleave the two halves of an even queue](../docs/questions/q204.md) | Stacks, queues, and deques | Easy | [Code](../src/questions/q204/Q204InterleaveHalves.java) |
| Q205 | [Find a feasible start for a circular fuel tour](../docs/questions/q205.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q205/Q205CircularTour.java) |
| Q206 | [Resolve one-dimensional asteroid collisions](../docs/questions/q206.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q206/Q206Asteroids.java) |
| Q207 | [Repeatedly remove adjacent equal character pairs](../docs/questions/q207.md) | Stacks, queues, and deques | Easy | [Code](../src/questions/q207/Q207AdjacentCancellation.java) |
| Q208 | [Decode nested repetition brackets](../docs/questions/q208.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q208/Q208DecodeRepeats.java) |
| Q209 | [Simplify an absolute Unix-style path](../docs/questions/q209.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q209/Q209CanonicalPath.java) |
| Q210 | [Score balanced parentheses by nesting rules](../docs/questions/q210.md) | Stacks, queues, and deques | Medium | [Code](../src/questions/q210/Q210ParenthesisScore.java) |
| Q211 | [Count words in sorted dictionary order](../docs/questions/q211.md) | Hash maps and sets | Easy | [Code](../src/questions/q211/Q211WordFrequencies.java) |
| Q212 | [Find the first integer appearing exactly once](../docs/questions/q212.md) | Hash maps and sets | Easy | [Code](../src/questions/q212/Q212FirstUniqueInteger.java) |
| Q213 | [Compare arrays while preserving duplicate multiplicities](../docs/questions/q213.md) | Hash maps and sets | Easy | [Code](../src/questions/q213/Q213EqualMultisets.java) |
| Q214 | [Return the distinct intersection in first-array order](../docs/questions/q214.md) | Hash maps and sets | Easy | [Code](../src/questions/q214/Q214DistinctIntersection.java) |
| Q215 | [Intersect arrays including matched duplicate occurrences](../docs/questions/q215.md) | Hash maps and sets | Easy | [Code](../src/questions/q215/Q215MultisetIntersection.java) |
| Q216 | [Detect equal values within an index distance](../docs/questions/q216.md) | Hash maps | Easy | [Code](../src/questions/q216/Q216NearbyDuplicate.java) |
| Q217 | [Detect nearby values with a bounded numeric difference](../docs/questions/q217.md) | Ordered sliding window | Medium | [Code](../src/questions/q217/Q217NearbyAlmostDuplicate.java) |
| Q218 | [Count subarrays whose sum is divisible by k](../docs/questions/q218.md) | Prefix residues | Medium | [Code](../src/questions/q218/Q218DivisibleSubarrays.java) |
| Q219 | [Find the longest interval with equal zeros and ones](../docs/questions/q219.md) | Prefix balance | Medium | [Code](../src/questions/q219/Q219BalancedBinaryInterval.java) |
| Q220 | [Find a longest consecutive integer set run](../docs/questions/q220.md) | Hash sets | Medium | [Code](../src/questions/q220/Q220LongestConsecutive.java) |
| Q221 | [Count distinct values in every fixed-size window](../docs/questions/q221.md) | Sliding hash frequencies | Medium | [Code](../src/questions/q221/Q221DistinctWindowCounts.java) |
| Q222 | [Count subarrays containing exactly k distinct values](../docs/questions/q222.md) | Two sliding windows | Medium | [Code](../src/questions/q222/Q222ExactlyKDistinct.java) |
| Q223 | [Validate partial Sudoku constraints](../docs/questions/q223.md) | Hash-set constraint keys | Easy | [Code](../src/questions/q223/Q223SudokuValidity.java) |
| Q224 | [Detect whether repeated digit-square sums reach one](../docs/questions/q224.md) | Cycle detection with a set | Easy | [Code](../src/questions/q224/Q224HappyNumber.java) |
| Q225 | [Group words equal under one cyclic alphabet shift](../docs/questions/q225.md) | Hashing normalized signatures | Medium | [Code](../src/questions/q225/Q225ShiftedWordGroups.java) |
| Q226 | [Count ordered equal-distance point triples](../docs/questions/q226.md) | Distance frequency maps | Medium | [Code](../src/questions/q226/Q226Boomerangs.java) |
| Q227 | [Count zero-sum tuples from four arrays](../docs/questions/q227.md) | Pair-sum hashing | Medium | [Code](../src/questions/q227/Q227FourArrayZeroSum.java) |
| Q228 | [Find the maximum number of collinear points](../docs/questions/q228.md) | Normalized slope keys | Hard | [Code](../src/questions/q228/Q228CollinearPoints.java) |
| Q229 | [Use every ticket in lexical itinerary order](../docs/questions/q229.md) | Maps of priority queues | Hard | [Code](../src/questions/q229/Q229TicketItinerary.java) |
| Q230 | [Implement an integer map with collision chains](../docs/questions/q230.md) | Hash-table implementation | Medium | [Code](../src/questions/q230/Q230ChainedHashMap.java) |
| Q231 | [Implement a hash set with linear probing and tombstones](../docs/questions/q231.md) | Hash-table implementation | Medium | [Code](../src/questions/q231/Q231OpenAddressSet.java) |
| Q232 | [Pop the most frequent value with recency ties](../docs/questions/q232.md) | Frequency buckets and stacks | Hard | [Code](../src/questions/q232/Q232FrequencyStack.java) |
| Q233 | [Answer whether any value has an exact frequency](../docs/questions/q233.md) | Two-level hash counts | Medium | [Code](../src/questions/q233/Q233FrequencyQueries.java) |
| Q234 | [Find shared preferences with minimum combined rank](../docs/questions/q234.md) | Hash lookup and ranking | Easy | [Code](../src/questions/q234/Q234MinimumIndexSum.java) |
| Q235 | [Format a rational number with a repeating decimal cycle](../docs/questions/q235.md) | Remainder-position map | Medium | [Code](../src/questions/q235/Q235RepeatingDecimal.java) |
| Q236 | [Search a sorted array by halving the candidate interval](../docs/questions/q236.md) | Binary search | Easy | [Code](../src/questions/q236/Q236IterativeBinarySearch.java) |
| Q237 | [Find the first sorted value at least a target](../docs/questions/q237.md) | Binary-search boundaries | Easy | [Code](../src/questions/q237/Q237LowerBound.java) |
| Q238 | [Find the first sorted value strictly greater than a target](../docs/questions/q238.md) | Binary-search boundaries | Easy | [Code](../src/questions/q238/Q238UpperBound.java) |
| Q239 | [Search a rotated sorted array with distinct values](../docs/questions/q239.md) | Modified binary search | Medium | [Code](../src/questions/q239/Q239RotatedSearch.java) |
| Q240 | [Find the minimum in a rotated array with duplicates](../docs/questions/q240.md) | Ambiguous binary search | Medium | [Code](../src/questions/q240/Q240RotatedMinimum.java) |
| Q241 | [Find a strict local peak without scanning the whole array](../docs/questions/q241.md) | Binary search on slope | Medium | [Code](../src/questions/q241/Q241PeakElement.java) |
| Q242 | [Compute the floor square root without floating point](../docs/questions/q242.md) | Integer binary search | Easy | [Code](../src/questions/q242/Q242IntegerSquareRoot.java) |
| Q243 | [Find the median of two sorted arrays by partitioning](../docs/questions/q243.md) | Binary-search partitions | Hard | [Code](../src/questions/q243/Q243MedianSortedArrays.java) |
| Q244 | [Sort with adjacent exchanges and an early-stop check](../docs/questions/q244.md) | Elementary sorting | Easy | [Code](../src/questions/q244/Q244BubbleSort.java) |
| Q245 | [Sort by repeatedly selecting the smallest remaining value](../docs/questions/q245.md) | Elementary sorting | Easy | [Code](../src/questions/q245/Q245SelectionSort.java) |
| Q246 | [Sort by inserting each value into a sorted prefix](../docs/questions/q246.md) | Elementary sorting | Easy | [Code](../src/questions/q246/Q246InsertionSort.java) |
| Q247 | [Sort by dividing and stably merging halves](../docs/questions/q247.md) | Divide-and-conquer sorting | Medium | [Code](../src/questions/q247/Q247MergeSort.java) |
| Q248 | [Sort with three-way quicksort partitions](../docs/questions/q248.md) | Partition sorting | Medium | [Code](../src/questions/q248/Q248ThreeWayQuickSort.java) |
| Q249 | [Sort a bounded integer range with occurrence counts](../docs/questions/q249.md) | Non-comparison sorting | Medium | [Code](../src/questions/q249/Q249CountingSort.java) |
| Q250 | [Sort signed ints using four stable byte passes](../docs/questions/q250.md) | Radix sorting | Medium | [Code](../src/questions/q250/Q250SignedRadixSort.java) |
| Q251 | [List tree values in inorder](../docs/questions/q251.md) | Trees and BST | Beginner | [Code](../src/questions/q251/Q251Inorder.java) |
| Q252 | [List tree values in preorder](../docs/questions/q252.md) | Trees and BST | Beginner | [Code](../src/questions/q252/Q252Preorder.java) |
| Q253 | [List tree values in postorder](../docs/questions/q253.md) | Trees and BST | Beginner | [Code](../src/questions/q253/Q253Postorder.java) |
| Q254 | [List values in breadth-first level order](../docs/questions/q254.md) | Trees and BST | Beginner | [Code](../src/questions/q254/Q254LevelOrder.java) |
| Q255 | [Alternate direction on successive tree levels](../docs/questions/q255.md) | Trees and BST | Beginner | [Code](../src/questions/q255/Q255ZigzagLevels.java) |
| Q256 | [Count every node](../docs/questions/q256.md) | Trees and BST | Beginner | [Code](../src/questions/q256/Q256NodeCount.java) |
| Q257 | [Count leaf nodes](../docs/questions/q257.md) | Trees and BST | Beginner | [Code](../src/questions/q257/Q257LeafCount.java) |
| Q258 | [Measure height in nodes](../docs/questions/q258.md) | Trees and BST | Beginner | [Code](../src/questions/q258/Q258TreeHeight.java) |
| Q259 | [Sum all tree values](../docs/questions/q259.md) | Trees and BST | Beginner | [Code](../src/questions/q259/Q259TreeSum.java) |
| Q260 | [Find the maximum tree value](../docs/questions/q260.md) | Trees and BST | Beginner | [Code](../src/questions/q260/Q260TreeMaximum.java) |
| Q261 | [Check whether every subtree is height-balanced](../docs/questions/q261.md) | Trees and BST | Intermediate | [Code](../src/questions/q261/Q261BalancedTree.java) |
| Q262 | [Find tree diameter measured in edges](../docs/questions/q262.md) | Trees and BST | Intermediate | [Code](../src/questions/q262/Q262TreeDiameter.java) |
| Q263 | [Return the left view of a binary tree](../docs/questions/q263.md) | Trees and BST | Intermediate | [Code](../src/questions/q263/Q263LeftView.java) |
| Q264 | [Return the right view of a binary tree](../docs/questions/q264.md) | Trees and BST | Intermediate | [Code](../src/questions/q264/Q264RightView.java) |
| Q265 | [Compute the sum at every depth](../docs/questions/q265.md) | Trees and BST | Intermediate | [Code](../src/questions/q265/Q265LevelSums.java) |
| Q266 | [Mirror a tree and return its inorder traversal](../docs/questions/q266.md) | Trees and BST | Intermediate | [Code](../src/questions/q266/Q266MirrorTree.java) |
| Q267 | [Check whether a root-to-leaf path has a target sum](../docs/questions/q267.md) | Trees and BST | Intermediate | [Code](../src/questions/q267/Q267RootLeafSum.java) |
| Q268 | [Sum numbers represented by root-to-leaf digits](../docs/questions/q268.md) | Trees and BST | Intermediate | [Code](../src/questions/q268/Q268RootLeafNumbers.java) |
| Q269 | [Sum only the deepest leaves](../docs/questions/q269.md) | Trees and BST | Intermediate | [Code](../src/questions/q269/Q269DeepestLeavesSum.java) |
| Q270 | [Find the maximum sum of a nonempty tree path](../docs/questions/q270.md) | Trees and BST | Intermediate | [Code](../src/questions/q270/Q270MaximumPathSum.java) |
| Q271 | [Find the lowest common ancestor of two existing values](../docs/questions/q271.md) | Trees and BST | Intermediate | [Code](../src/questions/q271/Q271LowestCommonAncestor.java) |
| Q272 | [Search a value in a binary search tree](../docs/questions/q272.md) | Trees and BST | Intermediate | [Code](../src/questions/q272/Q272BstSearch.java) |
| Q273 | [Validate BST ordering against every ancestor](../docs/questions/q273.md) | Trees and BST | Intermediate | [Code](../src/questions/q273/Q273ValidateBst.java) |
| Q274 | [Find the kth smallest BST key](../docs/questions/q274.md) | Trees and BST | Intermediate | [Code](../src/questions/q274/Q274KthSmallest.java) |
| Q275 | [Find the greatest BST key not exceeding a target](../docs/questions/q275.md) | Trees and BST | Intermediate | [Code](../src/questions/q275/Q275BstFloor.java) |
| Q276 | [Find the smallest BST key at least a target](../docs/questions/q276.md) | Trees and BST | Intermediate | [Code](../src/questions/q276/Q276BstCeiling.java) |
| Q277 | [Sum BST keys in an inclusive interval](../docs/questions/q277.md) | Trees and BST | Intermediate | [Code](../src/questions/q277/Q277BstRangeSum.java) |
| Q278 | [Insert a missing BST key and show sorted contents](../docs/questions/q278.md) | Trees and BST | Intermediate | [Code](../src/questions/q278/Q278BstInsert.java) |
| Q279 | [Delete a BST key while preserving ordering](../docs/questions/q279.md) | Trees and BST | Intermediate | [Code](../src/questions/q279/Q279BstDelete.java) |
| Q280 | [Check mirror symmetry around the root](../docs/questions/q280.md) | Trees and BST | Intermediate | [Code](../src/questions/q280/Q280SymmetricTree.java) |
| Q281 | [Check whether a binary tree is complete](../docs/questions/q281.md) | Trees and BST | Intermediate | [Code](../src/questions/q281/Q281CompleteTree.java) |
| Q282 | [Find minimum root-to-leaf depth in nodes](../docs/questions/q282.md) | Trees and BST | Intermediate | [Code](../src/questions/q282/Q282MinimumDepth.java) |
| Q283 | [List nodes at a given depth from the root](../docs/questions/q283.md) | Trees and BST | Intermediate | [Code](../src/questions/q283/Q283NodesAtDistance.java) |
| Q284 | [Serialize a tree with null markers](../docs/questions/q284.md) | Trees and BST | Intermediate | [Code](../src/questions/q284/Q284SerializeTree.java) |
| Q285 | [Flatten a tree into a preorder right-child chain](../docs/questions/q285.md) | Trees and BST | Intermediate | [Code](../src/questions/q285/Q285FlattenPreorder.java) |
| Q286 | [Find the kth largest array element](../docs/questions/q286.md) | Heaps | Medium | [Code](../src/questions/q286/Q286KthLargest.java) |
| Q287 | [Find the kth smallest array element](../docs/questions/q287.md) | Heaps | Medium | [Code](../src/questions/q287/Q287KthSmallest.java) |
| Q288 | [Return the k largest values in descending order](../docs/questions/q288.md) | Heaps | Medium | [Code](../src/questions/q288/Q288LargestK.java) |
| Q289 | [Merge k sorted integer feeds](../docs/questions/q289.md) | Heaps | Hard | [Code](../src/questions/q289/Q289MergeSortedFeeds.java) |
| Q290 | [Minimize the cost of connecting ropes](../docs/questions/q290.md) | Heaps and greedy | Medium | [Code](../src/questions/q290/Q290ConnectRopes.java) |
| Q291 | [Report the median after each stream value](../docs/questions/q291.md) | Two heaps | Hard | [Code](../src/questions/q291/Q291RunningMedian.java) |
| Q292 | [Report the kth largest after every eligible prefix](../docs/questions/q292.md) | Heaps | Medium | [Code](../src/questions/q292/Q292StreamingKth.java) |
| Q293 | [Select the k most frequent integers](../docs/questions/q293.md) | Hashing and heaps | Medium | [Code](../src/questions/q293/Q293FrequentValues.java) |
| Q294 | [Select the most frequent words with lexical ties](../docs/questions/q294.md) | Maps and priority queues | Medium | [Code](../src/questions/q294/Q294FrequentWords.java) |
| Q295 | [Return k points nearest the origin](../docs/questions/q295.md) | Heaps and geometry | Medium | [Code](../src/questions/q295/Q295ClosestPoints.java) |
| Q296 | [Simulate repeatedly smashing the two heaviest stones](../docs/questions/q296.md) | Max heaps | Easy | [Code](../src/questions/q296/Q296LastStone.java) |
| Q297 | [Find the minimum number of meeting rooms](../docs/questions/q297.md) | Interval heaps | Medium | [Code](../src/questions/q297/Q297MeetingRooms.java) |
| Q298 | [Find the minimum slots for tasks with a cooldown](../docs/questions/q298.md) | Greedy scheduling | Medium | [Code](../src/questions/q298/Q298CooldownTasks.java) |
| Q299 | [Sort values displaced by at most k positions](../docs/questions/q299.md) | Bounded heaps | Medium | [Code](../src/questions/q299/Q299SortNearlySorted.java) |
| Q300 | [Find k pairs with the smallest sums](../docs/questions/q300.md) | Heap frontier | Medium | [Code](../src/questions/q300/Q300SmallestSumPairs.java) |
| Q301 | [Build a binary min-heap in an array](../docs/questions/q301.md) | Heap construction | Medium | [Code](../src/questions/q301/Q301BuildMinHeap.java) |
| Q302 | [Check the binary min-heap property](../docs/questions/q302.md) | Heap invariants | Easy | [Code](../src/questions/q302/Q302ValidateMinHeap.java) |
| Q303 | [Sort an integer array using an explicit heap](../docs/questions/q303.md) | Sorting with heaps | Medium | [Code](../src/questions/q303/Q303HeapSort.java) |
| Q304 | [Minimize refueling stops to reach a target](../docs/questions/q304.md) | Greedy max-heap | Hard | [Code](../src/questions/q304/Q304RefuelStops.java) |
| Q305 | [Find the smallest range covering every sorted feed](../docs/questions/q305.md) | Heap frontier | Hard | [Code](../src/questions/q305/Q305CoveringRange.java) |
| Q306 | [Build directed adjacency lists from an edge list](../docs/questions/q306.md) | Graphs | Beginner | [Code](../src/questions/q306/Q306AdjacencyLists.java) |
| Q307 | [Build an undirected adjacency matrix](../docs/questions/q307.md) | Graphs | Beginner | [Code](../src/questions/q307/Q307AdjacencyMatrix.java) |
| Q308 | [Count degrees in an undirected multigraph](../docs/questions/q308.md) | Graphs | Beginner | [Code](../src/questions/q308/Q308VertexDegrees.java) |
| Q309 | [Reverse every directed graph edge](../docs/questions/q309.md) | Graphs | Easy | [Code](../src/questions/q309/Q309TransposeGraph.java) |
| Q310 | [Traverse reachable vertices in breadth-first order](../docs/questions/q310.md) | Graphs | Medium | [Code](../src/questions/q310/Q310BreadthFirstOrder.java) |
| Q311 | [Traverse reachable vertices in depth-first order](../docs/questions/q311.md) | Graphs | Medium | [Code](../src/questions/q311/Q311DepthFirstOrder.java) |
| Q312 | [Count connected components in an undirected graph](../docs/questions/q312.md) | Graphs | Medium | [Code](../src/questions/q312/Q312ConnectedComponents.java) |
| Q313 | [Determine whether a directed target is reachable](../docs/questions/q313.md) | Graphs | Medium | [Code](../src/questions/q313/Q313Reachability.java) |
| Q314 | [Find shortest edge-count distances from a source](../docs/questions/q314.md) | Graphs | Medium | [Code](../src/questions/q314/Q314UnweightedDistances.java) |
| Q315 | [Reconstruct one shortest unweighted route](../docs/questions/q315.md) | Graphs | Medium | [Code](../src/questions/q315/Q315ShortestRoute.java) |
| Q316 | [Detect a cycle in a simple undirected graph](../docs/questions/q316.md) | Graphs | Medium | [Code](../src/questions/q316/Q316UndirectedCycle.java) |
| Q317 | [Detect a directed cycle using DFS colors](../docs/questions/q317.md) | Graphs | Medium | [Code](../src/questions/q317/Q317DirectedCycle.java) |
| Q318 | [Produce a deterministic topological order or report a cycle](../docs/questions/q318.md) | Graphs | Medium | [Code](../src/questions/q318/Q318TopologicalOrder.java) |
| Q319 | [Check whether an undirected graph can be two-colored](../docs/questions/q319.md) | Graphs | Medium | [Code](../src/questions/q319/Q319BipartiteCheck.java) |
| Q320 | [Enumerate every source-to-target path in a DAG](../docs/questions/q320.md) | Graphs | Medium | [Code](../src/questions/q320/Q320AllDagPaths.java) |
| Q321 | [Count four-connected islands in a binary grid](../docs/questions/q321.md) | Grid graphs | Medium | [Code](../src/questions/q321/Q321IslandCount.java) |
| Q322 | [Find the area of the largest island](../docs/questions/q322.md) | Grid graphs | Medium | [Code](../src/questions/q322/Q322MaximumIsland.java) |
| Q323 | [Recolor the connected region containing a chosen pixel](../docs/questions/q323.md) | Grid traversal | Easy | [Code](../src/questions/q323/Q323FloodFill.java) |
| Q324 | [Compute each cell distance to the nearest zero](../docs/questions/q324.md) | Multi-source BFS | Medium | [Code](../src/questions/q324/Q324NearestZero.java) |
| Q325 | [Find the time until all reachable fresh oranges rot](../docs/questions/q325.md) | Multi-source BFS | Medium | [Code](../src/questions/q325/Q325RottingTime.java) |
| Q326 | [Find the shortest one-letter word transformation](../docs/questions/q326.md) | Implicit graphs | Hard | [Code](../src/questions/q326/Q326WordLadder.java) |
| Q327 | [Find minimum semesters with unlimited parallel courses](../docs/questions/q327.md) | Graphs | Medium | [Code](../src/questions/q327/Q327MinimumSemesters.java) |
| Q328 | [Infer an alphabet order from sorted words](../docs/questions/q328.md) | Topological ordering | Hard | [Code](../src/questions/q328/Q328AlienAlphabet.java) |
| Q329 | [Find shortest paths with nonnegative weights](../docs/questions/q329.md) | Graphs | Medium | [Code](../src/questions/q329/Q329DijkstraDistances.java) |
| Q330 | [Find shortest paths allowing negative edges](../docs/questions/q330.md) | Graphs | Medium | [Code](../src/questions/q330/Q330BellmanFord.java) |
| Q331 | [Find all-pairs shortest distances](../docs/questions/q331.md) | Graphs | Medium | [Code](../src/questions/q331/Q331FloydWarshall.java) |
| Q332 | [Find an undirected minimum spanning-tree weight with Prim](../docs/questions/q332.md) | Graphs | Medium | [Code](../src/questions/q332/Q332PrimMst.java) |
| Q333 | [Find a minimum spanning-tree weight using sorted edges](../docs/questions/q333.md) | Graphs | Medium | [Code](../src/questions/q333/Q333KruskalMst.java) |
| Q334 | [Process union and connectivity queries online](../docs/questions/q334.md) | Graphs | Medium | [Code](../src/questions/q334/Q334OnlineConnectivity.java) |
| Q335 | [Return the last edge that closes an undirected cycle](../docs/questions/q335.md) | Graphs | Medium | [Code](../src/questions/q335/Q335RedundantEdge.java) |
| Q336 | [Count provinces from a symmetric connection matrix](../docs/questions/q336.md) | Graphs | Medium | [Code](../src/questions/q336/Q336MatrixComponents.java) |
| Q337 | [Count strongly connected components with Kosaraju](../docs/questions/q337.md) | Graphs | Medium | [Code](../src/questions/q337/Q337StrongComponents.java) |
| Q338 | [Find edges whose removal disconnects an undirected graph](../docs/questions/q338.md) | Graphs | Medium | [Code](../src/questions/q338/Q338Bridges.java) |
| Q339 | [Find vertices whose removal increases component count](../docs/questions/q339.md) | Graphs | Medium | [Code](../src/questions/q339/Q339ArticulationPoints.java) |
| Q340 | [Decide whether all undirected edges can be traversed once](../docs/questions/q340.md) | Graphs | Medium | [Code](../src/questions/q340/Q340EulerTrailExists.java) |
| Q341 | [Decide whether a path visits every vertex exactly once](../docs/questions/q341.md) | Graphs | Hard | [Code](../src/questions/q341/Q341HamiltonianPath.java) |
| Q342 | [Find minimum dice rolls on a board with snakes and ladders](../docs/questions/q342.md) | Graphs | Medium | [Code](../src/questions/q342/Q342BoardJumps.java) |
| Q343 | [Find the fewest obstacles to remove across a grid](../docs/questions/q343.md) | Graphs | Medium | [Code](../src/questions/q343/Q343ObstacleRemoval.java) |
| Q344 | [Find the longest directed path length in a DAG](../docs/questions/q344.md) | Graphs | Medium | [Code](../src/questions/q344/Q344LongestDagPath.java) |
| Q345 | [Count directed source-to-target paths in a DAG](../docs/questions/q345.md) | Graphs | Medium | [Code](../src/questions/q345/Q345DagPathCount.java) |
| Q346 | [Find complete dictionary words with a trie](../docs/questions/q346.md) | Tries | Beginner | [Code](../src/questions/q346/Q346ExactTrieSearch.java) |
| Q347 | [Check whether dictionary words share a requested prefix](../docs/questions/q347.md) | Tries | Beginner | [Code](../src/questions/q347/Q347TriePrefixExists.java) |
| Q348 | [Count dictionary occurrences under each prefix](../docs/questions/q348.md) | Tries | Beginner | [Code](../src/questions/q348/Q348TriePrefixCounts.java) |
| Q349 | [Return the first k lexicographic completions of a prefix](../docs/questions/q349.md) | Tries | Medium | [Code](../src/questions/q349/Q349TrieAutocomplete.java) |
| Q350 | [Find the longest prefix shared by all dictionary words](../docs/questions/q350.md) | Tries | Beginner | [Code](../src/questions/q350/Q350TrieCommonPrefix.java) |
| Q351 | [Delete exact words without damaging shared trie prefixes](../docs/questions/q351.md) | Tries | Medium | [Code](../src/questions/q351/Q351TrieDeletion.java) |
| Q352 | [Find the shortest distinguishing prefix of each word](../docs/questions/q352.md) | Tries | Medium | [Code](../src/questions/q352/Q352UniqueWordPrefixes.java) |
| Q353 | [Search dictionary patterns with single-character wildcards](../docs/questions/q353.md) | Trie backtracking | Medium | [Code](../src/questions/q353/Q353WildcardDictionary.java) |
| Q354 | [Replace words by their shortest stored root](../docs/questions/q354.md) | Prefix dictionaries | Medium | [Code](../src/questions/q354/Q354ReplaceRoots.java) |
| Q355 | [Find the fewest dictionary pieces forming a string](../docs/questions/q355.md) | Trie-guided dynamic programming | Medium | [Code](../src/questions/q355/Q355MinimumWordSegments.java) |
| Q356 | [Maximize the unsigned XOR of two array values](../docs/questions/q356.md) | Binary tries | Medium | [Code](../src/questions/q356/Q356MaximumPairXor.java) |
| Q357 | [Find the latest word matching both prefix and suffix](../docs/questions/q357.md) | Combined-key trie | Hard | [Code](../src/questions/q357/Q357PrefixSuffixFilter.java) |
| Q358 | [Find a longest word whose every prefix is a word](../docs/questions/q358.md) | Trie terminal chains | Medium | [Code](../src/questions/q358/Q358BuildableWord.java) |
| Q359 | [Count distinct nonempty substrings with a suffix trie](../docs/questions/q359.md) | Suffix tries | Medium | [Code](../src/questions/q359/Q359DistinctSubstrings.java) |
| Q360 | [Check whether each stream prefix ends in a dictionary word](../docs/questions/q360.md) | Reversed trie and bounded history | Medium | [Code](../src/questions/q360/Q360StreamSuffixMatches.java) |
| Q361 | [Maintain prefix totals under point additions](../docs/questions/q361.md) | Fenwick trees | Medium | [Code](../src/questions/q361/Q361FenwickPrefixUpdates.java) |
| Q362 | [Answer range totals while assigning individual values](../docs/questions/q362.md) | Fenwick trees | Medium | [Code](../src/questions/q362/Q362FenwickRangeAssignments.java) |
| Q363 | [Add to whole intervals and query individual values](../docs/questions/q363.md) | Difference arrays with Fenwick trees | Medium | [Code](../src/questions/q363/Q363FenwickRangeAddPoint.java) |
| Q364 | [Support range additions and range totals with two Fenwick trees](../docs/questions/q364.md) | Weighted difference prefixes | Hard | [Code](../src/questions/q364/Q364FenwickRangeAddSum.java) |
| Q365 | [Locate a cumulative rank in changing frequencies](../docs/questions/q365.md) | Fenwick binary lifting | Hard | [Code](../src/questions/q365/Q365FenwickOrderStatistic.java) |
| Q366 | [Count out-of-order index pairs with compressed ranks](../docs/questions/q366.md) | Fenwick trees and compression | Medium | [Code](../src/questions/q366/Q366InversionCount.java) |
| Q367 | [Count strictly smaller later values for every position](../docs/questions/q367.md) | Fenwick rank frequencies | Medium | [Code](../src/questions/q367/Q367SmallerToRight.java) |
| Q368 | [Count range values above independent thresholds](../docs/questions/q368.md) | Offline sweep with Fenwick tree | Hard | [Code](../src/questions/q368/Q368OfflineThresholdQueries.java) |
| Q369 | [Maintain range minima under point assignment](../docs/questions/q369.md) | Iterative segment trees | Medium | [Code](../src/questions/q369/Q369SegmentMinimum.java) |
| Q370 | [Maintain range greatest common divisors under assignments](../docs/questions/q370.md) | Associative segment aggregates | Medium | [Code](../src/questions/q370/Q370SegmentGcd.java) |
| Q371 | [Add to intervals and query totals with lazy propagation](../docs/questions/q371.md) | Lazy segment trees | Hard | [Code](../src/questions/q371/Q371LazyRangeSum.java) |
| Q372 | [Maintain interval minima under interval increments](../docs/questions/q372.md) | Lazy segment trees | Hard | [Code](../src/questions/q372/Q372LazyRangeMinimum.java) |
| Q373 | [Answer static range minima in constant query time](../docs/questions/q373.md) | Sparse tables | Medium | [Code](../src/questions/q373/Q373SparseTableMinimum.java) |
| Q374 | [Maintain range totals with square-root-sized blocks](../docs/questions/q374.md) | Square-root decomposition | Medium | [Code](../src/questions/q374/Q374SqrtDecomposition.java) |
| Q375 | [Find kth-smallest values in static subarrays using persistent trees](../docs/questions/q375.md) | Persistent segment trees | Hard | [Code](../src/questions/q375/Q375PersistentRangeKth.java) |
| Q376 | [Recursive Factorial](../docs/questions/q376.md) | Recursion and backtracking | Easy | [Code](../src/questions/q376/Q376RecursiveFactorial.java) |
| Q377 | [Recursive Digit Sum](../docs/questions/q377.md) | Recursion and backtracking | Easy | [Code](../src/questions/q377/Q377RecursiveDigitSum.java) |
| Q378 | [Recursive Array Total](../docs/questions/q378.md) | Recursion and backtracking | Easy | [Code](../src/questions/q378/Q378RecursiveArrayTotal.java) |
| Q379 | [Recursive String Reversal](../docs/questions/q379.md) | Recursion and backtracking | Easy | [Code](../src/questions/q379/Q379RecursiveStringReversal.java) |
| Q380 | [Recursive Palindrome Test](../docs/questions/q380.md) | Recursion and backtracking | Easy | [Code](../src/questions/q380/Q380RecursivePalindromeTest.java) |
| Q381 | [Fast Recursive Power](../docs/questions/q381.md) | Recursion and backtracking | Medium | [Code](../src/questions/q381/Q381FastRecursivePower.java) |
| Q382 | [Recursive Euclidean GCD](../docs/questions/q382.md) | Recursion and backtracking | Easy | [Code](../src/questions/q382/Q382RecursiveEuclideanGCD.java) |
| Q383 | [Recursive Binary Search](../docs/questions/q383.md) | Recursion and backtracking | Easy | [Code](../src/questions/q383/Q383RecursiveBinarySearch.java) |
| Q384 | [Tower Of Hanoi Moves](../docs/questions/q384.md) | Recursion and backtracking | Medium | [Code](../src/questions/q384/Q384TowerOfHanoiMoves.java) |
| Q385 | [Generate String Subsequences](../docs/questions/q385.md) | Recursion and backtracking | Medium | [Code](../src/questions/q385/Q385GenerateStringSubsequences.java) |
| Q386 | [Generate Distinct Permutations](../docs/questions/q386.md) | Recursion and backtracking | Medium | [Code](../src/questions/q386/Q386GenerateDistinctPermutations.java) |
| Q387 | [Choose K Numbers](../docs/questions/q387.md) | Recursion and backtracking | Medium | [Code](../src/questions/q387/Q387ChooseKNumbers.java) |
| Q388 | [Phone Keypad Combinations](../docs/questions/q388.md) | Recursion and backtracking | Medium | [Code](../src/questions/q388/Q388PhoneKeypadCombinations.java) |
| Q389 | [Generate Balanced Parentheses](../docs/questions/q389.md) | Recursion and backtracking | Medium | [Code](../src/questions/q389/Q389GenerateBalancedParentheses.java) |
| Q390 | [Count N Queens Placements](../docs/questions/q390.md) | Recursion and backtracking | Hard | [Code](../src/questions/q390/Q390CountNQueensPlacements.java) |
| Q391 | [Count Blocked Grid Paths Recursively](../docs/questions/q391.md) | Recursion and backtracking | Medium | [Code](../src/questions/q391/Q391CountBlockedGridPathsRecursively.java) |
| Q392 | [Word Search Backtracking](../docs/questions/q392.md) | Recursion and backtracking | Medium | [Code](../src/questions/q392/Q392WordSearchBacktracking.java) |
| Q393 | [Combination Sum Unlimited](../docs/questions/q393.md) | Recursion and backtracking | Medium | [Code](../src/questions/q393/Q393CombinationSumUnlimited.java) |
| Q394 | [Combination Sum Single Use](../docs/questions/q394.md) | Recursion and backtracking | Medium | [Code](../src/questions/q394/Q394CombinationSumSingleUse.java) |
| Q395 | [Recursive Subset Sum Decision](../docs/questions/q395.md) | Recursion and backtracking | Medium | [Code](../src/questions/q395/Q395RecursiveSubsetSumDecision.java) |
| Q396 | [Palindrome Partition Enumeration](../docs/questions/q396.md) | Recursion and backtracking | Medium | [Code](../src/questions/q396/Q396PalindromePartitionEnumeration.java) |
| Q397 | [Restore IPv4 Addresses](../docs/questions/q397.md) | Recursion and backtracking | Medium | [Code](../src/questions/q397/Q397RestoreIPv4Addresses.java) |
| Q398 | [Recursive Digit Decodings](../docs/questions/q398.md) | Recursion and backtracking | Medium | [Code](../src/questions/q398/Q398RecursiveDigitDecodings.java) |
| Q399 | [Kth Grammar Symbol](../docs/questions/q399.md) | Recursion and backtracking | Medium | [Code](../src/questions/q399/Q399KthGrammarSymbol.java) |
| Q400 | [Josephus Survivor](../docs/questions/q400.md) | Recursion and backtracking | Medium | [Code](../src/questions/q400/Q400JosephusSurvivor.java) |
| Q401 | [Climbing Stairs](../docs/questions/q401.md) | Dynamic programming | Easy | [Code](../src/questions/q401/Q401ClimbingStairs.java) |
| Q402 | [Minimum Cost Climbing Stairs](../docs/questions/q402.md) | Dynamic programming | Easy | [Code](../src/questions/q402/Q402MinimumCostClimbingStairs.java) |
| Q403 | [House Robber](../docs/questions/q403.md) | Dynamic programming | Medium | [Code](../src/questions/q403/Q403HouseRobber.java) |
| Q404 | [Circular House Robber](../docs/questions/q404.md) | Dynamic programming | Medium | [Code](../src/questions/q404/Q404CircularHouseRobber.java) |
| Q405 | [Unique Grid Paths](../docs/questions/q405.md) | Dynamic programming | Easy | [Code](../src/questions/q405/Q405UniqueGridPaths.java) |
| Q406 | [Grid Paths With Obstacles](../docs/questions/q406.md) | Dynamic programming | Medium | [Code](../src/questions/q406/Q406GridPathsWithObstacles.java) |
| Q407 | [Minimum Grid Path Sum](../docs/questions/q407.md) | Dynamic programming | Medium | [Code](../src/questions/q407/Q407MinimumGridPathSum.java) |
| Q408 | [Triangle Minimum Path](../docs/questions/q408.md) | Dynamic programming | Medium | [Code](../src/questions/q408/Q408TriangleMinimumPath.java) |
| Q409 | [Maximal Square Area](../docs/questions/q409.md) | Dynamic programming | Medium | [Code](../src/questions/q409/Q409MaximalSquareArea.java) |
| Q410 | [Count All One Squares](../docs/questions/q410.md) | Dynamic programming | Medium | [Code](../src/questions/q410/Q410CountAllOneSquares.java) |
| Q411 | [Minimum Coin Count](../docs/questions/q411.md) | Dynamic programming | Medium | [Code](../src/questions/q411/Q411MinimumCoinCount.java) |
| Q412 | [Coin Combination Count](../docs/questions/q412.md) | Dynamic programming | Medium | [Code](../src/questions/q412/Q412CoinCombinationCount.java) |
| Q413 | [Ordered Sum Count](../docs/questions/q413.md) | Dynamic programming | Medium | [Code](../src/questions/q413/Q413OrderedSumCount.java) |
| Q414 | [Target Sum Sign Assignments](../docs/questions/q414.md) | Dynamic programming | Medium | [Code](../src/questions/q414/Q414TargetSumSignAssignments.java) |
| Q415 | [Equal Sum Partition](../docs/questions/q415.md) | Dynamic programming | Medium | [Code](../src/questions/q415/Q415EqualSumPartition.java) |
| Q416 | [Minimum Subset Sum Difference](../docs/questions/q416.md) | Dynamic programming | Medium | [Code](../src/questions/q416/Q416MinimumSubsetSumDifference.java) |
| Q417 | [Zero One Knapsack](../docs/questions/q417.md) | Dynamic programming | Medium | [Code](../src/questions/q417/Q417ZeroOneKnapsack.java) |
| Q418 | [Unbounded Knapsack](../docs/questions/q418.md) | Dynamic programming | Medium | [Code](../src/questions/q418/Q418UnboundedKnapsack.java) |
| Q419 | [Rod Cutting Revenue](../docs/questions/q419.md) | Dynamic programming | Medium | [Code](../src/questions/q419/Q419RodCuttingRevenue.java) |
| Q420 | [Count Subsets With Target Sum](../docs/questions/q420.md) | Dynamic programming | Medium | [Code](../src/questions/q420/Q420CountSubsetsWithTargetSum.java) |
| Q421 | [Longest Common Subsequence Length](../docs/questions/q421.md) | Dynamic programming | Medium | [Code](../src/questions/q421/Q421LongestCommonSubsequenceLength.java) |
| Q422 | [Longest Common Substring Length](../docs/questions/q422.md) | Dynamic programming | Medium | [Code](../src/questions/q422/Q422LongestCommonSubstringLength.java) |
| Q423 | [Edit Distance](../docs/questions/q423.md) | Dynamic programming | Medium | [Code](../src/questions/q423/Q423EditDistance.java) |
| Q424 | [Minimum Deletions To Equal Strings](../docs/questions/q424.md) | Dynamic programming | Medium | [Code](../src/questions/q424/Q424MinimumDeletionsToEqualStrings.java) |
| Q425 | [Distinct Target Subsequences](../docs/questions/q425.md) | Dynamic programming | Medium | [Code](../src/questions/q425/Q425DistinctTargetSubsequences.java) |
| Q426 | [Count Palindromic Substrings](../docs/questions/q426.md) | Dynamic programming | Medium | [Code](../src/questions/q426/Q426CountPalindromicSubstrings.java) |
| Q427 | [Longest Palindromic Subsequence](../docs/questions/q427.md) | Dynamic programming | Medium | [Code](../src/questions/q427/Q427LongestPalindromicSubsequence.java) |
| Q428 | [Minimum Palindrome Partition Cuts](../docs/questions/q428.md) | Dynamic programming | Medium | [Code](../src/questions/q428/Q428MinimumPalindromePartitionCuts.java) |
| Q429 | [Interleaving Strings](../docs/questions/q429.md) | Dynamic programming | Medium | [Code](../src/questions/q429/Q429InterleavingStrings.java) |
| Q430 | [Wildcard Pattern Match](../docs/questions/q430.md) | Dynamic programming | Medium | [Code](../src/questions/q430/Q430WildcardPatternMatch.java) |
| Q431 | [Longest Increasing Subsequence](../docs/questions/q431.md) | Dynamic programming | Medium | [Code](../src/questions/q431/Q431LongestIncreasingSubsequence.java) |
| Q432 | [Count Longest Increasing Subsequences](../docs/questions/q432.md) | Dynamic programming | Medium | [Code](../src/questions/q432/Q432CountLongestIncreasingSubsequences.java) |
| Q433 | [Longest Bitonic Subsequence](../docs/questions/q433.md) | Dynamic programming | Medium | [Code](../src/questions/q433/Q433LongestBitonicSubsequence.java) |
| Q434 | [Word Break Feasibility](../docs/questions/q434.md) | Dynamic programming | Medium | [Code](../src/questions/q434/Q434WordBreakFeasibility.java) |
| Q435 | [Count Word Segmentations](../docs/questions/q435.md) | Dynamic programming | Medium | [Code](../src/questions/q435/Q435CountWordSegmentations.java) |
| Q436 | [Decode Digit Strings](../docs/questions/q436.md) | Dynamic programming | Medium | [Code](../src/questions/q436/Q436DecodeDigitStrings.java) |
| Q437 | [Maximal All One Rectangle](../docs/questions/q437.md) | Dynamic programming | Hard | [Code](../src/questions/q437/Q437MaximalAllOneRectangle.java) |
| Q438 | [Matrix Chain Multiplication Cost](../docs/questions/q438.md) | Dynamic programming | Hard | [Code](../src/questions/q438/Q438MatrixChainMultiplicationCost.java) |
| Q439 | [Burst Balloons](../docs/questions/q439.md) | Dynamic programming | Hard | [Code](../src/questions/q439/Q439BurstBalloons.java) |
| Q440 | [Egg Drop Minimum Moves](../docs/questions/q440.md) | Dynamic programming | Hard | [Code](../src/questions/q440/Q440EggDropMinimumMoves.java) |
| Q441 | [Stock Profit With Cooldown](../docs/questions/q441.md) | Dynamic programming | Medium | [Code](../src/questions/q441/Q441StockProfitWithCooldown.java) |
| Q442 | [Stock Profit With Transaction Fee](../docs/questions/q442.md) | Dynamic programming | Medium | [Code](../src/questions/q442/Q442StockProfitWithTransactionFee.java) |
| Q443 | [Stock Profit With At Most K Trades](../docs/questions/q443.md) | Dynamic programming | Hard | [Code](../src/questions/q443/Q443StockProfitWithAtMostKTrades.java) |
| Q444 | [Dungeon Minimum Initial Health](../docs/questions/q444.md) | Dynamic programming | Hard | [Code](../src/questions/q444/Q444DungeonMinimumInitialHealth.java) |
| Q445 | [Minimum Falling Path Sum](../docs/questions/q445.md) | Dynamic programming | Medium | [Code](../src/questions/q445/Q445MinimumFallingPathSum.java) |
| Q446 | [Regular Expression Full Match](../docs/questions/q446.md) | Dynamic programming | Hard | [Code](../src/questions/q446/Q446RegularExpressionFullMatch.java) |
| Q447 | [Boolean Parenthesization Count](../docs/questions/q447.md) | Dynamic programming | Hard | [Code](../src/questions/q447/Q447BooleanParenthesizationCount.java) |
| Q448 | [Optimal Search Tree Cost](../docs/questions/q448.md) | Dynamic programming | Hard | [Code](../src/questions/q448/Q448OptimalSearchTreeCost.java) |
| Q449 | [Frog Jump Across Stones](../docs/questions/q449.md) | Dynamic programming | Hard | [Code](../src/questions/q449/Q449FrogJumpAcrossStones.java) |
| Q450 | [Longest Arithmetic Subsequence](../docs/questions/q450.md) | Dynamic programming | Medium | [Code](../src/questions/q450/Q450LongestArithmeticSubsequence.java) |
| Q451 | [Maximum Compatible Meetings](../docs/questions/q451.md) | Greedy and search on answer | Medium | [Code](../src/questions/q451/Q451MaximumCompatibleMeetings.java) |
| Q452 | [Maximum Courses Before Deadlines](../docs/questions/q452.md) | Greedy and search on answer | Hard | [Code](../src/questions/q452/Q452MaximumCoursesBeforeDeadlines.java) |
| Q453 | [Assign Cookies](../docs/questions/q453.md) | Greedy and search on answer | Easy | [Code](../src/questions/q453/Q453AssignCookies.java) |
| Q454 | [Lemonade Change](../docs/questions/q454.md) | Greedy and search on answer | Easy | [Code](../src/questions/q454/Q454LemonadeChange.java) |
| Q455 | [Jump Game Reachability](../docs/questions/q455.md) | Greedy and search on answer | Medium | [Code](../src/questions/q455/Q455JumpGameReachability.java) |
| Q456 | [Minimum Forward Jumps](../docs/questions/q456.md) | Greedy and search on answer | Medium | [Code](../src/questions/q456/Q456MinimumForwardJumps.java) |
| Q457 | [Gas Station Circuit](../docs/questions/q457.md) | Greedy and search on answer | Medium | [Code](../src/questions/q457/Q457GasStationCircuit.java) |
| Q458 | [Minimum Candy Allocation](../docs/questions/q458.md) | Greedy and search on answer | Hard | [Code](../src/questions/q458/Q458MinimumCandyAllocation.java) |
| Q459 | [Task Scheduler With Cooldown](../docs/questions/q459.md) | Greedy and search on answer | Medium | [Code](../src/questions/q459/Q459TaskSchedulerWithCooldown.java) |
| Q460 | [Partition Labels](../docs/questions/q460.md) | Greedy and search on answer | Medium | [Code](../src/questions/q460/Q460PartitionLabels.java) |
| Q461 | [Queue Reconstruction By Height](../docs/questions/q461.md) | Greedy and search on answer | Medium | [Code](../src/questions/q461/Q461QueueReconstructionByHeight.java) |
| Q462 | [Huffman Weighted Encoding Cost](../docs/questions/q462.md) | Greedy and search on answer | Medium | [Code](../src/questions/q462/Q462HuffmanWeightedEncodingCost.java) |
| Q463 | [Maximum Units On A Truck](../docs/questions/q463.md) | Greedy and search on answer | Easy | [Code](../src/questions/q463/Q463MaximumUnitsOnATruck.java) |
| Q464 | [Fractional Knapsack](../docs/questions/q464.md) | Greedy and search on answer | Medium | [Code](../src/questions/q464/Q464FractionalKnapsack.java) |
| Q465 | [Deadline Job Sequencing Profit](../docs/questions/q465.md) | Greedy and search on answer | Medium | [Code](../src/questions/q465/Q465DeadlineJobSequencingProfit.java) |
| Q466 | [Minimum Simultaneous Platforms](../docs/questions/q466.md) | Greedy and search on answer | Medium | [Code](../src/questions/q466/Q466MinimumSimultaneousPlatforms.java) |
| Q467 | [Minimum Rescue Boats](../docs/questions/q467.md) | Greedy and search on answer | Medium | [Code](../src/questions/q467/Q467MinimumRescueBoats.java) |
| Q468 | [Minimum Refueling Stops](../docs/questions/q468.md) | Greedy and search on answer | Hard | [Code](../src/questions/q468/Q468MinimumRefuelingStops.java) |
| Q469 | [Minimum Banana Eating Speed](../docs/questions/q469.md) | Greedy and search on answer | Medium | [Code](../src/questions/q469/Q469MinimumBananaEatingSpeed.java) |
| Q470 | [Ship Packages Within Days](../docs/questions/q470.md) | Greedy and search on answer | Medium | [Code](../src/questions/q470/Q470ShipPackagesWithinDays.java) |
| Q471 | [Minimize the largest sum among k contiguous groups](../docs/questions/q471.md) | Binary search on answer | Hard | [Code](../src/questions/q471/Q471SplitArrayMinimax.java) |
| Q472 | [Place k items to maximize their minimum separation](../docs/questions/q472.md) | Binary search and greedy placement | Medium | [Code](../src/questions/q472/Q472MaximizeSeparation.java) |
| Q473 | [Find the kth entry in a multiplication table](../docs/questions/q473.md) | Value-domain binary search | Hard | [Code](../src/questions/q473/Q473MultiplicationTableKth.java) |
| Q474 | [Find the earliest time machines finish a target workload](../docs/questions/q474.md) | Monotone production counts | Medium | [Code](../src/questions/q474/Q474ParallelProductionTime.java) |
| Q475 | [Find the median of a matrix with sorted rows](../docs/questions/q475.md) | Binary search over values | Hard | [Code](../src/questions/q475/Q475RowSortedMedian.java) |
| Q476 | [Count one bits by clearing the lowest set bit](../docs/questions/q476.md) | Bit manipulation | Easy | [Code](../src/questions/q476/Q476SetBitCount.java) |
| Q477 | [Recognize a positive power of two](../docs/questions/q477.md) | Bit manipulation | Easy | [Code](../src/questions/q477/Q477PowerOfTwo.java) |
| Q478 | [Recover one unpaired value with XOR](../docs/questions/q478.md) | XOR cancellation | Easy | [Code](../src/questions/q478/Q478SingleAmongPairs.java) |
| Q479 | [Recover two distinct unpaired values with XOR partitioning](../docs/questions/q479.md) | Bit partitions | Medium | [Code](../src/questions/q479/Q479TwoSingles.java) |
| Q480 | [Recover a singleton when every other value occurs three times](../docs/questions/q480.md) | Bit-state counters | Medium | [Code](../src/questions/q480/Q480SingleAmongTriples.java) |
| Q481 | [Compute the bitwise AND of an inclusive integer range](../docs/questions/q481.md) | Common binary prefixes | Medium | [Code](../src/questions/q481/Q481RangeBitwiseAnd.java) |
| Q482 | [Reverse all32 bits and return their unsigned value](../docs/questions/q482.md) | Bit manipulation | Easy | [Code](../src/questions/q482/Q482ReverseBits.java) |
| Q483 | [Generate a sequence whose adjacent bitmasks differ by one bit](../docs/questions/q483.md) | Gray-code construction | Medium | [Code](../src/questions/q483/Q483GrayCode.java) |
| Q484 | [Enumerate every submask of a bitmask exactly once](../docs/questions/q484.md) | Subset bit operations | Medium | [Code](../src/questions/q484/Q484EnumerateSubmasks.java) |
| Q485 | [Find a minimum round trip with subset dynamic programming](../docs/questions/q485.md) | Bitmask dynamic programming | Hard | [Code](../src/questions/q485/Q485TravelingSalesperson.java) |
| Q486 | [Generate every prime up to a limit](../docs/questions/q486.md) | Sieve of Eratosthenes | Easy | [Code](../src/questions/q486/Q486PrimeSieve.java) |
| Q487 | [Factor a positive integer into prime factors](../docs/questions/q487.md) | Trial division | Easy | [Code](../src/questions/q487/Q487PrimeFactorization.java) |
| Q488 | [Raise a value to a large power modulo m](../docs/questions/q488.md) | Exponentiation by squaring | Medium | [Code](../src/questions/q488/Q488ModularExponentiation.java) |
| Q489 | [Find a modular inverse using extended Euclid](../docs/questions/q489.md) | Number theory | Medium | [Code](../src/questions/q489/Q489ModularInverse.java) |
| Q490 | [Compute an exact combination count without factorial overflow](../docs/questions/q490.md) | BigInteger arithmetic | Medium | [Code](../src/questions/q490/Q490BinomialCoefficient.java) |
| Q491 | [Count binary-tree shapes with exact Catalan numbers](../docs/questions/q491.md) | Combinatorial dynamic programming | Medium | [Code](../src/questions/q491/Q491CatalanNumbers.java) |
| Q492 | [Compute a huge-index Fibonacci residue with matrix powers](../docs/questions/q492.md) | Matrix exponentiation | Medium | [Code](../src/questions/q492/Q492MatrixFibonacci.java) |
| Q493 | [Sample k stream values uniformly with bounded storage](../docs/questions/q493.md) | Randomized streaming | Medium | [Code](../src/questions/q493/Q493ReservoirSample.java) |
| Q494 | [Support insert remove and uniform random lookup](../docs/questions/q494.md) | Array plus hash index | Medium | [Code](../src/questions/q494/Q494RandomizedSet.java) |
| Q495 | [Implement an LRU cache with a hash map and linked nodes](../docs/questions/q495.md) | Cache design | Hard | [Code](../src/questions/q495/Q495ManualLruCache.java) |
| Q496 | [Evict the least frequently used cache entry with LRU ties](../docs/questions/q496.md) | Cache frequency buckets | Hard | [Code](../src/questions/q496/Q496LfuCache.java) |
| Q497 | [Expire cache entries without deleting newer replacements](../docs/questions/q497.md) | Hash map and expiration heap | Hard | [Code](../src/questions/q497/Q497ExpiringCache.java) |
| Q498 | [Limit arrivals with a periodically refilled token bucket](../docs/questions/q498.md) | Rate-limiter simulation | Medium | [Code](../src/questions/q498/Q498TokenBucket.java) |
| Q499 | [Build an approximate membership filter with no false negatives](../docs/questions/q499.md) | Bloom filters | Medium | [Code](../src/questions/q499/Q499BloomMembership.java) |
| Q500 | [Read historical array values without copying every snapshot](../docs/questions/q500.md) | Versioned histories and binary search | Hard | [Code](../src/questions/q500/Q500SnapshotArray.java) |
