package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. Pascal's Triangle
 */
public class PascalsTriangle {
    public static void main(String[] args) {
        PascalsTriangle alg = new PascalsTriangle();
        System.out.println(alg.generate(1));
        System.out.println(alg.generate(2));
        System.out.println(alg.generate(3));
        System.out.println(alg.generate(4));
        System.out.println(alg.generate(5));
        System.out.println(alg.generate(6));
        System.out.println(alg.generate(12));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>(numRows);
        res.add(List.of(1));

        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(i + 1);
            row.add(1);

            for (int j = 1; j < i; j++) {
                List<Integer> integers = res.get(i - 1);
                row.add(integers.get(j - 1) + integers.get(j));
            }

            row.add(1);
            res.add(row);
        }

        return res;
    }
}
