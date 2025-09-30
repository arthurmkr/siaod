package ru.mab.siaod.leetcode;

/**
 * 13. Roman to Integer
 */
public class RomanToInteger {
    public static void main(String[] args) {
        RomanToInteger alg = new RomanToInteger();
        System.out.println(alg.romanToInt("III"));
        System.out.println(alg.romanToInt("LVIII"));
        System.out.println(alg.romanToInt("MCMXCIV"));
    }

    public int romanToInt(String s) {
        int[] numbers = new int[27];
        numbers['M' - 'A'] = 1000;
        numbers['D' - 'A'] = 500;
        numbers['C' - 'A'] = 100;
        numbers['L' - 'A'] = 50;
        numbers['X' - 'A'] = 10;
        numbers['V' - 'A'] = 5;
        numbers['I' - 'A'] = 1;

        int sum = 0;
        int prev = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            int number = numbers[s.charAt(i) - 'A'];
            if (number < prev) {
                sum -= number;
            } else {
                sum += number;
            }
            prev = number;
        }

        return sum;
    }
}
