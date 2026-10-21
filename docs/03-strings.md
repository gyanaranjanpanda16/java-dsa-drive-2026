# 03 — Strings: text has structure and a character model

Previous: [Arrays](02-arrays.md). Next: [Searching and sorting](04-searching-sorting.md).
Runnable source: [StringProblems.java](../src/dsa/strings/StringProblems.java).

## Why a string rather than a character array?

A string represents text with operations such as comparison, searching, and extracting sections. Java's `String` is immutable: its text does not change after creation. This helps callers safely share values and makes stable string keys practical in maps. A `char[]` is mutable, while `StringBuilder` supports assembling text without expressing every edit as a new immutable string.

```java
StringBuilder report = new StringBuilder();
for (int value : values) {
    report.append(value).append('\n');
}
String finished = report.toString();
```

Repeatedly doing `result = result + piece` inside a growing loop can repeatedly copy accumulated content, producing quadratic total copying. A builder provides a clearer model for incremental construction. Immutability does not mean a string variable cannot be reassigned; it means the existing string object's content does not change.

## First choose what “character” means

Java's `char` is a UTF-16 code unit. Some Unicode characters, such as many emoji, occupy two code units. `text.length()` counts code units, not necessarily human-perceived characters. Even code points are not identical to grapheme clusters: an accented letter can be represented by multiple code points.

The implementations state their contracts:

- `isPalindrome`: exact, case-sensitive comparison of Unicode code points; spaces and punctuation participate.
- `areAnagramsLowercase`: only English `a..z`; other characters throw an exception.
- `longestUniqueSubstring`: length and uniqueness measured in Unicode code points.

Normalization, ignoring punctuation, locale-specific case folding, and grapheme segmentation require additional rules. Never silently assume those rules from a problem title.

## Worked scenario 1: mirror validation

A puzzle accepts strings that read the same forwards and backwards. For `"level"`:

```text
l e v e l
↑       ↑  equal; move inward
  ↑   ↑    equal; move inward
    ↑      center needs no comparison
```

**Why it works:** after each successful comparison, all positions outside the remaining interval have matching mirrors. Once the pointers meet, every mirrored pair has been checked. Any mismatch proves the whole string is not a palindrome. Empty and one-code-point strings pass.

The implementation converts to code points, so both time and extra space are O(n). A restricted ASCII version could use `charAt` and O(1) extra space. Do not label this implementation O(1) space just because the pointer loop uses few variables.

## Worked scenario 2: word rearrangement

A word game asks whether `"listen"` can be rearranged into `"silent"`. Count each letter in the first word, then subtract each letter in the second.

```text
first counts:  e:1 i:1 l:1 n:1 s:1 t:1
subtract second: every count becomes 0
```

**Why it works:** rearrangement changes positions but preserves multiplicities. Equal length alone is insufficient: `"aab"` and `"abb"` have different counts. Checking a fixed alphabet requires O(a+b) time and O(26), or O(1), extra space. For general Unicode text, a map of code-point counts is more appropriate. This idea extends to inventory comparison when each symbol represents an item type.

## Worked scenario 3: longest segment without repetition

Imagine a stream of event types; find the longest consecutive run in which no type repeats. For `"abba"`:

| Right index | Symbol | Left boundary after update | Unique window |
|---:|---|---:|---|
| 0 | a | 0 | a |
| 1 | b | 0 | ab |
| 2 | b | 2 | b |
| 3 | a | 2 | ba |

Store each symbol's last index. On repetition, set `left = max(left, previous + 1)`. The `max` matters: the earlier `a` is already outside the current window and must not move the boundary backward.

**Why it works:** before adding a new symbol, the window is unique. Only that symbol can introduce a duplicate. Excluding its previous occurrence restores uniqueness while retaining the longest valid window ending here. Each right position is processed once; hash-map operations give expected O(n) total time. Code-point storage and the map use O(n) space.

## Where these ideas help—and where they do not

Frequency counts support character inventories and grouping anagrams. Sliding windows support bounded consecutive segments in logs and measurements. Prefix trees support prefix lookup; full substring search may need algorithms such as KMP. A palindrome check alone is not a realistic spell checker or text-understanding system.

## Practice ladder

**Start:** reverse restricted ASCII text, count vowels, test exact palindrome. **Build:** anagram counting, first unique symbol, longest unique substring. **Interview:** minimum covering window, group anagrams, longest palindromic substring, KMP. Before coding, specify case handling, alphabet, empty input, and what the returned indices measure.

References: [String API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/String.html), [StringBuilder API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/StringBuilder.html).
