package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

public class Matrix {
    public final int columns;
    public final int rows;

    public final int[][] matrix;

    public Matrix(int columns, int rows, boolean fillRandom) {
        this.columns = columns;
        this.rows = rows;
        this.matrix = new int[rows][columns];
        if (fillRandom) {
            RandomGenerator rng = RandomGenerator.getDefault();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = rng.nextInt(100);
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static Matrix multiply(Matrix A, Matrix B, ExecutorService executor) {
        int m = A.rows;
        int n = A.columns;
        int k = B.columns;

        Matrix C = new Matrix(m, k, false);

        AtomicInteger currentRow = new AtomicInteger(0);
        AtomicInteger currentCol = new AtomicInteger(0);

        int totalElements = m * k;
        AtomicInteger completedElements = new AtomicInteger(0);

        for (int t = 0; t < totalElements; t++) {
            executor.submit(() -> {
                int row, col;

                // next element selection
                synchronized (currentRow) {
                    row = currentRow.get();
                    col = currentCol.getAndIncrement();

                    if (col == k) {
                        currentCol.set(0);
                        col = currentCol.getAndIncrement();
                        row = currentRow.incrementAndGet();
                    }
                }

                if (row < m && col < k) {
                    // calculate C[row][col]
                    int sum = 0;
                    for (int i = 0; i < n; i++) {
                        sum += A.matrix[row][i] * B.matrix[i][col];
                    }
                    C.matrix[row][col] = sum;

                    int completed = completedElements.incrementAndGet();
                    if (completed == totalElements) {
                        executor.shutdown();
                    }
                }
            });
        }
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return C;
    }
}
