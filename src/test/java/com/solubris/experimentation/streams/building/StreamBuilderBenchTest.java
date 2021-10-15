package com.solubris.experimentation.streams.building;

import com.solubris.experimentation.JmhHelper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;

import java.util.stream.Stream;

import static com.solubris.experimentation.streams.building.StreamBuilderBench.*;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamBuilderBenchTest {

    @Test
    void canBuildTheDataFromArrayList() {
        Stream<Long> result = arrayListStream(1000, new Long[]{1L, 2L});
        long count = result.count();

        assertThat(count).isEqualTo(1000);
    }

    @Test
    void canBuildTheDataFromArrayListWithKnownSize() {
        Stream<Long> result = arrayListStreamWithKnownSize(1000, new Long[]{1L, 2L});
        long count = result.count();

        assertThat(count).isEqualTo(1000);
    }

    @Test
    void canBuildTheDataFromStreamBuilder() {
        Stream<Long> result = streamBuilder(1000, new Long[]{1L, 2L});
        long count = result.count();

        assertThat(count).isEqualTo(1000);
    }

    @Test
    @Tag("jmh")
    void run() throws RunnerException {
        new Runner(JmhHelper.jmhOptionsFor(StreamBuilderBench.class)).run();
    }
}
