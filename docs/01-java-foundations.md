# 01 — Java foundations: understand what your program is doing

Read next: [Arrays](02-arrays.md). Runnable source: [JavaBasics.java](../src/dsa/foundations/JavaBasics.java).

## Why start here?

A data structure organizes values so that particular operations are convenient. An algorithm is a sequence of steps that uses those values to answer a question. Before learning either, be comfortable tracing a loop, calling a method, and reasoning about which object a variable refers to.

Java did not invent arrays, queues, or trees. It offers language features and library implementations that make these older computer-science ideas usable with types, bounds checks, garbage collection, and common interfaces. This course explains the engineering reasons for using them; it does not invent historical motivations for Java's designers.

## A first real problem: count even sensor readings

Input: `[3, 8, 4, 7]`. Required answer: `2`.

```java
int count = 0;
for (int value : readings) {
    if (value % 2 == 0) {
        count++;
    }
}
```

| Step | Value | Even? | Count |
|---|---:|---|---:|
| Before loop | — | — | 0 |
| 1 | 3 | No | 0 |
| 2 | 8 | Yes | 1 |
| 3 | 4 | Yes | 2 |
| 4 | 7 | No | 2 |

**Why it works:** after each iteration, `count` equals the number of even readings in the prefix already processed. It starts true for an empty prefix; each iteration adds exactly one when appropriate. When the prefix is the whole array, the result is correct. This kind of persistent statement is a **loop invariant**. It is more useful than memorizing the loop.

The program visits `n` readings once: **O(n) time**. It keeps only a counter and the current value: **O(1) extra space**. The input array is not counted as newly allocated auxiliary memory.

## Values, objects, and references

Primitive variables such as `int count` contain primitive values. Array and object variables contain references.

```text
original ──┐
           ├──> [1, 2, 3]
alias ─────┘
copy ─────────> [1, 2, 3]
```

`int[] alias = original` creates another reference to the same array. Changing `alias[0]` changes the array seen through `original`. `original.clone()` creates a separate array. For an object array, that clone copies the references inside it; the referenced objects remain shared.

Java passes arguments **by value**, including reference values. A method can mutate an array through its copied reference. Assigning that parameter to a new array does not reassign the caller's variable.

## Why types and contracts matter

An `int` holds a signed 32-bit value; `long` holds a signed 64-bit value. Arithmetic may overflow silently. Widen **before** the operation: `(long) a + b`, not `(long) (a + b)`. In the example source, factorial accepts only `0..20` because `21!` exceeds `long`.

Distinguish empty data from missing data. An empty array has length zero; `null` refers to no array. New lesson methods reject null explicitly. Methods such as maximum return `OptionalInt.empty()` when an empty array has no meaningful answer.

Use `.equals()` for string content. `==` compares references for objects. Use `Arrays.equals(a, b)` to compare one-dimensional primitive-array content.

## From loops to problem solving

`isPrime(n)` checks possible divisors only up to `sqrt(n)`: if `n = a × b`, both factors cannot exceed the square root. The code uses `divisor <= n / divisor` to avoid overflowing a squared divisor. Values below two are not prime. Trial division is useful for small numbers and learning invariants; repeated large-scale prime queries need different algorithms.

Learn these tools before moving on: conditions, `for`/`while`, methods and return values, array indexing, classes, constructors, references, and exceptions. Learn generics with collections: `List<Integer>` expresses what elements a list holds. It stores boxed integers; `int[]` stores primitives.

## Practice and readiness

1. **Beginner:** print integers `1..n`; count positives, negatives, and zeros.
2. **Beginner:** sum even values using `long`; trace empty and all-negative arrays.
3. **Intermediate:** implement factorial yourself and explain why `0! = 1`.
4. **Intermediate:** improve prime testing by skipping even divisors after checking two.
5. **Explain aloud:** why does changing an array inside a method affect the caller?

Advance when you can trace these programs on paper, state input/output rules, and explain time and extra space without running the code.

Reference: [official Java learning materials](https://dev.java/learn/).
