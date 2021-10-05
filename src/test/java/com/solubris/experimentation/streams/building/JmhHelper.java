package com.solubris.experimentation.streams.building;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;

public class JmhHelper {
    public static Options jmhOptionsFor(Class<?> aClass) {
        new File("target/jmh-report/").mkdirs();

        return new OptionsBuilder()
                .include(aClass.getSimpleName())
                .forks(1)
                .warmupIterations(0)
                .measurementIterations(1)
//                .addProfiler("gc")
//                .jvmArgs("-ea")
                .resultFormat(ResultFormatType.TEXT)
                .result("target/jmh-report/" + aClass.getSimpleName() + ".txt")
                .build();
    }
}
