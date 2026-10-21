# Solved Java question bank — partial edition

**Status: 160 completed questions out of the requested 500. Work was stopped at the user's request.** This edition publishes the answers already written; it does not claim that the full 500-question collection or every Java collection topic is finished.

Every listed question has its own runnable Java file, explanation page, sample output, and at least three self-test checks. Questions are original educational exercises covering common techniques, not verified records of questions asked by a particular employer. The code uses Java 17-compatible standard-library APIs.

## What is available

| Number range | Completed | Coverage |
| --- | ---: | --- |
| Q001–Q065 | 65 | Printing elements, basic Java, array scans, transformations, prefix sums, windows, and common array algorithms |
| Q126–Q145 | 20 | String operations, character counting, windows, and substring matching |
| Q251–Q295 | 45 | Binary trees, BSTs, and heap/priority-queue problems |
| Q376–Q405 | 30 | Recursion, backtracking, and initial dynamic-programming problems |
| **Total** | **160** | **The gaps in numbering represent unfinished work.** |

The remaining **340** requested answers are not included. Some planned topics (including dedicated linked-list, queue, stack, and map question batches) remain unfinished. The earlier [topic-based curriculum](../README.md) still contains examples and explanations for these structures.

## Start with the first program

- [Q001 — Print every array element: explanation](../docs/questions/q001.md)
- [Q001 — Runnable, commented Java answer](../src/questions/q001/Q001PrintElements.java)

From the repository root:

```bash
bash scripts/run.sh questions.q001.Q001PrintElements
bash scripts/test.sh
```

The first command prints `10 20 30`. The second compiles everything, runs the original curriculum tests, and verifies all 160 question self-tests and demonstration outputs. No paid service or external Java library is required.

The JSON manifests contain the exact expected sample outputs. The TSV index is the test runner's dependency-free lookup table. Documentation explains the accepted input domain: a sample passing does not establish correctness for arbitrary inputs outside that domain.

## Browse the completed answers

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
