# experimentation
Explore various ideas through experimentation

master has the template readme files
docs has the replaced readme files
ci updates the docs branch from master

must exclude the docs branch from ci

Alternatively, could use wiki.  can push to the project wiki repo.
but what about the replacement?
still would be good to have two branches for that.
even better would be able to replacement occur in a reentrant manner.
so template tags would need to be invisible tokens in the pages.
Can use html comments:
<!--- just --->
here's another [][comment]
[comment]: # kjhklhljk
could use a custom language, eg:

```bench::benchMark1.txt
dssdfssdf
```

but then cant have a table.  is that a problem?

```
dssdfssdf,asdasd,sdfdf,dfgdfg
```

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

