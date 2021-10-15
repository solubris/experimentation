# Experimentation
Explore various ideas through experimentation

## Running
To run the benchmarks from command line:

    mvn clean compile exec:exec

Run a specific bench:

    mvn clean compile exec:exec -Djmh.benchmark=CloningBench

Run with a profiler:

    mvn clean compile exec:exec -Djmh.extra.flag=-prof -Djmh.extra.value=jfr

### GitHub Action

There is a github action which can be triggered manually to run the benchmarks:

https://github.com/solubris/experimentation/actions/workflows/bench.yml

### CI

The CI job runs tests for the benchmarks and runs them in an abbreviated manner.
The should use the -ea flag to ensure the asserts are made, but this flag can be omitted for the real benchmarks so as not interfere with the results.

## Reports

Reports are writtem in md files.  Currently in the wiki (but not sure this is a good idea).

### Result Injection

The benchmark results can be injected into the wiki pages by embedding a code block as follows:

    ```bench::StreamBuilderBench
    ```

Where StreamBuilderBench is the name of the benchmark.  This allows for the results to be replaced in a reentrant manner.



This approach could also be used for the github pages.
Which is better, wiki or github pages?
wiki won't trigger ci

https://github.com/solubris/experimentation.wiki.git


curChunk = (E[]) new Object[1 << initialChunkPower];
initialChunkPower = 4
1 << 1 = 2
1 << 2 = 4
1 << 3 = 8
1 << 4 = 16

starts with 8 spines
8  - 16, 32, 64, 128, 256, 512, 1024, 2048 - 2^(8+3)
16 - 524,288 - 2^(16+3)
32 - 34,359,738,368 - 2^(32+3)

worst case, spine arrays are copied twice

what about these?
ArrayBuilderBench
ArrayListBuilderBench

jmh profiles?
Supported profilers:
cl: Classloader profiling via standard MBeans
comp: JIT compiler profiling via standard MBeans
gc: GC profiling via standard MBeans
jfr: Java Flight Recorder profiler
pauses: Pauses profiler
perfc2c: Linux perf c2c profiler
safepoints: Safepoints profiler
stack: Simple and naive Java stack profiler

different jvm's?
each jvm is a separate run, so how to push results to wiki?
need to pass the jvm into the scripts
so it can be used for the replacement
the jdk version will be required in code block,
otherwise the different jvm jobs would overwrite each other
it would be nice to have results from different jvm's in the one block, so they can be compared
only way is to append a jvm column onto the results
this should not be too hard to do
easy to add the column, but how to combine the results from different jobs in one block?
when replacing the block, it could match against the jvm and only replace those lines
- extract block
- remove lines matching jdk
- append new lines for the jdk
- sort lines - need to sort don't know where new lines fit in

need to sort based on columns, csv would be better for this
csv output won't look nice for the md files
could use csv if there was a way to map to a table in the md file

Another way is to combine the results from all the jobs, eg:
job2:
needs: job1

Will this wait for all the jobs to complete? Yes, it works brilliantly

Following the maven dir structure:
src/main/java
src/test/java
src/cicd/bash
src/cicd/groovy
src/cicd/pipeline
