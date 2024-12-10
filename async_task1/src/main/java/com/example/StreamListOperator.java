package com.example;

import java.util.List;

public class StreamListOperator implements IListOperator {
    public StreamListOperator() {
    }

    @Override
    public int sum(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).reduce(0, (subtotal, el) -> subtotal + el);
    }

    @Override
    public double average(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    @Override
    public double standardDeviation(List<Integer> list) {
        double mean = this.average(list);
        double variance = list.stream()
                .mapToDouble(num -> Math.pow(num - mean, 2))
                .average()
                .orElse(0.0);
        return Math.sqrt(variance);
    }

    @Override
    public List<Integer> doubleElements(List<Integer> list) {
        return list.stream().map(v -> v * 2).toList();
    }

    @Override
    public List<Integer> filterDivisibleByThree(List<Integer> list) {
        return list.stream().filter(v -> v % 3 == 0 && v % 2 == 0).toList();
    }
}
