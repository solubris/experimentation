package com.solubris.experimentation.objects.cloning;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;

@Warmup(iterations = 4)
@Measurement(iterations = 3)
public class CloningBench {

    @State(Scope.Benchmark)
    public static class TheState {
        @Param
        public Strategy strategy;

        public SmallValue smallValue;
        public MediumValue mediumValue;
        public LargeValue largeValue;

        @Setup(Level.Trial)
        public void setup() {
            Random random = new Random();
            smallValue = new SmallValue.Builder()
                    .withTheDouble(random.nextDouble())
                    .withTheInt(random.nextInt())
                    .withTheLong(random.nextLong())
                    .withTheString("" + random.nextInt())
                    .build();
            mediumValue = new MediumValue.Builder()
                    .withDouble1(random.nextDouble())
                    .withDouble2(random.nextDouble())
                    .withInt1(random.nextInt())
                    .withInt2(random.nextInt())
                    .withLong1(random.nextLong())
                    .withLong2(random.nextLong())
                    .withString1("" + random.nextInt())
                    .withString2("" + random.nextInt())
                    .build();
            largeValue = new LargeValue.Builder()
                    .withDouble1(random.nextDouble())
                    .withDouble2(random.nextDouble())
                    .withDouble3(random.nextDouble())
                    .withDouble4(random.nextDouble())
                    .withInt1(random.nextInt())
                    .withInt2(random.nextInt())
                    .withInt3(random.nextInt())
                    .withInt4(random.nextInt())
                    .withLong1(random.nextLong())
                    .withLong2(random.nextLong())
                    .withLong3(random.nextLong())
                    .withLong4(random.nextLong())
                    .withString1("" + random.nextInt())
                    .withString2("" + random.nextInt())
                    .withString3("" + random.nextInt())
                    .withString4("" + random.nextInt())
                    .build();
        }

        @Setup(Level.Invocation)
        public void prepare() {
        }

        @TearDown(Level.Invocation)
        public void reset() {
        }

        @TearDown(Level.Trial)
        public void report() {
        }
    }

    public enum Strategy {
        CLONE {
            public void runSmall(TheState state, Blackhole blackhole) {
                SmallValue smallValue = state.smallValue;
                SmallValue newValue = smallValue.clone();
                assert newValue != smallValue;
                assert newValue.equals(smallValue);
                blackhole.consume(newValue);
            }

            public void runMedium(TheState state, Blackhole blackhole) {
                MediumValue mediumValue = state.mediumValue;
                MediumValue newValue = mediumValue.clone();
                assert newValue != mediumValue;
                assert newValue.equals(mediumValue);
                blackhole.consume(newValue);
            }

            public void runLarge(TheState state, Blackhole blackhole) {
                LargeValue largeValue = state.largeValue;
                LargeValue newValue = largeValue.clone();
                assert newValue != largeValue;
                assert newValue.equals(largeValue);
                blackhole.consume(newValue);
            }
        },
        COPY {
            public void runSmall(TheState state, Blackhole blackhole) {
                SmallValue smallValue = state.smallValue;
                SmallValue newValue = SmallValue.Builder.from(smallValue).build();
                assert newValue != smallValue;
                assert newValue.equals(smallValue);
                blackhole.consume(newValue);
            }

            public void runMedium(TheState state, Blackhole blackhole) {
                MediumValue mediumValue = state.mediumValue;
                MediumValue newValue = MediumValue.Builder.from(mediumValue).build();
                assert newValue != mediumValue;
                assert newValue.equals(mediumValue);
                blackhole.consume(newValue);
            }

            public void runLarge(TheState state, Blackhole blackhole) {
                LargeValue largeValue = state.largeValue;
                LargeValue newValue = LargeValue.Builder.from(largeValue).build();
                assert newValue != largeValue;
                assert newValue.equals(largeValue);
                blackhole.consume(newValue);
            }
        };

        public abstract void runSmall(TheState state, Blackhole blackhole);

        public abstract void runMedium(TheState state, Blackhole blackhole);

        public abstract void runLarge(TheState state, Blackhole blackhole);
    }

    @Benchmark
    public void small(TheState state, Blackhole blackhole) {
        state.strategy.runSmall(state, blackhole);
    }

    @Benchmark
    public void medium(TheState state, Blackhole blackhole) {
        state.strategy.runMedium(state, blackhole);
    }

    @Benchmark
    public void large(TheState state, Blackhole blackhole) {
        state.strategy.runLarge(state, blackhole);
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

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CloningBench.class.getSimpleName())
                .forks(1)
//                .addProfiler("gc")
//                .jvmArgs("-ea")
                .build();

        new Runner(opt).run();
    }

}
