package ru.mab.siaod.leetcode;

import java.util.Arrays;

public class DailyTemperatures {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 71, 69, 72, 76, 73})));
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{30, 40, 50, 60})));
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{30, 60, 90})));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        int[] stack = new int[temperatures.length];
        int top = 0;

        for (int i = 1; i < temperatures.length; i++) {
            while (top >= 0 && temperatures[i] > temperatures[stack[top]]) {
                res[stack[top]] = i - stack[top];
                top--;
            }

            top++;
            stack[top] = i;
        }

        return res;
    }
}
