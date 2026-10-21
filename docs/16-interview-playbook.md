# 16 — An interview solution is an argument plus a program

The interviewer needs to understand your model, the reason your approach works, and whether your code handles the promised inputs. Memorizing a familiar solution is not enough when the constraints change. The timing below is a practice routine, not a universal company rubric.

## A 40-minute practice round

**Minutes 0–5: understand.** Restate the task. Clarify empty input, duplicates, ordering, mutation, bounds, and the required return value. Ask whether “largest” means distinct and whether graph edges are weighted or directed. Work one small example by hand.

**Minutes 5–10: establish a baseline.** Describe the simplest correct method and its cost. For Two Sum, checking every pair is a legitimate O(n²) baseline. Explain the repeated work: searching for a matching partner again and again.

**Minutes 10–15: choose and justify.** A hash map stores values already seen, making complement lookup expected O(1). State the invariant: before examining index i, the map contains values from earlier indices only. Therefore a found partner is different from i. Checking before insertion prevents using the same element twice.

**Minutes 15–30: implement.** Write clear names and a single consistent contract. Avoid clever syntax that makes the invariant harder to see. Say when you mutate data. Use `long` before arithmetic when `int` might overflow; assigning an already-overflowed int expression to long is too late.

**Minutes 30–35: verify.** Trace a typical case and the most important boundary cases. Try duplicate partners `[3,3]` with target 6, no solution, one element, and extreme values. Explain your no-solution return convention.

**Minutes 35–40: discuss tradeoffs.** Compare memory, sorting plus two pointers, preservation of original indices, and constraints that would change the choice. If you are stuck, articulate what is known and the next smaller step rather than going silent.

## How to explain correctness

For an iterative algorithm, define an **invariant**: something true before and after every iteration. Explain initialization, preservation, and what it implies at termination.

Example, moving zeros: before each read, the region before the write pointer contains exactly the nonzero elements from the already-read prefix, in original order. Copying a newly seen nonzero extends that region. The write pointer never passes the read pointer, so unread values are safe. After reading all input, filling the remaining suffix with zero gives the required stable arrangement.

For recursion, explain the base case, why recursive inputs are smaller, and how correct smaller answers combine. For DP, name the state in words, list base cases, derive the recurrence, and justify the order of evaluation. For greedy, provide an exchange argument or another actual proof; “it seems optimal” is not enough.

## The minimum useful test checklist

| Concern | Example |
| --- | --- |
| No data / smallest input | Empty array, one tree node, zero target amount |
| Repetition | Duplicate keys, equal heap values, repeated characters |
| Order | Already sorted, reverse sorted, all equal |
| Boundaries | First/last index, invalid window size, out-of-range vertex |
| Numeric range | Large sums, negative numbers, sentinel collisions |
| Structure | Disconnected graph, directed cycle, skewed tree |
| Side effects | Input should remain unchanged vs documented in-place change |

Choose tests based on the algorithm's likely failure modes. You do not need to recite every possible case for every question.

## When a familiar pattern does not apply

- Binary search needs a valid ordering or monotonic predicate.
- A positive-only sliding sum window can fail when negative values appear.
- BFS minimizes edge count, not arbitrary weighted cost.
- Dijkstra requires nonnegative weights.
- An unbalanced BST can become linear-height.
- Greedy coin selection fails for arbitrary denominations.
- A hash set answers membership but does not preserve sorted order.
- Union-find tracks components but does not reconstruct a path.

## Preparation beyond the coding round

Practise explaining real projects: problem, constraints, your contribution, alternatives, failure, and measured outcome. Prepare honest behavioral examples; do not invent impact. Add design preparation at the depth required by your target role. Company requirements change and differ by level; check current official materials, such as [Amazon's software development interview topics](https://amazon.jobs/content/en/how-we-hire/interview-prep/software-development-topics).

Measure readiness through several unfamiliar problems and mock interviews, then repair repeated weaknesses. Do not use a single fast solve or a fixed solved-problem count as proof of readiness.
