package com.solubris.experimentation.streams.building;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.solubris.experimentation.streams.building.ArrayBuilderBench.arrayListToArray;
import static com.solubris.experimentation.streams.building.ArrayBuilderBench.streamBuilderToArray;
import static org.assertj.core.api.Assertions.assertThat;

public class ArrayBuilderBenchTest {

    @Test
    void canBuildTheDataFromArrayList() {
        Long[] result = arrayListToArray(1000);
        long count = result.length;

        assertThat(count).isEqualTo(1000);
    }

    @Test
    void canBuildTheDataFromStreamBuilder() {
        Long[] result = streamBuilderToArray(1000);
        long count = result.length;

        assertThat(count).isEqualTo(1000);
    }

    @Test
    @Tag("jmh")
    void run() throws RunnerException {
        new Runner(JmhHelper.jmhOptionsFor(ArrayBuilderBench.class)).run();
    }
}
