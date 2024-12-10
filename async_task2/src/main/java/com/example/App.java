package com.example;

import java.util.concurrent.Executors;

class App {
    public static void main(String[] args) {
        Matrix A = createMatrixA();
        Matrix B = createMatrixB();

        Matrix C = Matrix.multiply(A, B, Executors.newFixedThreadPool(10));

        System.out.println("Matrix A:");
        A.print();

        System.out.println("Matrix B:");
        B.print();

        System.out.println("Result matrix C:");
        C.print();
    }

    private static Matrix createMatrixA() {
        Matrix matrix = new Matrix(3, 3, false);
        matrix.matrix[0][0] = 1;
        matrix.matrix[0][1] = 2;
        matrix.matrix[0][2] = 3;
        matrix.matrix[1][0] = 4;
        matrix.matrix[1][1] = 5;
        matrix.matrix[1][2] = 6;
        matrix.matrix[2][0] = 7;
        matrix.matrix[2][1] = 8;
        matrix.matrix[2][2] = 9;
        return matrix;
    }

    private static Matrix createMatrixB() {
        Matrix matrix = new Matrix(3, 3, false);
        matrix.matrix[0][0] = 1;
        matrix.matrix[0][1] = 0;
        matrix.matrix[0][2] = 2;
        matrix.matrix[1][0] = 0;
        matrix.matrix[1][1] = 1;
        matrix.matrix[1][2] = 2;
        matrix.matrix[2][0] = 1;
        matrix.matrix[2][1] = 2;
        matrix.matrix[2][2] = 1;
        return matrix;
    }
}
