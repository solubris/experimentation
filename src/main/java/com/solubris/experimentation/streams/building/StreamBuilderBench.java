package com.solubris.experimentation.streams.building;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Warmup(iterations = 4)
@Measurement(iterations = 3)
public class StreamBuilderBench {

    @State(Scope.Benchmark)
    public static class TheState {
        public long expectedTotal;

//        long initialStealCount;

        @Param
        public Strategy strategy;

        //        @Param({"10000", "100000", "1000000", "10000000"})
        @Param({"1000000"})
        public int size;
        public Long[] longs;

//        @Param({"false", "true"})
//        @Param({"true"})
//        public boolean parallel;

        @Setup(Level.Trial)
        public void setup() {
            Random random = new Random();
            longs = new Long[]{random.nextLong(), random.nextLong()};
            expectedTotal = size;
//            initialStealCount = ForkJoinPool.commonPool().getStealCount();
        }

        @Setup(Level.Invocation)
        public void prepare() {
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
        UNSIZED_ARRAY_LIST {
            public Stream<Long> run(TheState state, Blackhole blackhole) {
                Stream<Long> result = arrayListStream(state.size, state.longs);
                assert result != null;
                return result;
            }
        },
        SIZED_ARRAY_LIST {
            public Stream<Long> run(TheState state, Blackhole blackhole) {
                Stream<Long> result = arrayListStreamWithKnownSize(state.size, state.longs);
                assert result != null;
                return result;
            }
        },
        STREAM_BUILDER {
            public Stream<Long> run(TheState state, Blackhole blackhole) {
                Stream<Long> result = streamBuilder(state.size, state.longs);
                assert result != null;
                return result;
            }
        };

        public abstract Stream<Long> run(TheState state, Blackhole blackhole);
    }

    @Benchmark
    public void buildTheStream(TheState state, Blackhole blackhole) {
        Strategy strategy = state.strategy;
        assert state.longs.length % 2 == 0; // ensure bitwise and will work
        Stream<Long> result = strategy.run(state, blackhole);
        assert result != null;
    }

    @Benchmark
    public void buildTheStreamAndCountIt(TheState state, Blackhole blackhole) {
        Strategy strategy = state.strategy;
        assert state.longs.length % 2 == 0; // ensure bitwise and will work
        Stream<Long> result = strategy.run(state, blackhole);
        int[] countHolder = new int[1];
        result.forEach(l -> countHolder[0]++);
        assert countHolder[0] == state.size;
    }

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

    static Stream<Long> arrayListStream(int size, Long[] longs) {
        List<Long> buffer = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            buffer.add(longs[i & (longs.length-1)]);
        }
        return buffer.stream();
    }

    static Stream<Long> arrayListStreamWithKnownSize(int size, Long[] longs) {
        List<Long> buffer = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            buffer.add(longs[i & (longs.length-1)]);
        }
        return buffer.stream();
    }

    static Stream<Long> streamBuilder(int size, Long[] longs) {
        Stream.Builder<Long> buffer = Stream.builder();
        for (int i = 0; i < size; i++) {
            buffer.add(longs[i & (longs.length-1)]);
        }
        return buffer.build();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StreamBuilderBench.class.getSimpleName())
                .forks(1)
//                .addProfiler("gc")
//                .jvmArgs("-ea")
                .build();

        new Runner(opt).run();
    }
}
