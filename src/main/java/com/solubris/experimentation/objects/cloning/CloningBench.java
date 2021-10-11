package com.solubris.experimentation.objects.cloning;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;

@Warmup(iterations = 1)
@Measurement(iterations = 10, time = 1)
public class CloningBench {

    @State(Scope.Benchmark)
    public static class TheState {
        @Param
        public Strategy strategy;

        public SmallValue smallValue;
        public MediumValue mediumValue;
        public LargeValue largeValue;
        public int repeat = 10000;

        @Setup(Level.Iteration)
        public void setup() {
            Random random = new Random();
            smallValue = SmallValue.builder()
                    .withTheDouble(random.nextDouble())
                    .withTheInt(random.nextInt())
                    .withTheLong(random.nextLong())
                    .withTheString("" + random.nextInt())
                    .buildByAllArgConstructor();
            mediumValue = MediumValue.builder()
                    .withDouble1(random.nextDouble())
                    .withDouble2(random.nextDouble())
                    .withInt1(random.nextInt())
                    .withInt2(random.nextInt())
                    .withLong1(random.nextLong())
                    .withLong2(random.nextLong())
                    .withString1("" + random.nextInt())
                    .withString2("" + random.nextInt())
                    .buildByAllArgConstructor();
            largeValue = LargeValue.builder()
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
                    .buildByAllArgConstructor();
        }

        @TearDown(Level.Iteration)
        public void report() {
        }
    }

    public enum Strategy {
        CLONE {
            public void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder) {
                SmallValue origValue = state.smallValue;
                SmallValue newValue = origValue.clone();
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }

            public void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder) {
                MediumValue origValue = state.mediumValue;
                MediumValue newValue = origValue.clone();
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }

            public void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder) {
                LargeValue origValue = state.largeValue;
                LargeValue newValue = origValue.clone();
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }
        },
        COPY_WITH_BUILDER_ALL_ARGS {
            public void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder) {
                SmallValue origValue = state.smallValue;
//                SmallValue newValue = state.smallValueBuilder.with(origValue).build();
                SmallValue newValue = SmallValue.from(origValue).buildByAllArgConstructor();
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }

            public void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder) {
                MediumValue origValue = state.mediumValue;
                MediumValue newValue = MediumValue.from(origValue).buildByAllArgConstructor();
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }

            public void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder) {
                LargeValue origValue = state.largeValue;
                LargeValue newValue = LargeValue.from(origValue).buildByAllArgConstructor();
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }
        },
        COPY_WITH_BUILDER_CONSTRUCTOR {
            public void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder) {
                SmallValue origValue = state.smallValue;
//                SmallValue newValue = state.smallValueBuilder.with(origValue).build();
                SmallValue newValue = SmallValue.from(origValue).buildByBuilderConstructor();
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }

            public void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder) {
                MediumValue origValue = state.mediumValue;
                MediumValue newValue = MediumValue.from(origValue).buildByBuilderConstructor();
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }

            public void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder) {
                LargeValue origValue = state.largeValue;
                LargeValue newValue = LargeValue.from(origValue).buildByBuilderConstructor();
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }
        },
        COPY_WITH_REUSABLE_BUILDER {
            public void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder) {
                SmallValue origValue = state.smallValue;
                SmallValue newValue = builder.with(origValue).buildByBuilderConstructor();
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }

            public void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder) {
                MediumValue origValue = state.mediumValue;
                MediumValue newValue = builder.with(origValue).buildByBuilderConstructor();
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }

            public void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder) {
                LargeValue origValue = state.largeValue;
                LargeValue newValue = builder.with(origValue).buildByBuilderConstructor();
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }
        },
        COPY_WITH_CONSTRUCTOR {
            public void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder) {
                SmallValue origValue = state.smallValue;
                SmallValue newValue = new SmallValue(origValue);
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }

            public void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder) {
                MediumValue origValue = state.mediumValue;
                MediumValue newValue = new MediumValue(origValue);
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }

            public void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder) {
                LargeValue origValue = state.largeValue;
                LargeValue newValue = new LargeValue(origValue);
                assert newValue != origValue;
                assert newValue.equals(origValue);
                blackhole.consume(newValue);
            }
        };

        public abstract void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder);

        public abstract void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder);

        public abstract void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder);
    }

    @Benchmark
    public void small(TheState state, Blackhole blackhole) {
        SmallValue.Builder smallValueBuilder = SmallValue.builder();
        for (int i = 0; i < state.repeat; i++) {
            state.strategy.runSmall(state, blackhole, smallValueBuilder);
        }
    }

    @Benchmark
    public void medium(TheState state, Blackhole blackhole) {
        MediumValue.Builder mediumValueBuilder = MediumValue.builder();
        for (int i = 0; i < state.repeat; i++) {
            state.strategy.runMedium(state, blackhole, mediumValueBuilder);
        }
    }

    @Benchmark
    public void large(TheState state, Blackhole blackhole) {
        LargeValue.Builder largeValueBuilder = LargeValue.builder();
        for (int i = 0; i < state.repeat; i++) {
            state.strategy.runLarge(state, blackhole, largeValueBuilder);
        }
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
