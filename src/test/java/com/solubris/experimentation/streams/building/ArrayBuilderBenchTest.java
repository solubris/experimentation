package com.solubris.experimentation.streams.building;

import com.solubris.experimentation.JmhHelper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;

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
