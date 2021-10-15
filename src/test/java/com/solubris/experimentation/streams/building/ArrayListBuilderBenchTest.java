package com.solubris.experimentation.streams.building;

import com.solubris.experimentation.JmhHelper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;

import java.util.List;

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
