package com.example;

import java.util.List;

public class ParallelStreamListOperator implements IListOperator {
    public ParallelStreamListOperator() {
    }

    @Override
    public int sum(List<Integer> list) {
        return list.parallelStream().mapToInt(Integer::intValue).reduce(0, (subtotal, el) -> subtotal + el);
    }

    @Override
    public double average(List<Integer> list) {
        return list.parallelStream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    @Override
    public double standardDeviation(List<Integer> list) {
        double mean = this.average(list);
        double variance = list.parallelStream()
                .mapToDouble(num -> Math.pow(num - mean, 2))
                .average()
                .orElse(0.0);
        return Math.sqrt(variance);
    }

    @Override
    public List<Integer> doubleElements(List<Integer> list) {
        return list.parallelStream().map(v -> v * 2).toList();
    }

    @Override
    public List<Integer> filterDivisibleByThree(List<Integer> list) {
        return list.parallelStream().filter(v -> v % 3 == 0).toList();
    }
}
