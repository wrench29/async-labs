package com.example;

import java.util.List;

public interface IListOperator {
    int sum(List<Integer> list);

    double average(List<Integer> list);

    double standardDeviation(List<Integer> list);

    List<Integer> doubleElements(List<Integer> list);

    List<Integer> filterDivisibleByThree(List<Integer> list);
}
