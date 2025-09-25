package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 22. Generate Parentheses
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        GenerateParentheses alg = new GenerateParentheses();
//        System.out.println(alg.generateParenthesis(1));
//        System.out.println(alg.generateParenthesis(2));
//        System.out.println(alg.generateParenthesis(3));
        long start = System.currentTimeMillis();
        alg.generateParenthesis(8);
        System.out.println(System.currentTimeMillis() - start);
    }

    public List<String> generateParenthesis(int n) {
        return getStrings(n);
    }
    private List<String> getStrings(int n) {
        if (n == 1) {
            return new ArrayList<>(List.of("()"));
        }
        List<String> strings = generateParenthesis(n - 1);

        List<String> res = new ArrayList<>();
        for (String string : strings) {
            res.add("(" + string + ")");
            res.add(strings + "()");

        }

        for (int i = 0; i < strings.size() - 1; i++) {
            res.add("()" + strings.get(i));
        }

        return res;
    }
}
