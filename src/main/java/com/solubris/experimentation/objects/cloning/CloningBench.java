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

        public int repeat = 100000;
        public SmallValue[] smallValues = new SmallValue[repeat];
        public MediumValue[] mediumValues = new MediumValue[repeat];
        public LargeValue[] largeValues = new LargeValue[repeat];

        @Setup(Level.Trial)
        public void setup() {
            Random random = new Random(System.nanoTime());
            for (int i = 0; i < repeat; i++) {
                smallValues[i] = SmallValue.builder()
                        .withTheDouble(random.nextDouble())
                        .withTheInt(random.nextInt())
                        .withTheLong(random.nextLong())
                        .withTheString(Integer.toString(random.nextInt()))
                        .buildByAllArgConstructor();
                mediumValues[i] = MediumValue.builder()
                        .withDouble1(random.nextDouble())
                        .withDouble2(random.nextDouble())
                        .withInt1(random.nextInt())
                        .withInt2(random.nextInt())
                        .withLong1(random.nextLong())
                        .withLong2(random.nextLong())
                        .withString1(Integer.toString(random.nextInt()))
                        .withString2(Integer.toString(random.nextInt()))
                        .buildByAllArgConstructor();
                largeValues[i] = LargeValue.builder()
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
                        .withString1(Integer.toString(random.nextInt()))
                        .withString2(Integer.toString(random.nextInt()))
                        .withString3(Integer.toString(random.nextInt()))
                        .withString4(Integer.toString(random.nextInt()))
                        .buildByAllArgConstructor();
            }
        }

        @TearDown(Level.Iteration)
        public void report() {
        }
    }

    public enum Strategy {
        CLONE {
            public void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder, int i) {
                SmallValue origValue = state.smallValues[i];
                SmallValue newValue = origValue.clone();
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }

            public void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder, int i) {
                MediumValue origValue = state.mediumValues[i];
                MediumValue newValue = origValue.clone();
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }

            public void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder, int i) {
                LargeValue origValue = state.largeValues[i];
                LargeValue newValue = origValue.clone();
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }
        },
        BUILDER_COPY_ALL_ARGS {
            public void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder, int i) {
                SmallValue origValue = state.smallValues[i];
                SmallValue newValue = SmallValue.from(origValue).buildByAllArgConstructor();
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }

            public void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder, int i) {
                MediumValue origValue = state.mediumValues[i];
                MediumValue newValue = MediumValue.from(origValue).buildByAllArgConstructor();
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }

            public void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder, int i) {
                LargeValue origValue = state.largeValues[i];
                LargeValue newValue = LargeValue.from(origValue).buildByAllArgConstructor();
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }
        },
        BUILDER_COPY_WITH_CONSTRUCTOR {
            public void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder, int i) {
                SmallValue origValue = state.smallValues[i];
                SmallValue newValue = SmallValue.from(origValue).buildByBuilderConstructor();
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }

            public void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder, int i) {
                MediumValue origValue = state.mediumValues[i];
                MediumValue newValue = MediumValue.from(origValue).buildByBuilderConstructor();
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }

            public void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder, int i) {
                LargeValue origValue = state.largeValues[i];
                LargeValue newValue = LargeValue.from(origValue).buildByBuilderConstructor();
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }
        },
        BUILDER_COPY_WITH_REUSE {
            public void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder, int i) {
                SmallValue origValue = state.smallValues[i];
                SmallValue newValue = builder.with(origValue).buildByBuilderConstructor();
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }

            public void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder, int i) {
                MediumValue origValue = state.mediumValues[i];
                MediumValue newValue = builder.with(origValue).buildByBuilderConstructor();
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }

            public void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder, int i) {
                LargeValue origValue = state.largeValues[i];
                LargeValue newValue = builder.with(origValue).buildByBuilderConstructor();
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }
        },
        CONSTRUCTOR_COPY_ONE_ARG {
            public void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder, int i) {
                SmallValue origValue = state.smallValues[i];
                SmallValue newValue = new SmallValue(origValue);
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }

            public void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder, int i) {
                MediumValue origValue = state.mediumValues[i];
                MediumValue newValue = new MediumValue(origValue);
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }

            public void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder, int i) {
                LargeValue origValue = state.largeValues[i];
                LargeValue newValue = new LargeValue(origValue);
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }
        },
        CONSTRUCTOR_COPY_ALL_ARGS {
            public void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder, int i) {
                SmallValue origValue = state.smallValues[i];
                SmallValue newValue = new SmallValue(
                        origValue.getTheInt(),
                        origValue.getTheLong(),
                        origValue.getTheDouble(),
                        origValue.getTheString()
                );
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }

            public void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder, int i) {
                MediumValue origValue = state.mediumValues[i];
                MediumValue newValue = new MediumValue(
                        origValue.getInt1(),
                        origValue.getInt2(),
                        origValue.getLong1(),
                        origValue.getLong2(),
                        origValue.getDouble1(),
                        origValue.getDouble2(),
                        origValue.getString1(),
                        origValue.getString2()
                );
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }

            public void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder, int i) {
                LargeValue origValue = state.largeValues[i];
                LargeValue newValue = new LargeValue(
                        origValue.getInt1(),
                        origValue.getInt2(),
                        origValue.getInt3(),
                        origValue.getInt4(),
                        origValue.getLong1(),
                        origValue.getLong2(),
                        origValue.getLong3(),
                        origValue.getLong4(),
                        origValue.getDouble1(),
                        origValue.getDouble2(),
                        origValue.getDouble3(),
                        origValue.getDouble4(),
                        origValue.getString1(),
                        origValue.getString2(),
                        origValue.getString3(),
                        origValue.getString4()
                );
                if(i == 0) {
                    blackhole.consume(newValue);
                }
                assert newValue != origValue;
                assert newValue.equals(origValue);
            }
        };

        public abstract void runSmall(TheState state, Blackhole blackhole, SmallValue.Builder builder, int i);

        public abstract void runMedium(TheState state, Blackhole blackhole, MediumValue.Builder builder, int i);

        public abstract void runLarge(TheState state, Blackhole blackhole, LargeValue.Builder builder, int i);
    }

    @Benchmark
    public void small(TheState state, Blackhole blackhole) {
        SmallValue.Builder smallValueBuilder = SmallValue.builder();
        for (int i = 0; i < state.repeat; i++) {
            state.strategy.runSmall(state, blackhole, smallValueBuilder, i);
        }
    }

    @Benchmark
    public void medium(TheState state, Blackhole blackhole) {
        MediumValue.Builder mediumValueBuilder = MediumValue.builder();
        for (int i = 0; i < state.repeat; i++) {
            state.strategy.runMedium(state, blackhole, mediumValueBuilder, i);
        }
    }

    @Benchmark
    public void large(TheState state, Blackhole blackhole) {
        LargeValue.Builder largeValueBuilder = LargeValue.builder();
        for (int i = 0; i < state.repeat; i++) {
            state.strategy.runLarge(state, blackhole, largeValueBuilder, i);
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
