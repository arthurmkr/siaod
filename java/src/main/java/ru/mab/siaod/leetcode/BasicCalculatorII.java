package ru.mab.siaod.leetcode;


/**
 * 227. Basic Calculator II
 */
public class BasicCalculatorII {

    public static void main(String[] args) {
        BasicCalculatorII calc = new BasicCalculatorII();
        System.out.println(calc.calculate("1") == 1);
        System.out.println(calc.calculate("3+1") == 4);
        System.out.println(calc.calculate("3-1") == 2);
        System.out.println(calc.calculate("3*2") == 6);
        System.out.println(calc.calculate("6/2") == 3);
        System.out.println(calc.calculate("6/2 + 3") == 6);
        System.out.println(calc.calculate("6/2 * 3") == 9);
        System.out.println(calc.calculate("3+2*2") == 7);
        System.out.println(calc.calculate(" 3/2 ") == 1);
        System.out.println(calc.calculate(" 3+5 / 2 ") == 5);
        System.out.println(calc.calculate(" 2+3+4 ") == 9);
        System.out.println(calc.calculate(" 2+3-4 ") == 1);
    }

    public int calculate(String s) {
        int[] number = {0};
        char[] charArray = s.toCharArray();
        int length = s.length();
        int i = getNextToken(0, charArray, number, length);
        int prevNumber = number[0];
        char prevOperator = '_';

        int sum = 0;
        while (i < length) {
            char curOperator = charArray[i];

            i = getNextToken(i, charArray, number, length);
            int curNumber = number[0];

            if (curOperator == '*') {
                prevNumber *= curNumber;
            } else if (curOperator == '/') {
                prevNumber /= curNumber;
            } else if (curOperator == '+' || curOperator == '-') {
                if (prevOperator == '-') {
                    sum -= prevNumber;
                } else {
                    sum += prevNumber;
                }
                prevNumber = curNumber;
                prevOperator = curOperator;
            }
        }

        if (prevOperator == '-') {
            sum -= prevNumber;
        } else {
            sum += prevNumber;
        }

        return sum;
    }

    private int getNextToken(int indexStart, char[] charArray, int[] number, int length) {
        int i = indexStart;
        for (; i < length; i++) {
            char c = charArray[i];
            if (Character.isDigit(c)) {
                break;
            }
        }

        number[0] = 0;
        for (; i < length; i++) {
            char c = charArray[i];
            if (c >= '0' && c <= '9') {
                if (number[0] > 0) {
                    number[0] *= 10;
                }
                number[0] += (c - '0');
            } else {
                break;
            }

        }

        for (; i < length; i++) {
            char c = charArray[i];
            if (c != ' ') {
                break;
            }
        }

        return i;
    }
}
