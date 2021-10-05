package com.solubris.experimentation.streams.building;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Warmup(iterations = 4)
@Measurement(iterations = 3)
public class ArrayBuilderBench {

    @State(Scope.Benchmark)
    public static class TheState {
        public long expectedTotal;

//        long initialStealCount;

        @Param
        public Strategy strategy;

        //        @Param({"10000", "100000", "1000000", "10000000"})
        @Param({"1000000"})
        public int size;

//        @Param({"false", "true"})
//        @Param({"true"})
//        public boolean parallel;

        @Setup(Level.Trial)
        public void setup() {
//            initialStealCount = ForkJoinPool.commonPool().getStealCount();
        }

        @Setup(Level.Invocation)
        public void prepare() {
            expectedTotal = size;
        }

        @TearDown(Level.Invocation)
        public void reset() {
        }

        @TearDown(Level.Trial)
        public void report() {
//            System.out.println("Steal Count: " + (ForkJoinPool.commonPool().getStealCount() - initialStealCount));
        }
    }

    public enum Strategy {
        ARRAY_LIST {
            public Long[] run(TheState state, Blackhole blackhole) {
                Long[] result = arrayListToArray(state.size);
                return result;
            }
        },
        STREAM_BUILDER {
            public Long[] run(TheState state, Blackhole blackhole) {
                Long[] result = streamBuilderToArray(state.size);
                return result;
            }
        };

        public abstract Long[] run(TheState state, Blackhole blackhole);
    }

    @Benchmark
    public void buildTheStream(TheState state, Blackhole blackhole) {
        Strategy strategy = state.strategy;
        strategy.run(state, blackhole);
    }

//    @Benchmark
//    public void buildTheStreamAndCountIt(TheState state, Blackhole blackhole) {
//        Strategy strategy = state.strategy;
//        Long[] result = strategy.run(state, blackhole);
//        var count = MutableTuple.of(0L);
//        result.forEach(l -> count.compute(c -> c + 1));
//        assertThat(count.get())
//                .isEqualTo(state.size);
//    }

//    @Test
//    public void canCount() {
//        StreamBuilderBenchmark underTest = new StreamBuilderBenchmark();
//        ArrayListState state = new ArrayListState();
//        state.size = 100000;
//        state.strategy = Strategy.LONG_HOLDER_FOREACH;
//        state.parallel = false;
//        state.setup();
//        state.prepare();
//        underTest.countTheStream(state, null);
//        state.reset();
//        state.report();
//    }

    static Long[] arrayListToArray(int size) {
        List<Long> buffer = new ArrayList<>();
        for (long i = 0; i < size; i++) {
            buffer.add(i);
        }
        // better use size 0 or presized array?
        return buffer.toArray(new Long[0]);
    }

    static Long[] streamBuilderToArray(int size) {
        Stream.Builder<Long> buffer = Stream.builder();
        for (long i = 0; i < size; i++) {
            buffer.add(i);
        }
        // array copy not used here
        return buffer.build().toArray(Long[]::new);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ArrayBuilderBench.class.getSimpleName())
                .forks(1)
//                .addProfiler("gc")
//                .jvmArgs("-ea")
                .build();

        new Runner(opt).run();
    }
}
