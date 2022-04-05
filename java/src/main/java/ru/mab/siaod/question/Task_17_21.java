package ru.mab.siaod.question;

public class Task_17_21 {
    public static void main(String[] args) {
        int[] arr = {0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0};
//        int[] arr = {0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 8, 0, 2, 0, 5, 2, 0, 3, 0, 0};

        int res = func2(arr);

        System.out.println("res: " + res + ", is equal: " + (res == 26));
    }

    private static int func(int[] arr) {
        int max = 0;
        int sum = 0;
        int column = 0;
        int count = 0;
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                sum += Math.min(max, arr[i]) * count - column;
                column = 0;
                max = arr[i];
                maxIndex = i;
                count = 0;
            } else {
                count++;
                column += arr[i];
            }
        }

        max = 0;
        count = 0;
        column = 0;
        for (int i = arr.length - 1; i >= maxIndex; i--) {
            if (max < arr[i]) {
                sum += Math.min(max, arr[i]) * count - column;
                column = 0;
                max = arr[i];
                count = 0;
            } else {
                count++;
                column += arr[i];
            }
        }

        return sum;
    }

    private static int func2(int[] histo) {
        int[] leftMaxes = new int[histo.length];
        int leftMax = histo[0];
        for (int i = 0; i < histo.length; i++) {
            leftMax = Math.max(leftMax, leftMaxes[i]);
            leftMaxes[i] = leftMax;
        }

        int sum = 0;
        int rightMax = histo[histo.length - 1];
        for (int i = histo.length - 1; i >= 0; i--) {
            rightMax = Math.max(rightMax, histo[i]);
            int secondTallest = Math.max(rightMax, leftMaxes[i]);

            if (secondTallest > histo[i]) {
                sum += secondTallest - histo[i];
            }
        }

        return sum;
    }
}
