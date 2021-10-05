# Stream.Builder vs ArrayList.stream()

## Hypothesis

Stream.Builder is more efficient than ArrayList.stream() for larger data sets

## Rationale

Stream.Builder uses an internal data structure that doesn't require array resizing so avoids the copy of data to a new array.
The copy operation is very fast but still could affect the efficiency of larger datasets.

## Experiments

Using JMH the following experiments were devised:

### buildTheStream

Creates the stream of the given size using the given strategy.

### buildTheStreamAndCountIt

After creating the stream, it iterates the stream.  This is might show the efficiency of iteration, as the stream produced by the Stream.Builder should be less efficient.

### Strategies




## Findings

StreamBuilderBench

```bench::StreamBuilderBench
Benchmark                                     (size)             (strategy)   Mode  Cnt   Score   Error  Units
StreamBuilderBench.buildTheStream            1000000             ARRAY_LIST  thrpt       17.791          ops/s
StreamBuilderBench.buildTheStream            1000000  ARRAY_LIST_KNOWN_SIZE  thrpt       31.290          ops/s
StreamBuilderBench.buildTheStream            1000000         STREAM_BUILDER  thrpt       31.469          ops/s
StreamBuilderBench.buildTheStreamAndCountIt  1000000             ARRAY_LIST  thrpt       15.326          ops/s
StreamBuilderBench.buildTheStreamAndCountIt  1000000  ARRAY_LIST_KNOWN_SIZE  thrpt       24.444          ops/s
StreamBuilderBench.buildTheStreamAndCountIt  1000000         STREAM_BUILDER  thrpt       21.554          ops/s
```

This shows how much more efficient using a stream build is to build a stream of unknown size than using an array list.
The ARRAY_LIST_KNOWN_SIZE strategy shows what happens if the array list of a known size.
This demonstrates that the array resizing is the only thing making the array list slower.
The buildTheStreamAndCountIt benchmark shows that the array list is more efficient for iteration.
However, the stream builder is still more efficient overall.
