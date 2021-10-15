package com.solubris.experimentation.objects.cloning;

import com.solubris.experimentation.JmhHelper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;

class CloningBenchTest {

    @Test
    @Tag("jmh")
    void run() throws RunnerException {
        new Runner(JmhHelper.jmhOptionsFor(CloningBench.class)).run();
    }
}