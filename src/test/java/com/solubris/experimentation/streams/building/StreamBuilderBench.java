package com.solubris.experimentation.streams.building;

import com.solubris.typedtuples.mutable.MutableTuple;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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
            public Stream<Long> run(TheState state, Blackhole blackhole) {
                Stream<Long> result = arrayListStream(state.size);
                assertThat(result)
                        .isNotNull();
                return result;
            }
        },
        ARRAY_LIST_KNOWN_SIZE {
            public Stream<Long> run(TheState state, Blackhole blackhole) {
                Stream<Long> result = arrayListStreamWithKnownSize(state.size);
                assertThat(result)
                        .isNotNull();
                return result;
            }
        },
        STREAM_BUILDER {
            public Stream<Long> run(TheState state, Blackhole blackhole) {
                Stream<Long> result = streamBuilder(state.size);
                assertThat(result)
                        .isNotNull();
                return result;
            }
        };

        public abstract Stream<Long> run(TheState state, Blackhole blackhole);
    }

    @Benchmark
    public void buildTheStream(TheState state, Blackhole blackhole) {
        Strategy strategy = state.strategy;
        strategy.run(state, blackhole);
    }

    @Benchmark
    public void buildTheStreamAndCountIt(TheState state, Blackhole blackhole) {
        Strategy strategy = state.strategy;
        Stream<Long> result = strategy.run(state, blackhole);
        var count = MutableTuple.of(0L);
        result.forEach(l -> count.compute(c -> c + 1));
        assertThat(count.get())
                .isEqualTo(state.size);
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

    private static Stream<Long> arrayListStream(int size) {
        List<Long> buffer = new ArrayList<>();
        for (long i = 0; i < size; i++) {
            buffer.add(i);
        }
        return buffer.stream();
    }

    @Test
    void canBuildTheDataFromArrayList() {
        Stream<Long> result = arrayListStream(1000);
        long count = result.count();

        assertThat(count).isEqualTo(1000);
    }

    private static Stream<Long> arrayListStreamWithKnownSize(int size) {
        List<Long> buffer = new ArrayList<>(size);
        for (long i = 0; i < size; i++) {
            buffer.add(i);
        }
        return buffer.stream();
    }

    @Test
    void canBuildTheDataFromArrayListWithKnownSize() {
        Stream<Long> result = arrayListStreamWithKnownSize(1000);
        long count = result.count();

        assertThat(count).isEqualTo(1000);
    }

    private static Stream<Long> streamBuilder(int size) {
        Stream.Builder<Long> buffer = Stream.builder();
        for (long i = 0; i < size; i++) {
            buffer.add(i);
        }
        return buffer.build();
    }

    @Test
    void canBuildTheDataFromStreamBuilder() {
        Stream<Long> result = streamBuilder(1000);
        long count = result.count();

        assertThat(count).isEqualTo(1000);
    }

    @Test
    @Tag("jmh")
    void run() throws RunnerException {
        new Runner(JmhHelper.jmhOptionsFor(getClass())).run();
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