package ru.mab.siaod;

public class BraceCombination {
    public static void main(String[] args) {
        int N = 10;
        f2(0, 0, N, "");
    }


    private static void f2(int close, int open, int max, String s) {
        if (s.length() >= 2 * max) {
            System.out.println(s);
            return;
        }

        if (open < max) {
            f2(close, open + 1, max, s + "(");
        }
        if (open > close) {
            f2(close + 1, open, max, s + ")");
        }
    }
}
