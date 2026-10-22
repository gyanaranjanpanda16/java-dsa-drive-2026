#!/usr/bin/env python3
"""Rebuild navigation from verified manifests; run scripts/test.sh before publishing."""
import base64
import json
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
GROUPS = [
    ("foundations", "Java basics, arrays, matrices, and Java collection APIs"),
    ("linear", "Strings, linked lists, stacks, queues, and initial hashing problems"),
    ("structures", "Trees, BSTs, heaps, graphs, and initial tries"),
    ("algorithms", "Recursion, backtracking, dynamic programming, greedy algorithms, and binary search"),
]


def spans(ids):
    """Format consecutive IDs without suggesting that numbering gaps are complete."""
    groups = []
    for number in sorted(ids):
        if groups and number == groups[-1][-1] + 1:
            groups[-1].append(number)
        else:
            groups.append([number])
    return ", ".join(f"Q{g[0]:03d}" if len(g) == 1 else f"Q{g[0]:03d}–Q{g[-1]:03d}" for g in groups)


rows = []
coverage = []
for name, description in GROUPS:
    entries = json.loads((ROOT / f"question-bank/manifests/{name}.json").read_text())
    coverage.append(f"| {spans(x['id'] for x in entries)} | {len(entries)} | {description} |")
    rows.extend(entries)
rows.sort(key=lambda x: x["id"])
ids = {x["id"] for x in rows}
assert len(ids) == len(rows), "Duplicate question IDs"
assert ids <= set(range(1, 501)), "Question ID outside 1..500"
assert len({x['className'] for x in rows}) == len(rows), "Duplicate class name"
assert {x['source'] for x in rows} == {str(p.relative_to(ROOT)) for p in (ROOT / 'src/questions').glob('q*/*.java')}, "Unindexed or missing question source"
assert {x['lesson'] for x in rows} == {str(p.relative_to(ROOT)) for p in (ROOT / 'docs/questions').glob('q*.md')}, "Unindexed or missing lesson"
for row in rows:
    assert (ROOT / row['source']).is_file(), row['source']
    assert (ROOT / row['lesson']).is_file(), row['lesson']
    assert isinstance(row.get('expectedOutput'), str) and 'PENDING' not in row['expectedOutput'], row['id']
    lesson = ROOT / row['lesson']
    text = lesson.read_text()
    marker = 'The exact printed output is recorded in the [question-bank index](../../question-bank/README.md) and checked automatically.'
    if marker in text:
        text = text.replace(marker, 'Expected printed output (verified):\n\n```text\n' + row['expectedOutput'].rstrip('\n') + '\n```')
    output = re.search(r'```text\n(.*?)\n```', text, re.S)
    assert output and output.group(1) == row['expectedOutput'].rstrip('\n'), f"Lesson sample differs: {lesson}"
    lesson.write_text(text)

count = len(rows)
missing = sorted(set(range(1, 501)) - ids)
status = f'**{count} completed and tested questions out of the requested 500.**'
if missing:
    status += f' This is a partial publication; **{len(missing)} questions remain unfinished** ({spans(missing)}).'
else:
    status += ' All IDs Q001–Q500 are included.'

intro = f'''# Solved Java question bank

{status}

Every listed question has its own runnable Java file, explanatory comments, a lesson page, sample output, and at least three self-test checks. These are educational exercises covering common techniques, not verified records of questions asked by a particular employer. Programs use Java 17-compatible standard-library APIs.

## Available programs

| Number range | Completed | Coverage |
| --- | ---: | --- |
''' + '\n'.join(coverage) + f'''
| **Total** | **{count}** | **Only completed programs appear below.** |

For the underlying concepts and real-world tradeoffs, read the [topic-based curriculum](../README.md), [Java collections guide](../docs/17-java-collections.md), and [real-world case studies](../docs/14-real-world-scenarios.md).

## Start with the first program

- [Q001 — Print every array element: explanation](../docs/questions/q001.md)
- [Q001 — Runnable, commented Java answer](../src/questions/q001/Q001PrintElements.java)

From the repository root:

```bash
bash scripts/run.sh questions.q001.Q001PrintElements
bash scripts/test.sh
```

The first command prints `10 20 30`. The second compiles everything, runs the curriculum tests, and verifies all {count} question self-tests and demonstration outputs. No external Java library is required.

Read the question before opening its answer. Write a small example, attempt a baseline, identify the invariant, then compare the commented implementation. Revisit questions independently instead of counting a copied solution as learned. The stated input contracts and complexity limits matter.

The JSON manifests store exact sample outputs; the TSV index drives the Java test runner. Maintainers can regenerate navigation with `python3 scripts/update-question-index.py` after verifying new manifest entries, then rerun all tests. Python is only needed for rebuilding navigation, not for running the Java programs.

## Browse completed answers

| ID | Question | Topic | Level | Java answer |
| --- | --- | --- | --- | --- |
'''
for row in rows:
    intro += f"| Q{row['id']:03d} | [{row['title']}](../{row['lesson']}) | {row['topic']} | {row['difficulty']} | [Code](../{row['source']}) |\n"
(ROOT / 'question-bank/README.md').write_text(intro)
(ROOT / 'question-bank/index.tsv').write_text('# id\tclassName\texpectedOutputBase64\n' + ''.join(f"{r['id']}\t{r['className']}\t{base64.b64encode(r['expectedOutput'].encode()).decode()}\n" for r in rows))
readme = ROOT / 'README.md'
text = readme.read_text()
section = f'''## Individual solved questions — {count} available

**[Browse the {count} completed questions and their Java answers](question-bank/README.md).** Each has a separate runnable file, explanatory comments, a lesson page, sample output, and self-tests. '''
section += f'This partial publication contains {count} of the requested 500 questions; {len(missing)} remain unfinished. The index identifies the exact completed ranges.\n\n' if missing else 'All 500 numbered answers are included.\n\n'
section += 'Start with [Q001: print every array element](docs/questions/q001.md), then follow the numbered answers in the index. The topic-based curriculum below explains the structures and their real-world uses.\n\n'
text = re.sub(r'## Individual solved questions.*?(?=## Start here)', lambda _: section, text, flags=re.S)
readme.write_text(text)
print(f'Indexed {count} completed questions; {len(missing)} remain.')
