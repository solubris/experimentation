package com.solubris.experimentation.streams.building;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.File;

public class JmhHelper {
    public static final String TARGET_JMH_REPORT = "target/jmh-report/";

    public static Options jmhOptionsFor(Class<?> aClass) {
        mkdirs(TARGET_JMH_REPORT);

        return new OptionsBuilder()
                .include(aClass.getSimpleName())
                .forks(1)
                .warmupIterations(1)
                .warmupTime(TimeValue.milliseconds(1000))
                .measurementIterations(1)
                .measurementTime(TimeValue.microseconds(1000))
//                .addProfiler("gc")
                .jvmArgs("-ea")
                .resultFormat(ResultFormatType.TEXT)
                .result(TARGET_JMH_REPORT + aClass.getSimpleName() + ".txt")
                .build();
    }

    @SuppressWarnings("UnusedReturnValue")
    private static boolean mkdirs(String targetJmhReport) {
        return new File(targetJmhReport).mkdirs();
    }
}
