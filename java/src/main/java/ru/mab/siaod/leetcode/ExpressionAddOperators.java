package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators
 */
public class ExpressionAddOperators {
    public static void main(String[] args) {
        ExpressionAddOperators alg = new ExpressionAddOperators();
//        System.out.println(alg.addOperators("123", 6));
//        System.out.println(alg.addOperators("232", 8));
//        System.out.println(alg.addOperators("3456237490", 9191));
        System.out.println(alg.addOperators("105", 5));
    }

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();

        char[] charArray = num.toCharArray();
        func(charArray, 1, target, charArray[0] - '0', res, "" + charArray[0]);
        return res;
    }

    private void func(char[] arr, int i, int target, int cur, List<String> res, String head) {
        if (i == arr.length) {
            if (target == cur) {
                res.add(head);
            }
            return;
        }

        int curDigit = arr[i] - '0';
        int nextIndex = i + 1;

        func(arr, nextIndex, target, curDigit * cur, res, head + "*" + curDigit);
        func(arr, nextIndex, target - cur, curDigit, res, head + "+" + curDigit);
        func(arr, nextIndex, target + cur, -curDigit, res, head + "-" + curDigit);
    }
}
