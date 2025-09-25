package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 412. Fizz Buzz
 */
public class FizzBuzz {
    public static void main(String[] args) {
        FizzBuzz alg = new FizzBuzz();
        System.out.println(alg.fizzBuzz(3));
        System.out.println(alg.fizzBuzz(5));
        System.out.println(alg.fizzBuzz(15));
    }

    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                res.add("FizzBuzz");
            } else if (i % 3 == 0) {
                res.add("Fizz");
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }
}
