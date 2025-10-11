package ru.mab.siaod.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. Letter Combinations of a Phone Number
 */
public class LetterCombinationsOfPhoneNumber {
    public static void main(String[] args) {
//        System.out.println(letterCombinations("23"));
//        System.out.println(letterCombinations(""));
//        System.out.println(letterCombinations("2"));
        System.out.println(letterCombinations("7979"));
    }

    public static List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) {
            return List.of();
        }

        char[][] charSets = new char[][]{
                {}, // 1
                {'a', 'b', 'c'}, // 2
                {'d', 'e', 'f'}, // 3
                {'g', 'h', 'i'}, // 4
                {'j', 'k', 'l'}, // 5
                {'m', 'n', 'o'}, // 6
                {'p', 'q', 'r', 's'}, // 7
                {'t', 'u', 'v'}, // 8
                {'w', 'x', 'y', 'z'} // 9
        };

        char[] arr = digits.toCharArray();
        List<String> res = new ArrayList<>();
        func(arr, 0, charSets, res, new char[arr.length]);
        return res;
    }

    private static void func(char[] arr, int i, char[][] charSets, List<String> res, char[] buf) {
        if (i >= arr.length) {
            res.add(new String(buf));
            return;
        }

        char[] charSet = charSets[arr[i] - '1'];
        for (char c : charSet) {
            buf[i] = c;
            func(arr, i + 1, charSets, res, buf);
        }
    }
}
