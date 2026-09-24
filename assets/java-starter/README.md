# ParvoCure Java Starter

This starter contains the package structure and reference data, but not the
completed business logic.

## Structure

- `data/` — breed, tax, and shipping reference data
- `src/parvocure/` — implementation classes
- `tests/parvocure/tests/` — unit and integration tests

## Compile

From this directory:

```bash
javac -d out src/parvocure/*.java tests/parvocure/tests/*.java
```

## Run the starter tests

```bash
java -cp out parvocure.tests.UnitTests
java -cp out parvocure.tests.IntegrationTests
```

## Your job

Complete the TODOs in the implementation classes and expand both the unit and
integration tests. Keep separate classes for the different responsibilities.

The provided data intentionally contains things worth noticing. Do not assume
that every requirement or data row is automatically correct.
