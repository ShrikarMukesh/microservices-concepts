package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StreamTests {

    @Test
    void streamTest1() {
        List<Integer> myNums = List.of(1, 2, 3, 4, 5);
        List<Integer> evenNums = myNums.stream()
                .filter(n -> n%2 == 0)
                .collect(Collectors.toList());
        System.out.println(evenNums);
    }

    @Test
    void streamTest2() {
        List<Integer> myNums = List.of(1, 2, 3, 4, 5);
        List<Integer> oddNums = myNums.stream()
                .filter(n -> n%2 != 0)
                .collect(Collectors.toList());
        System.out.println(oddNums);
    }

    @Test
    void streamTest3() {
        List<Integer> myNums = List.of(1, 2, 3, 4, 5);
        List<Integer> squaredNums = myNums.stream()
                .map(n -> n*n)
                .collect(Collectors.toList());
        System.out.println(squaredNums);
    }

}
