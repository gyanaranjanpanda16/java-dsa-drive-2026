# 13. Recursion, backtracking, greedy choices, and dynamic programming

Source: [AlgorithmPatterns.java](../src/dsa/algorithms/AlgorithmPatterns.java).

A data structure organizes information. An algorithmic pattern organizes reasoning. The same array can support backtracking, a greedy algorithm, or dynamic programming depending on the question. These ideas are language-independent; Java methods, collections, and arrays express them.

## Recursion: solve a smaller version

A recursive method needs a base case, progress toward it, and a definition of what each call returns or guarantees. Every active call consumes stack space. “Uses no extra array” does not mean O(1) memory if recursion is deep.

Backtracking adds a reversible choice: **choose → explore → undo**. Imagine selecting optional product features. For `[1,2]`, a decision tree branches on excluding or including each feature:

```text
index 0: []
  exclude 1 -> exclude 2 => []
               include 2 => [2]
  include 1 -> exclude 2 => [1]
               include 2 => [1,2]
```

Our `subsets` method keeps a working list. At a leaf, it copies the list; storing the same mutable list would make earlier results change during later work. After exploring the include branch, removing the last element restores the caller's state.

**Why it works:** every subset corresponds to one unique sequence of include/exclude decisions. Both decisions are explored for each position, so none is missed. The method enumerates by position: duplicate values can produce identical-looking output lists. With n elements there are 2ⁿ subsets; copying them costs O(n·2ⁿ) time/output storage, with O(n) call-stack and working-list space. Exhaustive feature enumeration quickly becomes impractical; pruning requires additional constraints.

## Greedy: commit only when you can justify the choice

For one meeting room, maximize the **number** of non-overlapping positive-length meetings. Our intervals are half-open `[start,end)`, so a meeting ending at 3 is compatible with one starting at 3.

Example: `[1,5)`, `[1,2)`, `[2,3)`, `[3,4)`. Choosing the longest or earliest-listed meeting yields one. Sorting by end time and repeatedly taking the next compatible meeting yields three.

**Exchange argument:** take any optimal schedule whose first meeting ends later than the earliest-finishing meeting. Replace its first meeting with the earliest-finishing one. This cannot block any later meeting because it ends no later. Therefore an optimal schedule exists containing our choice; repeat the argument on the remaining compatible meetings. This proof supports the algorithm. Merely finding successful examples does not.

The implementation sorts a copy, preserving input order, and costs O(n log n) time and O(n) extra space. It does not solve weighted-profit scheduling, multiple rooms, or resource constraints. Those are different problems.

## Dynamic programming: remember answers to repeated subproblems

Suppose coins have values `[1,3,4]` and the target is 6. Greedily taking 4 then 1 then 1 uses three coins; 3+3 uses two. A locally largest choice has no valid guarantee here.

Define `dp[t]` as the minimum number of coins making total t with unlimited reuse. Set `dp[0]=0`. For each positive total, try every possible last coin c:

```text
dp[t] = min(dp[t-c] + 1), over c <= t with reachable dp[t-c]
For [1,3,4]: dp[0..6] = [0,1,2,1,1,2,2]
```

**Why it works:** every solution has a last coin. Removing it leaves a smaller total. If the remaining solution were not optimal, replacing it with a better one would improve the whole solution. Since all coin values are positive, those smaller states have already been computed. Unreachable states stay distinct from real answers; return `-1` when the target is impossible. Nonpositive coins and negative amounts are rejected.

The table costs O(amount × numberOfCoins) time and O(amount) space. This is pseudo-polynomial in the numerical amount, not polynomial in the number of digits used to encode it. It is suitable only when the amount is reasonably bounded.

## Two-dimensional DP: sequence comparison

`lcsLength` finds the longest common **subsequence**, which may skip characters while preserving order. `abcde` and `ace` have LCS length 3. A substring would require consecutive positions.

Let `dp[i][j]` describe the first i and j characters. Matching final characters extend the diagonal solution by one. If they differ, an optimal common subsequence must omit at least one final character, so take the better of `dp[i-1][j]` and `dp[i][j-1]`. Empty-prefix states are zero. The implementation uses O(mn) time and space and compares Java UTF-16 `char` units; it is not a Unicode grapheme-aware text diff. LCS models a core sequence-matching question, while practical diff tools also optimize readability and large-file performance.

## Practice and a decision process

1. Trace the call stack and undo step for three-element subsets.
2. Add duplicate-aware subsets using sorting and branch skipping.
3. Reconstruct which coins were selected, not just their count.
4. Reconstruct an LCS and compare it with longest common substring.
5. Solve weighted interval scheduling with DP; explain why the greedy proof fails.

Ask: must I enumerate all answers? Are subproblems repeated? Can I prove a safe local choice? What state captures everything the future needs? Choose a pattern from those answers, rather than matching problem titles mechanically.
