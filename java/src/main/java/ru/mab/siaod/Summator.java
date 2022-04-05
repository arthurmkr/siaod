package ru.mab.siaod;

public class Summator {
    public static void main(String[] args) {
        int a = 0b1010;
        int b = 0b1111;

        System.out.println(sum(a, b));
    }

    private static int sum(int a, int b) {
        while (b != 0) {
            int sum = a ^ b;
            int carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }
}
