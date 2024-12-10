package com.example;

import java.util.List;
import java.util.random.RandomGenerator;

class App {
    public static void main(String[] args) {
        List<Integer> list = createIntList(10);
        System.out.print("Integers list: ");
        printList(list);
        System.out.println(" --- Stream API operations --- ");
        runOperations(list, new StreamListOperator());
        System.out.println(" --- Parallel Stream API operations --- ");
        runOperations(list, new ParallelStreamListOperator());
    }

    private static void runOperations(List<Integer> list, IListOperator operator) {
        int sum = operator.sum(list);
        System.out.printf("Sum: %d\n", sum);
        double avg = operator.average(list);
        System.out.printf("Average: %.02f\n", avg);
        double dev = operator.standardDeviation(list);
        System.out.printf("Standard Deviation: %.02f\n", dev);
        List<Integer> doubledList = operator.doubleElements(list);
        System.out.print("Doubled list: ");
        printList(doubledList);
        List<Integer> filteredList = operator.filterDivisibleByThree(list);
        System.out.print("Filtered list: ");
        printList(filteredList);
    }

    private static List<Integer> createIntList(int len) {
        RandomGenerator rng = RandomGenerator.getDefault();
        return rng.ints(len, 1, 101).boxed().toList();
    }

    private static void printList(List<Integer> list) {
        System.out.print("[ ");
        list.forEach(v -> System.out.printf("%d ", v));
        System.out.print("]\n");
    }
}
