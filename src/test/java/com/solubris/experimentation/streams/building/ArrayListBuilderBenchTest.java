package com.solubris.experimentation.streams.building;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.solubris.experimentation.streams.building.ArrayListBuilderBench.arrayList;
import static com.solubris.experimentation.streams.building.ArrayListBuilderBench.streamBuilderToArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListBuilderBenchTest {

    @Test
    void canBuildTheDataFromArrayList() {
        List<Long> result = arrayList(1000);
        long count = result.size();

        assertThat(count).isEqualTo(1000);
    }

    @Test
    void canBuildTheDataFromStreamBuilder() {
        List<Long> result = streamBuilderToArrayList(1000);
        long count = result.size();

        assertThat(count).isEqualTo(1000);
    }

    @Test
    @Tag("jmh")
    void run() throws RunnerException {
        new Runner(JmhHelper.jmhOptionsFor(ArrayListBuilderBench.class)).run();
    }
}
