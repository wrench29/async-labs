package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    Matrix A, B;
    final int rows = 1000;
    final int columns = 1000;

    @Setup
    public void setup() {
        A = new Matrix(rows, columns, true);
        B = new Matrix(rows, columns, true);
    }

    @Benchmark
    public Matrix benchmarkThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        return Matrix.multiply(A, B, threadPool);
    }

    @Benchmark
    public Matrix benchmarkVirtualThreads() {
        ExecutorService virtualThreads = Executors.newVirtualThreadPerTaskExecutor();
        return Matrix.multiply(A, B, virtualThreads);
    }
}
