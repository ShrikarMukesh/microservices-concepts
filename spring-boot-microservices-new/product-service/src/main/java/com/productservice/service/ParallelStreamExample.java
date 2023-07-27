package com.productservice.service;

import java.util.Arrays;
import java.util.List;

public class ParallelStreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Sequential stream
        int sumOfSquaresSequential = numbers.stream()
                .mapToInt(num -> {
                    System.out.println("Sequential: " + num + " - Thread: " + Thread.currentThread().getName());
                    return num * num;
                })
                .sum();
        System.out.println("Sum of squares (sequential): " + sumOfSquaresSequential);

        // Parallel stream
        int sumOfSquaresParallel =
                numbers.parallelStream()
                .mapToInt(num -> {
                    System.out.println("Parallel: " + num + " - Thread: " + Thread.currentThread().getName());
                    return num * num;
                })
                .sum();
        System.out.println("Sum of squares (parallel): " + sumOfSquaresParallel);
    }
}

