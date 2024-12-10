package com.example;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10, time = 2, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 15, time = 2, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1)
public class MyBenchmark {
    private List<Integer> list;
    private StreamListOperator streamListOperator;
    private ParallelStreamListOperator parallelStreamListOperator;

    @Setup
    public void setup() {
        list = RandomGenerator.getDefault().ints(10_000_000, 1, 101).boxed().toList();
        streamListOperator = new StreamListOperator();
        parallelStreamListOperator = new ParallelStreamListOperator();
    }

    /*
     * Stream benchmarks
     */

    @Benchmark
    public int benchmarkStreamSum() {
        return streamListOperator.sum(list);
    }

    @Benchmark
    public double benchmarkStreamAverage() {
        return streamListOperator.average(list);
    }

    @Benchmark
    public double benchmarkStreamStandardDeviation() {
        return streamListOperator.standardDeviation(list);
    }

    @Benchmark
    public List<Integer> benchmarkStreamDoubleElements() {
        return streamListOperator.doubleElements(list);
    }

    @Benchmark
    public List<Integer> benchmarkStreamFilter() {
        return streamListOperator.filterDivisibleByThree(list);
    }

    /*
     * Parallel Stream benchmarks
     */

    @Benchmark
    public int benchmarkParallelStreamSum() {
        return parallelStreamListOperator.sum(list);
    }

    @Benchmark
    public double benchmarkParallelStreamAverage() {
        return parallelStreamListOperator.average(list);
    }

    @Benchmark
    public double benchmarkParallelStreamStandardDeviation() {
        return parallelStreamListOperator.standardDeviation(list);
    }

    @Benchmark
    public List<Integer> benchmarkParallelStreamDoubleElements() {
        return parallelStreamListOperator.doubleElements(list);
    }

    @Benchmark
    public List<Integer> benchmarkParallelStreamFilter() {
        return parallelStreamListOperator.filterDivisibleByThree(list);
    }
}
